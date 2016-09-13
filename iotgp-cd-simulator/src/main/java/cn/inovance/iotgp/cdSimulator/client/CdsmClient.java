package cn.inovance.iotgp.cdSimulator.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.CdsmClientIoHandler;
import cn.inovance.iotgp.cdSimulator.main.CdSimulator;
import cn.inovance.iotgp.cdSimulator.util.CDSessionService;
import cn.inovance.iotgp.common.msg.cdSimulator.HeartbeatReq;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginReq;
import cn.inovance.iotgp.common.msg.cdSimulator.RunStatusNotify;

@Component
public class CdsmClient {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** 默认数据包分隔符 **/
	protected final String DEFAULT_DELIMITER = "\r\n\r\n";

	private SocketConnector connector;
	private IoSession session;
	private IoHandler ioHandler;

	private String host;
	private int port;
	private boolean useLog;
	private int idleTime;

	private boolean connected = false;
	private boolean logined = false;

	private String securityCode;
	private String sysAccount;

	public CdsmClient() {
	}

	// 启动客户端
	public void startClient() {
		init();
		connect();
	}

	// 停止客户端
	public void stopClient() {
		connected = false;
		logined = false;
	}

	/**
	 * 组件初始化
	 */
	private void init() {
		securityCode = java.util.UUID.randomUUID().toString();
		sysAccount = String.format("cdsimulator_%s@domain", securityCode);

		connector = new NioSocketConnector();
		SocketSessionConfig sessionConfig = connector.getSessionConfig();
		sessionConfig.setIdleTime(IdleStatus.READER_IDLE, idleTime);
//		sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 30000);  //读写都空闲时间:30秒
//		sessionConfig.setIdleTime(IdleStatus.READER_IDLE, 40000);//读(接收通道)空闲时间:40秒
//		sessionConfig.setIdleTime(IdleStatus.WRITER_IDLE, 50000);//写(发送通道)空闲时间:50秒
		sessionConfig.setTcpNoDelay(true);
		sessionConfig.setKeepAlive(true);
		sessionConfig.setSoLinger(-1); // close()时立即关闭不等待

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();

		// 绑定编解码器
		TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(
				Charset.forName("UTF-8"), DEFAULT_DELIMITER, DEFAULT_DELIMITER);
		textLineCodecFactory.setDecoderMaxLineLength(102400 * 1024);
		chain.addLast("textLineCodec", new ProtocolCodecFilter(
				textLineCodecFactory));

		// 绑定IO事件处理器
		if (ioHandler == null) {
			logger.error("ioHandler 未设置");
			return;
		}

		((CdsmClientIoHandler) ioHandler).setCdsmClient(this);
		connector.setHandler(ioHandler);
		
	}

	/**
	 * 连接服务器，直到连接成功
	 */
	public void connect() {
		connected = false;
		logined = false;
		ConnectFuture future = connector.connect(new InetSocketAddress(host,
				port));
		logger.info("cdsm connect status="+future.isConnected());
		if (!future.isConnected()) {
			
			// 利用监听器进行重连
			future.addListener(new IoFutureListener<ConnectFuture>() {
				public void operationComplete(ConnectFuture future) {
					try {
						logger.info("重连上"+future.isConnected());
						
						session = future.getSession();// 获取连接的session
						session.setAttribute(StaticValues.KEY_IOSESSION_SECURITYCODE,securityCode); // 缓存securityCode至Session会话中
						connected = true;
//						CDSessionService.getInstance().saveTimeoutTime(StaticValues.KEY_IOSESSION_SECURITYCODE,securityCode,24*60*60);//缓存至redis 时间24小时
						sendLoginRequest(); // 发送登录请求报文
//						logger.info("----session-address---"+session.getRemoteAddress()+"--sessionid:"+session.getId()
//								+"---securityCode="+securityCode+"--conf:"+session.getConfig()+"--handler"+session.getHandler());
						logger.info("重新连接到cdsm服务器[{}:{}]成功 ...", host,port);
					} catch (RuntimeIoException e) {
						
						try {
							logger.info("重新连接到cdsm服务器[{}:{}]失败，{}秒后重连 ...", host,
									port, StaticValues.CDSM_ReconnectInterval);
							Thread.sleep(StaticValues.CDSM_ReconnectInterval * 1000);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						connect(); // 重新连接
					}
				}
			});
		}else {
			try {
				logger.info("连接到cdsm服务器[{}:{}]成功 ...", host,port);
				session = future.getSession();// 获取连接的session
				//session.setAttribute(StaticValues.KEY_IOSESSION_SECURITYCODE,securityCode); // 缓存securityCode至Session会话中
//				CDSessionService.getInstance().saveTimeoutTime(StaticValues.KEY_IOSESSION_SECURITYCODE,securityCode,24*60*60);//缓存至redis 时间24小时
				connected = true;
				sendLoginRequest(); // 发送登录请求报文
//				logger.info("----session-address---"+session.getRemoteAddress()+"--sessionid:"+session.getId()
//						+"---securityCode="+securityCode+"--conf:"+session.getConfig()+"--handler"+session.getHandler());
			} catch (Exception e) {

				try {
					logger.info("重新连接到cdsm服务器[{}:{}]失败，{}秒后重连 ...", host,port, StaticValues.CDSM_ReconnectInterval);
					Thread.sleep(StaticValues.CDSM_ReconnectInterval * 1000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				connect(); // 重新连接
			}
		
		}
//		CDSessionService.getInstance().saveTimeoutTime(StaticValues.KEY_IOSESSION_SECURITYCODE,securityCode,24*60*60);//缓存至redis 时间24小时
//		logger.info("----session-address---"+session.getRemoteAddress()+"--sessionid:"+session.getId()
//				+"---securityCode="+securityCode+"--conf:"+session.getConfig()+"--handler"+session.getHandler());
		}

	/**
	 * 登录成功处理
	 */
	public void processLoginSucc() {
		this.logined = true;
	}

	/**
	 * 发送报文
	 */
	public boolean send(String message) {
		if (connected && session != null) {
			session.write(message);
			logger.debug("=>cdsm send message：" + message);
			return true;
		}
		return false;
	}

	/**
	 * 发送登录请求消息
	 */
	public void sendLoginRequest() {
		LoginReq req = new LoginReq();
		req.setFrom(sysAccount);
		req.setSecurityCode(securityCode);
		this.send(req.toString());
		logger.info("发送登录请求消息:{}", req.toString());
	}

	/**
	 * 发送心跳通知消息
	 */
	public void sendHeartBeat() {
		if (!logined)
			return;
		HeartbeatReq req = new HeartbeatReq();
		req.setFrom(sysAccount);
		req.setSecurityCode(securityCode);
		this.send(req.toString());
		logger.debug("发送心跳通知消息:{}", req.toString());
	}

	/**
	 * 发送运行状态通知消息
	 */
	public void sendRunStatusNotify() {
		if (!logined)
			return;
		RunStatusNotify notify = new RunStatusNotify();
		notify.setFrom(sysAccount);
		notify.setDistributeCount(CdSimulatorMgr.distribuCount);
		notify.setConnectCount(CdSimulatorMgr.cdSimulatorClientList.size());
		notify.setOnlineCount(CdSimulator.cdSimulatorMgr.getOnlineCount());
		//notify.setSimulateDeviceInfoMap(CdSimulatorMgr.simulateDeviceInfoMap);
		this.send(notify.toString());
		logger.debug("发送运行状态通知消息:{}", notify.toString());
	}

	public IoHandler getIoHandler() {
		return ioHandler;
	}

	public void setIoHandler(IoHandler ioHandler) {
		this.ioHandler = ioHandler;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public boolean isUseLog() {
		return useLog;
	}

	public void setUseLog(boolean useLog) {
		this.useLog = useLog;
	}

	public int getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

}
