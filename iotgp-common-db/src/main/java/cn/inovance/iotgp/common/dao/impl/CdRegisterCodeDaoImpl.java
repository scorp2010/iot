/**
 * Project Name:cds
 * File Name:CdRegisterCodeDaoImpl.java
 * Package Name:cn.inovance.iotgp.cds.dao.impl
 * Date:2014-4-24上午9:07:01
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdRegisterCodeDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CdRegisterCode;
import cn.inovance.iotgp.common.domain.CdSoftwareInfo;
import cn.inovance.iotgp.common.domain.CollectDevice;
import cn.inovance.iotgp.common.status.StatusEnum;
import cn.inovance.iotgp.common.util.ClassUtils;

/**
 * ClassName:CdRegisterCodeDaoImpl.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CdRegisterCodeDaoImpl extends BaseDaoImpl<CdRegisterCode>
		implements CdRegisterCodeDao {
 
	@Autowired
	private SuperBaseDao superBaseDao;

	@Override
	public CdRegisterCode getUniqueCdRegisterCode(String propertyName,
			String value) {
		if (!ClassUtils.getFiledName(CdRegisterCode.class).contains(propertyName)){
			return null;
		}
		String hql = "select distinct t from CdRegisterCode t where t." + propertyName +"='"+ value +"'";
		return getByHql(hql);
	}

	@Override
	public int updateCustomer(List<String> cdRegCodeList, String customerCode,
			String customerName) {
		String hql = "update CdRegisterCode t set t.customerCode =:customerCode,t.customerName =:customerName,t.updateTime =:updateTime " +
				" where t.regCode in (:cdRegCodeList)";
		
		
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("customerCode", customerCode);
		q.setParameter("customerName", customerName);
		q.setTimestamp("updateTime", new Date());
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		System.out.println(q.getQueryString());
		return q.executeUpdate();
		
	}
	
	public List<Object[]> queryCdAndCustomer(List<String> cdRegCodeList) {
		String hql = "select cd.regCode,cd.customerCode,cd.customerName " +
				" from CdRegisterCode cd"+
				" where cd.customerCode is not null" +
				" and cd.regCode in (:cdRegCodeList)";
		
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		
		
		List<Object[]> list =q.list();
		
		return list;
		
	}

	@Override
	public String getCdRegCodeCustomerCode(String cdRegCode) {
		String hql = "select cd.customerCode " +
				" from CdRegisterCode cd"+
				" where cd.customerCode is not null" +
				" and cd.regCode = :cdRegCode";
	
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("cdRegCode", cdRegCode);
		List<Object> list =q.list();
		if(null !=list && list.size() > 0 ){
				return list.get(0).toString();
		}
		return null;
		
	}
	@Override
	public String getCdRegCodeByProductSn(String productSn) {
		String hql = "select cd.regCode " +
				" from CdRegisterCode cd"+
				" where cd.productSn = :productSn";
	
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameter("productSn", productSn);
		List<Object> list =q.list();
		if(null !=list && list.size() > 0 ){
				return list.get(0).toString();
		}
		return null;
		
	}

	@Override
	public int updateCdRegCodeCustomerBame(String customerCode,
			String customerName) {
		
		if(StringUtils.isNotBlank(customerCode)  && StringUtils.isNotBlank(customerName)){
			
			String hql = "update CdRegisterCode t set t.customerName =:customerName " +
					" where t.customerCode =:customerCode";
			Query q = superBaseDao.getCurrentSession().createQuery(hql);
			q.setParameter("customerCode", customerCode);
			q.setParameter("customerName", customerName);
			return q.executeUpdate();
			
		}
		return 0;
	}

	@Override
	public List<CdRegisterCode> findBySql(CdRegisterCode data, int rows,
			int page, String sort, String order) {
		StringBuilder sql = new StringBuilder();
		
		final Map <String,String> sortMap = new HashMap<String,String>(0);
		sortMap.put("regCode", "reg_code");
		sortMap.put("mac", "mac");
		sortMap.put("productModel", "package_model");
		sortMap.put("randomCode", "random_code");
		sortMap.put("encryptSn", "encrypt_sn");
		sortMap.put("productionDate", "production_date");
		sortMap.put("testTime", "test_time");
		sortMap.put("activeTime", "active_time");
		
		sql.append("Select tccrct.*,tccm.package_model from t_cds_cd_register_code tccrct left join t_cds_cd_model tccm on tccm.materiel = tccrct.product_model ");
		
		sql.append("where 1=1 ");
		
		
		if(data != null){
			if(StringUtils.isNotBlank(data.getProductModel())){
				sql.append("and tccrct.product_model='" + data.getProductModel() + "' ");
			}
			if(StringUtils.isNotBlank(data.getRegCode())){
				sql.append("and tccrct.reg_code like '%" + data.getRegCode() + "%' ");
			}
			if(StringUtils.isNotBlank(data.getMac())){
				sql.append("and tccrct.mac='" + data.getMac() + "' ");
			}
			if(StringUtils.isNotBlank(data.getPackageSn())){
				sql.append("and tccrct.package_sn='" + data.getPackageSn() + "' ");
			}
			if(StringUtils.isNotBlank(data.getSimImsi())){
				sql.append("and tccrct.sim_imsi='" + data.getSimImsi() + "' ");
			}
			if(StringUtils.isNotBlank(data.getBoardSn())){
				sql.append("and tccrct.board_sn='" + data.getBoardSn() + "' ");
			}
			if(StringUtils.isNotBlank(data.getProductSn())){
				sql.append("and tccrct.product_sn='" + data.getProductSn() + "' ");
			}
			if(StringUtils.isNotBlank(data.getCdTestStatus())){
				sql.append("and tccrct.test_status='" + data.getCdTestStatus() + "' ");
			}
			if(StringUtils.isNotBlank(data.getCdActiveStatus())){
				sql.append("and tccrct.active_status='" + data.getCdActiveStatus() + "' ");
			}
			if(StringUtils.isNotBlank(data.getStartCreateTime())){
				sql.append("and tccrct.production_date >='" + data.getStartCreateTime() + "' ");
			}
			if(StringUtils.isNotBlank(data.getEndCreateTime())){
				sql.append("and tccrct.production_date <='" + data.getEndCreateTime() + "' ");
			}
			if(StringUtils.isNotBlank(data.getCustomerName())){
				sql.append("and tccrct.customer_name='" + data.getCustomerName() + "' ");
			}
		}
		
		if(StringUtils.isNotBlank(sort) && sortMap.containsKey(sort)) {
			sql.append("order by "+sortMap.get(sort)+" ");
		}else 
		{
			sql.append("order by tccrct.id  ");
		}
		
		if (StringUtils.isNotBlank(order)) {
			sql.append(order);
		} else {
			sql.append(" desc ");
		}
		
		if(rows > 0 && page > 0 ){
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}
		//List<CdRegisterCode> list = findBySql(sql.toString());
		List<Map> list = findBySql(sql.toString());
		List<CdRegisterCode> listCdRegisterInfo = new ArrayList<CdRegisterCode>(0);
		
		for (Map map : list) {
			CdRegisterCode cdRegisterCode = new CdRegisterCode();
			cdRegisterCode.setId(map.get("id").toString());
			if(map.get("reg_code") != null){
				cdRegisterCode.setRegCode(map.get("reg_code").toString());
			}
			if(map.get("mac") != null){
				cdRegisterCode.setMac(map.get("mac").toString());
			}
			if(map.get("package_model") != null){
				cdRegisterCode.setProductModel(map.get("package_model").toString());
			}
			if(map.get("random_code") != null){
				cdRegisterCode.setRandomCode(map.get("random_code").toString());
			}
			if(map.get("encrypt_sn") != null){
				cdRegisterCode.setEncryptSn(map.get("encrypt_sn").toString());
			}
			if(map.get("board_sn") != null){
				cdRegisterCode.setBoardSn(map.get("board_sn").toString());
			}
			if(map.get("package_sn") != null){
				cdRegisterCode.setPackageSn(map.get("package_sn").toString());
			}
			if(map.get("customer_name") != null){
				cdRegisterCode.setCustomerName(map.get("customer_name").toString());
			}
			if(map.get("product_sn") != null){
				cdRegisterCode.setProductSn(map.get("product_sn").toString());
			}
			if(map.get("production_date") != null){
				try {
					cdRegisterCode.setProductionDate(DateUtils.parseDate(map.get("production_date").toString(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if(map.get("test_status") != null){
				cdRegisterCode.setCdTestStatus(map.get("test_status").toString());
			}
			if(map.get("test_time") != null){
				try {
					cdRegisterCode.setTestTime(DateUtils.parseDate(map.get("test_time").toString(), "yyyy-MM-dd HH:mm:ss.S"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(map.get("active_status") != null){
				cdRegisterCode.setCdActiveStatus(map.get("active_status").toString());
			}
			if(map.get("active_time") != null){
				try {
					cdRegisterCode.setActiveTime(DateUtils.parseDate(map.get("active_time").toString(), "yyyy-MM-dd HH:mm:ss.S"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			listCdRegisterInfo.add(cdRegisterCode);
		}
		
		return listCdRegisterInfo;
	}

	@Override
	public long countfindBySql(CdRegisterCode data) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) rows from t_cds_cd_register_code tccrct left join t_cds_cd_model tccm on tccm.materiel = tccrct.product_model ");
		
		sql.append("where 1=1 ");
		
		if(data != null){
			if(StringUtils.isNotBlank(data.getProductModel())){
				sql.append("and tccrct.product_model='" + data.getProductModel() + "' ");
			}
			if(StringUtils.isNotBlank(data.getRegCode())){
				sql.append("and tccrct.reg_code like '%" + data.getRegCode() + "%' ");
			}
			if(StringUtils.isNotBlank(data.getMac())){
				sql.append("and tccrct.mac='" + data.getMac() + "' ");
			}
			if(StringUtils.isNotBlank(data.getPackageSn())){
				sql.append("and tccrct.package_sn='" + data.getPackageSn() + "' ");
			}
			if(StringUtils.isNotBlank(data.getSimImsi())){
				sql.append("and tccrct.sim_imsi='" + data.getSimImsi() + "' ");
			}
			if(StringUtils.isNotBlank(data.getBoardSn())){
				sql.append("and tccrct.board_sn='" + data.getBoardSn() + "' ");
			}
			if(StringUtils.isNotBlank(data.getProductSn())){
				sql.append("and tccrct.product_sn='" + data.getProductSn() + "' ");
			}
			if(StringUtils.isNotBlank(data.getCdTestStatus())){
				sql.append("and tccrct.test_status='" + data.getCdTestStatus() + "' ");
			}
			if(StringUtils.isNotBlank(data.getCdActiveStatus())){
				sql.append("and tccrct.active_status='" + data.getCdActiveStatus() + "' ");
			}
			if(StringUtils.isNotBlank(data.getStartCreateTime())){
				sql.append("and tccrct.production_date >='" + data.getStartCreateTime() + "' ");
			}
			if(StringUtils.isNotBlank(data.getEndCreateTime())){
				sql.append("and tccrct.production_date <='" + data.getEndCreateTime() + "' ");
			}
			if(StringUtils.isNotBlank(data.getCustomerName())){
				sql.append("and tccrct.customer_name='" + data.getCustomerName() + "' ");
			}
		}
		BigInteger size =  countBySql(sql.toString());
		return size.longValue();
	}
	
}
