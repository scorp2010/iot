package cn.inovance.iotgp.common.domain;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Project Name:iotgp-common-db
 * File Name:CustomerProductModel.java
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

/**
 * ClassName:CustomerProductModel.
 * @author   lk2200
 * @version
 * @since    JDK 1.7
 * @see
 */
@Entity(name = "CustomerProductModel")
@Table(name = "t_oms_customer_product_model",indexes ={ @Index(name="t_oms_dnsCode_index",columnList= "dhs_code,update_time"),
		@Index(name="t_oms_product_index",columnList= "td_product_name")	,
		@Index(name="t_oms_customercode_product_index",columnList= "customer_code")
})
public class CustomerProductModel implements Serializable {

	private java.lang.String id;
	/**客户业务系统编码*/
	private java.lang.String dhsCode;
	/**客户账号*/
	private java.lang.String customerCode;
	/**产品型号名称*/
	private java.lang.String tdProductName;
	/**产品型号ID*/
	private java.lang.String tdProductId;
	/**产品型号版本*/
	private java.lang.String tdProductVersion;
	
	/**单板软件名称*/
	private java.lang.String softName;
	/**单板软件版本*/
	private java.lang.String softVersion;
	
	/**
	 * 更新时间
	 * 
	 * @pdOid 7eb136d2-0492-4a6e-a319-824b80ad1627
	 */
	private java.lang.Long updateTime;
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
	@Column(name = "dhs_code", insertable = true, updatable = true, length = 50)
	public java.lang.String getDhsCode() {
		return dhsCode;
	}

	public void setDhsCode(java.lang.String dhsCode) {
		this.dhsCode = dhsCode;
	}
	
	@Basic(optional = true)
	@Column(name = "td_product_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getTdProductName() {
		return tdProductName;
	}

	public void setTdProductName(java.lang.String tdProductName) {
		this.tdProductName = tdProductName;
	}

	@Basic(optional = true)
	@Column(name = "td_product_model_id_fk", insertable = true, updatable = true, length = 50)
	public java.lang.String getTdProductId() {
		return tdProductId;
	}

	public void setTdProductId(java.lang.String tdProductId) {
		this.tdProductId = tdProductId;
	}

	@Basic(optional = true)
	@Column(name = "td_product_version", insertable = true, updatable = true, length = 50)
	public java.lang.String getTdProductVersion() {
		return tdProductVersion;
	}

	public void setTdProductVersion(java.lang.String tdProductVersion) {
		this.tdProductVersion = tdProductVersion;
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
	@Column(name = "update_time")
	public java.lang.Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}

	@Transient
	public java.lang.String getSoftName() {
		return softName;
	}

	public void setSoftName(java.lang.String softName) {
		this.softName = softName;
	}

	@Transient
	public java.lang.String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(java.lang.String softVersion) {
		this.softVersion = softVersion;
	}
	

}

