package cn.inovance.iotgp.common.domain;

import java.io.Serializable;
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

@Entity(name="TdSoftWareUpdateJob")
@Table(name="t_mms_td_software_update_job",indexes ={ 
		@Index(name="td_software_job_index",columnList= "cd_reg_code,soft_name,soft_version"),
		@Index(name="tdcontroller_scope_id_index",columnList= "tdcontroller_batchupdate_scope_id")
})
public class TdSoftWareUpdateJob
  implements Serializable
{
  private String id;
  private String cdRegCode;
  private Date createTime;
  private Date updateTime;
  
  private String tdControllerBatchUpdateScopeId;
  private String createrAccount;
  private String softName;
  private String softVersion;
  private String updateResult;
  private String updateResultName;
  private Integer progress;
  private String sdate;
  private String edate;

  @Id
  @GenericGenerator(name="system-uuid", strategy="cn.inovance.iotgp.common.uuid.UUIDGenerator")
  @GeneratedValue(generator="system-uuid")
  @Column(name="id", nullable=false, insertable=true, updatable=true, length=36)
  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
  @Basic(optional=true)
  @Column(name="cd_reg_code", insertable=true, updatable=true, length=36)
  public String getCdRegCode() {
    return this.cdRegCode;
  }
  public void setCdRegCode(String cdRegCode) {
    this.cdRegCode = cdRegCode;
  }
  @Basic(optional=true)
  @Column(name="soft_name", insertable=true, updatable=true, length=100)
  public String getSoftName() { return this.softName; }

  public void setSoftName(String softName) {
    this.softName = softName;
  }
  @Basic(optional=true)
  @Column(name="soft_version", insertable=true, updatable=true, length=20)
  public String getSoftVersion() { return this.softVersion; }

  public void setSoftVersion(String softVersion) {
    this.softVersion = softVersion;
  }
  @Basic(optional=true)
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="create_time", insertable=true, updatable=true, length=7)
  public Date getCreateTime() { return this.createTime; }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  
  @Basic(optional=true)
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="update_time", insertable=true, updatable=true, length=7)
  public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
  
  @Basic(optional=true)
  @Column(name="creater_account", insertable=true, updatable=true, length=50)
  public String getCreaterAccount() { return this.createrAccount; }

  public void setCreaterAccount(String createrAccount) {
    this.createrAccount = createrAccount;
  }
  @Basic(optional=true)
  @Column(name="update_result", insertable=true, updatable=true, length=5)
  public String getUpdateResult() { return this.updateResult; }

  public void setUpdateResult(String updateResult) {
    this.updateResult = updateResult;
  }
  @Basic(optional=true)
  @Column(name="tdcontroller_batchupdate_scope_id", insertable=true, updatable=true, length=36)
  public String getTdControllerBatchUpdateScopeId() {
	return tdControllerBatchUpdateScopeId;
  }

  public void setTdControllerBatchUpdateScopeId(
		String tdControllerBatchUpdateScopeId) {
	this.tdControllerBatchUpdateScopeId = tdControllerBatchUpdateScopeId;
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

  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (
      this.cdRegCode == null ? 0 : this.cdRegCode.hashCode());
    result = 31 * result + (
      this.createTime == null ? 0 : this.createTime.hashCode());
    result = 31 * result + (
      this.createrAccount == null ? 0 : this.createrAccount.hashCode());
    result = 31 * result + (this.edate == null ? 0 : this.edate.hashCode());
    result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
    result = 31 * result + (
      this.progress == null ? 0 : this.progress.hashCode());
    result = 31 * result + (this.sdate == null ? 0 : this.sdate.hashCode());
    result = 31 * result + (
      this.softName == null ? 0 : this.softName.hashCode());
    result = 31 * result + (
      this.softVersion == null ? 0 : this.softVersion.hashCode());
    result = 31 * result + (
      this.updateResult == null ? 0 : this.updateResult.hashCode());
    result = 31 * 
      result + (
      this.updateResultName == null ? 0 : this.updateResultName.hashCode());
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
    TdSoftWareUpdateJob other = (TdSoftWareUpdateJob)obj;
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
    if (this.createrAccount == null) {
      if (other.createrAccount != null)
        return false;
    } else if (!this.createrAccount.equals(other.createrAccount))
      return false;
    if (this.edate == null) {
      if (other.edate != null)
        return false;
    } else if (!this.edate.equals(other.edate))
      return false;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.progress == null) {
      if (other.progress != null)
        return false;
    } else if (!this.progress.equals(other.progress))
      return false;
    if (this.sdate == null) {
      if (other.sdate != null)
        return false;
    } else if (!this.sdate.equals(other.sdate))
      return false;
    if (this.softName == null) {
      if (other.softName != null)
        return false;
    } else if (!this.softName.equals(other.softName))
      return false;
    if (this.softVersion == null) {
      if (other.softVersion != null)
        return false;
    } else if (!this.softVersion.equals(other.softVersion))
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
    return true;
  }

  public String toString()
  {
    return "TdSoftWareUpdateJob [id=" + this.id + ", cdRegCode=" + this.cdRegCode + 
      ", createTime=" + this.createTime + ", createrAccount=" + 
      this.createrAccount + ", softName=" + this.softName + ", softVersion=" + 
      this.softVersion + ", updateResult=" + this.updateResult + 
      ", updateResultName=" + this.updateResultName + ", progress=" + 
      this.progress + ", sdate=" + this.sdate + ", edate=" + this.edate + "]";
  }
}