package cn.inovance.iotgp.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SoftWareUpdateJobDetailsDao;
import cn.inovance.iotgp.common.domain.SoftwareUpdateDetails;
import cn.inovance.iotgp.common.domain.SoftwareUpdateJob;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.status.StatusEnum;

@Repository
public class SoftWareUpdateJobDetailsDaoImpl extends
		BaseDaoImpl<SoftwareUpdateDetails> implements
		SoftWareUpdateJobDetailsDao {
	static final String CDREGCODE_SPLIT = ";";

	@Override
	public Long countSoftWareUpdateJobDetailByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SoftwareUpdateDetails t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<SoftwareUpdateDetails> findSoftWareUpdateJobDetailByParam(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from SoftwareUpdateDetails t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public void updateCancel(SoftwareUpdateDetails data, String updateResult) {
		String sql = "update t_mms_software_update_details set update_result='" + updateResult
				+ "' where software_update_job_id_fk='"
				+ data.getSoftWareUpdateJob().getId() + "'";
		executeSql(sql);
	}

	@Override
	public void deleteJobDetails(SoftwareUpdateJob t) {
		String sql = "DELETE FROM t_mms_software_update_details WHERE software_update_job_id_fk='"
				+ t.getId() + "'";
		executeSql(sql);

	}

	@Override
	public List<SoftwareUpdateDetails> findUnFinishedSoftWareUpdateJobDetails(
			SoftwareUpdateDetails data) {
		StringBuilder sql = new StringBuilder();
		sql.append("from SoftwareUpdateDetails t ");
		sql.append("where 1=1 ");
		sql.append("and t.cdRegCode=:cdRegCode ");
		sql.append("and t.softName=:softName ");
		sql.append("and ( t.updateResult = :noUpdate or t.updateResult = :install or " +
				" t.updateResult=:download ) ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdRegCode", data.getCdRegCode());
		params.put("softName", data.getSoftName());
		params.put("noUpdate", StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value);
		params.put("install", StatusEnum.SOFTWARE_UPDATE_DETAILS_INSTALLING.value);
		params.put("download", StatusEnum.SOFTWARE_UPDATE_DETAILS_DOWNLING.value);
		return find(sql.toString(),params);
	}
	
	/**
	 * 判断采集设备是否有手动升级任务
	 */
	@Override
	public List<SoftwareUpdateDetails> findUnFinishedSoftWareUpdateManualDetails(
			SoftwareUpdateDetails data) {
		StringBuilder sql = new StringBuilder();
		sql.append("from SoftwareUpdateDetails t ");
		sql.append("where 1=1 ");
		sql.append("and t.cdRegCode=:cdRegCode ");
		sql.append("and ( t.updateResult = :noUpdate or t.updateResult = :install or " +
				" t.updateResult=:download ) and t.softWareUpdateJob.id is not null ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdRegCode", data.getCdRegCode());
		params.put("noUpdate", StatusEnum.SOFTWARE_UPDATE_DETAILS_NOT_UPDATE.value);
		params.put("install", StatusEnum.SOFTWARE_UPDATE_DETAILS_INSTALLING.value);
		params.put("download", StatusEnum.SOFTWARE_UPDATE_DETAILS_DOWNLING.value);
		return find(sql.toString(),params);
	}
	
	/**
	 * 获取上报的单板软件是否有创建的升级任务
	 */
	@Override
	public List<SoftwareUpdateDetails> findUnFinishedSoftWareUpdateToProductModel(
			SoftwareUpdateDetails data) {
		StringBuilder sql = new StringBuilder();
		sql.append("from SoftwareUpdateDetails t ");
		sql.append("where 1=1 ");
		sql.append("and t.cdRegCode=:cdRegCode ");
		sql.append("and t.softName=:softName ");
		sql.append("and t.version=:version ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdRegCode", data.getCdRegCode());
		params.put("softName", data.getSoftName());
		params.put("version", data.getVersion());
		return find(sql.toString(),params);
	}
	
	@Override
	public void updateOldSoftCancel(SoftwareUpdateDetails data, String updateResult) {
		String sql = "update t_mms_software_update_details set update_result='" + updateResult
				+ "' where cd_reg_code='" + data.getCdRegCode() 
				+ "' and soft_name='" + data.getSoftName() 
				+ "' and version='" + data.getVersion() 
				+ "' and (update_result = '1' or update_result = '11' or update_result = '5')";
		executeSql(sql);
	}
}
