package cn.inovance.iotgp.common.msg.cdSimulator;

public class HeartbeatReq extends Request {

	public HeartbeatReq() {
		this.msgType = MsgType.HEARTBEAT_REQ;
	}
	
	private String  securityCode="";

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
