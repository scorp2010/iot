/**
 * Project Name:iotgp-common-db
 * File Name:CdRunStatusDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-5-26下午2:55:09
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.CdRunStatusLog;

/**
 * ClassName:CdRunStatusDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-26 下午2:55:09 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CdRunStatusDao  extends BaseDao<CdRunStatusLog> {

	public List<CdRunStatusLog> getCdRunStatusLogByCdRegCode(String cdRegCode);
	List<CdRunStatusLog>  getCdRunStatus(List<String> cdRegCodes);
}
