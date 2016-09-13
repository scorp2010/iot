package cn.inovance.iotgp.cdSimulator.util;

public class StringUtil {
	public static boolean isNullOrEmptyString(String str) {
		return str == null || "".equals(str.toString());
	}
}
