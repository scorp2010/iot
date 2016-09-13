/**
 * Project Name:iotgp-common-db
 * File Name:CdSoftWareInfoDaoImpl.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.BaseDao;
import cn.inovance.iotgp.common.dao.CdSoftWareInfoDao;
import cn.inovance.iotgp.common.dao.SoftWareInfoDao;
import cn.inovance.iotgp.common.dao.SoftWareUpdateJobDetailsDao;
import cn.inovance.iotgp.common.domain.CdSoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareUpdateDetails;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.status.StatusEnum;

/**
 * ClassName:CdSoftWareInfoDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-22 下午4:24:02 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class CdSoftWareInfoDaoImpl  extends BaseDaoImpl<CdSoftwareInfo> implements CdSoftWareInfoDao {
	@Autowired
	private SoftWareInfoDao softWareInfoDao;
	
	@Autowired
	private SoftWareUpdateJobDetailsDao softwareUpdateDetailsDao;

	@Override
	public List<CdSoftwareInfo> findBySql(CdSoftwareInfo data,int rows, int page,String sort,String order) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT tmcsi.* ,tmsud.version new_version FROM t_mms_cd_software_info tmcsi ");
		sql.append("left join t_mms_software_update_details tmsud ");
		sql.append("on tmsud.soft_name=tmcsi.name and tmsud.update_result='"+ StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value+ "' ") ;
		if(StringUtils.isNotBlank(data.getCdRegCode())){
			sql.append("and tmsud.cd_reg_code='"+ data.getCdRegCode()+ "' ") ;
		}
		//sql.append("left join t_mms_software_info tmsi on tmsi.name=tmcsi.name ") ;
		sql.append("where 1=1 ");
		//sql.append("and tmsi.soft_type='"+ StatusEnum.SOFTWARE_UPDATE_JOB_TYPE_CD.value +"' ");
		if(StringUtils.isNotBlank(data.getCdRegCode())){
			sql.append("and tmcsi.cd_reg_code='" + data.getCdRegCode() + "' ");
		}
		sql.append("order by tmcsi.update_time desc ");
		if(rows > 0 && page > 0 ){
			sql.append("limit " + (page - 1) * rows + "," + rows);
		}
		List<Map> list = findBySql(sql.toString());
		
		
		
		
	
		StringBuilder jobSql=new StringBuilder(200);
		StringBuilder softInfoSql=new StringBuilder(200);
		
		jobSql.append("select i.create_time as createTime from t_mms_software_info i where 1=1 "
				+ " and i.name=:name and i.version=:version ");
		
		softInfoSql.append("select d.update_time as updateTime from  t_mms_software_update_details d where 1=1 "
				+ " and d.cd_reg_code=:cdRegCode and d.soft_name=:name  and d.version=:version ");
		
		Map<String, Type> jobResultValueTypeMap=new HashMap<String, Type>();
		Map<String, Type> softResultValueTypeMap=new HashMap<String, Type>();
		softResultValueTypeMap.put("updateTime", StandardBasicTypes.TIMESTAMP);
		jobResultValueTypeMap.put("createTime", StandardBasicTypes.TIMESTAMP);
		
		Map<String, Object> jobParams=new HashMap<String,Object>();
		Map<String, Object> softInfoParams=new HashMap<String,Object>();
		
		List<CdSoftwareInfo> tdsoftwares = new ArrayList<CdSoftwareInfo>(0);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Map map:list){
			CdSoftwareInfo obj = new CdSoftwareInfo();
			if(map.get("id")!= null ){
				obj.setId(map.get("id").toString());
			}
			if(map.get("cd_reg_code")!= null ){
				obj.setCdRegCode(map.get("cd_reg_code").toString());
				softInfoParams.put("cdRegCode", map.get("cd_reg_code").toString());
			}
			if(map.get("name")!= null ){
				obj.setName(map.get("name").toString());
				jobParams.put("name", map.get("name").toString());
				softInfoParams.put("name", map.get("name").toString());
			}
			if(map.get("version")!= null ){
				obj.setVersion(map.get("version").toString());
				jobParams.put("version", map.get("version").toString());
				softInfoParams.put("version", map.get("version").toString());
			}
			
			try {
				
				if(map.get("create_time")!= null ){
					//obj.setCreateTime(sdf.parse(map.get("create_time").toString()));
					obj.setCreateTime(DateUtils.parseDate(map.get("create_time").toString(), "yyyy-MM-dd HH:mm:ss.S"));
				}
				if(map.get("update_time")!= null ){
					//obj.setUpdateTime(sdf.parse(map.get("update_time").toString()));
					obj.setUpdateTime(DateUtils.parseDate(map.get("update_time").toString(), "yyyy-MM-dd HH:mm:ss.S"));
				}
			} catch (ParseException e) {
					e.printStackTrace();	
			}
			if(map.get("new_version")!= null ){
				obj.setNewVersion(map.get("new_version").toString());
			}
			List<SoftwareInfo>  jobList=softWareInfoDao.findObjListBySql(jobSql.toString(),jobParams , jobResultValueTypeMap ,  SoftwareInfo.class);
			List<SoftwareUpdateDetails> softList=softwareUpdateDetailsDao.findObjListBySql(softInfoSql.toString(), softInfoParams,softResultValueTypeMap,SoftwareUpdateDetails.class);
			if (softList!=null && softList.size()>0) {
				obj.setSoftUpdateDate(softList.get(0).getUpdateTime());
			}
			if (jobList!=null && jobList.size()>0) {
				obj.setJobCreatDate(jobList.get(0).getCreateTime());
			}
			
			tdsoftwares.add(obj);
			
		}
		return tdsoftwares;
	}

	@Override
	public long countfindBySql(CdSoftwareInfo data) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) rows FROM t_mms_cd_software_info tmcsi ");
		sql.append("left join t_mms_software_update_details tmsud ");
		sql.append("on tmsud.soft_name=tmcsi.name and tmsud.update_result='"+ StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value+ "' ") ;
		if(StringUtils.isNotBlank(data.getCdRegCode())){
			sql.append("and tmsud.cd_reg_code='"+ data.getCdRegCode()+ "' ") ;
		}
		//sql.append("left join t_mms_software_info tmsi on tmsi.name=tmcsi.name ") ;
		sql.append("where 1=1 ");
	   // sql.append("and tmsi.soft_type='"+ StatusEnum.SOFTWARE_UPDATE_JOB_TYPE_CD.value +"' ");
		if(data != null && StringUtils.isNotBlank(data.getCdRegCode())){
			sql.append("and tmcsi.cd_reg_code='" + data.getCdRegCode() + "' ");
		}
		BigInteger size =  countBySql(sql.toString());
		return size.longValue();
	}
	
	@Override
	public List<CdSoftwareInfo> queryCdsByCdRegCodeAndSoft(CdSoftwareInfo data) {
		String hql = "select distinct cd " +
				"from CdSoftwareInfo cd"+
				" where cd.cdRegCode in ("+data.getCdRegCode() +") and cd.name = '"+ data.getName() + "' and cd.version = '" + data.getVersion() + "'";
		List<CdSoftwareInfo> list =find(hql);
		return list;
	}
	
	@Override
	public List<CdSoftwareInfo> queryCdsByCdRegCodeAndSoftName(CdSoftwareInfo data) {
		String hql = "select distinct cd " +
				"from CdSoftwareInfo cd"+
				" where cd.cdRegCode in ("+data.getCdRegCode() +") and cd.name = '"+ data.getName() +  "'";
		List<CdSoftwareInfo> list =find(hql);
		return list;
	}
	
	@Override
	public List<CdSoftwareInfo> queryListByCdRegCode(CdSoftwareInfo data) {
		
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#cdRegCode_S_EQ", data.getCdRegCode());
		return findByFilter(hqlFilter);
	}

}

