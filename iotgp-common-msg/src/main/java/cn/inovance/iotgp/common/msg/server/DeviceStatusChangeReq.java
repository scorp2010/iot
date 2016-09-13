/**
 * Project Name:cdag
 * File Name:ServerLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:31:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.enums.DeviceStatusType;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

/**
 * ClassName:DeviceStatusChangeReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:31:45 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceStatusChangeReq extends Request {

	private String deviceSerial = "";

	private short status = DeviceStatusType.OffLine.value();

	
	private String loginIpPort="";
	
	
	public DeviceStatusChangeReq() {
		this.msgType = MsgType.DEVICE_STATUS_CHANGE_REQ;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getLoginIpPort() {
		return loginIpPort;
	}

	public void setLoginIpPort(String loginIpPort) {
		this.loginIpPort = loginIpPort;
	}

	public static void main(String[] args) {
		DeviceStatusChangeReq req = new DeviceStatusChangeReq();
		req.setFrom("123");
		req.setTo("gdhs");
		System.out.println(req.toString());
		DeviceStatusChangeReq req1 = JsonBinder.normalBinder.fromJson(
				req.toString(), DeviceStatusChangeReq.class);
		System.out.println(req1.toString());
	}
}
