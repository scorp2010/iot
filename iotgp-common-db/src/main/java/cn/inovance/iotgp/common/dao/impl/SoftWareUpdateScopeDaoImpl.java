package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SoftWareUpdateScopeDao;
import cn.inovance.iotgp.common.domain.SoftwareUpdateScope;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.util.DateUtil;
@Repository
public class SoftWareUpdateScopeDaoImpl extends
		BaseDaoImpl<SoftwareUpdateScope> implements SoftWareUpdateScopeDao {
	@Resource
	SuperBaseDao  superBaseDao;
	@Override
	public Long countSoftwareUpdateByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SoftwareUpdateScope t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<SoftwareUpdateScope> findSoftwareUpdateByParam(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from SoftwareUpdateScope t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SoftwareUpdateScope> findSoftwareUpdateScope() {
		String hql = "select distinct t from SoftwareUpdateScope t ";
		return find(hql);
	}

	@Override
	public List<String> getSwitchKeys(SoftwareUpdateScope data) {
		
		String hql = "select t.id from SoftwareUpdateScope t where t.isSwitch='Y' ";
		if(null != data && StringUtils.isNotBlank(data.getSdate()) ){
			hql += "and t.createTime >= :sDate ";
		}
		if(	null != data && StringUtils.isNotBlank(data.getEdate()) ){
			hql += "and t.createTime <= :eDate ";
		}
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		if(	null != data && StringUtils.isNotBlank(data.getSdate()) ){
			q.setTimestamp("sDate", DateUtil.strDayToDateLong(data.getSdate()));
		}
		if(	null != data && StringUtils.isNotBlank(data.getEdate()) ){
			q.setTimestamp("eDate", DateUtil.strDayToDateLong(data.getEdate()));
		}
		
		List<Object> objList = q.list();
		List<String> keyList  = new ArrayList<String>(objList.size());
		for(Object each : objList){
			keyList.add(each.toString());
		}
		return keyList;
	}
}
