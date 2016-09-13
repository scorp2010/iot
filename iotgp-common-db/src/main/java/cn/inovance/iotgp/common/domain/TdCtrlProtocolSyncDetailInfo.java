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

@SuppressWarnings("serial")
@Entity(name = "TdCtrlProtocolSyncDetailInfo")
@Table(name = "t_oms_td_controller_sync_detail")
public class TdCtrlProtocolSyncDetailInfo implements Serializable {

	/** ID标识 */
	private String id;
	/** 子系统账号 */
	private String sysAccount;
	/** 同步类型（1：新增 2：修改 3：删除）  */
	private short syncType;
	/** 控制器类型 */
	private String ctrlType;
	/** 创建时间 */
	private Date createTime;
	
	public TdCtrlProtocolSyncDetailInfo() {
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
	@Column(name = "sys_account", insertable = true, nullable =true, updatable = true, length = 36)
	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}
	
	@Basic(optional = false)
	@Column(name = "sync_type", insertable = true, updatable = true)
	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}
	
	@Basic(optional = false)
	@Column(name = "ctrl_type", insertable = true, updatable = true, length = 36)
	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
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
