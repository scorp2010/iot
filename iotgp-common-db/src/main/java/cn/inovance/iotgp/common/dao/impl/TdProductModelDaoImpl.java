package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.TdControllerTypeDao;
import cn.inovance.iotgp.common.dao.TdProductModelDao;
import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.domain.TdProductModel;
import cn.inovance.iotgp.common.filter.HqlFilter;

@Repository
public class TdProductModelDaoImpl extends BaseDaoImpl<TdProductModel> implements TdProductModelDao {

	@Override
	public Long countTdProductModelByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from TdProductModel t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<TdProductModel> findTdProductModelByParam(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from TdProductModel t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<TdProductModel> queryListAll() {
		String hql = "select distinct t from TdProductModel t ";
		return find(hql);
	}

	@Override
	public TdProductModel findByName(TdProductModel data) {
		
		String hql = "select distinct t from TdProductModel t where t.productName ='" + data.getProductName() + "'";
		return getByHql(hql); 
	}
	
	@Override
	public TdProductModel findBySoftName(String softName,String version) {
		
		String hql = "select distinct t from TdProductModel t inner join fetch t.softWareInfo as c  where c.name ='" + softName + "' and c.version='" + version + "'";
		return getByHql(hql); 
	}
	
	@Override
	public TdProductModel findTdProductModelById(String id) {
		
		String hql = "select distinct t from TdProductModel t inner join fetch t.softWareInfo as c  where t.id ='" + id + "'";
		return getByHql(hql); 
	}

}
