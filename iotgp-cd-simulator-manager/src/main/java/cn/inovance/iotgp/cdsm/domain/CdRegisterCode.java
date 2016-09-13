package cn.inovance.iotgp.cdsm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import cn.inovance.iotgp.cdsm.annotation.FieldChineseName;

@Entity(name = "t_cds_cd_register_code")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CdRegisterCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.cdsm.domain.generator.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@FieldChineseName(name = "编号")
	private String id;

	@Column(name = "reg_code")
	@FieldChineseName(name = "注册码")
	private String regCode;

	@Column(name = "mac")
	@FieldChineseName(name = "mac地址")
	private String mac;

	@Column(name = "random_code")
	@FieldChineseName(name = "随机码")
	private String randomCode;

	@Column(name = "encrypt_sn")
	@FieldChineseName(name = "加密序列号")
	private String encryptSn;

	@Column(name = "encrypt_key")
	@FieldChineseName(name = "加密key")
	private String encryptKey = "30012466300124663001246630012466";

	@Column(name = "production_date")
	@FieldChineseName(name = "生产日期 ")
	private Date productionDate;

	@Column(name = "sim_imsi")
	@FieldChineseName(name = "IMSI号")
	private String simImsi;

	@Column(name = "board_sn")
	@FieldChineseName(name = "单板sn")
	private String boardSn;

	@Column(name = "product_sn")
	@FieldChineseName(name = "生产流水号")
	private String productSn;

	@Column(name = "product_model")
	@FieldChineseName(name = "产品型号")
	private String productModel;

	@Column(name = "test_time")
	@FieldChineseName(name = "生产测试时间")
	private Date testTime;

	@Column(name = "active_time")
	@FieldChineseName(name = "激活时间")
	private Date activeTime;

	@Column(name = "test_status")
	@FieldChineseName(name = "设备测试状态")
	private String cdTestStatus;

	@Column(name = "active_status")
	@FieldChineseName(name = "设备激活状态")
	private String cdActiveStatus;

	@Column(name = "customer_code")
	@FieldChineseName(name = "所属客户编码")
	private String customerCode;

	@Column(name = "customer_name")
	@FieldChineseName(name = "所属客户名称")
	private String customerName;

	@Column(name = "creator")
	@FieldChineseName(name = "创建人员")
	private String creator;

	@Column(name = "creator_name")
	@FieldChineseName(name = "创建人员名称")
	private String creatorName;

	@Column(name = "create_time")
	@FieldChineseName(name = "创建时间")
	private Date createTime;

	@Column(name = "update_time")
	@FieldChineseName(name = "更新时间")
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public String getEncryptSn() {
		return encryptSn;
	}

	public void setEncryptSn(String encryptSn) {
		this.encryptSn = encryptSn;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getSimImsi() {
		return simImsi;
	}

	public void setSimImsi(String simImsi) {
		this.simImsi = simImsi;
	}

	public String getBoardSn() {
		return boardSn;
	}

	public void setBoardSn(String boardSn) {
		this.boardSn = boardSn;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public String getCdTestStatus() {
		return cdTestStatus;
	}

	public void setCdTestStatus(String cdTestStatus) {
		this.cdTestStatus = cdTestStatus;
	}

	public String getCdActiveStatus() {
		return cdActiveStatus;
	}

	public void setCdActiveStatus(String cdActiveStatus) {
		this.cdActiveStatus = cdActiveStatus;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public CdRegisterCode() {
	}

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("cn.inovance.iotgp.cdsm.domain.CdRegisterCode: ");
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
