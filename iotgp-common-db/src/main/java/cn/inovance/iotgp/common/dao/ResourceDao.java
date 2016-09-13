/**
 * Project Name:bms
 * File Name:ResourceDao.java
 * Package Name:cn.inovance.iotgp.bms.dao
 * Date:2014-4-15上午9:24:37
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.Resource;
import cn.inovance.iotgp.common.filter.HqlFilter;

/**
 * ClassName:ResourceDao. Date: 2014-4-15 上午9:24:37 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public interface ResourceDao {

	/**
	 * 获得资源树，或者combotree(只查找菜单类型的节点).
	 * 
	 * @return
	 */
	public List<Resource> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得资源树，或者combotree(只查找菜单类型的节点).
	 * 
	 * @return
	 */
	public List<Resource> getMainMenuBySql(Resource data);

	/**
	 * 获得资源treeGrid.
	 * 
	 * @return
	 */
	public List<Resource> resourceTreeGrid(HqlFilter hqlFilter);

	/**
	 * 获得资源treeGrid.
	 * 
	 * @return
	 */
	public List<Resource> resourceTreeGridBySql(Resource data);

	/**
	 * 更新资源.
	 */
	public void updateResource(Resource syresource, String[] excludesProperties);

	/**
	 * 保存一个资源.
	 * 
	 * @param syresource
	 * @param userId
	 * @return
	 */
	public void saveResource(Resource syresource, String userId);

	/**
	 * 查找符合条件的资源.
	 */
	public List<Resource> findResourceByFilter(HqlFilter hqlFilter);

	public boolean isParentToChild(Resource t, Resource pt, Resource oldParent);

}
