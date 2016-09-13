/***********************************************************************
 * Module:  FileInfo.java
 * Author:  w1898
 * Purpose: Defines the Class FileInfo
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 文件信息
 * 
 */
@Entity(name = "FileInfo")
@Table(name = "t_fms_file_info")
public class FileInfo implements java.io.Serializable {

	private static final long serialVersionUID = -4100060189590444175L;
	// 编号
	private String id;
	// 文件名
	private String fileName;
	private String fileTrueName;
	// 文件类型 ，默认软件
	private CommonType fileType;
	// 文件路径
	private String filePath;
	// 文件长度
	private Long fileLength;
	// 文件md5
	private String fileMd5;
	// 创建时间
	private Date createTime;
	// 所在域
	private String ownerDomain;

	// 查询新增字段
	private String sdate;
	private String edate;

	public FileInfo() {
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
	@Column(name = "file_name", insertable = true, updatable = true, length = 100)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String newFileName) {
		this.fileName = newFileName;
	}
	@Basic(optional = true)
	@Column(name = "file_TrueName", insertable = true, updatable = true, length = 100)
	public String getFileTrueName() {
		return fileTrueName;
	}

	public void setFileTrueName(String fileTrueName) {
		this.fileTrueName = fileTrueName;
	}
	
	@Basic(optional = true)
	@Column(name = "file_path", insertable = true, updatable = true, length = 100)
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String newFilePath) {
		this.filePath = newFilePath;
	}

	@Basic(optional = true)
	@Column(name = "file_length", insertable = true, updatable = true)
	public Long getFileLength() {
		return fileLength;
	}

	public void setFileLength(Long newFileLength) {
		this.fileLength = newFileLength;
	}

	@Basic(optional = true)
	@Column(name = "file_md5", insertable = true, updatable = true, length = 36)
	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String newFileMd5) {
		this.fileMd5 = newFileMd5;
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
	@Column(name = "owner_domain", insertable = true, updatable = true, length = 100)
	public String getOwnerDomain() {
		return ownerDomain;
	}

	public void setOwnerDomain(String newOwnerDomain) {
		this.ownerDomain = newOwnerDomain;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_type")
	public CommonType getFileType() {
		return fileType;
	}

	public void setFileType(CommonType fileType) {
		this.fileType = fileType;
	}

}