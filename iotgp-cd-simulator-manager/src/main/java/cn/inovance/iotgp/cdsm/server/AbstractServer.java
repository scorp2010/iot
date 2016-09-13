package cn.inovance.iotgp.cdsm.server;

import org.apache.mina.core.service.IoHandler;

public abstract class AbstractServer {

	/**
	 * 默认数据包分隔符
	 */
	protected final String DEFAULT_DELIMITER = "\r\n\r\n";

	/** 默认服务器名称 **/
	protected String serverName;
	
	/** 默认侦听IP **/
	protected String host;

	/** 默认侦听端口 **/
	protected int port;

	/** 默认标识是否采用日志. **/
	protected boolean useLog;

	/** 默认空闲时间 **/
	protected int idleTime;

	/** 默认CPU核数 **/
	protected int processorNum;
	
	/** 默认工作线程数 **/
	protected int executerNum;

	/** 接收数据缓存(8K). */
	protected int rcvBufferSize = 8 * 1024;

	/** 发送数据缓存(8K). **/
	protected int sendBufferSize = 8 * 1024;

	/** 事件处理器 **/
	protected IoHandler socketIoHandler;
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
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

	public int getProcessorNum() {
		return processorNum;
	}

	public void setProcessorNum(int processorNum) {
		this.processorNum = processorNum;
	}

	public int getExecuterNum() {
		return executerNum;
	}

	public void setExecuterNum(int executerNum) {
		this.executerNum = executerNum;
	}

	public int getRcvBufferSize() {
		return rcvBufferSize;
	}

	public void setRcvBufferSize(int rcvBufferSize) {
		this.rcvBufferSize = rcvBufferSize;
	}

	public int getSendBufferSize() {
		return sendBufferSize;
	}

	public void setSendBufferSize(int sendBufferSize) {
		this.sendBufferSize = sendBufferSize;
	}

	public IoHandler getSocketIoHandler() {
		return socketIoHandler;
	}

	public void setSocketIoHandler(IoHandler socketIoHandler) {
		this.socketIoHandler = socketIoHandler;
	}
	
}
