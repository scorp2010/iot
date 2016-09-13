/**
 * Project Name:cds
 * File Name:CommonTypeDaoImpl.java
 * Package Name:cn.inovance.iotgp.cds.dao.impl
 * Date:2014-4-24下午2:47:24
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CommonTypeDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CommonType;
import cn.inovance.iotgp.common.domain.FileInfo;

/**
 * ClassName:CommonTypeDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-24 下午2:47:24 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CommonTypeDaoImpl extends BaseDaoImpl<CommonType> implements
		CommonTypeDao {

	@Override
	public CommonType getByCode(String code, String type) {
		
		String hql = "select distinct t from CommonType t where t.type='"
				+ type + "' and t.code='" + code + "'";
		return getByHql(hql);
	}

	@Override
	public List<CommonType> getByType(String type) {
		
		String hql = "select distinct t from CommonType t where t.type='"
				+ type + "'";
		return find(hql);
	}

	@Override
	public CommonType getById(String id) {
		
		String hql = "select distinct t from CommonType t where t.id='" + id
				+ "'";
		return getByHql(hql);
	}

	@Override
	public List<CommonType> getType() {
		
		String sql = "select t.type from t_common_type t group by t.type";
		List<Map> list =findBySql(sql);
		List<CommonType> commonTypeList = new ArrayList<CommonType>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			CommonType commonType = new CommonType();
			commonType.setType(map.get("type").toString());
			commonTypeList.add(commonType);
		}
		return commonTypeList;
	}
	
}
