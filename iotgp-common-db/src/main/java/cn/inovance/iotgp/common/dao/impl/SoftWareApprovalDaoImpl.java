package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SoftWareApprovalDao;
import cn.inovance.iotgp.common.dao.SoftWareInfoDao;
import cn.inovance.iotgp.common.dao.SoftWareNameDao;
import cn.inovance.iotgp.common.domain.SoftwareApproval;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareName;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.BeanUtils;

@Repository
public class SoftWareApprovalDaoImpl extends BaseDaoImpl<SoftwareApproval> implements
		SoftWareApprovalDao {
	@Override
	public Long countSoftWareApprovalByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SoftwareApproval t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<SoftwareApproval> findSoftWareApprovalByParam(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SoftwareApproval t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public SoftwareApproval querySoftwareApprovalByParam(SoftwareApproval data) {
		String sql = "select t.* from t_mms_software_approval t where t.name='"+ data.getName() +"' and t.version='"+ data.getVersion() +"'";
		List list = findBySql(sql);
		return (SoftwareApproval) (list.size() < 1 ? null : list.get(0));
	}
	
	/**
	 * 添加
	 * 
	 * @param SedsController
	 */
	public void saveSoftwareApproval(SoftwareApproval data) {
		save(data);
	}
	

	@Override
	public void updateSoftwareApproval(SoftwareApproval data) {
		if (!StringUtils.isBlank(data.getId())) {
			SoftwareApproval t = getById(data.getId());
			BeanUtils.copyNotNullProperties(data, t,
					new String[] { "remark", "pendingVersion", "pendingSoft","controlTypeName" });
			//t.setFileInfo(data.getFileInfo());
			update(t);
		}
	}

	@Override
	public List<SoftwareApproval> findWithGroupBySoftName(SoftwareApproval data) {
		
		String sql = "select name from t_mms_software_name t where t.name like '%"+data.getName()+"%'  group by name";
		List<Map> list  = findBySql(sql);
		List<SoftwareApproval> softwareInfoList = new ArrayList<SoftwareApproval>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			SoftwareApproval softwareInfo = new SoftwareApproval();
			softwareInfo.setName(map.get("name").toString());
			softwareInfoList.add(softwareInfo);
		}
		return softwareInfoList;
	}
}
