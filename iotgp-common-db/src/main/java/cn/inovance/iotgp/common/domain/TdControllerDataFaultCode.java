/**
 * Project Name:iotgp-common-db
 * File Name:TdControllerDataFaultCode.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-11-24上午9:16:34
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
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
import org.springframework.transaction.annotation.Transactional;

import antlr.JavaCodeGeneratorPrintWriterManager;

/**
 * ClassName:TdControllerDataFaultCode <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-11-24 上午9:16:34 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "TdControllerDataFaultCode")
@Table(name = "t_mms_td_controller_data_fault_code",indexes ={ @Index(name="faultCode_index",columnList= "fault_code"),
		@Index(name="idx_id_update_time",columnList= "td_controller_type_id_fk,update_time") })
public class TdControllerDataFaultCode implements java.io.Serializable{
	
	private static final long serialVersionUID = 2566359305778126242L;
	/** 编号 */
	private String id;
	/** 故障代码 */
	private String faultCode;
	/** 故障代码 描述 */
	private String describe;
	/** 故障缘由 */
	private String faultCause;
	/** 故障处理方式 */
	private String processMethod;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
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
	@Column(name = "fault_code", insertable = true, updatable = true, length = 20,unique= true)
	public String getFaultCode() {
		return faultCode;
	}
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	@Basic(optional = true)
	@Column(name = "fault_desc", insertable = true, updatable = true, length = 200 )
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Basic(optional = true)
	@Column(name = "fault_cause", insertable = true, updatable = true, length = 1000 )
	public String getFaultCause() {
		return faultCause;
	}
	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}
	@Basic(optional = true)
	@Column(name = "process_method", insertable = true, updatable = true, length = 1000 )
	public String getProcessMethod() {
		return processMethod;
	}
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	@Basic(optional = true)
	@Column(name = "creator", insertable = true, updatable = true, length = 50 )
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((describe == null) ? 0 : describe.hashCode());
		result = prime * result
				+ ((faultCause == null) ? 0 : faultCause.hashCode());
		result = prime * result
				+ ((faultCode == null) ? 0 : faultCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((processMethod == null) ? 0 : processMethod.hashCode());
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
		TdControllerDataFaultCode other = (TdControllerDataFaultCode) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (describe == null) {
			if (other.describe != null)
				return false;
		} else if (!describe.equals(other.describe))
			return false;
		if (faultCause == null) {
			if (other.faultCause != null)
				return false;
		} else if (!faultCause.equals(other.faultCause))
			return false;
		if (faultCode == null) {
			if (other.faultCode != null)
				return false;
		} else if (!faultCode.equals(other.faultCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (processMethod == null) {
			if (other.processMethod != null)
				return false;
		} else if (!processMethod.equals(other.processMethod))
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
		return "TdControllerDataFaultCode [id=" + id + ", faultCode="
				+ faultCode + ", describe=" + describe + ", faultCause="
				+ faultCause + ", processMethod=" + processMethod
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	
}

