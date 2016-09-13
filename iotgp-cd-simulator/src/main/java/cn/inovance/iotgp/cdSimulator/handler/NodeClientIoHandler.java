package cn.inovance.iotgp.cdSimulator.handler;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.ad.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@Component
public class NodeClientIoHandler extends IoHandlerAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 消息处理映射,由spring注入
	private Map<Integer, ICdSimulatorMessageProcessor> messageProcessorMap;
	
	private static volatile NodeClientIoHandler nodeClientIoHandler=null;
	
	private NioSocketConnector connector;

	public void setMessageProcessorMap(
			Map<Integer, ICdSimulatorMessageProcessor> messageProcessorMap) {
		this.messageProcessorMap = messageProcessorMap;
	}
	
	public static NodeClientIoHandler getInstances(NioSocketConnector connector){
		if (nodeClientIoHandler==null) {
			synchronized (NodeClientIoHandler.class) {
				if (nodeClientIoHandler==null) {
					nodeClientIoHandler=new NodeClientIoHandler(connector); 
				}
			}
		}
		return nodeClientIoHandler;
	}
	private NodeClientIoHandler(){
	}
	public NodeClientIoHandler(NioSocketConnector connector){
		this.connector=connector;
	}


	public void setConnector(NioSocketConnector connector) {
		this.connector = connector;
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) {
		byte[] msg = (byte[]) message;
		logger.debug(" messageReceived() 接收到报文：{}",
				ByteOps.bytes2HexStringWithBlank(msg));
		
		try {
			String regCode = (String) session
					.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
			NodeClient nodeClient = CdSimulatorMgr.cdSimulatorClientList
					.get(regCode);

			if (nodeClient == null) {
				logger.warn("nodeClient[{}]不在连接列表中", regCode);
				return;
			}

			int msgType = Header.getCommandId(msg);
			ICdSimulatorMessageProcessor messageReqProcessor = messageProcessorMap
					.get(msgType);
			logger.debug("****mgstype*****"+msgType+"**hex:"+Integer.toHexString(msgType)+"------map-----"+messageProcessorMap);
			if (messageReqProcessor == null) {
				logger.warn("msgType[{}]无法识别:{}", msgType, msg);
				return;
			}
			if (msgType == Commands.AD_UPDATE_QUERY_RSP){
				nodeClient.getMediaUpdateFlag().set(true);
			}
			if (!messageReqProcessor.processMessage(nodeClient, msg)) {
				logger.error("processMessage() 消息处理失败 msgType[{}] msg[{}]",
						msgType, ByteOps.bytes2HexStringWithBlank(msg));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("processMessage() 处理消息发生未可预知错误 msg[{}]",
					ByteOps.bytes2HexStringWithBlank(msg));
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info(session + "->session 连接关闭-"+session.isClosing()+" colse ");

		String regCode = (String) session
				.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
		NodeClient nodeClient = CdSimulatorMgr.cdSimulatorClientList
				.get(regCode);
		nodeClient.closed();

		// reconnectInterval为0时不进行重连
		if (StaticValues.CDAG_ReconnectInterval > 0
				&& session.getAttribute("ACTIVE_ClOSE") == null
				//&&!session.isClosing()
				) {
			logger.warn("[{}]连接断开，将进行重连操作", regCode);
			Thread.sleep(StaticValues.CDAG_ReconnectInterval * 1000);
			nodeClient.connect();
		} else {
			logger.warn("[{}]连接断开，不进行重连操作", regCode);
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("与CDAG[{}]的连接异常[{}],将关闭连接", session.getRemoteAddress(),
				cause.getMessage());
		logger.error("SessionConnectException",cause);
		cause.printStackTrace();
		session.close(true);
		connector.dispose();
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
