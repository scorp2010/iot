/**
 * Project Name:domain
 * File Name:UUIDGenerator.java
 * Package Name:cn.inovance.iotgp.hibernate.uuid
 * Date:2014-4-22下午6:25:37
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.uuid;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;

/**
 * ClassName:UUIDGenerator <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-22 下午6:25:37 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class UUIDGenerator implements IdentifierGenerator, Configurable {

	private String sep = "";

	@Override
	public void configure(Type type, Properties params, Dialect d)
			throws MappingException {

		sep = ConfigurationHelper.getString("separator", params, "");

	}

	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		String uuidStr = UUID.randomUUID().toString();

		long time = System.currentTimeMillis();
		String timeStr = time + "";

		String timeTemp = timeStr.substring(0, 12);

		StringBuffer sb = new StringBuffer(timeTemp);
		String[] uuidArray = uuidStr.split("-");

		for (String uuidStrTemp : uuidArray) {
			sb.append(uuidStrTemp);
		}

		return sb.substring(0, 32);
	}
	public static String generate(){
		String uuidStr = UUID.randomUUID().toString();

		long time = System.currentTimeMillis();
		String timeStr = time + "";

		String timeTemp = timeStr.substring(0, 12);

		StringBuffer sb = new StringBuffer(timeTemp);
		String[] uuidArray = uuidStr.split("-");

		for (String uuidStrTemp : uuidArray) {
			sb.append(uuidStrTemp);
		}

		return sb.substring(0, 32);
	}
}
