package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class AudioHeaderRsp extends AbstractRspPackage {
	
	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public AudioHeaderRsp() {
		this.header.setMsgType(Commands.AUDIO_HEADER_RSP);
	}

	public AudioHeaderRsp(byte[] data) {
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
		return "AudioHeaderRsp [securityCode=" + securityCode
				+ ", errorCode=" + errorCode + "]";
	}
}
