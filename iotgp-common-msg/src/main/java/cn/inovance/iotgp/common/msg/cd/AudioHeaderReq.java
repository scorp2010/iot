package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class AudioHeaderReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	/** 安全码 */
	private StringPdu securityCode;
	/** 通道 1：单声道 2：立体音 */
	private ShortPdu channel = new ShortPdu();
	/** 采样配置信息 bit7-bit4:采用频率 bit3-bit0:采样位数 */
	private ShortPdu sampling = new ShortPdu();

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(securityCode);
	}

	public ShortPdu getChannel() {
		return channel;
	}

	public void setChannel(short channel) {
		this.channel = new ShortPdu(channel);
	}

	public ShortPdu getSampling() {
		return sampling;
	}

	public void setSampling(short sampling) {
		this.sampling = new ShortPdu(sampling);
	}

	public AudioHeaderReq() {
		this.header.setMsgType(Commands.AUDIO_HEADER_REQ);
	}

	public AudioHeaderReq(byte[] data) {
		super(data);
	}
	
	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, channel.getBytes(), index);
		index += channel.getLength();
		ByteOps.addByteArray(data, sampling.getBytes(), index);
		index += sampling.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		channel = new ShortPdu(index, data);
		index += channel.getLength();
		sampling = new ShortPdu(index, data);
		index += sampling.getLength();
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + SECURITY_LENGTH + channel.getLength()
				+ this.sampling.getLength();
	}

	@Override
	public String toString() {
		return "AudioEstablishReq [securityCode=" + securityCode + ", channel="
				+ channel + ", sampling=" + sampling + "]";
	}

}
