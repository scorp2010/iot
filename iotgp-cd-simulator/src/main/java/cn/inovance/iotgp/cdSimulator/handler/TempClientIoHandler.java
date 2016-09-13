package cn.inovance.iotgp.cdSimulator.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.processor.IGftpeMessageProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ITempClientMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.GftpTransparentDownMsg;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class TempClientIoHandler extends IoHandlerAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private SimpleDateFormat dataFmt = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	// 消息处理映射,由spring注入
	private Map<Integer, IGftpeMessageProcessor> gftpCommandProcessorMap;
	// 消息处理映射,由spring注入
	private Map<Integer, ITempClientMessageProcessor> tempClientProcessorMap;

	public void setGftpCommandProcessorMap(
			Map<Integer, IGftpeMessageProcessor> gftpCommandProcessorMap) {
		this.gftpCommandProcessorMap = gftpCommandProcessorMap;
	}

	public void setTempClientProcessorMap(
			Map<Integer, ITempClientMessageProcessor> tempClientProcessorMap) {
		this.tempClientProcessorMap = tempClientProcessorMap;
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String remoteAddress = session.getRemoteAddress().toString();
		byte[] msg = (byte[]) message; // 使用是字节编解码器，此处可以直接强制转换

		logger.debug("接收到[{}]的字节流为: {}", remoteAddress,
				ByteOps.bytes2HexStringWithBlank(msg));
		if (msg != null && msg.length >= Header.LENGTH_HEADER) {
			int msgId = Header.getCommandId(msg);
			if (msgId == Commands.TRNSPARENT_GFTP_TO_CD) {
				GftpTransparentDownMsg gftpMsg = new GftpTransparentDownMsg(msg);
				gftpMsg.parse();
				int gftpCommandId = ByteOps
						.makeShort(gftpMsg.getData()[Header.LENGTH_HEADER]);
				IGftpeMessageProcessor gftpCommandProcessor = gftpCommandProcessorMap
						.get(gftpCommandId);
				gftpCommandProcessor.processMessage(session, gftpMsg);
			} else if (msgId == Commands.VIDEO_HEADER_RSP
					|| msgId == Commands.AUDIO_HEADER_RSP
					|| msgId == Commands.DATA_CONN_HEARTBEAT_RSP) {
				ITempClientMessageProcessor processor = tempClientProcessorMap
						.get(msgId);
				processor.processMessage(session, (byte[]) message);
			} else {
				logger.warn("不支持的消息ID：{}", msgId);
				session.close(true);
			}
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.debug("{} {} {}", dataFmt.format(new Date()),
				"TempClientIoHandler sessionCreated",
				session.getRemoteAddress());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.debug("{} {} {}", dataFmt.format(new Date()),
				"TempClientIoHandler sessionOpened", session.getRemoteAddress());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.debug("{} {} {}", dataFmt.format(new Date()),
				"TempClientIoHandler sessionClosed", session.getRemoteAddress());
		String regCode = (String) session
				.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
		String mapKey = (String) session
				.getAttribute(StaticValues.KEY_IOSESSION_TEMPGUID);

		CdSimulatorMgr.cdSimulatorClientList.get(regCode).removeThreadHandler(
				mapKey);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.debug("{} {} {}", dataFmt.format(new Date()),
				"TempClientIoHandler sessionIdle", session.getRemoteAddress());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("{} {} {} {}", dataFmt.format(new Date()),
				"TempClientIoHandler exceptionCaught",
				session.getRemoteAddress(), cause.getMessage());
		session.close(true); // 关闭会话
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.debug("{} {} {}", dataFmt.format(new Date()),
				"TempClientIoHandler messageSent", session.getRemoteAddress());
	}
}
