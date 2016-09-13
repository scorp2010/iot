package cn.inovance.iotgp.cdsm.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticValues {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StaticValues.class);

	/** 持久属性集 */
	public static Properties pt;
	
	/** IoSession的序列号保存key值 */
	public static final String KEY_IOSESSION = "session";
	public static final String KEY_IOSESSION_ID = "session_id";
	public static final String KEY_IOSESSION_KEY = "session_key";
	public static final String KEY_IOSESSION_HOST = "session_host";
	public static final String KEY_IOSESSION_PORT = "session_port";

	public static final String REGCODE_CORPCODE = "szinovance";
	
	public static String sysAccount = "cdsm_001@domaintest";
	
	/** 对外访问ip */
	public static String externalHost = "0.0.0.0";
	/** 对外访问端口 */
	public static int externalPort = 6789;
	/** 维护的注册码数量 */
	public static int regCodeCount = 1 * 10000;
	
	public static boolean geneZhujiFlag = false;
	
	public static boolean initDataFlag = false;

	static {
		try {
			// 读取配置文件
			InputStream is = StaticValues.class
					.getResourceAsStream("/global.properties");
			pt = new Properties();
			pt.load(is);

			externalHost = pt.getProperty("external_host");
			externalPort = Integer.parseInt(pt.getProperty("external_port"));
			regCodeCount = Integer.parseInt(pt.getProperty("regCode_Count"));
			geneZhujiFlag = Boolean.parseBoolean(pt.getProperty("gene_zhuji_flag"));
			//initDataFlag = Boolean.parseBoolean(pt.getProperty("init_data_flag"));
		} catch (IOException e) {
			LOGGER.info("获取全局变量失败", e);
		}
	}
}
