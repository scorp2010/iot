package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.DeviceSyncInfo;

public interface DeviceSyncInfoDao extends BaseDao<DeviceSyncInfo> {

	
	public void saveAll(List<DeviceSyncInfo> list);
}
