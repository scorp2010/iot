package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.enums.OnOfflineStatus;

public class AppStatusChangeReq extends Request {

	public AppStatusChangeReq() {
		this.msgType = MsgType.APP_STATUS_CHANGE_REQ;
	}

	private String account = "";
	private String domain = "";
	private short status = OnOfflineStatus.OffLine.value();

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

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}
