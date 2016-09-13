package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.SoftwareUpdateJob;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface SoftWareUpdateJobDao {
	public Long countSoftWareUpdateJobByParam(HqlFilter hqlFilter);

	public List<SoftwareUpdateJob> findSoftWareUpdateJobByParam(
			HqlFilter hqlFilter, int page, int rows);
	
	public List<SoftwareUpdateJob> findSoftWareUpdateJob(SoftwareUpdateJob  data,int page,int rows,String sort,String order);
}
