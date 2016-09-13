/**
 * Project Name:iotgp-common-db
 * File Name:TdControllerDataCommandLibDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2015-1-15上午10:25:46
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.TdControllerDataCommandLibDao;
import cn.inovance.iotgp.common.domain.TdControllerDataCommandLib;

/**
 * ClassName:TdControllerDataCommandLibDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-15 上午10:25:46 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class TdControllerDataCommandLibDaoImpl  extends BaseDaoImpl<TdControllerDataCommandLib> implements
		TdControllerDataCommandLibDao {

	@Override
	public void deleteByControllerId(String controllerId) {
		
		if(StringUtils.isBlank(controllerId)){
			return;
		}
		String sql = "DELETE FROM t_mms_td_controller_data_command_lib where td_controller_type_id_fk=:controllerId";
		Map<String, Object> params = new LinkedHashMap<String, Object>(0);
		params.put("controllerId", controllerId);
		executeSql(sql, params);
		
	}

	@Override
	public List<TdControllerDataCommandLib> findByTdControllerId(
			String tdControllerId) {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		StringBuilder hql =  new StringBuilder();
		hql.append("select distinct fc from TdControllerDataCommandLib fc where 1=1 ");
		if(StringUtils.isNotBlank(tdControllerId)){
			hql.append("  and  fc.tdControllerType.id =:tdControllerId ");
			paramsMap.put("tdControllerId", tdControllerId);
		}
		return find(hql.toString(), paramsMap);
	}
	 

}

