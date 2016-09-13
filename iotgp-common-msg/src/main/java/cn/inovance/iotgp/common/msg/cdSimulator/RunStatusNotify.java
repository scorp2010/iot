package cn.inovance.iotgp.common.msg.cdSimulator;

import java.util.concurrent.ConcurrentHashMap;

import cn.inovance.iotgp.common.msg.cd.SimulateDeviceInfo;

public class RunStatusNotify extends Request {

	public RunStatusNotify() {
		this.msgType = MsgType.RUNSTATUS_NOTIFY;
	}

	private Integer distributeCount = 0; // 设备分配数
	private Integer connectCount = 0; // 设备连接数
	private Integer onlineCount = 0; // 设备在线数
	
	/**模拟设备统计信息Map*/
	private ConcurrentHashMap<String, SimulateDeviceInfo>  simulateDeviceInfoMap;
	

	public Integer getConnectCount() {
		return connectCount;
	}

	public void setConnectCount(Integer connectCount) {
		this.connectCount = connectCount;
	}

	public Integer getDistributeCount() {
		return distributeCount;
	}

	public void setDistributeCount(Integer distributeCount) {
		this.distributeCount = distributeCount;
	}

	public Integer getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(Integer onlineCount) {
		this.onlineCount = onlineCount;
	}

	public ConcurrentHashMap<String, SimulateDeviceInfo> getSimulateDeviceInfoMap() {
		return simulateDeviceInfoMap;
	}

	public void setSimulateDeviceInfoMap(ConcurrentHashMap<String, SimulateDeviceInfo> simulateDeviceInfoMap) {
		this.simulateDeviceInfoMap = simulateDeviceInfoMap;
	}

}