package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.TdControllerDataProtocolTag;
import cn.inovance.iotgp.common.domain.TdControllerDataProtocolValue;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface TdControllerDataProtocolValueDao extends BaseDao<TdControllerDataProtocolValue> {

	public Long countTdControllerDataProtocolValueByParam(HqlFilter hqlFilter);

	public List<TdControllerDataProtocolValue> findTdControllerDataProtocolValueByParam(
			HqlFilter hqlFilter, int page, int rows);

	public Integer queryMaxStart(TdControllerDataProtocolValue data);

	public List<TdControllerDataProtocolValue> findTdControllerDataProtocolValueByFK(String id);

	public void deleteByBatch(TdControllerDataProtocolTag data);
}
