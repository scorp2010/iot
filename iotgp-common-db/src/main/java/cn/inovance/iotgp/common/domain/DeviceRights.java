/***********************************************************************
 * Module:  DeviceRights.java
 * Author:  w1898
 * Purpose: Defines the Class DeviceRights
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * 客户设备权限
 * 
 * 
 * @pdOid e0072757-784d-49f6-bdde-898965e03041
 */
@Entity(name = "DeviceRights")
@Table(name = "t_oms_device_rights")
public class DeviceRights implements java.io.Serializable{
	/** @pdOid 69f5d291-a03b-4ba1-b1fd-a5b0b3711fe3 */
	private String id;
	/** @pdOid 88b1af0e-b718-49f9-a2d0-2a7c485e9f55 */
	private java.lang.String cdRegCode;
	/**
	 * 设备操作权限 8位字符串表示： 00000000 左起 第一位表示deviceManage 第二位targetDeviceManage
	 * 第三位recordManage 第四位realtimeView 第五位talk 0-无权限 1-有权限 后面三位预留
	 * 
	 * 
	 * @pdOid 7560e4a1-d609-4799-bf10-6e7249cb9e1d
	 */
	private java.lang.String rights;
	/**
	 * 权限类型 0-所属权限 1-使用权限
	 * 
	 * @pdOid 2188b81b-ed37-49ec-b7f6-6baa9c5161bd
	 */
	private java.lang.String rightType;

	/**
	 * 权限类型名称
	 * 
	 * @pdOid 2188b81b-ed37-49ec-b7f6-6baa9c5161bd
	 */
	private java.lang.String rightTypeName;

	/**
	 * @pdRoleInfo migr=no name=CustomerAccountInfo
	 *             assc=customerAccountInfoDeviceRights mult=0..1 side=A
	 */
	private CustomerInfo customerInfo;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(java.lang.String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}

	@Basic(optional = true)
	@Column(name = "rights", insertable = true, updatable = true, length = 15)
	public java.lang.String getRights() {
		return rights;
	}

	public void setRights(java.lang.String rights) {
		this.rights = rights;
	}

	@Basic(optional = true)
	@Column(name = "right_type", insertable = true, updatable = true, length = 1)
	public java.lang.String getRightType() {
		return rightType;
	}

	public void setRightType(java.lang.String rightType) {
		this.rightType = rightType;
	}

	@Transient
	public java.lang.String getRightTypeName() {
		return rightTypeName;
	}

	public void setRightTypeName(java.lang.String rightTypeName) {
		this.rightTypeName = rightTypeName;
	}


	/**
	 * @pdGenerated default parent getter
	 */

	@ManyToOne
	@JoinColumn(name = "customer_info_id",referencedColumnName = "id", nullable = true)
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newCustomerAccountInfo
	 */
	public void setCustomerInfo(CustomerInfo newCustomerInfo) {
		this.customerInfo = newCustomerInfo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceRights other = (DeviceRights) obj;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (customerInfo == null) {
			if (other.customerInfo != null)
				return false;
		} else if (!customerInfo.equals(other.customerInfo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rightType == null) {
			if (other.rightType != null)
				return false;
		} else if (!rightType.equals(other.rightType))
			return false;
		if (rightTypeName == null) {
			if (other.rightTypeName != null)
				return false;
		} else if (!rightTypeName.equals(other.rightTypeName))
			return false;
		if (rights == null) {
			if (other.rights != null)
				return false;
		} else if (!rights.equals(other.rights))
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime
				* result
				+ ((customerInfo == null) ? 0 : customerInfo
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((rightType == null) ? 0 : rightType.hashCode());
		result = prime * result
				+ ((rightTypeName == null) ? 0 : rightTypeName.hashCode());
		result = prime * result + ((rights == null) ? 0 : rights.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "DeviceRights [id=" + id 
				+ ", cdRegCode=" + cdRegCode + ", rights=" + rights
				+ ", rightType=" + rightType + ", rightTypeName="
				+ rightTypeName + ", customerInfo="
				+ customerInfo + "]";
	}

}