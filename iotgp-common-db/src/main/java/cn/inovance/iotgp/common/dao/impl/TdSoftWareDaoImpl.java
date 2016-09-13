/**
 * Project Name:iotgp-common-db
 * File Name:TdSoftWareDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-7-16下午8:24:01
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.TdSoftWareDao;
import cn.inovance.iotgp.common.domain.TdSoftWare;
import cn.inovance.iotgp.common.status.StatusEnum;

/**
 * ClassName:TdSoftWareDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-7-16 下午8:24:01 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class TdSoftWareDaoImpl implements TdSoftWareDao {

	@Autowired
	SuperBaseDao<TdSoftWare> superBaseDao;
	@Override
	public List<TdSoftWare> find(TdSoftWare tdSoftWare, int rows, int page) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tmtd.id,tmtd.cd_reg_code," +
				"tmtd.address_code,tmtd.td_type_code,tsi.name,tmcsi.version cur_version,tsi.version new_version,"
				+ "tsi.soft_file_name,tsi.soft_file_path,tsi.soft_file_length,tsi.soft_file_md5 "
				+ "FROM t_mms_target_device tmtd "
				+ "LEFT JOIN t_mms_td_controller_type ttct "
				+ "ON tmtd.td_type_controller_id_fk = ttct.id "
				+ "LEFT JOIN t_mms_software_info tsi "
				+ "ON tsi.id = ttct.software_info_id "
				+ "LEFT JOIN t_mms_cd_software_info tmcsi "
				+ "ON tmcsi.name=tsi.name and tmcsi.cd_reg_code=tmtd.cd_reg_code "
				+ " where 1=1 ");
		
		if (StringUtils.isNotBlank(tdSoftWare.getCdRegCode())) {
			sql.append(" and  tmtd.cd_reg_code='" + tdSoftWare.getCdRegCode()
					+ "' ");
		}
		if (StringUtils.isNotBlank(tdSoftWare.getName())) {
			sql.append(" and  tsi.name='" + tdSoftWare.getName()
					+ "' ");
		}
		if (StringUtils.isNotBlank(tdSoftWare.getNewVersion())) {
			sql.append(" and  tsi.version='" + tdSoftWare.getNewVersion()
					+ "' ");
		}
		if (StringUtils.isNotBlank(tdSoftWare.getTdControllerTypeId())) {
			sql.append(" and  ttct.id='" + tdSoftWare.getTdControllerTypeId()
					+ "' ");
		}
		
		sql.append(" order by tmtd.cd_reg_code ");
		if(rows > 0 && page > 0 ){
			sql.append("limit " + (page - 1) * rows + "," + rows);
		}

		List<Map> list = superBaseDao.findBySql(sql.toString());
    
		List<TdSoftWare> tdsoftwares = new ArrayList<TdSoftWare>(0);
		
		for(Map map:list){
			TdSoftWare obj = new TdSoftWare();
			if(map.get("id")!= null ){
				obj.setId(map.get("id").toString());
			}
			if(map.get("cd_reg_code")!= null ){
				obj.setCdRegCode(map.get("cd_reg_code").toString());
			}
			if(map.get("address_code")!= null ){
				obj.setAddressCode(Integer.valueOf(map.get("address_code").toString()));
			}
			if(map.get("td_type_code")!= null ){
				obj.setTdTypeCode(Integer.valueOf(map.get("td_type_code").toString()));
			}
			if(map.get("name")!= null ){
				obj.setName(map.get("name").toString());
			}
			if(map.get("cur_version")!= null ){
				obj.setCurVersion(map.get("cur_version").toString());
			}
			if(map.get("new_version")!= null ){
				obj.setNewVersion(map.get("new_version").toString());
			}
			if(map.get("soft_file_name")!= null ){
				obj.setFileName(map.get("soft_file_name").toString());
			}
			if(map.get("soft_file_path")!= null ){
				obj.setFilePath(map.get("soft_file_path").toString());
			}
			if(map.get("soft_file_length")!= null ){
				obj.setFileLength(Long.valueOf(map.get("soft_file_length").toString()));
			}
			if(map.get("soft_file_md5")!= null ){
				obj.setFileMd5(map.get("soft_file_md5").toString());
			}	
			tdsoftwares.add(obj);
		}
		return tdsoftwares;
	}
	@Override
	public List<TdSoftWare> grid(TdSoftWare tdSoftWare, int rows, int page) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tmtd.id,tmtd.cd_reg_code," +
				"tmtd.address_code,tmtd.td_type_code,tsi.name,tmcsi.version cur_version,tmtsuj.soft_version new_version "
				+ "FROM t_mms_target_device tmtd "
				+ "LEFT JOIN t_mms_td_controller_type ttct "
				+ "ON tmtd.td_type_controller_id_fk = ttct.id "
				+ "LEFT JOIN t_mms_software_info tsi "
				+ "ON tsi.id = ttct.software_info_id "
				+ "LEFT JOIN t_mms_cd_software_info tmcsi "
				+ "ON tmcsi.name=tsi.name ");
		if (StringUtils.isNotBlank(tdSoftWare.getCdRegCode())) {
			sql.append(" and  tmcsi.cd_reg_code='" + tdSoftWare.getCdRegCode()
					+ "' ");
		}
		sql.append("LEFT JOIN t_mms_td_software_update_job tmtsuj "
		+ "ON tmtsuj.soft_name=tsi.name and tmtsuj.update_result='" +StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value +"' ");
		if (StringUtils.isNotBlank(tdSoftWare.getCdRegCode())) {
			sql.append(" and tmtsuj.cd_reg_code='"+ tdSoftWare.getCdRegCode() +"' ");
		}
		sql.append(" where 1=1 ");
		
		if (StringUtils.isNotBlank(tdSoftWare.getCdRegCode())) {
			sql.append(" and  tmtd.cd_reg_code='" + tdSoftWare.getCdRegCode()
					+ "' ");
		}
		if (StringUtils.isNotBlank(tdSoftWare.getName())) {
			sql.append(" and  tsi.name='" + tdSoftWare.getName()
					+ "' ");
		}
		if (StringUtils.isNotBlank(tdSoftWare.getNewVersion())) {
			sql.append(" and  tsi.version='" + tdSoftWare.getNewVersion()
					+ "' ");
		}
		if (StringUtils.isNotBlank(tdSoftWare.getTdControllerTypeId())) {
			sql.append(" and  ttct.id='" + tdSoftWare.getTdControllerTypeId()
					+ "' ");
		}
		sql.append(" order by tmtd.cd_reg_code ");
		if(rows > 0 && page > 0 ){
			sql.append("limit " + (page - 1) * rows + "," + rows);
		}

		List<Map> list = superBaseDao.findBySql(sql.toString());
    
		List<TdSoftWare> tdsoftwares = new ArrayList<TdSoftWare>(0);
		
		for(Map map:list){
			TdSoftWare obj = new TdSoftWare();
			if(map.get("id")!= null ){
				obj.setId(map.get("id").toString());
			}
			if(map.get("cd_reg_code")!= null ){
				obj.setCdRegCode(map.get("cd_reg_code").toString());
			}
			if(map.get("address_code")!= null ){
				obj.setAddressCode(Integer.valueOf(map.get("address_code").toString()));
			}
			if(map.get("td_type_code")!= null ){
				obj.setTdTypeCode(Integer.valueOf(map.get("td_type_code").toString()));
			}
			if(map.get("name")!= null ){
				obj.setName(map.get("name").toString());
			}
			if(map.get("cur_version")!= null ){
				obj.setCurVersion(map.get("cur_version").toString());
			}
			if(map.get("new_version")!= null ){
				obj.setNewVersion(map.get("new_version").toString());
			}
			tdsoftwares.add(obj);
		}
		return tdsoftwares;
	
	}

	@Override
	public TdSoftWare getByTdControllerTypeId(TdSoftWare tdSoftWare) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tmsi.name,tmsi.version version "
				+ "FROM t_mms_software_info tmsi "
				+ "JOIN t_mms_td_controller_type ttct "
				+ "ON ttct.software_info_id = tmsi.id ");
		sql.append("where 1=1 ");
		if (StringUtils.isNotBlank(tdSoftWare.getTdControllerTypeId())) {
			sql.append(" and ttct.id='" + tdSoftWare.getTdControllerTypeId()
					+ "' ");
		}
		List<Map> list = superBaseDao.findBySql(sql.toString());
		for(Map map:list){
			TdSoftWare obj = new TdSoftWare();
			if(map.get("name")!= null ){
				obj.setName(map.get("name").toString());
			}
			if(map.get("version")!= null ){
				obj.setCurVersion(map.get("version").toString());
			}
			return obj;
		}
		return null;
	}

}
