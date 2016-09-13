/**
 * Project Name:iotgp-common-db
 * File Name:TdSoftWare.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-7-16下午7:24:21
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

/**
 * ClassName:TdSoftWare <br/>
 * Function: 这是视图 <br/>
 * Date:     2014-7-16 下午7:24:21 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
	public class TdSoftWare implements java.io.Serializable{
   /** id. */
	private String id;
	/** 目标设备注册码.*/
	private String cdRegCode;
	/** 目标设备地址.*/
	private Integer addressCode;
	/** 目标设备类型.*/
	private Integer tdTypeCode;
	/** 软件名称.*/
	private  String name;
	/** 当前软件版本.*/
	private  String curVersion;
	
	/** 最新软件版本.*/
	private  String newVersion;
	
	/** 文件名字.*/
	private String fileName;
	/** 文件路径.*/
	private String filePath;
	/** 文件长度.*/
	private Long fileLength;
	/** 文件md5.*/
	private String fileMd5;
	private String fileId;
	private String tdControllerTypeId;
	private String tdControllerTypeDataProtocolId;
	
	
	public String getTdControllerTypeDataProtocolId() {
		return tdControllerTypeDataProtocolId;
	}
	public void setTdControllerTypeDataProtocolId(
			String tdControllerTypeDataProtocolId) {
		this.tdControllerTypeDataProtocolId = tdControllerTypeDataProtocolId;
	}
	public String getTdControllerTypeId() {
		return tdControllerTypeId;
	}
	public void setTdControllerTypeId(String tdControllerTypeId) {
		this.tdControllerTypeId = tdControllerTypeId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCdRegCode() {
		return cdRegCode;
	}
	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}
	public Integer getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(Integer addressCode) {
		this.addressCode = addressCode;
	}
	public Integer getTdTypeCode() {
		return tdTypeCode;
	}
	public void setTdTypeCode(Integer tdTypeCode) {
		this.tdTypeCode = tdTypeCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurVersion() {
		return curVersion;
	}
	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}
	public String getNewVersion() {
		return newVersion;
	}
	public void setNewVersion(String neWVersion) {
		this.newVersion = neWVersion;
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
	public Long getFileLength() {
		return fileLength;
	}
	public void setFileLength(Long fileLength) {
		this.fileLength = fileLength;
	}
	public String getFileMd5() {
		return fileMd5;
	}
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressCode == null) ? 0 : addressCode.hashCode());
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime * result
				+ ((fileLength == null) ? 0 : fileLength.hashCode());
		result = prime * result + ((fileMd5 == null) ? 0 : fileMd5.hashCode());
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((tdTypeCode == null) ? 0 : tdTypeCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TdSoftWare other = (TdSoftWare) obj;
		if (addressCode == null) {
			if (other.addressCode != null)
				return false;
		} else if (!addressCode.equals(other.addressCode))
			return false;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (fileLength == null) {
			if (other.fileLength != null)
				return false;
		} else if (!fileLength.equals(other.fileLength))
			return false;
		if (fileMd5 == null) {
			if (other.fileMd5 != null)
				return false;
		} else if (!fileMd5.equals(other.fileMd5))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tdTypeCode == null) {
			if (other.tdTypeCode != null)
				return false;
		} else if (!tdTypeCode.equals(other.tdTypeCode))
			return false;
		
		return true;
	}
	@Override
	public String toString() {
		return "TdSoftWare [id=" + id + ", cdRegCode=" + cdRegCode
				+ ", addressCode=" + addressCode + ", tdTypeCode=" + tdTypeCode
				+ ", name=" + name + ", fileName="
				+ fileName + ", filePath="
				+ filePath + ", fileLength=" + fileLength + ", fileMd5="
				+ fileMd5 + "]";
	}
	
}

