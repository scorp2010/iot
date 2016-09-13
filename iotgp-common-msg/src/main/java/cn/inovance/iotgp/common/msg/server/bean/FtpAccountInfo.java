/**
 * Project Name:iotgp-common-msg
 * File Name:FtpAccountInfo.java
 * Package Name:cn.inovance.iotgp.common.msg.server.bean
 * Date:2014-5-27上午10:54:50
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server.bean;

/**
 * ClassName:FtpAccountInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-27 上午10:54:50 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class FtpAccountInfo {

	private String account;

	private String password;

	public FtpAccountInfo() {

	}

	public FtpAccountInfo(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
