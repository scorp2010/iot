package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.domain.TdProductModel;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface TdProductModelDao extends BaseDao<TdProductModel> {
	public Long countTdProductModelByParam(HqlFilter hqlFilter);

	public List<TdProductModel> findTdProductModelByParam(HqlFilter hqlFilter, int page,
			int rows);

	public List<TdProductModel> queryListAll();

	public TdProductModel findByName(TdProductModel data); 
	
	public TdProductModel findBySoftName(String softName,String version);
	
	public TdProductModel findTdProductModelById(String id); 
}
