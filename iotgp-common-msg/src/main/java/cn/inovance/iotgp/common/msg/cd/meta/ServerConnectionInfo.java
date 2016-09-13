/**
 * Project Name:cdag
 * File Name:ServerConnectionInfo.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-4-15下午4:53:10
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd.meta;

/**
 * ClassName:ServerConnectionInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 下午4:53:10 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ServerConnectionInfo {

	public static int IPV4_LENGTH = 4;

	public static int IPV6_LENGTH = 16;

	private int hostLength = 4;

	private int[] host;

	private int port;

	public ServerConnectionInfo() {

	}

	public ServerConnectionInfo(int[] host, int port) {
		this.hostLength = host.length;
		this.host = host;
		this.port = port;
	}

	public ServerConnectionInfo(String[] host, int port) {
		this.hostLength = host.length;
		this.host = new int[host.length];
		for (int i = 0; i < host.length; i++) {
			this.host[i] = Integer.valueOf(host[i]);
		}
		this.port = port;
	}

	public int getHostLength() {
		return hostLength;
	}

	public void setHostLength(int hostLength) {
		this.hostLength = hostLength;
	}

	public int[] getHost() {
		return host;
	}

	public void setHost(int[] host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
