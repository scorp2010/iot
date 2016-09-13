package cn.inovance.iotgp.common.msg.server;

public class AppStatusChangeRsp extends Response {

	public AppStatusChangeRsp() {
		this.msgType = MsgType.APP_STATUS_CHANGE_RSP;
	}

	public AppStatusChangeRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.APP_STATUS_CHANGE_RSP;
	}

	public AppStatusChangeRsp(AppStatusChangeReq req) {
		super(req);
		this.msgType = MsgType.APP_STATUS_CHANGE_RSP;
	}

}
