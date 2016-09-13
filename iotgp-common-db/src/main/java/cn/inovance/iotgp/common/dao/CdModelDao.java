/**
 * Project Name:iotgp-common-db
 * File Name:CdModelDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2015-7-2上午10:57:42
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;


import cn.inovance.iotgp.common.domain.CdModel;

/**
 * ClassName:CdModelDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-7-2 上午10:57:42 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface CdModelDao extends BaseDao<CdModel> {

	CdModel getByMateriel(String materiel);
	
}

