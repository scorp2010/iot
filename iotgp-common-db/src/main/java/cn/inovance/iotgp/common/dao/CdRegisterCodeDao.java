/**
 * Project Name:cds
 * File Name:CdRegisterCodeDao.java
 * Package Name:cn.inovance.iotgp.cds.dao
 * Date:2014-4-24上午9:06:14
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.List;
import java.util.Map;

import cn.inovance.iotgp.common.domain.CdRegisterCode;
import cn.inovance.iotgp.common.domain.CdSoftwareInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;

/**
 * ClassName:CdRegisterCodeDao.
 * 
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CdRegisterCodeDao extends BaseDao<CdRegisterCode> {
	
	CdRegisterCode getUniqueCdRegisterCode(String property, String value);

	/**
	 * 批量更新设备所属客户
	 * @param cdRegCodeList
	 * @param customerCode
	 * @param customerName
	 * @return
	 */
	public int updateCustomer(List<String> cdRegCodeList,String customerCode,String customerName);
	/**
	 * 通过注册码列表查询客户账号
	 * @param cdRegCodeList
	 * @return
	 */
	public List<Object[]> queryCdAndCustomer(List<String> cdRegCodeList);
	
	
	public List<CdRegisterCode> findBySql(CdRegisterCode data,int rows, int page,String sort,String order);
	public long countfindBySql(CdRegisterCode data);
	
	
	String  getCdRegCodeCustomerCode(String cdRegCode);
	
	String getCdRegCodeByProductSn(String productSn);
	
	int  updateCdRegCodeCustomerBame(String customerCode,String customerName);
}
