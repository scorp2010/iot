package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.domain.FileInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;

public interface FileInfoDao {

	public Long countFileInfoByParam(HqlFilter hqlFilter);

	public List<FileInfo> findFileInfoByParam(HqlFilter hqlFilter, int page,
			int rows);

	public List<FileInfo> findSoftFile();

	public List<FileInfo> findFileInfo();

	public FileInfo findByMd5(String md5);

	public FileInfo findFileInfoById(String id);

	public void delete(FileInfo fileInfo);

	public  List<FileInfo> findFileInfoExcludeHasSoftInfo(String fileName,int page,int rows);
	
	public long countFileInfoExcludeHasSoftInfo(String fileName);
}
