/**
 * Project Name:iotgp-cd-simulator
 * File Name:MediaFile.java
 * Package Name:cn.inovance.iotgp.cdSimulator.processor.node
 * Date:2015-12-22下午3:22:00
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.cdSimulator.processor.node;
public class MediaFile{
	protected String fileName;
	protected String fileMd5;
	protected String filePath;
	protected String fileType;
	public MediaFile() {

	}
	public MediaFile(String fileType, String fileMd5, String fileName, String filePath){
		this.fileType = fileType;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileMd5 = fileMd5;
	}
	@Override
	public String toString() {
		return "MediaFile [fileName=" + fileName + ", fileMd5=" + fileMd5
				+ ", filePath=" + filePath + ", fileType=" + fileType + "]";
	}
}