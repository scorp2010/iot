package cn.inovance.iotgp.cdsm.service;

public interface CdRegisterCodeService {

	boolean deleteCDSMCdRegisterCode();
	
	boolean initCdRegisterCode(int start,int count);
	
	boolean initZhujiCdRegisterCode(int start,int count);
}
