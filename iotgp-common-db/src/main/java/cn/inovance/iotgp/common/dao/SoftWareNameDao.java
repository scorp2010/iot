package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareName;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface SoftWareNameDao extends BaseDao<SoftwareName> {
	public Long countSoftWareNameByParam(HqlFilter hqlFilter);

	public List<SoftwareName> findSoftWareNameByParam(HqlFilter hqlFilter,
			int page, int rows);

	public SoftwareName querySoftwareNameByParam(SoftwareName data);

	public void saveSoftwareName(SoftwareName data);

	public List<SoftwareName> findSoftWareName();

	public void updateSoftwareName(SoftwareName data);
	
	public List<SoftwareName> findWithGroupBySoftName(SoftwareName data);
	/**
	 * 校验软件名称是否从软件名称表中选取
	 * @param data
	 * @return
	 */
	public Long checkSoftName(SoftwareName data);
}
