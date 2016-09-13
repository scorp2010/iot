package cn.inovance.iotgp.common.msg.cdSimulator;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class RunStatusRsp extends Response {

	public RunStatusRsp() {
		this(SeqGenerator.next());
	}

	public RunStatusRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.RUNSTATUS_RSP;
	}

	public RunStatusRsp(RunStatusNotify req) {
		super(req);
		this.msgType = MsgType.RUNSTATUS_RSP;
	}

}
