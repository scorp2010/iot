package cn.inovance.iotgp.cdSimulator.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdSimulator.codec.CdCodecFactory;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.main.CdSimulator;

public class TempClient {
	private static final Logger logger = LoggerFactory
			.getLogger(NodeClient.class);

	private SocketConnector connector;
	private IoSession session;

	private String serverHost = "127.0.0.1";
	private int serverPort = 5678;

	private boolean connected = false;

	private String regCode;
	private String tempSessionMapKey;

	public TempClient(String host, int port, String registerCode,
			String tempSessionMapKey) {
		this.serverHost = host;
		this.serverPort = port;
		this.regCode = registerCode;
		this.tempSessionMapKey = tempSessionMapKey;

		connector = new NioSocketConnector();

		connector.setConnectTimeoutMillis(30 * 1000);
		// 绑定编解码器
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new CdCodecFactory()));
		// 绑定IO事件处理器
		connector.setHandler(CdSimulator.tempClientIoHandler);

		SocketSessionConfig sessionConfig = connector.getSessionConfig();
		sessionConfig.setReuseAddress(true);
		sessionConfig.setTcpNoDelay(true);
		sessionConfig.setKeepAlive(true);
		sessionConfig.setSoLinger(-1); // close()时是否等待
		sessionConfig.setWriteTimeout(5000); // 写操作的超时时间
		sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 5 * 60); // 读写通道均在5分种内无任何操作就进入空闲状态
	}

	public void connect() {
		ConnectFuture cf = connector.connect(new InetSocketAddress(serverHost,
				serverPort));// 建立连接（异步）
		// cf.awaitUninterruptibly(); // 等待连接创建完成

		// 添加一个监听器（异步）
		cf.addListener(new IoFutureListener<ConnectFuture>() {
			public void operationComplete(ConnectFuture future) {
				try {
					session = future.getSession();
					session.setAttribute(StaticValues.KEY_IOSESSION_REGCODE,
							regCode); // 缓存regCode至Session会话中
					session.setAttribute(StaticValues.KEY_IOSESSION_TEMPGUID,
							tempSessionMapKey); // 缓存tempSessionMapKey至Session会话中
					connected = true;
				} catch (Exception e) {
					try {
						logger.info("建立连接[{}:{}]时出现异常，{1}秒后重连 ...", serverHost,
								serverPort, 3);
						Thread.sleep(1 * 1000);
					} catch (InterruptedException ie) {
						// ie.printStackTrace();
					}
					connect(); // 重新连接
				}
			}
		});
	}

	public void close() {
		if (session != null && session.getCloseFuture() != null)
			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
		connector.dispose();
	}

	public void send(byte[] data) {
		try {
			session.write(data); // 写入
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getTempSessionMapKey() {
		return tempSessionMapKey;
	}

	public void setTempSessionMapKey(String tempSessionMapKey) {
		this.tempSessionMapKey = tempSessionMapKey;
	}

}
