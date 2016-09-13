/**
 * Project Name:cdag
 * File Name:DeviceLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-4-10下午5:16:44
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * ClassName:DeviceLoginReq <br/>
 * Function: 采集设备登陆请求消息. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-10 下午5:16:44 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceLoginReq extends AbstractReqPackage {

	protected static final int DEVICE_REG_CODE_LENGTH = 28;

	protected static final int ENCRY_PASSWORD_LENGTH = 16;

	protected static final int SECURITY_CODE_LENGTH = 6;

	/** 设备注册码. */
	private StringPdu deviceRegCode = new StringPdu();

	/** 加密后的设备登陆密码. */
	private StringPdu encryPassword = new StringPdu();

	/** 登陆安全码. */
	private StringPdu securityCode = new StringPdu();

	public StringPdu getDeviceRegCode() {
		return deviceRegCode;
	}

	public void setDeviceRegCode(StringPdu deviceRegCode) {
		this.deviceRegCode = deviceRegCode;
	}

	public StringPdu getEncryPassword() {
		return encryPassword;
	}

	public void setEncryPassword(StringPdu encryPassword) {
		this.encryPassword = encryPassword;
	}

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(StringPdu securityCode) {
		this.securityCode = securityCode;
	}

	public DeviceLoginReq() {
		this.header.setMsgType(Commands.LOGIN_REQ);
	}

	public DeviceLoginReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, deviceRegCode.getBytes(), index);
		index += deviceRegCode.getLength();
		ByteOps.addByteArray(data, encryPassword.getBytes(), index);
		index += encryPassword.getLength();
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {

		deviceRegCode = new StringPdu(DEVICE_REG_CODE_LENGTH, index, data);
		index += deviceRegCode.getLength();
		encryPassword = new StringPdu(ENCRY_PASSWORD_LENGTH, index, data);
		index += encryPassword.getLength();
		securityCode = new StringPdu(SECURITY_CODE_LENGTH, index, data);
		index += securityCode.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + +this.deviceRegCode.getLength()
				+ this.encryPassword.getLength()
				+ this.securityCode.getLength();
	}

	@Override
	public String toString() {
		return "DeviceLoginReq [deviceRegCode=" + deviceRegCode
				+ ", encryPassword=" + encryPassword + ", securityCode="
				+ securityCode + "]";
	}

	public static void main(String[] args) {
		DeviceLoginReq req = new DeviceLoginReq();
		StringPdu deviceRegCode = new StringPdu(28, "010270184D800171");
		req.setDeviceRegCode(deviceRegCode);
		StringPdu encryPassword = new StringPdu(16, "30fb0943375125cb");
		req.setEncryPassword(encryPassword);
		String strSecurityCode = new String(RandomCode.generateCode8())
				.substring(0, 6);
		StringPdu securityCode = new StringPdu(strSecurityCode);
		req.setSecurityCode(securityCode);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		DeviceLoginReq req1 = new DeviceLoginReq(req.getData());
		try {
			System.out.println(req.getData().length);
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
