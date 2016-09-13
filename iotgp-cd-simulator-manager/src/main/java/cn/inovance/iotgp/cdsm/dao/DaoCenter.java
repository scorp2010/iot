package cn.inovance.iotgp.cdsm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCenter {

	@Autowired
	private CdRegisterCodeDao cdRegisterCodeDao;

	public CdRegisterCodeDao getCdRegisterCodeDao() {
		return cdRegisterCodeDao;
	}

	public void setCdRegisterCodeDao(CdRegisterCodeDao cdRegisterCodeDao) {
		this.cdRegisterCodeDao = cdRegisterCodeDao;
	}

}
