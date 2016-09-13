/**
 * Project Name:sshe
 * File Name:RedisKeyPrefix.java
 * Package Name:sy.util.web
 * Date:2014-4-2上午9:01:08
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.redis;

/**
 * ClassName:RedisKeyPrefix <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-2 上午9:01:08 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class RedisKeyPrefix {

	public static final String KEY_DELIMITER = ":";

	/** GDHS服务器session数据. */
	public static final String GDHS_SESSION = "gdhs";

	/** CDAG服务器session数据. */
	public static final String CDAG_SESSION = "cdag";

	/** RDTS服务器session数据. */
	public static final String RDTS_SESSION = "rdts";
	
	/** MDDS服务器session数据. */
	public static final String MDDS_SESSION = "mdds";
	
	/** 用户登录session数据. */
	public static final String LOGIN_SESSION = "login";
	
	/** fmsuser数据. */
	public static final String FMS_DATA = "fmsData";
	
	/** CD运行状态数据. */
	public static final String CD_DATA = "cdData";
	
	
}
