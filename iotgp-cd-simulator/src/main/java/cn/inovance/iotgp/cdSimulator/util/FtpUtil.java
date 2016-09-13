package cn.inovance.iotgp.cdSimulator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpUtil {
	protected final static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	private FTPClient ftp;

	/**
	 * 从FTP服务器下载文件
	 */
	public boolean downLoadFile(String host, int port, String username,
			String password, String remotePath, String fileName,
			String lacalFileName) {
		long downloadStartTime = System.currentTimeMillis();
		logger.info("文件下载开始：===");
		boolean success = false;
		try {
			if (!connect(host, port, username, password, remotePath))
				return false;
			ftp.enterLocalPassiveMode(); // 采用被动模式传输

			File localFile = new File(lacalFileName);

			// 目录不存在则创建
			File parent = localFile.getParentFile();
			if (parent != null && !parent.exists())
				parent.mkdirs();

			OutputStream os = new FileOutputStream(localFile);
			success = ftp.retrieveFile(fileName, os); // 下载文件
			os.flush();
			os.close();
			long downloadEndTime = System.currentTimeMillis();
			if (localFile.length() >0)
			logger.info("文件下载结束，下载文件大小：{}byte，下载时间：{}ms，下载速度：{}KB/s", 
					localFile.length(),downloadEndTime-downloadStartTime, localFile.length()/(downloadEndTime-downloadStartTime)*1000/1024);
			else 
				logger.info("文件大小为空！");
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (Exception e) {
				}
			}
		}
		return success;
	}

	/**
	 * FTP服务器上传文件
	 */
	public boolean upLoadFile(String host, int port, String username,
			String password, String remotePath, String fileName,
			String lacalFileName) {
		boolean success = true;
		try {
			if (!connect(host, port, username, password, remotePath))
				return false;
			ftp.enterLocalPassiveMode(); // 采用被动模式传输

			File localFile = new File(lacalFileName);
			InputStream is = new FileInputStream(localFile);
			ftp.storeFile(fileName, is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (Exception e) {
				}
			}
		}

		return success;
	}

	/**
	 * 连接FTP服务器
	 * 
	 * @param host
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @return
	 */
	private boolean connect(String host, int port, String username,
			String password, String remotePath) {
		ftp = new FTPClient();
		try {
			// 连接
			ftp.connect(host, port);
			// 登录
			if (!ftp.login(username, password))
				return false;
			// 转移到FTP服务器目录
			if (!ftp.changeWorkingDirectory(remotePath))
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		boolean ret =false;
		ret =(new FtpUtil()).downLoadFile("58.60.228.147", 8501, "748865688FG0020043V000000000",
		 "76420135", "/download/1/2015/01/22/",
		 "d2f8cfe622c2445e9d0141ba9b050548.2", "E:\\localfiles\\d2f8cfe622c2445e9d0141ba9b050548.2");
		 
		/*
		 * ret = downLoadFile("127.0.0.1", 2121, "800600014DC00013012345673098",
		 * "67037161", "/download/1/2014/12/01/",
		 * "f4bdef2990da4d30bec176a802133824.",
		 * "E:\\localfiles\\f4bdef2990da4d30bec176a802133824.");
		 */
		System.out.println(ret);
	}

}
