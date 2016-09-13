/**
 * Project Name:iotgp-common-db
 * File Name:CdOnlineDayStatisticDao.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2015-3-25上午9:59:48
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdOnlineDayStatisticDao;
import cn.inovance.iotgp.common.domain.CdOnlineDayStatistic;
import cn.inovance.iotgp.common.util.DateUtil;

/**
 * ClassName:CdOnlineDayStatisticDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午9:59:48 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class CdOnlineDayStatisticDaoImpl extends BaseDaoImpl<CdOnlineDayStatistic> implements CdOnlineDayStatisticDao {

	@Override
	public List<CdOnlineDayStatistic> findByMonthAndCdRegCode(Date date,
			String cdRegCode) {
		if( null == date ){
			return null;
		}
		String month = DateUtil.dateToString(date, "yyyy-MM");
		StringBuilder hql = new StringBuilder(200); 
		hql.append("from CdOnlineDayStatistic t ");
		hql.append("where 1=1 ");
		hql.append("and t.cdRegCode=:cdRegCode ");
		hql.append("and t.ownerMonth=:ownerMonth ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdRegCode", cdRegCode);
		params.put("ownerMonth", month);
		
		return find(hql.toString(), params);
	}

}

