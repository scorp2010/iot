package cn.inovance.iotgp.common.msg.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

public class IotStringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * 把Unicode码转成 中文
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		if (dataStr != null) {
			while (start > -1) {
				end = dataStr.indexOf("\\u", start + 2);
				String charStr = "";
				if (end == -1) {
					charStr = dataStr.substring(start + 2, dataStr.length());
				} else {
					charStr = dataStr.substring(start + 2, end);
				}
				char letter = (char) Integer.parseInt(charStr, 16);
				buffer.append(new Character(letter).toString());
				start = end;
			}
		}
		return buffer.toString();
	}

	/**
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * 编码ISO8859ToGB2312
	 * 
	 * @param message
	 * @return
	 */
	public static String convertFromISO8859ToGB2312(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("ISO8859-1");
			return new String(tmpbyte, "gb2312");
		}
		return "";
	}

	/**
	 * 编码UTF8ToGB2312
	 * 
	 * @param message
	 * @return
	 */
	public static String convertFromUTF8ToGB2312(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("UTF-8");
			return new String(tmpbyte, "gb2312");
		}
		return "";
	}

	/**
	 * 编码GB2312ToUTF-8
	 * 
	 * @param message
	 * @return
	 */
	public static String convertFromGB2312ToUTF8(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("gb2312");
			return new String(tmpbyte, "UTF-8");
		}
		return "";
	}

	/**
	 * 编码ISO8859ToUTF8
	 * 
	 * @param message
	 * @return
	 */
	public static String convertFromISO8859ToUTF8(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("ISO8859-1");
			return new String(tmpbyte, "UTF-8");
		}
		return "";
	}

	/**
	 * 编码UTF8ToISO8859
	 * 
	 * @param message
	 * @return
	 */
	public static String convertFromUTF8ToISO8859(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("UTF-8");
			return new String(tmpbyte, "ISO8859-1");
		}
		return "";
	}

	/**
	 * 编码ISO8859ToGBK
	 * 
	 * @author heguanhua
	 * @param message
	 * @return
	 */
	public static String convertFromISO8859ToGBK(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("ISO8859-1");
			return new String(tmpbyte, "GBK");
		}
		return "";
	}

	/**
	 * 编码GBKToISO8859
	 * 
	 * @param message
	 * @return
	 */
	public static String convertFromGBKToISO8859(String message)
			throws UnsupportedEncodingException {
		if (message != null && !message.equals("")) {
			byte[] tmpbyte = message.getBytes("GBK");
			return new String(tmpbyte, "ISO8859-1");
		}
		return "";
	}

	public String convertFormASCIIToUTF8(String message)
			throws UnsupportedEncodingException {

		String tempstr = "";
		String[] result;
		StringBuffer sb = new StringBuffer();

		int i;
		int AsciiCode;
		result = message.split(" ");
		for (i = 1; i < result.length; i++) {
			AsciiCode = Integer.parseInt(result[i]);
			if (AsciiCode < 0) {
				int ii = 65536 + AsciiCode;
				byte temp[] = { (byte) (ii / 256), (byte) (ii % 256) };
				tempstr = new String(temp, "gb2312");
				sb.append(tempstr);
			} else {
				sb.append((char) AsciiCode);
			}
		}
		return sb.toString();
	}

	/**
	 * 把clob字段中的文字中的回车改为网页中的换行
	 * 
	 * @param strtemp
	 * @return
	 */
	public static String getStringAddBr(String strtemp) {
		StringBuffer sb = new StringBuffer();
		String br = "<br>";
		int j = 0;
		for (int i = 0; i < strtemp.length(); i++) {
			if (strtemp.charAt(i) == '\r') {
				sb.append(strtemp.substring(j, i + 1));
				sb.append(br);
				j = i + 1;
			}
		}
		return sb.toString();
	}

	public static String deleteAdReserveInfoId(String all,
			String adReserveInfoId) {
		if (StringUtils.isNotBlank(all)) {
			return all.replaceAll(adReserveInfoId + ";", "");
		} else
			return "";
	}

	public static String addAdReserveInfoId(String all, String adReserveInfoId) {
		if (StringUtils.isNotBlank(all)) {
			all = deleteAdReserveInfoId(all, adReserveInfoId);
			return all + adReserveInfoId + ";";
		} else
			return adReserveInfoId + ";";
	}
}
