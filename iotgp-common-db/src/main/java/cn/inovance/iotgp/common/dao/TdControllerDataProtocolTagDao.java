package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerDataProtocolTag;
import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface TdControllerDataProtocolTagDao extends BaseDao<TdControllerDataProtocolTag> {

	public Long countTdControllerDataProtocolTagByParam(HqlFilter hqlFilter);

	public List<TdControllerDataProtocolTag> findTdControllerDataProtocolTagByParam(
			HqlFilter hqlFilter, int page, int rows);

	public List<TdControllerDataProtocolTag> findTdControllerDataProtocolTag();

	public Long countTagEntityByNameAndValue(TdControllerDataProtocolTag tagEntity);

	public List<TdControllerDataProtocolTag> findTdControllerDataProtocolTagById(String id);

	public void deleteByTagBatch(TdControllerType controller);
}
