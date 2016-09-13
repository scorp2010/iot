/**
s * Project Name:iotgp-common-db
 * File Name:BaseCdOnline.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-3-27上午10:17:29
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.beans.Transient;

import javax.annotation.Resource;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:BaseCdOnline <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-27 上午10:17:29 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@MappedSuperclass
public class BaseCdOnlineStatistic implements java.io.Serializable{

	/**
	 *  编号.
	 */
	private String id;
	/**
	 * 注册码.
	 */
	private String  cdRegCode;
	/**
	 * 掉线次数
	 */
	private Integer  offLineTimes;
	
	/**
	 * 查询是按天（day），还是按月（month）
	 */
	private String method;
	
	public  static final String  METHOD_DAY = "d";
	public  static final String  METHOD_MONTH = "m";

	private String startDate;
	
	private String endDate;
	private String onlineRate;
	
	private String customerName;
	
	private String productSn;
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 36)
	public String getCdRegCode() {
		return cdRegCode;
	}
	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}
	
	@Basic(optional = true)
	@Column(name = "offline_times", insertable = true)
	public Integer getOffLineTimes() {
		return offLineTimes;
	}
	public void setOffLineTimes(Integer offLineTimes) {
		this.offLineTimes = offLineTimes;
	}
	@Transient
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Transient
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Transient
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Transient
	public String getOnlineRate() {
		return onlineRate;
	}
	public void setOnlineRate(String onlineRate) {
		this.onlineRate = onlineRate;
	}
	@Basic(optional = true)
	@Column(name = "customer_name", insertable = true, length = 50)
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Column(name = "product_sn", insertable = true,length = 32)
	public String getProductSn() {
		return productSn;
	}
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
   
}

