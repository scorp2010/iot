package cn.inovance.iotgp.cdSimulator.processor.node.gftp;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.thread.GftpHandler;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.IGftpeMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.GftpTransparentDownMsg;
import cn.inovance.iotgp.common.msg.cd.gftp.EndTransferNofity;
import cn.inovance.iotgp.common.msg.cd.gftp.EndTransferRsp;

@Component
public class FileTransferEndNotifyProcessor extends AbstractIoProcessor implements IGftpeMessageProcessor {

	public void processMessage(IoSession session, GftpTransparentDownMsg msg) {
		EndTransferRsp rsp = new EndTransferRsp();
		GftpHandler handler = null;
		String regCode = null;
		NodeClient nodeClient = null;
		try {
			EndTransferNofity notify = new EndTransferNofity(
					msg.getGftpCommand());

			logger.info("EndTransferNofity:[{}]", notify.getValue());

			regCode = (String) session
					.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
			String mapKey = (String) session
					.getAttribute(StaticValues.KEY_IOSESSION_TEMPGUID);

			nodeClient = CdSimulatorMgr.cdSimulatorClientList
					.get(regCode);

			if (nodeClient == null) {
				logger.error("NodeClient is Null For RegisterCode:[{}]",
						regCode);
				return;
			}

			handler = (GftpHandler) (nodeClient.getThreadHandler(mapKey));

			handler.saveFile(); // 保存文件
		} catch (Exception e) {
			logger.error(e.getMessage());
			rsp.setErrorCode(EndTransferRsp.ERROR_INTERNAL);
		}

		// 发送响应报文
		GftpTransparentDownMsg downMsg = new GftpTransparentDownMsg(
				rsp.getBytes().length);
		downMsg.setGftpCommand(rsp.getBytes());
		downMsg.construct();
		session.write(downMsg.getData());

		if (handler != null)
			handler.switchSignal(GftpHandler.SIGNAL_END_TRANSFER); // 文件传输结束
	}

}
