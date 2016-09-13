package cn.inovance.iotgp.cdsm.util;

import java.util.Map;

public class JsonUtil {

	@SuppressWarnings("rawtypes")
	public static String getMsgType(String jsonMsg) {
		Map jsonObject = JsonBinder.nonNullBinder.fromJson(jsonMsg, Map.class);
		String msgType = (String) jsonObject.get("msgType");
		return msgType;
	}
	
}
