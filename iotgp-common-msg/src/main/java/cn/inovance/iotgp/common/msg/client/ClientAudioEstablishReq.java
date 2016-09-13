package cn.inovance.iotgp.common.msg.client;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 音频客户端连接请求消息
 * 
 * @author c2100
 */
public class ClientAudioEstablishReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public ClientAudioEstablishReq() {
		this.header.setMsgType(Commands.CLIENT_AUDIO_ESTABLISH_REQ);
	}

	public ClientAudioEstablishReq(byte[] data) {
		super(data);
	}

	public String getSecurityCode() {
		return securityCode.getValue();
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
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
		return "VideoConnEstablishReq [securityCode=" + securityCode + "]";
	}

}
