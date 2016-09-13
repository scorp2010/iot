/**
 * Project Name:bms
 * File Name:UserDao.java
 * Package Name:cn.inovance.iotgp.bms.dao
 * Date:2014-4-17上午9:11:27
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.User;
import cn.inovance.iotgp.common.filter.MapFilter;

/**
 * ClassName:UserDao.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public interface UserDao {
	/**
	 * 修改用户角色.
	 * 
	 * @param id
	 *            用户ID
	 * @param roleIds
	 *            角色IDS
	 */
	public void grantRole(String id, String roleIds);

	/**
	 * 修改用户机构.
	 * 
	 * @param id
	 *            用户ID
	 * @param organizationIds
	 *            机构IDS
	 */
	public void grantOrganization(String id, String organizationIds);

	/**
	 * 统计用户注册时间图表.
	 * 
	 * @return
	 */
	public List<Long> userCreateDatetimeChart();

	/**
	 * 用户树.
	 * 
	 * @return
	 */
	public List<User> userTree(MapFilter mapFilter);

	/**
	 * 统计?角色的用户.
	 * 
	 * @param roleId
	 * @return
	 */
	public Long countUserByRoleId(String roleId);

	/**
	 * 统计没有角色的用户数量.
	 * 
	 * @return
	 */
	public Long countUserByNotRoleId();

	/**
	 * 不执行级联删除
	 */
	public void deleteUser(String deleteId);
}
