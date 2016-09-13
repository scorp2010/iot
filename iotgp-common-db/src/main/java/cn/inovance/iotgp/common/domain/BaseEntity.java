/**
 * Project Name:bms
 * File Name:BaseDomain.java
 * Package Name:cn.inovance.iotgp.hibernate.domain
 * Date:2014-4-8下午6:46:20
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName:BaseEntity. 持久化类基类
 * 
 * @author w1898
 * @since JDK 1.7
 */
@MappedSuperclass
public abstract class BaseEntity implements java.io.Serializable {

	/**
	 * 编号.
	 */
	private java.lang.String id;
	/**
	 * 状态.
	 */
	private java.lang.Short status;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "status")
	public java.lang.Short getStatus() {
		if (null != this.status) {
			return this.status;
		} else {
			return new Short((short) 0);
		}
	}

	public void setStatus(java.lang.Short status) {
		this.status = status;
	}

}
