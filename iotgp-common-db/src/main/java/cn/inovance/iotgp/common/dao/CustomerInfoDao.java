/**
 * Project Name:iotgp-common-db
 * File Name:CustomerInfoDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-9-23下午5:04:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.domain.CollectDevice;
import cn.inovance.iotgp.common.domain.CustomerInfo;
import cn.inovance.iotgp.common.domain.User;

/**
 * ClassName:CustomerInfoDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-23 下午5:04:45 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public interface CustomerInfoDao extends BaseDao<CustomerInfo>{

	public CustomerInfo getCustomerInfoByCustomerCode(String customerCode);
	public CustomerInfo getCustomerInfoByMesCustomerCode(String mesCustomerCode);
	public User getUserByCustomerCode(String customerCode);
	
	public void setControllerScope(CustomerInfo data);
	
	public void setControllerScopeNull(CustomerInfo data);
	
	public List<CustomerInfo> getCustomerInfoByBatchScopeId(String id);
	
	public void setControllerScopeNullByTdProductBatchUpdateScopeId(String id);
	
	public void setControllerScopeNullById(String id);
}

