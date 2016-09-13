/***********************************************************************
 * Module:  CdRegisterCode.java
 * Author:  w1898
 * Purpose: Defines the Class CdRegisterCode
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;
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
 * 设备注册码
 * 
 * @pdOid 11be18a6-e5f7-457f-aac3-f57787c05b1a
 */
@Entity(name = "CdRegisterCode")
@Table(name = "t_cds_cd_register_code",indexes ={ @Index(name="cd_reg_code_index",columnList= "reg_code"),@Index(name="cd_production_date_index",columnList= "production_date"),
		@Index(name="cd_product_sn_index",columnList= "product_sn"),
		@Index(name="cd_customer_code_index",columnList= "customer_code"),
		@Index(name="cd_active_status_index",columnList= "active_status"),
		@Index(name="cd_production_date",columnList= "active_status")})
public class CdRegisterCode implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 32c97975-9e84-4222-8f63-aa782ccc6d87
	 */
	private java.lang.String id;
	/**
	 * 注册码 生产需要字段（导出）
	 * 
	 * @pdOid 4e7ec3f0-3c22-4304-b782-4f0b34a60094
	 */
	private java.lang.String regCode;

	/**
	 * 公司编码.
	 */
	private java.lang.String manufactuer;
	/**
	 * mac地址 生产需要字段（导出）
	 * 
	 * @pdOid 89140f91-c0c1-4496-96a4-d775a168eee4
	 */
	private java.lang.String mac;
	/**
	 * 随机码 生产需要字段（导出）
	 * 
	 * @pdOid 95f692cd-0456-4820-9eb9-9f3124753bc9
	 */
	private java.lang.String randomCode;
	/**
	 * 加密序列号 生产需要字段（导出）
	 * 
	 * @pdOid cb15ac4a-7368-4d8a-816a-db17e136e518
	 */
	private java.lang.String encryptSn;
	/**
	 * 生产日期 生产需要字段（导出）
	 * 
	 * @pdOid 991b2f68-6b92-42d1-b46c-96ae1e6d8725
	 */
	private java.util.Date productionDate;
	/** @pdOid 0f6ce551-1859-4b83-a0d5-c613e8225e22 */
	private java.lang.String simImsi;
	/**
	 * 单板sn.
	 * 
	 * @pdOid 4bb1c302-2b0c-49a6-a5b1-a2ebac31de76
	 */
	private java.lang.String boardSn;
	/**
	 * 生产流水号.
	 */
	private java.lang.String productSn;
	
	/**
	 * 整机SN.
	 */
	private java.lang.String packageSn;
	/**
	 * 历史流水号.
	 */
	private java.lang.String historyProductSn;
	/**
	 * 产品型号.
	 */
	private java.lang.String productModel;
	/**
	 * 生产测试时间
	 * 
	 * @pdOid 02041f52-9524-450a-8887-ea01d38d9a97
	 */
	private java.util.Date testTime;
	/**
	 * 激活时间
	 * 
	 * @pdOid 7396857e-61d7-4451-81df-05f7364b60ca
	 */
	private java.util.Date activeTime;
	/**
	 * 设备测试状态
	 * 
	 * @pdOid ea31869e-3c8e-4e4e-b7eb-e9f8f8f7e292
	 */
	private String cdTestStatus;
	
	private String cdTestStatusName;
	

	/**
	 * 设备激活状态
	 * 
	 * @pdOid ea31869e-3c8e-4e4e-b7eb-e9f8f8f7e292
	 */
	private String cdActiveStatus;
	
	private String cdActiveStatusName;

	/** @pdOid 4454d621-4fbf-4007-a9c7-cce471b59d2f */
	private java.lang.String creator;

	private java.lang.String creatorName;
	/**
	 * 创建时间
	 * 
	 * @pdOid f42baaed-72a3-46f2-9b05-a13bcf4e1f6b
	 */
	private java.util.Date createTime;

	private java.util.Date updateTime;

	/**
	 * 查询条件.
	 */
	private java.lang.String startCreateTime;
	private java.lang.String endCreateTime;

	private java.lang.String encryptKey = "30012466300124663001246630012466";

	/**
	 * 设备所属客户编码.
	 */
	private java.lang.String customerCode;

	/**
	 * 设备所属客户名称.
	 */
	private java.lang.String customerName;
	
	/**
	 * 附属的主机等其他采集设备.注册码之间用分号;分割
	 */
	private java.lang.String relateCdRegCodes;
	
	/**
	 * 设备模式,现有情况下1.中继2主机
	 */
	private java.lang.Integer mode;
	
	private String fileUrl;
	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdRegisterCode() {
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
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
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
	 * Get value of regCode
	 * 
	 * @return regCode
	 */
	@Basic(optional = true)
	@Column(name = "reg_code", insertable = true, updatable = true, length = 50)
	public java.lang.String getRegCode() {
		return regCode;
	}

	/**
	 * Set value of regCode
	 * 
	 * @param newRegCode
	 */
	public void setRegCode(java.lang.String newRegCode) {
		this.regCode = newRegCode;
	}

	@Basic(optional = true)
	@Column(name = "history_product_sn", insertable = true, updatable = true, length = 200)
	public java.lang.String getHistoryProductSn() {
		return historyProductSn;
	}

	public void setHistoryProductSn(java.lang.String historyProductSn) {
		this.historyProductSn = historyProductSn;
	}

	/**
	 * Get value of mac
	 * 
	 * @return mac
	 */
	@Basic(optional = true)
	@Column(name = "mac", insertable = true, updatable = true, length = 20)
	public java.lang.String getMac() {
		return mac;
	}

	/**
	 * Set value of productModel
	 * 
	 * @param productModel
	 */
	public void setProductModel(java.lang.String productModel) {
		this.productModel = productModel;
	}

	/**
	 * Get value of productModel
	 * 
	 * @return productModel
	 */
	@Basic(optional = true)
	@Column(name = "product_model", insertable = true, updatable = true, length = 50)
	public java.lang.String getProductModel() {
		return this.productModel;
	}

	/**
	 * Set value of mac
	 * 
	 * @param newMac
	 */
	public void setMac(java.lang.String newMac) {
		this.mac = newMac;
	}

	/**
	 * Get value of randomCode
	 * 
	 * @return randomCode
	 */
	@Basic(optional = true)
	@Column(name = "random_code", insertable = true, updatable = true, length = 10)
	public java.lang.String getRandomCode() {
		return randomCode;
	}

	/**
	 * Set value of randomCode
	 * 
	 * @param newRandomCode
	 */
	public void setRandomCode(java.lang.String newRandomCode) {
		this.randomCode = newRandomCode;
	}

	/**
	 * Get value of encryptSn
	 * 
	 * @return encryptSn
	 */
	@Basic(optional = true)
	@Column(name = "encrypt_sn", insertable = true, updatable = true, length = 16)
	public java.lang.String getEncryptSn() {
		return encryptSn;
	}

	/**
	 * Set value of encryptSn
	 * 
	 * @param newEncryptSn
	 */
	public void setEncryptSn(java.lang.String newEncryptSn) {
		this.encryptSn = newEncryptSn;
	}

	/**
	 * Get value of productionDate
	 * 
	 * @return productionDate
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.DATE)
	@Column(name = "production_date", insertable = true, updatable = true)
	public java.util.Date getProductionDate() {
		return this.productionDate;
	}

	/**
	 * Set value of productionDate
	 * 
	 * @param newProductionDate
	 */
	public void setProductionDate(java.util.Date newProductionDate) {
		this.productionDate = newProductionDate;
	}

	/**
	 * Get value of simImsi
	 * 
	 * @return simImsi
	 */
	@Basic(optional = true)
	@Column(name = "sim_imsi", insertable = true, updatable = true, length = 15)
	public java.lang.String getSimImsi() {
		return simImsi;
	}

	/**
	 * Set value of simImsi
	 * 
	 * @param newSimImsi
	 */
	public void setSimImsi(java.lang.String newSimImsi) {
		this.simImsi = newSimImsi;
	}

	/**
	 * Get value of productSn
	 * 
	 * @return productSn
	 */
	@Basic(optional = true)
	@Column(name = "board_sn", insertable = true, updatable = true, length = 32)
	public java.lang.String getBoardSn() {
		return boardSn;
	}

	/**
	 * Set value of productSn
	 * 
	 * @param newProductSn
	 */
	public void setBoardSn(java.lang.String boardSn) {
		this.boardSn = boardSn;
	}

	/**
	 * Get value of productSn
	 * 
	 * @return productSn
	 */
	@Basic(optional = true)
	@Column(name = "product_sn", insertable = true, updatable = true, length = 32)
	public java.lang.String getProductSn() {
		return productSn;
	}

	/**
	 * Set value of productSn
	 * 
	 * @param newProductSn
	 */
	public void setProductSn(java.lang.String productSn) {
		this.productSn = productSn;
	}

	/**
	 * Get value of testTime
	 * 
	 * @return testTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "test_time", insertable = true, updatable = true)
	public java.util.Date getTestTime() {
		return testTime;
	}

	/**
	 * Set value of testTime
	 * 
	 * @param newTestTime
	 */
	public void setTestTime(java.util.Date newTestTime) {
		this.testTime = newTestTime;
	}

	/**
	 * Get value of activeTime
	 * 
	 * @return activeTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "active_time", insertable = true, updatable = true)
	public java.util.Date getActiveTime() {
		return activeTime;
	}

	/**
	 * Set value of activeTime
	 * 
	 * @param newActiveTime
	 */
	public void setActiveTime(java.util.Date newActiveTime) {
		this.activeTime = newActiveTime;
	}

	/**
	 * Get value of cdType
	 * 
	 * @return cdType
	 */
	@Basic
	@Column(name = "test_status", insertable = true, updatable = true, length = 5)
	public String getCdTestStatus() {
		/*if(StringUtils.isBlank(cdTestStatus))
			return StatusEnum.CD_REGISTERT_CODE_TEST_STATUS_CODE_NO.value;*/
		return cdTestStatus;
	}

	/**
	 * Set value of cdType
	 * 
	 * @param newCdType
	 */
	public void setCdTestStatus(String cdTestStatus) {
		this.cdTestStatus = cdTestStatus;
	}

	/**
	 * Get value of cdType
	 * 
	 * @return cdType
	 */
	@Basic
	@Column(name = "active_status", insertable = true, updatable = true, length = 5)
	public String getCdActiveStatus() {
		/*if(StringUtils.isBlank(cdActiveStatus))
			return StatusEnum.CD_REGISTERT_CODE_ACTIVE_STATUS_CODE_NO.value;*/
		return cdActiveStatus;
	}

	/**
	 * Set value of cdType
	 * 
	 * @param newCdType
	 */
	
	public void setCdActiveStatus(String cdActiveStatus) {
		this.cdActiveStatus = cdActiveStatus;
	}
	@Transient
	public String getCdTestStatusName() {
		
		return cdTestStatusName;
	}

	public void setCdTestStatusName(String cdTestStatusName) {
		this.cdTestStatusName = cdTestStatusName;
	}
	@Transient
	public String getCdActiveStatusName() {
		
		return cdActiveStatusName;
	}

	public void setCdActiveStatusName(String cdActiveStatusName) {
		this.cdActiveStatusName = cdActiveStatusName;
	}
	/**
	 * Get value of creator
	 * 
	 * @return creator
	 */
	@Basic(optional = true)
	@Column(name = "creator", nullable = true, length = 50)
	public java.lang.String getCreator() {
		return creator;
	}

	/**
	 * Set value of creator
	 * 
	 * @param newCreator
	 */
	public void setCreator(java.lang.String newCreator) {
		this.creator = newCreator;
	}

	@Basic(optional = true)
	@Column(name = "creator_name", nullable = true, length = 50)
	public java.lang.String getCreatorName() {
		return creatorName;
	}

	/**
	 * Set value of creator
	 * 
	 * @param newCreator
	 */
	public void setCreatorName(java.lang.String creatorName) {
		this.creatorName = creatorName;
	}

	@Basic(optional = true)
	@Column(name = "encrypt_key", nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(java.lang.String encryptKey) {
		this.encryptKey = encryptKey;
	}

	/**
	 * Get value of createTime
	 * 
	 * @return createTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * Set value of createTime
	 * 
	 * @param newCreateTime
	 */
	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * Set value of createTime
	 * 
	 * @param newCreateTime
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	@Basic(optional = true)
	@Column(name = "customer_code", nullable = true, length = 100)
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}
	@Basic(optional = true)
	@Column(name = "customer_name", nullable = true, length = 100)
	public java.lang.String getCustomerName() {
		return customerName;
	}
     
	public void setCustomerName(java.lang.String customerName) {
		this.customerName = customerName;
	}
	@Basic(optional = true)
	@Column(name = "relate_cd_reg_codes", nullable = true, length = 200)
	public java.lang.String getRelateCdRegCodes() {
		return relateCdRegCodes;
	}

	public void setRelateCdRegCodes(java.lang.String relateCdRegCodes) {
		this.relateCdRegCodes = relateCdRegCodes;
	}
	@Basic(optional = true)
	@Column(name = "mode", nullable = true)
	public java.lang.Integer getMode() {
		return mode;
	}

	public void setMode(java.lang.Integer mode) {
		this.mode = mode;
	}

	@Transient
	public java.lang.String getStartCreateTime() {
		return this.startCreateTime;
	}

	public void setStartCreateTime(java.lang.String startCreateTime) {

		this.startCreateTime = startCreateTime;

	}

	@Transient
	public java.lang.String getEndCreateTime() {
		return this.endCreateTime;
	}

	public void setEndCreateTime(java.lang.String endCreateTime) {
		this.endCreateTime = endCreateTime;

	}

	@Transient
	public java.lang.String getManufactuer() {
		return this.manufactuer;
	}

	public void setManufactuer(java.lang.String manufactuer) {
		this.manufactuer = manufactuer;

	}
	
	@Basic(optional = true)
	@Column(name = "package_sn", nullable = true, length = 50)
	public java.lang.String getPackageSn() {
		return packageSn;
	}

	public void setPackageSn(java.lang.String packageSn) {
		this.packageSn = packageSn;
	}

	@Transient
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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

		if (!(other instanceof CdRegisterCode))
			return false;

		CdRegisterCode cast = (CdRegisterCode) other;

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
		if (this.regCode != null)
			hashCode = 29 * hashCode + regCode.hashCode();
		if (this.mac != null)
			hashCode = 29 * hashCode + mac.hashCode();
		if (this.randomCode != null)
			hashCode = 29 * hashCode + randomCode.hashCode();
		if (this.encryptSn != null)
			hashCode = 29 * hashCode + encryptSn.hashCode();
		if (this.productionDate != null)
			hashCode = 29 * hashCode + productionDate.hashCode();
		if (this.simImsi != null)
			hashCode = 29 * hashCode + simImsi.hashCode();
		if (this.boardSn != null)
			hashCode = 29 * hashCode + boardSn.hashCode();
		if (this.productSn != null)
			hashCode = 29 * hashCode + productSn.hashCode();
		if (this.cdTestStatus != null)
			hashCode = 29 * hashCode + cdTestStatus.hashCode();
		if (this.testTime != null)
			hashCode = 29 * hashCode + testTime.hashCode();
		if (this.activeTime != null)
			hashCode = 29 * hashCode + activeTime.hashCode();
		if (this.cdActiveStatus != null)
			hashCode = 29 * hashCode + cdActiveStatus.hashCode();
		if (this.creator != null)
			hashCode = 29 * hashCode + creator.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
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
		ret.append("cn.inovance.iotgp.cds.CdRegisterCode: ");
		ret.append("id='" + id + "'");
		ret.append("regCode='" + regCode + "'");
		ret.append("mac='" + mac + "'");
		ret.append("randomCode='" + randomCode + "'");
		ret.append("encryptSn='" + encryptSn + "'");
		ret.append("productionDate='" + productionDate + "'");
		ret.append("simImsi='" + simImsi + "'");
		ret.append("productSn='" + productSn + "'");
		ret.append("boardSn='" + boardSn + "'");
		ret.append("testTime='" + testTime + "'");
		ret.append("activeTime='" + activeTime + "'");
		ret.append("cdActiveStatus='" + cdActiveStatus + "'");
		ret.append("cdTestStatus='" + cdTestStatus + "'");
		ret.append("creator='" + creator + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}

}