package cn.inovance.iotgp.common.msg.server;

public class AppLoginRsp extends Response {

	public AppLoginRsp() {
		this.msgType = MsgType.APP_LOGIN_RSP;
	}

	public AppLoginRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.APP_LOGIN_RSP;
	}

	public AppLoginRsp(AppLoginReq req) {
		super(req);
		this.msgType = MsgType.APP_LOGIN_RSP;
	}

	private String account = "";

	private String domain = "";

	private String securityCode = "";

	private String userData = "";

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

}
