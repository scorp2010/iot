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
public class SetVideoPlayProgressReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;
	
	private StringPdu securityCode;

	private ShortPdu playProgressHour = new ShortPdu();
	
	private ShortPdu playProgressMin = new ShortPdu();
	
	private ShortPdu playProgressSecond = new ShortPdu();
	
	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}
	
	public Short getPlayProgressHour() {
		return playProgressHour.getValue();
	}


	public void setPlayProgressHour(Short playProgressHour) {
		this.playProgressHour = new ShortPdu(playProgressHour);
	}


	public Short getPlayProgressMin() {
		return playProgressMin.getValue();
	}

	public void setPlayProgressMin(Short playProgressMin) {
		this.playProgressMin = new ShortPdu(playProgressMin);
	}


	public ShortPdu getPlayProgressSecond() {
		return playProgressSecond;
	}

	public void setPlayProgressSecond(Short playProgressSecond) {
		this.playProgressSecond = new ShortPdu(playProgressSecond);
	}

	public SetVideoPlayProgressReq() {
		this.header.setMsgType(Commands.SET_VIDEO_PLAY_PROGRESS_REQ);
	}

	public SetVideoPlayProgressReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {

		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, playProgressHour.getBytes(), index);
		index += playProgressHour.getLength();
		ByteOps.addByteArray(data, playProgressMin.getBytes(), index);
		index += playProgressMin.getLength();
		ByteOps.addByteArray(data, playProgressSecond.getBytes(), index);
		index += playProgressSecond.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {

		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		playProgressHour = new ShortPdu(index, data);
		index += playProgressHour.getLength();
		playProgressMin = new ShortPdu(index, data);
		index += playProgressMin.getLength();
		playProgressSecond = new ShortPdu(index, data);
		index += playProgressSecond.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER
				+ SECURITY_LENGTH
				+ playProgressHour.getLength()
				+ playProgressMin.getLength()
				+ playProgressSecond.getLength();
	}

	@Override
	public String toString() {
		return "SetVideoPlayProgressReq [securityCode=" + securityCode
				+ ", playProgressHour=" + playProgressHour
				+ ", playProgressMin=" + playProgressMin
				+ ", playProgressSecond=" + playProgressSecond + "]";
	}

	public static void main(String[] args) throws Exception {
		SetVideoPlayProgressReq rsp = new SetVideoPlayProgressReq();
		rsp.setSecurityCode(new String(RandomCode.generateCode16()));
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		System.out.println(rsp.toString());
	}

}
