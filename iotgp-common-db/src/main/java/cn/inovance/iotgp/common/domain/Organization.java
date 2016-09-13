package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * ClassName: Organization.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 */
@Entity
@Table(name = "t_sys_organization", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Organization extends BaseEntity implements java.io.Serializable {

	/**
	 * 虚拟属性，用于获得当前机构的父机构ID.
	 */
	private java.lang.String parentId;
	/**
	 * 创建时间.
	 */
	private java.util.Date createDatetime;
	/**
	 * 更新时间.
	 */
	private java.util.Date updateDatetime;
	/**
	 * 名称.
	 */
	private java.lang.String orgName;
	/**
	 * 地址.
	 */
	private java.lang.String address;
	/**
	 * 编码,唯一.
	 */
	private java.lang.String orgCode;
	/**
	 * 描述.
	 */
	private java.lang.String orgDesc;
	/**
	 * icon.
	 */
	private java.lang.String iconCls;
	/**
	 * 序列(保留).
	 */
	private Integer seq;
	/**
	 * 创建人编号.
	 */
	private java.lang.String createUserId;
	/**
	 * 级联.
	 */
	private Organization organization;
	/**
	 * set.
	 */
	private Set<Organization> organizationSet = new HashSet<Organization>(0);
	/**
	 * set.
	 */
	private Set<User> userSet = new HashSet<User>(0);
	/**
	 * set.
	 */
	private Set<Resource> resourceSet = new HashSet<Resource>(0);

	/**
	 * easyUI tree使用.
	 */
	private String state;

	@Column(name = "state", length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public Date getUpdateDatetime() {
		if (this.updateDatetime != null)
			return this.updateDatetime;
		return new Date();
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreateDatetime() {
		if (this.createDatetime != null)
			return this.createDatetime;
		return new Date();
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	@Column(name = "org_name", nullable = false, length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "org_code", length = 200)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "org_desc", length = 200)
	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	@Column(name = "iconcls", length = 200)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "req", precision = 8, scale = 0)
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "create_user_id", length = 36)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = CascadeType.ALL)
	public Set<Organization> getOrganizationSet() {
		return this.organizationSet;
	}

	public void setOrganizationSet(Set<Organization> organizationSet) {
		this.organizationSet = organizationSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_organization", schema = "", joinColumns = { @JoinColumn(name = "organization_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<User> getUserSet() {
		return this.userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_organization_resource", schema = "", joinColumns = { @JoinColumn(name = "organization_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) })
	public Set<Resource> getResourceSet() {
		return this.resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

	/**
	 * 用于业务逻辑的字段，注解@Transient代表不需要持久化到数据库中
	 * 
	 * @return
	 */
	@Transient
	public String getParentId() {
		if (organization != null && !StringUtils.isBlank(organization.getId())) {
			return organization.getId();
		}
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
