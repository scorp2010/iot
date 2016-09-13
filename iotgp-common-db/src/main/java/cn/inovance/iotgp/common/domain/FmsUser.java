/***********************************************************************
 * Module:  FmsUser.java
 * Author:  z1979
 * Purpose: Defines the Class FmsUser
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 文件系统用户
 * 
 * @pdOid ff5d7390-1df0-488f-a105-1893e5f09d97
 */
@Entity(name = "FmsUser")
@Table(name = "t_fms_user",indexes ={ @Index(name="userid_index",columnList= "userid")  })
public class FmsUser implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid dfd5371b-e656-4150-9bb9-253053c032c5
	 */
	private java.lang.String id;
	/**
	 * 用户帐号 50
	 * 
	 * @pdOid cd30549c-7d38-4077-9aff-b291ce5b0434
	 */
	private java.lang.String userid;
	/**
	 * 原始密码
	 * 
	 * @pdOid 0d55216e-3abb-44fd-a0ce-22490937d7e2
	 */
	private java.lang.String originalpassword;
	/**
	 * 密码明文的md5，10字节
	 * 
	 * @pdOid 0047f78b-d301-45e8-8645-eb22eea69bd3
	 */
	private java.lang.String userpassword;
	/**
	 * 写权限
	 * 
	 * @pdOid 0d4697ee-3d2d-494c-8b74-f5b3597ee916
	 */
	private java.lang.String writepermission;
	/**
	 * 用户根目录
	 * 
	 * @pdOid 1be03071-37a5-4324-8ef3-287e2dec789c
	 */
	private java.lang.String homedirectory;
	/** @pdOid dd41c84f-51c0-4ac5-898d-e54f161caf7a */
	private java.lang.Integer idletime;
	/** @pdOid 1fec1c52-39cd-4931-a691-565ff7b37ac1 */
	private java.lang.Integer maxloginnumber;
	/** @pdOid 2eedc289-f432-4e20-a7e1-33d55ebeaa84 */
	private java.lang.Integer maxloginperip;
	/** @pdOid bb42b7b1-64a9-4271-acfc-304a5c308b3f */
	private java.lang.Integer uploadrate;
	/** @pdOid 0067f03d-482f-44ca-987d-28a5e8b1815f */
	private java.lang.Integer downloadrate;
	/** @pdOid 16148ef2-2925-4fcf-8f58-4c0afbceb883 */
	private java.lang.String enableflag;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public FmsUser() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	/**
	 * Get value of userid
	 * 
	 * @return userid
	 */
	@Basic(optional = true)
	@Column(name = "userid", insertable = true, updatable = true, length = 100)
	public java.lang.String getUserid() {
		return userid;
	}

	/**
	 * Set value of userid
	 * 
	 * @param newUserid
	 */
	public void setUserid(java.lang.String newUserid) {
		this.userid = newUserid;
	}

	/**
	 * Get value of originalpassword
	 * 
	 * @return originalpassword
	 */
	@Basic(optional = true)
	@Column(name = "originalpassword", insertable = true, updatable = true, length = 100)
	public java.lang.String getOriginalpassword() {
		return originalpassword;
	}

	/**
	 * Set value of originalpassword
	 * 
	 * @param newOriginalpassword
	 */
	public void setOriginalpassword(java.lang.String newOriginalpassword) {
		this.originalpassword = newOriginalpassword;
	}

	/**
	 * Get value of userpassword
	 * 
	 * @return userpassword
	 */
	@Basic(optional = true)
	@Column(name = "userpassword", insertable = true, updatable = true, length = 32)
	public java.lang.String getUserpassword() {
		return userpassword;
	}

	/**
	 * Set value of userpassword
	 * 
	 * @param newUserpassword
	 */
	public void setUserpassword(java.lang.String newUserpassword) {
		this.userpassword = newUserpassword;
	}

	/**
	 * Get value of writepermission
	 * 
	 * @return writepermission
	 */
	@Basic(optional = true)
	@Column(name = "writepermission", insertable = true, updatable = true, length = 1)
	public java.lang.String getWritepermission() {
		return writepermission;
	}

	/**
	 * Set value of writepermission
	 * 
	 * @param newWritepermission
	 */
	public void setWritepermission(java.lang.String newWritepermission) {
		this.writepermission = newWritepermission;
	}

	/**
	 * Get value of homedirectory
	 * 
	 * @return homedirectory
	 */
	@Basic(optional = true)
	@Column(name = "homedirectory", insertable = true, updatable = true, length = 512)
	public java.lang.String getHomedirectory() {
		return homedirectory;
	}

	/**
	 * Set value of homedirectory
	 * 
	 * @param newHomedirectory
	 */
	public void setHomedirectory(java.lang.String newHomedirectory) {
		this.homedirectory = newHomedirectory;
	}

	/**
	 * Get value of idletime
	 * 
	 * @return idletime
	 */
	@Basic(optional = true)
	@Column(name = "idletime", insertable = true, updatable = true)
	public java.lang.Integer getIdletime() {
		return idletime;
	}

	/**
	 * Set value of idletime
	 * 
	 * @param newIdletime
	 */
	public void setIdletime(java.lang.Integer newIdletime) {
		this.idletime = newIdletime;
	}

	/**
	 * Get value of maxloginnumber
	 * 
	 * @return maxloginnumber
	 */
	@Basic(optional = true)
	@Column(name = "maxloginnumber", insertable = true, updatable = true)
	public java.lang.Integer getMaxloginnumber() {
		return maxloginnumber;
	}

	/**
	 * Set value of maxloginnumber
	 * 
	 * @param newMaxloginnumber
	 */
	public void setMaxloginnumber(java.lang.Integer newMaxloginnumber) {
		this.maxloginnumber = newMaxloginnumber;
	}

	/**
	 * Get value of maxloginperip
	 * 
	 * @return maxloginperip
	 */
	@Basic(optional = true)
	@Column(name = "maxloginperip", insertable = true, updatable = true)
	public java.lang.Integer getMaxloginperip() {
		return maxloginperip;
	}

	/**
	 * Set value of maxloginperip
	 * 
	 * @param newMaxloginperip
	 */
	public void setMaxloginperip(java.lang.Integer newMaxloginperip) {
		this.maxloginperip = newMaxloginperip;
	}

	/**
	 * Get value of uploadrate
	 * 
	 * @return uploadrate
	 */
	@Basic(optional = true)
	@Column(name = "uploadrate", insertable = true, updatable = true)
	public java.lang.Integer getUploadrate() {
		return uploadrate;
	}

	/**
	 * Set value of uploadrate
	 * 
	 * @param newUploadrate
	 */
	public void setUploadrate(java.lang.Integer newUploadrate) {
		this.uploadrate = newUploadrate;
	}

	/**
	 * Get value of downloadrate
	 * 
	 * @return downloadrate
	 */
	@Basic(optional = true)
	@Column(name = "downloadrate", insertable = true, updatable = true)
	public java.lang.Integer getDownloadrate() {
		return downloadrate;
	}

	/**
	 * Set value of downloadrate
	 * 
	 * @param newDownloadrate
	 */
	public void setDownloadrate(java.lang.Integer newDownloadrate) {
		this.downloadrate = newDownloadrate;
	}

	/**
	 * Get value of enableflag
	 * 
	 * @return enableflag
	 */
	@Basic(optional = true)
	@Column(name = "enableflag", insertable = true, updatable = true, length = 1)
	public java.lang.String getEnableflag() {
		return enableflag;
	}

	/**
	 * Set value of enableflag
	 * 
	 * @param newEnableflag
	 */
	public void setEnableflag(java.lang.String newEnableflag) {
		this.enableflag = newEnableflag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (!(other instanceof FmsUser))
			return false;

		FmsUser cast = (FmsUser) other;

		if (!this.getId().equals(cast.getId()))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		if (this.userid != null)
			hashCode = 29 * hashCode + userid.hashCode();
		if (this.originalpassword != null)
			hashCode = 29 * hashCode + originalpassword.hashCode();
		if (this.userpassword != null)
			hashCode = 29 * hashCode + userpassword.hashCode();
		if (this.writepermission != null)
			hashCode = 29 * hashCode + writepermission.hashCode();
		if (this.homedirectory != null)
			hashCode = 29 * hashCode + homedirectory.hashCode();
		if (this.idletime != null)
			hashCode = 29 * hashCode + idletime.hashCode();
		if (this.maxloginnumber != null)
			hashCode = 29 * hashCode + maxloginnumber.hashCode();
		if (this.maxloginperip != null)
			hashCode = 29 * hashCode + maxloginperip.hashCode();
		if (this.uploadrate != null)
			hashCode = 29 * hashCode + uploadrate.hashCode();
		if (this.downloadrate != null)
			hashCode = 29 * hashCode + downloadrate.hashCode();
		if (this.enableflag != null)
			hashCode = 29 * hashCode + enableflag.hashCode();
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
		ret.append("cn.inovance.iotgp.fms.FmsUser: ");
		ret.append("id='" + id + "'");
		ret.append("userid='" + userid + "'");
		ret.append("originalpassword='" + originalpassword + "'");
		ret.append("userpassword='" + userpassword + "'");
		ret.append("writepermission='" + writepermission + "'");
		ret.append("homedirectory='" + homedirectory + "'");
		ret.append("idletime='" + idletime + "'");
		ret.append("maxloginnumber='" + maxloginnumber + "'");
		ret.append("maxloginperip='" + maxloginperip + "'");
		ret.append("uploadrate='" + uploadrate + "'");
		ret.append("downloadrate='" + downloadrate + "'");
		ret.append("enableflag='" + enableflag + "'");
		return ret.toString();
	}

}