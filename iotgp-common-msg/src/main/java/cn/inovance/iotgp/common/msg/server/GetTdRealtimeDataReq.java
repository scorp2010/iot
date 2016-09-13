package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.meta.TdTag;

/**
 * @author z1979
 */
public class GetTdRealtimeDataReq extends Request {
	private String securityCode;

	private String deviceSerial;

	private String tdSerial;

	private List<TdTag> tdDataTags = new ArrayList<TdTag>();

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getTdSerial() {
		return tdSerial;
	}

	public void setTdSerial(String tdSerial) {
		this.tdSerial = tdSerial;
	}

	public List<TdTag> getTdDataTags() {
		return tdDataTags;
	}

	public void setTdDataTags(List<TdTag> tdDataTags) {
		this.tdDataTags = tdDataTags;
	}

	public void addTdDataTags(TdTag tdTag) {
		this.tdDataTags.add(tdTag);
	}

	public GetTdRealtimeDataReq() {
		this.msgType = MsgType.GET_TD_REALTIME_DATA_REQ;
	}

}
