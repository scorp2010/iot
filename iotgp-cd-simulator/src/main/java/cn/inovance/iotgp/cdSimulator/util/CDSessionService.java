package cn.inovance.iotgp.cdSimulator.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.main.CdSimulator;

/**
 * ClassName: SessionService <br/>
 * Function: Redis数据访问客户端封装类. <br/>
 * date: 2014-4-8 上午9:28:15 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings({"unchecked","rawtypes"})
@Component
public class CDSessionService {
	/*** 创建全局的唯一redisTemplate实例. */
	public static RedisTemplate redisTemplate = (RedisTemplate) CdSimulator.redisTemplate;
	/** 本类采用单实例模式. */
	private static CDSessionService instance = null;
	/** Web用户session数据超时时间(分钟). */
	private final long webSessionExpiryTime = 30;

	private static ValueOperations<String, Object> valueOper;
	
	private static HashOperations<String, String, Object> hashValueOper;

	static {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

		valueOper = redisTemplate.opsForValue();
		hashValueOper = redisTemplate.opsForHash();
	}

	public static void setValueSerializer(
			JacksonJsonRedisSerializer<Object> jacksonJsonRedisSerializer) {
		redisTemplate.setValueSerializer(jacksonJsonRedisSerializer);
	}

	/**
	 * 取得超时时间.
	 * 
	 * @return long
	 */
	public long getWebSessionTimeoutSecends() {
		long time = webSessionExpiryTime * 60;

		return time;
	}

	/**
	 * 单例模式创建SessionService对象.
	 * 
	 * @return SessionService
	 */
	public static CDSessionService getInstance() {
		if (instance == null) {
			instance = new CDSessionService();
		}
		return instance;
	}

	/**
	 * 构造函数初始化
	 */
	protected CDSessionService() {

	}

	/**
	 * 判断Redis中是否存在该关键字
	 * 
	 * @param key
	 *            关键字
	 * @return
	 */
	public boolean sessionExists(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * get:获取关键字对应的值域. <br/>
	 * 
	 * @author z1979
	 * @param key
	 *            关键字
	 * @return 关键字对应的值域
	 * @since JDK 1.7
	 */
	public Object get(String key) {
		Object value = null;
		if (sessionExists(key)) {
			value = valueOper.get(key);
			valueOper.set(key, value);
		}
		return value;
	}

	/**
	 * get:获取关键字对应的值域，同时修改关键字的超时时间. <br/>
	 * 
	 * @author z1979
	 * @param key
	 *            关键字
	 * @param timeoutSecends
	 *            数据超时时间（秒）
	 * @return 关键字对应的值域
	 * @since JDK 1.7
	 */
	public Object get(String key, long timeoutSecends) {
		Object value = null;
		if (sessionExists(key)) {
			value = valueOper.get(key);
			valueOper.set(key, value, timeoutSecends, TimeUnit.SECONDS);
		}
		return value;
	}

	/**
	 * saveTimeoutTime:保存关键字及对应的值，不指定超时时间. <br/>
	 * 
	 * @param key
	 *            关键字
	 * @param value
	 *            值域
	 */
	public void save(String key, Object value) {

		valueOper.set(key, value);
	}

	/**
	 * saveTimeoutTime:保存关键字及对应的值，同时设置关键字超时时间. <br/>
	 * 
	 * @author z1979
	 * @param key
	 *            关键字
	 * @param value
	 *            值域
	 * @param timeoutSecends
	 *            数据超时时间（秒）
	 * @since JDK 1.7
	 */
	public void saveTimeoutTime(String key, Object value, long timeoutSecends) {

		valueOper.set(key, value, timeoutSecends, TimeUnit.SECONDS);

	}
	
	public void saveDeviceLoginInfos(String clientSecurityCode, String cdRegCode, Object value){
		hashValueOper.put(clientSecurityCode, cdRegCode, value);
	}

	/**
	 * remove:删除关键字. <br/>
	 * 
	 * @author z1979
	 * @param key
	 *            关键字
	 * @since JDK 1.7
	 */
	public void remove(String key) {

		redisTemplate.delete(key);
	}

	/**
	 * updateExpiryDate:更新数据超时时间. <br/>
	 * 
	 * @author z1979
	 * @param key
	 *            关键字
	 * @param timeoutSeconds
	 *            数据超时时间（秒）
	 * @since JDK 1.7
	 */
	public void updateExpiryDate(String key, long timeoutSeconds) {
		redisTemplate.expire(key, timeoutSeconds, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		CDSessionService.getInstance();
	}

}
