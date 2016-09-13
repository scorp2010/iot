package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.DeviceSyncInfoDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CdAllocationLog;
import cn.inovance.iotgp.common.domain.DeviceSyncInfo;

@Repository
public class DeviceSyncInfoDaoImpl extends BaseDaoImpl<DeviceSyncInfo>
		implements DeviceSyncInfoDao {

	@Autowired
	private SuperBaseDao superBaseDao;
	
	@Override
	public void saveAll(List<DeviceSyncInfo> list) {
		 for(int i=0;i<list.size();i++){
			 DeviceSyncInfo deviceSyncInfo = list.get(i);
			 superBaseDao.getCurrentSession().save(deviceSyncInfo);
			 if(i % 20 == 0){
				 superBaseDao.getCurrentSession().flush();
				 superBaseDao.getCurrentSession().clear();
			 }
		 }
		
	}
	
}
