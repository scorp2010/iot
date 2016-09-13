/**
 * Project Name:iotgp-common-db
 * File Name:TdControllerBatchUpdateScope.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-3-6下午4:14:40
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:TdControllerBatchUpdateScope <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-6 下午4:14:40 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "TdSoftWareBatchUpdateScope")
@Table(name = "t_mms_td_software_batch_update_scope",indexes ={ @Index(name="td_software_batch_update_scope_index",columnList= "name")})
public class TdSoftWareBatchUpdateScope implements Serializable {
	/** 编号 */
	private String id;
	/** 软件批量更新名字 */
	private String name;
	/**
	 * 状态，记录是否创建job.
	 */
	private String status;
	public  static String NO_CREATE_UPDATE_JOB = "N"; 
	public  static String HAS_CREATE_UPDATE_JOB = "Y";
	/** 目标设备控制器名字 .*/
	private String tdctrollerName;
	/** 目标设备控制器 版本 .*/
	private String tdctrollerVersion;
	/** 目标设备控制器 ID .*/
	private String tdControllerId;
	/**别名*/
	private java.lang.String tdControllerNickName;
	/**默认控制器*/
	private java.lang.String defaultController;
	/** 目标设备产品型号名字 .*/
	private String tdProductModelName;
	/** 目标设备产品型号 版本 .*/
	private String tdProductModelVersion;
	/** 目标设备产品型号ID .*/
	private String tdProductModelId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 创建时间 */
	private String startCreateTime;
	/** 更新时间 */
	private String endCreateTime;
	
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}
	
	public void setId(String newId) {
		this.id = newId;
	}
	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Basic(optional = true)
	@Column(name = "status", insertable = true, updatable = true, length = 4)
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Basic(optional = true)
	@Column(name = "td_controller_id_fk", insertable = true, updatable = true, length = 50)
	public String getTdControllerId() {
		return tdControllerId;
	}

	public void setTdControllerId(String tdControllerId) {
		this.tdControllerId = tdControllerId;
	}

	@Basic(optional = true)
	@Column(name = "td_controller_name", insertable = true, updatable = true, length = 100)
	public String getTdctrollerName() {
		return tdctrollerName;
	}
	@Basic(optional = true)
	@Column(name = "td_controller_nick_name", insertable = true, updatable = true, length = 100)
	public java.lang.String getTdControllerNickName() {
		return tdControllerNickName;
	}

	public void setTdControllerNickName(java.lang.String tdControllerNickName) {
		this.tdControllerNickName = tdControllerNickName;
	}
	public void setTdctrollerName(String tdctrollerName) {
		this.tdctrollerName = tdctrollerName;
	}

	@Basic(optional = true)
	@Column(name = "td_controller_version", insertable = true, updatable = true, length = 50)
	public String getTdctrollerVersion() {
		return tdctrollerVersion;
	}

	public void setTdctrollerVersion(String tdctrollerVersion) {
		this.tdctrollerVersion = tdctrollerVersion;
	}
	@Basic(optional = true)
	@Column(name = "default_controller", insertable = true, updatable = true, nullable = true, length = 2)
	public java.lang.String getDefaultController() {
		return defaultController;
	}

	public void setDefaultController(java.lang.String defaultController) {
		this.defaultController = defaultController;
	}
	@Basic(optional = true)
	@Column(name = "td_product_model_name", insertable = true, updatable = true, length = 100)
	public String getTdProductModelName() {
		return tdProductModelName;
	}

	public void setTdProductModelName(String tdProductModelName) {
		this.tdProductModelName = tdProductModelName;
	}

	@Basic(optional = true)
	@Column(name = "td_product_model_version", insertable = true, updatable = true, length = 50)
	public String getTdProductModelVersion() {
		return tdProductModelVersion;
	}

	public void setTdProductModelVersion(String tdProductModelVersion) {
		this.tdProductModelVersion = tdProductModelVersion;
	}

	@Basic(optional = true)
	@Column(name = "td_product_model_id_fk", insertable = true, updatable = true, length = 50)
	public String getTdProductModelId() {
		return tdProductModelId;
	}

	public void setTdProductModelId(String tdProductModelId) {
		this.tdProductModelId = tdProductModelId;
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
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    @Transient
	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}
	@Transient
	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	
	
}

