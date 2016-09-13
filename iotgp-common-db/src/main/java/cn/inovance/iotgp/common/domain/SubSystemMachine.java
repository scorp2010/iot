/***********************************************************************
 * Module:  SubSystem.java
 * Author:  l2200
 * Purpose: Defines the Class SubSystemMachine
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


/**
 * 子系统机器
 * 
 */
@Entity(name = "SubSystemMachine")
@Table(name = "t_cds_sub_system_machine")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SubSystemMachine implements java.io.Serializable {

	private static final long serialVersionUID = 4725843586356720767L;
	/** 编号 */
	private String id;
	/** 子系统机器编码 */
	private String code;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 私有服务IP */
	private String privateHost;
	/** 服务登录用户 */
	private String sshUser;
	/** 服务登录密码 */
	private String sshPassword;
	/** 服务端口 */
	private Integer sshPort;
	/** 公共服务IP */
	private String publicHost;
	/** 服务存放地址 */
	private String jazminHome;
	/** 服务机器存放地址 */
	private String memcachedHome;
	private String haproxyHome;
	private String redisHome;
	private String mongodbHome;
	/** 描述信息 */
	private String description;
	
	
	public SubSystemMachine() {
	}

	
	/*@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)*/
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String newId) {
		this.id = newId;
	}

	@Basic(optional = true)
	@Column(name = "code", insertable = true, updatable = true, length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String newCode) {
		this.code = newCode;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreateTime() {
		if(createTime != null ){
			return createTime;
		}else {
			return new Date();
		}
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public Date getUpdateTime() {
		if(updateTime != null){
			return updateTime;
		}else{
			return new Date();
		}
	}

	public void setUpdateTime(Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	@Basic(optional = true)
	@Column(name = "private_host", insertable = true, updatable = true, length = 50)
	public String getPrivateHost() {
		return privateHost;
	}

	public void setPrivateHost(String privateHost) {
		this.privateHost = privateHost;
	}

	@Basic(optional = true)
	@Column(name = "ssh_user", insertable = true, updatable = true, length = 50)
	public String getSshUser() {
		return sshUser;
	}


	public void setSshUser(String sshUser) {
		this.sshUser = sshUser;
	}


	@Basic(optional = true)
	@Column(name = "ssh_password", insertable = true, updatable = true, length = 50)
	public String getSshPassword() {
		return sshPassword;
	}


	public void setSshPassword(String sshPassword) {
		this.sshPassword = sshPassword;
	}

	@Basic(optional = true)
	@Column(name = "ssh_port", insertable = true, updatable = true, length = 50)
	public Integer getSshPort() {
		return sshPort;
	}


	public void setSshPort(Integer sshPort) {
		this.sshPort = sshPort;
	}

	@Basic(optional = true)
	@Column(name = "public_host", insertable = true, updatable = true, length = 50)
	public String getPublicHost() {
		return publicHost;
	}


	public void setPublicHost(String publicHost) {
		this.publicHost = publicHost;
	}

	@Basic(optional = true)
	@Column(name = "jazmin_home", insertable = true, updatable = true, length = 100)
	public String getJazminHome() {
		return jazminHome;
	}


	public void setJazminHome(String jazminHome) {
		this.jazminHome = jazminHome;
	}

	@Basic(optional = true)
	@Column(name = "memcached_home", insertable = true, updatable = true, length = 100)
	public String getMemcachedHome() {
		return memcachedHome;
	}


	public void setMemcachedHome(String memcachedHome) {
		this.memcachedHome = memcachedHome;
	}

	@Basic(optional = true)
	@Column(name = "haproxy_home", insertable = true, updatable = true, length = 100)
	public String getHaproxyHome() {
		return haproxyHome;
	}


	public void setHaproxyHome(String haproxyHome) {
		this.haproxyHome = haproxyHome;
	}

	@Basic(optional = true)
	@Column(name = "redis_home", insertable = true, updatable = true, length = 100)
	public String getRedisHome() {
		return redisHome;
	}


	public void setRedisHome(String redisHome) {
		this.redisHome = redisHome;
	}

	@Basic(optional = true)
	@Column(name = "mongodb_home", insertable = true, updatable = true, length = 100)
	public String getMongodbHome() {
		return mongodbHome;
	}


	public void setMongodbHome(String mongodbHome) {
		this.mongodbHome = mongodbHome;
	}

	@Basic(optional = true)
	@Column(name = "description", insertable = true, updatable = true, length = 500)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
}