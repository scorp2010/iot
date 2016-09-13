/**
 * Project Name:iotgp-common
 * File Name:ServerParameter.java
 * Package Name:cn.inovance.iotgp.common.msg.server.bean
 * Date:2014-4-18下午2:22:29
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server.bean;

/**
 * ClassName:ServerParameter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-18 下午2:22:29 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ServerParameter {

	private String key;

	private String value;

	public ServerParameter() {

	}

	public ServerParameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
