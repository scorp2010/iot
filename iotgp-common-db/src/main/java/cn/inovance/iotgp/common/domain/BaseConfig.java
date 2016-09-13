/***********************************************************************
 * Module:  BasConfig.java
 * Author:  w1898
 * Purpose: Defines the Class BasConfig
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 客户定制bas信息相关配置信息
 * 
 * 
 * @pdOid 6e9252f4-61b5-44fc-a0d6-f7782ba337f8
 */
@Entity(name = "BasConfig")
@Table(name = "t_oms_base_config")
public class BaseConfig implements java.io.Serializable {
	/** @pdOid 1be1954c-9999-4f4c-b5be-67edd8714c87 */
	private java.lang.String id;
	/**
	 * 配置参数编码
	 * 
	 * @pdOid f49011b2-28f2-4eee-8ff0-2e03b714321c
	 */
	private java.lang.String paraCode;
	/**
	 * 配置参数值
	 * 
	 * @pdOid c44cd583-3bdf-41a9-81aa-b437185b0fea
	 */
	private java.lang.String paraValue;
	/**
	 * 备注信息
	 * 
	 * @pdOid fc7e828f-cb3c-4b31-a330-a3a40b06b5f7
	 */
	private java.lang.String remark;

	/**
	 * @pdRoleInfo migr=no name=CustomizedBasInfo assc=customizedBasInfoBasInfo
	 *             mult=0..1 side=A
	 */
	public CustomerInfo customerInfo;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public BaseConfig() {
		// TODO Add your own initialization code here.
	}

	/**
	 * @pdGenerated default parent getter
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_info_id_fk", referencedColumnName = "id", nullable = true)
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newCustomizedBasInfo
	 */
	public void setCustomerInfo(CustomerInfo newCustomerInfo) {
		if (this.customerInfo == null
				|| !this.customerInfo.equals(newCustomerInfo)) {
			if (this.customerInfo != null) {
				CustomerInfo oldCustomerInfo = this.customerInfo;
				this.customerInfo = null;
				// oldCustomizedBasInfo.removeBasConfig(this);
			}
			if (newCustomerInfo != null) {
				this.customerInfo = newCustomerInfo;
				// this.customizedBasInfo.addBasConfig(this);
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
	 * Get value of paraCode
	 * 
	 * @return paraCode
	 */
	@Basic(optional = true)
	@Column(name = "para_Code", insertable = true, updatable = true, length = 30)
	public java.lang.String getParaCode() {
		return paraCode;
	}

	/**
	 * Set value of paraCode
	 * 
	 * @param newParaCode
	 */
	public void setParaCode(java.lang.String newParaCode) {
		this.paraCode = newParaCode;
	}

	/**
	 * Get value of paraValue
	 * 
	 * @return paraValue
	 */
	@Basic(optional = true)
	@Column(name = "para_value", insertable = true, updatable = true, length = 1024)
	public java.lang.String getParaValue() {
		return paraValue;
	}

	/**
	 * Set value of paraValue
	 * 
	 * @param newParaValue
	 */
	public void setParaValue(java.lang.String newParaValue) {
		this.paraValue = newParaValue;
	}

	/**
	 * Get value of remark
	 * 
	 * @return remark
	 */
	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 1024)
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * Set value of remark
	 * 
	 * @param newRemark
	 */
	public void setRemark(java.lang.String newRemark) {
		this.remark = newRemark;
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

		if (!(other instanceof BaseConfig))
			return false;

		BaseConfig cast = (BaseConfig) other;

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
		if (this.paraCode != null)
			hashCode = 29 * hashCode + paraCode.hashCode();
		if (this.paraValue != null)
			hashCode = 29 * hashCode + paraValue.hashCode();
		if (this.remark != null)
			hashCode = 29 * hashCode + remark.hashCode();
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
		ret.append("cn.inovance.iotgp.common.domain.BasConfig: ");
		ret.append("id='" + id + "'");
		ret.append("paraCode='" + paraCode + "'");
		ret.append("paraValue='" + paraValue + "'");
		ret.append("remark='" + remark + "'");
		return ret.toString();
	}

}