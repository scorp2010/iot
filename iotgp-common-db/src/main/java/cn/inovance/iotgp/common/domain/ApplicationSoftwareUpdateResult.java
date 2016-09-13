/***********************************************************************
 * Module:  ApplicationSoftwareInfo.java
 * Author:  2200
 * Purpose: Defines the Class ApplicationSoftwareInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

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
 * 应用软件任务信息
 * 
 */
@Entity(name = "ApplicationSoftwareUpdateResult")
@Table(name = "t_mms_application_software_update_result",indexes ={ @Index(name="software_name_index",columnList= "name"),@Index(name="software_version_index",columnList= "version")})

public class ApplicationSoftwareUpdateResult implements java.io.Serializable {

	private static final long serialVersionUID = 4320706714110319857L;
	/** 编号 */
	private String id;
	/** 软件名称 */
	private String name;
	/** 软件版本 */
	private String version;
	/** 错误编码 */
	private String updateCode;
	/** 错误原因 */
	private String updateLog;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private Date   createTime;
	
	/** 查询新增字段 */
	private String sdate;
	private String edate;

	public ApplicationSoftwareUpdateResult() {
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
	@Column(name = "name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	@Basic(optional = true)
	@Column(name = "version", insertable = true, updatable = true, length = 50)
	public String getVersion() {
		return version;
	}

	public void setVersion(String newVersion) {
		this.version = newVersion;
	}
	@Basic(optional = true)
	@Column(name = "update_code", insertable = true, updatable = true, length = 50)
	public String getUpdateCode() {
		return updateCode;
	}
	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	@Basic(optional = true)
	@Column(name = "update_log", insertable = true, updatable = true, length = 50)
	public String getUpdateLog() {
		return updateLog;
	}
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}
	@Basic(optional = true)
	@Column(name = "creator", insertable = true, updatable = true, length = 50)
	public String getCreator() {
		return creator;
	}

	public void setCreator(String newCreator) {
		this.creator = newCreator;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((edate == null) ? 0 : edate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((updateCode == null) ? 0 : updateCode.hashCode());
		result = prime * result + ((updateLog == null) ? 0 : updateLog.hashCode());
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationSoftwareUpdateResult other = (ApplicationSoftwareUpdateResult) obj;
		
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (edate == null) {
			if (other.edate != null)
				return false;
		} else if (!edate.equals(other.edate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sdate == null) {
			if (other.sdate != null)
				return false;
		} else if (!sdate.equals(other.sdate))
			return false;
		
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApplicationSoftwareUpdateResult [id=" + id + ", name=" + name + ", version="
				+ version + ", updateCode=" + updateCode
				+ ", updateLog=" + updateLog
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", sdate=" + sdate + ", edate="
				+ edate + "]";
	}

	
}