package cn.inovance.iotgp.common.dao;

import java.util.Date;
import java.util.List;

import javax.swing.ListModel;


import cn.inovance.iotgp.common.domain.TargetDevice;
import cn.inovance.iotgp.common.domain.TdControllerType;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.TdSimpleDataProtocolTag;

public interface TargetDeviceDao extends BaseDao<TargetDevice> {

	public Long countTargetDeviceByParam(HqlFilter hqlFilter);

	public List<TargetDevice> findTargetDeviceByParam(HqlFilter hqlFilter,
			int page, int rows);

	public List<TargetDevice> findListByCdRegCode(HqlFilter hqlFilter);

	public Long countTargetDevice(TargetDevice data);
	
	public List<TdSimpleDataProtocolTag> findTdProtocol(TargetDevice data);
	
	public List<TargetDevice> findList(TargetDevice data);
	public List<TargetDevice> findListByCdRegCodeList(List<String> cdRegCodes);
	public int updateByCdRegCodeList(List<String> cdRegCodes,Short updateType,Date updateTime);
	public void updateTdController(List<String> cds,TdControllerType controller ,int typeCode,Short updateType);
	
	public TargetDevice getControllerTdByIdOrCdRegCodeAdress(TargetDevice data);
	
	public void updateDhsCode(List<String> cdRegCodes,String dhsCode,String customerCode,Short updateType);
	
}
