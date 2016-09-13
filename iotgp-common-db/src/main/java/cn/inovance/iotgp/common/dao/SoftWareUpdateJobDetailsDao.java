package cn.inovance.iotgp.common.dao;

import java.util.List;

import javax.swing.ListModel;

import cn.inovance.iotgp.common.domain.SoftwareUpdateDetails;
import cn.inovance.iotgp.common.domain.SoftwareUpdateJob;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface SoftWareUpdateJobDetailsDao extends
		BaseDao<SoftwareUpdateDetails> {
	public Long countSoftWareUpdateJobDetailByParam(HqlFilter hqlFilter);

	public List<SoftwareUpdateDetails> findSoftWareUpdateJobDetailByParam(
			HqlFilter hqlFilter, int page, int rows);

	public void updateCancel(SoftwareUpdateDetails data, String updateResult);

	public void deleteJobDetails(SoftwareUpdateJob t);
	
	public  List<SoftwareUpdateDetails> findUnFinishedSoftWareUpdateJobDetails (SoftwareUpdateDetails t);
	
	public void updateOldSoftCancel(SoftwareUpdateDetails data, String updateResult);
	
	public List<SoftwareUpdateDetails> findUnFinishedSoftWareUpdateToProductModel(
			SoftwareUpdateDetails data);
	public List<SoftwareUpdateDetails> findUnFinishedSoftWareUpdateManualDetails(
			SoftwareUpdateDetails data);

}
