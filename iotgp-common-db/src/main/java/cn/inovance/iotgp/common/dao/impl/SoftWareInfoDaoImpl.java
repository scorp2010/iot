package cn.inovance.iotgp.common.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SoftWareInfoDao;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.BeanUtils;

@Repository
public class SoftWareInfoDaoImpl extends BaseDaoImpl<SoftwareInfo> implements
		SoftWareInfoDao {
	@Override
	public Long countSoftWareInfoByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SoftwareInfo t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<SoftwareInfo> findSoftWareInfoByParam(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SoftwareInfo t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public SoftwareInfo querySoftwareInfoByParam(SoftwareInfo data) {
		String sql = "select t.* from t_mms_software_info t where (t.name='"
				+ data.getName() + "' and t.version='" + data.getVersion()+"')"
				+ "or t.soft_file_md5='" + data.getSoftFileMd5() + "'";
//		List list = findBySql(sql);
		List<Map<String, String>> list = findBySql(sql);
		SoftwareInfo softwareInfo=new SoftwareInfo();
		System.out.println(list.size()<1);
		if (list!=null&&list.size()>=1) {
//			softwareInfo.setSoftFileMd5(((Map<String, String>)list.get(0)).get("soft_file_md5"));
			softwareInfo.setSoftFileMd5(list.get(0).get("soft_file_md5"));
		}
		return list.size() < 1 ? null : softwareInfo;
	}

	@Override
	public void saveSoftwareInfo(SoftwareInfo data) {
		save(data);
	}

	@Override
	public List<SoftwareInfo> findSoftWareInfo() {
		String hql = "select distinct t from SoftwareInfo t ";
		return find(hql);
	}

	@Override
	public void updateSoftwareInfo(SoftwareInfo data) {
		if (!StringUtils.isBlank(data.getId())) {
			SoftwareInfo t = getById(data.getId());
			BeanUtils.copyNotNullProperties(data, t,
					new String[] { "fileInfo","createTime", "creator", "auditTime",
							"auditorId", "status" });
			//t.setFileInfo(data.getFileInfo());
			update(t);
		}
	}

	@Override
	public List<SoftwareInfo> findWithGroupBySoftName(SoftwareInfo data) {
		
		String sql = "select name from t_mms_software_info t where t.name like '%"+data.getName()+"%'  group by name";
		List<Map> list  = findBySql(sql);
		List<SoftwareInfo> softwareInfoList = new ArrayList<SoftwareInfo>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			SoftwareInfo softwareInfo = new SoftwareInfo();
			softwareInfo.setName(map.get("name").toString());
			softwareInfoList.add(softwareInfo);
		}
		return softwareInfoList;
	}

	@Override
	public List<SoftwareInfo> findWithGroupFromSoftName(SoftwareInfo data) {
		String sql = "select name from t_mms_software_name t where t.name like '%"+data.getName()+"%'  group by name";
		List<Map> list  = findBySql(sql);
		List<SoftwareInfo> softwareInfoList = new ArrayList<SoftwareInfo>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			SoftwareInfo softwareInfo = new SoftwareInfo();
			softwareInfo.setName(map.get("name").toString());
			softwareInfoList.add(softwareInfo);
		}
		return softwareInfoList;
	}
	
	@Override
	public SoftwareInfo querySoftwareInfoById(String id) throws Exception {
		String sql = "select t.* from t_mms_software_info t where t.id='" + id + "'";
		List list = findBySql(sql);
		SoftwareInfo softInfo = new SoftwareInfo();
		System.out.println(list);
		if(list != null && list.size() > 0){
			Map map = (HashMap) list.get(0);
			String creator = String.valueOf(map.get("creator"));
			String create_time = "";
			if(map.get("create_time") != null){
				create_time = String.valueOf(map.get("create_time"));
			}
			String soft_environment = String.valueOf(map.get("soft_environment"));
			String soft_file_md5 = String.valueOf(map.get("soft_file_md5"));
			String version = String.valueOf(map.get("version"));
			String audit_time = "";
			if(map.get("audit_time") != null){
				audit_time = String.valueOf(map.get("audit_time"));
			}
			
			String soft_type = String.valueOf(map.get("soft_type"));
			String soft_file_length = "0";
			if(map.get("soft_file_length") != null){
				soft_file_length = String.valueOf(map.get("soft_file_length"));
			}
			String soft_desc = String.valueOf(map.get("soft_desc"));
			String soft_file_path = String.valueOf(map.get("soft_file_path"));
			String name = String.valueOf(map.get("name"));
			String auditorId = String.valueOf(map.get("auditor_id"));
			String softStatus =  String.valueOf(map.get("status"));
			String softFileName = String.valueOf(map.get("soft_file_name"));
			String status =  String.valueOf(map.get("audit_status"));
			String fileTypeCode =  String.valueOf(map.get("file_type_code"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			softInfo.setCreator(creator);
			if(map.get("create_time") != null){
				softInfo.setCreateTime(sdf.parse(create_time));
			}
			softInfo.setSoftwareEnvironment(soft_environment);
			softInfo.setSoftFileMd5(soft_file_md5);
			softInfo.setVersion(version);
			if(map.get("audit_time") != null){
				softInfo.setAuditTime(sdf.parse(audit_time));
			}
			softInfo.setSoftType(soft_type);
			if(map.get("soft_file_length") != null){
				softInfo.setSoftFileLength(Long.valueOf(soft_file_length));
			}
			softInfo.setDesc(soft_desc);
			softInfo.setSoftFilePath(soft_file_path);
			softInfo.setName(name);
			softInfo.setAuditorId(auditorId);
			softInfo.setStatus(softStatus);
			softInfo.setSoftFileName(softFileName);
			softInfo.setStatus(status);
			softInfo.setFileTypeCode(fileTypeCode);
			softInfo.setId(id);
			
		}
		
		return softInfo;
	}
}
