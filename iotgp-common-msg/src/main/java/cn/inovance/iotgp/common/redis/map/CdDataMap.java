/**
a * Project Name:iotgp-common-msg
 * File Name:CdMap.java
 * Package Name:cn.inovance.iotgp.common.redis.map
 * Date:2014-6-4下午1:50:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.redis.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.inovance.iotgp.common.redis.RedisKeyPrefix;
import cn.inovance.iotgp.common.redis.SessionService;

/**
 * ClassName:CdMap <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-4 下午1:50:45 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class CdDataMap {

	private static Log logger = LogFactory.getLog(CdDataMap.class);
	public static final String RUN_STATUS = "run_status";
	public static final String CD_PARAMETERS = "cd_send_msg";
	public static final String CD_MSG_RESULT = "cd_msg_result";
	static {
		SessionService.redisTemplate
				.setKeySerializer(new StringRedisSerializer());
		SessionService.redisTemplate
				.setValueSerializer(new JdkSerializationRedisSerializer());

	}

	public static void setCdRunStatusMap(String cdregCode,
			Map<Short, String> status) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.putAll(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + RUN_STATUS, status);
	}

	public static void setCdRunStatus(String cdregCode, Short module,
			String value) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.put(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + RUN_STATUS, module, value);
	}

	public static Map<Short, String> getCdRunStatusMap(String cdregCode) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		Map<Short, String> cdRunStatus = hashOperations
				.entries(RedisKeyPrefix.CD_DATA + RedisKeyPrefix.KEY_DELIMITER
						+ cdregCode + RedisKeyPrefix.KEY_DELIMITER + RUN_STATUS);
		return cdRunStatus;
	}

	public static Map<Short, String> getCdRunStatusMap(String cdregCode,
			List<Short> listModules) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		List<String> resultList = hashOperations
				.multiGet(
						RedisKeyPrefix.CD_DATA + RedisKeyPrefix.KEY_DELIMITER
								+ cdregCode + RedisKeyPrefix.KEY_DELIMITER
								+ RUN_STATUS, listModules);
		Map<Short, String> cdRunStatus = new HashMap<Short, String>(0);
		Short index = 0;
		for (String each : resultList) {
			cdRunStatus.put(listModules.get(0), each);
			index++;
		}
		return cdRunStatus;
	}

	public static void removeCdRunStatusMap(String cdregCode) {
		SessionService.redisTemplate.delete(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + RUN_STATUS);
	}

	public static void removeCdRunStatus(String cdregCode, Short module) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.delete(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + RUN_STATUS, module);
	}

	public static void setSendCdMsgObject(String cdregCode, Long seq, Object obj) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.put(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + CD_PARAMETERS, seq, obj);
	}

	public static Object getSendCdMsgObject(String cdregCode, Long seq) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		return hashOperations.get(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + CD_PARAMETERS, seq);

	}

	public static void removeSendCdMsgObject(String cdregCode, Long seq) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.delete(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + CD_PARAMETERS, seq);

	}

	public static void setMsgResultObject(String cdregCode, Integer msgType,
			Object obj) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.put(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + CD_MSG_RESULT, msgType, obj);
	}

	public static Object getMsgResultObject(String cdregCode, Integer msgType) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		return hashOperations.get(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + CD_MSG_RESULT, msgType);

	}

	public static void removeMsgResultObject(String cdregCode, Integer msgType) {
		HashOperations hashOperations = SessionService.redisTemplate
				.opsForHash();
		hashOperations.delete(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + cdregCode
				+ RedisKeyPrefix.KEY_DELIMITER + CD_MSG_RESULT, msgType);

	}

	public static Short getDeviceSoftwareUpdateProgress(String deviceSerial,
			String softwareName, String softwareVersion) {
		HashOperations<String, String, Short> valueOper = SessionService.redisTemplate
				.opsForHash();
		Short updateProgess = valueOper.get(RedisKeyPrefix.CD_DATA
				+ RedisKeyPrefix.KEY_DELIMITER + deviceSerial
				+ RedisKeyPrefix.KEY_DELIMITER + "SoftwareUpdateInfo",
				softwareName + RedisKeyPrefix.KEY_DELIMITER + softwareVersion);
		return updateProgess;
	}
}
