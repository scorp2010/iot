/**
 * Project Name:cdag
 * File Name:ServerMsgAddress.java
 * Package Name:cn.inovance.iotgp.common.msg.server.bean
 * Date:2014-4-14上午9:33:52
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server.bean;

import cn.inovance.iotgp.common.enums.ServerType;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

/**
 * ClassName:ServerMsgAddress <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-14 上午9:33:52 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ServerMsgAddress {

	public static final String ADDRESS_FIRST_DELIMITER = "_";

	public static final String ADDRESS_SECOND_DELIMITER = "@";

	private String serial = "";

	private String domain = "";

	public ServerMsgAddress() {
	}

	public ServerMsgAddress(String serial) {
		this.serial = serial;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static ServerMsgAddress parse(String strServerMsgAddress)
			throws MessageParseException {
		ServerMsgAddress serverMsgAddress = new ServerMsgAddress();
		try {
			int firstDelimiterIndex = strServerMsgAddress
					.indexOf(ADDRESS_SECOND_DELIMITER);
			serverMsgAddress.setSerial(strServerMsgAddress.substring(0,
					firstDelimiterIndex));
			serverMsgAddress.setDomain(strServerMsgAddress.substring(
					firstDelimiterIndex + 1, strServerMsgAddress.length()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
		return serverMsgAddress;
	}
	
	@Override
	public String toString() {
		return serial + ADDRESS_SECOND_DELIMITER + domain;
	}

	public static ServerType getServerType(String sysAccount) {
		return ServerType.valueOfString(sysAccount.split(ADDRESS_FIRST_DELIMITER)[0]);
	}
	
	public static String getDomain(String sysAccount) {
		return sysAccount.split(ADDRESS_SECOND_DELIMITER)[1];
	}
	
	public static void main(String[] args) throws MessageParseException {
		String strServerAddress = "cdag_001@main";
		ServerMsgAddress newServerMsgAddress = ServerMsgAddress
				.parse(strServerAddress);
		System.out.println(newServerMsgAddress.toString());
		System.out.println(newServerMsgAddress.getSerial());
	}

	
}
