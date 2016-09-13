package cn.inovance.iotgp.common.msg.server;

/**
 * @author z1979
 */
public class DataConnEstablishReq extends Request {
	private String securityCode;

	private String deviceSerial;
	
	private String tdSerial;

	public String getTdSerial() {
		return tdSerial;
	}

	public void setTdSerial(String tdSerial) {
		this.tdSerial = tdSerial;
	}

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

	public DataConnEstablishReq() {
		this.msgType = MsgType.TD_REALTIME_DATA_ESTABLISH_REQ;
	}

}
