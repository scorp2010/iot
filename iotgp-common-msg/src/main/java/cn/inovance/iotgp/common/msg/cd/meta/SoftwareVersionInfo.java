/**
 * Project Name:cdag
 * File Name:ServerConnectionInfo.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-4-15下午4:53:10
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd.meta;

/**
 * ClassName:ServerConnectionInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 下午4:53:10 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class SoftwareVersionInfo {

	private String softwareName;
	
	private String softwareVersion;
	
	private short transferType;
	
	public SoftwareVersionInfo() {

	}

	public SoftwareVersionInfo(String softwareName, String softwareVersion,short transferType) {
		this.softwareName = softwareName;
		this.softwareVersion = softwareVersion;
		this.transferType = transferType;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public short getTransferType() {
		return transferType;
	}

	public void setTransferType(short transferType) {
		this.transferType = transferType;
	}

	@Override
	public String toString() {
		return "SoftwareVersionInfo [softwareName=" + softwareName
				+ ", softwareVersion=" + softwareVersion 
				+ ", transferType=" + transferType + "]";
	}

}
