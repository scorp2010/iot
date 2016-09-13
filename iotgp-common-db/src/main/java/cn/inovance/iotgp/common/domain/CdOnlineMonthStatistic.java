/**
 * Project Name:iotgp-common-db
 * File Name:CdOnlineStatistic.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-3-25上午8:39:27
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

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
 * ClassName:CdOnlineStatistic <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午8:39:27 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Entity(name = "CdOnlineMonthStatistic")
@Table(name = "t_mms_cd_online_month_statistic",indexes = { 
		@Index(name="multi_index",columnList= "cd_reg_code,online_month"),
		@Index(name="online_month_index",columnList= "online_month"),
		@Index(name="online_rate_index",columnList= "online_rate_of_month"),
	}
)
public class CdOnlineMonthStatistic extends BaseCdOnlineStatistic {

	/**
	 * 日期
	 */
	private String  onlineMonth; 
	/**
	 * 在线时长小时
	 */
	private Float onlineTimeDuration;
	/**
	 * 在线率  = 月总在线时长/24**Days（小时）
	 */
	private Float  onlineRateOfMonth;
	
	
	
	@Basic(optional = true)
	@Column(name = "online_month", insertable = true, updatable = true,length = 10)
	public String getOnlineMonth() {
		return onlineMonth;
	}
	public void setOnlineMonth(String onlineMonth) {
		this.onlineMonth = onlineMonth;
	}
	@Basic(optional = true)
	@Column(name = "online_time_duration", insertable = true,nullable = true)
	public Float getOnlineTimeDuration() {
		return onlineTimeDuration;
	}
	public void setOnlineTimeDuration(Float onlineTimeDuration) {
		this.onlineTimeDuration = onlineTimeDuration;
	}
	@Basic(optional = true)
	@Column(name = "online_rate_of_month", insertable = true)
	public Float getOnlineRateOfMonth() {
		return onlineRateOfMonth;
	}
	public void setOnlineRateOfMonth(Float onlineRateOfMonth) {
		this.onlineRateOfMonth = onlineRateOfMonth;
	}
	
	
}

