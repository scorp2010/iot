/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceRunStatusReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-29下午7:27:14
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * ClassName:VideoStreamingData <br/>
 * Function: 视频流消息0x0072. <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class VideoStreamData extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;
	/**
	 * 每帧包个数.
	 */
	private IntPdu packNumPerFrame = new IntPdu();
	/**
	 * 帧的包序号 .
	 */
	private IntPdu packNo = new IntPdu();
	/**
	 * 秒时间戳.
	 */
	private LongPdu secondTimeStamp = new LongPdu();
	/** 微秒时间戳 . */
	private LongPdu milSecondTimeStamp = new LongPdu();
	/** 视频流 . */
	private byte[] streamData;

	public VideoStreamData() {
		this.header.setMsgType(Commands.VIDEO_STREAM_DATA);
	}

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public VideoStreamData(byte[] data) {
		super(data);
	}

	public IntPdu getPackNumPerFrame() {
		return packNumPerFrame;
	}

	public void setPackNumPerFrame(IntPdu packNumPerFrame) {
		this.packNumPerFrame = packNumPerFrame;
	}

	public IntPdu getPackNo() {
		return packNo;
	}

	public void setPackNo(IntPdu packNo) {
		this.packNo = packNo;
	}

	public LongPdu getSecondTimeStamp() {
		return secondTimeStamp;
	}

	public void setSecondTimeStamp(LongPdu secondTimeStamp) {
		this.secondTimeStamp = secondTimeStamp;
	}

	public LongPdu getMilSecondTimeStamp() {
		return this.milSecondTimeStamp;
	}

	public void setMilSecondTimeStamp(LongPdu milSecondTimeStamp) {
		this.milSecondTimeStamp = milSecondTimeStamp;
	}

	public byte[] getStreamData() {
		return streamData;
	}

	public void setStreamData(byte[] streamingData) {
		this.streamData = streamingData;
	}

	public void setSecurityCode(StringPdu securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, packNumPerFrame.getBytes(), index);
		index += packNumPerFrame.getLength();
		ByteOps.addByteArray(data, packNo.getBytes(), index);
		index += packNo.getLength();
		ByteOps.addByteArray(data, secondTimeStamp.getBytes(), index);
		index += secondTimeStamp.getLength();
		ByteOps.addByteArray(data, milSecondTimeStamp.getBytes(), index);
		index += milSecondTimeStamp.getLength();
		if (index < data.length) {
			ByteOps.addByteArray(data, streamData, index);
			index += streamData.length;
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		packNumPerFrame = new IntPdu(index, data);
		index += packNumPerFrame.getLength();
		packNo = new IntPdu(index, data);
		index += packNo.getLength();
		secondTimeStamp = new LongPdu(index, data);
		index += secondTimeStamp.getLength();
		milSecondTimeStamp = new LongPdu(index, data);
		index += milSecondTimeStamp.getLength();
		if (index < data.length) {
			streamData = ByteOps.cpByteArray(data, index, data.length - index);
			index += streamData.length;
		}

	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + SECURITY_LENGTH
				+ packNumPerFrame.getLength() + this.packNo.getLength()
				+ this.secondTimeStamp.getLength()
				+ this.milSecondTimeStamp.getLength()
				+ (streamData != null ? streamData.length : 0);
	}

	@Override
	public String toString() {
		return "VideoStreamingData [securityCode=" + securityCode
				+ ", packNumPerFrame=" + packNumPerFrame + ", packNo=" + packNo
				+ ", secondTimeStamp=" + secondTimeStamp
				+ ", milSecondTimeStamp=" + milSecondTimeStamp
				+ ", streamData=" + Arrays.toString(streamData) + "]";
	}

	public static void main(String[] args) {

		VideoStreamData req = new VideoStreamData();
		req.setSecurityCode(new String(RandomCode.generateCode16()));
		req.setPackNumPerFrame(new IntPdu(4));
		req.setPackNo(new IntPdu(4));
		req.setSecondTimeStamp(new LongPdu(System.currentTimeMillis()));
		req.setMilSecondTimeStamp(new LongPdu(System.currentTimeMillis()));
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		VideoStreamData req1 = new VideoStreamData(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
