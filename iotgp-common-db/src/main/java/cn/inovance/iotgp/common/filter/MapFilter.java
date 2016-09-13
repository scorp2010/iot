/**
 * Project Name:bms
 * File Name:MapFilter.java
 * Package Name:cn.inovance.iotgp.bms.web.filter
 * Date:2014-4-17上午11:14:32
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:MapFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-17 上午11:14:32 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class MapFilter {

	private Map<Integer, String> params = new HashMap<Integer, String>(0);// 条件参数

	public void AddParameter(Integer key, String value) {
		params.put(key, value);
	}

	public String getParameterValue(Integer key) {
		if (params.containsKey(key)) {
			return params.get(key);
		} else {
			return null;
		}
	}
}
