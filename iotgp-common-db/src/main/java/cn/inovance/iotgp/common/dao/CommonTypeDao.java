/**
 * Project Name:cds
 * File Name:CommonTypeDao.java
 * Package Name:cn.inovance.iotgp.cds.dao
 * Date:2014-4-24下午2:43:37
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.CommonType;

/**
 * ClassName:CommonTypeDao.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CommonTypeDao {

	public CommonType getByCode(String code,String type);
	public List<CommonType> getByType(String type);
	public CommonType getById(String id);
	public List<CommonType> getType();
}
