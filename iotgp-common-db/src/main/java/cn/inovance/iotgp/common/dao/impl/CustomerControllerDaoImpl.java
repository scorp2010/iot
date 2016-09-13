/**
 * Project Name:iotgp-common-db
 * File Name:DnsCodeControllerDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2015-5-27下午2:46:18
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CustomerControllerDao;
import cn.inovance.iotgp.common.domain.CustomerController;

/**
 * ClassName:DnsCodeControllerDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-5-27 下午2:46:18 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class CustomerControllerDaoImpl extends BaseDaoImpl<CustomerController> implements
		CustomerControllerDao {

	@Override
	public List<CustomerController> findByDhsCode(String dnsCode) {
		
		String hql = "from CustomerController t where t.dhsCode=:dhsCode order by t.tdControllerName";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("dhsCode", dnsCode.trim());
		return find(hql, parameters);
	}
	@Override
	public List<CustomerController> findByCustomerCode(String customerCode) {
		
		String hql = "from CustomerController t where t.customerCode=:customerCode order by t.tdControllerName";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("customerCode", customerCode.trim());
		return find(hql, parameters);
	}
	@Override
	public List<CustomerController> findByTdControllerId(String tdControllerId){
		
		String hql = "from CustomerController t where t.tdControllerId=:tdControllerId " +
				"order by t.tdControllerName";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tdControllerId", tdControllerId.trim());
		return find(hql, parameters);
	}
	@Override
	public List<CustomerController> findByTdControllerIdAndDhsCode(String tdControllerId,String dhsCode){
		
		String hql = "from CustomerController t where t.tdControllerId=:tdControllerId " +
				" and t.dhsCode=:dhsCode " +
				"order by t.tdControllerName";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tdControllerId", tdControllerId.trim());
		parameters.put("dhsCode", dhsCode.trim());
		return find(hql, parameters);
	}
	
	@Override
	public void deleteByControllerId(String controllerId) {
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("tdControllerId", controllerId.trim());
		String sql = "delete from t_oms_customer_controller where td_controller_type_id_fk:=tdControllerId";
		executeSql(sql, parameters);
	}

	@Override
	public void deleteByDhsCode(String dhsCode) {
		
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("dhsCode", dhsCode.trim());
		String sql = "delete from t_oms_customer_controller where dhs_code:=dhsCode";
		executeSql(sql, parameters);
		
	}
	@Override
	public CustomerController getByCustomerCodeAndControllerId(
			String customerCode, String tdControllerId) {
		String hql = "from CustomerController t where t.customerCode=:customerCode " +
				" and t.tdControllerId=:tdControllerId "+
				" order by t.tdControllerName";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("customerCode", customerCode.trim());
		parameters.put("tdControllerId", tdControllerId.trim());
		return getByHql(hql, parameters);
	}
	@Override
	public void deleteByCustomerCode(String custoemrCode) {
		
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("customerCode", custoemrCode.trim());
		String sql = "delete from t_oms_customer_controller where customer_code=:customerCode";
		executeSql(sql, parameters);
		
	}
	@Override
	public void updateDhsCode(String newDshCode, String custoemrCode) {
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("custoemrCode", custoemrCode.trim());
		parameters.put("newDshCode", newDshCode.trim());
		String sql = "update t_oms_customer_controller set dhs_code=:newDshCode where customer_code:=customerCode";
		executeSql(sql, parameters);
		
	}
	@Override
	public CustomerController getCustomerDefaultController(String customerCode) {
		
		String hql = "from CustomerController t where t.customerCode=:customerCode " +
				" and t.defaultController='Y' ";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("customerCode", customerCode.trim());
		return getByHql(hql, parameters);
	}

}

