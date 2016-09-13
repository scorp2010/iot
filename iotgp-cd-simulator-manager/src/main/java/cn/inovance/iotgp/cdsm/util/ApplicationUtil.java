package cn.inovance.iotgp.cdsm.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

public class ApplicationUtil {

	private static Log logger = LogFactory.getLog(ApplicationUtil.class);

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(
			ApplicationContext applicationContext) {
		synchronized (ApplicationUtil.class) {
			logger.info("setApplicationContext, notifyAll");
			ApplicationUtil.applicationContext = applicationContext;
			ApplicationUtil.class.notifyAll();
		}
	}

	public static ApplicationContext getApplicationContext() {
		synchronized (ApplicationUtil.class) {
			while (applicationContext == null) {
				try {
					logger.info("getApplicationContext, wait...");
					ApplicationUtil.class.wait(5 * 60 * 1000);
					if (applicationContext == null) {
						logger.warn(
								"Have been waiting for ApplicationContext to be set for 5 minute",
								new Exception());
					}
				} catch (InterruptedException ex) {
					logger.warn("getApplicationContext, wait interrupted");
				}
			}
			return applicationContext;
		}
	}

	/**
	 * 这是一个便利的方法，帮助我们快速得到一个BEAN
	 * 
	 * @param beanName
	 *            bean的名字
	 * @return 返回一个bean对象
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

}
