package cn.inovance.iotgp.common.domain;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Project Name:iotgp-common-db
 * File Name:DnsCodeController.java
 * Package Name:
 * Date:2015-5-27下午1:45:21
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

/**
 * ClassName:DnsCodeController.
 * @author   w1898
 * @version
 * @since    JDK 1.7
 * @see
 */
@Entity(name = "CustomerController")
@Table(name = "t_oms_customer_controller",indexes ={ @Index(name="t_oms_dnsCode_index",columnList= "dhs_code,update_time"),
		@Index(name="t_oms_controller_index",columnList= "td_controller_name")	,
		@Index(name="t_oms_customercode_controller_index",columnList= "customer_code")
})
public class CustomerController implements Serializable {

	private java.lang.String id;
	/**客户业务系统编码*/
	private java.lang.String dhsCode;
	/**控制器名称*/
	private java.lang.String tdControllerName;
	/**控制器ID*/
	private java.lang.String tdControllerId;
	/**别名*/
	private java.lang.String tdControllerNickName;
	/**客户账号*/
	private java.lang.String customerCode;
	/**客户账号*/
	private java.lang.String defaultController;
	/**产品型号名称*/
	private java.lang.String tdProductName;
	/**产品型号ID*/
	private java.lang.String tdProductId;
	/**产品型号版本*/
	private java.lang.String tdProductVersion;
	
	public static String DEFAULT_CONTROLLER_Y="Y";
	public static String DEFAULT_CONTROLLER_N="N";
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
	@Column(name = "td_controller_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getTdControllerName() {
		return tdControllerName;
	}

	public void setTdControllerName(java.lang.String tdControllerName) {
		this.tdControllerName = tdControllerName;
	}
	@Basic(optional = true)
	@Column(name = "td_controller_type_id_fk", insertable = true, updatable = true, length = 50)
	public java.lang.String getTdControllerId() {
		return tdControllerId;
	}
	public void setTdControllerId(java.lang.String tdControllerId) {
		this.tdControllerId = tdControllerId;
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
	@Column(name = "td_controller_nick_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getTdControllerNickName() {
		return tdControllerNickName;
	}

	public void setTdControllerNickName(java.lang.String tdControllerNickName) {
		this.tdControllerNickName = tdControllerNickName;
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
	@Basic(optional = true)
	@Column(name = "default_controller", insertable = true, updatable = true, nullable = false, length = 2)
	public java.lang.String getDefaultController() {
		return defaultController;
	}

	public void setDefaultController(java.lang.String defaultController) {
		this.defaultController = defaultController;
	}

}

