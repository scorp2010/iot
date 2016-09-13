/**
 * Project Name:cdag
 * File Name:DeviceLoginRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-4-14下午3:00:10
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.Coder;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * ClassName:VideoConnEstablishRsp <br/>
 * Function: 视频连接响应消息0x0071. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-14 下午3:00:10 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class VideoHeaderReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;
	
	private StringPdu securityCode;

	private ShortPdu cameraSerial;
	
	private byte[] videoDataHeader;

	public StringPdu getSecurityCode() {
		return securityCode;
	}


	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}
	
	public Short getCameraSerial() {
		return cameraSerial.getValue();
	}


	public void setCameraSerial(Short cameraSerial) {
		this.cameraSerial = new ShortPdu(cameraSerial);
	}
	
	
	public byte[] getVideoDataHeader() {
		return videoDataHeader;
	}


	public void setVideoDataHeader(byte[] videoDataHeader) {
		this.videoDataHeader = videoDataHeader;
	}

	public VideoHeaderReq() {
		this.header.setMsgType(Commands.VIDEO_HEADER_REQ);
	}

	public VideoHeaderReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {

		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, cameraSerial.getBytes(), index);
		index += cameraSerial.getLength();
		ByteOps.addByteArray(data, videoDataHeader, index);
		index += videoDataHeader.length;
	}

	@Override
	protected void parseBody() throws MessageParseException {

		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		cameraSerial = new ShortPdu(index, data);
		index += cameraSerial.getLength();
		videoDataHeader = ByteOps.cpByteArray(data, index, data.length - index);
		index += videoDataHeader.length;
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER
				+ SECURITY_LENGTH
				+ cameraSerial.getLength() + videoDataHeader.length;
	}

	@Override
	public String toString() {
		try {
			return "ReportVideoHeaderReq [securityCode=" + securityCode
					+ ", cameraSerial=" + cameraSerial + ", videoDataHeader="
					+ ByteOps.bytes2HexStringWithBlank(videoDataHeader) + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ReportVideoHeaderReq [securityCode=" + securityCode
				+ ", cameraSerial=" + cameraSerial + "]";
	}

	public static void main(String[] args) throws Exception {
		
		VideoHeaderReq rsp = new VideoHeaderReq();
		rsp.setSecurityCode(new String(RandomCode.generateCode16()));
		rsp.setCameraSerial((short) 1);
		byte[] videoDataHeader = Coder.decryptBASE64("Z0IAHqaBYJZAaM44gA==");
		rsp.setVideoDataHeader(videoDataHeader);
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		System.out.println(rsp.toString());
	}

}
