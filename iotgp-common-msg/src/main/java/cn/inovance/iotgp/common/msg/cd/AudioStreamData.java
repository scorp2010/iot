package cn.inovance.iotgp.common.msg.cd;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class AudioStreamData extends AbstractStreamDataPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;
	/** 包序号 */
	private IntPdu packNo = new IntPdu();
	/** 秒时间戳 */
	private LongPdu secondTimeStamp = new LongPdu();
	/** 微秒时间戳 */
	private LongPdu milSecondTimeStamp = new LongPdu();
	/** 音频流 */
	private byte[] streamData;

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
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

	public void setStreamData(byte[] streamData) {
		this.streamData = streamData;
	}

	public void setSecurityCode(StringPdu securityCode) {
		this.securityCode = securityCode;
	}

	public AudioStreamData() {
		this.header.setMsgType(Commands.AUDIO_STREAM_DATA);
	}

	public AudioStreamData(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
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
		return Header.LENGTH_HEADER + SECURITY_LENGTH + this.packNo.getLength()
				+ this.secondTimeStamp.getLength()
				+ this.milSecondTimeStamp.getLength()
				+ (streamData != null ? streamData.length : 0);
	}

	@Override
	public String toString() {
		return "AudioStreamData [securityCode=" + securityCode + ", packNo="
				+ packNo + ", secondTimeStamp=" + secondTimeStamp
				+ ", milSecondTimeStamp=" + milSecondTimeStamp
				+ ", streamData=" + Arrays.toString(streamData) + "]";
	}
}
