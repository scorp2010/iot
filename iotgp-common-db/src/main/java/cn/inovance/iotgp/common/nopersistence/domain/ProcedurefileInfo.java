/***********************************************************************
 * Module:  CdEventLog.java
 * Author:  w1898
 * Purpose: Defines the Class CdOnlineLog
 ***********************************************************************/

package cn.inovance.iotgp.common.nopersistence.domain;

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
 * 维保过程数据任务展示
 * 
 */
@Entity(name = "procedurefileInfo")
@Table(name = "t_fms_procedurefile_info")
public class ProcedurefileInfo implements java.io.Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 * 
	 */
	private java.lang.String id;
	/**
	 * 采集设备注册码
	 * 
	 */
	private java.lang.String cdRegCode;
	/**
	 * 文件名
	 * 
	 */
	private java.lang.String fileName;

	/**
	 * 文件相对路径
	 * 
	 */
	private java.lang.String filePath;
	/**
	 * 文件类型
	 * 
	 */
	private java.lang.String fileType;
	private java.lang.String fileTypeDes;
	/**
	 * 停止时间
	 * 
	 */
	private Date startTime;
	/**
	 * 开始时间
	 * 
	 */
	private Date stopTime;
	/**
	 *注册码
	 * 
	 */
	private java.lang.String regCode;
	/**
	 * DHS编码
	 * 
	 */
	private java.lang.String dhsCode;

	/**
	 * 创建时间
	 * 
	 */
	private Date createTime;
	private String sdate;
	private String edate;
	private String progress; //进度
	private String downloadUrl; //下载地址
	
	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public ProcedurefileInfo() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	/**
	 * Get value of cdRegCode
	 * 
	 * @return cdRegCode
	 */
	@Transient
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
	 * @return the fileName
	 */
	@Basic(optional = true)
	@Column(name = "file_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

	

	/**
	 * @return the dhsCode
	 */
	@Basic(optional = true)
	@Column(name = "dhs_code", insertable = true, updatable = true, length = 50)
	public java.lang.String getDhsCode() {
		return dhsCode;
	}

	/**
	 * @param dhsCode the dhsCode to set
	 */
	public void setDhsCode(java.lang.String dhsCode) {
		this.dhsCode = dhsCode;
	}
	
	
	@Basic(optional = true)
	@Column(name = "file_path", insertable = true, updatable = true, length = 255)
	public java.lang.String getFilePath() {
		return filePath;
	}

	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	@Basic(optional = true)
	@Column(name = "file_type", insertable = true, updatable = true, length = 36)
	public java.lang.String getFileType() {
		return fileType;
	}

	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", insertable = true, updatable = true, length = 20)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stop_time", insertable = true, updatable = true, length = 20)
	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	@Basic(optional = true)
	@Column(name = "reg_code", insertable = true, updatable = true, length = 28)
	public java.lang.String getRegCode() {
		return regCode;
	}

	public void setRegCode(java.lang.String regCode) {
		this.regCode = regCode;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public java.lang.String getFileTypeDes() {
		return fileTypeDes;
	}

	public void setFileTypeDes(java.lang.String fileTypeDes) {
		this.fileTypeDes = fileTypeDes;
	}

	@Transient
	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}


	@Transient
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
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

		if (!(other instanceof ProcedurefileInfo))
			return false;

		ProcedurefileInfo cast = (ProcedurefileInfo) other;

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
		if (this.regCode != null)
			hashCode = 29 * hashCode + regCode.hashCode();
		
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
		ret.append("cn.inovance.iotgp.common.nopersistence.domain: ");
		ret.append("id='" + id + "'");
		ret.append("regCode='" + regCode + "'");
		return ret.toString();
	}

}