package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface TdControllerTypeDao extends BaseDao<TdControllerType> {
	public Long countTdControllerTypeByParam(HqlFilter hqlFilter);

	public List<TdControllerType> findTdControllerTypeByParam(HqlFilter hqlFilter, int page,
			int rows);

	public List<TdControllerType> queryListAll();

	public TdControllerType findByName(TdControllerType data); 
	
	public TdControllerType findTdControllerTypeById(String id); 
}
