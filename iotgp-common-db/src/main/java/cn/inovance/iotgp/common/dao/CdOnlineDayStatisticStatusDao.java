/**
 * Project Name:iotgp-common-db
 * File Name:CdOnlineDayStatisticDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2015-3-25上午9:59:37
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.Date;
import java.util.List;

import cn.inovance.iotgp.common.domain.CdOnlineDayStatistic;
import cn.inovance.iotgp.common.domain.DeviceLastLoginStatus;

/**
 * ClassName:CdOnlineDayStatisticDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午9:59:37 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface CdOnlineDayStatisticStatusDao  extends BaseDao<DeviceLastLoginStatus>{
	public List<DeviceLastLoginStatus> findStatusByCdRegCode(String cdRegCode);
	
	public int updateStatus(DeviceLastLoginStatus deviceLastLoginStatus);
}

