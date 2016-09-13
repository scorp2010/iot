package cn.inovance.iotgp.common.msg.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64工具类。在生成发给终端的XML命令时提供Base64编码和解码工具。
 * 
 * @author
 */
public class Base64Util {

	/**
	 * Base64编码器
	 */
	// /private static final BASE64Encoder encoder = new BASE64Encoder();

	/**
	 * Base64解码器
	 */
	// /private static final BASE64Decoder decoder = new BASE64Decoder();

	/**
	 * 将十进制数字符串转换为相应的十六进制数字符串。
	 * 
	 * @param decString
	 *            十进制字符串
	 * @return 十六进制字符串
	 */
	public static String getHexString(String decString) {
		return Integer.toHexString(Integer.parseInt(decString));
	}

	/**
	 * 将十六进制数字符串转换为相应的十进制数字符串。
	 * 
	 * @param hexString
	 *            十六进制数的字符串
	 * @return 相应的十进制数的字符串
	 */
	public static String getDecString(String hexString) {
		if (hexString.startsWith("0x")) {
			hexString = hexString.substring(2);
		}
		return Integer.toString(Integer.parseInt(hexString, 16));
	}

	/**
	 * 将字符串转为Base64编码。
	 * 
	 * @param s
	 *            字符串
	 * @return 字符串的Base64编码
	 */
	public static String string2Base64Encode(String s) {
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			return encoder.encode(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将Base64编码转为字符串。
	 * 
	 * @param base64Encode
	 *            Base64编码
	 * @return Base64编码的字符串
	 */
	public static String base64Encode2String(String base64Encode) {
		try {
			// return new String(decoder.decodeBuffer(base64Encode),"utf-8");
			BASE64Decoder decoder = new BASE64Decoder();
			return new String(decoder.decodeBuffer(base64Encode), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将数字转为指定字节数byte数组之后，再转为Base64编码。
	 * 
	 * @param number数字
	 * @param bytesLength
	 *            字节长度
	 * @return Base64编码
	 */
	public static String number2Base64Encode(long number, int bytesLength) {
		byte[] bytes = number2ByteArray(number, bytesLength);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);
	}

	/**
	 * 将Base64编码转为byte数组之后，再转为数字。
	 * 
	 * @param base64String
	 *            Base64编码
	 * @return 数字
	 */
	public static long base64Encode2Number(String base64String) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(base64String);
			return byteArray2Number(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 将数字转化为指定字节数的byte数组。
	 * 
	 * @param number
	 *            数字
	 * @param length
	 *            字节数
	 * @return byte数组
	 */
	private static byte[] number2ByteArray(long number, int length) {
		long temp = number;
		byte[] bytes = new byte[length];
		for (int i = bytes.length - 1; i > -1; i--) {
			bytes[i] = new Long(temp & 0xff).byteValue();
			temp = temp >> 8;
		}
		return bytes;
	}

	/**
	 * 将byte数组转化为数字。
	 * 
	 * @param bytes
	 *            byte数组
	 * @return 数字
	 */
	private static long byteArray2Number(byte[] bytes) {
		long result = bytes[0];
		for (int i = 1; i < bytes.length; i++) {
			result <<= 8;
			// byte[i]取正值
			result = result + (bytes[i] + 256) % 256;
		}
		return result;
	}

	public static void main(String[] args) {
		// String msg =
		// "PFRSQU5TSVRfSU5GTz48Q09NTUFORD5HRVRfU1VCREVWSUNFX0lORk9fUkVRPC9DT01NQU5EPjxQ"
		// +
		// "QUdFX0lORk8gSW5kZXg9IjEiIFNpemU9IjEwIi8+PFFVRVJZX0lORk8gU3ViZGV2aWNlTG9jYXRp"
		// +
		// "b249IiIgU3ViRGV2aWNlVHlwZT0iIiBEZXZpY2VTZXJpYWw9IjIwMDQ1R0FLN0VYOVBIRloiLz48"
		// +
		// "L1RSQU5TSVRfSU5GTz4=";
		// System.out.println(Base64Util.base64Encode2String(msg));
		String msg = "PFRSQU5TSVRfSU5GTz4KICAgIDxDT01NQU5EPkFERF9ORVdfU1VCREVWSUNFX0lORk88L0NPTU1BTkQ+CiAgICA8U1VCREVWSUNFX0xJU1Q+CiAgICAgICAgPFNVQkRFVklDRV9JTkZPIFNlcmlhbD0iODAxRDAwMDAwMDAwMDAwMSIgTmFtZT0iODUwOCIgVHlwZT0iODAxRCIgTG9jYXRpb249IlN0cmVhbWluZzoxIiBQYXJhbWV0ZXI9IiIgU3RhdHVzPSJGRkZGIiBSRklEPSIiIEljb25OYW1lPSIiIExvY2F0aW9uSWNvbnM9IiIgLz4KICAgIDwvU1VCREVWSUNFX0xJU1Q+CjwvVFJBTlNJVF9JTkZPPgo=";
		System.out.println(Base64Util.base64Encode2String(msg));
		// System.out.println(new Date(1308539835982L).toLocaleString());
	}

}
