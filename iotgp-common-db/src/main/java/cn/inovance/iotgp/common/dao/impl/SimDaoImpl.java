/**
 * Project Name:iotgp-common-db
 * File Name:SimDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-9-2下午2:44:06
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SimDao;
import cn.inovance.iotgp.common.domain.Sim;

/**
 * ClassName:SimDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-2 下午2:44:06 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class SimDaoImpl extends BaseDaoImpl<Sim> implements SimDao{

	@Override
	public Long countByImsi(String imsi) {
		String hql = "select count(distinct t) from Sim t where t.imsi='" + imsi+"'";
		return count(hql);
	}

	@Override
	public Sim findByImsi(String imsi) {
		String hql = "SELECT t from Sim t where t.imsi='" + imsi+"'";
		 List<Sim> list = (List<Sim>)find(hql);
		 if(list == null  || list.size() == 0){
			 return null;
		 }
		 return list.get(0);
	}

}

