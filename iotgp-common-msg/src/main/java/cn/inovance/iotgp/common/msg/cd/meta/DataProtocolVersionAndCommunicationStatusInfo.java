/**
 * Project Name:iotgp-common-msg
 * File Name:DataProtocolVersion.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-9-4上午11:27:51
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd.meta;
/**
 * ClassName:DataProtocolVersion <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-4 上午11:27:51 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DataProtocolVersionAndCommunicationStatusInfo {

	private Integer tdAddressCode;
	
	private Integer communicateStatus;
	
	private String dataProtocolName;
	
	private String dataProtocolVersion;
	
	public DataProtocolVersionAndCommunicationStatusInfo (){
		
	}
	public DataProtocolVersionAndCommunicationStatusInfo(String dataProtocolName, String dataProtocolVersion,int tdAddressCode,int communicateStatus) {
		this.dataProtocolName = dataProtocolName;
		this.dataProtocolVersion = dataProtocolVersion;
		this.tdAddressCode = tdAddressCode;
		this.communicateStatus = communicateStatus;
	}
	
	public Integer getCommunicateStatus() {
		return communicateStatus;
	}
	public void setCommunicateStatus(Integer communicateStatus) {
		this.communicateStatus = communicateStatus;
	}
	public Integer getTdAddressCode() {
		return tdAddressCode;
	}
	public void setTdAddressCode(Integer tdAddressCode) {
		this.tdAddressCode = tdAddressCode;
	}
	public String getDataProtocolName() {
		return dataProtocolName;
	}
	public void setDataProtocolName(String dataProtocolName) {
		this.dataProtocolName = dataProtocolName;
	}
	public String getDataProtocolVersion() {
		return dataProtocolVersion;
	}
	public void setDataProtocolVersion(String dataProtocolVersion) {
		this.dataProtocolVersion = dataProtocolVersion;
	}
	
	@Override
	public String toString() {
		return "DataProtocolVersionInfo [tdAddressCode=" + tdAddressCode
				+ ", communicateStatus=" + communicateStatus
				+ ", dataProtocolName=" + dataProtocolName
				+ ", dataProtocolVersion=" + dataProtocolVersion + "]";
	}

}

