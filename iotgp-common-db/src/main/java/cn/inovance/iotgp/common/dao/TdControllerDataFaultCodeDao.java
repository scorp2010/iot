/**
 * Project Name:iotgp-common-db
 * File Name:TdControllerDataFaultCodeDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-11-24上午10:57:43
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerDataFaultCode;

/**
 * ClassName:TdControllerDataFaultCodeDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-11-24 上午10:57:43 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface TdControllerDataFaultCodeDao  extends BaseDao<TdControllerDataFaultCode>{

	public List<TdControllerDataFaultCode> findByTdControllerId(String tdControllerId);
	public TdControllerDataFaultCode getByTdControllerDataFaultCode(TdControllerDataFaultCode data);
	public void deleteByControllerId(String controllerId);
}

