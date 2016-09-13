/**
r * Project Name:iotgp-common-db
 * File Name:Cd.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-5-26下午2:55:25
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.test.util.JsonPathExpectationsHelper;

import cn.inovance.iotgp.common.dao.CdRunStatusDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CdRunStatusLog;
/**
 * ClassName:CdRunStatusDaoImpl. Date: 2014-5-26 下午2:55:25 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CdRunStatusDaoImpl extends BaseDaoImpl<CdRunStatusLog> implements
		CdRunStatusDao {

	@Resource
	SuperBaseDao superBaseDao;
	@Override
	public List <CdRunStatusLog> getCdRunStatusLogByCdRegCode(String cdRegCode) {
		String hql = "from CdRunStatusLog t where 1=1 and t.cdRegCode=:cdRegCode ";
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("cdRegCode", cdRegCode);
		return find(hql, params);
	}

	@Override
	public List<CdRunStatusLog> getCdRunStatus(List<String> cdRegCodes) {
		String hql = "select t " +
				" from CdRunStatusLog t"+
				" where 1=1  " +
				" and t.cdRegCode in (:cdRegCodeList)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodes);
		List<CdRunStatusLog> logList = q.list();
		return logList;
	}

}
