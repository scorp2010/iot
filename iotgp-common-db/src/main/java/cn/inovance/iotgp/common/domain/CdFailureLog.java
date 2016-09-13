/***********************************************************************
 * Module:  CdFailureLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdFailureLog
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
 * 采集设备故障日志
 * 
 * @pdOid 02ce8758-3d10-4bc4-9286-d3e9cb731aa2
 */
@Entity(name = "CdFailureLog")
@Table(name = "t_mms_cd_failure_log")
public class CdFailureLog implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 20a10b38-cbf8-435f-9913-79eb1b7da6ab
	 */
	private java.lang.String id;
	/**
	 * 采集设备注册码
	 * 
	 * @pdOid bad9c13d-5e72-4dce-8bbd-6b6bfba56729
	 */
	private java.lang.String cdRegCode;
	/**
	 * 采集设备模块编码
	 * 
	 * @pdOid 18abaa7b-2b64-49f9-80d1-c63e3691c84e
	 */
	private java.lang.Integer cdModuleCode;
	/**
	 * 故障时间
	 * 
	 * @pdOid b8e91636-ef66-4111-965e-9215e004d4a1
	 */
	private java.util.Date failureTime;
	/**
	 * 故障模块运行状态值
	 * 
	 * @pdOid 397ad3f8-c0bc-4577-9aef-6dbde6cab95d
	 */
	private java.lang.String failureValue;
	/**
	 * 系统记录故障时间
	 * 
	 * @pdOid 592cd5c5-a87a-46db-8399-7d54ff9cc2ea
	 */
	private java.util.Date createTime;
	/**
	 * 处理人
	 * 
	 * @pdOid 40669944-0fae-46ce-9584-fc54dc69b371
	 */
	private java.lang.String handleUser;
	/**
	 * 处理结果
	 * 
	 * @pdOid 085cb8c8-3f99-4da3-8aac-30a048d6dcce
	 */
	private java.lang.String handleResult;
	/**
	 * 处理时间
	 * 
	 * @pdOid 4b71e502-10fb-4588-bd26-c70de92afbf9
	 */
	private java.util.Date handleTime;
	/**
	 * 处理状态 0:未处理 1:处理中 2:处理完成
	 * 
	 * @pdOid b251b4b2-d022-44cf-8901-62ad23b0745e
	 */
	private java.lang.String handleStatus;
	/**
	 * 处理状态名称
	 * 
	 * @pdOid b251b4b2-d022-44cf-8901-62ad23b0745e
	 */
	private java.lang.String handleStatusName;
	/**
	 * 更新时间
	 * 
	 * @pdOid 485e7191-b294-4794-acdf-7dc255138bfb
	 */
	private java.util.Date updateTime;
	/**
	 * 故障代码
	 * 
	 * @pdOid 34f12273-e460-4a08-a35f-dab6f37ebb2c
	 */
	private java.lang.String failueCode;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdFailureLog() {
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
	public String getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(String newId) {
		this.id = newId;
	}

	/**
	 * Get value of cdRegCode
	 * 
	 * @return cdRegCode
	 */
	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	/**
	 * Set value of cdRegCode
	 * 
	 * @param newCdRegCode
	 */
	public void setCdRegCode(java.lang.String newCdRegCode) {
		this.cdRegCode = newCdRegCode;
	}

	/**
	 * Get value of cdModuleCode
	 * 
	 * @return cdModuleCode
	 */
	@Basic(optional = true)
	@Column(name = "cd_module_code", insertable = true, updatable = true)
	public java.lang.Integer getCdModuleCode() {
		return cdModuleCode;
	}

	/**
	 * Set value of cdModuleCode
	 * 
	 * @param newCdModuleCode
	 */
	public void setCdModuleCode(java.lang.Integer newCdModuleCode) {
		this.cdModuleCode = newCdModuleCode;
	}

	/**
	 * Get value of failureTime
	 * 
	 * @return failureTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "failure_time", insertable = true, updatable = true)
	public java.util.Date getFailureTime() {
		return failureTime;
	}

	/**
	 * Set value of failureTime
	 * 
	 * @param newFailureTime
	 */
	public void setFailureTime(java.util.Date newFailureTime) {
		this.failureTime = newFailureTime;
	}

	/**
	 * Get value of failureValue
	 * 
	 * @return failureValue
	 */
	@Basic(optional = true)
	@Column(name = "failure_value", insertable = true, updatable = true, length = 500)
	public java.lang.String getFailureValue() {
		return failureValue;
	}

	/**
	 * Set value of failureValue
	 * 
	 * @param newFailureValue
	 */
	public void setFailureValue(java.lang.String newFailureValue) {
		this.failureValue = newFailureValue;
	}

	/**
	 * Get value of createTime
	 * 
	 * @return createTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	/**
	 * Set value of createTime
	 * 
	 * @param newCreateTime
	 */
	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	/**
	 * Get value of handleUser
	 * 
	 * @return handleUser
	 */
	@Basic(optional = true)
	@Column(name = "handle_user", insertable = true, updatable = true, length = 50)
	public java.lang.String getHandleUser() {
		return handleUser;
	}

	/**
	 * Set value of handleUser
	 * 
	 * @param newHandleUser
	 */
	public void setHandleUser(java.lang.String newHandleUser) {
		this.handleUser = newHandleUser;
	}

	/**
	 * Get value of handleResult
	 * 
	 * @return handleResult
	 */
	@Basic(optional = true)
	@Column(name = "handle_result", insertable = true, updatable = true, length = 256)
	public java.lang.String getHandleResult() {
		return handleResult;
	}

	/**
	 * Set value of handleResult
	 * 
	 * @param newHandleResult
	 */
	public void setHandleResult(java.lang.String newHandleResult) {
		this.handleResult = newHandleResult;
	}

	/**
	 * Get value of handleTime
	 * 
	 * @return handleTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "handle_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getHandleTime() {
		if (this.handleTime != null)
			return this.handleTime;
		return new Date();
	}

	/**
	 * Set value of handleTime
	 * 
	 * @param newHandleTime
	 */
	public void setHandleTime(java.util.Date newHandleTime) {
		this.handleTime = newHandleTime;
	}

	/**
	 * Get value of handleStatus
	 * 
	 * @return handleStatus
	 */
	@Basic(optional = true)
	@Column(name = "handle_status", insertable = true, updatable = true, length = 10)
	public java.lang.String getHandleStatus() {
		return handleStatus;
	}

	/**
	 * Set value of handleStatus
	 * 
	 * @param newHandleStatus
	 */
	public void setHandleStatus(java.lang.String newHandleStatus) {
		this.handleStatus = newHandleStatus;
	}

	/**
	 * Get value of handleStatusName
	 * 
	 * @return handleStatusName
	 */
	@Transient
	public java.lang.String getHandleStatusName() {
		return handleStatusName;
	}

	/**
	 * Set value of handleStatusName
	 * 
	 * @param newHandleStatusName
	 */
	public void setHandleStatusName(java.lang.String newHandleStatusName) {
		this.handleStatusName = newHandleStatusName;
	}

	/**
	 * Get value of updateTime
	 * 
	 * @return updateTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	/**
	 * Set value of updateTime
	 * 
	 * @param newUpdateTime
	 */
	public void setUpdateTime(java.util.Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	/**
	 * Get value of failueCode
	 * 
	 * @return failueCode
	 */
	@Basic(optional = true)
	@Column(name = "failue_code", insertable = true, updatable = true, length = 100)
	public java.lang.String getFailueCode() {
		return failueCode;
	}

	/**
	 * Set value of failueCode
	 * 
	 * @param newFailueCode
	 */
	public void setFailueCode(java.lang.String newFailueCode) {
		this.failueCode = newFailueCode;
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

		if (!(other instanceof CdFailureLog))
			return false;

		CdFailureLog cast = (CdFailureLog) other;

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
		if (this.cdRegCode != null)
			hashCode = 29 * hashCode + cdRegCode.hashCode();
		if (this.cdModuleCode != null)
			hashCode = 29 * hashCode + cdModuleCode.hashCode();
		if (this.failureTime != null)
			hashCode = 29 * hashCode + failureTime.hashCode();
		if (this.failureValue != null)
			hashCode = 29 * hashCode + failureValue.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
		if (this.handleUser != null)
			hashCode = 29 * hashCode + handleUser.hashCode();
		if (this.handleResult != null)
			hashCode = 29 * hashCode + handleResult.hashCode();
		if (this.handleTime != null)
			hashCode = 29 * hashCode + handleTime.hashCode();
		if (this.handleStatus != null)
			hashCode = 29 * hashCode + handleStatus.hashCode();
		if (this.updateTime != null)
			hashCode = 29 * hashCode + updateTime.hashCode();
		if (this.failueCode != null)
			hashCode = 29 * hashCode + failueCode.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.CdFailureLog: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		ret.append("cdModuleCode='" + cdModuleCode + "'");
		ret.append("failureTime='" + failureTime + "'");
		ret.append("failureValue='" + failureValue + "'");
		ret.append("createTime='" + createTime + "'");
		ret.append("handleUser='" + handleUser + "'");
		ret.append("handleResult='" + handleResult + "'");
		ret.append("handleTime='" + handleTime + "'");
		ret.append("handleStatus='" + handleStatus + "'");
		ret.append("updateTime='" + updateTime + "'");
		ret.append("failueCode='" + failueCode + "'");
		return ret.toString();
	}

}