package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.server.bean.ServerMsgAddress;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

public class AppLoginReq extends Request {

	public AppLoginReq() {
		this.msgType = MsgType.APP_LOGIN_REQ;
	}

	private String account = "";

	private String password = "";

	private String domain = "";

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static void main(String[] args) {
		AppLoginReq req = new AppLoginReq();
		req.setTo((new ServerMsgAddress("gdhs")).toString());
		System.out.println(req.toString());
		AppLoginReq req1 = JsonBinder.normalBinder.fromJson(req.toString(),
				AppLoginReq.class);
		System.out.println(req1.toString());
	}

}
