/**
 * Project Name:iotgp-common-db
 * File Name:CdParameterDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-5-23上午11:38:20
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import cn.inovance.iotgp.common.domain.CdParameter;

/**
 * ClassName:CdParameterDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-23 上午11:38:20 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CdParameterDao extends BaseDao<CdParameter> {

	public CdParameter getByCdRegCode(String cdRegCode);
}
