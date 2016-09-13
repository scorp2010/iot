package cn.inovance.iotgp.common.msg.cdSimulator;

public class LoginReq extends Request {

	public LoginReq() {
		this.msgType = MsgType.LOGIN_REQ;
	}
	
	private String  securityCode="";

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
