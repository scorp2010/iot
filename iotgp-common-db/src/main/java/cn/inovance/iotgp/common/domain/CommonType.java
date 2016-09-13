/**
 * Project Name:domain
 * File Name:CommonType.java
 * Package Name:cn.inovance.iotgp.hibernate.common.domain
 * Date:2014-4-24下午2:02:35
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.domain;

import java.util.Date;

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
 * ClassName:CommonType. 公共数据表，存储type类型
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Entity(name = "CommonType")
@Table(name = "t_common_type",indexes ={ @Index(name="common_type_index",columnList= "type")})
public class CommonType implements java.io.Serializable {

	/**
	 * 编号.
	 * 
	 **/
	private java.lang.String id;
	/**
	 * 名称.
	 * 
	 **/
	private java.lang.String value;
	/**
	 * 内部编码.
	 * 
	 **/
	private java.lang.String code;
	/**
	 * value.
	 * 
	 **/
	private java.lang.String remark;
	/**
	 * 描述.
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

	@Column(name = "type_value", length = 50)
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
	public boolean equals(Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (!(other instanceof CommonType))
			return false;

		CommonType cast = (CommonType) other;

		if (!this.getId().equals(cast.getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		if (this.value != null)
			hashCode = 29 * hashCode + value.hashCode();
		if (this.remark != null)
			hashCode = 29 * hashCode + remark.hashCode();
		if (this.type != null)
			hashCode = 29 * hashCode + type.hashCode();
		if (this.creater != null)
			hashCode = 29 * hashCode + creater.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
		if (this.updateTime != null)
			hashCode = 29 * hashCode + updateTime.hashCode();
		if (this.hasDelete != null)
			hashCode = 29 * hashCode + hasDelete.hashCode();
		return hashCode;
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
