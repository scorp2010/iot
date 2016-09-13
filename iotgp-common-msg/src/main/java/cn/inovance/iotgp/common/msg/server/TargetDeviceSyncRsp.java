package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class TargetDeviceSyncRsp extends Response {
	
	public TargetDeviceSyncRsp() {
		this(SeqGenerator.next());
	}

	public TargetDeviceSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.TARGET_DEVICE_SYNC_RSP;
	}
	
	public TargetDeviceSyncRsp(TargetDeviceSyncReq req) {
		super(req);
		this.msgType = MsgType.TARGET_DEVICE_SYNC_RSP;
	}
	
}
