package cn.inovance.iotgp.common.msg.server;

public class TargetDeviceStatusChangeRsp extends Response {
	
	public TargetDeviceStatusChangeRsp() {
		this.msgType = MsgType.TARGET_DEVICE_STATUS_CHANGE_RSP;
	}

	public TargetDeviceStatusChangeRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.TARGET_DEVICE_STATUS_CHANGE_RSP;
	}
	
	public TargetDeviceStatusChangeRsp(TargetDeviceStatusChangeReq req) {
		super(req);
		this.msgType = MsgType.TARGET_DEVICE_STATUS_CHANGE_RSP;
	}
}
