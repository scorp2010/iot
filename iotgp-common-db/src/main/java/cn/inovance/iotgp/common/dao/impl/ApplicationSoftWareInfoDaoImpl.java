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

import cn.inovance.iotgp.common.dao.ApplicationSoftWareInfoDao;
import cn.inovance.iotgp.common.dao.SoftWareInfoDao;
import cn.inovance.iotgp.common.domain.ApplicationSoftwareInfo;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.BeanUtils;

@Repository
public class ApplicationSoftWareInfoDaoImpl extends BaseDaoImpl<ApplicationSoftwareInfo> implements
		ApplicationSoftWareInfoDao {
	@Override
	public Long countApplicationSoftWareInfoByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from ApplicationSoftwareInfo t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public ApplicationSoftwareInfo querySoftwareInfoByParam(ApplicationSoftwareInfo data) {
		String sql = "select t.* from t_mms_application_software_info t where (t.name='"
				+ data.getName() + "' and t.version='" + data.getVersion()+"')"
				+ "or t.soft_file_md5='" + data.getSoftFileMd5() + "'";
//		List list = findBySql(sql);
		List<Map<String, String>> list = findBySql(sql);
		ApplicationSoftwareInfo softwareInfo=new ApplicationSoftwareInfo();
		System.out.println(list.size()<1);
		if (list!=null&&list.size()>=1) {
//			softwareInfo.setSoftFileMd5(((Map<String, String>)list.get(0)).get("soft_file_md5"));
			softwareInfo.setSoftFileMd5(list.get(0).get("soft_file_md5"));
		}
		return list.size() < 1 ? null : softwareInfo;
	}

	@Override
	public void saveApplicationSoftwareInfo(ApplicationSoftwareInfo data) {
		save(data);
	}

	@Override
	public void updateSoftwareInfo(ApplicationSoftwareInfo data) {
		if (!StringUtils.isBlank(data.getId())) {
			ApplicationSoftwareInfo t = getById(data.getId());
			BeanUtils.copyNotNullProperties(data, t,
					new String[] { "fileInfo","createTime", "creator","desc" });
			//t.setFileInfo(data.getFileInfo());
			update(t);
		}
	}

	@Override
	public List<ApplicationSoftwareInfo> findWithGroupFromSoftName(ApplicationSoftwareInfo data) {
		String sql = "select name from t_mms_application_software_info t where t.name like '%"+data.getName()+"%'  group by name";
		List<Map> list  = findBySql(sql);
		List<ApplicationSoftwareInfo> softwareInfoList = new ArrayList<ApplicationSoftwareInfo>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			ApplicationSoftwareInfo softwareInfo = new ApplicationSoftwareInfo();
			softwareInfo.setName(map.get("name").toString());
			softwareInfoList.add(softwareInfo);
		}
		return softwareInfoList;
	}

	@Override
	public List<ApplicationSoftwareInfo> findSoftWareByNameAndVersion(
			ApplicationSoftwareInfo data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = sdf.format(data.getCreateTime());
		String sql = "select t.* from t_mms_application_software_info t where t.name='"
				+ data.getName() + "' and t.create_time >= STR_TO_DATE('"+createTime+"','%Y-%m-%d %H:%i:%s')";
		List<Map> list  = findBySql(sql);
		List<ApplicationSoftwareInfo> softwareInfoList = new ArrayList<ApplicationSoftwareInfo>(0);
		
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			ApplicationSoftwareInfo softwareInfo = new ApplicationSoftwareInfo();
			softwareInfo.setName(map.get("name").toString());
			softwareInfo.setVersion(map.get("version").toString());
			softwareInfo.setDesc(map.get("soft_desc").toString());
			softwareInfo.setSoftFileName(map.get("soft_file_name").toString());
			softwareInfo.setSoftFilePath(map.get("soft_file_path").toString());
			softwareInfo.setSoftFileMd5(map.get("soft_file_md5").toString());
			String create_time = "";
			if(map.get("create_time") != null){
				create_time = String.valueOf(map.get("create_time"));
			}
			if(map.get("create_time") != null){
				softwareInfo.setCreateTime(sdf.parse(create_time));
			}
			softwareInfoList.add(softwareInfo);
		}
		return softwareInfoList;
	}
}
