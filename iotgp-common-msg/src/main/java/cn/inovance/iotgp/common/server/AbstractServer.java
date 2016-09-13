package cn.inovance.iotgp.common.server;

/**
 * ClassName: AbstractServer <br/>
 * Function: 服务器抽象基类. <br/>
 * date: 2014-4-4 下午5:04:09 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public abstract class AbstractServer {

	/**
	 * 默认数据包分隔符.
	 */
	protected final String DEFAULT_DELIMITER = "\r\n\r\n";

	/** 默认侦听IP. **/
	protected String host;

	/** 默认侦听端口. **/
	protected int port;

	/** 默认CPU核数. **/
	protected int processorNum;

	/** 默认标识是否采用日志. **/
	protected boolean useLog;

	/** 默认空闲时间. **/
	protected int idleTime;

	/** 默认工作线程数目. **/
	protected int executerNum;

	/** 默认服务器名称. **/
	protected String serverName;

	/** 接收数据缓存(8K). */
	protected int rcvBufferSize = 8 * 1024;

	/** 发送数据缓存(1K). **/
	protected int sendBufferSize = 1 * 1024;

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

	public int getProcessorNum() {
		return processorNum;
	}

	public void setProcessorNum(int processorNum) {
		this.processorNum = processorNum;
	}

	public boolean isUserLog() {
		return useLog;
	}

	public void setUserLog(boolean userLog) {
		this.useLog = userLog;
	}

	public int getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

	public int getExecuterNum() {
		return executerNum;
	}

	public void setExecuterNum(int executerNum) {
		this.executerNum = executerNum;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setUseLog(boolean useLog) {
		this.useLog = useLog;
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
}
