/**
 * Project Name:iotgp-common-db
 * File Name:TdSoftWareUpdateJobDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-7-18下午6:58:33
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;
import java.util.List;

import cn.inovance.iotgp.common.domain.TdSoftWareUpdateJob;

/**
 * ClassName:TdSoftWareUpdateJobDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-18 下午6:58:33 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface TdSoftWareUpdateJobDao extends BaseDao<TdSoftWareUpdateJob> {

	public void updateNoUpdateJobOverLaped(TdSoftWareUpdateJob data);
	
	public long updateAndCountFindByData(TdSoftWareUpdateJob data);
	
	public List<TdSoftWareUpdateJob> findUniqueByNameVersionNoFinish(TdSoftWareUpdateJob data);
	
	public void setJobTdScopeScopeNull(String tdControllerBatchUpdateScopeId);
	public long countFindByData(TdSoftWareUpdateJob data);
}

