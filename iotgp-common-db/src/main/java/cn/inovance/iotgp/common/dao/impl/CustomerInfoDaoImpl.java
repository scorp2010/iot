/**
 * Project Name:iotgp-common-db
 * File Name:CustomerInfoDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-9-23下午5:05:46
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CustomerInfoDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.TdControllerBatchUpdateScopeDao;
import cn.inovance.iotgp.common.dao.TdSoftWareBatchUpdateScopeDao;
import cn.inovance.iotgp.common.domain.CollectDevice;
import cn.inovance.iotgp.common.domain.CustomerInfo;
import cn.inovance.iotgp.common.domain.TdControllerBatchUpdateScope;
import cn.inovance.iotgp.common.domain.TdSoftWareBatchUpdateScope;
import cn.inovance.iotgp.common.domain.User;

/**
 * ClassName:CustomerInfoDaoImpl.
 * @author   w1898
 * @version
 * @since    JDK 1.7
 * @see
 */
@Repository
public class CustomerInfoDaoImpl extends BaseDaoImpl<CustomerInfo> implements CustomerInfoDao {

	@Resource
	SuperBaseDao<User> superDao;
	@Resource
	TdSoftWareBatchUpdateScopeDao tdSoftWareBatchUpdateScopeDao;
	@Override
	public CustomerInfo getCustomerInfoByCustomerCode(String customerCode) {
		
		String hql ="from CustomerInfo t where t.customerCode='"+customerCode.trim()+"'";
		return getByHql(hql);
	}

	@Override
	public CustomerInfo getCustomerInfoByMesCustomerCode(String mesCustomerCode) {
		if(StringUtils.isBlank(mesCustomerCode.trim())){
			return null;
		}
		String hql ="from CustomerInfo t where t.mesCustomerCode='"+mesCustomerCode.trim()+"'";
		return getByHql(hql);
	}

	@Override
	public User getUserByCustomerCode(String customerCode) {
		
		if(StringUtils.isBlank(customerCode.trim())){
			return null;
		}
		String hql ="select t from User as t inner join fetch t.customerInfo as c  where c.customerCode='"+customerCode.trim()+"'";
		return superDao.getByHql(hql);
	}
	
	/**
	 * 保存客户软件批量更新范围
	 */
	@Override
	public void setControllerScope(CustomerInfo data) {
		TdSoftWareBatchUpdateScope tdSoftWareBatchUpdateScope = null;
		if(StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())){
			tdSoftWareBatchUpdateScope = tdSoftWareBatchUpdateScopeDao.getById(data.getOwnerTargetDeviceControllerUpdateScope());
		}
		if( null == tdSoftWareBatchUpdateScope ){
			return;
		}
		data.setOwnerTargetDeviceControllerName(tdSoftWareBatchUpdateScope.getTdctrollerName());
		data.setOwnerTargetDeviceProductName(tdSoftWareBatchUpdateScope.getTdProductModelName());
		
		if(data.getSelectAll().equals(CustomerInfo.SELECT_ALL_YES)){
			StringBuilder sql = new StringBuilder(200); 
			int pages = 1;
			int rows  = 500;
			while(true){
				sql.append("SELECT t.id FROM  t_oms_customer_info t ");
				sql.append(" WHERE 1=1 AND (t.owner_td_software_update_scope_id IS null OR LENGTH(t.owner_td_software_update_scope_id) = 0 ) ");
			    if(StringUtils.isNotBlank(data.getCustomerName())){
			    	sql.append("AND t.customer_name LIKE '%" + data.getCustomerName().trim()+"%' ");
			    }
			    if(StringUtils.isNotBlank(data.getCustomerIndustry())){
			    	sql.append("AND t.customer_industry = '" + data.getCustomerIndustry().trim()+"' ");
			    }
			    if(StringUtils.isNotBlank(data.getCustomerSubSystem().getCode())){
			    	sql.append("AND t.customerSubSystem.code LIKE '%" + data.getCustomerSubSystem().getCode().trim()+"%' ");
			    }
				sql.append(" order by t.create_time ");
				sql.append(" LIMIT "+(pages-1)*rows+","+rows);
				List<Map> list = findBySql(sql.toString());
				StringBuilder cdIds = new StringBuilder(2000); 
				cdIds.append("(");
				boolean first = true;
				for (int i = 0; i < list.size(); i++) {
					Map map = list.get(i);
					if(first){
						cdIds.append("'"+map.get("id")+"'");
						first = false;
					}else {
						cdIds.append(",'"+map.get("id")+"'");
					}
				}
				
				cdIds.append(")");
				if(!first){
					setControllerScope(cdIds.toString(),data);
				}
				if(list.size() < rows ){
					break;
				}
				sql.delete(0, sql.length());
				first = true;
			}
			
		}
		if(StringUtils.isNotBlank(data.getCdIds())){
			setControllerScope("("+data.getCdIds()+")",data);
		}
	}
	/**
	 * prameters ('1','2','3')
	 *
	 * @author lk2200
	 * @since JDK 1.7
	 */
	public void setControllerScope(String cdids,CustomerInfo data){
		String sql ="UPDATE t_oms_customer_info SET owner_td_software_update_scope_id = '" + data.getOwnerTargetDeviceControllerUpdateScope()+"' ," +
				"owner_td_controller_name = '"+data.getOwnerTargetDeviceControllerName() + "' ," + "owner_td_product_name = '"+data.getOwnerTargetDeviceProductName() +"' WHERE id in "+cdids;
		
		executeSql(sql);
	}
	
	
	@Override
	public void setControllerScopeNull(CustomerInfo data) {
		
		if(StringUtils.isBlank(data.getOwnerTargetDeviceControllerUpdateScope())){
			   return;
		   }
		   if(data.getSelectAll().equals(CollectDevice.SELECT_ALL_YES)){
			   int pages = 1;
			   int rows  = 500;
			   StringBuilder sqlBuilder = new StringBuilder(200); 
			   while(true){
				   sqlBuilder.append("SELECT t.id FROM  t_oms_customer_info t ");	
				   sqlBuilder.append(" WHERE 1=1 AND t.owner_td_software_update_scope_id='"+ data.getOwnerTargetDeviceControllerUpdateScope()+"' ");
					if(StringUtils.isNotBlank(data.getCustomerName())){
						sqlBuilder.append("AND t.customer_name LIKE '%" + data.getCustomerName().trim()+"%' ");
				    }
				    if(StringUtils.isNotBlank(data.getCustomerIndustry())){
				    	sqlBuilder.append("AND t.customer_industry = '" + data.getCustomerIndustry().trim()+"' ");
				    }
				    if(StringUtils.isNotBlank(data.getCustomerSubSystem().getCode())){
				    	sqlBuilder.append("AND t.customerSubSystem.code LIKE '%" + data.getCustomerSubSystem().getCode().trim()+"%' ");
				    }
				    sqlBuilder.append(" order by t.create_time ");
					sqlBuilder.append(" LIMIT "+(pages-1)*rows+","+rows);
					List<Map> list = findBySql(sqlBuilder.toString());
					StringBuilder cdIds = new StringBuilder(2000); 
					boolean first = true;
					for (int i = 0; i < list.size(); i++) {
						Map map = list.get(i);
						if(first){
							cdIds.append("'"+map.get("id")+"'");
							first = false;
						}else {
							cdIds.append(",'"+map.get("id")+"'");
						}
					}
					if(!first){
						setControllerScopeNull(cdIds.toString());
					}
					if(list.size() < rows ){
						break;
					}
					sqlBuilder.delete(0, sqlBuilder.length());
					first = true; 
			   }
		   }else if(StringUtils.isNotBlank(data.getCdIds())){ 
			   setControllerScopeNull(data.getCdIds());
		   }
	}
	public void setControllerScopeNull(String cdids){
		 String sql = "UPDATE t_oms_customer_info set owner_td_controller_name=null,owner_td_product_name=null,owner_td_software_update_scope_id=null ";
		 sql += "WHERE id in("+ cdids+")";
		 executeSql(sql);
	}

	@Override
	public List<CustomerInfo> getCustomerInfoByBatchScopeId(String id) {
		String hql ="select t from CustomerInfo as t inner join fetch t.customerSubSystem as c where t.ownerTargetDeviceControllerUpdateScope='"+id+"'";
		return find(hql);
	}
	
	public void setControllerScopeNullByTdProductBatchUpdateScopeId(String id){
		 String sql = "UPDATE t_oms_customer_info set owner_td_controller_name=null,owner_td_product_name=null,owner_td_software_update_scope_id=null ";
		 sql += "WHERE owner_td_software_update_scope_id ='"+id+"'";
		 executeSql(sql);
	}
	
	public void setControllerScopeNullById(String id){
		 String sql = "UPDATE t_oms_customer_info set owner_td_controller_name=null,owner_td_product_name=null,owner_td_software_update_scope_id=null ";
		 sql += "WHERE id ='"+id+"'";
		 executeSql(sql);
	}
}

