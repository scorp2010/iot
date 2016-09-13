package cn.inovance.iotgp.common.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ExtendedPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private Properties props;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		this.props = props;
	}

	public Object getProperty(String key) {
		return props.get(key);
	}
}
