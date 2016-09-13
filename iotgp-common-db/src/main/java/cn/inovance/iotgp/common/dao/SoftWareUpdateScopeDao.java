package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.SoftwareUpdateScope;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface SoftWareUpdateScopeDao extends BaseDao<SoftwareUpdateScope>  {
	public Long countSoftwareUpdateByParam(HqlFilter hqlFilter);

	public List<SoftwareUpdateScope> findSoftwareUpdateByParam(
			HqlFilter hqlFilter, int page, int rows);

	public List<SoftwareUpdateScope> findSoftwareUpdateScope();

	public List<String> getSwitchKeys(SoftwareUpdateScope data);
}
