package cn.inovance.iotgp.common.msg.server;

public class CustomerSyncRsp extends Response {

	public CustomerSyncRsp() {
		this.msgType = MsgType.CUSTOMER_INFO_SYNC_RSP;
	}
	
	public CustomerSyncRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.CUSTOMER_INFO_SYNC_RSP;
	}
	
}
