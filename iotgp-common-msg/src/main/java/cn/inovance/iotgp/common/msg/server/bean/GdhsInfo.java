/**
 * Project Name:iotgp-common-msg
 * File Name:GdhsInfo.java
 * Package Name:cn.inovance.iotgp.common.msg.server.bean
 * Date:2014-5-4下午5:49:34
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server.bean;

/**
 * ClassName:GdhsInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-4 下午5:49:34 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class GdhsInfo {

	private String sysAccount = "";

	private String host = "";

	private int port = 0;

	public GdhsInfo(String sysAccount, String host, int port) {
		this.sysAccount = sysAccount;
		this.host = host;
		this.port = port;
	}

	public GdhsInfo() {

	}

	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
