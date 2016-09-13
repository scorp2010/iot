package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface SoftWareInfoDao extends BaseDao<SoftwareInfo> {
	public Long countSoftWareInfoByParam(HqlFilter hqlFilter);

	public List<SoftwareInfo> findSoftWareInfoByParam(HqlFilter hqlFilter,
			int page, int rows);

	public SoftwareInfo querySoftwareInfoByParam(SoftwareInfo data);

	public void saveSoftwareInfo(SoftwareInfo data);

	public List<SoftwareInfo> findSoftWareInfo();

	public void updateSoftwareInfo(SoftwareInfo data);
	
	public List<SoftwareInfo> findWithGroupBySoftName(SoftwareInfo data);
	
	/**
	 * 查找软件名称表获取软件名称
	 * @param data
	 * @return
	 */
	public List<SoftwareInfo> findWithGroupFromSoftName(SoftwareInfo data);
	
	/**
	 *  查询软件信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public SoftwareInfo querySoftwareInfoById(String id) throws Exception;
	
}
