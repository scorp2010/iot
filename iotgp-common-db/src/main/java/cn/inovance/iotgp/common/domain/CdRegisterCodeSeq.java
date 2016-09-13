/**
 * Project Name:iotgp-common-db
 * File Name:CdRegisterCodeSeq.java
 * Package Name:cn.inovance.iotgp.hibernate.cds.domain
 * Date:2014-4-28上午8:56:14
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CdRegisterCodeSeq. 设备注册码自增seq Date: 2014-4-28 上午8:56:14 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Entity(name = "CdRegisterCodeSeq")
@Table(name = "t_cds_cd_register_code_seq")
public class CdRegisterCodeSeq implements java.io.Serializable {

	private java.lang.String id;

	private java.lang.Long cdRegisterCode;

	private java.lang.String type;
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
	 * Get value of regCode
	 * 
	 * @return regCode
	 */
	@Basic(optional = true)
	@Column(name = "reg_code_seq")
	public java.lang.Long getCdRegisterCode() {
		return cdRegisterCode;
	}

	/**
	 * Set value of regCode
	 * 
	 * @param newRegCode
	 */
	public void setCdRegisterCode(java.lang.Long cdRegisterCode) {
		this.cdRegisterCode = cdRegisterCode;
	}

	@Basic(optional = true)
	@Column(name = "seq_type",insertable = true, updatable = true,length = 20)
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}
	
}
