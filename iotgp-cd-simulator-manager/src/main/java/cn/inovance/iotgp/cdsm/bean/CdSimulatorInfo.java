package cn.inovance.iotgp.cdsm.bean;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import cn.inovance.iotgp.common.msg.cd.SimulateDeviceInfo;

public class CdSimulatorInfo {

	private long gID = 0; // 唯一ID标识
	private String clientCode = ""; // 客户端（设备）编号
	private String remoteAddress = null; // 客户端IP:Port
	private IoSession session = null; // 客户端会话
	private Date connectTime = null; // 连接时间
	private Date lastInteractiveTime = null; // 最后一次交互时间
	private boolean logined = false; // 是否登录标识
	private int distributeCount = 0; // 分配设备数量
	private int connectCount = 0; // 连接设备数量
	private int onlineCount = 0; // 在线设备数量
	
	/**模拟设备统计信息Map*/
	private ConcurrentHashMap<String, SimulateDeviceInfo>  simulateDeviceInfoMap;

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

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public Date getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(Date connectTime) {
		this.connectTime = connectTime;
	}

	public Date getLastInteractiveTime() {
		return lastInteractiveTime;
	}

	public void setLastInteractiveTime(Date lastInteractiveTime) {
		this.lastInteractiveTime = lastInteractiveTime;
	}

	public boolean getLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public int getConnectCount() {
		return connectCount;
	}

	public void setConnectCount(int connectCount) {
		this.connectCount = connectCount;
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

	public ConcurrentHashMap<String, SimulateDeviceInfo> getSimulateDeviceInfoMap() {
		return simulateDeviceInfoMap;
	}

	public void setSimulateDeviceInfoMap(ConcurrentHashMap<String, SimulateDeviceInfo> simulateDeviceInfoMap) {
		this.simulateDeviceInfoMap = simulateDeviceInfoMap;
	}

}
