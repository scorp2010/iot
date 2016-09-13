/***********************************************************************
 * Module:  CollectDevice.java
 * Author:  w1898
 * Purpose: Defines the Class CollectDevice
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集设备
 *
 */
@Entity(name = "CollectDevice")
@Table(name = "t_mms_collect_device", indexes = {
		@Index(name = "cd_reg_code_index", columnList = "cd_reg_code"),
		@Index(name = "cd_online_stauts_index", columnList = "online_stauts"),
		@Index(name = "owner_td_controller_update_scope_index", columnList = "owner_td_controller_update_scope_id"),
		@Index(name = "owner_td_controller_name_index", columnList = "owner_td_controller_name"),
		@Index(name = "idx_dhscode", columnList = "dhs_code,update_time") })
public class CollectDevice implements java.io.Serializable {
	private static final long serialVersionUID = -2136751489452465419L;
	/** 编号，uuid */
	private java.lang.String id;
	/** 设备注册码 */
	private java.lang.String cdRegCode;
	/** 最后登录时间 */
	private java.util.Date lastLoginTime;
	/** 最后离线时间 */
	private java.util.Date lastLogoutTime;
	/** 注册时间 */
	private java.util.Date regTime;
	/** 所属客户 */
	private java.lang.String customerCode;
	/*** 所属客户名称 */
	private java.lang.String customerName;
	/** 在线状态 1 正常 2 离线 */
	private java.lang.String onlineStauts;
	/** 在线状态名称 1.正常 2.离线3.失败 */
	private java.lang.String onlineStautsName;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 登录cdag编码 */
	private java.lang.String subSysCode;
	/** 起始注册时间 */
	private java.lang.String startRegDate;
	/** 截至注册时间 */
	private java.lang.String endRegDate;
	/** 采集设备字段：业务类型 */
	private String businessType;
	/** 文件传输类型 */
	private Short transferType = 0;
	private String businessTypeName;
	/** 采集设备下目标设备所属控制器 */
	private String ownerTargetDeviceControllerName;
	/** 采集设备下目标设备所属控制器范围 */
	private String ownerTargetDeviceControllerUpdateScope;
	private Boolean checked;
	private String searchMode;
	public final static String SEARCH_MODE_TD_CONTROLLER_SCOPE = "1";
	/** Y和N */
	private String selectAll;
	public final static String SELECT_ALL_NO = "N";
	public final static String SELECT_ALL_YES = "Y";
	private String cdIds;
	private String fileUrl;
	/**登录响应消息*/
	private String loginMsg;
	/**
	 * 生产流水号
	 */
	private String productSn;
	/** 登录信息ip和port */
	private String loginIpPort;
	/** 设备模式,现有情况下1.中继2主机 */
	private Integer mode;
	/**物料，型号*/
	private String materiel;
	/** 附属的主机等其他采集设备.注册码之间用分号;分割 */
	private String relateCdRegCodes;
	/** 参数 */
	private String configParameter;
	/** 最后更新时间 */
	private Date updateTime;
	/** 更新类型 */
	private Short updateType;
	/** 业务系统编码 */
	public String dhsCode;
	/**重定向标记*/
	public Integer relocateFlag;
	/**设备IP*/
	public String cdHost;
	
	/**
	 * 指令名称
	 */
	private String commandName;
	/**
	 * 指令模板
	 */
	private String commandTemplet;
	
	/**
	 * 指令参数
	 */
	private String commandPara;
	
	/**
	 * 指令校验方式
	 */
	private String commandCheckType;
	
	/**
	 * 指令数据格式
	 */
	private String commandDataFormat;
	
	/**
	 * 指令响应数据长度
	 */
	private int cmdRspDataLength;
	

	private String sim;
	
	/**
	 * MAC地址
	 */
	private String mac;
	
	/**
	 * 新增查询条件
	 * 软件版本(softwareVersion)、软件最新版本(softwareVersionMax)、更新状态(updateResultName)、软件类别(softwareCategory,1--用户程序,2--单板)
	 * @return
	 */
	private String softwareVersion;
	
	private String softwareName;
	
	private String softwareVersionMax;
	
	private String updateResultName;
	
	
	private String softwareCategory;
	
	@Transient
	public String getSoftwareCategory() {
		return softwareCategory;
	}


	public void setSoftwareCategory(String softwareCategory) {
		this.softwareCategory = softwareCategory;
	}
	
	@Transient
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	
	@Transient
	public String getSoftwareName() {
		return softwareName;
	}


	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}


	@Transient
	public String getSoftwareVersionMax() {
		return softwareVersionMax;
	}

	
	public void setSoftwareVersionMax(String softwareVersionMax) {
		this.softwareVersionMax = softwareVersionMax;
	}

	@Transient
	public String getUpdateResultName() {
		return updateResultName;
	}


	public void setUpdateResultName(String updateResultName) {
		this.updateResultName = updateResultName;
	}
	
	@Basic(optional = true)
	@Column(name = "sim", insertable = true, length = 36)
	public String getSim() {
		return sim;
	}


	public void setSim(String sim) {
		this.sim = sim;
	}

	@Transient
	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}
	
	
	@Transient
	public String getCommandName() {
		return commandName;
	}

	
	@Transient
	public String getCommandPara() {
		return commandPara;
	}


	/**
	 * @param commandPara the commandPara to set
	 */
	public void setCommandPara(String commandPara) {
		this.commandPara = commandPara;
	}


	@Transient
	public String getCommandCheckType() {
		return commandCheckType;
	}

	@Transient
	public int getCmdRspDataLength() {
		return cmdRspDataLength;
	}


	public void setCmdRspDataLength(int cmdRspDataLength) {
		this.cmdRspDataLength = cmdRspDataLength;
	}

	/**
	 * @param commandCheckType the commandCheckType to set
	 */
	public void setCommandCheckType(String commandCheckType) {
		this.commandCheckType = commandCheckType;
	}


	@Transient
	public String getCommandDataFormat() {
		return commandDataFormat;
	}


	/**
	 * @param commandDataFormat the commandDataFormat to set
	 */
	public void setCommandDataFormat(String commandDataFormat) {
		this.commandDataFormat = commandDataFormat;
	}


	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	@Transient
	public String getCommandTemplet() {
		return commandTemplet;
	}

	
	public void setCommandTemplet(String commandTemplet) {
		this.commandTemplet = commandTemplet;
	}

	public java.util.Set<CdSoftwareInfo> cdSoftWareInfoList = new HashSet<CdSoftwareInfo>(
			0);

	public CollectDevice() {
	}

	@OneToMany(mappedBy = "collectDevice", fetch = FetchType.LAZY)
	public java.util.Set<CdSoftwareInfo> getCdSoftWareInfoList() {
		if (cdSoftWareInfoList == null)
			cdSoftWareInfoList = new java.util.HashSet<CdSoftwareInfo>();
		return cdSoftWareInfoList;
	}

	public void setCdSoftWareInfoList(
			java.util.Set<CdSoftwareInfo> newCdSoftWareInfoList) {
		this.cdSoftWareInfoList = newCdSoftWareInfoList;
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(java.lang.String newRegCode) {
		this.cdRegCode = newRegCode;
	}
	@Basic(optional = true)
	@Column(name = "mode", insertable = true, length = 5)
	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}
	@Basic(optional = true)
	@Column(name = "relate_cd_reg_codes", insertable = true, length = 100)
	public String getRelateCdRegCodes() {
		return relateCdRegCodes;
	}

	public void setRelateCdRegCodes(String relateCdRegCodes) {
		this.relateCdRegCodes = relateCdRegCodes;
	}
	@Basic(optional = true)
	@Column(name = "dhs_code", insertable = true, length = 50)
	public String getDhsCode() {
		return dhsCode;
	}

	public void setDhsCode(String dhsCode) {
		this.dhsCode = dhsCode;
	}


	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getLastLoginTime() {

		return this.lastLoginTime;
	}

	public void setLastLoginTime(java.util.Date newLastLoginTime) {
		this.lastLoginTime = newLastLoginTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_logout_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getLastLogoutTime() {
		return lastLogoutTime;
	}

	public void setLastLogoutTime(java.util.Date lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(java.util.Date newRegTime) {
		this.regTime = newRegTime;
	}

	@Basic(optional = true)
	@Column(name = "customer_code", insertable = true, length = 100)
	public java.lang.String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(java.lang.String customerCode) {
		this.customerCode = customerCode;
	}

	@Basic(optional = true)
	@Column(name = "customer_name", insertable = true, length = 100)
	public java.lang.String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(java.lang.String newCustomerName) {
		this.customerName = newCustomerName;
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
	@Column(name = "owner_td_controller_update_scope_id", insertable = true, length = 36)
	public String getOwnerTargetDeviceControllerUpdateScope() {
		return ownerTargetDeviceControllerUpdateScope;
	}

	public void setOwnerTargetDeviceControllerUpdateScope(
			String ownerTargetDeviceControllerUpdateScope) {
		this.ownerTargetDeviceControllerUpdateScope = ownerTargetDeviceControllerUpdateScope;
	}

	@Basic(optional = true)
	@Column(name = "online_stauts", insertable = true, updatable = true, length = 10)
	public java.lang.String getOnlineStauts() {
		return onlineStauts;
	}
	public void setOnlineStauts(java.lang.String newOnlineStauts) {
		this.onlineStauts = newOnlineStauts;
	}
	@Transient
	public java.lang.String getOnlineStautsName() {
		return onlineStautsName;
	}


	public void setOnlineStautsName(java.lang.String newOnlineStautsName) {
		this.onlineStautsName = newOnlineStautsName;
	}
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateTime() {

		return this.createTime;

	}
	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}
	@Basic(optional = true)
	@Column(name = "sub_sys_code", insertable = true, updatable = true, length = 50)
	public java.lang.String getSubSysCode() {
		return subSysCode;
	}

	public void setSubSysCode(java.lang.String subSysCode) {
		this.subSysCode = subSysCode;
	}
	
	@Transient
	public java.lang.String getStartRegDate() {
		return startRegDate;
	}

	public void setStartRegDate(java.lang.String startRegDate) {
		this.startRegDate = startRegDate;
	}

	@Transient
	public java.lang.String getEndRegDate() {
		return endRegDate;
	}

	public void setEndRegDate(java.lang.String endRegDate) {
		this.endRegDate = endRegDate;
	}

	@Basic(optional = true)
	@Column(name = "business_type", insertable = true, updatable = true, length = 30)
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Basic(optional = true)
	@Column(name = "transfer_type", insertable = true, updatable = true)
	public Short getTransferType() {
		return transferType;
	}

	public void setTransferType(Short transferType) {
		this.transferType = transferType == null ? 0 : transferType;
	}

	@Basic(optional = true)
	@Column(name = "login_ip_port", insertable = true, length = 50)
	public String getLoginIpPort() {
		return loginIpPort;
	}

	public void setLoginIpPort(String loginIpPort) {
		this.loginIpPort = loginIpPort;
	}

	@Basic(optional = true)
	@Column(name = "config_parameters", insertable = true, length = 2048)
	public String getConfigParameter() {
		return configParameter;
	}

	public void setConfigParameter(String configParameter) {
		this.configParameter = configParameter;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Basic(optional = true)
	@Column(name = "update_type", insertable = true, length = 10)
	public Short getUpdateType() {
		return updateType;
	}
	@Basic(optional = true)
	@Column(name = "materiel", insertable = true, length = 20)
	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	
	@Basic(optional = true)
	@Column(name = "relocate_flag", insertable = true, length = 1)
	public Integer getRelocateFlag() {
		return relocateFlag;
	}

	public void setRelocateFlag(Integer relocateFlag) {
		this.relocateFlag = relocateFlag;
	}
	
	@Basic(optional = true)
	@Column(name = "cd_host", insertable = true, length = 50)
	public String getCdHost() {
		return cdHost;
	}

	public void setCdHost(String cdHost) {
		this.cdHost = cdHost;
	}

	@Basic(optional = true)
	@Column(name = "login_msg", insertable = true, length = 255)
	public String getLoginMsg() {
		return loginMsg;
	}


	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}


	public void setUpdateType(Short updateType) {
		this.updateType = updateType;
	}

	@Transient
	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	@Transient
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Transient
	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
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
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Transient
	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectDevice other = (CollectDevice) obj;
		if (businessType == null) {
			if (other.businessType != null)
				return false;
		} else if (!businessType.equals(other.businessType))
			return false;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (cdSoftWareInfoList == null) {
			if (other.cdSoftWareInfoList != null)
				return false;
		} else if (!cdSoftWareInfoList.equals(other.cdSoftWareInfoList))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;

		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (endRegDate == null) {
			if (other.endRegDate != null)
				return false;
		} else if (!endRegDate.equals(other.endRegDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastLoginTime == null) {
			if (other.lastLoginTime != null)
				return false;
		} else if (!lastLoginTime.equals(other.lastLoginTime))
			return false;

		if (onlineStauts == null) {
			if (other.onlineStauts != null)
				return false;
		} else if (!onlineStauts.equals(other.onlineStauts))
			return false;
		if (onlineStautsName == null) {
			if (other.onlineStautsName != null)
				return false;
		} else if (!onlineStautsName.equals(other.onlineStautsName))
			return false;
		if (regTime == null) {
			if (other.regTime != null)
				return false;
		} else if (!regTime.equals(other.regTime))
			return false;
		if (startRegDate == null) {
			if (other.startRegDate != null)
				return false;
		} else if (!startRegDate.equals(other.startRegDate))
			return false;
		if (subSysCode == null) {
			if (other.subSysCode != null)
				return false;
		} else if (!subSysCode.equals(other.subSysCode))
			return false;
		if (transferType != other.transferType)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessType == null) ? 0 : businessType.hashCode());
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime
				* result
				+ ((cdSoftWareInfoList == null) ? 0 : cdSoftWareInfoList
						.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());

		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((endRegDate == null) ? 0 : endRegDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastLoginTime == null) ? 0 : lastLoginTime.hashCode());

		result = prime * result
				+ ((onlineStauts == null) ? 0 : onlineStauts.hashCode());
		result = prime
				* result
				+ ((onlineStautsName == null) ? 0 : onlineStautsName.hashCode());
		result = prime * result + ((regTime == null) ? 0 : regTime.hashCode());
		result = prime * result
				+ ((startRegDate == null) ? 0 : startRegDate.hashCode());
		result = prime * result
				+ ((subSysCode == null) ? 0 : subSysCode.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "CollectDevice [id=" + id + ", cdRegCode=" + cdRegCode
				+ ", lastLoginTime=" + lastLoginTime + ", regTime=" + regTime
				+ ", onlineStauts=" + onlineStauts + ", onlineStautsName="
				+ onlineStautsName 
				+ ", createTime=" + createTime + ", subSysCode=" + subSysCode
				+ ", startRegDate=" + startRegDate + ", endRegDate="
				+ endRegDate + ", businessType=" + businessType
				+ ", cdSoftWareInfoList=" + cdSoftWareInfoList + "]";
	}



}