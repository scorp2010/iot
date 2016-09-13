/***********************************************************************
 * Module:  CdParameter.java
 * Author:  w1898
 * Purpose: Defines the Class CdParameter
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集设备系统参数
 * 
 * @pdOid 936f1e2d-a7ac-4acd-95f4-e0ff6744c3bb
 */
@Entity(name = "CdParameter")
@Table(name = "t_mms_cd_parameter",indexes ={ @Index(name="t_mms_cd_parameter_index",columnList= "cd_reg_code")  })
public class CdParameter implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 1722e421-3c96-427f-acf8-229f7ba1eaa2
	 */
	private java.lang.String id;
	/**
	 * 采集设备注册码
	 * 
	 * @pdOid 735feeed-4666-42d4-808a-f0a9be3a1c52
	 */
	private java.lang.String cdRegCode;
	/**
	 * 所有参数放在该字段;设备模块编号
	 * 
	 * @pdOid 03f31aef-7dad-41ca-a955-196decc57f43
	 */
	private java.lang.String parameters;
	/**
	 * 参数版本
	 * 
	 * @pdOid 056efad4-9e2e-4a6c-91ab-bf93ae338961
	 */
	private java.lang.String version;
	/**
	 * 参数状态
	 * 
	 * @pdOid c22dad24-4e9f-4cb9-9143-b5c066313275
	 */
	private java.lang.String status;
	/**
	 * 参数状态名称
	 * 
	 */
	private java.lang.String statusName;
	/**
	 * 创建时间
	 * 
	 * @pdOid 2a0f000c-6aa0-42cc-9efd-752751ba35e3
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 * 
	 * @pdOid bda1f333-6505-4daf-8890-fb3a42b6a78b
	 */
	private java.util.Date updateTime;

	/**
	 * @pdRoleInfo migr=no name=CollectDevice assc=collectDeiveCdParameter
	 *             mult=0..1 side=A
	 */
	public CollectDevice collectDevice;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdParameter() {
		// TODO Add your own initialization code here.
	}

	/**
	 * @pdGenerated default parent getter
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "collect_device_id_fk", referencedColumnName = "id", nullable = true)
	public CollectDevice getCollectDevice() {
		return collectDevice;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newCollectDevice
	 */
	public void setCollectDevice(CollectDevice newCollectDevice) {
		if (this.collectDevice == null
				|| !this.collectDevice.equals(newCollectDevice)) {
			if (this.collectDevice != null) {
				CollectDevice oldCollectDevice = this.collectDevice;
				this.collectDevice = null;
				// oldCollectDevice.removeCdParameterList(this);
			}
			if (newCollectDevice != null) {
				this.collectDevice = newCollectDevice;
				// this.collectDevice.addCdParameterList(this);
			}
		}
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

	/**
	 * Get value of parameters
	 * 
	 * @return parameters
	 */
	@Basic(optional = true)
	@Column(name = "parameters", insertable = true, updatable = true)
	public java.lang.String getParameters() {
		return parameters;
	}

	/**
	 * Set value of parameters
	 * 
	 * @param newParameters
	 */
	public void setParameters(java.lang.String newParameters) {
		this.parameters = newParameters;
	}

	/**
	 * Get value of version
	 * 
	 * @return version
	 */
	@Basic(optional = true)
	@Column(name = "version", insertable = true, updatable = true, length = 30)
	public java.lang.String getVersion() {
		return version;
	}

	/**
	 * Set value of version
	 * 
	 * @param newVersion
	 */
	public void setVersion(java.lang.String newVersion) {
		this.version = newVersion;
	}

	/**
	 * Get value of status
	 * 
	 * @return status
	 */
	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true, length = 10)
	public java.lang.String getStatus() {
		if (null == status || status.isEmpty())
			return "0";
		return status;
	}

	/**
	 * Set value of status
	 * 
	 * @param newStatus
	 */
	public void setStatus(java.lang.String newStatus) {
		this.status = newStatus;
	}

	/**
	 * Get value of status
	 * 
	 * @return status
	 */
	@Transient
	public java.lang.String getStatusName() {
		return statusName;
	}

	/**
	 * Set value of status
	 * 
	 * @param newStatus
	 */
	public void setStatusName(java.lang.String newStatusName) {
		this.statusName = newStatusName;
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

		if (!(other instanceof CdParameter))
			return false;

		CdParameter cast = (CdParameter) other;

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
		if (this.parameters != null)
			hashCode = 29 * hashCode + parameters.hashCode();
		if (this.version != null)
			hashCode = 29 * hashCode + version.hashCode();
		if (this.status != null)
			hashCode = 29 * hashCode + status.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
		if (this.updateTime != null)
			hashCode = 29 * hashCode + updateTime.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.CdParameter: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		ret.append("parameters='" + parameters + "'");
		ret.append("version='" + version + "'");
		ret.append("status='" + status + "'");
		ret.append("createTime='" + createTime + "'");
		ret.append("updateTime='" + updateTime + "'");
		return ret.toString();
	}

}