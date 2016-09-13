package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class HeartbeatRsp extends AbstractRspPackage {
	protected final int INDEX_SERVER_TIME=48;
	
	public final int PACKAGE_LENGTH=52;
	
	private LongPdu serverTime;
	

	public Long getServerTime() {
		return serverTime.getValue();
	}

	public void setServerTime(Long serverTime) {
		this.serverTime = new LongPdu(serverTime);
	}
	public HeartbeatRsp(Header header){
		data = new byte[PACKAGE_LENGTH];
		this.header = header.clone(Commands.HEARTBEAT_RSP, data);
	}
	
	public HeartbeatRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.HEARTBEAT_RSP);
	}
	
	public HeartbeatRsp(byte[] data){
		this.data = data;
		header.setData(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, serverTime.getBytes(), INDEX_SERVER_TIME);
	}

	@Override
	public void parseBody() throws MessageParseException {
		serverTime = new LongPdu(this.INDEX_SERVER_TIME, data);
	}

	public static void main(String[] args) throws MessageParseException{
		HeartbeatRsp rsp = new HeartbeatRsp();
		rsp.setErrorCode(1);
		rsp.getHeader().setEquipmentNo("1234566");
		rsp.getHeader().setSessionID("11111111111111");
		rsp.setServerTime(System.currentTimeMillis());
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		HeartbeatRsp rsp2 = new HeartbeatRsp(rsp.getData());
		rsp2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
	}
}
