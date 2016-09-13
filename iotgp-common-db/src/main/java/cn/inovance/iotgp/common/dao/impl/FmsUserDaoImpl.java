/**
 * Project Name:iotgp-common-db
 * File Name:CdOperationDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-5-26上午11:44:20
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdOperationDao;
import cn.inovance.iotgp.common.dao.FmsUserDao;
import cn.inovance.iotgp.common.domain.CdOperationLog;
import cn.inovance.iotgp.common.domain.FmsUser;
import cn.inovance.iotgp.common.filter.HqlFilter;

/**
 * ClassName:CdOperationDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-26 上午11:44:20 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class FmsUserDaoImpl extends BaseDaoImpl<FmsUser> implements
			FmsUserDao {

	@Override
	public FmsUser findFmsUserByCondition(FmsUser fmsUser) {

		HqlFilter hqlFilter = new HqlFilter();
		if(StringUtils.isNotBlank(fmsUser.getUserid())){
			hqlFilter.addFilter("QUERY_t#userid_S_EQ", fmsUser.getUserid());
		}
		List<FmsUser> list = findByFilter(hqlFilter);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
