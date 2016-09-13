package cn.inovance.iotgp.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/** 设备信息同步明细表 */
@SuppressWarnings("serial")
@Entity(name = "DeviceSyncInfo")
@Table(name = "t_oms_cd_sync")
public class DeviceSyncInfo implements Serializable {

	/** 自增ID */
	private java.lang.String id;

	/** 客户账号 */
	private String customerAccount;

	/** 同步对象（1：CD 2：TD） */
	private short syncObjType;

	/** 同步类型（1：新增 2：删除 3：替换 ）  */
	private short syncType;

	/** 替换前采集设备注册码 */
	private String oldCdRegCode;

	/** 采集设备注册码列表（多个用分号隔开） */
	private String cdRegCodes;

	/** 替换前目标设备id */
	private String oldTdId;

	/** 目标设备id列表（多个用分号隔开）（删除时由于目标设备的记录已删除，存储的是tdType-tdAddress） */
	private String tdIds;

	/** 创建时间 */
	private Date createTime;

	/** 创建账号 */
	private String createUser;

	/** 处理标识（1：未处理 2：已处理） */
	private short processStatus;

	/** 处理时间 */
	private Date processTime;

	/** 处理账号 */
	private String processUser;

	public DeviceSyncInfo() {
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	@Basic(optional = false)
	@Column(name = "customer_account", insertable = true, updatable = true, length = 36)
	public String getCustomerAccount() {
		return customerAccount;
	}
	
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Basic(optional = false)
	@Column(name = "sync_obj_type", insertable = true, updatable = true)
	public short getSyncObjType() {
		return syncObjType;
	}

	public void setSyncObjType(short syncObjType) {
		this.syncObjType = syncObjType;
	}

	@Basic(optional = false)
	@Column(name = "sync_type", insertable = true, updatable = true, length = 36)
	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	@Basic(optional = true)
	@Column(name = "old_cd_reg_code", insertable = true, updatable = true, length = 36)
	public String getOldCdRegCode() {
		return oldCdRegCode;
	}

	public void setOldCdRegCode(String oldCdRegCode) {
		this.oldCdRegCode = oldCdRegCode;
	}

	@Basic(optional = true)
	@Column(name = "cd_reg_codes", insertable = true, updatable = true, length = 4000,columnDefinition = "longtext")
	public String getCdRegCodes() {
		return cdRegCodes;
	}

	public void setCdRegCodes(String cdRegCodes) {
		this.cdRegCodes = cdRegCodes;
	}

	@Basic(optional = true)
	@Column(name = "old_td_id", insertable = true, updatable = true, length = 36)
	public String getOldTdId() {
		return oldTdId;
	}

	public void setOldTdId(String oldTdId) {
		this.oldTdId = oldTdId;
	}

	@Basic(optional = true)
	@Column(name = "td_ids", insertable = true, updatable = true, length = 4000,columnDefinition = "longtext")
	public String getTdIds() {
		return tdIds;
	}

	public void setTdIds(String tdIds) {
		this.tdIds = tdIds;
	}

	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Basic(optional = false)
	@Column(name = "create_user", insertable = true, updatable = true, length = 36)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Basic(optional = false)
	@Column(name = "process_status", insertable = true, updatable = true,columnDefinition="tinyint default 1")
	public short getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(short processStatus) {
		this.processStatus = processStatus;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "process_time", insertable = true, updatable = true, length = 7)
	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@Basic(optional = true)
	@Column(name = "process_user", insertable = true, updatable = true, length = 36)
	public String getProcessUser() {
		return processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}
	
	
}
