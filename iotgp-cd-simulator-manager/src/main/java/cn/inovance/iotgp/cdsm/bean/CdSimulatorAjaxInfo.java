package cn.inovance.iotgp.cdsm.bean;

public class CdSimulatorAjaxInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long gID = 0; // 唯一ID标识
	private String clientCode = ""; // 客户端（设备）编号
	private String remoteAddress = null; // 客户端IP:Port
	private String connectTimeStr = null; // 连接时间
	private String lastInteractiveTimeStr = null; // 最后一次交互时间
	private String loginedStr = ""; // 是否登录标识
	private int distributeCount = 0; // 分配设备数量
	private int connectCount = 0;// 连接设备数量
	private int onlineCount = 0; // 在线设备数量

	public long getgID() {
		return gID;
	}

	public void setgID(long gID) {
		this.gID = gID;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getConnectTimeStr() {
		return connectTimeStr;
	}

	public void setConnectTimeStr(String connectTimeStr) {
		this.connectTimeStr = connectTimeStr;
	}

	public String getLastInteractiveTimeStr() {
		return lastInteractiveTimeStr;
	}

	public void setLastInteractiveTimeStr(String lastInteractiveTimeStr) {
		this.lastInteractiveTimeStr = lastInteractiveTimeStr;
	}

	public String getLoginedStr() {
		return loginedStr;
	}

	public void setLoginedStr(String loginedStr) {
		this.loginedStr = loginedStr;
	}

	public int getDistributeCount() {
		return distributeCount;
	}

	public void setDistributeCount(int distributeCount) {
		this.distributeCount = distributeCount;
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}

	public int getConnectCount() {
		return connectCount;
	}

	public void setConnectCount(int connectCount) {
		this.connectCount = connectCount;
	}

}
