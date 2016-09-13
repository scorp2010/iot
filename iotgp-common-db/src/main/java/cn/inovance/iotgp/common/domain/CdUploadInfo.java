/***********************************************************************
 * Module:  CdUploadInfo.java
 * Author:  w1898
 * Purpose: Defines the Class CdUploadInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集上传文件信息
 * 
 * 
 * @pdOid 0942d502-1b00-483f-9ccc-c2ca08117e2d
 */
@Entity(name = "CdUploadInfo")
@Table(name = "t_mms_cd_upload_info")
public class CdUploadInfo {
	/**
	 * 编号
	 * 
	 * @pdOid c1e1d997-70c2-400a-9f55-a22c9cc4c044
	 */
	private java.lang.String id;
	/**
	 * 设备注册码
	 * 
	 * @pdOid cf39ddd9-df49-4fb2-bc9d-fd77c811d61f
	 */
	private java.lang.String cdRegCode;
	/**
	 * 目标设备序号
	 * 
	 * @pdOid f01f7acd-b097-430e-8f45-f597a52d447d
	 */
	private java.lang.Integer tdSeq;
	/**
	 * 文件名字
	 * 
	 * @pdOid 91121160-0026-40c2-aa26-06536bb04b43
	 */
	private java.lang.String fileName;
	/**
	 * 文件类型
	 * 
	 * @pdOid f9c18c93-b915-4f19-a5bf-4f55b32adb74
	 */
	private java.lang.String fileType;
	/**
	 * 文件类型名称
	 * 
	 * @pdOid f9c18c93-b915-4f19-a5bf-4f55b32adb74
	 */
	private java.lang.String fileTypeName;
	/**
	 * 开始时间
	 * 
	 * @pdOid afca522c-484c-45a5-bf17-9b8ec1953794
	 */
	private java.util.Date startTime;
	/**
	 * 结束时间
	 * 
	 * @pdOid 5ee9272a-78b9-4d6e-b137-d1665d2c259d
	 */
	private java.util.Date endTime;
	/** @pdOid eb9a42cb-84f9-4d96-88dc-f003ffd52d79 */
	private java.lang.String userAccount;
	/**
	 * 文件信息编号
	 * 
	 * @pdOid 5a09c9a0-5f5d-40e7-a8b1-11fdd1904a75
	 */
	private java.lang.String fileInfoId;
	/**
	 * 状态
	 * 
	 * @pdOid 89ce7581-2437-4f82-a260-903890d71188
	 */
	private java.lang.String status;
	/**
	 * 状态名称.
	 * 
	 * */
	private java.lang.String statusName;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(java.lang.String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}

	@Basic(optional = true)
	@Column(name = "td_seq")
	public java.lang.Integer getTdSeq() {
		return tdSeq;
	}

	public void setTdSeq(java.lang.Integer tdSeq) {
		this.tdSeq = tdSeq;
	}

	@Basic(optional = true)
	@Column(name = "file_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getFileName() {
		return fileName;
	}

	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

	@Basic(optional = true)
	@Column(name = "file_type", insertable = true, updatable = true, length = 10)
	public java.lang.String getFileType() {
		return fileType;
	}

	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	@Transient
	public java.lang.String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(java.lang.String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getStartTime() {
		if (this.startTime != null)
			return this.startTime;
		return new Date();
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getEndTime() {

		if (this.endTime != null)
			return this.endTime;
		return new Date();
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	@Basic(optional = true)
	@Column(name = "user_account", insertable = true, updatable = true, length = 100)
	public java.lang.String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(java.lang.String userAccount) {
		this.userAccount = userAccount;
	}

	@Basic(optional = true)
	@Column(name = "file_info_id", insertable = true, updatable = true, length = 36)
	public java.lang.String getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(java.lang.String fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true, length = 10)
	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	@Transient
	public java.lang.String getStatusName() {
		return statusName;
	}

	public void setStatusName(java.lang.String statusName) {
		this.statusName = statusName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdUploadInfo other = (CdUploadInfo) obj;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (fileInfoId == null) {
			if (other.fileInfoId != null)
				return false;
		} else if (!fileInfoId.equals(other.fileInfoId))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		if (fileTypeName == null) {
			if (other.fileTypeName != null)
				return false;
		} else if (!fileTypeName.equals(other.fileTypeName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		if (tdSeq == null) {
			if (other.tdSeq != null)
				return false;
		} else if (!tdSeq.equals(other.tdSeq))
			return false;
		if (userAccount == null) {
			if (other.userAccount != null)
				return false;
		} else if (!userAccount.equals(other.userAccount))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((fileInfoId == null) ? 0 : fileInfoId.hashCode());
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result
				+ ((fileTypeName == null) ? 0 : fileTypeName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((statusName == null) ? 0 : statusName.hashCode());
		result = prime * result + ((tdSeq == null) ? 0 : tdSeq.hashCode());
		result = prime * result
				+ ((userAccount == null) ? 0 : userAccount.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "CdUploadInfo [id=" + id + ", cdRegCode=" + cdRegCode
				+ ", tdSeq=" + tdSeq + ", fileName=" + fileName + ", fileType="
				+ fileType + ", fileTypeName=" + fileTypeName + ", startTime="
				+ startTime + ", endTime=" + endTime + ", userAccount="
				+ userAccount + ", fileInfoId=" + fileInfoId + ", status="
				+ status + ", statusName=" + statusName + "]";
	}

}