/**
 * Project Name:iotgp-common-db
 * File Name:FmsUserDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-7-29下午3:11:01
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import cn.inovance.iotgp.common.domain.FmsUser;

/**
 * ClassName:FmsUserDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-29 下午3:11:01 <br/>
 * @author   z1979
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface FmsUserDao {

	FmsUser findFmsUserByCondition(FmsUser fmsUser);

}

