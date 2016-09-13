package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class TdCtrlProtocolSyncRsp extends Response {

	public TdCtrlProtocolSyncRsp() {
		this(SeqGenerator.next());
	}

	public TdCtrlProtocolSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.CTRL_PROTOCOL_SYNC_RSP;
	}

	public TdCtrlProtocolSyncRsp(TdCtrlProtocolSyncReq req) {
		super(req);
		this.msgType = MsgType.CTRL_PROTOCOL_SYNC_RSP;
	}
}
