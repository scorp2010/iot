/***********************************************************************
 * Module:  PhoneCallTime.java
 * Author:  w1898
 * Purpose: Defines the Class PhoneCallTime
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
 * 通话记录
 * 
 * @pdOid 3f69ccb3-c50f-43c8-9ac9-1c511f6910fb
 */
@Entity(name = "PhoneCallTime")
@Table(name = "t_mms_phone_call_time")
public class PhoneCallTime implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid dbeae069-9fb1-43ee-863a-a5a94ee87904
	 */
	private java.lang.String id;
	/**
	 * 设备注册码
	 * 
	 * @pdOid 9314d0a2-ee83-4443-bdf4-ec181aad9ef9
	 */
	private java.lang.String cdRegCode;
	/**
	 * 目标设备序号
	 * 
	 * @pdOid 06f4e758-4f2f-46b7-9d8b-604a638fa902
	 */
	private java.lang.Integer tdSeq;
	/**
	 * 国际移动用户识别码
	 * 
	 * @pdOid afb50320-101b-4f49-b6c7-2fa50e992c46
	 */
	private java.lang.String simImsi;
	/**
	 * 通话开始时间
	 * 
	 * @pdOid 61a9eaac-f3ea-4bc0-9ec8-3823c23098f1
	 */
	private java.util.Date startTime;
	/**
	 * 通话截至时间
	 * 
	 * @pdOid 4890ecf2-cedf-42a4-aff5-b6d1db307b45
	 */
	private java.util.Date endTime;
	/**
	 * 按s计通话时长
	 * 
	 * @pdOid 025d0f07-8026-4472-887d-e7e1efbad287
	 */
	private java.lang.Integer callTime;
	/**
	 * 创建时间
	 * 
	 * @pdOid 979e55b7-05e1-4710-8611-10deb58d7eb0
	 */
	private java.util.Date createTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public PhoneCallTime() {
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
	 * Get value of tdSeq
	 * 
	 * @return tdSeq
	 */
	@Basic(optional = true)
	@Column(name = "td_seq", insertable = true, updatable = true)
	public java.lang.Integer getTdSeq() {
		return tdSeq;
	}

	/**
	 * Set value of tdSeq
	 * 
	 * @param newTdSeq
	 */
	public void setTdSeq(java.lang.Integer newTdSeq) {
		this.tdSeq = newTdSeq;
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
	 * Get value of startTime
	 * 
	 * @return startTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getStartTime() {
		if (this.startTime != null)
			return this.startTime;
		return new Date();
	}

	/**
	 * Set value of startTime
	 * 
	 * @param newStartTime
	 */
	public void setStartTime(java.util.Date newStartTime) {
		this.startTime = newStartTime;
	}

	/**
	 * Get value of endTime
	 * 
	 * @return endTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getEndTime() {
		if (this.endTime != null)
			return this.endTime;
		return new Date();
	}

	/**
	 * Set value of endTime
	 * 
	 * @param newEndTime
	 */
	public void setEndTime(java.util.Date newEndTime) {
		this.endTime = newEndTime;
	}

	/**
	 * Get value of callTime
	 * 
	 * @return callTime
	 */
	@Basic(optional = true)
	@Column(name = "call_time", insertable = true, updatable = true)
	public java.lang.Integer getCallTime() {
		return callTime;
	}

	/**
	 * Set value of callTime
	 * 
	 * @param newCallTime
	 */
	public void setCallTime(java.lang.Integer newCallTime) {
		this.callTime = newCallTime;
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

		if (!(other instanceof PhoneCallTime))
			return false;

		PhoneCallTime cast = (PhoneCallTime) other;

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
		if (this.tdSeq != null)
			hashCode = 29 * hashCode + tdSeq.hashCode();
		if (this.simImsi != null)
			hashCode = 29 * hashCode + simImsi.hashCode();
		if (this.startTime != null)
			hashCode = 29 * hashCode + startTime.hashCode();
		if (this.endTime != null)
			hashCode = 29 * hashCode + endTime.hashCode();
		if (this.callTime != null)
			hashCode = 29 * hashCode + callTime.hashCode();
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
		ret.append("cn.inovance.iotgp.bms.mms.PhoneCallTime: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		ret.append("tdSeq='" + tdSeq + "'");
		ret.append("simImsi='" + simImsi + "'");
		ret.append("startTime='" + startTime + "'");
		ret.append("endTime='" + endTime + "'");
		ret.append("callTime='" + callTime + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}

}