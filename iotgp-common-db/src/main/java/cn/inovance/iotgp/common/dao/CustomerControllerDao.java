/**
 * Project Name:iotgp-common-db
 * File Name:DnsCodeControllerDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2015-5-27下午2:44:52
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.CustomerController;

/**
 * ClassName:DnsCodeControllerDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-5-27 下午2:44:52 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface CustomerControllerDao extends BaseDao<CustomerController>{

	public List<CustomerController> findByDhsCode(String dnsCode);
	public List<CustomerController> findByCustomerCode(String customerCode);
	public List<CustomerController> findByTdControllerIdAndDhsCode(String tdControllerId,String dhsCode);
	public List<CustomerController> findByTdControllerId(String tdControllerId);
	public CustomerController getByCustomerCodeAndControllerId(String customerCode,String tdControllerId);
	public void deleteByControllerId(String controllerName);
	public void deleteByDhsCode(String dnsCode);
	public void updateDhsCode(String newDshCode,String custoemrCode);
	public void deleteByCustomerCode(String custoemrCode);
	public CustomerController getCustomerDefaultController(String customerCode);
}

