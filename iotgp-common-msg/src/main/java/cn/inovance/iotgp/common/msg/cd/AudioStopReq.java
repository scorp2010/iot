package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class AudioStopReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public AudioStopReq() {
		this.header.setMsgType(Commands.AUDIO_STOP_REQ);
	}

	public AudioStopReq(byte[] data) {
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

		return Header.LENGTH_HEADER + SECURITY_LENGTH;
	}

	@Override
	public String toString() {
		return "VideoConnDestroyReq [securityCode=" + securityCode + "]";
	}

}
