package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class DeviceSyncRsp extends Response {
	public DeviceSyncRsp() {
		this(SeqGenerator.next());
	}

	public DeviceSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.DEVICE_INFO_SYNC_RSP;
	}

	public DeviceSyncRsp(DeviceSyncReq req) {
		super(req);
		this.msgType = MsgType.DEVICE_INFO_SYNC_RSP;
	}
}
