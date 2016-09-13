package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SoftWareUpdateJobDao;
import cn.inovance.iotgp.common.domain.SoftwareUpdateJob;
import cn.inovance.iotgp.common.filter.HqlFilter;

@Repository
public class SoftWareUpdateJobDaoImpl extends BaseDaoImpl<SoftwareUpdateJob>
		implements SoftWareUpdateJobDao {

	@Override
	public Long countSoftWareUpdateJobByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SoftwareUpdateJob t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<SoftwareUpdateJob> findSoftWareUpdateJobByParam(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from SoftwareUpdateJob t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SoftwareUpdateJob> findSoftWareUpdateJob(SoftwareUpdateJob data,int page,int rows,String sort,String order) {
		
		if(null  == data ){
			return null;
		}
		HqlFilter hqlFilter = new HqlFilter();
		if(data.getSoftWareInfo() != null){
			hqlFilter.addFilter("QUERY_t#softWareInfo#id_S_EQ",
					data.getSoftWareInfo().getId());
		}
		return findByFilter(hqlFilter);
	}
}
