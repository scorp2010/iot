package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.enums.DeviceStatusType;

public class TargetDeviceStatusChangeReq extends Request {

	private String deviceSerial;

	private Short type;

	private String address;

	private short status = DeviceStatusType.OffLine.value();

	public TargetDeviceStatusChangeReq() {
		this.msgType = MsgType.TARGET_DEVICE_STATUS_CHANGE_REQ;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}
