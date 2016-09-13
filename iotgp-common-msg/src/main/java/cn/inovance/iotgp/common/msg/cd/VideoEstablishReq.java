/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceRunStatusReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-29下午7:27:14
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * ClassName:VideoConnEstablishReq <br/>
 * Function: 视频连接请求消息0x0070. <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class VideoEstablishReq extends AbstractReqPackage {
	
	public static final int TRANFER_CONFIG_CONN_TYPE_LENGTH = 1;
	public static final int TRANFER_CONFIG_VIEW_TYPE_LENGTH = 1;
	public static final int TRANFER_CONFIG_RESOLUTION_LENGTH = 3;
	public static final int TRANFER_CONFIG_RESERVE_LENGTH = 3;
	
	private static final int SECURITY_LENGTH = 16;
	
	private StringPdu securityCode;
	/**
	 * 摄像头编号（点播：编号为0xff；直播：0-16）.
	 */
	private ShortPdu cameraSerial = new ShortPdu();
	/**
	 * Bit0 ：通信方式 0 TCP，1 UDP Bit1：视频类型 0 直播，1 点播 Bit4-Bit2：视频分辨率 Bit7-Bit5：保留 .
	 */
	private ShortPdu tranferConfig = new ShortPdu();
	/**
	 * MDDS服务器信息 .
	 */
	private ServerConnectionInfoPdu serverConnectionInfo = new ServerConnectionInfoPdu("0.0.0.0",0);
	/** 用于点播 ，可选 .*/
	private ShortPdu fileNameLength = new ShortPdu();
	/** 用于点播 ，可选 .*/
	private StringPdu fileName = new StringPdu("");

	public VideoEstablishReq() {
		this.header.setMsgType(Commands.VIDEO_ESTABLISH_REQ);
	}

	
	public String getSecurityCode() {
		return securityCode.getValue();
	}


	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}


	public VideoEstablishReq(byte[] data) {
		super(data);
	}


	public ServerConnectionInfoPdu getServerConnectionInfo() {
		return serverConnectionInfo;
	}

	public void setServerConnectionInfo(
			ServerConnectionInfoPdu serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
	}
	
	public Short getCameraSerial() {
		return cameraSerial.getValue();
	}


	public void setCameraSerial(Short cameraSerial) {
		this.cameraSerial = new ShortPdu(cameraSerial);
	}


	public Short getTranferConfig() {
		return tranferConfig.getValue();
	}


	public void setTranferConfig(Short tranferConfig) {
		this.tranferConfig = new ShortPdu(tranferConfig);
	}


	public StringPdu getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = new StringPdu(fileName);
	}


	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, cameraSerial.getBytes(), index);
		index += cameraSerial.getLength();
		ByteOps.addByteArray(data, tranferConfig.getBytes(), index);
		index += tranferConfig.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
		String strTransferConfig = Integer.toBinaryString(tranferConfig.getValue());
		if (tranferConfig.getValue() > 0 && strTransferConfig.charAt(1) == '1'){// 判断是否为点播
			fileNameLength = new ShortPdu((short)fileName.getLength());
			ByteOps.addByteArray(data, fileNameLength.getBytes(), index);
			index += fileNameLength.getLength();
			ByteOps.addByteArray(data, fileName.getBytes(), index);
			index += fileName.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		cameraSerial = new ShortPdu(index, data);
		index += cameraSerial.getLength();
		tranferConfig = new ShortPdu(index, data);
		index += tranferConfig.getLength();
		serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		index += serverConnectionInfo.getLength();
		if (index < data.length) {
			ByteOps.addByteArray(data, fileNameLength.getBytes(), index);
			index += fileNameLength.getLength();
			fileName = new StringPdu(fileNameLength.getValue(), index, data);
			index += fileName.getLength();
		}

	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + SECURITY_LENGTH + cameraSerial.getLength()
				+ this.tranferConfig.getLength()
				+ this.serverConnectionInfo.getLength() + (fileName.getLength() > 0 ? fileName.getLength() + 1 : 0);
	}

	


	@Override
	public String toString() {
		return "VideoConnEstablishReq [securityCode=" + securityCode
				+ ", cameraSerial=" + cameraSerial + ", tranferConfig="
				+ tranferConfig + ", serverConnectionInfo="
				+ serverConnectionInfo
				+ ", fileName=" + fileName + "]";
	}


	public static void main(String[] args) {

		VideoEstablishReq req = new VideoEstablishReq();
		req.setSecurityCode(new String(RandomCode.generateCode16()));
		req.setCameraSerial((short) 1);
		final int videoTranferConfig = 0;
		req.setTranferConfig((short) videoTranferConfig);
		int[] host = { 192, 168, 60, 47 };
		ServerConnectionInfoPdu pdu = new ServerConnectionInfoPdu(host, 6397);
		req.setServerConnectionInfo(pdu);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		String test1 = "002000700100003743394C4E37434432434932484E5944010004C0A83C2F18FD";
		VideoEstablishReq req1 = new VideoEstablishReq(ByteOps.hexStringToBytes(test1));
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
