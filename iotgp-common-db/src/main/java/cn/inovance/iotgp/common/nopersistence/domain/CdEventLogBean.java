/***********************************************************************
 * Module:  CdEventLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdOnlineLog
 ***********************************************************************/

package cn.inovance.iotgp.common.nopersistence.domain;

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
 * 采集设备事件日志
 * 
 * @pdOid c6612a2b-274b-4ec6-8a00-971872b861f4
 */
public class CdEventLogBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 * 
	 * @pdOid f386beee-3081-4947-becb-b41936e8434f
	 */
	private java.lang.String id;
	/**
	 * 采集设备注册码
	 * 
	 * @pdOid f8779a44-c0a4-4cf7-b989-a750c5f8b003
	 */
	private java.lang.String cdRegCode;
	/**
	 * 目标设备地址
	 * 
	 * @pdOid 1505a030-9b45-49a8-8796-51c65b339e7d
	 */
	private java.lang.String tdAddress;

	/**
	 * 事件序号
	 * 
	 * @pdOid 1505a030-9b45-49a8-8796-51c65b339e7d
	 */
	private java.lang.Long eventSeq;
	/**
	 * 事件编码
	 * 
	 * @pdOid 5a9faa8f-9106-48ab-9a03-4527940c191e
	 */
	private java.lang.Integer eventCode;
	/**
	 * 事件内容
	 * 
	 * @pdOid 73241153-ec20-4d38-ac94-59b8e91ee1fc
	 */
	private java.lang.String eventData;
	/**
	 * 事件状态
	 * 
	 * @pdOid c41a452f-eccd-4504-a0ee-419ae19676d9
	 */
	private java.lang.String eventStatus;
	/**
	 * 事件触发时间
	 * 
	 * @pdOid 31246a0a-daca-4d8a-8cc6-ab65bad3d9e0
	 */
	private Date eventTime;
	/**
	 * 事件发生时刻对应实时数据
	 * 
	 * @pdOid 8ed951e9-fb47-4177-adfa-13e651a25005
	 */
	private java.lang.String eventRealTimeDatas;
	/**
	 * 客户编码
	 * 
	 * @pdOid 8ed951e9-fb47-4177-adfa-13e651a25005
	 */
	private java.lang.String customerCode;

	/**
	 * DHS编码
	 * 
	 * @pdOid 8ed951e9-fb47-4177-adfa-13e651a25005
	 */
	private java.lang.String dhsCode;

	/**
	 * 最后更新时间
	 * 
	 * @pdOid 8ed951e9-fb47-4177-adfa-13e651a25005
	 */
	private Date lastUpdateTime;
	
	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdEventLogBean() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
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
	 * @return the tdAddress
	 */
	public java.lang.String getTdAddress() {
		return tdAddress;
	}

	/**
	 * @param tdAddress the tdAddress to set
	 */
	public void setTdAddress(java.lang.String tdAddress) {
		this.tdAddress = tdAddress;
	}

	/**
	 * @return the eventSeq
	 */
	public java.lang.Long getEventSeq() {
		return eventSeq;
	}

	/**
	 * @param eventSeq the eventSeq to set
	 */
	public void setEventSeq(java.lang.Long eventSeq) {
		this.eventSeq = eventSeq;
	}

	/**
	 * @return the eventCode
	 */
	public java.lang.Integer getEventCode() {
		return eventCode;
	}

	/**
	 * @param eventCode the eventCode to set
	 */
	public void setEventCode(java.lang.Integer eventCode) {
		this.eventCode = eventCode;
	}

	/**
	 * @return the eventData
	 */
	public java.lang.String getEventData() {
		return eventData;
	}

	/**
	 * @param eventData the eventData to set
	 */
	public void setEventData(java.lang.String eventData) {
		this.eventData = eventData;
	}

	/**
	 * @return the eventStatus
	 */
	public java.lang.String getEventStatus() {
		return eventStatus;
	}

	/**
	 * @param eventStatus the eventStatus to set
	 */
	public void setEventStatus(java.lang.String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * @return the eventTime
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the eventRealTimeDatas
	 */
	public java.lang.String getEventRealTimeDatas() {
		return eventRealTimeDatas;
	}

	/**
	 * @param eventRealTimeDatas the eventRealTimeDatas to set
	 */
	public void setEventRealTimeDatas(java.lang.String eventRealTimeDatas) {
		this.eventRealTimeDatas = eventRealTimeDatas;
	}

	/**
	 * @return the customerCode
	 */
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	} 

	/**
	 * @return the dhsCode
	 */
	public java.lang.String getDhsCode() {
		return dhsCode;
	}

	/**
	 * @param dhsCode the dhsCode to set
	 */
	public void setDhsCode(java.lang.String dhsCode) {
		this.dhsCode = dhsCode;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * Mongodb会自动生成ObjectId
	 * @author fhp
	 *
	 */
	public class Oid{
		String $oid;
		public String get$oid() {
			return $oid;
		}
 
		public void set$oid(String $oid) {
			this.$oid = $oid;
		}
		
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

		if (!(other instanceof CdEventLogBean))
			return false;

		CdEventLogBean cast = (CdEventLogBean) other;

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
		ret.append("cn.inovance.iotgp.common.domain.CdOnlineLog: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		return ret.toString();
	}

}