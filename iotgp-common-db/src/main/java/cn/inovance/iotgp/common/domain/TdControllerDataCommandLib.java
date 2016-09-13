/**
 * Project Name:iotgp-common-db
 * File Name:CommandLib.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-1-15上午9:49:51
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CommandLib. <br/>
 * Date:     2015-1-15 上午9:49:51 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "TdControllerDataCommandLib")
@Table(name = "t_mms_td_controller_data_command_lib",indexes ={ @Index(name="td_controller_type_id_index",columnList= "td_controller_type_id_fk,update_time"),@Index(name="command_name_index",columnList= "command_name")  })
public class TdControllerDataCommandLib implements java.io.Serializable {

	/**
	 * 编号.
	 */
	private String id;
	/** 
	 * 名称
	 */
	private String name;
	/**
	 * 指令模板
	 */
	private String templet;
	
	/** 
	 * 指令参数，多个参数用逗号隔开
	 */
	private String params;	
	
	/**
	 * 数据格式  1.16进制 2.ASCII码
	 */
	private Short format;
	
	private String formatName;
	/** 
	 * 校验方式 1.CRC校验 2.和校验
	 */
	private Short checkType;
	
	private String checkTypeName;
	/**
	 * 最终指令
	 */
	private String cmd;	
	
	/** 
	 * 响应数据长度
	 */
	private Short rspDataLength;
	
	/** 
	 * 创建时间
	 */
	private Date createTime;
	
	/** 
	 * 修改时间
	 */
	private Date updateTime;
	/** 更新类型 */
	private Short updateType;

	private TdControllerType tdControllerType;
	
	
	private String fileUrl;
	
	
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
	@Column(name = "command_name", insertable = true, updatable = true, length = 40 )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic(optional = true)
	@Column(name = "command_templet", insertable = true, updatable = true, length = 100 )
	public String getTemplet() {
		return templet;
	}
	public void setTemplet(String templet) {
		this.templet = templet;
	}
	@Basic(optional = true)
	@Column(name = "command_params", insertable = true, updatable = true, length = 100 )
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	@Basic(optional = true)
	@Column(name = "command_format", insertable = true, updatable = true )
	public Short getFormat() {
		return format;
	}
	public void setFormat(Short format) {
		this.format = format;
	}
	@Basic(optional = true)
	@Column(name = "command_checkType", insertable = true, updatable = true )
	public Short getCheckType() {
		return checkType;
	}
	public void setCheckType(Short checkType) {
		this.checkType = checkType;
	}
	@Basic(optional = true)
	@Column(name = "command_cmd", insertable = true, updatable = true, length = 200 )
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	@Basic(optional = true)
	@Column(name = "command_rsp_length", insertable = true, updatable = true)
	public Short getRspDataLength() {
		return rspDataLength;
	}
	public void setRspDataLength(Short rspDataLength) {
		this.rspDataLength = rspDataLength;
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
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "td_controller_type_id_fk", referencedColumnName = "id", nullable = true)
	public TdControllerType getTdControllerType() {
		return tdControllerType;
	}
	public void setTdControllerType(
			TdControllerType tdControllerType) {
		this.tdControllerType = tdControllerType;
	}
	@Basic(optional = true)
	@Column(name = "update_type", insertable = true,length = 10)
	public Short getUpdateType() {
		return updateType;
	}
	public void setUpdateType(Short updateType) {
		this.updateType = updateType;
	}
	@Transient
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	@Transient
	public String getCheckTypeName() {
		return checkTypeName;
	}
	public void setCheckTypeName(String checkTypeName) {
		this.checkTypeName = checkTypeName;
	}
	@Transient
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((checkType == null) ? 0 : checkType.hashCode());
		result = prime * result + ((cmd == null) ? 0 : cmd.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result
				+ ((rspDataLength == null) ? 0 : rspDataLength.hashCode());
		result = prime * result + ((templet == null) ? 0 : templet.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
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
		TdControllerDataCommandLib other = (TdControllerDataCommandLib) obj;
		if (checkType == null) {
			if (other.checkType != null)
				return false;
		} else if (!checkType.equals(other.checkType))
			return false;
		if (cmd == null) {
			if (other.cmd != null)
				return false;
		} else if (!cmd.equals(other.cmd))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		if (rspDataLength == null) {
			if (other.rspDataLength != null)
				return false;
		} else if (!rspDataLength.equals(other.rspDataLength))
			return false;
		if (templet == null) {
			if (other.templet != null)
				return false;
		} else if (!templet.equals(other.templet))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TdControllerDataCommandLib [id=" + id + ", name=" + name
				+ ", templet=" + templet + ", params=" + params + ", format="
				+ format + ", checkType=" + checkType + ", cmd=" + cmd
				+ ", rspDataLength=" + rspDataLength + ", createTime="
				+ createTime + ", updateTime=" + updateTime
				+ "]";
	}

}

