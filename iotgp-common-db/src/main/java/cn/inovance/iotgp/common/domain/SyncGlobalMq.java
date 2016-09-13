/**
 * Project Name:iotgp-common-db
 * File Name:SyncGlobalMq.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-5-25下午5:05:35
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

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

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:SyncGlobalMq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-5-25 下午5:05:35 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "SyncGlobalMq")
@Table(name = "t_mms_sync_global_mq",indexes ={ @Index(name="sys_account_index",columnList= "sys_account,create_time,msg_type")
		 })
public class SyncGlobalMq {

	/** 编号 */
	private String id;
	/**创建时间*/
	private Long createTime;
	/**失效时间*/
	private Long expiredTime;
	/**系统编码*/
	private String  sysAccount;
	/**消息类型*/
	private String msgType;
	/**消息*/
	private String msg;
	
	
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
	@Column(name = "create_time")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Basic(optional = true)
	@Column(name = "expired_time")
	public Long getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}
	@Basic(optional = true)
	@Column(name = "sys_account", insertable = true, updatable = true, length = 50)
	public String getSysAccount() {
		return sysAccount;
	}
	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}
	@Basic(optional = true)
	@Column(name = "msg_type", insertable = true, updatable = true, length = 100)
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	@Basic(optional = true)
	@Column(name = "msg", insertable = true, updatable = true, columnDefinition = "longtext")
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}

