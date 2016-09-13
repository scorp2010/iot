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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdOnlineDayStatisticDao;
import cn.inovance.iotgp.common.dao.CdOnlineDayStatisticStatusDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CdOnlineDayStatistic;
import cn.inovance.iotgp.common.domain.DeviceLastLoginStatus;
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
public class CdOnlineDayStatisticStatusDaoImpl extends BaseDaoImpl<DeviceLastLoginStatus> implements CdOnlineDayStatisticStatusDao {

	@Autowired
	private SuperBaseDao superBaseDao;
	
	@Override
	public List<DeviceLastLoginStatus> findStatusByCdRegCode(String cdRegCode) {
		StringBuilder hql = new StringBuilder(200); 
		hql.append("from DeviceLastLoginStatus t ");
		hql.append("where 1=1 ");
		hql.append("and t.cdRegCode=:cdRegCode ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdRegCode", cdRegCode);
		
		return find(hql.toString(), params);
	}
	
	@Override
	public int updateStatus(DeviceLastLoginStatus deviceLastLoginStatus) {
		
		if(StringUtils.isNotBlank(deviceLastLoginStatus.getCdRegCode())){
			
			String hql = "update DeviceLastLoginStatus t set t.onlineStauts =:onlineStauts,t.lastTime =:lastTime,t.updateTime =:updateTime " +
					" where t.cdRegCode=:cdRegCode";
			Query q = superBaseDao.getCurrentSession().createQuery(hql);
			q.setParameter("onlineStauts", deviceLastLoginStatus.getOnlineStauts());
			q.setParameter("lastTime", deviceLastLoginStatus.getLastTime());
			q.setParameter("updateTime", deviceLastLoginStatus.getUpdateTime());
			q.setParameter("cdRegCode", deviceLastLoginStatus.getCdRegCode());
			return q.executeUpdate();
			
		}
		return 0;
	}

}

