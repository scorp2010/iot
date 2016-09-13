package cn.inovance.iotgp.cdsm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MD5Util {

	private final static Log LOGGER = LogFactory.getLog(MD5Util.class);
	static MessageDigest md = null;

	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ne) {
			LOGGER.error("NoSuchAlgorithmException: md5", ne);
		}
	}

	/**
	 * 对一个文件求他的md5值
	 * 
	 * @param f
	 *            要求md5值的文件
	 * @return md5串
	 */
	public static String md5(File f) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}

			return new String(Hex.encodeHex(md.digest()));
		} catch (FileNotFoundException e) {
			LOGGER.error("md5 file " + f.getAbsolutePath() + " failed:"
					+ e.getMessage());
			return null;
		} catch (IOException e) {
			LOGGER.error("md5 file " + f.getAbsolutePath() + " failed:"
					+ e.getMessage());
			return null;
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				LOGGER.error("文件关闭失败：", e);
			}
		}
	}

	/**
	 * 求一个字符串的md5值
	 * 
	 * @param target
	 *            字符串
	 * @return md5 value
	 */
	public static String md5(String target) {
		return DigestUtils.md5Hex(target);
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String get32bitmd5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("计算MD5错误", e);
			return null;
		}
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String get16bitmd5(String str) {
		try {
			// 32位加密
			String rawmd5 = get32bitmd5(str);
			// 16位的加密
			return rawmd5.substring(8, 24);
		} catch (Exception e) {
			LOGGER.error("计算16位MD5错误", e);
			return null;
		}
	}

	public static void main(String[] args) {
		String filePath = "F:\\java\\workspace\\ABMS\\WebRoot\\ftproot\\media\\0101\\picture\\Water_lilies.jpg";
		File pic = new File(filePath);
		LOGGER.info(MD5Util.md5(pic).toUpperCase());
	}
	
}
