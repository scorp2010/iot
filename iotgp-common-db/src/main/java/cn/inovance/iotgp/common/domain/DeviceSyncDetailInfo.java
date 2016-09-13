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
@Entity(name = "DeviceSyncDetailInfo")
@Table(name = "t_oms_cd_sync_detail")
public class DeviceSyncDetailInfo implements Serializable {

	/** ID标识 */
	private String id;
	/** 客户账号 */
	private String customerAccount;
	/** 同步对象（1：CD 2：TD） */
	private short syncObjType;
	/** 同步类型（1：新增  2：删除  3：替换）  */
	private short syncType;
	/** 替换前采集设备注册码 */
	private String oldCdRegCode;
	/** 采集设备注册码 */
	private String cdRegCode;
	/** 替换前目标设备ID（删除时由于目标设备的记录已删除，存储的是tdType-tdAddress） */
	private String oldTdId;
	/** 新目标设备ID*/
	private String tdId;
	/** 创建时间 */
	private Date createTime;
	
	public DeviceSyncDetailInfo(){
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	@Column(name = "sync_type", insertable = true, updatable = true)
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
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
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
	@Column(name = "td_id", insertable = true, updatable = true, length = 36)
	public String getTdId() {
		return tdId;
	}

	public void setTdId(String tdId) {
		this.tdId = tdId;
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
	
}
