package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;

import cn.inovance.iotgp.common.msg.server.bean.TargetDeviceInfo;

public class TargetDeviceSyncReq extends Request {

	private String customerAccount;
	
	/** 同步类型（1：新增 2：删除 3：替换 ）  */
	private short syncType;

	private String deviceSerial;

	private ArrayList<TargetDeviceInfo> targetDeviceInfos = new ArrayList<TargetDeviceInfo>();

	public TargetDeviceSyncReq() {
		this.msgType = MsgType.TARGET_DEVICE_SYNC_REQ;
	}

	public void addTargetDevice(TargetDeviceInfo targetDeviceInfo) {
		targetDeviceInfos.add(targetDeviceInfo);
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public ArrayList<TargetDeviceInfo> getTargetDeviceInfos() {
		return targetDeviceInfos;
	}

	public void setTargetDeviceInfos(
			ArrayList<TargetDeviceInfo> targetDeviceInfos) {
		this.targetDeviceInfos = targetDeviceInfos;
	}

}
