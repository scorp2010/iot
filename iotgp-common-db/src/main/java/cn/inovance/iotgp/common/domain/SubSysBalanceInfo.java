/***********************************************************************
 * Module:  SubSysBalanceInfo.java
 * Author:  w1898
 * Purpose: Defines the Class SubSysBalanceInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

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
 * 子系统负载均衡信息
 * 
 * @pdOid bac1b2f9-320a-4de3-baf6-af0a36f272b7
 */
@Entity(name = "SubSysBalanceInfo")
@Table(name = "t_cds_sub_sys_balance_info")
public class SubSysBalanceInfo implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 85865550-6259-454b-838d-9a8090131b8d
	 */
	public java.lang.String id;
	/**
	 * 子系统编码
	 * 
	 * @pdOid f0d58673-94dc-47c6-ae7c-c7e6c0c880ae
	 */
	public java.lang.String subSysCode;
	/**
	 * 运行状态项编码
	 * 
	 * @pdOid 267af2d7-b83c-4bb1-a90a-ba3528b3dd8d
	 */
	public java.lang.String balanceCode;
	/**
	 * 运行状态值
	 * 
	 * @pdOid b24a726c-e2fe-426a-b560-2609345784f4
	 */
	public java.lang.String balanceValue;
	/**
	 * 上报时间
	 * 
	 * @pdOid 9717cf96-5f08-4229-a029-272f500778e6
	 */
	public java.util.Date reportTime;
	/**
	 * 创建时间
	 * 
	 * @pdOid 15196674-7813-498c-9db6-2ee53565f6f4
	 */
	public java.util.Date createTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public SubSysBalanceInfo() {
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
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
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
	 * Get value of subSysCode
	 * 
	 * @return subSysCode
	 */
	@Basic(optional = true)
	@Column(name = "sub_sys_code", insertable = true, updatable = true, length = 50)
	public java.lang.String getSubSysCode() {
		return subSysCode;
	}

	/**
	 * Set value of subSysCode
	 * 
	 * @param newSubSysCode
	 */
	public void setSubSysCode(java.lang.String newSubSysCode) {
		this.subSysCode = newSubSysCode;
	}

	/**
	 * Get value of balanceCode
	 * 
	 * @return balanceCode
	 */
	@Basic(optional = true)
	@Column(name = "balance_code", insertable = true, updatable = true)
	public java.lang.String getBalanceCode() {
		return balanceCode;
	}

	/**
	 * Set value of balanceCode
	 * 
	 * @param newBalanceCode
	 */
	public void setBalanceCode(java.lang.String newBalanceCode) {
		this.balanceCode = newBalanceCode;
	}

	/**
	 * Get value of balanceValue
	 * 
	 * @return balanceValue
	 */
	@Basic(optional = true)
	@Column(name = "balance_value", insertable = true, updatable = true)
	public java.lang.String getBalanceValue() {
		return balanceValue;
	}

	/**
	 * Set value of balanceValue
	 * 
	 * @param newBalanceValue
	 */
	public void setBalanceValue(java.lang.String newBalanceValue) {
		this.balanceValue = newBalanceValue;
	}

	/**
	 * Get value of reportTime
	 * 
	 * @return reportTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "report_time", insertable = true, updatable = true)
	public java.util.Date getReportTime() {
		return reportTime;
	}

	/**
	 * Set value of reportTime
	 * 
	 * @param newReportTime
	 */
	public void setReportTime(java.util.Date newReportTime) {
		this.reportTime = newReportTime;
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
		return createTime;
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

		if (!(other instanceof SubSysBalanceInfo))
			return false;

		SubSysBalanceInfo cast = (SubSysBalanceInfo) other;

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
		if (this.subSysCode != null)
			hashCode = 29 * hashCode + subSysCode.hashCode();
		if (this.balanceCode != null)
			hashCode = 29 * hashCode + balanceCode.hashCode();
		if (this.balanceValue != null)
			hashCode = 29 * hashCode + balanceValue.hashCode();
		if (this.reportTime != null)
			hashCode = 29 * hashCode + reportTime.hashCode();
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
		ret.append("cn.inovance.iotgp.cds.SubSysBalanceInfo: ");
		ret.append("id='" + id + "'");
		ret.append("subSysCode='" + subSysCode + "'");
		ret.append("balanceCode='" + balanceCode + "'");
		ret.append("balanceValue='" + balanceValue + "'");
		ret.append("reportTime='" + reportTime + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}

}