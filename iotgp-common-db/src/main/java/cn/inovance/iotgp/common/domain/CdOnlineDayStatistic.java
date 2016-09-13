/**
 * Project Name:iotgp-common-db
 * File Name:CdOnlineStatistic.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-3-25上午8:39:27
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CdOnlineStatistic <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午8:39:27 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "CdOnlineDayStatistic")
@Table(name = "t_mms_cd_online_day_statistic",indexes ={ 
		@Index(name="multi_index",columnList= "cd_reg_code,online_day"),
		@Index(name="cd_reg_code_ownder_month_index",columnList= "cd_reg_code,owner_month"),
		@Index(name="online_day_index",columnList= "online_day"),
		@Index(name="online_rate_index",columnList= "online_rate_of_day"),
		}
)
public class CdOnlineDayStatistic  extends BaseCdOnlineStatistic{

	/**
	 * 日期
	 */
	private Date  onlineDay; 
	/**
	 * 月份
	 */
	private String  ownerMonth;
	/**
	 * 在线时长
	 */
	private Integer onlineTimeDuration;
	/**
	 * 在线率  = 时长/24（小时）
	 */
	private Float  onlineRateOfDay;

	
	@Basic(optional = true)
	@Temporal(TemporalType.DATE)
	@Column(name = "online_day", insertable = true, updatable = true)
	public Date getOnlineDay() {
		return onlineDay;
	}
	public void setOnlineDay(Date onlineDay) {
		this.onlineDay = onlineDay;
	}
	@Basic(optional = true)
	@Column(name = "online_time_duration", insertable = true)
	public Integer getOnlineTimeDuration() {
		return onlineTimeDuration;
	}
	public void setOnlineTimeDuration(Integer onlineTimeDuration) {
		this.onlineTimeDuration = onlineTimeDuration;
	}
	@Basic(optional = true)
	@Column(name = "online_rate_of_day", insertable = true)
	public Float getOnlineRateOfDay() {
		return onlineRateOfDay;
	}
	public void setOnlineRateOfDay(Float onlineRateOfDay) {
		this.onlineRateOfDay = onlineRateOfDay;
	}
	
	@Basic(optional = true)
	@Column(name = "owner_month", insertable = true,length = 10)
	public String getOwnerMonth() {
		return ownerMonth;
	}
	public void setOwnerMonth(String ownerMonth) {
		this.ownerMonth = ownerMonth;
	}
	
}

