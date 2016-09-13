/***********************************************************************
 * Module:  DeviceLastLoginTime.java
 * Author:  lk2200
 * Purpose: Defines the Class DeviceLastLoginTime
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
 *设备前天最后的在线状态，用来计算昨天的设备在线率.
 * 
 */
@Entity(name = "DeviceLastLoginStatus")
@Table(name = "t_mms_cd_device_last_login_status")
public class DeviceLastLoginStatus implements java.io.Serializable {

	/** @pdOid c84a95d4-bc77-4268-b482-88522fc97d64 */
	private java.lang.String id;
	/**
	 * 设备注册码
	 */
	private java.lang.String cdRegCode;
	/** 在线状态 1 正常 2 离线 */
	private java.lang.String onlineStauts;
	/** 最后登录时间 */
	private java.util.Date lastTime;
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
	
	public DeviceLastLoginStatus() {
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
	@Column(name = "cd_reg_code", insertable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(java.lang.String newRegCode) {
		this.cdRegCode = newRegCode;
	}
	
	@Basic(optional = true)
	@Column(name = "online_stauts", insertable = true, updatable = true, length = 10)
	public java.lang.String getOnlineStauts() {
		return onlineStauts;
	}
	public void setOnlineStauts(java.lang.String newOnlineStauts) {
		this.onlineStauts = newOnlineStauts;
	}
	
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(java.util.Date lastTime) {
		this.lastTime = lastTime;
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
		return "CustomerInfo [id=" + id + ", cdRegCode=" + cdRegCode
				+ ", onlineStauts=" + onlineStauts
				+ ", lastTime=" + lastTime
				+ ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}