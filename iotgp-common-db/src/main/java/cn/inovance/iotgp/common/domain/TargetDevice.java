/***********************************************************************
 * Module:  TargetDevice.java
 * Author:  w1898
 * Purpose: Defines the Class TargetDevice
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * 目标设备
 * 
 */
@Entity(name = "TargetDevice")
@Table(name = "t_mms_target_device",indexes ={ @Index(name="cd_reg_code_index",columnList= "cd_reg_code,td_type_code"),
		@Index(name="idx_dhs_code",columnList= "dhs_code,update_time") })
public class TargetDevice implements java.io.Serializable {

	private static final long serialVersionUID = -7139053614972298999L;
	/** 编号 */
	private String id;
	/** 设备注册码 */
	private String cdRegCode;
	/** 目标设备在采集设备中的序号 */
	private Integer tdSeq;
	/** 目标设备名称 */
	private String name;
	/** 地址码 */
	private java.lang.Integer addressCode;
	/** 目标设备配置信息版本 */
	private String configInfoVersion;
	/** 通信状态 1在线 .2离线  */
	private String cdCommunitonStatus;
	/** 通信状态 */
	private String cdCommunitonStatusName;
	
	/** 目标设备类型编码 */
	private Integer tdTypeCode;
	private String tdTypeCodeName;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 更新类型 */
	private Short updateType;
	
	private String sdate;
	private String edate;
	//public CollectDevice collectDevice;

	public TdControllerType tdControllerType;
	/** TdControllerType.typeName*/
	private String typeName;
	/**用户程序软件名称  它是数据协议版本使用的软件  */
	private String userSoftWareName;
	/** 当前软件版本 */
	private String curSoftWareVersion;
	/** 最新软件版本 */
	private String newSoftWareVersion;
	private String fileId;
	/** 所属业务系统，和CollectDevice表该字段冗余*/
	private String dhsCode;
	/** 所属客户编码，和CollectDevice表该字段冗余*/
	private String customerCode;
	public TargetDevice() {
	}

	public TargetDevice(String id){
		this.id=id;
	}
	
	/*@ManyToOne
	@JoinColumn(name = "collect_device_id_fk", referencedColumnName = "id", nullable = true)
	public CollectDevice getCollectDevice() {
		return collectDevice;
	}

	public void setCollectDevice(CollectDevice newCollectDevice) {
		this.collectDevice = newCollectDevice;
	}*/

	@ManyToOne
	@JoinColumn(name = "td_type_controller_id_fk", referencedColumnName = "id", nullable = true)
	public TdControllerType getTdControllerType() {
		return tdControllerType;
	}

	public void setTdControllerType(TdControllerType newTdControllerType) {
		this.tdControllerType = newTdControllerType;
	}
	
	

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(String newCdRegCode) {
		this.cdRegCode = newCdRegCode;
	}

	@Basic(optional = true)
	@Column(name = "td_seq", insertable = true, updatable = true)
	public Integer getTdSeq() {
		return tdSeq;
	}

	public void setTdSeq(Integer newTdSeq) {
		this.tdSeq = newTdSeq;
	}

	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	@Basic(optional = true)
	@Column(name = "address_code", insertable = true, updatable = true)
	public java.lang.Integer getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(java.lang.Integer newAddressCode) {
		this.addressCode = newAddressCode;
	}

	@Basic(optional = true)
	@Column(name = "config_info_version", insertable = true, updatable = true, length = 20)
	public String getConfigInfoVersion() {
		return configInfoVersion;
	}

	public void setConfigInfoVersion(String newConfigInfoVersion) {
		this.configInfoVersion = newConfigInfoVersion;
	}

	@Basic(optional = true)
	@Column(name = "comm_status", insertable = true, updatable = true, length = 5)
	public String getCdCommunitonStatus() {
		return cdCommunitonStatus;
	}

	public void setCdCommunitonStatus(String newStatus) {
		this.cdCommunitonStatus = newStatus;
	}

	@Basic(optional = true)
	@Column(name = "td_type_code", insertable = true, updatable = true, length = 4)
	public Integer getTdTypeCode() {
		return tdTypeCode;
	}

	public void setTdTypeCode(Integer newTdTypeCode) {
		this.tdTypeCode = newTdTypeCode;
	}

	@Transient
	public String getTdTypeCodeName() {
		return tdTypeCodeName;
	}

	public void setTdTypeCodeName(String tdTypeCodeName) {
		this.tdTypeCodeName = tdTypeCodeName;
	}
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(Date newUpdateTime) {
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
	@Column(name = "software_name", insertable = true, updatable = true, length = 100)
	public String getUserSoftWareName() {
		return userSoftWareName;
	}

	public void setUserSoftWareName(String userSoftWareName) {
		this.userSoftWareName = userSoftWareName;
	}

	@Transient
	public String getCurSoftWareVersion() {
		return curSoftWareVersion;
	}

	public void setCurSoftWareVersion(String curSoftWareVersion) {
		this.curSoftWareVersion = curSoftWareVersion;
	}
	@Transient
	public String getNewSoftWareVersion() {
		return newSoftWareVersion;
	}

	public void setNewSoftWareVersion(String newSoftWareVersion) {
		this.newSoftWareVersion = newSoftWareVersion;
	}

	
	@Transient
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	@Transient
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	@Transient
	public String getCdCommunitonStatusName() {
		return cdCommunitonStatusName;
	}
	public void setCdCommunitonStatusName(String cdCommunitonStatusName) {
		this.cdCommunitonStatusName = cdCommunitonStatusName;
	}
	@Transient
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Transient
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	@Basic(optional = true)
	@Column(name = "dhs_code", insertable = true, updatable = true, length = 50)
	public String getDhsCode() {
		return dhsCode;
	}

	public void setDhsCode(String dhsCode) {
		this.dhsCode = dhsCode;
	}
	@Basic(optional = true)
	@Column(name = "customer_code", insertable = true, updatable = true, length = 100)
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	

}