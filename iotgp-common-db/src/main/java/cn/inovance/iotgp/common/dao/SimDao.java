/**
 * Project Name:iotgp-common-db
 * File Name:SimDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-9-2下午2:42:27
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import cn.inovance.iotgp.common.domain.Sim;

/**
 * ClassName:SimDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-2 下午2:42:27 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface SimDao  extends BaseDao<Sim>{

	public Long countByImsi(String imsi);
	public Sim findByImsi(String imsi);
}

