package cn.inovance.iotgp.cdsm.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextRegister implements ApplicationContextAware {

	private Log log = LogFactory.getLog(getClass());

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationUtil.setApplicationContext(applicationContext);
		log.debug("ApplicationContext registed");
	}

}
