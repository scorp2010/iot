package cn.inovance.iotgp.common.msg.server;

public class DeviceParameterSyncRsp extends Response {

	public DeviceParameterSyncRsp() {
		this.msgType = MsgType.DEVICE_PARAS_SYNC_RSP;
	}
	
	public DeviceParameterSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.DEVICE_PARAS_SYNC_RSP;
	}
	
	public DeviceParameterSyncRsp(DeviceParameterSyncReq req) {
		super(req);
		this.msgType = MsgType.DEVICE_PARAS_SYNC_RSP;
	}
	
}
