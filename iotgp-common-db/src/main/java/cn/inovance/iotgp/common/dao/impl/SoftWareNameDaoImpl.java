package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SoftWareInfoDao;
import cn.inovance.iotgp.common.dao.SoftWareNameDao;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareName;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.BeanUtils;

@Repository
public class SoftWareNameDaoImpl extends BaseDaoImpl<SoftwareName> implements
		SoftWareNameDao {
	@Override
	public Long countSoftWareNameByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SoftwareName t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<SoftwareName> findSoftWareNameByParam(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SoftwareName t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public SoftwareName querySoftwareNameByParam(SoftwareName data) {
		String sql = "select t.* from t_mms_software_name t where t.name='"+ data.getName() +"'";
		List list = findBySql(sql);
		return list.size() < 1 ? null : new SoftwareName();
	}

	@Override
	public void saveSoftwareName(SoftwareName data) {
		save(data);
	}

	@Override
	public List<SoftwareName> findSoftWareName() {
		String hql = "select distinct t from SoftwareName t ";
		return find(hql);
	}

	@Override
	public void updateSoftwareName(SoftwareName data) {
		if (!StringUtils.isBlank(data.getId())) {
			SoftwareName t = getById(data.getId());
			BeanUtils.copyNotNullProperties(data, t,
					new String[] { "createTime", "creator", "updateTime" });
			//t.setFileInfo(data.getFileInfo());
			update(t);
		}
	}

	@Override
	public List<SoftwareName> findWithGroupBySoftName(SoftwareName data) {
		
		String sql = "select name from t_mms_software_name t where t.name like '%"+data.getName()+"%'  group by name";
		List<Map> list  = findBySql(sql);
		List<SoftwareName> softwareInfoList = new ArrayList<SoftwareName>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			SoftwareName softwareInfo = new SoftwareName();
			softwareInfo.setName(map.get("name").toString());
			softwareInfoList.add(softwareInfo);
		}
		return softwareInfoList;
	}
	
	@Override
	public Long checkSoftName(SoftwareName data) {
		String hql = "select count(distinct t.name) from SoftwareName t where t.name ='"+data.getName()+"'";
		return count(hql);
	}
}
