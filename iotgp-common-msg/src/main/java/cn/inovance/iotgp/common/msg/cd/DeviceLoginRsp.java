/**
 * Project Name:cdag
 * File Name:DeviceLoginRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-4-14下午3:00:10
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.Date;

import cn.inovance.iotgp.common.msg.cd.meta.DatatimePdu;
import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:DeviceLoginRsp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-14 下午3:00:10 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceLoginRsp extends AbstractRspPackage {

	public static final int ERROR_REG_CODE_INVALID = 0X01;

	public static final int ERROR_SERVER_RELOCATE = 0X02;

	public static final int ERROR_DEVICE_ALREADY_LOGIN = 0X03;

	public static final int ERROR_PASSWORD_INVALID = 0X04;

	public static final short SERVER_HOST_IPV4 = 4;

	public static final short SERVER_HOST_IPV6 = 6;
	/**
	 * 服务器当前时间（CBD）
	 */
	private DatatimePdu serverTime = new DatatimePdu(new Date());

	/**
	 * 重定向服务器信息
	 */
	private ServerConnectionInfoPdu serverConnectionInfo;

	public DatatimePdu getServerTime() {
		return serverTime;
	}

	public void setServerTime(DatatimePdu serverTime) {
		this.serverTime = serverTime;
	}

	public ServerConnectionInfoPdu getServerConnectionInfo() {
		return serverConnectionInfo;
	}

	public void setServerConnectionInfo(
			ServerConnectionInfoPdu serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
	}

	public DeviceLoginRsp() {
		this.header.setMsgType(Commands.LOGIN_RSP);
	}

	public DeviceLoginRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {

		ByteOps.addByteArray(data, serverTime.getBytes(), index);
		index += serverTime.getLength();
		if (this.getErrorCode() == ERROR_SERVER_RELOCATE) {
			ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
			index += serverConnectionInfo.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {

		serverTime = new DatatimePdu(index, data);
		index += serverTime.getLength();
		if (this.getErrorCode() == ERROR_SERVER_RELOCATE) {
			serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		}
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER
				+ errorCode.getLength()
				+ serverTime.getLength()
				+ ((this.getErrorCode() == ERROR_SERVER_RELOCATE) ? serverConnectionInfo
						.getLength() : 0);
	}

	@Override
	public String toString() {
		return "DeviceLoginRsp [serverTime=" + serverTime
				+ ", serverConnectionInfo=" + serverConnectionInfo
				+ ", errorCode=" + errorCode + ", header=" + header + "]";
	}

	public static void main(String[] args) {
		DeviceLoginRsp rsp = new DeviceLoginRsp();
		rsp.setErrorCode(ERROR_PASSWORD_INVALID);
		DatatimePdu serverTime = new DatatimePdu(new Date());
		rsp.setServerTime(serverTime);
		int[] host = { 192, 168, 60, 47 };
		ServerConnectionInfoPdu pdu = new ServerConnectionInfoPdu(host, 6397);
		rsp.setServerConnectionInfo(pdu);
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		System.out.println(rsp.toString());
	}

}
