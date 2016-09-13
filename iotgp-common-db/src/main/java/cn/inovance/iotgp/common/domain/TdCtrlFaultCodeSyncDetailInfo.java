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
@Entity(name = "TdCtrlFaultCodeSyncDetailInfo")
@Table(name = "t_oms_td_controller_FaultCode_sync_detail")
public class TdCtrlFaultCodeSyncDetailInfo implements Serializable,Cloneable {

	/** ID标识 */
	private String id;
	/** 子系统账号 */
	private String sysAccount;
	/** 同步类型（1：新增 2：修改 3：删除） */
	private short syncType;
	/** 控制器类型 */
	private String ctrlType;
	/** 故障编码 */
	private String faultCode;
	/** 创建时间 */
	private Date createTime;

	public TdCtrlFaultCodeSyncDetailInfo() {
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

	@Basic(optional = true)
	@Column(name = "sys_account", insertable = true, nullable = false, updatable = true, length = 36)
	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	@Basic(optional = true)
	@Column(name = "sync_type", insertable = true, updatable = true)
	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	@Basic(optional = true)
	@Column(name = "ctrl_type", insertable = true, updatable = true, length = 36)
	public String getCtrlType() {
		return ctrlType;
	}

	@Basic(optional = false)
	@Column(name = "fault_code", insertable = true, updatable = true, length = 36)
	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
