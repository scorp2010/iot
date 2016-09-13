package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.FileInfoDao;
import cn.inovance.iotgp.common.domain.FileInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;

@Repository
public class FileInfoDaoImpl extends BaseDaoImpl<FileInfo> implements
		FileInfoDao {
	@Override
	public Long countFileInfoByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from FileInfo t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<FileInfo> findFileInfoByParam(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from FileInfo t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<FileInfo> findSoftFile() {
		String hql = "select distinct t from FileInfo t where t.fileType.code='1'";
		return find(hql);
	}

	@Override
	public List<FileInfo> findFileInfo() {
		String hql = "select distinct t from FileInfo t ";
		return find(hql);
	}

	@Override
	public FileInfo findByMd5(String md5) {
		FileInfo fileInfo = null;
		String hql = "select distinct t from FileInfo t where t.fileMd5='"
				+ md5 + "'";
		List<FileInfo> list = find(hql);
		if (list.size() > 0) {
			fileInfo = list.get(0);
		}
		return fileInfo;
	}

	@Override
	public FileInfo findFileInfoById(String id) {
		return getById(id);
	}

	@Override
	public List<FileInfo> findFileInfoExcludeHasSoftInfo(String fileName,
			int page, int rows) {
		String sql = "select ffi.* from t_fms_file_info ffi left join " +
				"t_mms_software_info msi on msi.file_info_id = ffi.id where msi.id is null ";

		if (StringUtils.isNotBlank(fileName)) {
			sql += "and ffi.file_TrueName like '%" + fileName + "%'";
		}
		List<Map> list = findBySql(sql, page, rows);
		List<FileInfo> fileList = new ArrayList<FileInfo>(0);
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			FileInfo fileInfo = new FileInfo();
			fileInfo.setId(map.get("id").toString());
			fileInfo.setFileTrueName(map.get("file_TrueName").toString());
			fileInfo.setFileLength(Long.decode(map.get("file_length")
					.toString()));
			fileInfo.setFilePath(map.get("file_path").toString());
			fileList.add(fileInfo);
		}
		return fileList;

	}

	@Override
	public long countFileInfoExcludeHasSoftInfo(String fileName) {

		String sql = "select count(ffi.id) from t_fms_file_info ffi left join t_mms_software_info msi on msi.file_info_id = ffi.id where msi.id is null ";

		if (StringUtils.isNotBlank(fileName)) {
			sql += "and ffi.file_TrueName like '%" + fileName + "%'";
		}
		return countBySql(sql).longValue();
	}
}
