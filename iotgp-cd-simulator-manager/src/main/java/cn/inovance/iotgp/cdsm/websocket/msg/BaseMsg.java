package cn.inovance.iotgp.cdsm.websocket.msg;

import java.util.Map;

import cn.inovance.iotgp.common.msg.util.JsonBinder;

public class BaseMsg {

	protected String msgType = "";

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return JsonBinder.normalBinder.toJson(this);
	}

	@SuppressWarnings("rawtypes")
	public static String getMsgType(String message) {
		Map jsonObject = JsonBinder.nonNullBinder.fromJson(message, Map.class);
		String msgType = (String) jsonObject.get("msgType");
		return msgType;
	}
}
