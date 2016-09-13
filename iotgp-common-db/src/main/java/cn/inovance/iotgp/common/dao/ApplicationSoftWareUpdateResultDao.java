package cn.inovance.iotgp.common.dao;


import cn.inovance.iotgp.common.domain.ApplicationSoftwareUpdateResult;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface ApplicationSoftWareUpdateResultDao extends BaseDao<ApplicationSoftwareUpdateResult> {
	public Long countApplicationSoftWareUpdateResultByParam(HqlFilter hqlFilter);

	public void saveApplicationSoftwareUpdateResult(ApplicationSoftwareUpdateResult data);
	
}
