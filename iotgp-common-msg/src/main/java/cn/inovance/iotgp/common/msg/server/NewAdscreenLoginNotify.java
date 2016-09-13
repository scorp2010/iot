/**
 * Project Name:iotgp-common-msg
 * File Name:AddNewGdhsInfoNotify.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-5-4下午4:13:26
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;


/**
 * ClassName:AddNewGdhsInfoNotify <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-4 下午4:13:26 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class NewAdscreenLoginNotify extends Request {

	public NewAdscreenLoginNotify() {
		this.msgType = MsgType.NEW_ADSCREEN_LOGIN_NOTIFY;
	}

	private String deviceRegCode;
	
	public String getDeviceRegCode() {
		return deviceRegCode;
	}

	public void setDeviceRegCode(String deviceRegCode) {
		this.deviceRegCode = deviceRegCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceRegCode == null) ? 0 : deviceRegCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewAdscreenLoginNotify other = (NewAdscreenLoginNotify) obj;
		if (deviceRegCode == null) {
			if (other.deviceRegCode != null)
				return false;
		} else if (!deviceRegCode.equals(other.deviceRegCode))
			return false;
		return true;
	}
	
	
}
