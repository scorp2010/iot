package cn.inovance.iotgp.common.msg.cdSimulator;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class HeartbeatRsp extends Response {

	public HeartbeatRsp() {
		this(SeqGenerator.next());
	}

	public HeartbeatRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.HEARTBEAT_RSP;
	}

	public HeartbeatRsp(HeartbeatReq req) {
		super(req);
		this.msgType = MsgType.HEARTBEAT_RSP;
	}

	private String securityCode = "";

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
}
