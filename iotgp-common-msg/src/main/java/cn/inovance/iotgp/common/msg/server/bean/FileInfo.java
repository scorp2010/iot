/**
 * Project Name:iotgp-common-msg
 * File Name:FileInfo.java
 * Package Name:cn.inovance.iotgp.common.msg.server.bean
 * Date:2014-5-27上午11:09:33
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server.bean;

/**
 * ClassName:FileInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-27 上午11:09:33 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class FileInfo {

	private String fileName;

	private String filePath;

	private String fileMd5;

	public FileInfo() {

	}

	public FileInfo(String fileName, String filePath, String fileMd5) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileMd5 = fileMd5;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

}
