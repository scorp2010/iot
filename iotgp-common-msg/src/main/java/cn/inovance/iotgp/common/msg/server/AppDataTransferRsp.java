package cn.inovance.iotgp.common.msg.server;

public class AppDataTransferRsp extends Response {
	public AppDataTransferRsp() {
		this.msgType = MsgType.APP_DATA_TRANSFER_RSP;
	}

	public AppDataTransferRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.APP_DATA_TRANSFER_RSP;
	}

	public AppDataTransferRsp(AppHeartbeatReq req) {
		super(req);
		this.msgType = MsgType.APP_DATA_TRANSFER_RSP;
	}
}
