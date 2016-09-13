/**
 * Project Name:cds
 * File Name:CdAllocationLogDao.java
 * Package Name:cn.inovance.iotgp.cds.dao
 * Date:2014-4-24上午9:06:14
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.CdAllocationLog;

/**
 * ClassName:CdAllocationLogDao
 * @author fb2112
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CdAllocationLogDao extends BaseDao<CdAllocationLog>{
	
	
	public List<CdAllocationLog> getByCdRegCodeList(List<String> cdRegCodeList);
	
	public int saveAll(List<CdAllocationLog> cdAllocationLogList);
	
	public int updateAll(List<CdAllocationLog> cdAllocationLogList);
	
}
