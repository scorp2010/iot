/**
 * Project Name:iotgp-common-db
 * File Name:CdEventDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-7-22下午4:24:02
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdEventDao;
import cn.inovance.iotgp.common.domain.CdEventLog;
import cn.inovance.iotgp.common.domain.CollectDevice;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.nopersistence.domain.CdEventLogBean;
import cn.inovance.iotgp.common.util.DateUtil;

/**
 * ClassName:CdEventDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-22 下午4:24:02 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class CdEventDaoImpl  extends BaseDaoImpl<CdEventLog> implements CdEventDao {

	@Override
	public List<CdEventLogBean> findBySql(CdEventLogBean data,int rows, int page,String sort,String order) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT tmcel.* FROM t_mms_cd_event_log tmcel where 1 = 1 ");
		if(StringUtils.isNotBlank(data.getCdRegCode())){
			sql.append("and tmcel.cd_reg_code='" + data.getCdRegCode() + "' ");
		}
		sql.append("order by tmcel.last_update_time desc ");
		if(rows > 0 && page > 0 ){
			sql.append("limit " + (page - 1) * rows + "," + rows);
		}
		List<Map> list = findBySql(sql.toString());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CdEventLogBean> cdEventLogs = new ArrayList<CdEventLogBean>(0);
		for(Map map:list){
			CdEventLogBean obj = new CdEventLogBean();
			if(map.get("id")!= null ){
				obj.setId(map.get("id").toString());
			}
			if(map.get("cd_reg_code")!= null ){
				obj.setCdRegCode(map.get("cd_reg_code").toString());
			}
			if(map.get("event_data")!= null ){
				obj.setEventData(map.get("event_data").toString());
			}
			if(map.get("td_address")!= null ){
				obj.setTdAddress(map.get("td_address").toString());
			}
			if(map.get("event_seq")!= null ){
				obj.setEventSeq(Long.valueOf(map.get("event_seq").toString()));
			}
			if(map.get("event_code")!= null ){
				obj.setEventCode(Integer.valueOf(map.get("event_code").toString()));
			}
			if(map.get("event_status")!= null ){
				obj.setEventStatus(map.get("event_status").toString());
			}
			
			if(map.get("event_real_time_datas")!= null ){
				obj.setEventRealTimeDatas(map.get("event_real_time_datas").toString());
			}
			if(map.get("customer_code")!= null ){
				obj.setCustomerCode(map.get("customer_code").toString());
			}
			if(map.get("dhs_code")!= null ){
				obj.setDhsCode(map.get("dhs_code").toString());
			}
			
			try {
				if(map.get("event_time")!= null){
					long timestamp= ((BigInteger)map.get("event_time")).longValue();
					obj.setEventTime(DateUtil.getLocalTime(new Date(timestamp*1000)));
				}
				if(map.get("last_update_time")!= null){
					obj.setLastUpdateTime(DateUtils.parseDate(sdf.format(map.get("last_update_time")).toString(), "yyyy-MM-dd HH:mm:ss"));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cdEventLogs.add(obj);
			
		}
		return cdEventLogs;
	}

	@Override
	public long countfindBySql(CdEventLogBean data) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) rows FROM t_mms_cd_event_log tmcel where 1 = 1 ");
		if(data != null && StringUtils.isNotBlank(data.getCdRegCode())){
			sql.append("and tmcel.cd_reg_code='" + data.getCdRegCode() + "' ");
		}
		BigInteger size =  countBySql(sql.toString());
		
		return size.longValue();
	}

	@Override
	public List<CdEventLog> findByHql(CdEventLog data,int rows, int page,String sort,String order) {
		
		HqlFilter hqlFilter  = new HqlFilter();
		if( null != data ){
			if(StringUtils.isNotBlank(data.getCdRegCode()))
			{
				hqlFilter.addFilter("QUERY_t#cdRegCode_S_EQ", data.getCdRegCode().trim());
			}
			if (null != (data.getEventCode())) {
				hqlFilter.addFilter("QUERY_t#eventCode_I_EQ", data.getEventCode().toString());
			}
			if (StringUtils.isNotBlank(data.getEventData())) {
				hqlFilter.addFilter("QUERY_t#eventData_S_EQ", data.getEventData().trim());
			}
			if(null != (data.getEventStatus()))
			{
				hqlFilter.addFilter("QUERY_t#eventStatus_I_EQ", data.getEventStatus().toString());
			}
		}
		if(StringUtils.isNotBlank(sort)) {
		//	sql.append("order by "+sortMap.get(sort)+" ");
			hqlFilter.addSort(sort);
		}else 
		{
			hqlFilter.addSort("lastUpdateTime");
		}
		if (StringUtils.isNotBlank(order)) {
			hqlFilter.addOrder(order);
		} else {
			hqlFilter.addOrder("desc");
		}
		return findByFilter(hqlFilter, page, rows);
	}

}

