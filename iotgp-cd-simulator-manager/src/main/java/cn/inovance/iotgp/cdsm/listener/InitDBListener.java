package cn.inovance.iotgp.cdsm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.service.CdRegisterCodeService;
import cn.inovance.iotgp.cdsm.util.ApplicationUtil;

/**
 * InitDBListener监听器
 * 
 * @author c2100
 * 
 */
public class InitDBListener implements ServletContextListener {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (StaticValues.initDataFlag){
			CdRegisterCodeService cdRegisterCodeService = (CdRegisterCodeService) ApplicationUtil
					.getBean("cdRegisterCodeServiceImpl");

			// 删除原来的regCode
			cdRegisterCodeService.deleteCDSMCdRegisterCode();

			// 生成regCode
			int totalCount = StaticValues.regCodeCount;
			int onceCount = 1000;
			int nowCount = 0;
			int savedCount = 0;
			while (totalCount > 0) {
				nowCount = totalCount > onceCount ? onceCount : totalCount;
				logger.info("Init RegCode {}~{}/{}", savedCount, savedCount
						+ nowCount, StaticValues.regCodeCount);
				cdRegisterCodeService.initCdRegisterCode(savedCount, nowCount);
				if (StaticValues.geneZhujiFlag){
					cdRegisterCodeService.initZhujiCdRegisterCode(savedCount, nowCount);
				}
				savedCount += nowCount;
				totalCount -= onceCount;
			}
		}
		}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
