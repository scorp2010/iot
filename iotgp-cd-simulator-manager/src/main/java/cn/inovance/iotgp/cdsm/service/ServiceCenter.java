package cn.inovance.iotgp.cdsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCenter {

	@Autowired
	private CdRegisterCodeService cdRegisterCodeService;

	public CdRegisterCodeService getCdRegisterCodeService() {
		return cdRegisterCodeService;
	}

	public void setCdRegisterCodeService(CdRegisterCodeService cdRegisterCodeService) {
		this.cdRegisterCodeService = cdRegisterCodeService;
	}
	
}
