/***********************************************************************
 * Module:  CustomerInfo.java
 * Author:  w1898
 * Purpose: Defines the Class CustomerInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 客户信息.
 * 
 */
@Entity(name = "CustomerInfo")
@Table(name = "t_oms_customer_info",indexes ={ @Index(name="t_oms_customer_index",columnList= "customer_name"),
		@Index(name="idx_update_time",columnList= "update_time")})
public class CustomerInfo implements java.io.Serializable {

	/** @pdOid c84a95d4-bc77-4268-b482-88522fc97d64 */
	private java.lang.String id;
	/**
	 * 单位名称
	 */
	private java.lang.String customerName;
	
	/**
	 * 联系人
	 */
	private java.lang.String linkMan;

	/**
	 * 联系人电话
	 */
	private java.lang.String linkManPhone;
	/**
	 * 联系人电邮
	 */
	private java.lang.String linkManEmail;
	
	/**
	 * 单位电话
	 */
	private java.lang.String customerPhone;
	/**
	 * 传真
	 */
	private java.lang.String customerFax;
	
	
	/**
	 * 企业logo
	 * 
	 * @pdOid ac655da7-9097-4e67-8254-545ccd329f92
	 */
	private java.lang.String customerLogo;
	/**
	 * 企业域名
	 * 
	 * 
	 * @pdOid 8531a48a-cbf3-464a-9312-59889f2c4e28
	 */
	private java.lang.String customerDomain;
	/**
	 * 站点名词.
	 */
	private String customerSiteName;
	
	/**
	 * 客户子系统
	 */
	private SubSystem customerSubSystem;
	
	/***
	 * 业务系统
	 */
	private java.lang.String customerServerId;
	
	/**
	 * 备注信息.
	 * 
	 * @pdOid 722f984d-6d3d-4495-90f5-e86b2ea31496
	 */
	private java.lang.String remark;
	
	/***
	 * 客户编码.
	 */
	private java.lang.String customerCode;
	
	/**
	 * 联系地址
	 * 
	 * @pdOid 03df7adf-0990-4ca5-b65b-f3abb8949e89
	 */
	private java.lang.String customerAddress;
	/**
	 * 状态
	 * 
	 * @pdOid 24a4e00d-bef7-4e4f-8392-a61a70525a1e
	 */
	private java.lang.String status;
	/**
	 * 采集设备注册限制个数
	 * 
	 * @pdOid d4d4a0cf-2353-4713-9dca-7ddf49a0423f
	 */
	private java.lang.Integer cdRegCodeLimit;
	/**
	 * 子账号创建限制个数
	 * 
	 * @pdOid b5a882a1-3c8d-41a0-afb1-d618461dd25e
	 */
	private java.lang.Integer subAccountLimit;
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

	/** 更新类型 */
	private Short updateType;
	/**
	 * 行业.
	 */
	private java.lang.String customerIndustry;
	
	/**
	 * 客户行业名称.
	 */
	private java.lang.String customerIndustryName;
	
	/**
	 * 客户自定义域名.
	 */
	private java.lang.String customizeDomain;
	
	/**
	 * Mes系统客户编号.
	 */
	private java.lang.String mesCustomerCode;
	
	/**
	 * 客户行业名称.
	 */
	private java.lang.String isResetCdAllocation = "N";
	
	/**
	 * 设备数量
	 */
	private Long countDevice;
	//客户软件批量更新
	private String searchMode;
	public final static String SEARCH_MODE_TD_CONTROLLER_PRODUCT_SCOPE = "1";
	/** Y和N */
	private String selectAll;
	public final static String SELECT_ALL_NO = "N";
	public final static String SELECT_ALL_YES = "Y";
	private String cdIds;
	/** 采集设备下目标设备所属控制器 */
	private String ownerTargetDeviceControllerName;
	/** 采集设备下目标设备所属产品型号 */
	private String ownerTargetDeviceProductName;
	/** 采集设备下目标设备所属控制器范围 */
	private String ownerTargetDeviceControllerUpdateScope;
	private String fileUrl;
	
	public final static  String  N_RESET_CD_ALLOCATION="N";
	public final static  String Y_RESET_CD_ALLOCATION="Y";
	
	public CustomerInfo() {
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
	@Column(name = "customer_logo", insertable = true, updatable = true, length = 100)
	public java.lang.String getCustomerLogo() {
		return customerLogo;
	}
	public void setCustomerLogo(java.lang.String customerLogo) {
		this.customerLogo = customerLogo;
	}
	@Basic(optional = true)
	@Column(name = "customer_domain", insertable = true, updatable = true, length = 100)
	public java.lang.String getCustomerDomain() {
		return customerDomain;
	}
	public void setCustomerDomain(java.lang.String customerDomain) {
		this.customerDomain = customerDomain;
	}
	@ManyToOne(fetch=FetchType.EAGER,cascade = { CascadeType.DETACH,
			CascadeType.MERGE})
	@JoinColumn(name = "customer_sub_sys_id",nullable = true)
	public SubSystem getCustomerSubSystem() {
		return customerSubSystem;
	}
	public void setCustomerSubSystem(SubSystem customerSubSystem) {
		this.customerSubSystem = customerSubSystem;
	}
    
	@Basic(optional = true)
	@Column(name = "customer_desc", insertable = true, updatable = true, length = 100)
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	@Basic(optional = true)
	@Column(name = "customer_server_id", insertable = true, updatable = true, length = 100)
	public java.lang.String getCustomerServerId() {
		return customerServerId;
	}
	public void setCustomerServerId(java.lang.String customerServerId) {
		this.customerServerId = customerServerId;
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
	@Column(name = "customer_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(java.lang.String customerName) {
		this.customerName = customerName;
	}
	@Basic(optional = true)
	@Column(name = "customer_phone", insertable = true, updatable = true, length = 20)
	public java.lang.String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(java.lang.String customerPhone) {
		this.customerPhone = customerPhone;
	}
	@Basic(optional = true)
	@Column(name = "link_man_email", insertable = true, updatable = true, length = 50)
	public java.lang.String getLinkManEmail() {
		return linkManEmail;
	}
	public void setLinkManEmail(java.lang.String linkManEmail) {
		this.linkManEmail = linkManEmail;
	}
	/**
	 * Get value of linkAddress
	 * 
	 * @return linkAddress
	 */
	@Basic(optional = true)
	@Column(name = "customer_address", insertable = true, updatable = true, length = 200)
	public java.lang.String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * Set value of linkAddress
	 * 
	 * @param newLinkAddress
	 */
	public void setCustomerAddress(java.lang.String customerAddress) {
		this.customerAddress = customerAddress;
	}

	
	@Basic(optional = true)
	@Column(name = "link_man", insertable = true, updatable = true, length = 50)
	public java.lang.String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(java.lang.String linkMan) {
		this.linkMan = linkMan;
	}
	@Basic(optional = true)
	@Column(name = "link_man_phone", insertable = true, updatable = true, length = 20)
	public java.lang.String getLinkManPhone() {
		return linkManPhone;
	}
	
	public void setLinkManPhone(java.lang.String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	@Basic(optional = true)
	@Column(name = "customer_fax", insertable = true, updatable = true, length = 20)
	public java.lang.String getCustomerFax() {
		return customerFax;
	}
	
	public void setCustomerFax(java.lang.String customerFax) {
		this.customerFax = customerFax;
	}
	@Basic(optional = true)
	@Column(name = "customer_site_name", insertable = true, updatable = true, length = 100)
	public String getCustomerSiteName() {
		return customerSiteName;
	}
	public void setCustomerSiteName(String customerSiteName) {
		this.customerSiteName = customerSiteName;
	}
	
	@Basic(optional = true)
	@Column(name = "customer_industry", insertable = true, updatable = true, length = 20)
	public java.lang.String getCustomerIndustry() {
		return customerIndustry;
	}
	public void setCustomerIndustry(java.lang.String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}
	
	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true, length = 10)
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String newStatus) {
		this.status = newStatus;
	}

	@Basic(optional = true)
	@Column(name = "cd_reg_code_limit", insertable = true, updatable = true)
	public java.lang.Integer getCdRegCodeLimit() {
		return cdRegCodeLimit;
	}

	public void setCdRegCodeLimit(java.lang.Integer newCdRegCodeLimit) {
		this.cdRegCodeLimit = newCdRegCodeLimit;
	}

	@Basic(optional = true)
	@Column(name = "sub_account_limit", insertable = true, updatable = true)
	public java.lang.Integer getSubAccountLimit() {
		return subAccountLimit;
	}

	public void setSubAccountLimit(java.lang.Integer newSubAccountLimit) {
		this.subAccountLimit = newSubAccountLimit;
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
	@Basic(optional = true)
	@Column(name = "update_type", insertable = true,length = 10)
	public Short getUpdateType() {
		return updateType;
	}
	public void setUpdateType(Short updateType) {
		this.updateType = updateType;
	}
	@Basic(optional = true)
	@Column(name = "customize_domain", insertable = true,length = 100)
	public java.lang.String getCustomizeDomain() {
		return customizeDomain;
	}
	public void setCustomizeDomain(java.lang.String customizeDomain) {
		this.customizeDomain = customizeDomain;
	}
	@Basic(optional = true)
	@Column(name = "mes_customer_code", insertable = true,length = 30)
	public java.lang.String getMesCustomerCode() {
		return mesCustomerCode;
	}
	public void setMesCustomerCode(java.lang.String mesCustomerCode) {
		this.mesCustomerCode = mesCustomerCode;
	}
	@Basic(optional = true)
	@Column(name = "owner_td_controller_name", insertable = true, length = 100)
	public String getOwnerTargetDeviceControllerName() {
		return ownerTargetDeviceControllerName;
	}

	public void setOwnerTargetDeviceControllerName(
			String ownerTargetDeviceControllerName) {
		this.ownerTargetDeviceControllerName = ownerTargetDeviceControllerName;
	}
	@Basic(optional = true)
	@Column(name = "owner_td_product_name", insertable = true, length = 100)
	public String getOwnerTargetDeviceProductName() {
		return ownerTargetDeviceProductName;
	}
	public void setOwnerTargetDeviceProductName(String ownerTargetDeviceProductName) {
		this.ownerTargetDeviceProductName = ownerTargetDeviceProductName;
	}
	@Basic(optional = true)
	@Column(name = "owner_td_software_update_scope_id", insertable = true, length = 36)
	public String getOwnerTargetDeviceControllerUpdateScope() {
		return ownerTargetDeviceControllerUpdateScope;
	}

	public void setOwnerTargetDeviceControllerUpdateScope(
			String ownerTargetDeviceControllerUpdateScope) {
		this.ownerTargetDeviceControllerUpdateScope = ownerTargetDeviceControllerUpdateScope;
	}
	@Transient
	public String getSelectAll() {
		return selectAll;
	}
	public void setSelectAll(String selectAll) {
		this.selectAll = selectAll;
	}
	@Transient
	public String getCdIds() {
		return cdIds;
	}
	public void setCdIds(String cdIds) {
		this.cdIds = cdIds;
	}
	@Transient
	public java.lang.String getCustomerIndustryName() {
		return customerIndustryName;
	}
	public void setCustomerIndustryName(java.lang.String customerIndustryName) {
		this.customerIndustryName = customerIndustryName;
	}
	@Transient
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	@Transient
	public Long getCountDevice() {
		return countDevice;
	}
	public void setCountDevice(Long countDevice) {
		this.countDevice = countDevice;
	}
	@Transient
	public java.lang.String getIsResetCdAllocation() {
		return isResetCdAllocation;
	}
	public void setIsResetCdAllocation(java.lang.String isResetCdAllocation) {
		this.isResetCdAllocation = isResetCdAllocation;
	}
	@Transient
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	@Override
	public String toString() {
		return "CustomerInfo [id=" + id + ", customerName=" + customerName
				+ ", linkMan=" + linkMan + ", linkManPhone=" + linkManPhone
				+ ", linkManEmail=" + linkManEmail + ", customerPhone="
				+ customerPhone + ", customerFax=" + customerFax
				+ ", customerLogo=" + customerLogo + ", customerDomain="
				+ customerDomain + ", customerSiteName=" + customerSiteName
				+ ", customerSubSystem=" + customerSubSystem + ", remark="
				+ remark + ", customerCode=" + customerCode
				+ ", customerAddress=" + customerAddress + ", status=" + status
				+ ", cdRegCodeLimit=" + cdRegCodeLimit + ", subAccountLimit="
				+ subAccountLimit + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", customerIndustry="
				+ customerIndustry + "]";
	}
	
}