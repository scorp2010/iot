package cn.inovance.iotgp.cdsm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.inovance.iotgp.cdsm.server.CdsmServer;
import cn.inovance.iotgp.cdsm.util.ApplicationUtil;

/**
 * CdsmServer监听器
 * 
 * @author c2100
 * 
 */
public class CdsmServerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CdsmServer cdsmServer = (CdsmServer) ApplicationUtil
				.getApplicationContext().getBean("cdsmServer");
		cdsmServer.init();
		new Thread(cdsmServer).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
