package cn.inovance.iotgp.common.msg.cdSimulator;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class Request extends JsonObject {

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
