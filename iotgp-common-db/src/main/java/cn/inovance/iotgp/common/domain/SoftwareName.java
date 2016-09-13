/***********************************************************************
 * Module:  SoftwareName.java
 * Author:  lk2200
 * Purpose: Defines the Class SoftwareName
 ***********************************************************************/

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 软件名称信息
 * 
 */
@Entity(name = "SoftwareName")
@Table(name = "t_mms_software_name",indexes ={ @Index(name="software_name_index",columnList= "name") })

public class SoftwareName implements java.io.Serializable {

	private static final long serialVersionUID = 4320706714110319858L;
	/** 编号 */
	private String id;
	/** 软件名称 */
	private String name;
	/** 用户程序类型 */
	private String userProgramType;
	/** 厂家编号 */
	private String factory;
	private String factoryName;
	/** 行业 */
	private String customerIndustry;
	private String customerIndustryName;
	/** 控制器型号编号 */
	private String controllerTypeNumber;
	/**软件类型（1：单板软件，2：用户程序） */
	private String softType;
	
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private Date   createTime;
	/** 修改时间 */
	private Date   updateTime;
	
	/** 查询新增字段 */
	private String sdate;
	private String edate;

	public SoftwareName() {
	}
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

	public void setName(String newName) {
		this.name = newName;
	}
	
	@Basic(optional = true)
	@Column(name = "user_program_type", insertable = true, updatable = true, length = 100)
	public String getUserProgramType() {
		return userProgramType;
	}
	public void setUserProgramType(String userProgramType) {
		this.userProgramType = userProgramType;
	}
	@Basic(optional = true)
	@Column(name = "factory", insertable = true, updatable = true, length = 20)
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	@Basic(optional = true)
	@Column(name = "customer_industry", insertable = true, updatable = true, length = 20)
	public String getCustomerIndustry() {
		return customerIndustry;
	}
	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}
	@Basic(optional = true)
	@Column(name = "controller_type_number", insertable = true, updatable = true, length = 20)
	public String getControllerTypeNumber() {
		return controllerTypeNumber;
	}
	public void setControllerTypeNumber(String controllerTypeNumber) {
		this.controllerTypeNumber = controllerTypeNumber;
	}
	@Basic(optional = true)
	@Column(name = "soft_type", insertable = true, updatable = true, length = 10)
	public String getSoftType() {
		return softType;
	}
	public void setSoftType(String softType) {
		this.softType = softType;
	}
	@Basic(optional = true)
	@Column(name = "creator", insertable = true, updatable = true, length = 50)
	public String getCreator() {
		return creator;
	}

	public void setCreator(String newCreator) {
		this.creator = newCreator;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
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
	public String getCustomerIndustryName() {
		return customerIndustryName;
	}
	public void setCustomerIndustryName(String customerIndustryName) {
		this.customerIndustryName = customerIndustryName;
	}
	@Transient
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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
	
	@Override
	public String toString() {
		return "SoftwareInfo [id=" + id + ", name=" + name + ", userProgramType="
				+ userProgramType + ", customerIndustry=" + customerIndustry + ", controllerTypeNumber="
				+ controllerTypeNumber + ", factory=" + factory
				+ ", creator=" + creator + ", createTime=" + createTime
			    + ", sdate=" + sdate + ", edate="
				+ edate + "]";
	}

	
}