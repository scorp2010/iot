/***********************************************************************
 * Module:  BasConfig.java
 * Author:  w1898
 * Purpose: Defines the Class BasConfig
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

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

/**
 * 客户定制bas信息相关配置信息
 * 
 * 
 * @pdOid 6e9252f4-61b5-44fc-a0d6-f7782ba337f8
 */
@Entity(name = "DataSyncLog")
@Table(name = "t_data_sync_log")
public class DataSyncLog implements java.io.Serializable {
	/** @pdOid 1be1954c-9999-4f4c-b5be-67edd8714c87 */
	private java.lang.String id;
	/**
	 * 数据同步请求消息
	 * 
	 * @pdOid f49011b2-28f2-4eee-8ff0-2e03b714321c
	 */
	private java.lang.String syncReqMsg;
	/**
	 * 数据同步响应消息
	 * 
	 * @pdOid c44cd583-3bdf-41a9-81aa-b437185b0fea
	 */
	private java.lang.String syncRspMsg;
	/**
	 * 同步状态 0-未同步 1-同步成功 2-同步失败
	 * 
	 * @pdOid fc7e828f-cb3c-4b31-a330-a3a40b06b5f7
	 */
	private java.lang.Integer status = 0;

	/**
	 * 创建时间
	 * 
	 * @pdOid 2e1fed47-9653-4c43-8468-bd73ba8c70fa
	 */
	private java.util.Date createTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public DataSyncLog() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "sync_req_msg", insertable = true, updatable = true, columnDefinition = "TEXT(64000) DEFAULT NULL COMMENT '同步请求消息'")
	public java.lang.String getSyncReqMsg() {
		return syncReqMsg;
	}

	public void setSyncReqMsg(java.lang.String syncReqMsg) {
		this.syncReqMsg = syncReqMsg;
	}

	@Basic(optional = true)
	@Column(name = "sync_rsp_msg", insertable = true, updatable = true, length = 1024)
	public java.lang.String getSyncRspMsg() {
		return syncRspMsg;
	}

	public void setSyncRspMsg(java.lang.String syncRspMsg) {
		this.syncRspMsg = syncRspMsg;
	}

	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true, length = 1)
	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true)
	public java.util.Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((syncReqMsg == null) ? 0 : syncReqMsg.hashCode());
		result = prime * result
				+ ((syncRspMsg == null) ? 0 : syncRspMsg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataSyncLog other = (DataSyncLog) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (syncReqMsg == null) {
			if (other.syncReqMsg != null)
				return false;
		} else if (!syncReqMsg.equals(other.syncReqMsg))
			return false;
		if (syncRspMsg == null) {
			if (other.syncRspMsg != null)
				return false;
		} else if (!syncRspMsg.equals(other.syncRspMsg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataSyncLog [id=" + id + ", syncReqMsg=" + syncReqMsg
				+ ", syncRspMsg=" + syncRspMsg + ", status=" + status
				+ ", createTime=" + createTime + "]";
	}

}