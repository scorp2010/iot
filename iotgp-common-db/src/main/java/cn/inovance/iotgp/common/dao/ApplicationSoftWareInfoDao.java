package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.ApplicationSoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface ApplicationSoftWareInfoDao extends BaseDao<ApplicationSoftwareInfo> {
	public Long countApplicationSoftWareInfoByParam(HqlFilter hqlFilter);

	public ApplicationSoftwareInfo querySoftwareInfoByParam(ApplicationSoftwareInfo data);

	public void saveApplicationSoftwareInfo(ApplicationSoftwareInfo data);

	public void updateSoftwareInfo(ApplicationSoftwareInfo data);
	
	/**
	 * 查找软件名称表获取软件名称
	 * @param data
	 * @return
	 */
	public List<ApplicationSoftwareInfo> findWithGroupFromSoftName(ApplicationSoftwareInfo data);
	
	public List<ApplicationSoftwareInfo> findSoftWareByNameAndVersion(ApplicationSoftwareInfo data) throws Exception;
	
}
