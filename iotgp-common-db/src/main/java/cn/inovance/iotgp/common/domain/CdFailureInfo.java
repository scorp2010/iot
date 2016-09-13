/***********************************************************************
 * Module:  CdFailureInfo.java
 * Author:  w1898
 * Purpose: Defines the Class CdFailureInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集设备故障信息
 * 
 * @pdOid 3515f4e8-8327-427d-9570-eb5a96e10f76
 */
@Entity(name = "CdFailureInfo")
@Table(name = "t_mms_cd_failure_info")
public class CdFailureInfo implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 73436c16-2139-46d7-ab57-2d820c4d15a7
	 */
	private java.lang.String id;
	/**
	 * 采集设备模块编码
	 * 
	 * 
	 * @pdOid 79e963be-3f45-4bf2-8eb4-c4197dfc787d
	 */
	private java.lang.Integer cdModuleCode;
	/**
	 * 故障名称
	 * 
	 * @pdOid 1450a054-36a5-41dc-904b-42dc0225b75d
	 */
	private java.lang.String failureName;
	/**
	 * 故障值
	 * 
	 * @pdOid 8d8c76be-87ef-441e-aabf-5e760c7cd27e
	 */
	private java.lang.String failureValue;
	/**
	 * 故障描述
	 * 
	 * @pdOid 6e93edf3-0364-4efc-a277-279e2071aecf
	 */
	private java.lang.String failureDesc;
	/**
	 * 创建时间
	 * 
	 * 
	 * @pdOid dda31e36-9be8-434c-92ac-003a6f4bc438
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 * 
	 * @pdOid 4cf9f94a-6358-46c0-bf3d-495cbfbf2e6b
	 */
	private java.util.Date updateTime;
	/**
	 * 故障代码
	 * 
	 * @pdOid 809367f6-9fa1-49d8-acf7-dadc49da1eb9
	 */
	private java.lang.String failuerCode;

	/**
	 * @pdRoleInfo migr=no name=FailureHandler assc=cdFailureInfoFailureHandler
	 *             coll=java.util.Set impl=java.util.HashSet mult=0..*
	 */
	public java.util.Set<FailureHandler> failureHandlerList = new HashSet<FailureHandler>(
			0);

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdFailureInfo() {
		// TODO Add your own initialization code here.
	}

	/**
	 * @pdGenerated default getter
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cdFailureInfo", cascade = CascadeType.ALL)
	public java.util.Set<FailureHandler> getFailureHandlerList() {
		return failureHandlerList;
	}

	/**
	 * @pdGenerated default iterator getter
	 */

	/**
	 * @pdGenerated default setter
	 * @param newFailureHandlerList
	 */
	public void setFailureHandlerList(
			java.util.Set<FailureHandler> newFailureHandlerList) {
		// removeAllFailureHandlerList();
		this.failureHandlerList = newFailureHandlerList;
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
	 * Get value of failureName
	 * 
	 * @return failureName
	 */
	@Basic(optional = true)
	@Column(name = "failure_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getFailureName() {
		return failureName;
	}

	/**
	 * Set value of failureName
	 * 
	 * @param newFailureName
	 */
	public void setFailureName(java.lang.String newFailureName) {
		this.failureName = newFailureName;
	}

	/**
	 * Get value of failureValue
	 * 
	 * @return failureValue
	 */
	@Basic(optional = true)
	@Column(name = "failure_value", insertable = true, updatable = true, length = 512)
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
	 * Get value of failureDesc
	 * 
	 * @return failureDesc
	 */
	@Basic(optional = true)
	@Column(name = "failure_desc", insertable = true, updatable = true, length = 1024)
	public java.lang.String getFailureDesc() {
		return failureDesc;
	}

	/**
	 * Set value of failureDesc
	 * 
	 * @param newFailureDesc
	 */
	public void setFailureDesc(java.lang.String newFailureDesc) {
		this.failureDesc = newFailureDesc;
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
	 * Get value of failuerCode
	 * 
	 * @return failuerCode
	 */
	@Basic(optional = true)
	@Column(name = "failuer_code", insertable = true, updatable = true, length = 100)
	public java.lang.String getFailuerCode() {
		return failuerCode;
	}

	/**
	 * Set value of failuerCode
	 * 
	 * @param newFailuerCode
	 */
	public void setFailuerCode(java.lang.String newFailuerCode) {
		this.failuerCode = newFailuerCode;
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

		if (!(other instanceof CdFailureInfo))
			return false;

		CdFailureInfo cast = (CdFailureInfo) other;

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
		if (this.cdModuleCode != null)
			hashCode = 29 * hashCode + cdModuleCode.hashCode();
		if (this.failureName != null)
			hashCode = 29 * hashCode + failureName.hashCode();
		if (this.failureValue != null)
			hashCode = 29 * hashCode + failureValue.hashCode();
		if (this.failureDesc != null)
			hashCode = 29 * hashCode + failureDesc.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
		if (this.updateTime != null)
			hashCode = 29 * hashCode + updateTime.hashCode();
		if (this.failuerCode != null)
			hashCode = 29 * hashCode + failuerCode.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.CdFailureInfo: ");
		ret.append("id='" + id + "'");
		ret.append("cdModuleCode='" + cdModuleCode + "'");
		ret.append("failureName='" + failureName + "'");
		ret.append("failureValue='" + failureValue + "'");
		ret.append("failureDesc='" + failureDesc + "'");
		ret.append("createTime='" + createTime + "'");
		ret.append("updateTime='" + updateTime + "'");
		ret.append("failuerCode='" + failuerCode + "'");
		return ret.toString();
	}

}