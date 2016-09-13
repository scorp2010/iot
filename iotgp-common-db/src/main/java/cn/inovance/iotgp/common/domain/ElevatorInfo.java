/***********************************************************************
 * Module:  ElevatorInfo.java
 * Author:  lk2200
 * Purpose: Defines the Class ElevatorInfo
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
 *电梯主机与中继对应日志表
 * 
 */
@Entity(name = "ElevatorInfo")
@Table(name = "t_elevator_info")
public class ElevatorInfo implements java.io.Serializable {

	/** @pdOid c84a95d4-bc77-4268-b482-88522fc97d64 */
	private java.lang.String id;
	/**
	 * 电梯工号
	 */
	private java.lang.String factoryNo;
	/** 电梯名称 */
	private java.lang.String aliasOfAddress;
	/**中继注册码 */
	private java.lang.String relayRegCode;
	/**主机注册码 */
	private java.lang.String mainRegCode;
	/**
	 * 创建时间
	 * 
	 * @pdOid bd955ad8-4100-405d-b51a-64748fcfb996
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 * 
	 * @pdOid 7eb136d2-0492-4a6e-a319-824b80ad1627
	 */
	private java.util.Date updateTime;
	
	public ElevatorInfo() {
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

	@Basic(optional = true)
	@Column(name = "factory_no", insertable = true, updatable = true, length = 20)
	public java.lang.String getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(java.lang.String factoryNo) {
		this.factoryNo = factoryNo;
	}
	@Basic(optional = true)
	@Column(name = "alias_of_address", insertable = true, updatable = true, length = 500)
	public java.lang.String getAliasOfAddress() {
		return aliasOfAddress;
	}
	public void setAliasOfAddress(java.lang.String aliasOfAddress) {
		this.aliasOfAddress = aliasOfAddress;
	}
	@Basic(optional = true)
	@Column(name = "relay_reg_code", insertable = true, updatable = true, length = 36)
	public String getRelayRegCode() {
		return relayRegCode;
	}
	public void setRelayRegCode(String relayRegCode) {
		this.relayRegCode = relayRegCode;
	}
	@Basic(optional = true)
	@Column(name = "main_reg_code", insertable = true, updatable = true, length = 36)
	public String getMainRegCode() {
		return mainRegCode;
	}
	public void setMainRegCode(String mainRegCode) {
		this.mainRegCode = mainRegCode;
	}
	
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public java.util.Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return updateTime;
	}
	public void setUpdateTime(java.util.Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}
	
	@Override
	public String toString() {
		return "CustomerInfo [id=" + id + ", factoryNo=" + factoryNo
				+ ", aliasOfAddress=" + aliasOfAddress
				+ ", relayRegCode=" + relayRegCode
				+ ", mainRegCode=" + mainRegCode
				+ ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}