package cn.inovance.iotgp.common.msg.cdSimulator.bean;

public class LoginTestResult {

	private String securityCode = ""; // 安全码
	private Integer distributeCount = 0; // 设备分配数
	private Integer connectCount = 0; // 设备连接数
	private Integer onlineCount = 0; // 设备在线数
	private long currConsumeTime = 0; // 当前总耗时
	private long connectTimeSpan = 0; // 连接总耗时
	private long avgConnectTime = 0; // 平均连接时长
	private long maxConnectTime = 0; // 最大连接时长
	private long minConnectTime = 0; // 最小连接时长
	private long loginTimeSpan = 0; // 登录总耗时
	private long avgLoginTime = 0; // 平均登录时长
	private long maxLoginTime = 0; // 最大登录时长
	private long minLoginTime = 0; // 最小登录时长
	private String regCode="";

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Integer getDistributeCount() {
		return distributeCount;
	}

	public void setDistributeCount(Integer distributeCount) {
		this.distributeCount = distributeCount;
	}

	public Integer getConnectCount() {
		return connectCount;
	}

	public void setConnectCount(Integer connectCount) {
		this.connectCount = connectCount;
	}

	public Integer getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(Integer onlineCount) {
		this.onlineCount = onlineCount;
	}

	public long getCurrConsumeTime() {
		return currConsumeTime;
	}

	public void setCurrConsumeTime(long currConsumeTime) {
		this.currConsumeTime = currConsumeTime;
	}

	public long getAvgConnectTime() {
		return avgConnectTime;
	}

	public void setAvgConnectTime(long avgConnectTime) {
		this.avgConnectTime = avgConnectTime;
	}

	public long getMaxConnectTime() {
		return maxConnectTime;
	}

	public void setMaxConnectTime(long maxConnectTime) {
		this.maxConnectTime = maxConnectTime;
	}

	public long getMinConnectTime() {
		return minConnectTime;
	}

	public void setMinConnectTime(long minConnectTime) {
		this.minConnectTime = minConnectTime;
	}

	public long getAvgLoginTime() {
		return avgLoginTime;
	}

	public void setAvgLoginTime(long avgLoginTime) {
		this.avgLoginTime = avgLoginTime;
	}

	public long getMaxLoginTime() {
		return maxLoginTime;
	}

	public void setMaxLoginTime(long maxLoginTime) {
		this.maxLoginTime = maxLoginTime;
	}

	public long getMinLoginTime() {
		return minLoginTime;
	}

	public void setMinLoginTime(long minLoginTime) {
		this.minLoginTime = minLoginTime;
	}

	public long getConnectTimeSpan() {
		return connectTimeSpan;
	}

	public void setConnectTimeSpan(long connectTimeSpan) {
		this.connectTimeSpan = connectTimeSpan;
	}

	public long getLoginTimeSpan() {
		return loginTimeSpan;
	}

	public void setLoginTimeSpan(long loginTimeSpan) {
		this.loginTimeSpan = loginTimeSpan;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

}
