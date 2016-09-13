package cn.inovance.iotgp.cdsm.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.inovance.iotgp.cdsm.domain.CdRegisterCode;
import cn.inovance.iotgp.cdsm.service.BaseService;
import cn.inovance.iotgp.cdsm.service.CdRegisterCodeService;
import cn.inovance.iotgp.cdsm.util.MD5Util;
import cn.inovance.iotgp.cdsm.util.RandomCode;

@Service
public class CdRegisterCodeServiceImpl extends BaseService implements
		CdRegisterCodeService {

	@Override
	public boolean deleteCDSMCdRegisterCode() {
		return this.dc.getCdRegisterCodeDao().deleteCDSMCdRegisterCode();
	}

	@Override
	public boolean initCdRegisterCode(int start, int count) {

		String corpCode = "szinovance";
		String productDate = "2003-01-01";
		String encrypKey = "30012466300124663001246630012466";
		for (int i = 0; i < count; i++) {
			try {
				String regCode = String.format("%s%s%s", corpCode, 100000000
						+ start + i, "000000000");
				String randomCode = RandomCode.generateCodeN(8);
				String mac = RandomCode.generateCodeN(12);
				// 加密sn生成方法：registercoder（序列号28个字符）+ mac地址（12个字符）+ 随机数（8个字符）+
				// key(32个字符)+ date（8个字符），得出16位md5值
				String encryptSn = MD5Util.get16bitmd5(regCode + mac
						+ randomCode + encrypKey + "20130101");

				CdRegisterCode cdRegisterCode = new CdRegisterCode();
				cdRegisterCode.setActiveTime(new Date());
				cdRegisterCode.setBoardSn("NK47DDE44A7" + i);
				cdRegisterCode.setCdActiveStatus("2");
				cdRegisterCode.setCdTestStatus("2");
				cdRegisterCode.setCreateTime(new Date());
				cdRegisterCode.setCreator("cdsm");
				cdRegisterCode.setCustomerCode("");
				cdRegisterCode.setCustomerName("");
				cdRegisterCode.setEncryptKey(encrypKey);
				cdRegisterCode.setEncryptSn(encryptSn);
				cdRegisterCode.setMac(mac);
				cdRegisterCode.setProductionDate(formatDate.parse(productDate));
				cdRegisterCode.setProductModel("1");
				cdRegisterCode.setProductSn("1");
				cdRegisterCode.setRandomCode(randomCode);
				cdRegisterCode.setRegCode(regCode);
				cdRegisterCode.setSimImsi("1");
				cdRegisterCode.setTestTime(new Date());
				cdRegisterCode.setUpdateTime(new Date());

				this.dc.getCdRegisterCodeDao().save(cdRegisterCode);

				/*
				 * CdRegisterCode existCdRegisterCode = this.dc
				 * .getCdRegisterCodeDao().getUniqueCdRegisterCode( "regCode",
				 * regCode); if (existCdRegisterCode == null) { //
				 * logger.info("注册码【{}】不存在，将新增此注册码", regCode);
				 * this.dc.getCdRegisterCodeDao().save(cdRegisterCode); } else {
				 * // logger.warn("注册码【{}】已存在，将不新增此注册码", regCode); }
				 */
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("initCdRegisterCode() Error:" + e.getMessage());
				continue;
			}
		}

		return true;
	}
	
	@Override
	public boolean initZhujiCdRegisterCode(int start, int count) {

		String corpCode = "szinovancf";
		String productDate = "2003-01-01";
		String encrypKey = "30012466300124663001246630012466";
		for (int i = 0; i < count; i++) {
			try {
				String regCode = String.format("%s%s%s", corpCode, 100000000
						+ start + i, "000000000");
				String randomCode = RandomCode.generateCodeN(8);
				String mac = RandomCode.generateCodeN(12);
				// 加密sn生成方法：registercoder（序列号28个字符）+ mac地址（12个字符）+ 随机数（8个字符）+
				// key(32个字符)+ date（8个字符），得出16位md5值
				String encryptSn = MD5Util.get16bitmd5(regCode + mac
						+ randomCode + encrypKey + "20130101");

				CdRegisterCode cdRegisterCode = new CdRegisterCode();
				cdRegisterCode.setActiveTime(new Date());
				cdRegisterCode.setBoardSn("NK47DDE44A8" + i);
				cdRegisterCode.setCdActiveStatus("2");
				cdRegisterCode.setCdTestStatus("2");
				cdRegisterCode.setCreateTime(new Date());
				cdRegisterCode.setCreator("cdsm");
				cdRegisterCode.setCustomerCode("");
				cdRegisterCode.setCustomerName("");
				cdRegisterCode.setEncryptKey(encrypKey);
				cdRegisterCode.setEncryptSn(encryptSn);
				cdRegisterCode.setMac(mac);
				cdRegisterCode.setProductionDate(formatDate.parse(productDate));
				cdRegisterCode.setProductModel("1");
				cdRegisterCode.setProductSn("1");
				cdRegisterCode.setRandomCode(randomCode);
				cdRegisterCode.setRegCode(regCode);
				cdRegisterCode.setSimImsi("1");
				cdRegisterCode.setTestTime(new Date());
				cdRegisterCode.setUpdateTime(new Date());

				this.dc.getCdRegisterCodeDao().save(cdRegisterCode);

				/*
				 * CdRegisterCode existCdRegisterCode = this.dc
				 * .getCdRegisterCodeDao().getUniqueCdRegisterCode( "regCode",
				 * regCode); if (existCdRegisterCode == null) { //
				 * logger.info("注册码【{}】不存在，将新增此注册码", regCode);
				 * this.dc.getCdRegisterCodeDao().save(cdRegisterCode); } else {
				 * // logger.warn("注册码【{}】已存在，将不新增此注册码", regCode); }
				 */
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("initCdRegisterCode() Error:" + e.getMessage());
				continue;
			}
		}

		return true;
	}

}
