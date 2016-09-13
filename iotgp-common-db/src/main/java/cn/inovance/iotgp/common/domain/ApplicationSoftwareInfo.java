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

import org.hibernate.annotations.GenericGenerator;

/**
 * 应用软件任务信息
 * 
 */
@Entity(name = "ApplicationSoftwareInfo")
@Table(name = "t_mms_application_software_info",indexes ={ @Index(name="software_name_index",columnList= "name"),@Index(name="software_version_index",columnList= "version"),@Index(name="soft_file_md5_index",columnList= "soft_file_md5") })

public class ApplicationSoftwareInfo implements java.io.Serializable {

	private static final long serialVersionUID = 4320706714110319857L;
	/** 编号 */
	private String id;
	/** 软件名称 */
	private String name;
	/** 软件版本 */
	private String version;
	
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
	/** 软件描述*/
	private String desc;
	
	/** 查询新增字段 */
	private String sdate;
	private String edate;

	public ApplicationSoftwareInfo() {
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
	@Column(name = "soft_file_md5", insertable = true, updatable = true, length = 1000)
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
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result
				+ ((softFileLength == null) ? 0 : softFileLength.hashCode());
		result = prime * result
				+ ((softFileMd5 == null) ? 0 : softFileMd5.hashCode());
		result = prime * result
				+ ((softFileName == null) ? 0 : softFileName.hashCode());
		result = prime * result
				+ ((softFilePath == null) ? 0 : softFilePath.hashCode());
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
		ApplicationSoftwareInfo other = (ApplicationSoftwareInfo) obj;
		
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
		
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApplicationSoftwareInfo [id=" + id + ", name=" + name + ", version="
				+ version + ", softFileName=" + softFileName
				+ ", softFilePath=" + softFilePath + ", softFileLength="
				+ softFileLength + ", softFileMd5=" + softFileMd5
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", sdate=" + sdate + ", edate="
				+ edate + "]";
	}

	
}