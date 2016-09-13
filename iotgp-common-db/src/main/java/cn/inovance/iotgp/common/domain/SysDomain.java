/***********************************************************************
 * Module:  SysDomain.java
 * Author:  w1898
 * Purpose: Defines the Class SysDomain
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.inovance.iotgp.common.uuid.UUIDGenerator;

/**
 * 系统域
 * 
 */
@Entity(name = "SysDomain")
@Table(name = "t_cds_sys_domain",indexes ={ @Index(name="sys_domain_index",columnList= "name")  })
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDomain implements java.io.Serializable {
	private static final long serialVersionUID = 4723019839781402486L;
	/** 编号 */
	private String id;
	/** 域名 */
	private String name;
	/** 内部编码 */
	private String innnerCode;
	/** 描述 */
	private String describe;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新时间 */
	private java.util.Date updateTime;

	private Set<SubSystem> subSystemList = new HashSet<SubSystem>(0);

	private String sdate;
	private String edate;

	public SysDomain() {
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysDomain")
	public Set<SubSystem> getSubSystemList() {
		return subSystemList;
	}

	public void setSubSystemList(Set<SubSystem> newSubSystemList) {
		this.subSystemList = newSubSystemList;
	}

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUIDGenerator.generate();
	}

	public void setId(String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	@Basic(optional = true)
	@Column(name = "innner_code", insertable = true, updatable = true, length = 50)
	public String getInnnerCode() {
		return innnerCode;
	}

	public void setInnnerCode(String newInnnerCode) {
		this.innnerCode = newInnnerCode;
	}

	@Basic(optional = true)
	@Column(name = "domain_desc", insertable = true, updatable = true, length = 100)
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String newDescribe) {
		this.describe = newDescribe;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public java.util.Date getCreateTime() {
		if(createTime == null){
			return new Date();
		}
		return createTime;
	}

	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public java.util.Date getUpdateTime() {
		if(null == updateTime){
			return new Date();
		}
		return updateTime;
	}

	public void setUpdateTime(java.util.Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	@Transient
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	@Transient
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

}