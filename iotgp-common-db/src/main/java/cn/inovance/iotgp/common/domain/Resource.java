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
 * ClassName: Resource. date: 2014-4-9 上午11:31:46
 * 
 * @author w1898
 * @version 1.0
 * @since JDK 1.7
 */
@Entity
@Table(name = "t_sys_resource", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Resource extends BaseEntity implements java.io.Serializable {

	/**
	 * 虚拟属性，用于获得当前资源的父资源ID.
	 */
	private java.lang.String parentId;
	/**
	 * 创建日期.
	 */
	private java.util.Date createDatetime;
	/**
	 * 更新日期.
	 */
	private java.util.Date updateDatetime;
	/**
	 * 资源code(唯一).
	 */
	private java.lang.String resCode;
	/**
	 * 资源名称.
	 */
	private java.lang.String resName;
	/**
	 * 资源url.
	 */
	private java.lang.String resUrl;
	/**
	 * 资源描述.
	 */
	private java.lang.String resDesc;
	/**
	 * 资源icon.
	 */
	private java.lang.String iconCls;
	/**
	 * 顺序号.
	 */
	private Integer seq;
	/**
	 * 备注.
	 */
	private java.lang.String remark;
	/**
	 * 是否预留.
	 */
	private boolean reserveFlag;
	/**
	 * 大图片路径.
	 **/
	private java.lang.String bigPicUrl;
	/**
	 * 中图片路径.
	 * */
	private java.lang.String midPicUrl;
	/**
	 * 小图片路径.
	 **/
	private java.lang.String smallPicUrl;
	/**
	 * 目标,(预留).
	 * */
	private java.lang.String target;
	/**
	 * 创建人编号.
	 */
	private java.lang.String createUserId;
	/**
	 * 资源类型.
	 */
	private ResourceType resourceType;
	/**
	 * 级联.
	 */
	private Resource resource;
	/**
	 * 角色.
	 */
	private Set<Role> roleSet = new HashSet<Role>(0);
	/**
	 * 机构组织.
	 */
	private Set<Organization> organizationSet = new HashSet<Organization>(0);

	/**
	 * easyUI tree使用.
	 */
	private String state;

	/**
	 * 资源集合.
	 */
	private Set<Resource> resourceSet = new HashSet<Resource>(0);

	@Column(name = "state", length = 10)
	public String getState() {
		return this.state;

	}

	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_type_id")
	public ResourceType getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

	@Column(name = "res_code", length = 100)
	public String getResCode() {
		return this.resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	@Column(name = "res_name", nullable = false, length = 100)
	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	@Column(name = "res_url", length = 200)
	public String getResUrl() {
		return this.resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	@Column(name = "res_desc", length = 200)
	public String getResDesc() {
		return this.resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	@Column(name = "iconcls", length = 200)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "seq", precision = 8, scale = 0)
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "remark", length = 200)
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "reserve_flag")
	public java.lang.Boolean getReserveFlag() {
		return this.reserveFlag;
	}

	public void setReserveFlag(Boolean reserveFlag) {
		this.reserveFlag = reserveFlag;
	}

	@Column(name = "big_pic_url", length = 200)
	public java.lang.String getBigPicUrl() {
		return this.bigPicUrl;
	}

	public void setBigPicUrl(String bigPicUrl) {
		this.bigPicUrl = bigPicUrl;
	}

	@Column(name = "mid_pic_url", length = 200)
	public java.lang.String getMidPicUrl() {
		return this.midPicUrl;
	}

	public void setMidPicUrl(String midPicUrl) {
		this.midPicUrl = midPicUrl;
	}

	@Column(name = "small_pic_url", length = 200)
	public java.lang.String getSmallPicUrl() {
		return this.smallPicUrl;
	}

	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}

	@Column(name = "target", length = 100)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "create_user_id", length = 36)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_role_resource", schema = "", joinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<Role> getRoleSet() {
		return this.roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_organization_resource", schema = "", joinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "organization_id", nullable = false, updatable = false) })
	public Set<Organization> getOrganizationSet() {
		return this.organizationSet;
	}

	public void setOrganizationSet(Set<Organization> organizationSet) {
		this.organizationSet = organizationSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource", cascade = CascadeType.ALL)
	public Set<Resource> getResourceSet() {
		return this.resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Transient
	public String getParentId() {
		if (resource != null && !StringUtils.isBlank(resource.getId())) {
			return resource.getId();
		}
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
