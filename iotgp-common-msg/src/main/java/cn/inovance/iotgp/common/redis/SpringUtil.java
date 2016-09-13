/**
 * Project Name:sshe
 * File Name:SpringUtil.java
 * Package Name:sy.util.web
 * Date:2014-4-1下午2:10:15
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ClassName:SpringUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-1 下午2:10:15 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class SpringUtil {
	
	private static final String fileSystemPath = "/res/spring-redis.xml";
	
	private static final String classPath = "spring-redis.xml";

	public static ApplicationContext context = null;
	
	static {
		try{
			context = new FileSystemXmlApplicationContext(fileSystemPath);
		}catch (Exception e){
			//context = new ClassPathXmlApplicationContext(classPath);
		}
		if(context == null){
			context = new ClassPathXmlApplicationContext(classPath);
		}
	}

	public static Object getBean(String serviceName) {
		return context.getBean(serviceName);
	}
	
}
