/**
 * Project Name:cdag
 * File Name:ServerConnectionInfoPdu.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-4-15上午9:22:54
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd.meta;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:ServerConnectionInfoPdu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 上午9:22:54 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ServerConnectionInfoPdu extends PDU {

	/**
	 * 服务器ip类型（ipv4、ipv6）
	 */
	private ShortPdu hostType = new ShortPdu();

	public ShortPdu getHostType() {
		return hostType;
	}

	public void setHostType(ShortPdu hostType) {
		this.hostType = hostType;
	}

	@Override
	public String toString() {
		return "ServerConnectionInfoPdu [hostType=" + hostType + ", host="
				+ Arrays.toString(host) + ", port=" + port + "]";
	}

	/**
	 * 服务器IP地址
	 */
	private ShortPdu[] host = new ShortPdu[16];
	/***
	 * 服务器连接端口
	 */
	private IntPdu port = new IntPdu(0);

	public ShortPdu[] getHost() {
		return host;
	}

	public void setHost(ShortPdu[] host) {
		this.host = host;
	}

	public IntPdu getPort() {
		return port;
	}

	public void setPort(IntPdu port) {
		this.port = port;
	}

	private ServerConnectionInfo serverConnectionInfo = new ServerConnectionInfo();

	@Override
	public ServerConnectionInfo getValue() {

		return serverConnectionInfo;
	}

	public ServerConnectionInfoPdu(ServerConnectionInfo serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
		hostType = new ShortPdu((short) serverConnectionInfo.getHostLength());
		for (int i = 0; i < serverConnectionInfo.getHostLength(); i++) {
			host[i] = new ShortPdu((short) serverConnectionInfo.getHost()[i]);
		}
		port = new IntPdu(serverConnectionInfo.getPort());
		this.dataLenth = getLength();
	}
	
	public ServerConnectionInfoPdu(String strHost, int port) {
		int[] cdagHost = null;
		String[] strCdagHostArray = strHost.split("\\.");
		cdagHost = new int[strCdagHostArray.length];
		for (int i = 0; i < strCdagHostArray.length; i++) {
			cdagHost[i] = Integer.parseInt(strCdagHostArray[i]);
		}
		this.serverConnectionInfo = new ServerConnectionInfo(cdagHost, port);
		this.hostType = new ShortPdu(
				(short) serverConnectionInfo.getHostLength());
		for (int i = 0; i < serverConnectionInfo.getHostLength(); i++) {
			this.host[i] = new ShortPdu(
					(short) serverConnectionInfo.getHost()[i]);
		}
		this.port = new IntPdu(serverConnectionInfo.getPort());
		this.dataLenth = getLength();
	}

	public ServerConnectionInfoPdu(int[] host, int port) {
		this.serverConnectionInfo = new ServerConnectionInfo(host, port);
		this.hostType = new ShortPdu(
				(short) serverConnectionInfo.getHostLength());
		for (int i = 0; i < serverConnectionInfo.getHostLength(); i++) {
			this.host[i] = new ShortPdu(
					(short) serverConnectionInfo.getHost()[i]);
		}
		this.port = new IntPdu(serverConnectionInfo.getPort());
		this.dataLenth = getLength();
	}

	public ServerConnectionInfoPdu(String[] host, int port) {
		this.serverConnectionInfo = new ServerConnectionInfo(host, port);
		this.hostType = new ShortPdu(
				(short) serverConnectionInfo.getHostLength());
		for (int i = 0; i < serverConnectionInfo.getHostLength(); i++) {
			this.host[i] = new ShortPdu(
					(short) serverConnectionInfo.getHost()[i]);
		}
		this.port = new IntPdu(serverConnectionInfo.getPort());
		this.dataLenth = getLength();
	}

	public ServerConnectionInfoPdu(int hostType, String host, int port) {
		String[] strHostArray = null;
		if (hostType == ServerConnectionInfo.IPV4_LENGTH) {
			strHostArray = host.split("\\.");
		} else {
			strHostArray = host.split(":");
		}
		this.serverConnectionInfo = new ServerConnectionInfo(strHostArray, port);
		this.hostType = new ShortPdu((short) hostType);
		for (int i = 0; i < serverConnectionInfo.getHostLength(); i++) {
			this.host[i] = new ShortPdu(
					(short) serverConnectionInfo.getHost()[i]);
		}
		this.port = new IntPdu(serverConnectionInfo.getPort());
		this.dataLenth = getLength();
	}

	public ServerConnectionInfoPdu(int startIndex, byte[] data)
			throws MessageParseException {
		dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			hostType = new ShortPdu(startIndex, data);
			startIndex += hostType.getLength();
			for (int i = 0; i < hostType.getValue(); i++) {
				host[i] = new ShortPdu(startIndex, data);
				startIndex += host[i].getLength();
			}
			port = new IntPdu(startIndex, data);
			startIndex += port.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public int getLength() {

		return hostType.getLength() + hostType.getValue() + port.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, hostType.getBytes(), index);
		index += hostType.getLength();
		for (int i = 0; i < hostType.getValue(); i++) {
			ByteOps.addByteArray(bytes, host[i].getBytes(), index);
			index += host[i].getLength();
		}
		ByteOps.addByteArray(bytes, port.getBytes(), index);
		index += port.getLength();
		return bytes;
	}

	public static void main(String[] args) {
		ServerConnectionInfo serverConnectionInfo = new ServerConnectionInfo();
		int[] host = { 192, 168, 60, 47 };
		serverConnectionInfo.setHost(host);
		serverConnectionInfo.setHostLength(4);
		serverConnectionInfo.setPort(6397);
		ServerConnectionInfoPdu pdu = new ServerConnectionInfoPdu(4,
				"192.168.60.47", 6397);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			ServerConnectionInfoPdu pdu1 = new ServerConnectionInfoPdu(0,
					pdu.getBytes());
			System.out
					.println(ByteOps.bytes2HexStringWithBlank(pdu1.getBytes()));
		} catch (MessageParseException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
