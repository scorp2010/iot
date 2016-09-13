/**
 * Project Name:iotgp-common-db
 * File Name:TdSoftWareUpdateJobDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-7-18下午6:59:19
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.TdSoftWareUpdateJobDao;
import cn.inovance.iotgp.common.domain.TdSoftWareUpdateJob;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.status.StatusEnum;

/**
 * ClassName:TdSoftWareUpdateJobDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-7-18 下午6:59:19 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class TdSoftWareUpdateJobDaoImpl extends
		BaseDaoImpl<TdSoftWareUpdateJob> implements TdSoftWareUpdateJobDao {

	@Resource
	SuperBaseDao<TdSoftWareUpdateJob> superBaseDao;
	@Override
	public long countFindByData(TdSoftWareUpdateJob data) {
		HqlFilter hqlFilter = new HqlFilter();

		if (null != data.getCdRegCode() && !"".equals(data.getCdRegCode())) {
			hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK", data.getCdRegCode());
		}
		if (StringUtils.isNotBlank(data.getUpdateResult())) {
			hqlFilter.addFilter("QUERY_t#updateResult_S_EQ",
					data.getUpdateResult());
		}
		if (StringUtils.isNotBlank(data.getSoftName())) {
			hqlFilter.addFilter("QUERY_t#softName_S_LK", data.getSoftName());
		}
		if (StringUtils.isNotBlank(data.getSoftVersion())) {
			hqlFilter.addFilter("QUERY_t#softVersion_S_EQ",
					data.getSoftVersion());
		}
		if (null != data.getSdate() && !"".equals(data.getSdate())) {
			hqlFilter.addFilter("QUERY_t#createTime_D_GE", data.getSdate());
		}
		if (null != data.getEdate() && !"".equals(data.getEdate())) {
			hqlFilter.addFilter("QUERY_t#createTime_D_LE", data.getEdate());
		}
		if (StringUtils.isNotBlank(data.getTdControllerBatchUpdateScopeId())) {
			hqlFilter.addFilter("QUERY_t#tdControllerBatchUpdateScopeId_S_EQ",
					data.getTdControllerBatchUpdateScopeId());
		}
		return countByFilter(hqlFilter);
	}

	
	@Override
	public void updateNoUpdateJobOverLaped(TdSoftWareUpdateJob data) {

		if (StringUtils.isBlank(data.getCdRegCode())) {
			return;
		}
		String sql = "update t_mms_td_software_update_job set update_result='"
				+ StatusEnum.SOFTWARE_UPDATE_DETAILS_OVERLAPED.value + "' ";
		sql += "where 1=1 and (update_result ='"+ StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value 
				+ "' or update_result = '" + StatusEnum.SOFTWARE_UPDATE_DETAILS_DOWNLING.value 
				+"' or update_result= '"+ StatusEnum.SOFTWARE_UPDATE_DETAILS_INSTALLING.value+"') ";
		sql += "and not (soft_version ='" + data.getSoftVersion()
				+ "' and soft_name='" + data.getSoftName() + "') ";
		sql += "and cd_reg_code='" + data.getCdRegCode() + "' ";
		/*
		 * if(StringUtils.isNotBlank(data.getSoftName())){
		 * 
		 * sql += "and soft_name='"+ data.getSoftName() + "' "; }
		 * if(StringUtils.isNotBlank(data.getSoftVersion())){
		 * 
		 * sql += "and soft_version !='"+ data.getSoftVersion() + "' "; }
		 */
		executeSql(sql);
	}

	@Override
	public long updateAndCountFindByData(TdSoftWareUpdateJob data) {
		
		StringBuilder hql = new StringBuilder(); 
		Map<String, Object> params= new HashMap<String, Object>(5);
		hql.append("select job from TdSoftWareUpdateJob job where 1=1 ");
		if (null != data.getCdRegCode() && !"".equals(data.getCdRegCode())){
			hql.append("and job.cdRegCode like :cd ");
			params.put("cd", "%"+data.getCdRegCode()+"%");
		}
		hql.append("and (job.updateResult = :r1 or job.updateResult = :r2 or job.updateResult = :r3 ) ");
		params.put("r1", StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value);
		params.put("r2", StatusEnum.SOFTWARE_UPDATE_DETAILS_DOWNLING.value);
		params.put("r3", StatusEnum.SOFTWARE_UPDATE_DETAILS_INSTALLING.value);
		
		if (StringUtils.isNotBlank(data.getSoftName())) {
			hql.append("and job.softName like :softName ");
			params.put("softName", "%"+data.getSoftName()+"%");
		}
		if (StringUtils.isNotBlank(data.getSoftVersion())) {
			hql.append("and job.softVersion = :softVersion ");
			params.put("softVersion", data.getSoftVersion());
		}
		if (null != data.getSdate() && !"".equals(data.getSdate())) {
			try {
				params.put("sdate", DateUtils.parseDate(data.getSdate(), new String[] {
						"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
						"yyyy-MM-dd", "yyyy/MM/dd" }));
				hql.append("and job.createTime >=:sdate ");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (null != data.getEdate() && !"".equals(data.getEdate())) {

			try {
				params.put("enddata", DateUtils.parseDate(data.getEdate(), new String[] {
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
					"yyyy-MM-dd", "yyyy/MM/dd" }));
				hql.append("and job.createTime <= :enddata");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*HqlFilter hqlFilter = new HqlFilter();

		if (null != data.getCdRegCode() && !"".equals(data.getCdRegCode())) {
			hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK", data.getCdRegCode());
		}
		if (StringUtils.isNotBlank(data.getUpdateResult())) {
			hqlFilter.addFilter("QUERY_t#updateResult_S_EQ",
					data.getUpdateResult());
		}
		if (StringUtils.isNotBlank(data.getSoftName())) {
			hqlFilter.addFilter("QUERY_t#softName_S_LK", data.getSoftName());
		}
		if (StringUtils.isNotBlank(data.getSoftVersion())) {
			hqlFilter.addFilter("QUERY_t#softVersion_S_EQ",
					data.getSoftVersion());
		}
		if (null != data.getSdate() && !"".equals(data.getSdate())) {
			hqlFilter.addFilter("QUERY_t#createTime_D_GE", data.getSdate());
		}
		if (null != data.getEdate() && !"".equals(data.getEdate())) {
			hqlFilter.addFilter("QUERY_t#createTime_D_LE", data.getEdate());
		}
		*/
		List<TdSoftWareUpdateJob> list = find(hql.toString(), params);
		if(list != null && list.size()>0  && StringUtils.isNotBlank(data.getTdControllerBatchUpdateScopeId())){
			for(TdSoftWareUpdateJob each:list){
				each.setTdControllerBatchUpdateScopeId(data.getTdControllerBatchUpdateScopeId());
				update(each);
			}
			return 1;
		}
		else if(list != null && list.size()>0){
			return 1;
		}
		return 0;
	}

	@Override
	public List<TdSoftWareUpdateJob> findUniqueByNameVersionNoFinish(
			TdSoftWareUpdateJob data) {

		StringBuilder sql = new StringBuilder();
		sql.append("from TdSoftWareUpdateJob t ");
		sql.append("where 1=1 ");
		sql.append("and t.cdRegCode=:cdRegCode ");
		sql.append("and t.softName=:softName ");
		sql.append("and ( t.updateResult = :noUpdate or t.updateResult = :install or  t.updateResult=:download ) ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdRegCode", data.getCdRegCode());
		params.put("softName", data.getSoftName());
		params.put("noUpdate",
				StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value);
		params.put("install",
				StatusEnum.SOFTWARE_UPDATE_DETAILS_INSTALLING.value);
		params.put("download",
				StatusEnum.SOFTWARE_UPDATE_DETAILS_DOWNLING.value);
		return find(sql.toString(),params);
	}

	@Override
	public void setJobTdScopeScopeNull(String tdControllerBatchUpdateScopeId) {

		if (StringUtils.isBlank(tdControllerBatchUpdateScopeId)) {
			return;
		}
		String sql = "update t_mms_td_software_update_job set tdcontroller_batchupdate_scope_id=null "
				+ "where tdcontroller_batchupdate_scope_id='"+ tdControllerBatchUpdateScopeId + "'";
		executeSql(sql);

	}

}
