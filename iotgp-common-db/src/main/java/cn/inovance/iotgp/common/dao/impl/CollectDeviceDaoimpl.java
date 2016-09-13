/**
 * Project Name:iotgp-common-db
 * File Name:CollectDeviceDaoimpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-5-19下午3:28:17
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CollectDeviceDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.TdControllerBatchUpdateScopeDao;
import cn.inovance.iotgp.common.domain.CdRegisterCode;
import cn.inovance.iotgp.common.domain.CollectDevice;
import cn.inovance.iotgp.common.domain.TdControllerBatchUpdateScope;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.status.StatusEnum;

/**
 * ClassName:CollectDeviceDaoimpl.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CollectDeviceDaoimpl extends BaseDaoImpl<CollectDevice> implements
		CollectDeviceDao {

	@Resource
	TdControllerBatchUpdateScopeDao tdControllerBatchUpdateScopeDao;
	@Resource
	SuperBaseDao<CollectDevice> superBaseDao;
	@Override
	public CollectDevice queryOneByCdRegCode(String cdRegCode) {

		String hql = "select distinct t from CollectDevice t where t.cdRegCode ='"
				+ cdRegCode + "'";

		return getByHql(hql);
	}
	
	/**
	 * 获取调拨设备时需要回收的设备
	 */
	@Override
	public CollectDevice queryNeedDeleteByCdRegCode(String cdRegCode) {

		String hql = "select distinct t from CollectDevice t where t.cdRegCode ='"
				+ cdRegCode + "' and t.customerCode is not null";

		return getByHql(hql);
	}

	@Override
	public List<CollectDevice> findAllBy(CollectDevice data) {

		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.cd_reg_code from t_mms_collect_device t where 1=1");
		if (StringUtils.isNotBlank(data.getCdRegCode())) {
			sql.append(" and t.cd_reg_code like '%"
					+ data.getCdRegCode().trim() + "%'");
		}
		if (StringUtils.isNotBlank(data.getStartRegDate())) {
			sql.append(" and UNIX_TIMESTAMP(t.reg_time) >= UNIX_TIMESTAMP('"
					+ data.getStartRegDate().trim() + "') ");
		}
		if (StringUtils.isNotBlank(data.getEndRegDate())) {
			sql.append(" and UNIX_TIMESTAMP(t.reg_time) <= UNIX_TIMESTAMP('"
					+ data.getEndRegDate().trim() + "')");
		}
		if (StringUtils.isNotBlank(data.getCustomerCode())) {
			sql.append(" and t.customer_code ='"
					+ data.getCustomerCode().trim() + "'");
		}
		if (StringUtils.isNotBlank(data.getOnlineStauts())) {
			sql.append(" and t.online_stauts ='"
					+ data.getOnlineStauts().trim() + "'");
		}
		if (StringUtils.isNotBlank(data.getSubSysCode())) {
			sql.append(" and t.sub_sys_code ='" + data.getSubSysCode().trim()
					+ "' ");
		}
		if (StringUtils.isNotBlank(data.getCustomerName())) {
			sql.append(" and t.customer_name like '%" + data.getCustomerName().trim()
					+ "%' ");
		}
		if (data.getMode() !=null && StringUtils.isNotBlank(data.getMode().toString())) {
			sql.append(" and t.mode = '" + data.getMode() + "' ");
		}
		if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())) {
			sql.append(" and t.owner_td_controller_update_scope_id ='" + data.getOwnerTargetDeviceControllerUpdateScope().trim()
					+ "' ");
		}
		List<Map> list = findBySql(sql.toString());
		List<CollectDevice> listCollectDevices = new ArrayList<CollectDevice>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			CollectDevice cd = new CollectDevice();
			cd.setCdRegCode(map.get("cd_reg_code").toString());
			listCollectDevices.add(cd);
		}
		return listCollectDevices;
	}
    public List<CollectDevice> findALlByOld(CollectDevice data, int page,
			int rows, String sort, String order, String q){
    	final StringBuffer sql = new StringBuffer();
		final Map <String,String> sortMap = new HashMap<String,String>(0);
		sortMap.put("createTime", "create_time");
		sortMap.put("regTime", "reg_time");
		sortMap.put("businessType", "business_type");
		sortMap.put("model", "product_model");
		sortMap.put("onlineStauts", "online_stauts");
		sql.append("SELECT cd.*,tccrc.mac as mac_register,tccrc.product_sn as product_sn_register FROM t_mms_collect_device cd ");
		sql.append("LEFT JOIN t_cds_cd_register_code tccrc on tccrc.reg_code = cd.cd_reg_code ");
		sql.append(" WHERE 1 = 1 ");
		if (StringUtils.isNotBlank(q)) {
			sql.append("AND cd.cd_reg_code like '%" + q + "%' ");
		} else if (null != data) {
			if (StringUtils.isNotBlank(data.getCdRegCode())) {
				sql.append("AND cd.cd_reg_code like '%" + data.getCdRegCode() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getStartRegDate())) {
				sql.append("AND UNIX_TIMESTAMP(cd.reg_time) >= UNIX_TIMESTAMP('"
						+ data.getStartRegDate().trim() + "') ");
			}
			if (StringUtils.isNotBlank(data.getEndRegDate())) {
				sql.append("AND UNIX_TIMESTAMP(cd.reg_time) <= UNIX_TIMESTAMP('"
						+ data.getEndRegDate().trim() + "') ");
			}

			if (StringUtils.isNotBlank(data.getCustomerName())) {
				sql.append("AND cd.customer_name like '%"
						+ data.getCustomerName() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getOnlineStauts())) {
				sql.append("AND cd.online_stauts = '" + data.getOnlineStauts()
						+ "' ");
			}
			if (StringUtils.isNotBlank(data.getBusinessType())) {
				sql.append("AND cd.business_type = '" + data.getBusinessType()
						+ "' ");
			}
			if (StringUtils.isNotBlank(data.getSubSysCode())) {
				sql.append("AND cd.sub_sys_code = '" + data.getSubSysCode()
						+ "' ");
			}
			if (StringUtils.isNotBlank(data.getDhsCode())) {
				sql.append("AND cd.dhs_code = '" + data.getDhsCode()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getProductSn())) {
				sql.append("AND tccrc.product_sn = '" + data.getProductSn()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getMac())) {
				sql.append("AND tccrc.mac = '" + data.getMac()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getSim())) {
				sql.append("AND cd.sim = '" + data.getSim()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerName())) {
				sql.append("AND cd.owner_td_controller_name like '%" + data.getOwnerTargetDeviceControllerName()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())) {
				sql.append("AND cd.owner_td_controller_update_scope_id = '" + data.getOwnerTargetDeviceControllerUpdateScope()
						+ "' ");
			}else if(StringUtils.isNotBlank(data.getSearchMode()) && data.getSearchMode().equals(CollectDevice.SEARCH_MODE_TD_CONTROLLER_SCOPE)) {
				sql.append("AND ( LENGTH(cd.owner_td_controller_update_scope_id) = 0 or cd.owner_td_controller_update_scope_id is null )" );
			}
		}
		if(StringUtils.isNotBlank(sort) && sortMap.containsKey(sort)) {
			sql.append("order by "+sortMap.get(sort)+" ");
		}else 
		{
			sql.append("order by online_stauts ");
		}
		if (StringUtils.isNotBlank(order)) {
			sql.append(order);
		} else {
			sql.append("desc");
		}
		
		if(rows > 0 && page > 0 ){
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}
		
		List<Map> list = findBySql(sql.toString());
		List<CollectDevice> listCollectDevices = new ArrayList<CollectDevice>(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Map map : list) {
			CollectDevice cd = new CollectDevice();
			cd.setId(map.get("id").toString());
			cd.setCdRegCode(map.get("cd_reg_code").toString());
			if(map.get("business_type") != null)
			{
				cd.setBusinessType(map.get("business_type").toString());
			}
			if(map.get("online_stauts") != null)
			{
				cd.setOnlineStauts(map.get("online_stauts").toString());
			}
			if(map.get("create_time") != null )
			{	
				
				try {
					cd.setCreateTime(df.parse(map.get("create_time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					
				}
			}
			if(map.get("sub_sys_code") != null)
			{
				cd.setSubSysCode(map.get("sub_sys_code").toString());
			}
			if(map.get("dhs_code") != null)
			{
				cd.setDhsCode(map.get("dhs_code").toString());
			}
			if(map.get("reg_time") != null )
			{	
				try {
					cd.setRegTime(df.parse(map.get("reg_time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					
				}
			}
			if(map.get("login_msg") != null )
			{	
				cd.setLoginMsg(map.get("login_msg").toString());
			}
			if(map.get("last_login_time") != null )
			{	
				try {
					cd.setLastLoginTime(df.parse(map.get("last_login_time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					
				}
			}
			if(map.get("last_logout_time") != null )
			{	
				try {
					cd.setLastLogoutTime(df.parse(map.get("last_logout_time").toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(map.get("customer_name") != null )
			{	
				cd.setCustomerName(map.get("customer_name").toString());
			}
			if(map.get("customer_code") != null )
			{	
				cd.setCustomerCode(map.get("customer_code").toString());
			}
			
			if(map.get("product_sn_register") != null )
			{	
				cd.setProductSn(map.get("product_sn_register").toString());
			}
			
			if(map.get("mac_register") != null )
			{	
				cd.setMac(map.get("mac_register").toString());
			}
			
			if(map.get("sim") != null )
			{	
				cd.setSim(map.get("sim").toString());
			}
			
			if(map.get("owner_td_controller_update_scope_id") != null )
			{	
				cd.setOwnerTargetDeviceControllerUpdateScope(map.get("owner_td_controller_update_scope_id").toString());
			}
			if(map.get("owner_td_controller_name") != null )
			{	
				cd.setOwnerTargetDeviceControllerName(map.get("owner_td_controller_name").toString());
			}
			listCollectDevices.add(cd);
		}
		
		return listCollectDevices;
    }
	@Override
	public List<CollectDevice> findAllBy(CollectDevice data, int pages,
			int rows, String sort, String order, String q) {
		
		HqlFilter hqlFilter  = new HqlFilter();
		if(StringUtils.isNotBlank(q)){
			hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK", q.trim());
		}
		else if( null != data ){
			if(StringUtils.isNotBlank(data.getCdRegCode()))
			{
				hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK", data.getCdRegCode().trim());
			}
			if (StringUtils.isNotBlank(data.getStartRegDate())) {
				hqlFilter.addFilter("QUERY_t#regTime_D_GE", data.getStartRegDate().trim());
			}
			if (StringUtils.isNotBlank(data.getEndRegDate())) {
				hqlFilter.addFilter("QUERY_t#regTime_D_LE", data.getEndRegDate().trim());
			}
			if(StringUtils.isNotBlank(data.getCustomerName()))
			{
				hqlFilter.addFilter("QUERY_t#customerName_S_LK", data.getCustomerName().trim());
			}
			if(StringUtils.isNotBlank(data.getOnlineStauts()))
			{
				hqlFilter.addFilter("QUERY_t#onlineStauts_S_EQ", data.getOnlineStauts().trim());
			}
			if(StringUtils.isNotBlank(data.getSubSysCode()))
			{
				hqlFilter.addFilter("QUERY_t#subSysCode_S_LK", data.getSubSysCode().trim());
			}
			if(StringUtils.isNotBlank(data.getDhsCode()))
			{
				hqlFilter.addFilter("QUERY_t#dhsCode_S_LK", data.getDhsCode().trim());
			}
			if(StringUtils.isNotBlank(data.getBusinessType()))
			{
				hqlFilter.addFilter("QUERY_t#businessType_S_EQ", data.getBusinessType().trim());
			}
			if(StringUtils.isNotBlank(data.getOnlineStauts()))
			{
				hqlFilter.addFilter("QUERY_t#onlineStauts_S_EQ", data.getOnlineStauts().trim());
			}
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerName())) {
				hqlFilter.addFilter("QUERY_t#ownerTargetDeviceControllerName_S_LK",data.getOwnerTargetDeviceControllerName().trim());
			}
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())) {
				hqlFilter.addFilter("QUERY_t#ownerTargetDeviceControllerUpdateScope_S_EQ" ,data.getOwnerTargetDeviceControllerUpdateScope());
			}else if(StringUtils.isNotBlank(data.getSearchMode()) && data.getSearchMode().equals(CollectDevice.SEARCH_MODE_TD_CONTROLLER_SCOPE)) {
				//sql.append("AND ( LENGTH(cd.owner_td_controller_update_scope_id) = 0 or cd.owner_td_controller_update_scope_id is null )" );
				hqlFilter.addFilter("(LENGTH(t.ownerTargetDeviceControllerUpdateScope) = 0 or t.ownerTargetDeviceControllerUpdateScope is null)",null);
			}
		}
		if(StringUtils.isNotBlank(sort)) {
		//	sql.append("order by "+sortMap.get(sort)+" ");
			hqlFilter.addSort(sort);
		}else 
		{
			hqlFilter.addSort("onlineStauts");
		}
		if (StringUtils.isNotBlank(order)) {
			hqlFilter.addOrder(order);
		} else {
			hqlFilter.addOrder("desc");
		}
		
		return findByFilter(hqlFilter, pages, rows);
	}
	
	@Override
	public Long countByDataOld(CollectDevice data,String q){
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM t_mms_collect_device cd ");
		sql.append("LEFT JOIN t_cds_cd_register_code tccrc on tccrc.reg_code = cd.cd_reg_code ");
		sql.append("WHERE 1=1 ");
		
		if (StringUtils.isNotBlank(q)) {
			sql.append("AND cd.cd_reg_code like '%" + q + "%' ");
		} else if (null != data) {
			if (StringUtils.isNotBlank(data.getCdRegCode())) {
				sql.append("AND cd.cd_reg_code like '%" + data.getCdRegCode() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getStartRegDate())) {
				sql.append("AND UNIX_TIMESTAMP(cd.reg_time) >= UNIX_TIMESTAMP('"
						+ data.getStartRegDate().trim() + "') ");
			}
			if (StringUtils.isNotBlank(data.getEndRegDate())) {
				sql.append("AND UNIX_TIMESTAMP(cd.reg_time) <= UNIX_TIMESTAMP('"
						+ data.getEndRegDate().trim() + "') ");
			}

			if (StringUtils.isNotBlank(data.getCustomerName())) {
				sql.append("AND cd.customer_name like '%"
						+ data.getCustomerName() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getOnlineStauts())) {
				sql.append("AND cd.online_stauts = '" + data.getOnlineStauts()
						+ "' ");
			}
			if (StringUtils.isNotBlank(data.getSubSysCode())) {
				sql.append("AND cd.sub_sys_code = '" + data.getSubSysCode()
						+ "' ");
			}
			if (StringUtils.isNotBlank(data.getBusinessType())) {
				sql.append("AND cd.business_type = '" + data.getBusinessType()
						+ "' ");
			}
			if (StringUtils.isNotBlank(data.getDhsCode())) {
				sql.append("AND cd.dhs_code = '" + data.getDhsCode()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getProductSn())) {
				sql.append("AND tccrc.product_sn = '" + data.getProductSn()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getMac())) {
				sql.append("AND tccrc.mac = '" + data.getMac()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getSim())) {
				sql.append("AND cd.sim = '" + data.getSim()
						+ "' ");
			}
			
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerName())) {
				sql.append("AND cd.owner_td_controller_name like '%" + data.getOwnerTargetDeviceControllerName()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())) {
				sql.append("AND cd.owner_td_controller_update_scope_id = '" + data.getOwnerTargetDeviceControllerUpdateScope()
						+ "' ");
			}else if(StringUtils.isNotBlank(data.getSearchMode()) && data.getSearchMode().equals(CollectDevice.SEARCH_MODE_TD_CONTROLLER_SCOPE)) {
				sql.append("AND ( LENGTH(cd.owner_td_controller_update_scope_id) = 0 or cd.owner_td_controller_update_scope_id is null )" );
			}
		}
		return countBySql(sql.toString()).longValue();
	}
	
	@Override
	public Long getDeviceSoftwareInfoCount(CollectDevice data,String q){
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM ( " +
				" SELECT cd.cd_reg_code,cd.customer_name,tmcsi.name as softwareName,tmcsi.version as softwareVersion,tt.soft_version as softwareVersionMax,tt.update_result as updateResultName  ");
		sql.append("FROM t_mms_collect_device cd LEFT JOIN t_mms_cd_software_info tmcsi on tmcsi.cd_reg_code =cd.cd_reg_code ");
		sql.append("LEFT JOIN (SELECT t.cd_reg_code,"
								+ "t.soft_name,"
								+ "t.soft_version,"
								+ "t.soft_category,"
								+ "t.update_result "
								+ "FROM (SELECT tmtsuj.cd_reg_code, "
											+ "tmtsuj.soft_name, "
											+ "tmtsuj.soft_version AS soft_version,"
											+ "tmtsuj.update_result,"
											+ "'1' AS soft_category "
											+ "FROM t_mms_td_software_update_job tmtsuj "
											+ "WHERE tmtsuj.update_result = '1' "
										+ "UNION "
									  + "SELECT tmsud.cd_reg_code,"
											+ "tmsud.soft_name, "
											+ "tmsud.version AS soft_version,"
											+ "tmsud.update_result,"
											+ "'2' AS soft_category "
											+ "FROM t_mms_software_update_details tmsud "
											+ "WHERE tmsud.update_result = '1'"
									  + ") t"
						+ ") tt "
						+ "ON tt.soft_name =tmcsi.name and tt.cd_reg_code = cd.cd_reg_code ");
		sql.append("WHERE 1=1 ");
		
		if (StringUtils.isNotBlank(q)) {
			sql.append("AND cd.cd_reg_code like '%" + q + "%' ");
		} else if (null != data) {
			if (StringUtils.isNotBlank(data.getCdRegCode())) {
				sql.append("AND cd.cd_reg_code like '%" + data.getCdRegCode().trim() + "%' ");
			}
			
			if (StringUtils.isNotBlank(data.getCustomerName())) {
				sql.append("AND cd.customer_name like '%" + data.getCustomerName().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareVersion())) {
				sql.append("AND tmcsi.version like '%"	+ data.getSoftwareVersion().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareName())) {
				sql.append("AND tmcsi.name like '%"	+ data.getSoftwareName().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareVersionMax())) {
				sql.append("AND tt.soft_version like '%"	+ data.getSoftwareVersionMax().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getUpdateResultName())) {
				sql.append("AND tt.update_result like '%"	+ data.getUpdateResultName() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareCategory())) {
				sql.append("AND tt.soft_category like '%"	+ data.getSoftwareCategory() + "%' ");
			}
		}else if (null == data) {
			sql.append("AND tt.soft_category = 1 ");
		}
		sql.append(" ) ttt");
		return countBySql(sql.toString()).longValue();
	}
	
	public List<CollectDevice> getDeviceSoftwareInfo(CollectDevice data, int page,int rows, String sort, String order, String q){
    	final StringBuffer sql = new StringBuffer();
		final Map <String,String> sortMap = new HashMap<String,String>(0);
		sortMap.put("createTime", "create_time");
		sortMap.put("onlineStauts", "online_stauts");
		sql.append("SELECT cd.cd_reg_code,"
				+ "cd.customer_name,"
				+ "tmcsi.name as softwareName,"
				+ "tmcsi.version as softwareVersion,"
				+ "tt.soft_version as softwareVersionMax,"
				+ "tt.update_result as updateResultName,"
				+ "tt.soft_category as softwareCategory "
				+ "FROM t_mms_collect_device cd ");
		sql.append("LEFT JOIN t_mms_cd_software_info tmcsi "
				+ "on tmcsi.cd_reg_code =cd.cd_reg_code ");
		sql.append("LEFT JOIN (SELECT t.cd_reg_code,"
									+ "t.soft_name,"
									+ "t.soft_version,"
									+ "t.soft_category,"
									+ "t.update_result "
									+ "FROM (SELECT tmtsuj.cd_reg_code, "
												+ "tmtsuj.soft_name, "
												+ "tmtsuj.soft_version AS soft_version,"
												+ "tmtsuj.update_result,"
												+ "'1' AS soft_category "
												+ "FROM t_mms_td_software_update_job tmtsuj "
												+ "WHERE tmtsuj.update_result = '1' "
											+ "UNION "
										  + "SELECT tmsud.cd_reg_code,"
												+ "tmsud.soft_name, "
												+ "tmsud.version AS soft_version,"
												+ "tmsud.update_result,"
												+ "'2' AS soft_category "
												+ "FROM t_mms_software_update_details tmsud "
												+ "WHERE tmsud.update_result = '1'"
										  + ") t"
							+ ") tt "
							+ "ON tt.soft_name =tmcsi.name and tt.cd_reg_code = cd.cd_reg_code ");
		
		/*sql.append("LEFT JOIN t_mms_software_update_details tmsud "
				+ "on tmsud.soft_name =tmcsi.name "
				+ "and tmsud.cd_reg_code = cd.cd_reg_code and tmsud.update_result='"+ StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value+ "'  ");*/
		sql.append(" WHERE 1 = 1 ");
		if (StringUtils.isNotBlank(q)) {
			sql.append("AND cd.cd_reg_code like '%" + q + "%' ");
		} else if (null != data) {
			if (StringUtils.isNotBlank(data.getCdRegCode())) {
				sql.append("AND cd.cd_reg_code like '%" + data.getCdRegCode().trim() + "%' ");
			}
			
			if (StringUtils.isNotBlank(data.getCustomerName())) {
				sql.append("AND cd.customer_name like '%" + data.getCustomerName().trim() + "%' ");
			}
			
			if (StringUtils.isNotBlank(data.getSoftwareVersion())) {
				sql.append("AND tmcsi.version like '%"	+ data.getSoftwareVersion().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareName())) {
				sql.append("AND tmcsi.name like '%"	+ data.getSoftwareName().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareVersionMax())) {
				sql.append("AND tt.soft_version like '%"	+ data.getSoftwareVersionMax().trim() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getUpdateResultName())) {
				sql.append("AND tt.update_result like '%"	+ data.getUpdateResultName() + "%' ");
			}
			if (StringUtils.isNotBlank(data.getSoftwareCategory())) {
				sql.append("AND tt.soft_category like '%"	+ data.getSoftwareCategory() + "%' ");
			}
		}else if (null == data) {
			sql.append("AND tt.soft_category = 1 ");
		}
		if(StringUtils.isNotBlank(sort) && sortMap.containsKey(sort)) {
			sql.append("order by "+sortMap.get(sort)+" ");
		}else 
		{
			sql.append("order by cd.cd_reg_code ");
		}
		if (StringUtils.isNotBlank(order)) {
			sql.append(order);
		} else {
			sql.append("desc");
		}
		
		if(rows > 0 && page > 0 ){
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}
		
		List<Map> list = findBySql(sql.toString());
		List<CollectDevice> listCollectDevices = new ArrayList<CollectDevice>(0);
		for (Map map : list) {
			CollectDevice cd = new CollectDevice();
			//cd.setId(map.get("id").toString());
			cd.setCdRegCode(map.get("cd_reg_code").toString());
			
			if(map.get("customer_name") != null )
			{	
				cd.setCustomerName(map.get("customer_name").toString());
			}
			if(map.get("softwareName") != null )
			{	
				cd.setSoftwareName(map.get("softwareName").toString());
			}
			if(map.get("softwareVersion") != null )
			{	
				cd.setSoftwareVersion(map.get("softwareVersion").toString());
			}
			if(map.get("softwareVersionMax") != null )
			{	
				cd.setSoftwareVersionMax(map.get("softwareVersionMax").toString());
			}
			if(map.get("updateResultName") != null )
			{	
				cd.setUpdateResultName(map.get("updateResultName").toString());
			}
			if(map.get("softwareCategory") != null )
			{	
				cd.setSoftwareCategory(map.get("softwareCategory").toString());
			}
			
			listCollectDevices.add(cd);
		}
		
		return listCollectDevices;
    }
	
	
	@Override
	public Long countByData(CollectDevice data,String q) {
		HqlFilter hqlFilter  = new HqlFilter();
		if(StringUtils.isNotBlank(q)){
			hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK", q.trim());
		}
		else if( null != data ){
			if(StringUtils.isNotBlank(data.getCdRegCode()))
			{
				hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK", data.getCdRegCode().trim());
			}
			if (StringUtils.isNotBlank(data.getStartRegDate())) {
				hqlFilter.addFilter("QUERY_t#regTime_D_GE", data.getStartRegDate().trim());
			}
			if (StringUtils.isNotBlank(data.getEndRegDate())) {
				hqlFilter.addFilter("QUERY_t#regTime_D_LE", data.getEndRegDate().trim());
			}
			if(StringUtils.isNotBlank(data.getCustomerName()))
			{
				hqlFilter.addFilter("QUERY_t#customerName_S_LK", data.getCustomerName().trim());
			}
			if(StringUtils.isNotBlank(data.getBusinessType()))
			{
				hqlFilter.addFilter("QUERY_t#businessType_S_EQ", data.getBusinessType().trim());
			}
			if(StringUtils.isNotBlank(data.getSim()))
			{
				hqlFilter.addFilter("QUERY_t#sim_S_EQ", data.getSim().trim());
			}
			if(StringUtils.isNotBlank(data.getOnlineStauts()))
			{
				hqlFilter.addFilter("QUERY_t#onlineStauts_S_EQ", data.getOnlineStauts().trim());
			}
			if(StringUtils.isNotBlank(data.getSubSysCode()))
			{
				hqlFilter.addFilter("QUERY_t#subSysCode_S_EQ", data.getSubSysCode().trim());
			}
			if(StringUtils.isNotBlank(data.getOnlineStauts()))
			{
				hqlFilter.addFilter("QUERY_t#onlineStauts_S_EQ", data.getOnlineStauts().trim());
			}
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerName())) {
				hqlFilter.addFilter("QUERY_t#ownerTargetDeviceControllerName_S_LK",data.getOwnerTargetDeviceControllerName().trim());
			}
			if (StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())) {
				hqlFilter.addFilter("QUERY_t#ownerTargetDeviceControllerUpdateScope_S_EQ" ,data.getOwnerTargetDeviceControllerUpdateScope());
			}else if(StringUtils.isNotBlank(data.getSearchMode()) && CollectDevice.SEARCH_MODE_TD_CONTROLLER_SCOPE.equals(data.getSearchMode())) {
				//sql.append("AND ( LENGTH(cd.owner_td_controller_update_scope_id) = 0 or cd.owner_td_controller_update_scope_id is null )" );
				hqlFilter.addFilter("(LENGTH(t.ownerTargetDeviceControllerUpdateScope) = 0 or t.ownerTargetDeviceControllerUpdateScope is null)",null);
			}
		}
		return countByFilter(hqlFilter);
	}

	@Override
	public void setControllerScopeNull(CollectDevice data) {
		
		if(StringUtils.isBlank(data.getOwnerTargetDeviceControllerUpdateScope())){
			   return;
		   }
		   if(data.getSelectAll().equals(CollectDevice.SELECT_ALL_YES)){
			   int pages = 1;
			   int rows  = 500;
			   StringBuilder sqlBuilder = new StringBuilder(200); 
			   while(true){
				   sqlBuilder.append("SELECT t.id FROM  t_mms_collect_device t ");	
				   sqlBuilder.append(" WHERE 1=1 AND t.owner_td_controller_update_scope_id='"+ data.getOwnerTargetDeviceControllerUpdateScope()+"' ");
				   if(StringUtils.isNotBlank(data.getCdRegCode())){
					   sqlBuilder.append("AND t.cd_reg_code LIKE '%" + data.getCdRegCode().trim()+"%' ");
				    }
				    if(StringUtils.isNotBlank(data.getOnlineStauts())){
				    	sqlBuilder.append("AND t.online_stauts = '" + data.getOnlineStauts().trim()+"' ");
				    }
				    if(StringUtils.isNotBlank(data.getCustomerName())){
				    	sqlBuilder.append("AND t.customer_name LIKE '%" + data.getCustomerName().trim()+"%' ");
				    }
				    if (StringUtils.isNotBlank(data.getStartRegDate())) {
				    	sqlBuilder.append("AND UNIX_TIMESTAMP(t.reg_time) >= UNIX_TIMESTAMP('"
								+ data.getStartRegDate().trim() + "') ");
					}
					if (StringUtils.isNotBlank(data.getEndRegDate())) {
						sqlBuilder.append("AND UNIX_TIMESTAMP(t.reg_time) <= UNIX_TIMESTAMP('"
								+ data.getEndRegDate().trim() + "') ");
					}
					if (StringUtils.isNotBlank(data.getBusinessType())) {
						sqlBuilder.append("AND t.business_type = '" + data.getBusinessType()
								+ "' ");
					}
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
						if(cdIds.toString().length() > 0){
						setControllerScopeNull(cdIds.toString());
						}
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
		 String sql = "UPDATE t_mms_collect_device set owner_td_controller_name=null,owner_td_controller_update_scope_id=null ";
		 sql += "WHERE id in("+ cdids+")";
		 executeSql(sql);
	}
	@Override
	public void setControllerScope( CollectDevice data) {
		TdControllerBatchUpdateScope tdControllerBatchUpdateScope = null;
		if(StringUtils.isNotBlank(data.getOwnerTargetDeviceControllerUpdateScope())){
			tdControllerBatchUpdateScope = tdControllerBatchUpdateScopeDao.getById(data.getOwnerTargetDeviceControllerUpdateScope());
		}
		if( null == tdControllerBatchUpdateScope ){
			return;
		}
		data.setOwnerTargetDeviceControllerName(tdControllerBatchUpdateScope.getTdctrollerName());
		
		if(data.getSelectAll().equals(CollectDevice.SELECT_ALL_YES)){
			StringBuilder sql = new StringBuilder(200); 
			int pages = 1;
			int rows  = 500;
			while(true){
				sql.append("SELECT t.id FROM  t_mms_collect_device t ");
				sql.append(" WHERE 1=1 AND (t.owner_td_controller_update_scope_id IS null OR LENGTH(t.owner_td_controller_update_scope_id) = 0 ) ");
			    if(StringUtils.isNotBlank(data.getCdRegCode())){
			    	sql.append("AND t.cd_reg_code LIKE '%" + data.getCdRegCode().trim()+"%' ");
			    }
			    if(StringUtils.isNotBlank(data.getOnlineStauts())){
			    	sql.append("AND t.online_stauts = '" + data.getOnlineStauts().trim()+"' ");
			    }
			    if(StringUtils.isNotBlank(data.getCustomerName())){
			    	sql.append("AND t.customer_name LIKE '%" + data.getCustomerName().trim()+"%' ");
			    }
			    if (StringUtils.isNotBlank(data.getStartRegDate())) {
					sql.append("AND UNIX_TIMESTAMP(t.reg_time) >= UNIX_TIMESTAMP('"
							+ data.getStartRegDate().trim() + "') ");
				}
				if (StringUtils.isNotBlank(data.getEndRegDate())) {
					sql.append("AND UNIX_TIMESTAMP(t.reg_time) <= UNIX_TIMESTAMP('"
							+ data.getEndRegDate().trim() + "') ");
				}
				if (StringUtils.isNotBlank(data.getBusinessType())) {
					sql.append("AND t.business_type = '" + data.getBusinessType()
							+ "' ");
				}
				sql.append(" order by t.cd_reg_code ");
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
	 * @author w1898
	 * @since JDK 1.7
	 */
	public void setControllerScope(String cdids,CollectDevice data){
		String sql ="UPDATE t_mms_collect_device SET owner_td_controller_update_scope_id = '" + data.getOwnerTargetDeviceControllerUpdateScope()+"' ," +
				"owner_td_controller_name = '"+data.getOwnerTargetDeviceControllerName() +"' WHERE id in "+cdids;
		/*Map<String, Object> hashMap= new HashMap<String,Object>();
		hashMap.put("id", data.getOwnerTargetDeviceControllerUpdateScope());
		hashMap.put("cdids", cdids);
		hashMap.put("name", data.getOwnerTargetDeviceControllerName());*/
		executeSql(sql);
	}

	@Override
	public void setControllerScopeNullByTdControllerBatchUpdateScopeId(
			String tdControllerBatchUpdateScopeId) {
		String sql = "UPDATE t_mms_collect_device set owner_td_controller_name=null,owner_td_controller_update_scope_id=null ";
		sql += "WHERE owner_td_controller_update_scope_id ='"+ tdControllerBatchUpdateScopeId +"' " ; 
		executeSql(sql);	
	}

	@Override
	public List<CollectDevice> findbySort(int pages, int rows, String sort,
			String order) {
		StringBuilder sql = new StringBuilder(200); 
		sql.append("SELECT cd.id,cd.cd_reg_code,cd.online_stauts,cd.last_login_time login_time,cd.last_logout_time logout_time,cd.customer_name from t_mms_collect_device cd ");
		sql.append("where 1=1 ");
		sql.append("order by "+sort+" "+ order+" ");
		sql.append(" LIMIT "+(pages-1)*rows+","+rows);
		List<Map> list  =  findBySql(sql.toString());
		List<CollectDevice> listCollectDevices = new ArrayList<CollectDevice>(rows);
		
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			CollectDevice cd = new CollectDevice();
			cd.setId(map.get("id").toString());
			cd.setCdRegCode(map.get("cd_reg_code").toString());
			if(null != map.get("online_stauts")){
				cd.setOnlineStauts(map.get("online_stauts").toString());
			}else{ //如果在线状态为空，则不统计
				continue;
			}
			if(null != map.get("customer_name")){
				cd.setCustomerName(map.get("customer_name").toString());
			}
			if(null != map.get("login_time")){
				cd.setLastLoginTime((Date)map.get("login_time"));
			}
			if(null != map.get("logout_time")){
				cd.setLastLogoutTime((Date)map.get("logout_time"));
			}
			listCollectDevices.add(cd);
		}
		return listCollectDevices;
	}

	@Override
	public int updateCustomer(List<String> cdRegCodeList, String customerCode,
			String customerName,String dnsCode,Short updateType,Date updateTime) {
		String hql = "update CollectDevice t set t.customerCode =:customerCode,t.customerName =:customerName,t.updateTime =:updateTime, " +
				" t.updateType=:updateType,t.dhsCode=:dnsCode "+
				" where t.cdRegCode in (:cdRegCodeList)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("customerCode", customerCode);
		q.setParameter("customerName", customerName);
		q.setTimestamp("updateTime", updateTime);
		q.setParameter("updateType", updateType);
		q.setParameter("dnsCode", dnsCode);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		System.out.println(q.getQueryString());
		return q.executeUpdate();
	}

	@Override
	public List<Object[]> queryDelCd(List<String> cdRegCodeList,String customerCode,String dhsCode) {
		String hql = "select cd.cdRegCode,cd.customerCode,cd.customerName, " +
				" cd.dhsCode from CollectDevice cd"+
				" where cd.cdRegCode in (:cdRegCodeList) and  cd.customerCode is not null and cd.customerCode !=:customerCode " +
				"and cd.dhsCode != :dhsCode ";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		q.setParameter("customerCode", customerCode);
		q.setParameter("dhsCode", dhsCode);
		List<Object[]> list = q.list();
		return list;
	}
	@Override
	public List<CollectDevice> queryUpdateCd(List<String> cdRegCodeList,String customerCode,String dhsCode) {
		String hql = "select cd from CollectDevice cd"+
				" where cd.cdRegCode in (:cdRegCodeList) and  cd.customerCode is not null and cd.customerCode !=:customerCode " +
				"and cd.dhsCode = :dhsCode ";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		q.setParameter("customerCode", customerCode);
		q.setParameter("dhsCode", dhsCode);
		return q.list();
	}
	@Override
	public List<CollectDevice> queryAddCd(List<String> cdRegCodeList,String customerCode,String dhsCode) {
		String hql = "select cd from CollectDevice cd "+
				" where cd.cdRegCode in (:cdRegCodeList) and ( cd.customerCode is null or cd.customerCode !=:customerCode ) " +
				" and (cd.dhsCode is null or cd.dhsCode != :dhsCode)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		q.setParameter("customerCode", customerCode);
		q.setParameter("dhsCode", dhsCode);
		return q.list();
		//List<CollectDevice> list = find(q.getQueryString());
	}
	@Override
	public List<Object[]> queryCdAndCustomer(List<String> cdRegCodeList) {
		String hql = "select cd.cdRegCode,cd.customerCode,cd.customerName,cd.dhsCode " +
				" from CollectDevice cd"+
				" where cd.customerCode is not null and cd.dhsCode is not null" +
				" and cd.cdRegCode in (:cdRegCodeList)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		List<Object[]> list =q.list();
		return list;
	}

	@Override
	public List<Object[]> queryCdsByCustomerCode(String customerCode) {
		
		String hql = "select cd.cdRegCode,cd.customerCode,cd.customerName, " +
				" cd.dhsCode from CollectDevice cd"+
				" where cd.customerCode is not null and cd.customerCode !=:customerCode";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("customerCode", customerCode);
		List<Object[]> list =q.list();
		return list;
	}
	
	//根据客户编码查询采集设备
	@Override
	public List<Object[]> queryCdRegCodeByCustomerCode(String customerCode) {
		
		String hql = "select cd.cdRegCode,cd.customerCode,cd.customerName, " +
				" cd.dhsCode from CollectDevice cd"+
				" where cd.customerCode =:customerCode";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("customerCode", customerCode);
		List<Object[]> list =q.list();
		return list;
	}

	@Override
	public int updateCustomer(String customerCode, String customerName,
			String dnsCode, Short updateType, Date updateTime) {
		String hql = "update CollectDevice t set t.customerCode =:customerCodeNull,t.customerName =:customerName,t.updateTime =:updateTime, " +
				" t.updateType=:updateType,t.dhsCode=:dnsCode "+
				" where t.customerCode=:customerCode";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("customerCodeNull", null);
		q.setParameter("customerCode", customerCode);
		q.setParameter("customerName", null);
		q.setTimestamp("updateTime", updateTime);
		q.setParameter("updateType", updateType);
		q.setParameter("dnsCode", null);
		return q.executeUpdate();
	}
	@Override
	public int updateCustomerName(String customerCode, String customerName){
		if(StringUtils.isNotBlank(customerCode)  && StringUtils.isNotBlank(customerName)){
			String hql = "update CollectDevice t set t.customerName =:customerName " +
					" where t.customerCode =:customerCode";
			Query q = superBaseDao.getCurrentSession().createQuery(hql);
			q.setParameter("customerCode", customerCode);
			q.setParameter("customerName", customerName);
			return q.executeUpdate();
		}
		return 0;
	}

	@Override
	public boolean queryRegCodeAndCustomer(String cdRegCode, String customerCode) {
		String hql = "select cd from CollectDevice cd "+
				" where cd.customerCode =:customerCode " +
				" and cd.cdRegCode =:cdRegCode ";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("cdRegCode", cdRegCode);
		q.setParameter("customerCode", customerCode);
		 if(q.list() == null || q.list().size() == 0 ){
			 return false;
		 }
		return true;
	}

	@Override
	public Long countfindBySql(CollectDevice data) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) rows FROM t_mms_target_device t1 LEFT JOIN t_mms_td_controller_data_command_lib t2 ON t1.td_type_controller_id_fk = t2.td_controller_type_id_fk  ");
		
		sql.append("where 1=1 ");
		
		if(data != null){
			if(StringUtils.isNotBlank(data.getId())){
				sql.append("and t1.id ='" + data.getId() + "' ");
			}
		}
		BigInteger size =  countBySql(sql.toString());
		return size.longValue();
	}

	@Override
	public List<CollectDevice> findBySql(CollectDevice data, int rows,
			int pages, String sort, String order) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT t2.* FROM t_mms_target_device t1 LEFT JOIN t_mms_td_controller_data_command_lib t2 ON t1.td_type_controller_id_fk = t2.td_controller_type_id_fk  ");
		
		sql.append("where 1=1 ");
		
		
		if(data != null){
			if(StringUtils.isNotBlank(data.getId())){
				sql.append("and t1.id='" + data.getId() + "' ");
			}
		}
		
		sql.append("order by t1.cd_reg_code  desc ");
		
		if(rows > 0 && pages > 0 ){
			sql.append(" limit " + (pages - 1) * rows + "," + rows);
		}
		//List<CdRegisterCode> list = findBySql(sql.toString());
		List<Map> listMap = findBySql(sql.toString());
		List<CollectDevice> list = new ArrayList<CollectDevice>(0);
		
		for (Map map : listMap) {
			CollectDevice collectDevice = new CollectDevice();
			if(map.get("command_name") != null){
				collectDevice.setCommandName(map.get("command_name").toString());
			}
			if(map.get("command_templet") != null){
				collectDevice.setCommandTemplet(map.get("command_templet").toString());
			}
			if(map.get("command_checkType") != null){
				collectDevice.setCommandCheckType(map.get("command_checkType").toString());
			}
			if(map.get("command_format") != null){
				collectDevice.setCommandDataFormat(map.get("command_format").toString());
			}
			if(map.get("command_params") != null){
				collectDevice.setCommandPara(map.get("command_params").toString());
			}
			if(map.get("command_rsp_length") != null){
				collectDevice.setCmdRspDataLength(Integer.parseInt(map.get("command_rsp_length").toString()));
			}
			
			list.add(collectDevice);
		}
		
		return list;
	}
}
