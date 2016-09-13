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
import cn.inovance.iotgp.common.dao.CustomerProductModelDao;
import cn.inovance.iotgp.common.domain.CustomerController;
import cn.inovance.iotgp.common.domain.CustomerProductModel;

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
public class CustomerProductModelDaoImpl extends BaseDaoImpl<CustomerProductModel> implements
		CustomerProductModelDao {

	@Override
	public List<CustomerProductModel> findByDhsCode(String dnsCode) {
		
		String hql = "from CustomerProductModel t where t.dhsCode=:dhsCode order by t.tdControllerName";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("dhsCode", dnsCode.trim());
		return find(hql, parameters);
	}
	@Override
	public List<CustomerProductModel> findByCustomerCode(String customerCode) {
		
		String hql = "from CustomerProductModel t where t.customerCode=:customerCode order by t.tdProductName";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("customerCode", customerCode.trim());
		return find(hql, parameters);
	}
	//通过产品型号Id找到对应的客户
	@Override
	public List<CustomerProductModel> findByTdProductId(String tdProductId){
		
		String hql = "from CustomerProductModel t where t.tdProductId=:tdProductId " +
				"order by t.tdProductName";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tdProductId", tdProductId);
		return find(hql, parameters);
	}
	@Override
	public List<CustomerProductModel> findByTdProductIdAndDhsCode(String tdProductId,String dhsCode,String version){
		
		String hql = "from CustomerProductModel t where t.tdProductId=:tdProductId " +
				" and t.dhsCode=:dhsCode " + " and t.tdProductVersion=:version " +
				"order by t.tdProductName";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("tdProductId", tdProductId);
		parameters.put("dhsCode", dhsCode);
		parameters.put("version", version);
		return find(hql, parameters);
	}
	
	@Override
	public void deleteByProductId(String controllerId) {
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
	//查询已存在的
	@Override
	public CustomerProductModel getByCustomerCodeAndProductId(
			String customerCode, String tdProductId) {
		String hql = "from CustomerProductModel t where t.customerCode=:customerCode " +
				" and t.tdProductId=:tdProductId "+
				" order by t.tdProductName";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("customerCode", customerCode);
		parameters.put("tdProductId", tdProductId);
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

}

