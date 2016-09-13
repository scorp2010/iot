package cn.inovance.iotgp.common.msg.cdSimulator;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class LoginRsp extends Response {

	public LoginRsp() {
		this(SeqGenerator.next());
	}

	public LoginRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.LOGIN_RSP;
	}

	public LoginRsp(LoginReq req) {
		super(req);
		this.msgType = MsgType.LOGIN_RSP;
	}

}
