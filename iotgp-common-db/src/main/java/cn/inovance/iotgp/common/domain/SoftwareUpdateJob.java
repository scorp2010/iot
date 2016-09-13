/***********************************************************************
 * Module:  SoftwareUpdateJob.java
 * Author:  w1898
 * Purpose: Defines the Class SoftwareUpdateJob
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 软件更新任务
 * 
 */
@Entity(name = "SoftwareUpdateJob")
@Table(name = "t_mms_software_update_job",indexes ={ @Index(name="t_mms_software_update_job_index",columnList= "name")})
public class SoftwareUpdateJob implements java.io.Serializable {

	private static final long serialVersionUID = -948430044693424593L;
	/** 编号 */
	private String id;
	/** 名称 */
	private String name;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createrAccount;
	/** 审批时间 */
	private Date auditTime;
	/** 审批人 */
	private String auditorId;
	/** 任务状态 1:未审批，2:审批通过，3:审批未通过. */
	private String status;
	private String statusName;
	/** 备注 */
	private String remark;

	public Set<SoftwareUpdateDetails> softWareUpdateDetails = new HashSet<SoftwareUpdateDetails>(
			0);
	public SoftwareUpdateScope softWareUpdateScope;
	
	public SoftwareInfo softWareInfo;

	/** 查询新增字段 */
	private String sdate;
	private String edate;
	private String scopename;
	private String softname;
	private String softVersion;
	
	public SoftwareUpdateJob() {
	}

	@OneToMany(mappedBy = "softWareUpdateJob", fetch = FetchType.LAZY)
	public Set<SoftwareUpdateDetails> getSoftWareUpdateDetails() {
		return softWareUpdateDetails;
	}

	public void setSoftWareUpdateDetails(
			Set<SoftwareUpdateDetails> newSoftWareUpdateDetails) {
		this.softWareUpdateDetails = newSoftWareUpdateDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "software_update_scope_id_fk", referencedColumnName = "id", nullable = true)
	public SoftwareUpdateScope getSoftWareUpdateScope() {
		return softWareUpdateScope;
	}

	public void setSoftWareUpdateScope(
			SoftwareUpdateScope newSoftwareUpdateScope) {
		this.softWareUpdateScope = newSoftwareUpdateScope;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "software_info_id_fk", referencedColumnName = "id", nullable = true)
	public SoftwareInfo getSoftWareInfo() {
		return softWareInfo;
	}

	public void setSoftWareInfo(SoftwareInfo newSoftwareInfo) {
		this.softWareInfo = newSoftwareInfo;
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Column(name = "creater_account", insertable = true, updatable = true, length = 50)
	public String getCreaterAccount() {
		return createrAccount;
	}

	public void setCreaterAccount(String newCreaterAccount) {
		this.createrAccount = newCreaterAccount;
	}

	@Basic(optional = true)
	@Column(name = "audit_time", insertable = true, updatable = true, length = 7)
	public Date getAuditTime() {
		return this.auditTime;
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
	@Column(name = "status", insertable = true, updatable = true, length = 5)
	public String getStatus() {
		return status;
	}

	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getScopename() {
		return scopename;
	}

	public void setScopename(String scopename) {
		this.scopename = scopename;
	}

	@Transient
	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}
	
	@Transient
	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}
	
	@Transient
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}