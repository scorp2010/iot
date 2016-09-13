/**
 * Project Name:iotgp-common-db
 * File Name:CdModelDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2015-7-2上午10:58:44
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdModelDao;
import cn.inovance.iotgp.common.domain.CdModel;

/**
 * ClassName:CdModelDaoImpl <br/>
 * Date:     2015-7-2 上午10:58:44
 * @author   w1898
 * @version
 * @since    JDK 1.7
 * @see
 */
@Repository
public class CdModelDaoImpl extends BaseDaoImpl<CdModel> implements CdModelDao {

	@Override
	public CdModel getByMateriel(String materiel) {
		
		if(StringUtils.isBlank(materiel)){
			return null;
		}
		String hql = "from CdModel t where t.materiel='"+materiel.trim()+"'";
		List<CdModel> listModel=  find(hql);
		if( null != listModel && listModel.size() > 0 ){
			return listModel.get(0);
		}
		return null;
	}

}

