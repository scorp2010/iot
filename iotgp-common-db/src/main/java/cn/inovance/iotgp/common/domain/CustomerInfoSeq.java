/**
 * Project Name:iotgp-common-db
 * File Name:CustomerSeq.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-9-24下午3:13:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CustomerSeq.
 * Reason:	 客户编码自增，用于生成客户编码和子客户编码
 * Date:     2014-9-24 下午3:13:45 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "CustomerInfoSeq")
@Table(name = "t_oms_customer_info_seq")
public class CustomerInfoSeq implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = -7255033094451491794L;
	/**
	 * 编号.
	 */
	private java.lang.String id;
	/**
	 * 客户编码.
	 */
	private java.lang.String customerCode;
	
	/**
	 * 客户seq.
	 */
	private Integer firstLevelSeq;
	/**
	 * 子客户seq.
	 */
	private java.lang.Integer subCustomerSeq;
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Basic(optional = true)
	@Column(name = "customer_code", insertable = true, updatable = true, length = 100)
	public java.lang.String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}
	@Basic(optional = true)
	@Column(name = "first_level_seq", insertable = true, updatable = true)
	public Integer getFirstLevelSeq() {
		return firstLevelSeq;
	}
	public void setFirstLevelSeq(Integer firstLevelSeq) {
		this.firstLevelSeq = firstLevelSeq;
	}
	@Basic(optional = true)
	@Column(name = "sub_customer_seq", insertable = true, updatable = true)
	public java.lang.Integer getSubCustomerSeq() {
		return subCustomerSeq;
	}
	public void setSubCustomerSeq(java.lang.Integer subCustomerSeq) {
		this.subCustomerSeq = subCustomerSeq;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerCode == null) ? 0 : customerCode.hashCode());
		result = prime * result
				+ ((firstLevelSeq == null) ? 0 : firstLevelSeq.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((subCustomerSeq == null) ? 0 : subCustomerSeq.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInfoSeq other = (CustomerInfoSeq) obj;
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} else if (!customerCode.equals(other.customerCode))
			return false;
		if (firstLevelSeq == null) {
			if (other.firstLevelSeq != null)
				return false;
		} else if (!firstLevelSeq.equals(other.firstLevelSeq))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subCustomerSeq == null) {
			if (other.subCustomerSeq != null)
				return false;
		} else if (!subCustomerSeq.equals(other.subCustomerSeq))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CustomerInfoSeq [id=" + id + ", customerCode=" + customerCode
				+ ", firstLevelSeq=" + firstLevelSeq + ", subCustomerSeq="
				+ subCustomerSeq + "]";
	}

}

