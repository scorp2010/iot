package cn.inovance.iotgp.common.msg.client;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class ClientHeartbeatReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public ClientHeartbeatReq() {
		this.header.setMsgType(Commands.CLIENT_HEARTBEAT_REQ);
	}

	public ClientHeartbeatReq(byte[] data) {
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
		return "ClientHeartbeatReq [securityCode=" + securityCode + "]";
	}


}
