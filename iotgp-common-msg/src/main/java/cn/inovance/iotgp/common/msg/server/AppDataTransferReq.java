package cn.inovance.iotgp.common.msg.server;

import java.nio.charset.Charset;

import cn.inovance.iotgp.common.msg.util.Base64;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

public class AppDataTransferReq extends Request {

	public AppDataTransferReq() {
		this.msgType = MsgType.APP_DATA_TRANSFER_REQ;
	}

	private String domain;

	private String account;

	private String transferData = ""; // 透传消息经Base64编码后的内容

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTransferData() {
		return transferData;
	}

	public void setTransferData(String transferData) {
		this.transferData = transferData;
	}

	public static void main(String[] args) {
		AppDataTransferReq req = new AppDataTransferReq();
		req.setFrom("xdhs");
		req.setTo("gdhs");
		req.setDomain("domain");
		req.setAccount("account");
		req.setTransferData(Base64.encodeBytes("transferData".getBytes(Charset
				.forName("utf-8"))));
		System.out.println(req.toString());
		AppDataTransferReq req1 = JsonBinder.normalBinder.fromJson(
				req.toString(), AppDataTransferReq.class);
		System.out.println(req1.toString());
	}
}
