package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.server.bean.DeviceInfo;

public class DeviceSyncReq extends Request {

	private String customerAccount;
	
	private short syncType;

	private String oldDeviceSerial;
	
	private DeviceInfo newDeviceInfo;
	
	public DeviceSyncReq() {
		this.msgType = MsgType.DEVICE_INFO_SYNC_REQ;
	}

	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getOldDeviceSerial() {
		return oldDeviceSerial;
	}

	public void setOldDeviceSerial(String oldDeviceSerial) {
		this.oldDeviceSerial = oldDeviceSerial;
	}

	public DeviceInfo getNewDeviceInfo() {
		return newDeviceInfo;
	}

	public void setNewDeviceInfo(DeviceInfo deviceInfo) {
		this.newDeviceInfo = deviceInfo;
	}
	
}
