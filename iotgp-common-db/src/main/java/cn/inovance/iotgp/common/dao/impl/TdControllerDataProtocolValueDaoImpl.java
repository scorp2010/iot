package cn.inovance.iotgp.common.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.TdControllerDataProtocolValueDao;
import cn.inovance.iotgp.common.domain.TdControllerDataProtocolTag;
import cn.inovance.iotgp.common.domain.TdControllerDataProtocolValue;
import cn.inovance.iotgp.common.filter.HqlFilter;

@Repository
public class TdControllerDataProtocolValueDaoImpl extends
		BaseDaoImpl<TdControllerDataProtocolValue> implements TdControllerDataProtocolValueDao {

	@Override
	public Long countTdControllerDataProtocolValueByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from TdControllerDataProtocolValue t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<TdControllerDataProtocolValue> findTdControllerDataProtocolValueByParam(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from TdControllerDataProtocolValue t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer queryMaxStart(TdControllerDataProtocolValue data) {
		String sql = " SELECT MAX(seq) as maxStart FROM t_mms_td_controller_data_protocol_value WHERE td_controller_data_protocol_tag_id_fk ='"
				+ data.getTdControllerDataProtocolTag().getId() + "'";
		List<Map<String, String>> queryResult = findBySql(sql);
		Integer resultInteger = 0;
		if (queryResult.size() > 0) {
			Map map = queryResult.get(0);
			if (null != map.get("maxStart")) {
				System.out.println(map.get("maxStart"));
				resultInteger = Integer.valueOf(map.get("maxStart").toString());
			}
		}
		return resultInteger;
	}

	@Override
	public List<TdControllerDataProtocolValue> findTdControllerDataProtocolValueByFK(String id) {
		String hql = "select distinct t from TdControllerDataProtocolValue t where t.tdControllerDataProtocolTag.id='"+id+"' order by seq asc";
		return find(hql);
	}

	@Override
	public void deleteByBatch(TdControllerDataProtocolTag data) {
		if(data == null){
			return;
		}
		String sql = "DELETE FROM t_mms_td_controller_data_protocol_value where td_controller_data_protocol_tag_id_fk=:tagId";
		Map<String, Object> params = new LinkedHashMap<String, Object>(0);
		params.put("tagId", data.getId());
		executeSql(sql, params);
		
	}


}
