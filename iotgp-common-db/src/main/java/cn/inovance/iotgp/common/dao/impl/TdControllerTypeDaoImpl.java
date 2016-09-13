package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.TdControllerTypeDao;
import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.filter.HqlFilter;

@Repository
public class TdControllerTypeDaoImpl extends BaseDaoImpl<TdControllerType> implements TdControllerTypeDao {

	@Override
	public Long countTdControllerTypeByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from TdControllerType t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<TdControllerType> findTdControllerTypeByParam(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from TdControllerType t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<TdControllerType> queryListAll() {
		String hql = "select distinct t from TdControllerType t ";
		return find(hql);
	}

	@Override
	public TdControllerType findByName(TdControllerType data) {
		
		String hql = "select distinct t from TdControllerType t where t.typeName ='" + data.getTypeName() + "'";
		return getByHql(hql); 
	}
	
	@Override
	public TdControllerType findTdControllerTypeById(String id) {
		
		String hql = "select distinct t from TdControllerType t where t.id ='" + id + "'";
		return getByHql(hql); 
	}

}
