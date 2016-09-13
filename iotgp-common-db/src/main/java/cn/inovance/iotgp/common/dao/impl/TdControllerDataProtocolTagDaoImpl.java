package cn.inovance.iotgp.common.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.TdControllerDataProtocolTagDao;
import cn.inovance.iotgp.common.domain.TdControllerDataProtocolTag;
import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.filter.HqlFilter;

@Repository
public class TdControllerDataProtocolTagDaoImpl extends BaseDaoImpl<TdControllerDataProtocolTag>
		implements TdControllerDataProtocolTagDao {

	@Override
	public Long countTdControllerDataProtocolTagByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from TdControllerDataProtocolTag t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<TdControllerDataProtocolTag> findTdControllerDataProtocolTagByParam(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from TdControllerDataProtocolTag t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<TdControllerDataProtocolTag> findTdControllerDataProtocolTag() {
		String hql = "select distinct t from TdControllerDataProtocolTag t ";
		return find(hql);
	}

	@Override
	public Long countTagEntityByNameAndValue(
			TdControllerDataProtocolTag tagEntity) {
		String hql = "select count(distinct t) from TdControllerDataProtocolTag t where t.tdControllerType.id='"+tagEntity.getTdControllerType().getId()+"' and (t.name='"+
			tagEntity.getName()+"' or t.value="+tagEntity.getValue()+")";
		return count(hql);
	}

	@Override
	public List<TdControllerDataProtocolTag> findTdControllerDataProtocolTagById(String id) {
		String hql = "select distinct t from TdControllerDataProtocolTag t where t.tdControllerType.id='"+id+"' order by value asc";
		return find(hql);
	}

	@Override
	public void deleteByTagBatch(TdControllerType controller) {
		if(controller == null ){
			return;
		}
		String sql = "DELETE FROM t_mms_td_controller_data_protocol_tag where td_controller_type_id_fk=:controllerId";
		Map<String, Object> params = new LinkedHashMap<String, Object>(0);
		params.put("controllerId", controller.getId());
		executeSql(sql, params);
	}


}
