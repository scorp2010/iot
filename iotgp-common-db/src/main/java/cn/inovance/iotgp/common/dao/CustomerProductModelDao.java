/**
 * Project Name:iotgp-common-db
 * File Name:DnsCodeProductDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2015-5-27下午2:44:52
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;
import cn.inovance.iotgp.common.domain.CustomerProductModel;

/**
 * ClassName:DnsCodeProductDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-5-27 下午2:44:52 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface CustomerProductModelDao extends BaseDao<CustomerProductModel>{

	public List<CustomerProductModel> findByDhsCode(String dnsCode);
	public List<CustomerProductModel> findByCustomerCode(String customerCode);
	public List<CustomerProductModel> findByTdProductIdAndDhsCode(String tdProductId,String dhsCode,String version);
	public List<CustomerProductModel> findByTdProductId(String tdProductId);
	public CustomerProductModel getByCustomerCodeAndProductId(String customerCode,String tdProductId);
	public void deleteByProductId(String ProductName);
	public void deleteByDhsCode(String dnsCode);
	public void updateDhsCode(String newDshCode,String custoemrCode);
	public void deleteByCustomerCode(String custoemrCode);
}

