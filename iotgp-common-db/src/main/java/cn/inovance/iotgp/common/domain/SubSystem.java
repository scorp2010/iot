/***********************************************************************
 * Module:  SubSystem.java
 * Author:  w1898
 * Purpose: Defines the Class SubSystem
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


/**
 * 子系统
 * 
 */
@Entity(name = "SubSystem")
@Table(name = "t_cds_sub_system",indexes ={ @Index(name="sys_code_index",columnList= "code")  })
@DynamicInsert(true)
@DynamicUpdate(true)
public class SubSystem implements java.io.Serializable {

	private static final long serialVersionUID = 4725843586356720767L;
	/** 编号 */
	private String id;
	/** 子系统编码 */
	private String code;
	/** 子系统名称 */
	private String name;
	/** 子系统类型 cdag，fms等 */
	private String type;
	private String typeName;
	/** 平台.
	 * 1.通用平台.
	 * 2.专用平台.
	 * */ 
	private String platForm;
	private String platFormName;
	

	/** 鉴权密码 */
	private String password;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** 离线时间 */
	private Date offlineTime;
	/** 在线状态 */
	private String onlineStatus;
	private String onlineStatusName;

	/** 描述信息 */
	private String describe;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 服务IP */
	private String internalHost;
	/** 服务IP类型：APN,WAN,LAN */
	private String internalHostType;
	/** 服务端口 */
	private Integer internalPort;

	private String externalHost;
	/** 服务IP类型：APN,WAN,LAN */
	private String externalHostType;
	/** 服务端口 */
	private Integer externalPort;
	
	private Set<SubSysConfig> subSysConfigList = new HashSet<SubSysConfig>(0);;

	private SysDomain sysDomain;

	private String sdate;
	private String edate;
	private String key;
	private String value;
	/** 备用字段 ,可作为RDTSip地址等. */
	private String reserve;
	/***/
	private Date lastHeartbeatTime;
	/***/
	private Integer sessionCount;
	
	private String cluster;
	private String subSysProperties;
	
	public SubSystem() {
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subSystem")
	public Set<SubSysConfig> getSubSysConfigList() {
		return subSysConfigList;
	}

	public void setSubSysConfigList(Set<SubSysConfig> newSubSysConfigList) {
		this.subSysConfigList = newSubSysConfigList;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "t_cds_sys_domain_id_fk")
	public SysDomain getSysDomain() {
		return sysDomain;
	}

	public void setSysDomain(SysDomain newSysDomain) {
		this.sysDomain = newSysDomain;
	}
	@Column(name = "platform",columnDefinition="varchar(10) default 'platform'")
	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}
	/*@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)*/
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		/*if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUIDGenerator.generate();*/
		return this.id;
	}

	public void setId(String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "code", insertable = true, updatable = true, length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String newCode) {
		this.code = newCode;
	}

	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	@Basic
	@Column(name = "subsys_type", insertable = true, updatable = true, length = 20)
	public String getType() {
		return type;
	}

	public void setType(String newType) {
		this.type = newType;
	}
	@Transient
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Basic(optional = true)
	@Column(name = "password", insertable = true, updatable = true, length = 50)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time", insertable = true, updatable = true, length = 7)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date newLastLoginTime) {
		this.lastLoginTime = newLastLoginTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "offline_time", insertable = true, updatable = true, length = 7)
	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date newOfflineTime) {
		this.offlineTime = newOfflineTime;
	}

	@Column(name = "online_status", insertable = true, updatable = true, length = 5)
	public String  getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String newOnlineStatus) {
		this.onlineStatus = newOnlineStatus;
	}

	@Transient
	public String getOnlineStatusName() {
		return onlineStatusName;
	}

	public void setOnlineStatusName(String onlineStatusName) {
		this.onlineStatusName = onlineStatusName;
	}
	@Basic(optional = true)
	@Column(name = "sub_sys_desc", insertable = true, updatable = true, length = 100)
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String newDescribe) {
		this.describe = newDescribe;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreateTime() {
		if(createTime != null ){
			return createTime;
		}else {
			return new Date();
		}
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public Date getUpdateTime() {
		if(updateTime != null){
			return updateTime;
		}else{
			return new Date();
		}
	}

	public void setUpdateTime(Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	@Basic(optional = true)
	@Column(name = "internal_host", insertable = true, updatable = true, length = 50)
	public String getInternalHost() {
		return internalHost;
	}

	public void setInternalHost(String internalHost) {
		this.internalHost = internalHost;
	}

	@Basic(optional = true)
	@Column(name = "internal_host_type", insertable = true, updatable = true, length = 20)
	public String getInternalHostType() {
		return internalHostType;
	}

	public void setInternalHostType(String internalHostType) {
		this.internalHostType = internalHostType;
	}

	@Basic(optional = true)
	@Column(name = "internal_port", insertable = true, updatable = true, length = 5)
	public Integer getInternalPort() {
		return internalPort;
	}

	public void setInternalPort(Integer internalPort) {
		this.internalPort = internalPort;
	}
	
	@Basic(optional = true)
	@Column(name = "external_host", insertable = true, updatable = true, length = 50)
	public String getExternalHost() {
		return externalHost;
	}

	public void setExternalHost(String externalHost) {
		this.externalHost = externalHost;
	}

	@Basic(optional = true)
	@Column(name = "external_host_type", insertable = true, updatable = true, length = 20)
	public String getExternalHostType() {
		return externalHostType;
	}

	public void setExternalHostType(String externalHostType) {
		this.externalHostType = externalHostType;
	}
	@Basic(optional = true)
	@Column(name = "external_port", insertable = true, updatable = true, length = 5)
	public Integer getExternalPort() {
		return externalPort;
	}

	public void setExternalPort(Integer externalPort) {
		this.externalPort = externalPort;
	}

	@Basic(optional = true)
	@Column(name = "reserve", insertable = true, updatable = true, length = 50)
	public String getReserve(){
		return this.reserve;
	}
	public void setReserve(String newReserve){
		this.reserve = newReserve;
	}
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_heartbeat_time", length = 7)
	public Date getLastHeartbeatTime() {
		return lastHeartbeatTime;
	}

	public void setLastHeartbeatTime(Date lastHeartbeatTime) {
		this.lastHeartbeatTime = lastHeartbeatTime;
	}
	
	@Basic(optional = true)
	@Column(name = "session_count", insertable = true, updatable = true, length = 5)
	public Integer getSessionCount() {
		return sessionCount;
	}

	public void setSessionCount(Integer sessionCount) {
		this.sessionCount = sessionCount;
	}

	@Basic(optional = true)
	@Column(name = "cluster", insertable = true, updatable = true, length = 50)
	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	@Basic(optional = true)
	@Column(name = "properties", insertable = true, updatable = true, length = 500)
	public String getSubSysProperties() {
		return subSysProperties;
	}

	public void setSubSysProperties(String subSysProperties) {
		this.subSysProperties = subSysProperties;
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
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Transient
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Transient
	public String getPlatFormName() {
		return platFormName;
	}

	public void setPlatFormName(String platFormName) {
		this.platFormName = platFormName;
	}
	
}