package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public abstract class Request extends JsonObject {

	private Long msgSeq = SeqGenerator.next();

	public Long getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(Long msgSeq) {
		this.msgSeq = msgSeq;
	}

	public Request() {

	}

}
