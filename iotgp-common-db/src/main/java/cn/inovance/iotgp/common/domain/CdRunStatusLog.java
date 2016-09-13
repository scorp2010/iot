/***********************************************************************
 * Module:  CdRunStatusLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdRunStatusLog
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集设备运行状态日志
 * 
 * @pdOid 81337800-6da6-45ae-b12f-70adf65d0305
 */
@Entity(name = "CdRunStatusLog")
@Table(name = "t_mms_cd_run_status_log",indexes ={ @Index(name="t_mms_cd_run_status_index",columnList= "cd_reg_code")  })
public class CdRunStatusLog implements java.io.Serializable {
	/** 编号*/
	private java.lang.String id;
	/** 采集设备注册码*/
	private java.lang.String cdRegCode;
	/**采集设备模块编码*/
	private java.lang.Integer cdModuleCode;
	private java.lang.String cdModuleCodeName;
	/** 采集设备模块状态值*/
	private java.lang.String cdModuleValue;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	/**运行状态 json格式*/
	private java.lang.String runStatus;
	/** 查询字段 */
	private java.lang.String startCreateTime;

	/** 查询字段 */
	private java.lang.String endCreateTime;
	/** 查询字段 ，采集设备模块编码*/
	private java.lang.String cdModuleCodes;
	/**运行状态 json格式*/
	private java.lang.String runStatusExt;
	/**自检状态*/
	private java.lang.String selfTestStatus;

	

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdRunStatusLog() {
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

	
	@Transient
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
	 * Get value of cdModuleValue
	 * 
	 * @return cdModuleValue
	 */

	@Transient
	public java.lang.String getCdModuleValue() {
		return cdModuleValue;
	}
	public void setCdModuleValue(java.lang.String newCdModuleValue) {
		this.cdModuleValue = newCdModuleValue;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateTime() {
		return this.createTime;
	
	}
	public void setUpdateTime(java.util.Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}
	@Basic(optional = true)
	@Column(name = "run_status", insertable = true, updatable = true, length = 1000)
	public java.lang.String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(java.lang.String runStatus) {
		this.runStatus = runStatus;
	}

	@Basic(optional = true)
	@Column(name = "run_status_ext", insertable = true, updatable = true, length = 4000)
	public java.lang.String getRunStatusExt() {
		return runStatusExt;
	}

	public void setRunStatusExt(java.lang.String runStatusExt) {
		this.runStatusExt = runStatusExt;
	}

	@Basic(optional = true)
	@Column(name = "self_test_status", insertable = true, updatable = true, length = 10)
	public java.lang.String getSelfTestStatus() {
		return selfTestStatus;
	}

	public void setSelfTestStatus(java.lang.String selfTestStatus) {
		this.selfTestStatus = selfTestStatus;
	}

	@Transient
	public java.lang.String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(java.lang.String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	@Transient
	public java.lang.String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(java.lang.String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	@Transient
	public java.lang.String getCdModuleCodes() {
		return cdModuleCodes;
	}

	public void setCdModuleCodes(java.lang.String cdModuleCodes) {
		this.cdModuleCodes = cdModuleCodes;
	}
	@Transient
	public java.lang.String getCdModuleCodeName() {
		return cdModuleCodeName;
	}

	public void setCdModuleCodeName(java.lang.String cdModuleCodeName) {
		this.cdModuleCodeName = cdModuleCodeName;
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

		if (!(other instanceof CdRunStatusLog))
			return false;

		CdRunStatusLog cast = (CdRunStatusLog) other;

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
		if (this.cdModuleValue != null)
			hashCode = 29 * hashCode + cdModuleValue.hashCode();

		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.CdRunStatusLog: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		ret.append("cdModuleCode='" + cdModuleCode + "'");
		ret.append("cdModuleValue='" + cdModuleValue + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}
	
}