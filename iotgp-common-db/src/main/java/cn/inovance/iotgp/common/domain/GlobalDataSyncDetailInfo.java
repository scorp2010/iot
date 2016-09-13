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
@Entity(name = "GlobalDataSyncDetailInfo")
@Table(name = "t_oms_global_data_sync_detail")
public class GlobalDataSyncDetailInfo implements Serializable, Cloneable {

	/** ID标识 */
	private String id;
	/** 子系统账号 */
	private String sysAccount;
	/** 消息类型 */
	private String msgType;
	/** 消息内容 */
	private String message;
	/** 创建时间 */
	private Date createTime;

	public GlobalDataSyncDetailInfo() {
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
	@Column(name = "sys_account", insertable = true, nullable = false, updatable = true, length = 200)
	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	@Basic(optional = false)
	@Column(name = "msgType", insertable = true, updatable = true, length = 50)
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Basic(optional = false)
	@Column(name = "message", insertable = true, updatable = true, length = 5000)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
