package cn.inovance.iotgp.common.msg.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	
	public static String getMsgType(String jsonMsg) {
		JSONObject json = JSON.parseObject(jsonMsg);
		
		//System.out.println("**"+jsonMsg);
		String msgType = (String) json.get("msgType");
		return msgType;
	}
}
