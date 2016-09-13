package cn.inovance.iotgp.cdSimulator.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CdRegisterCode;

@Repository
public class CdSimulatorDao {

	@Autowired
	private SuperBaseDao<CdRegisterCode> superBaseDao;

	public List<CdRegisterCode> find(String hql, int start, int count) {
		return superBaseDao.findLimit(hql, start, count);
	}

	public SuperBaseDao<CdRegisterCode> getSuperBaseDao() {
		return superBaseDao;
	}

	public void setSuperBaseDao(SuperBaseDao<CdRegisterCode> superBaseDao) {
		this.superBaseDao = superBaseDao;
	}

}
