package cn.inovance.iotgp.cdSimulator.handler;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.util.JsonUtil;

@Component
public class CdsmClientIoHandler extends IoHandlerAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 消息处理映射,由spring注入
	private Map<String, ICdsmMessageProcessor> messageProcessorMap;
	private CdsmClient cdsmClient;

	public void setMessageProcessorMap(
			Map<String, ICdsmMessageProcessor> messageProcessorMap) {
		this.messageProcessorMap = messageProcessorMap;
	}

	public void setCdsmClient(CdsmClient cdsmClient) {
		this.cdsmClient = cdsmClient;
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		String msg = (String) message;
		logger.debug(" messageReceived() 接收到报文：{}", msg);

		try {
			String msgType = JsonUtil.getMsgType(msg);
			ICdsmMessageProcessor messageProcessor = messageProcessorMap
					.get(msgType);

			if (messageProcessor == null) {
				logger.warn("msgType[{}]无法识别", msgType);
				return;
			}

			if (!messageProcessor.processMessage(cdsmClient, msg)) {
				logger.error("processMessage() 消息处理失败 msgType[{}] msg[{}]",
						msgType, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("processMessage() 处理消息发生未可预知错误 msg[{}]", msg);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info(session + "->连接关闭");

		cdsmClient.stopClient();

		// reconnectInterval为0时不进行重连
		if (StaticValues.CDSM_ReconnectInterval > 0) {
			Thread.sleep(StaticValues.CDSM_ReconnectInterval * 1000);
			cdsmClient.connect();
		} else {
			logger.warn("与CDSM连接断开，配置为不进行重连操作");
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("与CDSM[{}]的连接异常[{}],将关闭连接", session.getRemoteAddress(),
				cause.getMessage());
		session.close(true);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		logger.info(session + "->空闲（心跳检测）超时");
		session.close(true);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info(session + "->建立连接");
	}

	@Override
	public void sessionCreated(IoSession session) {
		logger.info(session + "->创建连接");
	}
	
	@Override
	public void messageSent(IoSession session, Object message) {
		logger.debug("->{} 已发送数据：{}", session, message.toString());
	}
}
