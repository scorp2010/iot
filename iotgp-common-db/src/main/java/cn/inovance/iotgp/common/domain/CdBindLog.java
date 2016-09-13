/***********************************************************************
 * Module:  CdBindLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdBindLog
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
 * 设备绑定日志
 * 
 * @pdOid 352cb2e6-e69b-4106-a05a-01be9da4c4f0
 */
@Entity(name = "CdBindLog")
@Table(name = "t_oms_cd_bind_log")
public class CdBindLog implements java.io.Serializable {
	/** @pdOid f92eac8f-fa12-4bba-a457-10eb5997c601 */
	private java.lang.String id;
	/**
	 * 新增采集设备编号
	 * 
	 * @pdOid cf5b4851-5e11-4afc-b5e1-916d84304bda
	 */
	private java.lang.String newCdRegCode;
	/**
	 * 解除采集设备编号
	 * 
	 * @pdOid 35f6b54f-1f40-4018-ba29-edd6f16cab13
	 */
	private java.lang.String oldCdRegCode;
	/**
	 * 1、绑定 2、替换 3、解除绑定
	 * 
	 * @pdOid a7e5e041-1df9-4f29-8b66-6c77bde455cc
	 */
	private java.lang.String bindType;
	/**
	 * 绑定名称
	 * 
	 * @pdOid a7e5e041-1df9-4f29-8b66-6c77bde455cc
	 */
	private java.lang.String bindTypeName;
	/**
	 * 相关客户主账号
	 * 
	 * @pdOid f9874256-b508-4eaa-bd08-0a7266507f31
	 */
	private java.lang.String customerAccount;
	/**
	 * 操作人账号
	 * 
	 * @pdOid caa4ab34-5e34-4a0e-a498-6d1e399b4d9c
	 */
	private java.lang.String bindUserAccount;
	/**
	 * 操作时间
	 * 
	 * @pdOid 2e1fed47-9653-4c43-8468-bd73ba8c70fa
	 */
	private java.util.Date createTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdBindLog() {
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
	 * Get value of newCdRegCode
	 * 
	 * @return newCdRegCode
	 */
	@Basic(optional = true)
	@Column(name = "new_cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getNewCdRegCode() {
		return newCdRegCode;
	}

	/**
	 * Set value of newCdRegCode
	 * 
	 * @param newNewCdRegCode
	 */
	public void setNewCdRegCode(java.lang.String newNewCdRegCode) {
		this.newCdRegCode = newNewCdRegCode;
	}

	/**
	 * Get value of oldCdRegCode
	 * 
	 * @return oldCdRegCode
	 */
	@Basic(optional = true)
	@Column(name = "old_cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getOldCdRegCode() {
		return oldCdRegCode;
	}

	/**
	 * Set value of oldCdRegCode
	 * 
	 * @param newOldCdRegCode
	 */
	public void setOldCdRegCode(java.lang.String newOldCdRegCode) {
		this.oldCdRegCode = newOldCdRegCode;
	}

	/**
	 * Get value of bindType
	 * 
	 * @return bindType
	 */
	@Basic(optional = true)
	@Column(name = "bind_type", insertable = true, updatable = true, length = 10)
	public java.lang.String getBindType() {
		return bindType;
	}

	/**
	 * Set value of bindType
	 * 
	 * @param newBindType
	 */
	public void setBindType(java.lang.String newBindType) {
		this.bindType = newBindType;
	}

	/**
	 * Get value of bindTypeName
	 * 
	 * @return bindTypeName
	 */

	@Transient
	public java.lang.String getBindTypeName() {
		return bindTypeName;
	}

	/**
	 * Set value of bindTypeName
	 * 
	 * @param newBindTypeName
	 */
	public void setBindTypeName(java.lang.String newBindTypeName) {
		this.bindTypeName = newBindTypeName;
	}

	/**
	 * Get value of customerAccount
	 * 
	 * @return customerAccount
	 */
	@Basic(optional = true)
	@Column(name = "customer_account", insertable = true, updatable = true, length = 50)
	public java.lang.String getCustomerAccount() {
		return customerAccount;
	}

	/**
	 * Set value of customerAccount
	 * 
	 * @param newCustomerAccount
	 */
	public void setCustomerAccount(java.lang.String newCustomerAccount) {
		this.customerAccount = newCustomerAccount;
	}

	/**
	 * Get value of bindUserAccount
	 * 
	 * @return bindUserAccount
	 */
	@Basic(optional = true)
	@Column(name = "bind_user_account", insertable = true, updatable = true, length = 50)
	public java.lang.String getBindUserAccount() {
		return bindUserAccount;
	}

	/**
	 * Set value of bindUserAccount
	 * 
	 * @param newBindUserAccount
	 */
	public void setBindUserAccount(java.lang.String newBindUserAccount) {
		this.bindUserAccount = newBindUserAccount;
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

		if (!(other instanceof CdBindLog))
			return false;

		CdBindLog cast = (CdBindLog) other;

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
		if (this.newCdRegCode != null)
			hashCode = 29 * hashCode + newCdRegCode.hashCode();
		if (this.oldCdRegCode != null)
			hashCode = 29 * hashCode + oldCdRegCode.hashCode();
		if (this.bindType != null)
			hashCode = 29 * hashCode + bindType.hashCode();
		if (this.customerAccount != null)
			hashCode = 29 * hashCode + customerAccount.hashCode();
		if (this.bindUserAccount != null)
			hashCode = 29 * hashCode + bindUserAccount.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.CdBindLog: ");
		ret.append("id='" + id + "'");
		ret.append("newCdRegCode='" + newCdRegCode + "'");
		ret.append("oldCdRegCode='" + oldCdRegCode + "'");
		ret.append("bindType='" + bindType + "'");
		ret.append("customerAccount='" + customerAccount + "'");
		ret.append("bindUserAccount='" + bindUserAccount + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}

}