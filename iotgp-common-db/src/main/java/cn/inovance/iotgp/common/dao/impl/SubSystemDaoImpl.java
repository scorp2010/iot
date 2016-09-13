/**
 * Project Name:cds
 * File Name:SubSystemDaoImpl.java
 * Package Name:cn.inovance.iotgp.cds.dao.impl
 * Date:2014-4-23下午4:41:05
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SubSystemDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.SubSystem;

/**
 * ClassName:SubSystemDaoImpl.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class SubSystemDaoImpl extends BaseDaoImpl<SubSystem> implements
		SubSystemDao {

	@Autowired
	private SuperBaseDao superBaseDao;
}
