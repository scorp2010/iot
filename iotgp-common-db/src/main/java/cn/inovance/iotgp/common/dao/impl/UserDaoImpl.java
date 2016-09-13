/**
 * Project Name:bms
 * File Name:UserDaoImpl.java
 * Package Name:cn.inovance.iotgp.bms.dao.impl
 * Date:2014-4-17上午9:12:28
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.UserDao;
import cn.inovance.iotgp.common.domain.Organization;
import cn.inovance.iotgp.common.domain.Role;
import cn.inovance.iotgp.common.domain.User;
import cn.inovance.iotgp.common.filter.MapFilter;
import cn.inovance.iotgp.common.util.DomainStatusUtil;

/**
 * ClassName:UserDaoImpl. Date: 2014-4-17 上午9:12:28 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Autowired
	private SuperBaseDao superBaseDao;
	@Autowired
	private SuperBaseDao<Role> roleDao;
	@Autowired
	private SuperBaseDao<Organization> organizationDao;

	@Override
	public void grantRole(String id, String roleIds) {

		User user = getById(id);
		if (user != null) {
			user.setRoleSet(new HashSet<Role>());
			for (String roleId : roleIds.split(",")) {
				if (!StringUtils.isBlank(roleId)) {
					Role role = roleDao.getById(Role.class, roleId);
					if (role != null) {
						user.getRoleSet().add(role);
					}
				}
			}
		}

	}

	@Override
	public void grantOrganization(String id, String organizationIds) {

		User user = getById(id);
		if (user != null) {
			user.setOrganizationSet(new HashSet<Organization>());
			for (String organizationId : organizationIds.split(",")) {
				if (!StringUtils.isBlank(organizationId)) {
					Organization organization = organizationDao.getById(
							Organization.class, organizationId);
					if (organization != null) {
						user.getOrganizationSet().add(organization);
					}
				}
			}
		}

	}

	@Override
	public List<Long> userCreateDatetimeChart() {

		List<Long> l = new ArrayList<Long>();
		int k = 0;
		for (int i = 0; i < 12; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("a", k);
			params.put("b", k + 2);
			k = k + 2;
			l.add(count(
					"select count(*) from User t where HOUR(t.createDatetime)>=:a and HOUR(t.createDatetime)<:b",
					params));
		}
		return l;
	}

	@Override
	public List<User> userTree(MapFilter mapFilter) {
		SQLQuery sqlQuery = superBaseDao
				.getCurrentSession()
				.createSQLQuery(
						"select id,status,create_time,create_user_id,email,link_phone,login_account,photo,state,update_time, user_name,parent_id from t_sys_user  where FIND_IN_SET(id, QueryChilds(?,?))");

		sqlQuery.setString(0, mapFilter.getParameterValue(0));
		sqlQuery.setString(1, mapFilter.getParameterValue(1));
		List<Object[]> objList = sqlQuery.list();
		List<User> users = new ArrayList<User>(0);
		for (int i = 0; i < objList.size(); i++) {
			Object[] objArray = objList.get(i);
			User user = new User();
			user.setId(objArray[0].toString());
			user.setStatus((short) Integer.parseInt(objArray[1].toString()));
			if (null != objArray[4]
					&& !StringUtils.isBlank(objArray[4].toString())) {
				user.setEmail(objArray[4].toString());
			}
			if (null != objArray[5]
					&& !StringUtils.isBlank(objArray[5].toString()))
				user.setLinkPhone(objArray[5].toString());
			if (null != objArray[6]
					&& !StringUtils.isBlank(objArray[6].toString()))
				user.setLoginAccount(objArray[6].toString());
			if (null != objArray[7]
					&& !StringUtils.isBlank(objArray[7].toString()))
				user.setPhoto(objArray[7].toString());
			if (null != objArray[8]
					&& !StringUtils.isBlank(objArray[8].toString()))
				user.setState(objArray[8].toString());
			if (null != objArray[10]
					&& !StringUtils.isBlank(objArray[10].toString()))
				user.setUserName(objArray[10].toString());
			if (null != objArray[11]
					&& !StringUtils.isBlank(objArray[11].toString()))
				user.setParentId(objArray[11].toString());
			try {
				user.setCreateDatetime(DateUtils.parseDate(
						objArray[2].toString(), "yyyy-MM-dd HH:mm:ss"));
				user.setUpdateDatetime(DateUtils.parseDate(
						objArray[9].toString(), "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException ex) {

			}
			users.add(user);
		}
		return users;
	}

	@Override
	public Long countUserByRoleId(String roleId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		String hql = "select count(*) from User t join t.roleSet role where role.id = :roleId";
		return count(hql, params);
	}

	@Override
	public Long countUserByNotRoleId() {
		String hql = "select count(*) from User t left join t.roleSet role where role.id is null";
		return count(hql);
	}

	@Override
	public void deleteUser(String deleteId) {
		User del = getById(deleteId);
		User par = del.getUser();
		if (del.getUserSet().size() > 0) {
			if (par != null) {
				for (User user : del.getUserSet()) {
					user.setCreateUserId(par.getId());
					user.setUser(par);
				}
			} else {
				for (User user : del.getUserSet()) {
					user.setUser(null);
					user.setCreateUserId(null);
				}
			}
		}
		del.setUser(null);
		del.setUserSet(null);
		delete(del);

		if (null != par && par.getUserSet().size() == 1) {
			par.setState(DomainStatusUtil.DOMIAN_STATE_OPEN);
		}
		update(par);
		// String hql =
		// "update User t set t. from User t left join t.roleSet role where role.id is null";
		// executeHql(hql);
	}
}
