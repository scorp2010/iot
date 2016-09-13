/**
 * Project Name:iotgp-common-db
 * File Name:TdSoftWareDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-7-16下午8:22:53
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.domain.TdSoftWare;

/**
 * ClassName:TdSoftWareDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-16 下午8:22:53 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface TdSoftWareDao {

		public List<TdSoftWare> find(TdSoftWare tdSoftWare,int rows,int page);
		
		public List<TdSoftWare> grid(TdSoftWare tdSoftWare,int rows,int page);
		
		public TdSoftWare getByTdControllerTypeId(TdSoftWare tdSoftWare);
}

