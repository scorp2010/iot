package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class FaultCodeSyncRsp extends Response {
	
	public FaultCodeSyncRsp() {
		this(SeqGenerator.next());
	}

	public FaultCodeSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.FAULT_CODE_SYNC_RSP;
	}
	
	public FaultCodeSyncRsp(FaultCodeSyncReq req) {
		super(req);
		this.msgType = MsgType.FAULT_CODE_SYNC_RSP;
	}

}
