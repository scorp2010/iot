/***********************************************************************
 * Module:  SimDataFlow.java
 * Author:  w1898
 * Purpose: Defines the Class SimDataFlow
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
 * sim卡数据流量
 * 
 * @pdOid 62c8695b-13e2-449d-8637-5f14c2ea3210
 */
@Entity(name = "SimDataFlow")
@Table(name = "t_mms_sim_data_flow")
public class SimDataFlow implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 387ca414-56f3-455e-9629-36bf71a26a55
	 */
	private java.lang.String id;
	/**
	 * 设备注册码
	 * 
	 * @pdOid cb0928a2-df0a-4286-b602-0ec6aea5a4f3
	 */
	private java.lang.String cdRegCode;
	/**
	 * 国际移动用户识别码
	 * 
	 * @pdOid 1302b90c-071c-4d01-bee6-220245c9ddfa
	 */
	private java.lang.String simImsi;
	/**
	 * 数据总流量
	 * 
	 * @pdOid 086b546d-3f49-49a4-bb85-c55c761e6276
	 */
	private java.lang.Long totalFlow;
	/**
	 * 周期流量（时间段内流程）
	 * 
	 * @pdOid b74c346f-049c-4270-b657-1f9bfa510897
	 */
	private java.lang.Integer intervalFlow;
	/**
	 * 上报时间
	 * 
	 * @pdOid 0dc09ad7-d4c9-4a34-bfdd-9f08a74cd048
	 */
	private java.util.Date reportTime;
	/**
	 * 最新上报时间
	 * 
	 * @pdOid df8ac657-9804-4d93-81f6-96d0fce53677
	 */
	private java.util.Date lastReportTime;
	/**
	 * 创建时间
	 * 
	 * @pdOid 199c9f79-8a7b-4433-af0a-b91f747cfaf6
	 */
	private java.util.Date createTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public SimDataFlow() {
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

	/**
	 * Get value of simImsi
	 * 
	 * @return simImsi
	 */
	@Basic(optional = true)
	@Column(name = "sim_imsi", insertable = true, updatable = true, length = 15)
	public java.lang.String getSimImsi() {
		return simImsi;
	}

	/**
	 * Set value of simImsi
	 * 
	 * @param newSimImsi
	 */
	public void setSimImsi(java.lang.String newSimImsi) {
		this.simImsi = newSimImsi;
	}

	/**
	 * Get value of totalFlow
	 * 
	 * @return totalFlow
	 */
	@Basic(optional = true)
	@Column(name = "total_flow", insertable = true, updatable = true)
	public java.lang.Long getTotalFlow() {
		return totalFlow;
	}

	/**
	 * Set value of totalFlow
	 * 
	 * @param newTotalFlow
	 */
	public void setTotalFlow(java.lang.Long newTotalFlow) {
		this.totalFlow = newTotalFlow;
	}

	/**
	 * Get value of intervalFlow
	 * 
	 * @return intervalFlow
	 */
	@Basic(optional = true)
	@Column(name = "interval_flow", insertable = true, updatable = true)
	public java.lang.Integer getIntervalFlow() {
		return intervalFlow;
	}

	/**
	 * Set value of intervalFlow
	 * 
	 * @param newIntervalFlow
	 */
	public void setIntervalFlow(java.lang.Integer newIntervalFlow) {
		this.intervalFlow = newIntervalFlow;
	}

	/**
	 * Get value of reportTime
	 * 
	 * @return reportTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "report_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getReportTime() {
		if (null != reportTime)
			return reportTime;
		return new Date();
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
	 * Get value of lastReportTime
	 * 
	 * @return lastReportTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_report_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getLastReportTime() {
		if (null != lastReportTime)
			return lastReportTime;
		return new Date();
	}

	/**
	 * Set value of lastReportTime
	 * 
	 * @param newLastReportTime
	 */
	public void setLastReportTime(java.util.Date newLastReportTime) {
		this.lastReportTime = newLastReportTime;
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
		if (null != createTime)
			return createTime;
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

		if (!(other instanceof SimDataFlow))
			return false;

		SimDataFlow cast = (SimDataFlow) other;

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
		if (this.simImsi != null)
			hashCode = 29 * hashCode + simImsi.hashCode();
		if (this.totalFlow != null)
			hashCode = 29 * hashCode + totalFlow.hashCode();
		if (this.intervalFlow != null)
			hashCode = 29 * hashCode + intervalFlow.hashCode();
		if (this.reportTime != null)
			hashCode = 29 * hashCode + reportTime.hashCode();
		if (this.lastReportTime != null)
			hashCode = 29 * hashCode + lastReportTime.hashCode();
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
		ret.append("cn.inovance.iotgp.bms.mms.SimDataFlow: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		ret.append("simImsi='" + simImsi + "'");
		ret.append("totalFlow='" + totalFlow + "'");
		ret.append("intervalFlow='" + intervalFlow + "'");
		ret.append("reportTime='" + reportTime + "'");
		ret.append("lastReportTime='" + lastReportTime + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}

}