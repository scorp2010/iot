package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.SoftwareApproval;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface SoftWareApprovalDao extends BaseDao<SoftwareApproval> {
	public Long countSoftWareApprovalByParam(HqlFilter hqlFilter);

	public List<SoftwareApproval> findSoftWareApprovalByParam(HqlFilter hqlFilter,
			int page, int rows);

	public SoftwareApproval querySoftwareApprovalByParam(SoftwareApproval data);

	/*
	 * 新增审批
	 */
	public void saveSoftwareApproval(SoftwareApproval data);

	public void updateSoftwareApproval(SoftwareApproval data);
	
	public List<SoftwareApproval> findWithGroupBySoftName(SoftwareApproval data);
}
