package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class TdCtrlCmdSyncRsp extends Response {

	public TdCtrlCmdSyncRsp() {
		this(SeqGenerator.next());
	}

	public TdCtrlCmdSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.TD_CTRL_CMD_SYNC_RSP;
	}

	public TdCtrlCmdSyncRsp(TdCtrlCmdSyncReq req) {
		super(req);
		this.msgType = MsgType.TD_CTRL_CMD_SYNC_RSP;
	}

}
