/***********************************************************************
 * Module:  CdOnlineLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdOnlineLog
 ***********************************************************************/

package cn.inovance.iotgp.common.nopersistence.domain;

import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * 采集设备在线日志
 * 
 * @pdOid c6612a2b-274b-4ec6-8a00-971872b861f4
 */
/* 
 * @Entity(name = "CdOnlineLog")
   @Table(name = "t_mms_cd_online_log",indexes ={ @Index(name="t_mms_cd_online_index",columnList= "cd_reg_code")  })
*/
public class CdOnlineLog implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid f386beee-3081-4947-becb-b41936e8434f
	 */
	private java.lang.Long id;
	/**
	 * 采集设备注册码
	 * 
	 * @pdOid f8779a44-c0a4-4cf7-b989-a750c5f8b003
	 */
	private java.lang.String cdRegCode;
	/**
	 * 日志类型
	 * 
	 * @pdOid 1505a030-9b45-49a8-8796-51c65b339e7d
	 */
	private java.lang.String logType;

	/**
	 * 日志类型名称
	 * 
	 * @pdOid 1505a030-9b45-49a8-8796-51c65b339e7d
	 */
	private java.lang.String logTypeName;
	/**
	 * 日志时间
	 * 
	 * @pdOid 5a9faa8f-9106-48ab-9a03-4527940c191e
	 */
	private java.lang.Long logTime;
	/**
	 * 采集设备IP地址
	 * 
	 * @pdOid 73241153-ec20-4d38-ac94-59b8e91ee1fc
	 */
	private java.lang.String cdIp;
	/**
	 * 国际移动用户识别码
	 * 
	 * @pdOid c41a452f-eccd-4504-a0ee-419ae19676d9
	 */
	private java.lang.String simImsi;
	/**
	 * 接入服务器IP地址
	 * 
	 * @pdOid 31246a0a-daca-4d8a-8cc6-ab65bad3d9e0
	 */
	private java.lang.String cdagIp;
	/**
	 * 接入服务器端口
	 * 
	 * @pdOid 8ed951e9-fb47-4177-adfa-13e651a25005
	 */
	private java.lang.Integer cdagPort;
	/** @pdOid e37df7ad-9da7-4691-a681-05a6c1453e99 */
	private java.util.Date logShowTime;

	/** 查询字段 */
	private java.lang.String startLogTime;

	/** 查询字段 */
	private java.lang.String endLogTime;
	
    /** mongodb id */
	private Oid _id;
	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public CdOnlineLog() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	/*@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)*/
	public java.lang.Long getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.Long newId) {
		this.id = newId;
	}

	/**
	 * Get value of cdRegCode
	 * 
	 * @return cdRegCode
	 */
	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}

	/**
	 * Set value of cdRegCode
	 * 
	 * @param newCdRegCode
	 */
	public void setCdRegCode(java.lang.String newCdRegCode) {
		this.cdRegCode = newCdRegCode;
	}

	/**
	 * Get value of logType
	 * 
	 * @return logType
	 */
	@Basic(optional = true)
	@Column(name = "log_type", insertable = true, updatable = true, length = 10)
	public java.lang.String getLogType() {
		return logType;
	}

	/**
	 * Set value of logType
	 * 
	 * @param newLogType
	 */
	public void setLogType(java.lang.String newLogType) {
		this.logType = newLogType;
	}

	/**
	 * Set value of logType
	 * 
	 * @param newLogType
	 */
	public void setLogTypeName(java.lang.String newLogTypeName) {
		this.logTypeName = newLogTypeName;
	}

	/**
	 * Get value of logType
	 * 
	 * @return logType
	 */
	@Transient
	public java.lang.String getLogTypeName() {
		return logTypeName;
	}

	/**
	 * Get value of logTime
	 * 
	 * @return logTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "log_time", insertable = true, updatable = true, length = 7)
	public java.lang.Long getLogTime() {
			return this.logTime;
	}

	/**
	 * Set value of logTime
	 * 
	 * @param newLogTime
	 */
	public void setLogTime(java.lang.Long newLogTime) {
		this.logTime = newLogTime;
	}

	/**
	 * Get value of cdIp
	 * 
	 * @return cdIp
	 */
	@Basic(optional = true)
	@Column(name = "cd_ip", insertable = true, updatable = true, length = 30)
	public java.lang.String getCdIp() {
		return cdIp;
	}

	/**
	 * Set value of cdIp
	 * 
	 * @param newCdIp
	 */
	public void setCdIp(java.lang.String newCdIp) {
		this.cdIp = newCdIp;
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
	 * Get value of cdagIp
	 * 
	 * @return cdagIp
	 */
	@Basic(optional = true)
	@Column(name = "cdag_ip", insertable = true, updatable = true, length = 30)
	public java.lang.String getCdagIp() {
		return cdagIp;
	}

	/**
	 * Set value of cdagIp
	 * 
	 * @param newCdagIp
	 */
	public void setCdagIp(java.lang.String newCdagIp) {
		this.cdagIp = newCdagIp;
	}

	/**
	 * Get value of cdagPort
	 * 
	 * @return cdagPort
	 */
	@Basic(optional = true)
	@Column(name = "cdag_port", insertable = true, updatable = true)
	public java.lang.Integer getCdagPort() {
		return cdagPort;
	}

	/**
	 * Set value of cdagPort
	 * 
	 * @param newCdagPort
	 */
	public void setCdagPort(java.lang.Integer newCdagPort) {
		this.cdagPort = newCdagPort;
	}

	/**
	 * Get value of createTime
	 * 
	 * @return createTime
	 */
	public java.util.Date getLogShowTime() {
		return this.logShowTime;

	}

	/**
	 * Set value of createTime
	 * 
	 * @param newCreateTime
	 */
	public void setLogShowTime(java.util.Date logShowTime) {
		this.logShowTime = logShowTime;
	}

	@Transient
	public java.lang.String getStartLogTime() {
		return startLogTime;
	}

	public void setStartLogTime(java.lang.String startLogTime) {
		this.startLogTime = startLogTime;
	}

	@Transient
	public java.lang.String getEndLogTime() {
		return endLogTime;
	}

	public void setEndLogTime(java.lang.String endLogTime) {
		this.endLogTime = endLogTime;
	}

	/**
	 * Mongodb会自动生成ObjectId
	 * @author fhp
	 *
	 */
	public class Oid{
		String $oid;
		public String get$oid() {
			return $oid;
		}
 
		public void set$oid(String $oid) {
			this.$oid = $oid;
		}
		
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

		if (!(other instanceof CdOnlineLog))
			return false;

		CdOnlineLog cast = (CdOnlineLog) other;

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
		if (this.cdRegCode != null)
			hashCode = 29 * hashCode + cdRegCode.hashCode();
		if (this.logType != null)
			hashCode = 29 * hashCode + logType.hashCode();
		if (this.logTime != null)
			hashCode = 29 * hashCode + logTime.hashCode();
		if (this.cdIp != null)
			hashCode = 29 * hashCode + cdIp.hashCode();
		if (this.simImsi != null)
			hashCode = 29 * hashCode + simImsi.hashCode();
		if (this.cdagIp != null)
			hashCode = 29 * hashCode + cdagIp.hashCode();
		if (this.cdagPort != null)
			hashCode = 29 * hashCode + cdagPort.hashCode();
	
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
		ret.append("cn.inovance.iotgp.common.domain.CdOnlineLog: ");
		ret.append("id='" + id + "'");
		ret.append("cdRegCode='" + cdRegCode + "'");
		ret.append("logType='" + logType + "'");
		ret.append("logTime='" + logTime + "'");
		ret.append("cdIp='" + cdIp + "'");
		ret.append("simImsi='" + simImsi + "'");
		ret.append("cdagIp='" + cdagIp + "'");
		ret.append("cdagPort='" + cdagPort + "'");
		return ret.toString();
	}

}