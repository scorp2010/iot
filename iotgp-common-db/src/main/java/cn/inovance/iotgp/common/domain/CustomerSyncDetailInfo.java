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
@Entity(name = "CustomerSyncDetailInfo")
@Table(name = "t_oms_customer_sync_detail")
public class CustomerSyncDetailInfo implements Serializable{
	
	/** ID标识 */
	private String id;
	/** 客户账号 */
	private String customerAccount;
	/** 同步类型（1：新增 2：修改 3：删除）  */
	private short syncType;
	/** 创建时间 */
	private Date createTime;
	
	public CustomerSyncDetailInfo() {
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
	@Column(name = "sync_type", insertable = true, updatable = true)
	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
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
