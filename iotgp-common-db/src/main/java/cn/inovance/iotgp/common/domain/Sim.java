/***********************************************************************
 * Module:  Sim.java
 * Author:  w1898
 * Purpose: Defines the Class Sim
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

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
 * sim卡
 * 
 * @pdOid d904a310-bf0b-4d1e-9eed-1e8d897b29f5
 */
@Entity(name = "Sim")
@Table(name = "t_mms_sim",indexes ={ @Index(name="imsi_index",columnList= "imsi")  })
public class Sim implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid dcb0f008-da29-4bc1-b312-c6d5a74f7875
	 */
	private java.lang.String id;
	
	
	/** 注册码 */
	private String cdRegCode;
	
	/** 绑定类型   1：未绑定 2：已绑定*/
	private String bind;
	
	private String bindName;
	private java.util.Date bindDate;
	/**
	 * 国际移动用户识别码
	 * 
	 * @pdOid 6b3c9039-45e0-487e-833b-a11965526cd1
	 */
	private java.lang.String imsi;
	/**
	 * ICCID为IC卡的唯一识别码
	 * 
	 * @pdOid 6a5a245c-99df-4af4-9178-a64a0732d6ee
	 */
	private java.lang.String iccid;
	/**
	 * 电话号码
	 * 
	 * @pdOid 54fdd695-7bbd-4a04-a495-e053d53bc973
	 */
	private java.lang.String phoneNumber;
	/**
	 * 运营商
	 * 
	 * @pdOid 27ea3e62-f3be-4bb8-97c6-9a5d16f3630e
	 */
	private java.lang.String telecomsOperator;
	private java.lang.String telecomsOperatorName;
	

	/**
	 * sim卡类型 2G，3G
	 * 
	 * @pdOid 8c491423-f04f-4cbe-ad1a-e76df146659f
	 */
	private java.lang.String netWork;

	private java.lang.String netWorkName;
	
	private java.lang.String resultTelePhoneMsg;

	/**
	 * 创建时间
	 * 
	 * @pdOid 20279683-6db2-4323-93df-03d90e0b6fde
	 */
	private java.util.Date createTime;
	/** @pdOid 1edab7ce-db5e-43b3-9280-54b3e0d33ab8 */
	private java.util.Date updateTime;
	
	/**sim 卡状态，通过联通接口获取:**/
	private String simStatus;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public Sim() {
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
	@Column(name = "id", nullable = false, insertable = true, updatable = false, length = 36)
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
	 * Get value of imsi
	 * 
	 * @return imsi
	 */
	@Basic(optional = true)
	@Column(name = "imsi", insertable = true, updatable = true, length = 20,unique=true)
	public java.lang.String getImsi() {
		return imsi;
	}

	/**
	 * Set value of imsi
	 * 
	 * @param newImsi
	 */
	public void setImsi(java.lang.String newImsi) {
		this.imsi = newImsi;
	}

	/**
	 * Get value of iccid
	 * 
	 * @return iccid
	 */
	@Basic(optional = true)
	@Column(name = "iccid", insertable = true, updatable = true, length = 20)
	public java.lang.String getIccid() {
		return iccid;
	}

	/**
	 * Set value of iccid
	 * 
	 * @param newIccid
	 */
	public void setIccid(java.lang.String newIccid) {
		this.iccid = newIccid;
	}

	/**
	 * Get value of phoneNumber
	 * 
	 * @return phoneNumber
	 */
	@Basic(optional = true)
	@Column(name = "phone_number", insertable = true, updatable = true, length = 20)
	public java.lang.String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set value of phoneNumber
	 * 
	 * @param newPhoneNumber
	 */
	public void setPhoneNumber(java.lang.String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}

	/**
	 * Get value of telecomsOperator
	 * 
	 * @return telecomsOperator
	 */
	@Basic(optional = true)
	@Column(name = "telecoms_operator", insertable = true, updatable = true, length = 5)
	public java.lang.String getTelecomsOperator() {
		return telecomsOperator;
	}

	/**
	 * Set value of telecomsOperator
	 * 
	 * @param newTelecomsOperator
	 */
	public void setTelecomsOperator(java.lang.String newTelecomsOperator) {
		this.telecomsOperator = newTelecomsOperator;
	}

	/**
	 * Get value of simType
	 * 
	 * @return simType
	 */
	@Basic(optional = true)
	@Column(name = "net_work", insertable = true, updatable = true, length = 5)
	public java.lang.String getNetWork() {
		return netWork;
	}

	/**
	 * Set value of simType
	 * 
	 * @param newSimType
	 */
	public void setNetWork(java.lang.String newNetWork) {
		this.netWork = newNetWork;
	}

	@Transient
	public java.lang.String getNetWorkName() {
		return netWorkName;
	}

	public void setNetWorkName(java.lang.String netWorkName) {
		this.netWorkName = netWorkName;
	}
	
	@Transient
	public java.lang.String getResultTelePhoneMsg() {
		return resultTelePhoneMsg;
	}

	public void setResultTelePhoneMsg(java.lang.String resultTelePhoneMsg) {
		this.resultTelePhoneMsg = resultTelePhoneMsg;
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

	/**
	 * Get value of updateTime
	 * 
	 * @return updateTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getUpdateTime() {
			return updateTime;
	}

	/**
	 * Set value of updateTime
	 * 
	 * @param newUpdateTime
	 */
	public void setUpdateTime(java.util.Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}
	@Column(name = "cd_reg_code", length = 50)
	public String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}
	
	@Basic
	@Column(name = "bind_type", insertable = true, updatable = true, length = 5)
	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bind_date", length = 7)
	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}
	@Transient
	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}


	@Transient
	public java.lang.String getTelecomsOperatorName() {
		return telecomsOperatorName;
	}

	public void setTelecomsOperatorName(java.lang.String telecomsOperatorName) {
		this.telecomsOperatorName = telecomsOperatorName;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sim other = (Sim) obj;
		if (bind == null) {
			if (other.bind != null)
				return false;
		} else if (!bind.equals(other.bind))
			return false;
		if (bindDate == null) {
			if (other.bindDate != null)
				return false;
		} else if (!bindDate.equals(other.bindDate))
			return false;
		if (bindName == null) {
			if (other.bindName != null)
				return false;
		} else if (!bindName.equals(other.bindName))
			return false;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (iccid == null) {
			if (other.iccid != null)
				return false;
		} else if (!iccid.equals(other.iccid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imsi == null) {
			if (other.imsi != null)
				return false;
		} else if (!imsi.equals(other.imsi))
			return false;
		if (netWork == null) {
			if (other.netWork != null)
				return false;
		} else if (!netWork.equals(other.netWork))
			return false;
		if (netWorkName == null) {
			if (other.netWorkName != null)
				return false;
		} else if (!netWorkName.equals(other.netWorkName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (telecomsOperator == null) {
			if (other.telecomsOperator != null)
				return false;
		} else if (!telecomsOperator.equals(other.telecomsOperator))
			return false;
		if (telecomsOperatorName == null) {
			if (other.telecomsOperatorName != null)
				return false;
		} else if (!telecomsOperatorName.equals(other.telecomsOperatorName))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bind == null) ? 0 : bind.hashCode());
		result = prime * result
				+ ((bindDate == null) ? 0 : bindDate.hashCode());
		result = prime * result
				+ ((bindName == null) ? 0 : bindName.hashCode());
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((iccid == null) ? 0 : iccid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
		result = prime * result + ((netWork == null) ? 0 : netWork.hashCode());
		result = prime * result
				+ ((netWorkName == null) ? 0 : netWorkName.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime
				* result
				+ ((telecomsOperator == null) ? 0 : telecomsOperator.hashCode());
		result = prime
				* result
				+ ((telecomsOperatorName == null) ? 0 : telecomsOperatorName
						.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Sim [id=" + id + ", cdRegCode=" + cdRegCode + ", bind=" + bind
				+ ", bindName=" + bindName + ", bindDate=" + bindDate
				+ ", imsi=" + imsi + ", iccid=" + iccid + ", phoneNumber="
				+ phoneNumber + ", telecomsOperator=" + telecomsOperator
				+ ", telecomsOperatorName=" + telecomsOperatorName
				+ ", netWork=" + netWork + ", netWorkName=" + netWorkName
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

	@Column(name = "sim_status", length = 50)
	public String getSimStatus() {
		return simStatus;
	}

	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
	}

}