/**
 * Project Name:iotgp-common-db
 * File Name:CdOnlineMonthStatistic.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2015-3-26上午9:55:44
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import javax.swing.ListModel;

import cn.inovance.iotgp.common.domain.CdOnlineMonthStatistic;

/**
 * ClassName:CdOnlineMonthStatistic <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-26 上午9:55:44 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface CdOnlineMonthStatisticDao extends BaseDao<CdOnlineMonthStatistic>{
	
	/**
	 * 根据指定月份和注册码查询
	 * @author w1898
	 * @param month
	 * @param cdRegCode
	 * @return
	 * @since JDK 1.7
	 */
	public List<CdOnlineMonthStatistic> findByMonthAndCdRegCode(String month,String cdRegCode);
}

