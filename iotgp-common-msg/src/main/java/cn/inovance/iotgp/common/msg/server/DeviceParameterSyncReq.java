package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.CdParameter;

public class DeviceParameterSyncReq extends Request {

	/** 同步类型 */
	private short syncType;

	private String deviceSerial = "";

	/** 设备参数信息 */
	private List<CdParameter> parameters = new ArrayList<CdParameter>();

	public DeviceParameterSyncReq() {
		this.msgType = MsgType.DEVICE_PARAS_SYNC_REQ;
	}

	public void addCdParameter(String tag, String value) {
		CdParameter cdParameter = new CdParameter();
		cdParameter.setTag(tag);
		cdParameter.setValue(value);
		parameters.add(cdParameter);
	}

	public void addCdParameter(CdParameter cdParameter) {
		parameters.add(cdParameter);
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

	public List<CdParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<CdParameter> parameters) {
		this.parameters = parameters;
	}

}
