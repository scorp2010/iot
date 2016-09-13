package cn.inovance.iotgp.cdsm.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdsm.bean.CdSimulatorInfo;

public class CdsmServer extends AbstractServer implements Runnable {

	private static Logger LOGGER = LoggerFactory.getLogger(CdsmServer.class);

	private SocketAcceptor acceptor; // 服务端Socket
	public static ConcurrentHashMap<Long, CdSimulatorInfo> clientLinkList = new ConcurrentHashMap<Long, CdSimulatorInfo>(); // 客户端连接列表
	public static ConcurrentHashMap<String, CdSimulatorInfo> clientOnlineList = new ConcurrentHashMap<String, CdSimulatorInfo>(); // 客户端在线列表

	public CdsmServer() {
	}

	public void init() {
		acceptor = new NioSocketAcceptor();

		// 设置Socket通讯属性
		SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
		sessionConfig.setReadBufferSize(rcvBufferSize); // 接收缓冲区大小
		sessionConfig.setSendBufferSize(sendBufferSize); // 发送缓冲区大小
		sessionConfig.setReuseAddress(true); // 端口复用
		sessionConfig.setTcpNoDelay(true); // 关闭nagle算法（nagle算法：使Socket发送短数据包尽量合并发送，可以提高总吞吐率，但会产生一些延迟）
		sessionConfig.setKeepAlive(true); // 心跳检测(可检测拨网线时的断开)
		sessionConfig.setSoLinger(-1); // 关闭socket时不做逗留
		sessionConfig.setWriteTimeout(5000); // 写操作的超时时间
		if (idleTime > 0)
			sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, idleTime); // 读写通道均在idleTime秒内无任何操作就进入空闲状态
		sessionConfig.setOobInline(false); // 启用/禁用
											// oobinline（tcp紧急数据的接收者见sendUrgentData）
											// 默认情况下，此选项是禁用的，即在套接字上接收的 tcp
											// 紧急数据被静默丢弃。如果用户希望接收到紧急数据，则必须启用此选项。启用时，可以将紧急数据内嵌在普通数据中接收

		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

		// 绑定编解码器
		TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(
				Charset.forName("UTF-8"), DEFAULT_DELIMITER, DEFAULT_DELIMITER);
		textLineCodecFactory.setDecoderMaxLineLength(64 * 1024);
		chain.addLast("textLineCodec", new ProtocolCodecFilter(
				textLineCodecFactory));

		// 将MINA的工作线程将会和IO线程分开，提高并发性能
		chain.addLast("threadPool",
				new ExecutorFilter(Executors.newFixedThreadPool(executerNum)));

		// 绑定IO事件处理器
		if (socketIoHandler != null) {
			acceptor.setHandler(socketIoHandler);
		} else {
			LOGGER.error("【警告】socketIoHandler 未设置");
		}
	}

	/**
	 * 启动服务
	 */
	public void start() {
		try {
			acceptor.bind(new InetSocketAddress(host, port)); // 绑定端口并启动监听
			LOGGER.info("CdsmServer Bind: {}:{}", host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断开客户端连接
	 * 
	 * @param gID
	 */
	public static void closeSession(long gID) {
		IoSession session = clientLinkList.get(gID).getSession();
		closeSession(session, false);
	}

	/**
	 * 断开客户端连接
	 * 
	 * @param gID
	 * @param immediately
	 */
	public static void closeSession(long gID, boolean immediately) {
		IoSession session = clientLinkList.get(gID).getSession();
		closeSession(session, immediately);
	}

	/**
	 * 断开客户端连接
	 * 
	 * @param session
	 * @param immediately
	 */
	public static void closeSession(IoSession session, boolean immediately) {
		if (session != null) {
			session.close(immediately).awaitUninterruptibly();
		}
	}

	/**
	 * 发送报文（按gID）
	 * 
	 * @param gID
	 * @param message
	 */
	public static void sendPacket(long gID, String message) {
		IoSession session = clientLinkList.get(gID).getSession();
		sendPacket(session, message);
	}

	/**
	 * 发送报文（按clientCode）
	 * 
	 * @param clientCode
	 * @param message
	 */
	public static void sendPacket(String clientCode, String message) {
		IoSession session = clientOnlineList.get(clientCode).getSession();
		sendPacket(session, message);
	}

	/**
	 * 发送报文（按session）
	 * 
	 * @param session
	 * @param message
	 */
	public static void sendPacket(IoSession session, String message) {
		if (session != null)
			session.write(message);
	}

	/**
	 * 发送报文（广播）
	 * 
	 * @param message
	 */
	public static void brodcastPacket(String message) {
		for (CdSimulatorInfo cdSimulator : clientOnlineList.values()) {
			cdSimulator.getSession().write(message);
		}
	}

	public static void printClientListInfo() {
		LOGGER.info(
				"CdsmServer.clientLinkList.size：{} CdsmServer.clientOnlineList.size：{}",
				CdsmServer.clientLinkList.size(),
				CdsmServer.clientOnlineList.size());
	}

	@Override
	public void run() {
		this.start();
	}
}
