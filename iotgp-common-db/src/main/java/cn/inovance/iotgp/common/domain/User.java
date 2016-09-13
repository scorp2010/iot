package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.inovance.iotgp.common.util.DomainStatusUtil;

/**
 * ClassName: User. date: 2014-4-9 上午9:54:04
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 */
@Entity
@Table(name = "t_sys_user", schema = "",indexes ={ @Index(name="sys_user_index",columnList= "login_account"),@Index(name="sys_user_name_index",columnList= "user_name")  })
@DynamicInsert(true)
@DynamicUpdate(true)
public class User extends BaseEntity implements java.io.Serializable {

	/**
	 * 登录ip地址 此属性不存数据库，虚拟属性.
	 */
	private String ip;
	/**
	 * 虚拟属性,用于获得当前用户的父帐号.
	 */
	private java.lang.String parentId;
	/**
	 * 用户名称.
	 */
	private java.lang.String userName;
	/**
	 * 登录账户,唯一.
	 */
	private java.lang.String loginAccount;
	/**
	 * 登陆密码.
	 */
	private java.lang.String password;
	private java.lang.String passwordConfirm;
	/**
	 * 联系电话.
	 */
	private java.lang.String linkPhone;
	/**
	 * 邮箱.
	 */
	private java.lang.String email;
	/**
	 * 创建人编号.
	 */
	private java.lang.String createUserId;
	/**
	 * 创建日期.
	 */
	private java.util.Date createDatetime;
	/**
	 * 更新日期.
	 */
	private java.util.Date updateDatetime;
	/**
	 * 照片地址.
	 */
	private java.lang.String photo;
	/**
	 * 级联.
	 */
	private User user;
	/**
	 * 组织机构.
	 */
	private Set<Organization> organizationSet = new HashSet<Organization>(0);
	/**
	 * 角色.
	 */
	private Set<Role> roleSet = new HashSet<Role>(0);
	/**
	 * user.
	 */
	private Set<User> userSet = new HashSet<User>(0);

	/**
	 * easyUI tree使用.
	 */
	private String state;

	/**
	 * 1:系统账户
	 * 2：客户账户
	 */
	private String identity;
	
	/**
	 * 关联的客户信息，（一个客户对应一个帐号）.
	 */
	private CustomerInfo customerInfo;

	

	@Column(name = "state", nullable = false, length = 10)
	public String getState() {
		if (null == this.state || this.state.isEmpty()) {
			return DomainStatusUtil.DOMIAN_STATE_OPEN;
		} else {
			return this.state;
		}
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "user_name", nullable = false, length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_info_id_fk", referencedColumnName = "id", nullable = true)
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
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

	@Column(name = "login_account", unique = true, nullable = false, length = 50)
	public String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "link_phone", length = 20)
	public String getLinkPhone() {
		return this.linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "create_user_id", length = 36)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "photo", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "parent_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_organization", schema = "", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "organization_id", nullable = false, updatable = false) })
	public Set<Organization> getOrganizationSet() {
		return this.organizationSet;
	}

	public void setOrganizationSet(Set<Organization> organizationSet) {
		this.organizationSet = organizationSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_role", schema = "", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<Role> getRoleSet() {
		return this.roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Transient
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {
			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	public Set<User> getUserSet() {
		return this.userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	@Transient
	public String getParentId() {
		if (user != null && !StringUtils.isBlank(user.getId())) {
			return user.getId();
		}
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name = "identity",columnDefinition="varchar(5) default 1")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	@Transient
	public java.lang.String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(java.lang.String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
}
