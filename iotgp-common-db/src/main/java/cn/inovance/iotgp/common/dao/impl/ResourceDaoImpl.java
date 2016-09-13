/**
 * Project Name:bms
 * File Name:ResourceDaoImpl.java
 * Package Name:cn.inovance.iotgp.bms.dao.impl
 * Date:2014-4-15上午9:32:38
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.ResourceDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.Organization;
import cn.inovance.iotgp.common.domain.Resource;
import cn.inovance.iotgp.common.domain.ResourceType;
import cn.inovance.iotgp.common.domain.Role;
import cn.inovance.iotgp.common.domain.User;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.BeanUtils;
import cn.inovance.iotgp.common.util.DateUtil;

/**
 * ClassName:ResourceDaoImpl.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements
		ResourceDao {

	@Autowired
	private SuperBaseDao<User> userDao;
	@Autowired
	private SuperBaseDao superBaseDao;

	@Override
	public List<Resource> getMainMenu(HqlFilter hqlFilter) {

		List<Resource> l = new ArrayList<Resource>();
		String hql = "select distinct t from Resource t join t.roleSet role join role.userSet user ";
		List<Resource> resource_role = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(resource_role);
		hql = "select distinct t from Resource t join t.organizationSet organization "
				+ "join organization.userSet user ";
		List<Resource> resource_organization = find(
				hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_organization);
		l = new ArrayList<Resource>(new HashSet<Resource>(l));// 去重
		Collections.sort(l, new Comparator<Resource>() { // 排序
					@Override
					public int compare(Resource o1, Resource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public List<Resource> getMainMenuBySql(Resource data) {

		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" resc.*, ");
		sql.append("count(childres.id) countchilds ");
		sql.append(" FROM ");
		sql.append(" t_sys_resource resc  ");
		sql.append(" left join ");
		sql.append(" t_sys_resource_type restype ");
		sql.append(" on resc.res_type_id = restype.id  ");
		sql.append(" left join ");
		sql.append(" t_sys_resource childres  ");
		sql.append(" on childres.parent_id = resc.id  ");
		sql.append(" and childres.res_type_id = restype.id  ");
		sql.append(" and restype.id = '0'   ");
		sql.append(" join ( ");
		sql.append(" select childres.id from t_sys_resource childres ");
		sql.append(" join(  ");
		sql.append(" select  ");
		sql.append(" tsrr.resource_id ");
		sql.append(" from t_sys_role_resource tsrr ");
		sql.append(" join t_sys_role tsr on tsr.id = tsrr.role_id  ");
		sql.append(" join  t_sys_user_role tsur  on tsr.id = tsur.role_id ");
		sql.append(" where   1=1  ");
		sql.append(" and tsur.user_id='" + data.getCreateUserId() + "'");
		sql.append(" union  ");
		sql.append(" select  ");
		sql.append(" tsor.resource_id ");
		sql.append(" from  ");
		sql.append(" t_sys_organization_resource tsor ");
		sql.append(" join  t_sys_organization tso on tso.id = tsor.organization_id  ");
		sql.append(" join  ");
		sql.append(" t_sys_user_organization tsuo on tsuo.organization_id = tso.id   ");
		sql.append(" where ");
		sql.append(" 1=1 ");
		sql.append(" and tsuo.user_id='" + data.getCreateUserId() + "'");
		sql.append(" ) res on res.resource_id = childres.id  ");
		sql.append(" )childrest on resc.id = childrest.id ");
		sql.append(" where ");
		sql.append(" 1=1   ");
		sql.append(" and restype.id ='0' ");
		if (StringUtils.isBlank(data.getParentId()))
			sql.append(" and resc.parent_id is null ");
		else
			sql.append(" and resc.parent_id='" + data.getParentId() + "'");
		sql.append(" group by ");
		sql.append(" resc.id  ");
		sql.append(" order by ");
		sql.append(" resc.seq asc ");

		List<Map> list = findBySql(sql.toString());
		List<Resource> listResources = new ArrayList<Resource>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			Resource resource = new Resource();
			resource.setId(map.get("id").toString());
			resource.setCreateDatetime(DateUtil.strToDateLong(map.get(
					"create_time").toString()));
			resource.setUpdateDatetime(DateUtil.strToDateLong(map.get(
					"update_time").toString()));
			if (null != map.get("target")) {
				resource.setTarget(map.get("target").toString());
			}
			if (null != map.get("iconcls")) {
				resource.setIconCls(map.get("iconcls").toString());
			}

			if (null != map.get("status")) {
				resource.setStatus(Short.valueOf(map.get("status").toString()));
			}
			if (Integer.parseInt(map.get("countchilds").toString()) > 0) {
				resource.setState("closed");
			} else {
				resource.setState("open");
			}
			// resource.setState(map.get("state").toString());
			if (null != map.get("res_name")) {
				resource.setResName(map.get("res_name").toString());
			}
			if (null != map.get("res_url")) {
				resource.setResUrl(map.get("res_url").toString());
			}
			listResources.add(resource);
		}
		return listResources;
	}

	private Date Date(Object object) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> resourceTreeGrid(HqlFilter hqlFilter) {

		List<Resource> l = new ArrayList<Resource>();
		String hql = "select distinct t from Resource t join t.roleSet role join role.userSet user";
		List<Resource> resource_role = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(resource_role);
		hql = "select distinct t from Resource t join t.organizationSet organization join "
				+ "organization.userSet user";
		List<Resource> resource_organization = find(
				hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_organization);
		l = new ArrayList<Resource>(new HashSet<Resource>(l));// 去重
		Collections.sort(l, new Comparator<Resource>() {// 排序
					@Override
					public int compare(Resource o1, Resource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public List<Resource> resourceTreeGridBySql(Resource data) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" resc.*");
		sql.append(" FROM ");
		sql.append(" t_sys_resource resc ");
		sql.append(" join ");
		sql.append(" ( ");
		sql.append(" select childres.parent_id,childres.id from t_sys_resource childres  ");
		sql.append(" join( ");
		sql.append(" select ");
		sql.append(" tsrr.resource_id ");
		sql.append(" from ");
		sql.append(" t_sys_role_resource tsrr ");
		sql.append(" join ");
		sql.append(" t_sys_role tsr  ");
		sql.append(" on tsr.id = tsrr.role_id ");
		sql.append(" join ");
		sql.append(" t_sys_user_role tsur ");
		sql.append(" on tsr.id = tsur.role_id ");
		sql.append(" where ");
		sql.append(" 1=1 ");
		sql.append("  and tsur.user_id='" + data.getCreateUserId() + "' ");
		sql.append(" union ");
		sql.append(" select ");
		sql.append(" tsor.resource_id ");
		sql.append(" from ");
		sql.append(" t_sys_organization_resource tsor  ");
		sql.append(" join ");
		sql.append(" t_sys_organization tso ");
		sql.append(" on tso.id = tsor.organization_id ");
		sql.append(" join ");
		sql.append(" t_sys_user_organization tsuo ");
		sql.append("  on tsuo.organization_id = tso.id ");
		sql.append(" where ");
		sql.append(" 1=1 ");
		sql.append(" and tsuo.user_id='" + data.getCreateUserId() + "' ");
		sql.append("  ) res on res.resource_id = childres.id  ");
		sql.append(" )childrest on childrest.id = resc.id ");
		sql.append(" where ");
		sql.append(" 1=1  ");
		if (!StringUtils.isBlank(data.getParentId())) {
			sql.append(" and resc.parent_id='" + data.getParentId() + "' ");
		} else {
			sql.append(" and resc.parent_id is null ");
		}
		sql.append(" group by ");
		sql.append(" resc.id  ");
		sql.append(" order by ");
		sql.append(" resc.seq  asc");
		List<Map> list = findBySql(sql.toString());
		List<Resource> listResources = new ArrayList<Resource>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			Resource resource = new Resource();
			resource.setId(map.get("id").toString());
			resource.setCreateDatetime(DateUtil.strToDateLong(map.get(
					"create_time").toString()));
			resource.setUpdateDatetime(DateUtil.strToDateLong(map.get(
					"update_time").toString()));
			if (null != map.get("target"))
				resource.setTarget(map.get("target").toString());
			if (null != map.get("iconcls"))
				resource.setIconCls(map.get("iconcls").toString());
			if (null != map.get("res_desc"))
				resource.setResDesc(map.get("res_desc").toString());
			resource.setStatus((short) Integer.parseInt(map.get("status")
					.toString()));
			resource.setSeq(Integer.decode(map.get("seq").toString()));
			if (map.get("parent_id") != null)
				resource.setParentId(map.get("parent_id").toString());
			if (map.get("res_code") != null)
				resource.setResCode(map.get("res_code").toString());
			if (map.get("reserve_flag") != null)
				resource.setReserveFlag(Boolean.parseBoolean(map.get(
						"reserve_flag").toString()));
			if (map.get("big_pic_url") != null)
				resource.setBigPicUrl(map.get("big_pic_url").toString());
			if (map.get("mid_pic_url") != null)
				resource.setMidPicUrl(map.get("mid_pic_url").toString());
			if (map.get("small_pic_url") != null)
				resource.setSmallPicUrl(map.get("small_pic_url").toString());
			if (map.get("parent_id") != null)
				resource.setParentId(map.get("parent_id").toString());
			if (map.get("remark") != null)
				resource.setRemark(map.get("remark").toString());
			if (map.get("create_user_id") != null)
				resource.setCreateUserId(map.get("create_user_id").toString());
			if (map.get("res_type_id") != null)
				resource.setResourceType((ResourceType) superBaseDao.getById(
						ResourceType.class, map.get("res_type_id").toString()));
			if (map.get("state") != null)
				resource.setState(map.get("state").toString());

			resource.setResName(map.get("res_name").toString());
			if (map.get("res_url") != null)
				resource.setResUrl(map.get("res_url").toString());
			listResources.add(resource);
		}
		Collections.sort(listResources, new Comparator<Resource>() {// 排序
					@Override
					public int compare(Resource o1, Resource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return listResources;
	}

	@Override
	public void updateResource(Resource syresource, String[] excludesProperties) {

		if (!StringUtils.isBlank(syresource.getId())) {
			Resource t = getById(syresource.getId());
			Resource oldParent = t.getResource();
			BeanUtils.copyNotNullProperties(syresource, t, excludesProperties);
			if (syresource.getResource() != null) {// 说明要修改的节点选中了上级节点
				Resource pt = getById(syresource.getResource().getId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setResource(pt);
			} else {
				t.setResource(null);
			}
		}

	}

	@Override
	public void saveResource(Resource syresource, String userId) {

		save(syresource);

		User user = userDao.getById(User.class, userId);
		Set<Role> roles = user.getRoleSet();
		if (roles != null && !roles.isEmpty()) {// 如果用户有角色，就将新资源放到用户的第一个角色里面
			List<Role> l = new ArrayList<Role>();
			l.addAll(roles);
			Collections.sort(l, new Comparator<Role>() {
				@Override
				public int compare(Role o1, Role o2) {
					if (o1.getCreateDatetime().getTime() > o2
							.getCreateDatetime().getTime()) {
						return 1;
					}
					if (o1.getCreateDatetime().getTime() < o2
							.getCreateDatetime().getTime()) {
						return -1;
					}
					return 0;
				}
			});
			l.get(0).getResourceSet().add(syresource);
		} else {// 如果用户没有角色
			Set<Organization> organizations = user.getOrganizationSet();
			if (organizations != null && !organizations.isEmpty()) {// 如果用户没有角色，但是有机构，那就将新资源放到第一个机构里面
				List<Organization> l = new ArrayList<Organization>();
				l.addAll(organizations);
				Collections.sort(l, new Comparator<Organization>() {
					@Override
					public int compare(Organization o1, Organization o2) {
						if (o1.getCreateDatetime().getTime() > o2
								.getCreateDatetime().getTime()) {
							return 1;
						}
						if (o1.getCreateDatetime().getTime() < o2
								.getCreateDatetime().getTime()) {
							return -1;
						}
						return 0;
					}
				});
				l.get(0).getResourceSet().add(syresource);
			}
		}

	}

	@Override
	public List<Resource> findResourceByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Resource t left join t.roleSet role left "
				+ "join t.organizationSet organization";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点下.
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * 
	 * @param oldParent
	 *            原上级节点
	 * @return boolean
	 */
	@Override
	public boolean isParentToChild(Resource t, Resource pt, Resource oldParent) {
		if (pt != null && pt.getResource() != null) {
			if (StringUtils.equals(pt.getResource().getId(), t.getId())) {
				pt.setResource(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getResource(), oldParent);
			}
		}
		return false;
	}
}
