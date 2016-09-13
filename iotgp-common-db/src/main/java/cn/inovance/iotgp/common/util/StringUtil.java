package cn.inovance.iotgp.common.util;

/**
 * String工具类.
 */
public class StringUtil {

	/**
	 * 格式化字符串.
	 * 
	 * 例：formateString("xxx{0}bbb",1) = xxx1bbb
	 * 
	 * @param str
	 * @param params
	 * @return string
	 */
	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str
					.replace("{" + i + "}", params[i] == null ? "" : params[i]);
		}
		return str;
	}
	
	
	/**
	 * 字符串编号加n后返回
	 * @param code 被加的字符串
	 * @param addNumber 需要加的数
	 * @return
	 */
	public static String codeAdd(String code,int addNumber){
	    Long longCode = Long.parseLong(code);
	    longCode = longCode + addNumber;
	    String strCode = longCode.toString();
	    while (strCode.length() < code.length()){
	    	strCode = "0" + strCode;
	    }   
	    return strCode;
	}
	
	public static boolean isNullOrEmptyString(String str) {
		return str == null || "".equals(str.toString());
	}

}
