/**
 * Project Name:iotgp-common-db
 * File Name:CdEventDao.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-7-22下午4:23:30
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.CdEventLog;
import cn.inovance.iotgp.common.domain.CdSoftwareInfo;
import cn.inovance.iotgp.common.nopersistence.domain.CdEventLogBean;

/**
 * ClassName:CdEventDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-22 下午4:23:30 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface CdEventDao extends BaseDao<CdEventLog>{

	public List<CdEventLogBean> findBySql(CdEventLogBean data,int rows, int page,String sort,String order);
	public long countfindBySql(CdEventLogBean data);
	public List<CdEventLog> findByHql(CdEventLog data,int rows, int page,String sort,String order);
}

