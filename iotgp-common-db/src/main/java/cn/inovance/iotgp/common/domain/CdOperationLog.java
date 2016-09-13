/***********************************************************************
 * Module:  CdOperationLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdOperationLog
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
 * 采集设备操作日志
 * 
 * @pdOid 5d469d5c-1365-4af6-831e-4cbf47521b35
 */
@Entity(name = "CdOperationLog")
@Table(name = "t_mms_cd_operation_log")
public class CdOperationLog implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 4bbe0686-c02f-4d45-a986-b998ac23ec08
	 */
	private java.lang.String id;
	/**
	 * 发起者
	 * 
	 * @pdOid 524fcef4-c22d-4344-b3d9-9bfbc04e03bd
	 */
	private java.lang.String from;
	/**
	 * 接收者
	 * 
	 * @pdOid 3ac4351c-33ba-417f-b3fd-d94e338a9c8b
	 */
	private java.lang.String to;
	/**
	 * 消息类型
	 * 
	 * @pdOid 281df8b0-74c4-4450-95e8-6dc39915111a
	 */
	private java.lang.String msgType;
	/**
	 * 原始数据类型
	 * 
	 * @pdOid 7e7ae96a-1cad-45d9-bee6-fcaa32d5b611
	 */
	private java.lang.String originalDataType;
	/**
	 * 原始数据实体
	 * 
	 * @pdOid 452ac935-6ec1-46d6-80ac-6245c1ead9d0
	 */

	private java.lang.String originalData;
	/** 操作日期 */
	private java.util.Date opreationDate;
	/** 查询字段 */
	private java.lang.String endOperationDate;
	/** 查询字段 */
	private java.lang.String startOperationDate;
	/** 操作人 */
	private java.lang.String operationAccount;

	private java.lang.String cdRegCode;
	
	private String subSysCode;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdOperationLog() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	/**
	 * Get value of from
	 * 
	 * @return from
	 */
	@Basic(optional = true)
	@Column(name = "operation_from", insertable = true, updatable = true, length = 50)
	public java.lang.String getFrom() {
		return from;
	}

	/**
	 * Set value of from
	 * 
	 * @param newFrom
	 */
	public void setFrom(java.lang.String newFrom) {
		this.from = newFrom;
	}

	/**
	 * Get value of to
	 * 
	 * @return to
	 */
	@Basic(optional = true)
	@Column(name = "operation_to", insertable = true, updatable = true, length = 50)
	public java.lang.String getTo() {
		return to;
	}

	/**
	 * Set value of to
	 * 
	 * @param newTo
	 */
	public void setTo(java.lang.String newTo) {
		this.to = newTo;
	}

	/**
	 * Get value of msgType
	 * 
	 * @return msgType
	 */
	@Basic(optional = true)
	@Column(name = "msg_type", insertable = true, updatable = true, length = 36)
	public java.lang.String getMsgType() {
		return msgType;
	}

	/**
	 * Set value of msgType
	 * 
	 * @param newMsgType
	 */
	public void setMsgType(java.lang.String newMsgType) {
		this.msgType = newMsgType;
	}

	/**
	 * Get value of originalDataType
	 * 
	 * @return originalDataType
	 */
	@Basic(optional = true)
	@Column(name = "original_data_type", insertable = true, updatable = true, length = 10)
	public java.lang.String getOriginalDataType() {
		return originalDataType;
	}

	/**
	 * Set value of originalDataType
	 * 
	 * @param newOriginalDataType
	 */
	public void setOriginalDataType(java.lang.String newOriginalDataType) {
		this.originalDataType = newOriginalDataType;
	}

	/**
	 * Get value of originalData
	 * 
	 * @return originalData
	 */
	@Basic(optional = true)
	@Column(name = "original_data", insertable = true, updatable = true)
	public java.lang.String getOriginalData() {
		return originalData;
	}

	/**
	 * Set value of originalData
	 * 
	 * @param newOriginalData
	 */
	public void setOriginalData(java.lang.String newOriginalData) {
		this.originalData = newOriginalData;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "opreation_time", length = 7)
	public java.util.Date getOpreationDate() {
		if (opreationDate != null) {
			return opreationDate;
		} else {
			return new Date();
		}
	}

	public void setOpreationDate(java.util.Date opreationDate) {
		this.opreationDate = opreationDate;
	}

	@Basic(optional = true)
	@Column(name = "operation_account", nullable = true, length = 50)
	public java.lang.String getOperationAccount() {
		return operationAccount;
	}

	public void setOperationAccount(java.lang.String operationAccount) {
		this.operationAccount = operationAccount;
	}

	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(java.lang.String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}

	@Transient
	public String getSubSysCode() {
		return subSysCode;
	}

	public void setSubSysCode(String subSysCode) {
		this.subSysCode = subSysCode;
	}

	@Transient
	public java.lang.String getStartOperationDate() {
		return startOperationDate;
	}

	public void setStartOperationDate(java.lang.String startOperationDate) {
		this.startOperationDate = startOperationDate;
	}

	@Transient
	public java.lang.String getEndOperationDate() {
		return endOperationDate;
	}

	public void setEndOperationDate(java.lang.String endOperationDate) {
		this.endOperationDate = endOperationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (!(other instanceof CdOperationLog))
			return false;

		CdOperationLog cast = (CdOperationLog) other;

		if (!this.getId().equals(cast.getId()))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		if (this.from != null)
			hashCode = 29 * hashCode + from.hashCode();
		if (this.to != null)
			hashCode = 29 * hashCode + to.hashCode();
		if (this.msgType != null)
			hashCode = 29 * hashCode + msgType.hashCode();
		if (this.originalDataType != null)
			hashCode = 29 * hashCode + originalDataType.hashCode();
		if (this.originalData != null)
			hashCode = 29 * hashCode + originalData.hashCode();
		if (this.opreationDate != null)
			hashCode = 29 * hashCode + opreationDate.hashCode();
		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("cn.inovance.iotgp.common.domain.CdOperationLog: ");
		ret.append("id='" + id + "'");
		ret.append("from='" + from + "'");
		ret.append("to='" + to + "'");
		ret.append("msgType='" + msgType + "'");
		ret.append("originalDataType='" + originalDataType + "'");
		ret.append("originalData='" + originalData + "'");
		ret.append("opreationDate='" + opreationDate + "'");
		return ret.toString();
	}

}