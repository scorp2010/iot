package cn.inovance.iotgp.cdsm.domain.generator;

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

public class UUIDGenerator implements IdentifierGenerator, Configurable {

	@SuppressWarnings("unused")
	private String sep = "";

	public void configure(Type type, Properties params, Dialect d)
			throws MappingException {
		sep = ConfigurationHelper.getString("separator", params, "");
	}

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

	public static String generate() {
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