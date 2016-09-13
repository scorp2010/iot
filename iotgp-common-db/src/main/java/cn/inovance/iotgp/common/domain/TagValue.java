/**
 * Project Name:iotgp-common-db
 * File Name:CdTagValue.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-6-13上午10:28:12
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CdTagValue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-13 上午10:28:12 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "CdTagValue")
@Table(name = "t_sys_tag_value")
public class TagValue implements java.io.Serializable {
	/**
	 * 编号.
	 * 
	 **/
	private java.lang.String id;
	/**
	 * 编码.
	 * 
	 **/
	private java.lang.String code;
	/**
	 * 名称.
	 * 
	 **/
	private java.lang.String value;
	/**
	 * 描述.
	 * 
	 */
	private java.lang.String remark;
	/**
	 * 类型.
	 * 
	 */
	private java.lang.String type;

	/**
	 * 创建时间.
	 * 
	 * */
	private java.util.Date createTime;
	/**
	 * 更新时间.
	 * 
	 * */
	private java.util.Date updateTime;

	/**
	    * 
	    */
	private java.lang.String creater;

	/**
	    * 
	    */
	private java.lang.String createrName;

	private java.lang.Short status;

	private java.lang.Boolean hasDelete;
	@Column(name = "status")
	public java.lang.Short getStatus() {
		return status;
	}

	public void setStatus(java.lang.Short status) {
		this.status = status;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	@Column(name = "type_value", nullable = false, length = 50)
	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	@Column(name = "remark", length = 100)
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	@Column(name = "creater", nullable = false, length = 36)
	public java.lang.String getCreater() {
		return this.creater;
	}

	public void setCreater(java.lang.String creater) {
		this.creater = creater;
	}

	@Column(name = "type", nullable = false, length = 100)
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	@Column(name = "creater_name", nullable = false, length = 100)
	public java.lang.String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(java.lang.String createrName) {
		this.createrName = createrName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public java.util.Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public java.util.Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "has_delete")
	public java.lang.Boolean getHasDelete() {

		if (null != hasDelete) {
			return hasDelete;
		} else {
			return false;
		}
	}

	public void setHasDelete(java.lang.Boolean hasDelete) {
		this.hasDelete = hasDelete;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagValue other = (TagValue) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creater == null) {
			if (other.creater != null)
				return false;
		} else if (!creater.equals(other.creater))
			return false;
		if (createrName == null) {
			if (other.createrName != null)
				return false;
		} else if (!createrName.equals(other.createrName))
			return false;
		if (hasDelete == null) {
			if (other.hasDelete != null)
				return false;
		} else if (!hasDelete.equals(other.hasDelete))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creater == null) ? 0 : creater.hashCode());
		result = prime * result
				+ ((createrName == null) ? 0 : createrName.hashCode());
		result = prime * result
				+ ((hasDelete == null) ? 0 : hasDelete.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("cn.inovance.iotgp.common.domain.CommonType: ");
		ret.append("id='" + id + "'");
		ret.append("value='" + value + "'");
		ret.append("remark='" + remark + "'");
		ret.append("type='" + type + "'");
		ret.append("creater='" + creater + "'");
		ret.append("createTime='" + createTime + "'");
		ret.append("updateTime='" + updateTime + "'");
		ret.append("hasDelete='" + hasDelete + "'");
		return ret.toString();
	}

}

