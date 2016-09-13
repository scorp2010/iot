/***********************************************************************
 * Module:  SoftwareApproval.java
 * Author:  lk2200
 * Purpose: Defines the Class SoftwareApproval
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
 * 软件审批信息
 * 
 */
@Entity(name = "SoftwareApproval")
@Table(name = "t_mms_software_approval",indexes ={ @Index(name="software_name_index",columnList= "name") })

public class SoftwareApproval implements java.io.Serializable {

	private static final long serialVersionUID = 4320706714110319859L;
	/** 编号 */
	private String id;
	/** 软件名称 */
	private String name;
	/** 软件版本 */
	private String version;
	/**  审核状态（0：表示待审核，1：表示审核通过，2：表示审核不通过） */
	private String approvalStatus;
	private String approvalStatusName;
	/** 审核类型(0:表示测试转正式版，1：表示用户程序软件变更，2：表示单板软件变更) */
	private String approvalType;
	private String approvalTypeName;
	/** 备注 */
	private String remark;
	/** 审批人 */
	private String approvalPerson;
	/** 提交审批人 */
	private String submitPerson;
	/** 提交时间 */
	private Date   submitTime;
	/** 审批时间 */
	private Date   approvalTime;
	/** 待审核版本软件版本(审核状态为1：表示软件变更) */
	private String pendingVersion;
	/** 待审核版本软件(审核状态为1：表示软件变更) */
	private String pendingSoft;
	/** 控制器名称名称 */
	private String controlTypeName;
	/** 产品型号名称 */
	private String productTypeName;
	/** 控制器Id*/
	private String controlTypeId;
	/** 软件Id（用于测试转正式环境）*/
	private String softWareId;
	/** 产品型号Id*/
	private String productModelId;
	
	/** 查询新增字段 */
	private String sdate;
	private String edate;

	public SoftwareApproval() {
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
	@Column(name = "version", insertable = true, updatable = true, length = 100)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Basic(optional = true)
	@Column(name = "approval_status", insertable = true, updatable = true, length = 10)
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	@Basic(optional = true)
	@Column(name = "approval_type", insertable = true, updatable = true, length = 10)
	public String getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 2000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Basic(optional = true)
	@Column(name = "approval_person", insertable = true, updatable = true, length = 100)
	public String getApprovalPerson() {
		return approvalPerson;
	}
	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	@Basic(optional = true)
	@Column(name = "submit_person", insertable = true, updatable = true, length = 100)
	public String getSubmitPerson() {
		return submitPerson;
	}
	public void setSubmitPerson(String submitPerson) {
		this.submitPerson = submitPerson;
	}
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "submit_time", insertable = true, updatable = true, length = 7)
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approval_time", insertable = true, updatable = true, length = 7)
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	@Basic(optional = true)
	@Column(name = "pending_ersion", insertable = true, updatable = true, length = 100)
	public String getPendingVersion() {
		return pendingVersion;
	}
	public void setPendingVersion(String pendingVersion) {
		this.pendingVersion = pendingVersion;
	}
	@Basic(optional = true)
	@Column(name = "pending_soft", insertable = true, updatable = true, length = 100)
	public String getPendingSoft() {
		return pendingSoft;
	}
	public void setPendingSoft(String pendingSoft) {
		this.pendingSoft = pendingSoft;
	}
	@Basic(optional = true)
	@Column(name = "control_type_name", insertable = true, updatable = true, length = 100)
	public String getControlTypeName() {
		return controlTypeName;
	}
	public void setControlTypeName(String controlTypeName) {
		this.controlTypeName = controlTypeName;
	}
	@Basic(optional = true)
	@Column(name = "product_type_name", insertable = true, updatable = true, length = 100)
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	@Basic(optional = true)
	@Column(name = "control_type_id", insertable = true, updatable = true, length = 100)
	public String getControlTypeId() {
		return controlTypeId;
	}
	public void setControlTypeId(String controlTypeId) {
		this.controlTypeId = controlTypeId;
	}
	@Basic(optional = true)
	@Column(name = "software_id", insertable = true, updatable = true, length = 100)
	public String getSoftWareId() {
		return softWareId;
	}
	public void setSoftWareId(String softWareId) {
		this.softWareId = softWareId;
	}
	@Basic(optional = true)
	@Column(name = "product_model_id", insertable = true, updatable = true, length = 100)
	public String getProductModelId() {
		return productModelId;
	}
	public void setProductModelId(String productModelId) {
		this.productModelId = productModelId;
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
	public String getApprovalStatusName() {
		return approvalStatusName;
	}
	public void setApprovalStatusName(String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}
	@Transient
	public String getApprovalTypeName() {
		return approvalTypeName;
	}
	public void setApprovalTypeName(String approvalTypeName) {
		this.approvalTypeName = approvalTypeName;
	}
	@Override
	public String toString() {
		return "SoftwareInfo [id=" + id + ", name=" + name + ", approvalStatus="
				+ approvalStatus + ", approvalType=" + approvalType + ", remark="
				+ remark + ", approvalPerson=" + approvalPerson
				+ ", submitPerson=" + submitPerson + ", approvalTime=" + approvalTime
				+ ", submitTime=" + submitTime + ", pendingVersion=" + pendingVersion
				+ ", pendingSoft=" + pendingSoft + ", controlTypeName=" + controlTypeName
			    + ", sdate=" + sdate + ", edate="
				+ edate + "]";
	}
}