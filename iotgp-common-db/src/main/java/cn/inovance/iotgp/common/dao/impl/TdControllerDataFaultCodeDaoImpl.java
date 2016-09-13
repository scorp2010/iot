/**
 * Project Name:iotgp-common-db
 * File Name:TdControllerDataFaultCodeDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-11-24上午10:58:38
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.TdControllerDataFaultCodeDao;
import cn.inovance.iotgp.common.domain.TdControllerDataFaultCode;

/**
 * ClassName:TdControllerDataFaultCodeDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-11-24 上午10:58:38 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class TdControllerDataFaultCodeDaoImpl extends BaseDaoImpl<TdControllerDataFaultCode> 
	implements TdControllerDataFaultCodeDao
{
	@Override
	public TdControllerDataFaultCode getByTdControllerDataFaultCode(TdControllerDataFaultCode data) {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>(0);
		StringBuilder hql =  new StringBuilder(300);
		hql.append("select distinct fc from TdControllerDataFaultCode fc where 1=1 ");
		if(StringUtils.isNotBlank(data.getId())){
			hql.append("and fc.id != :id");
			paramsMap.put("id", data.getId());
		}
		if(StringUtils.isNotBlank(data.getTdControllerType().getId())){
			hql.append("  and  fc.tdControllerType.id =:protocolId ");
			paramsMap.put("protocolId", data.getTdControllerType().getId());
		}
		if(StringUtils.isNotBlank(data.getFaultCode())){
			hql.append(" and  fc.faultCode =:faultCode ");
			paramsMap.put("faultCode", data.getFaultCode());
		}		
		return getByHql(hql.toString(), paramsMap);
	}

	@Override
	public void deleteByControllerId(
			String controllerId) {
		if(StringUtils.isBlank(controllerId)){
			return;
		}
		String sql = "DELETE FROM t_mms_td_controller_data_fault_code where td_controller_type_id_fk=:controllerId";
		Map<String, Object> params = new LinkedHashMap<String, Object>(0);
		params.put("controllerId", controllerId);
		executeSql(sql, params);
	}

	@Override
	public List<TdControllerDataFaultCode> findByTdControllerId(
			String tdControllerId) {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		StringBuilder hql =  new StringBuilder();
		hql.append("select distinct fc from TdControllerDataFaultCode fc where 1=1 ");
		if(StringUtils.isNotBlank(tdControllerId)){
			hql.append("  and  fc.tdControllerType.id =:tdControllerId ");
			paramsMap.put("tdControllerId", tdControllerId);
		}
		return find(hql.toString(), paramsMap);
	}
 
}

