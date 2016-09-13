package cn.inovance.iotgp.cdsm.server;

import java.util.Date;
import java.util.Map;

import javax.json.Json;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.bean.CdSimulatorInfo;
import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.server.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.cdsm.util.StringUtil;
import cn.inovance.iotgp.common.msg.util.JsonBinder;
import cn.inovance.iotgp.common.msg.util.JsonUtil;

@Component
public class CdsmServerIoHandler extends IoHandlerAdapter {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	// Spring 注入处理器映射表
	private Map<String, ICdsmMessageProcessor> messageProcessorMap;

	public void setMessageProcessorMap(
			Map<String, ICdsmMessageProcessor> messageProcessorMap) {
		this.messageProcessorMap = messageProcessorMap;
	}

	/**
	 * 接收到消息时调用的方法 一般情况下 message 是一个IoBuffer 类，如果你使用了协议编解码器，那么可以强制转换为你需要的类型。
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = (String) message;
		LOGGER.debug(" messageReceived() 接收到报文：{}", msg);

		try {
			// 更新最新交互时间
			CdSimulatorInfo cdSimulatorInfo = CdsmServer.clientLinkList
					.get(session.getId());
			if (cdSimulatorInfo != null)
				cdSimulatorInfo.setLastInteractiveTime(new Date());
			
			String msgType = JsonUtil.getMsgType(msg);
			ICdsmMessageProcessor messageProcessor = messageProcessorMap
					.get(msgType);
			//System.out.println("****mgstype*****"+msgType+"------map-----"+messageProcessorMap);
			if (messageProcessor == null) {
				LOGGER.warn("msgType[{}]无法识别", msgType);
				return;
			}

			if (!messageProcessor.processMessage(session, msg)) {
				LOGGER.error("processMessage() 消息处理失败 msgType[{}] msg[{}]",
						msgType, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("processMessage() 处理消息发生未可预知错误 msg[{}]", msg);
		}
	}

	/**
	 * 这个方法当一个Session对象被创建的时候调用。 对于TCP 来说，连接被接受的时候调用，但要注意此时TCP
	 * 连接并未建立，此方法仅代表字面含义，在对象IoSession 被创建完毕的时候，回调这个方法。 对于UDP
	 * 来说，当有数据包收到的时候回调这个方法，因为UDP 是无连接的。
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LOGGER.info(session + "->建立连接");

		session.setAttribute(StaticValues.KEY_IOSESSION_ID, session.getId()); // 用于用户自定义信息缓存

		// 封装客户端信息
		CdSimulatorInfo clnt = new CdSimulatorInfo();
		clnt.setgID(session.getId());
		clnt.setConnectTime(new Date());
		clnt.setLastInteractiveTime(new Date());
		clnt.setRemoteAddress(session.getRemoteAddress().toString().substring(1));
		clnt.setSession(session);

		CdsmServer.clientLinkList.put(clnt.getgID(), clnt); // 添加到客户端连接列表

		CdsmServer.printClientListInfo(); // 打印客户端连接信息
	}

	/**
	 * 这个方法在连接被打开时调用，它总是在sessionCreated()方法之后被调用。 对于TCP
	 * 来说，它是在连接被建立之后调用，你可以在这里执行一些认证操作、发送数据等。 对于UDP
	 * 来说，这个方法与sessionCreated()没什么区别，
	 * 但是紧跟其后执行。如果你每隔一段时间，发送一些数据，那么sessionCreated()
	 * 方法只会在第一次调用，但是sessionOpened()方法每次都会调用。
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LOGGER.info(session + "->连接打开");
	}

	/**
	 * 对于TCP 来说，连接被关闭时，调用这个方法。 对于UDP 来说，IoSession 的close()方法被调用时才会毁掉这个方法。
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOGGER.info(session + "->连接关闭");

		CdSimulatorInfo cdSimulatorInfo = CdsmServer.clientLinkList
				.remove(session.getId()); // 从连接列表中移除此客户端
		if (cdSimulatorInfo != null
				&& !StringUtil.isNullOrEmptyString(cdSimulatorInfo
						.getClientCode()))
			CdsmServer.clientOnlineList.remove(cdSimulatorInfo.getClientCode()); // 从在线列表中移除此客户端

		CdsmServer.printClientListInfo(); // 打印客户端连接信息
	}

	/**
	 * 这个方法在IoSession 的通道进入空闲状态时调用，对于UDP 协议来说，这个方法始终不会被调用。
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		LOGGER.warn(session + "->空闲超时");
		session.close(true);
	}

	/**
	 * 在程序、Mina自身出现异常时回调，一般这里是关闭IoSession。
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LOGGER.error(session + "->捕获异常:" + cause.getMessage());
		session.close(false);
	}

	/**
	 * 当发送消息成功后调用这个方法。
	 * 发送消息应该在sessionOpened()、messageReceived()方法中调用IoSession.write()方法完成。
	 * 因为在sessionOpened()方法中，TCP 连接已经真正打开，同样的在messageReceived()方法TCP
	 * 连接也是打开状态，只不过两者的时机不同。
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		LOGGER.debug("->{} 已发送数据：{}", session, message.toString());
	}
}
