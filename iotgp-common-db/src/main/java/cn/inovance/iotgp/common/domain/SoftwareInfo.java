/***********************************************************************
 * Module:  SoftwareInfo.java
 * Author:  w1898
 * Purpose: Defines the Class SoftwareInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 软件任务信息
 * 
 */
@Entity(name = "SoftwareInfo")
@Table(name = "t_mms_software_info",indexes ={ @Index(name="software_name_index",columnList= "name"),@Index(name="software_version_index",columnList= "version"),@Index(name="soft_file_md5_index",columnList= "soft_file_md5") })
@DynamicInsert(true)
@DynamicUpdate(true)
public class SoftwareInfo implements java.io.Serializable {

	private static final long serialVersionUID = 4320706714110319857L;
	/** 编号 */
	private String id;
	/** 软件名称 */
	private String name;
	/** 软件版本 */
	private String version;
	/** 软件类型 */
	private String softType;
	private String softTypeName;
	/** 文件名 */
	private String softFileName;
	/** 文件路径 */
	private String softFilePath;
	/** 文件长度 */
	private Long   softFileLength;
	/** 文件md5 */
	private String softFileMd5;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private Date   createTime;
	/** 审批时间 */
	private Date   auditTime;
	/** 审批人 */
	private String auditorId;
	/** 状态 */
	private String status;
	/** 状态 */
	private String softStatus;
	/** 软件描述*/
	private String desc;
	/**
	 * 软件环境状态（0:测试环境，1:测试转正式中，2:生产环境）
	 */
	private String softwareEnvironment;
	private String fileTypeCode;
	
	/**是否作废:Y:是，N：不是*/
	private String isCancel;
	
	/** 作废时间 */
	private Date cancelTime;
	
	/** 作废人 */
	private String cancelAuthor;
	
	/** 查询新增字段 */
	private String sdate;
	private String edate;

	public SoftwareInfo() {
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
	@Column(name = "soft_type", insertable = true, updatable = true, length = 20)
	public String getSoftType() {
		return softType;
	}

	public void setSoftType(String softType) {
		this.softType = softType;
	}
	@Transient
	public String getSoftTypeName() {
		return softTypeName;
	}

	public void setSoftTypeName(String softTypeName) {
		this.softTypeName = softTypeName;
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

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_time", insertable = true, updatable = true, length = 7)
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date newAuditTime) {
		this.auditTime = newAuditTime;
	}

	@Basic(optional = true)
	@Column(name = "auditor_id", insertable = true, updatable = true, length = 50)
	public String getAuditorId() {
		return auditorId;
	}

	
	public void setAuditorId(String newAuditorId) {
		this.auditorId = newAuditorId;
	}

	@Basic(optional = true)
	@Column(name = "audit_status", insertable = true, updatable = true,length = 4)
	public String getStatus() {
		return status;
	}

	public void setStatus(String newStatus) {
		this.status = newStatus;
	}
	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true,length = 20)
	public String getSoftStatus() {
		return softStatus;
	}
	public void setSoftStatus(String softStatus) {
		this.softStatus = softStatus;
	}
	@Basic(optional = true)
	@Column(name = "soft_file_name", insertable = true, updatable = true, length = 100)
	public String getSoftFileName() {
		return softFileName;
	}

	public void setSoftFileName(String softFileName) {
		this.softFileName = softFileName;
	}
	@Basic(optional = true)
	@Column(name = "soft_file_path", insertable = true, updatable = true, length = 200)
	public String getSoftFilePath() {
		return softFilePath;
	}

	public void setSoftFilePath(String softFilePath) {
		this.softFilePath = softFilePath;
	}
	@Basic(optional = true)
	@Column(name = "soft_file_length", insertable = true, updatable = true)
	public Long getSoftFileLength() {
		return softFileLength;
	}

	public void setSoftFileLength(Long softFileLength) {
		this.softFileLength = softFileLength;
	}
	@Basic(optional = true)
	@Column(name = "soft_file_md5", insertable = true, updatable = true, length = 50)
	public String getSoftFileMd5() {
		return softFileMd5;
	}

	public void setSoftFileMd5(String softFileMd5) {
		this.softFileMd5 = softFileMd5;
	}
	
	@Basic(optional = true)
	@Column(name = "soft_desc", insertable = true, updatable = true, length = 100)
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Basic(optional = true)
	@Column(name = "soft_environment", insertable = true, updatable = true, length = 10)
	public String getSoftwareEnvironment() {
		return softwareEnvironment;
	}
	public void setSoftwareEnvironment(String softwareEnvironment) {
		this.softwareEnvironment = softwareEnvironment;
	}
	@Basic(optional = true)
	@Column(name = "file_type_code", insertable = true, updatable = true, length = 20)
	public String getFileTypeCode() {
		return fileTypeCode;
	}
	public void setFileTypeCode(String fileTypeCode) {
		this.fileTypeCode = fileTypeCode;
	}
	@Column(name = "is_cancel",columnDefinition="varchar(2) default 'N'", insertable = true, updatable = true, length = 2)
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancel_time", insertable = true, updatable = true, length = 7)
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	@Column(name = "cancel_author", insertable = true, updatable = true, length = 50)
	public String getCancelAuthor() {
		return cancelAuthor;
	}
	public void setCancelAuthor(String cancelAuthor) {
		this.cancelAuthor = cancelAuthor;
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
				+ ((auditTime == null) ? 0 : auditTime.hashCode());
		result = prime * result
				+ ((auditorId == null) ? 0 : auditorId.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((edate == null) ? 0 : edate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result
				+ ((softFileLength == null) ? 0 : softFileLength.hashCode());
		result = prime * result
				+ ((softFileMd5 == null) ? 0 : softFileMd5.hashCode());
		result = prime * result
				+ ((softFileName == null) ? 0 : softFileName.hashCode());
		result = prime * result
				+ ((softFilePath == null) ? 0 : softFilePath.hashCode());
		result = prime * result
				+ ((softType == null) ? 0 : softType.hashCode());
		result = prime * result
				+ ((softTypeName == null) ? 0 : softTypeName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		SoftwareInfo other = (SoftwareInfo) obj;
		if (auditTime == null) {
			if (other.auditTime != null)
				return false;
		} else if (!auditTime.equals(other.auditTime))
			return false;
		if (auditorId == null) {
			if (other.auditorId != null)
				return false;
		} else if (!auditorId.equals(other.auditorId))
			return false;
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
		if (softFileLength == null) {
			if (other.softFileLength != null)
				return false;
		} else if (!softFileLength.equals(other.softFileLength))
			return false;
		if (softFileMd5 == null) {
			if (other.softFileMd5 != null)
				return false;
		} else if (!softFileMd5.equals(other.softFileMd5))
			return false;
		if (softFileName == null) {
			if (other.softFileName != null)
				return false;
		} else if (!softFileName.equals(other.softFileName))
			return false;
		if (softFilePath == null) {
			if (other.softFilePath != null)
				return false;
		} else if (!softFilePath.equals(other.softFilePath))
			return false;
		if (softType == null) {
			if (other.softType != null)
				return false;
		} else if (!softType.equals(other.softType))
			return false;
		if (softTypeName == null) {
			if (other.softTypeName != null)
				return false;
		} else if (!softTypeName.equals(other.softTypeName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		return "SoftwareInfo [id=" + id + ", name=" + name + ", version="
				+ version + ", softType=" + softType + ", softTypeName="
				+ softTypeName + ", softFileName=" + softFileName
				+ ", softFilePath=" + softFilePath + ", softFileLength="
				+ softFileLength + ", softFileMd5=" + softFileMd5
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", auditTime=" + auditTime + ", auditorId=" + auditorId
				+ ", status=" + status + ", sdate=" + sdate + ", edate="
				+ edate + "]";
	}
	
	
}