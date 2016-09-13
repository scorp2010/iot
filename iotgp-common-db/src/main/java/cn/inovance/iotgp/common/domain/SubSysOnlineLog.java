/***********************************************************************
 * Module:  SubSysOnlineLog.java
 * Author:  w1898
 * Purpose: Defines the Class SubSysOnlineLog
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

import org.hibernate.annotations.GenericGenerator;

/**
 * 子系统在线日志
 * 
 */
@Entity(name = "SubSysOnlineLog")
@Table(name = "t_cds_sub_sys_online_log",indexes ={ @Index(name="sub_sys_online_index",columnList= "sub_sys_code")})
public class SubSysOnlineLog implements java.io.Serializable {

	private static final long serialVersionUID = 8633498236816314493L;
	/** 编号 */
	public String id;
	/** 子系统编号 */
	public String subSysId;
	/** 子系统编码 */
	public String subSysCode;
	/** 登录类型 */
	public Short loginType;
	/** 登入时间 */
	public java.util.Date loginTime;
	/** 登录ip地址 */
	public String loginIp;
	/** 登陆端口 */
	public Integer loginPort;

	@Basic(optional = true)
	@Column(name = "login_port", insertable = true, updatable = true)
	public Integer getLoginPort() {
		return loginPort;
	}

	public void setLoginPort(Integer loginPort) {
		this.loginPort = loginPort;
	}

	public SubSysOnlineLog() {
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
	@Column(name = "sub_sys_id", insertable = true, updatable = true, length = 32)
	public String getSubSysId() {
		return subSysId;
	}

	public void setSubSysId(String newSubSysId) {
		this.subSysId = newSubSysId;
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
	@Column(name = "login_type", insertable = true, updatable = true)
	public Short getLoginType() {
		return loginType;
	}

	public void setLoginType(Short newLoginType) {
		this.loginType = newLoginType;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_time", insertable = true, updatable = true)
	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.util.Date newLoginTime) {
		this.loginTime = newLoginTime;
	}

	@Basic(optional = true)
	@Column(name = "login_ip", insertable = true, updatable = true, length = 30)
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String newLoginIp) {
		this.loginIp = newLoginIp;
	}

}