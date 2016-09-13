package cn.inovance.iotgp.common.msg.server;

public class AppHeartbeatReq extends Request {

	private String account = "";

	private String domain = "";

	public AppHeartbeatReq() {
		this.msgType = MsgType.APP_HEARTBEAT_REQ;
	}

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

}
