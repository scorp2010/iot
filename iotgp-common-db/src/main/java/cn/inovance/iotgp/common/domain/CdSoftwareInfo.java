/***********************************************************************
 * Module:  CdSoftwareInfo.java
 * Author:  w1898
 * Purpose: Defines the Class CdSoftwareInfo
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集设备软件信息
 * 
 */
@Entity(name = "CdSoftwareInfo")
@Table(name = "t_mms_cd_software_info",indexes ={ @Index(name="t_mms_cd_software_index",columnList= "cd_reg_code,name,version")  })
public class CdSoftwareInfo implements java.io.Serializable {

	private static final long serialVersionUID = -9186341136283512122L;
	// 编号
	private String id;
	// 采集设备注册码
	private String cdRegCode;
	// 软件名字
	private String name;
	// 软件版本
	private String version;
	
	private String fileType;

	private String fileTypeName;
	// 更新时间
	private Date updateTime;
	// 文件信息编号
	private String fileInfoId;
	// 创建时间
	private Date createTime;
    /** 关联采集设备更新任务details,job是否立即更新,不存储. */
	private String newVersion;
	public CollectDevice collectDevice;
	
	

	public CdSoftwareInfo() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "collect_device_id_fk", referencedColumnName = "id", nullable = true)
	public CollectDevice getCollectDevice() {
		return collectDevice;
	}

	public void setCollectDevice(CollectDevice newCollectDevice) {
		this.collectDevice = newCollectDevice;
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
	@Column(name = "name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	@Basic(optional = true)
	@Column(name = "version", insertable = true, updatable = true)
	public String getVersion() {
		return version;
	}

	public void setVersion(String newVersion) {
		this.version = newVersion;
	}

	@Basic(optional = true)
	@Column(name = "file_type", insertable = true, updatable = true,length=5)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Transient
	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	@Basic(optional = true)
	@Column(name = "file_info_id", insertable = true, updatable = true, length = 32)
	public String getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(String newFileInfoId) {
		this.fileInfoId = newFileInfoId;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}
	@Transient
	public String getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime * result
				+ ((collectDevice == null) ? 0 : collectDevice.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((fileInfoId == null) ? 0 : fileInfoId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
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
		CdSoftwareInfo other = (CdSoftwareInfo) obj;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (collectDevice == null) {
			if (other.collectDevice != null)
				return false;
		} else if (!collectDevice.equals(other.collectDevice))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (fileInfoId == null) {
			if (other.fileInfoId != null)
				return false;
		} else if (!fileInfoId.equals(other.fileInfoId))
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
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
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
		return "CdSoftwareInfo [id=" + id + ", cdRegCode=" + cdRegCode
				+ ", name=" + name + ", version=" + version + ", updateTime="
				+ updateTime + ", fileInfoId=" + fileInfoId + ", createTime="
				+ createTime + "]";
	}
	
	
	


	/**--------start------------ 扩展属性,只作为属性返回，不映射数据库 加注解 @Transient-------*/
	private Date jobCreatDate;
	private Date softUpdateDate;
	
	@Transient
	public Date getJobCreatDate() {
		return jobCreatDate;
	}
	public void setJobCreatDate(Date jobCreatDate) {
		this.jobCreatDate = jobCreatDate;
	}
	
	@Transient
	public Date getSoftUpdateDate() {
		return softUpdateDate;
	}
	public void setSoftUpdateDate(Date softUpdateDate) {
		this.softUpdateDate = softUpdateDate;
	}
	
	
	/**--------end------------扩展属性,只作为属性返回，不映射数据库 加注解@Transient-------*/
}