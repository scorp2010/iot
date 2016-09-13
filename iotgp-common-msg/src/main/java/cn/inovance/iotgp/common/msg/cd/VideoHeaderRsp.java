/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceCmdChanelRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-3下午1:53:46
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:ReportVideoHeaderRsp. 
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */

public class VideoHeaderRsp extends AbstractRspPackage {
	
	public static final int SERVER_CONN_ERROR = 0X01;

	public static final int ERROR_TD_SERIAL_NOT_EXIST = 0X02;
	
	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;
	
	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public VideoHeaderRsp() {
		this.header.setMsgType(Commands.VIDEO_HEADER_RSP);
	}

	public VideoHeaderRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();

	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + errorCode.getLength() + SECURITY_LENGTH;
	}

	

	@Override
	public String toString() {
		return "VideoHeaderRsp [securityCode=" + securityCode
				+ ", errorCode=" + errorCode + "]";
	}

	public static void main(String[] args) {

		VideoHeaderRsp rsp = new VideoHeaderRsp();
		rsp.setErrorCode((short) 1);
		rsp.setSecurityCode("ibjb8964");
		rsp.construct();
		System.out.println(ByteOps.bytesToHexString(rsp.getData()));
		VideoHeaderRsp rsp1 = new VideoHeaderRsp(rsp.getData());
		System.out.println(rsp.getData().length);
		try {
			rsp1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(rsp1.toString());
	}

}
