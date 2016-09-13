/***********************************************************************
 * Module:  SoftwareUpdateScope.java
 * Author:  w1898
 * Purpose: Defines the Class SoftwareUpdateScope
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 软件更新范围
 * 
 */
@Entity(name = "SoftwareUpdateScope")
@Table(name = "t_mms_software_update_scope",indexes ={ @Index(name="t_mms_software_update_scope",columnList= "name")})
public class SoftwareUpdateScope implements java.io.Serializable {

	private static final long serialVersionUID = 6808889806256753391L;
	/** 编号， */
	private String id;
	/** 范围名称 */
	private String name;
	/** 更新设备数量 */
	private Integer cdCount;
	/** 所有更新的采集设备的注册码 */
	private String cdRegCodes;
	/** 更新范围描述 */
	private String describe;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createAccount;
	/** 平台切换 */
	private String isSwitch ;
	public static final String Y_SWITCH ="Y";
	public static final String N_SWITCH ="N";
	
	public Set<SoftwareUpdateJob> softWareUpdateJobList = new HashSet<SoftwareUpdateJob>(
			0);

	/** 查询新增字段 */
	private String sdate;
	private String edate;

	private String fileUrl;
	
	public SoftwareUpdateScope() {
	}

	@OneToMany(mappedBy = "softWareUpdateScope", fetch = FetchType.LAZY)
	public Set<SoftwareUpdateJob> getSoftWareUpdateJobList() {
		return softWareUpdateJobList;
	}

	public void setSoftWareUpdateJobList(
			Set<SoftwareUpdateJob> newSoftWareUpdateJobList) {
		this.softWareUpdateJobList = newSoftWareUpdateJobList;
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
	@Column(name = "cd_count", insertable = true, updatable = true)
	public Integer getCdCount() {
		return cdCount;
	}

	public void setCdCount(Integer newCdCount) {
		this.cdCount = newCdCount;
	}

	@Lob()
	@Basic(optional = true,fetch = FetchType.LAZY)
	@Column(name = "cd_reg_codes", insertable = true, columnDefinition = "mediumtext DEFAULT NULL COMMENT '升级设备范围;'")
	public String getCdRegCodes() {
		return cdRegCodes;
	}

	public void setCdRegCodes(String newCdRegCodes) {
		this.cdRegCodes = newCdRegCodes;
	}

	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 100)
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String newDescribe) {
		this.describe = newDescribe;
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
	@Column(name = "create_account", insertable = true, updatable = true, length = 50)
	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String newCreateAccount) {
		this.createAccount = newCreateAccount;
	}

	
	@Basic(optional = true)
	@Column(name = "is_switch", insertable = true, updatable = true, length = 5)
	public String getIsSwitch() {
		return isSwitch;
	}

	public void setIsSwitch(String isSwitch) {
		this.isSwitch = isSwitch;
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
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}