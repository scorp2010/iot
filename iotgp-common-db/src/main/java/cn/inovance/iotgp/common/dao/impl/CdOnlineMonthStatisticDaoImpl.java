/**
 * Project Name:iotgp-common-db
 * File Name:CdOnlineMonthStatisticDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2015-3-26上午9:56:25
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdOnlineMonthStatisticDao;
import cn.inovance.iotgp.common.domain.CdOnlineMonthStatistic;


/**
 * ClassName:CdOnlineMonthStatisticDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-26 上午9:56:25 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class CdOnlineMonthStatisticDaoImpl extends BaseDaoImpl< CdOnlineMonthStatistic> implements CdOnlineMonthStatisticDao {

	@Override
	public List<CdOnlineMonthStatistic> findByMonthAndCdRegCode(String month,
			String cdRegCode) {
		
		
		return null;
	}
		
}

