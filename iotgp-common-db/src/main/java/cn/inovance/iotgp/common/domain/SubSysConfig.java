/***********************************************************************
 * Module:  SubSysConfig.java
 * Author:  w1898
 * Purpose: Defines the Class SubSysConfig
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

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

import org.hibernate.annotations.GenericGenerator;

/**
 * 子系统配置
 * 
 */
@Entity(name = "SubSysConfig")
@Table(name = "t_cds_sub_sys_config",indexes ={ @Index(name="sub_sys_config_index",columnList= "sub_sys_code")})
public class SubSysConfig implements java.io.Serializable {

	private static final long serialVersionUID = -4360260604262801504L;
	/** 编号 */
	private String id;
	/** 子系统编码 */
	private String subSysCode;
	/** 对外服务IP地址 */
	private String externalHost;
	/** 对外服务端口 */
	private Integer externalPort;
	/** 对外服务地址 */
	private String internalHost;
	/** 对内服务端口 */
	private Integer internalPort;
	/** 端口协议 0 TCP 1 UDP */
	private Short portType;
	/** 配置参数 external_ip; external_port; innner_ip; innner_port; */
	private String parameters;
	/** 配置版本 */
	private Integer configVersion;
	/** 更新时间 */
	private java.util.Date updateTime;
	/** 创建时间 */
	private java.util.Date createTime;

	private SubSystem subSystem;

	public SubSysConfig() {
	}

	@ManyToOne
	@JoinColumn(name = "t_cds_sub_system_id_fk", referencedColumnName = "id", nullable = true)
	public SubSystem getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(SubSystem newSubSystem) {
		this.subSystem = newSubSystem;
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "sub_sys_code", insertable = true, updatable = true, length = 50)
	public String getSubSysCode() {
		return subSysCode;
	}

	public void setSubSysCode(String newSubSysCode) {
		this.subSysCode = newSubSysCode;
	}

	@Basic(optional = true)
	@Column(name = "external_host", insertable = true, updatable = true, length = 30)
	public String getExternalHost() {
		return externalHost;
	}

	public void setExternalHost(String newExternalHost) {
		this.externalHost = newExternalHost;
	}

	@Basic(optional = true)
	@Column(name = "external_port", insertable = true, updatable = true)
	public Integer getExternalPort() {
		return externalPort;
	}

	public void setExternalPort(Integer newExternalPort) {
		this.externalPort = newExternalPort;
	}

	@Basic(optional = true)
	@Column(name = "internal_host", insertable = true, updatable = true, length = 30)
	public String getInternalHost() {
		return internalHost;
	}

	public void setInternalHost(String newInternalHost) {
		this.internalHost = newInternalHost;
	}

	@Basic(optional = true)
	@Column(name = "internal_port", insertable = true, updatable = true)
	public Integer getInternalPort() {
		return internalPort;
	}

	public void setInternalPort(Integer newInternalPort) {
		this.internalPort = newInternalPort;
	}

	@Basic(optional = true)
	@Column(name = "port_type", insertable = true, updatable = true)
	public Short getPortType() {
		return portType;
	}

	public void setPortType(Short newPortType) {
		this.portType = newPortType;
	}

	@Basic(optional = true)
	@Column(name = "parameters", insertable = true, updatable = true, length = 2048)
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String newParameters) {
		this.parameters = newParameters;
	}

	@Basic(optional = true)
	@Column(name = "config_version", insertable = true, updatable = true)
	public Integer getConfigVersion() {
		return configVersion;
	}

	public void setConfigVersion(Integer newConfigVersion) {
		this.configVersion = newConfigVersion;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

}