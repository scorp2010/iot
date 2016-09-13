package cn.inovance.iotgp.common.msg.server.bean;

import java.util.ArrayList;

public class DeviceInfo {

	private String deviceSerial;

	private short status;

	private short deviceMode;

	private String loginIpPort;
	
	private ArrayList<TargetDeviceInfo> targetDeviceInfos = new ArrayList<TargetDeviceInfo>();

	public DeviceInfo() {
	}

	public DeviceInfo(String deviceSerial, short status, short deviceMode,
			ArrayList<TargetDeviceInfo> targetDeviceInfos) {
		this.deviceSerial = deviceSerial;
		this.status = status;
		this.deviceMode = deviceMode;
		this.targetDeviceInfos = targetDeviceInfos;
	}

	public void addTargetDevice(TargetDeviceInfo targetDeviceInfo) {
		targetDeviceInfos.add(targetDeviceInfo);
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public String getLoginIpPort() {
		return loginIpPort;
	}

	public void setLoginIpPort(String loginIpPort) {
		this.loginIpPort = loginIpPort;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getDeviceMode() {
		return deviceMode;
	}

	public void setDeviceMode(short deviceMode) {
		this.deviceMode = deviceMode;
	}

	public ArrayList<TargetDeviceInfo> getTargetDeviceInfos() {
		return targetDeviceInfos;
	}

	public void setTargetDeviceInfos(
			ArrayList<TargetDeviceInfo> targetDeviceInfos) {
		this.targetDeviceInfos = targetDeviceInfos;
	}

}
