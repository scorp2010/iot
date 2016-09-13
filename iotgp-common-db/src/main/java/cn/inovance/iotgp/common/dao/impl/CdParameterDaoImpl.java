/**
 * Project Name:iotgp-common-db
 * File Name:CdParameterDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-5-23上午11:38:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdParameterDao;
import cn.inovance.iotgp.common.domain.CdParameter;

/**
 * ClassName:CdParameterDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-23 上午11:38:45 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CdParameterDaoImpl extends BaseDaoImpl<CdParameter> implements
		CdParameterDao {

	@Override
	public CdParameter getByCdRegCode(String cdRegCode) {

		String hql = "select distinct t from CdParameter t where t.cdRegCode = '"
				+ cdRegCode + "'";
		return getByHql(hql);
	}

}
