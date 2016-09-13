package cn.inovance.iotgp.cdsm.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdsm.server.processor.IWebSocketMessageProcessor;
import cn.inovance.iotgp.cdsm.util.ApplicationUtil;
import cn.inovance.iotgp.cdsm.websocket.msg.BaseMsg;

@ServerEndpoint(value = "/websocket")
public class CdsmWebSocket {

	private static final Logger logger = LoggerFactory.getLogger(CdsmWebSocket.class);

	public static ConcurrentHashMap<String, Session> clientSessionList = new ConcurrentHashMap<String, Session>();

	@SuppressWarnings("unchecked")
	private HashMap<String, IWebSocketMessageProcessor> messageProcesserMap = (HashMap<String, IWebSocketMessageProcessor>) ApplicationUtil
			.getBean("websocketMessageProcessorMap");

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		logger.info("{}=>websocket client open", session.toString());
		clientSessionList.put(session.getId(), session);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info("{}=>websocket client close", session.toString());
		clientSessionList.remove(session.getId());
	}

	@OnError
	public void onError(Session session, Throwable thr) {
		logger.info("{}=>websocket client error", session.toString());
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		logger.info("{}=>websocket client message:{}", session.toString(),
				message);
		try {
			String msgType = BaseMsg.getMsgType(message);
			IWebSocketMessageProcessor messageProcessor = messageProcesserMap
					.get(msgType);

			if (messageProcessor == null) {
				logger.warn("msgType[{}]无法识别", msgType);
				return;
			}

			if (!messageProcessor.processMessage(session, message)) {
				logger.error("onMessage() 消息处理失败 msgType[{}] msg[{}]", msgType,
						message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("onMessage() 处理消息发生不可预知错误 msg[{}]", message);
		}
	}

	/**
	 * 发送消息
	 */
	public static void sendMsg(Session session, String message) {
		if (session == null)
			return;

		try {
			session.getBasicRemote().sendText(message);
			logger.info("{}=>websocket sent message:{}", session.toString(),
					message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 广播消息
	 */
	public static void broadcastMsg(String message) {
		for (Session session : clientSessionList.values()) {
			sendMsg(session, message);
		}
	}

}
