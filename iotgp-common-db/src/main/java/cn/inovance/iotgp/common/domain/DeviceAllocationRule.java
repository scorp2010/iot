/***********************************************************************
 * Module:  deviceAllocationRule.java
 * Author:  w1898
 * Purpose: Defines the Class deviceAllocationRule
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

import org.hibernate.annotations.GenericGenerator;

/**
 * 设备自动授权规则
 * 
 * 设备区域信息如何录入
 * 
 * @pdOid 93367ae3-b5cb-4d65-bb29-ecab714de44b
 */
@Entity(name = "deviceAllocationRule")
@Table(name = "t_oms_device_grant_rule")
public class DeviceAllocationRule implements java.io.Serializable {
	/** @pdOid 3ba158e4-5ce0-4420-bd34-f0af50edacef */
	private java.lang.String id;
	/**
	 * 目的客户账号
	 * 
	 * @pdOid b6a5c62a-7aa8-4aac-b132-a450204b9988
	 */
	private java.lang.String destCustomerAccount;
	/**
	 * 客户账号(多个用逗号隔开)
	 * 
	 * @pdOid 0afbe423-f927-4dff-9986-32bb25ae8f26
	 */
	private java.lang.String customerAccounts;
	/**
	 * 默认操作权限
	 * 
	 * @pdOid 52b7a128-9e9d-45da-987e-4e07649c142a
	 */
	private java.lang.String deviceRights;
	/**
	 * 创建人
	 * 
	 * @pdOid b6365a3f-0532-46b2-aee6-b2365166d950
	 */
	private java.lang.String creator;
	/**
	 * 创建时间
	 * 
	 * @pdOid 7a94d10f-5f87-4f80-8aca-5e52a5db1fbd
	 */
	private java.util.Date createTime;
	/** @pdOid cbfbbf01-b02e-451c-8f67-6887e9d112e6 */
	private java.lang.Integer status;
	/**
	 * 审核人
	 * 
	 * @pdOid 9f2dbd47-4fe6-4ad4-a096-c56511843919
	 */
	private java.lang.String auditor;
	/** @pdOid 48e2d4a5-084c-4512-b52f-72b96264d042 */
	private java.util.Date auditTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public DeviceAllocationRule() {
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
	 * Get value of destCustomerAccount
	 * 
	 * @return destCustomerAccount
	 */
	@Basic(optional = true)
	@Column(name = "dest_customer_account", insertable = true, updatable = true, length = 100)
	public java.lang.String getDestCustomerAccount() {
		return destCustomerAccount;
	}

	/**
	 * Set value of destCustomerAccount
	 * 
	 * @param newDestCustomerAccount
	 */
	public void setDestCustomerAccount(java.lang.String newDestCustomerAccount) {
		this.destCustomerAccount = newDestCustomerAccount;
	}

	/**
	 * Get value of customerAccounts
	 * 
	 * @return customerAccounts
	 */
	@Basic(optional = true)
	@Column(name = "customer_accounts", insertable = true, updatable = true, length = 1024)
	public java.lang.String getCustomerAccounts() {
		return customerAccounts;
	}

	/**
	 * Set value of customerAccounts
	 * 
	 * @param newCustomerAccounts
	 */
	public void setCustomerAccounts(java.lang.String newCustomerAccounts) {
		this.customerAccounts = newCustomerAccounts;
	}

	/**
	 * Get value of deviceRights
	 * 
	 * @return deviceRights
	 */
	@Basic(optional = false)
	@Column(name = "device_rights", nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.String getDeviceRights() {
		return deviceRights;
	}

	/**
	 * Set value of deviceRights
	 * 
	 * @param newDeviceRights
	 */
	public void setDeviceRights(java.lang.String newDeviceRights) {
		this.deviceRights = newDeviceRights;
	}

	/**
	 * Get value of creator
	 * 
	 * @return creator
	 */
	@Basic(optional = true)
	@Column(name = "creator", insertable = true, updatable = true, length = 30)
	public java.lang.String getCreator() {
		return creator;
	}

	/**
	 * Set value of creator
	 * 
	 * @param newCreator
	 */
	public void setCreator(java.lang.String newCreator) {
		this.creator = newCreator;
	}

	/**
	 * Get value of createTime
	 * 
	 * @return createTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
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
	 * Get value of status
	 * 
	 * @return status
	 */
	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true)
	public java.lang.Integer getStatus() {
		return status;
	}

	/**
	 * Set value of status
	 * 
	 * @param newStatus
	 */
	public void setStatus(java.lang.Integer newStatus) {
		this.status = newStatus;
	}

	/**
	 * Get value of auditor
	 * 
	 * @return auditor
	 */
	@Basic(optional = true)
	@Column(name = "auditor", insertable = true, updatable = true, length = 254)
	public java.lang.String getAuditor() {
		return auditor;
	}

	/**
	 * Set value of auditor
	 * 
	 * @param newAuditor
	 */
	public void setAuditor(java.lang.String newAuditor) {
		this.auditor = newAuditor;
	}

	/**
	 * Get value of auditTime
	 * 
	 * @return auditTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_time", insertable = true, updatable = true)
	public java.util.Date getAuditTime() {
		return auditTime;
	}

	/**
	 * Set value of auditTime
	 * 
	 * @param newAuditTime
	 */
	public void setAuditTime(java.util.Date newAuditTime) {
		this.auditTime = newAuditTime;
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

		if (!(other instanceof DeviceAllocationRule))
			return false;

		DeviceAllocationRule cast = (DeviceAllocationRule) other;

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
		if (this.destCustomerAccount != null)
			hashCode = 29 * hashCode + destCustomerAccount.hashCode();
		if (this.customerAccounts != null)
			hashCode = 29 * hashCode + customerAccounts.hashCode();
		if (this.deviceRights != null)
			hashCode = 29 * hashCode + deviceRights.hashCode();
		if (this.creator != null)
			hashCode = 29 * hashCode + creator.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
		if (this.status != null)
			hashCode = 29 * hashCode + status.hashCode();
		if (this.auditor != null)
			hashCode = 29 * hashCode + auditor.hashCode();
		if (this.auditTime != null)
			hashCode = 29 * hashCode + auditTime.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.deviceAllocationRule: ");
		ret.append("id='" + id + "'");
		ret.append("destCustomerAccount='" + destCustomerAccount + "'");
		ret.append("customerAccounts='" + customerAccounts + "'");
		ret.append("deviceRights='" + deviceRights + "'");
		ret.append("creator='" + creator + "'");
		ret.append("createTime='" + createTime + "'");
		ret.append("status='" + status + "'");
		ret.append("auditor='" + auditor + "'");
		ret.append("auditTime='" + auditTime + "'");
		return ret.toString();
	}

}