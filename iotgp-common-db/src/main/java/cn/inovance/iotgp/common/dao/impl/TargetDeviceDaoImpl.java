package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.TargetDeviceDao;
import cn.inovance.iotgp.common.domain.TargetDevice;
import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.TdSimpleDataProtocolTag;
import cn.inovance.iotgp.common.util.TdSimpleDataProtocolValue;

@Repository
public class TargetDeviceDaoImpl extends BaseDaoImpl<TargetDevice> implements
		TargetDeviceDao {
	@Resource
	SuperBaseDao superBaseDao;
	@Override
	public Long countTargetDeviceByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from TargetDevice t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<TargetDevice> findTargetDeviceByParam(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from TargetDevice t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<TargetDevice> findListByCdRegCode(HqlFilter hqlFilter) {

		String hql = "select distinct t from TargetDevice t";

		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countTargetDevice(TargetDevice data) {
		String hql = "select count(distinct t) from TargetDevice t where t.cdRegCode='"
				+ data.getCdRegCode() + " and t.tdTypeCode=" + data.getTdTypeCode() 
				+ "' and t.addressCode='"
				+ data.getAddressCode() + "'";
		return count(hql);
	}

	@Override
	public List<TdSimpleDataProtocolTag> findTdProtocol(TargetDevice data) {
		String sql = "select tmtcdpv.t_name,tmtcdpv.remark,tmtcdpv.seq, "
				+ "tmtcdpv.byte_index,tmtcdpv.bit_index,tmtcdpv.length,tmtcdpv.function_code, "
				+ "tmtcdpv.length_unit,tmtcdpv.transfer_data_type,tmtcdpv.transfer_method, "
				+ "tmtcdpv.transfer_content,tmtcdpv.value_unit,tmtcdpv.endian, "
				+ "tmtcdpt.name tag_name,tmtcdpt.value tag_value,tmtcdpt.sample_period from "
				+ "t_mms_td_controller_data_protocol_value  tmtcdpv "
				+ "left join t_mms_td_controller_data_protocol_tag tmtcdpt on tmtcdpt.id = "
				+ "tmtcdpv.td_controller_data_protocol_tag_id_fk "
				+ "left join t_mms_td_controller_type tmtdct on tmtdct.id = "
				+ "tmtcdpt.td_controller_type_id_fk "
				+ "left join t_mms_target_device tmtd  on tmtd.td_type_controller_id_fk=tmtdct.id "
				+ "where tmtd.id =:tmtdId "
				+ "order by tmtcdpt.value asc,tmtcdpv.seq";
		Map<String, Object> params = new LinkedHashMap<String, Object>(0);
		params.put("tmtdId", data.getId());
		List<Map> list = findBySql(sql.toString(),params);
		List<TdSimpleDataProtocolTag> tagList = new ArrayList<TdSimpleDataProtocolTag>(
				0);
		List<TdSimpleDataProtocolValue> valueList = new ArrayList<TdSimpleDataProtocolValue>(
				0);
		TdSimpleDataProtocolTag tag = null;
		Integer preValue = null;
		Integer curValue = null;
		for (Map map : list) {
			TdSimpleDataProtocolValue tdValue = new TdSimpleDataProtocolValue();
			if (map.get("t_name") != null) {
				tdValue.setName(map.get("t_name").toString());
			}
			if (map.get("function_code") != null) {
				tdValue.setFunctionCode(map.get("function_code").toString());
			}
			if (map.get("remark") != null) {
				tdValue.setRemark(map.get("remark").toString());
			}
			if (map.get("seq") != null) {
				tdValue.setSeq(Integer.valueOf(map.get("seq").toString()));
			}
			if (map.get("byte_index") != null) {
				tdValue.setByteIndex(Integer.valueOf(map.get("byte_index").toString()));
			}
			if (map.get("bit_index") != null) {
				tdValue.setBitIndex(Integer.valueOf(map.get("bit_index").toString()));
			}
			if (map.get("length") != null) {
				tdValue.setLength(Integer.valueOf(map.get("length").toString()));
			}
			if (map.get("length_unit") != null) {
				tdValue.setLengthUnit(map.get("length_unit").toString());
			}
			if (map.get("transfer_data_type") != null) {
				tdValue.setTransferDataType(map.get("transfer_data_type").toString());
			}
			if (map.get("transfer_method") != null) {
				tdValue.setTransferMethod(map.get("transfer_method").toString());
			}
			if (map.get("transfer_content") != null) {
				tdValue.setTransferContent(map.get("transfer_content").toString());
			}
			if (map.get("value_unit") != null) {
				tdValue.setValueUnit(map.get("value_unit").toString());
			}
			if (map.get("endian") != null) {
				tdValue.setEndian(map.get("endian").toString());
			}
			if (map.get("tag_value") != null) {
				curValue = Integer.valueOf(map.get("tag_value").toString());
			}
			if (preValue == null) {
				tag = new TdSimpleDataProtocolTag();
				tag.setId(curValue);
				if (map.get("tag_name") != null) {
					tag.setText(map.get("tag_name").toString());
				}
				if (map.get("sample_period") != null) {
					tag.setSamplingperiod(Integer.valueOf(map.get("sample_period").toString()));
				}
			} else if (preValue != curValue) {
				tag.setValueList(valueList);
				tagList.add(tag);
				tag = null;
				tag = new TdSimpleDataProtocolTag();
				tag.setId(curValue);
				if (map.get("tag_name") != null) {
					tag.setText(map.get("tag_name").toString());
				}
				if (map.get("sample_period") != null) {
					tag.setSamplingperiod(Integer.valueOf(map.get("sample_period").toString()));
				}
				valueList = new ArrayList<TdSimpleDataProtocolValue>(0);
			}

			preValue = curValue;
			valueList.add(tdValue);
		}
		if (tag != null) {
			tag.setValueList(valueList);
			tagList.add(tag);
		}

		return tagList;
	}

	@Override
	public List<TargetDevice> findList(TargetDevice data) {
		HqlFilter hqlFilter = new HqlFilter();
		if (null != data) {
			if (null != data.getCdRegCode() && !"".equals(data.getCdRegCode())) {
				hqlFilter.addFilter("QUERY_t#cdRegCode_S_LK",
						data.getCdRegCode());
			}
			if (null != data.getTdTypeCode()) {
				hqlFilter.addFilter("QUERY_t#tdTypeCode_I_EQ",
						data.getTdTypeCode().toString());
			}
		}
		
		return findByFilter(hqlFilter);
	}

	@Override
	public List<TargetDevice> findListByCdRegCodeList(List<String> cdRegCodes) {
		if(cdRegCodes.size() <= 0){
			return new ArrayList<>(0);
		}
		String hql = "select td from TargetDevice td"+
				" where td.cdRegCode in ( :cdRegCodeList )";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodes);
		return q.list();
	}

	@Override
	public int updateByCdRegCodeList(List<String> cdRegCodes,Short updateType,Date updateTime) {
		String hql = "update TargetDevice t set t.updateTime =:updateTime, " +
				" t.updateType=:updateType"+
				" where t.cdRegCode in (:cdRegCodeList)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodes);
		q.setTimestamp("updateTime", updateTime);
		q.setParameter("updateType", updateType);
		System.out.println(q.getQueryString());
		return q.executeUpdate();
	}

	@Override
	public void updateTdController(List<String> cds, TdControllerType controller,int typeCode,Short updateType) {
		String hql = "update TargetDevice t set t.updateTime =:updateTime, " +
				" t.updateType=:updateType,t.tdControllerType =:controller "+
				" where t.tdTypeCode=:typeCode and t.cdRegCode in (:cdRegCodeList)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cds);
		q.setTimestamp("updateTime", new Date());
		q.setParameter("updateType", updateType);
		q.setEntity("controller",controller );
		q.setParameter("typeCode", typeCode);
		System.out.println(q.getQueryString());
		q.executeUpdate();
	}

	@Override
	public TargetDevice getControllerTdByIdOrCdRegCodeAdress(TargetDevice data) {

		if (null != data) {
			if (StringUtils.isNotBlank(data.getId())) {
				return getById(data.getId());
			}else {
				HqlFilter hqlFilter = new HqlFilter();
				if(StringUtils.isNotBlank(data.getCdRegCode())){
					hqlFilter.addFilter("QUERY_t#cdRegCode_S_EQ",
							data.getCdRegCode());
				}
				if(data.getAddressCode() != null){
					hqlFilter.addFilter("QUERY_t#addressCode_I_EQ",
							data.getAddressCode().toString());
				}else {
					hqlFilter.addFilter("QUERY_t#addressCode_I_EQ",
							"0");
				}
				hqlFilter.addFilter("QUERY_t#tdTypeCode_I_EQ",
						"0");
				return getByFilter(hqlFilter);
			}

		}else {
			return null;
		}
	}

	@Override
	public void updateDhsCode(List<String> cdRegCodes, String dhsCode,String customerCode,Short updateType) {
		
		if(cdRegCodes.size() <= 0){
			return;
		}
		String hql = "update TargetDevice t set t.updateTime =:updateTime,t.dhsCode=:dhsCode, " +
				" t.updateType=:updateType,t.customerCode=:customerCode "+
				" where t.cdRegCode in (:cdRegCodeList)";
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodes);
		q.setTimestamp("updateTime", new Date(System.currentTimeMillis()-1000));
		q.setParameter("dhsCode", dhsCode);
		q.setParameter("updateType", updateType);
		q.setParameter("customerCode", customerCode);
		System.out.println(q.getQueryString());
		q.executeUpdate();
		
	}

}
