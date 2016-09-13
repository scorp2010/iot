package cn.inovance.iotgp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressUtil {

	// 根据常规判断一个IP地址是否为内网IP
	public static boolean isIpInternal(String ip) {
		String reg = "(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";// 正则表达式=。
		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(ip);
		return matcher.find();
	}

	// 根据前缀判断一个IP地址是否为内网IP
	public static boolean isIpInternal(String ip, String internalIpSuffix) {
		String[] internalIpSuffixArray = internalIpSuffix.split(";");
		for (String suffix : internalIpSuffixArray) {
			if (ip.startsWith(suffix))
				return true;
		}
		return false;
	}

	// 根据前缀判断一个IP地址是否为内网IP
	public static boolean isIpInternal(Integer checkType, String ip,
			String internalIpSuffix) {
		if (checkType == 0) {
			return isIpInternal(ip); // 按常规判断设备IP是否为内网IP
		} else if (checkType == 1) {
			return isIpInternal(ip, internalIpSuffix); // 按前缀判断设备IP是否为内网IP
		}
		return false;
	}

}
