/**
 * Project Name:cdag
 * File Name:ServerLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:31:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.server.bean.ServerMsgAddress;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

/**
 * ClassName:ServerLoginReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:31:45 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ServerLoginReq extends Request {

	private String account = "";

	private String password = "";

	private String externalServerHost = "";

	private Integer externalServerPort = 0;

	private String externalServerHostType = "";

	private String internalServerHost = "";

	private Integer internalServerPort = 0;

	private String internalServerHostType = "";

	public ServerLoginReq() {
		this.msgType = MsgType.SERVER_LOGIN_REQ;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExternalServerHost() {
		return externalServerHost;
	}

	public void setExternalServerHost(String externalServerHost) {
		this.externalServerHost = externalServerHost;
	}

	public Integer getExternalServerPort() {
		return externalServerPort;
	}

	public void setExternalServerPort(Integer externalServerPort) {
		this.externalServerPort = externalServerPort;
	}

	public String getExternalServerHostType() {
		return externalServerHostType;
	}

	public void setExternalServerHostType(String externalServerHostType) {
		this.externalServerHostType = externalServerHostType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getInternalServerHost() {
		return internalServerHost;
	}

	public void setInternalServerHost(String internalServerHost) {
		this.internalServerHost = internalServerHost;
	}

	public Integer getInternalServerPort() {
		return internalServerPort;
	}

	public void setInternalServerPort(Integer internalServerPort) {
		this.internalServerPort = internalServerPort;
	}

	public String getInternalServerHostType() {
		return internalServerHostType;
	}

	public void setInternalServerHostType(String internalServerHostType) {
		this.internalServerHostType = internalServerHostType;
	}

	public static void main(String[] args) {
		ServerLoginReq req = new ServerLoginReq();
		req.setTo((new ServerMsgAddress("gdhs")).toString());
		System.out.println(req.toString());
		ServerLoginReq req1 = JsonBinder.normalBinder.fromJson(req.toString(),
				ServerLoginReq.class);
		System.out.println(req1.toString());
	}
}
