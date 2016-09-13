/**
 * Project Name:iotgp-common-db
 * File Name:CollectDeviceDao.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-5-19下午3:27:19
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.Date;
import java.util.List;

import cn.inovance.iotgp.common.domain.CollectDevice;

/**
 * ClassName:CollectDeviceDao.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CollectDeviceDao extends BaseDao<CollectDevice> {

	public CollectDevice queryOneByCdRegCode(String cdRegCode);
	
	public CollectDevice queryNeedDeleteByCdRegCode(String cdRegCode);

	public List<CollectDevice> findAllBy(CollectDevice data);

	public List<CollectDevice> findAllBy(CollectDevice data, int pages,
			int rows, String sort, String order, String q);
	public List<CollectDevice> findALlByOld(CollectDevice data, int page,
			int rows, String sort, String order, String q);
	
	public List<CollectDevice> getDeviceSoftwareInfo(CollectDevice data, int page,int rows, String sort, String order, String q);
	
	public Long countByData(CollectDevice data,String q);
	public Long countByDataOld(CollectDevice data,String q);
	
	public Long getDeviceSoftwareInfoCount(CollectDevice data,String q);
	
	public void setControllerScopeNull(CollectDevice data);
	public void setControllerScopeNullByTdControllerBatchUpdateScopeId(String tdControllerBatchUpdateScopeId);
	public void setControllerScope(CollectDevice data);
	public List<CollectDevice> findbySort(int pages,int rows, String sort, String order);
	public int updateCustomer(List<String> cdRegCodeList,String customerCode,String customerName,String dnsCode,Short updateType,Date updateTime);
	/**
	 * 查询需要回调的设备
	 */
	public List<Object[]> queryDelCd(List<String> cdRegCodeList,String customerCode,String dhsCode);
	
	 List<CollectDevice> queryUpdateCd(List<String> cdRegCodeList,String customerCode,String dhsCode);
	/**
	 * 查询需要客户的设备
	 */
	public List<Object[]> queryCdsByCustomerCode(String customerCode);
	public List<Object[]> queryCdRegCodeByCustomerCode(String customerCode);
	public int updateCustomer(String customerCode,String customerName,String dnsCode,Short updateType,Date updateTime);
	/**
	 * 查询需要调拨的设备
	 */
	public List<CollectDevice> queryAddCd(List<String> cdRegCodeList,String customerCode,String dhsCode);
	
	public List<Object[]> queryCdAndCustomer(List<String> cdRegCodeList);
	int updateCustomerName(String customerCode, String customerName);
	boolean queryRegCodeAndCustomer(String cdRegCode,String customerCode);
	
	public Long countfindBySql(CollectDevice data);
	public List<CollectDevice> findBySql(CollectDevice data,int rows,int pages,String sort, String order);
	
}
