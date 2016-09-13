/**
 * Project Name:iotgp-common-db
 * File Name:TdControllerDataCommandLibDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2015-1-15上午10:21:53
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerDataCommandLib;

/**
 * ClassName:TdControllerDataCommandLibDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-15 上午10:21:53 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface TdControllerDataCommandLibDao extends BaseDao<TdControllerDataCommandLib>  {

	public void deleteByControllerId(String controllerId);
	public List<TdControllerDataCommandLib> findByTdControllerId(String tdControllerId);
}

