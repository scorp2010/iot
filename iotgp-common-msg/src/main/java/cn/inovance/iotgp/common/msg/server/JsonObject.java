package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.JsonBinder;

public class JsonObject {

	private String from = "";

	private String to = "";

	private String proxy = "";

	protected String msgType = "";

	private String reserve = "";

	protected String version = "1.0";

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return JsonBinder.normalBinder.toJson(this);
	}
}
