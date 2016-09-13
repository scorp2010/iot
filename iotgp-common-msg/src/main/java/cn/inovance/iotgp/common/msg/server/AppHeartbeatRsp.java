package cn.inovance.iotgp.common.msg.server;

public class AppHeartbeatRsp extends Response {
	public AppHeartbeatRsp() {
		this.msgType = MsgType.APP_HEARTBEAT_RSP;
	}

	public AppHeartbeatRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.APP_HEARTBEAT_RSP;
	}

	public AppHeartbeatRsp(AppHeartbeatReq req) {
		super(req);
		this.msgType = MsgType.APP_HEARTBEAT_RSP;
	}
}
