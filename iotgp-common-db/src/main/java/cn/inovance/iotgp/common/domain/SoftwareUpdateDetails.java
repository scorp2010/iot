package cn.inovance.iotgp.common.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @Title: SoftwareUpdateDetails.java 
 * @Package cn.inovance.iotgp.common.domain 
 * @Description: 软件更新信息表
 * @author xy2126 
 * @date 2016年1月15日 上午9:58:40 
 * @version V1.0
 */
@Entity(name="SoftwareUpdateDetails")
@Table(name="t_mms_software_update_details",indexes ={ @Index(name="t_mms_software_update_index",columnList= "cd_reg_code,soft_name,version")  })
public class SoftwareUpdateDetails
  implements Serializable
{
  private static final long serialVersionUID = -1790622374529504148L;
  private String id;
  private String cdRegCode;
  private String softName;
  private String version;
  private Date updateTime;
  private String updateResult;
  private String updateResultName;
  private Integer progress;
  private Date createTime;
  public SoftwareUpdateJob softWareUpdateJob;
  private String sdate;
  private String edate;

  @ManyToOne
  @JoinColumn(name="software_update_job_id_fk", referencedColumnName="id", nullable=true)
  public SoftwareUpdateJob getSoftWareUpdateJob()
  {
    return this.softWareUpdateJob;
  }

  public void setSoftWareUpdateJob(SoftwareUpdateJob newSoftwareUpdateJob) {
    this.softWareUpdateJob = newSoftwareUpdateJob; } 
  @Id
  @GenericGenerator(name="system-uuid", strategy="cn.inovance.iotgp.common.uuid.UUIDGenerator")
  @GeneratedValue(generator="system-uuid")
  @Column(name="id", nullable=false, insertable=true, updatable=true, length=36)
  public String getId() { return this.id; }

  public void setId(String newId)
  {
    this.id = newId;
  }
  @Basic(optional=true)
  @Column(name="cd_reg_code", insertable=true, updatable=true, length=36)
  public String getCdRegCode() {
    return this.cdRegCode;
  }

  public void setCdRegCode(String newCdRegCode) {
    this.cdRegCode = newCdRegCode;
  }
  @Basic(optional=true)
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="update_time", insertable=true, updatable=true, length=7)
  public Date getUpdateTime() { return this.updateTime; }

  public void setUpdateTime(Date newUpdateTime)
  {
    this.updateTime = newUpdateTime;
  }
  @Basic(optional=true)
  @Column(name="update_result", insertable=true, updatable=true, length=5)
  public String getUpdateResult() {
    return this.updateResult;
  }

  public void setUpdateResult(String newUpdateResult) {
    this.updateResult = newUpdateResult;
  }

  @Transient
  public String getUpdateResultName() {
    return this.updateResultName;
  }

  public void setUpdateResultName(String updateResultName) {
    this.updateResultName = updateResultName;
  }

  @Transient
  public Integer getProgress() {
    return this.progress;
  }

  public void setProgress(Integer progress) {
    this.progress = progress;
  }

  @Basic(optional=true)
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="create_time", insertable=true, updatable=true, length=7)
  public Date getCreateTime() { return this.createTime; }

  public void setCreateTime(Date newCreateTime)
  {
    this.createTime = newCreateTime;
  }
  @Basic(optional=true)
  @Column(name="soft_name", insertable=true, updatable=true, length=100)
  public String getSoftName() {
    return this.softName;
  }

  public void setSoftName(String softName) {
    this.softName = softName;
  }
  @Basic(optional=true)
  @Column(name="version", insertable=true, updatable=true, length=20)
  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Transient
  public String getSdate() {
    return this.sdate;
  }

  public void setSdate(String sdate) {
    this.sdate = sdate;
  }

  @Transient
  public String getEdate() {
    return this.edate;
  }

  public void setEdate(String edate) {
    this.edate = edate;
  }

  public String toString()
  {
    return "SoftwareUpdateDetails [id=" + this.id + ", cdRegCode=" + this.cdRegCode + 
      ", softName=" + this.softName + ", version=" + this.version + 
      ", updateTime=" + this.updateTime + ", updateResult=" + 
      this.updateResult + ", updateResultName=" + this.updateResultName + 
      ", progress=" + this.progress + ", createTime=" + this.createTime + "]";
  }

  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (
      this.cdRegCode == null ? 0 : this.cdRegCode.hashCode());
    result = 31 * result + (
      this.createTime == null ? 0 : this.createTime.hashCode());
    result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
    result = 31 * result + (
      this.softName == null ? 0 : this.softName.hashCode());
    result = 31 * result + (this.progress == null ? 0 : this.progress.hashCode());
    result = 31 * result + (
      this.updateResult == null ? 0 : this.updateResult.hashCode());
    result = 31 * 
      result + (
      this.updateResultName == null ? 0 : this.updateResultName.hashCode());
    result = 31 * result + (
      this.updateTime == null ? 0 : this.updateTime.hashCode());
    result = 31 * result + (this.version == null ? 0 : this.version.hashCode());
    return result;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SoftwareUpdateDetails other = (SoftwareUpdateDetails)obj;
    if (this.cdRegCode == null) {
      if (other.cdRegCode != null)
        return false;
    } else if (!this.cdRegCode.equals(other.cdRegCode))
      return false;
    if (this.createTime == null) {
      if (other.createTime != null)
        return false;
    } else if (!this.createTime.equals(other.createTime))
      return false;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.softName == null) {
      if (other.softName != null)
        return false;
    } else if (!this.softName.equals(other.softName))
      return false;
    if (this.progress == null) {
      if (other.progress != null)
        return false;
    } else if (!this.progress.equals(other.progress))
      return false;
    if (this.updateResult == null) {
      if (other.updateResult != null)
        return false;
    } else if (!this.updateResult.equals(other.updateResult))
      return false;
    if (this.updateResultName == null) {
      if (other.updateResultName != null)
        return false;
    } else if (!this.updateResultName.equals(other.updateResultName))
      return false;
    if (this.updateTime == null) {
      if (other.updateTime != null)
        return false;
    } else if (!this.updateTime.equals(other.updateTime))
      return false;
    if (this.version == null) {
      if (other.version != null)
        return false;
    } else if (!this.version.equals(other.version))
      return false;
    return true;
  }
}