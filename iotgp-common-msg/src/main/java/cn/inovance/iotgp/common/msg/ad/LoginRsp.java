package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class LoginRsp extends AbstractRspPackage {
	protected final int INDEX_SESSION_ID=48;
	protected final int INDEX_PASS_PORT=66;
	protected final int INDEX_CONNECT_TYPE=74;
	protected final int INDEX_HEARTBEAT_INTERVAL=75;
	protected final int INDEX_AD_UPDATE_INTERVAL=77;
	
	public final int PACKAGE_LENGTH=79;
	
	public final int LENGTH_SESSION_ID=18;
	public final int LENGTH_PASS_PORT=8;
	
	private StringPdu sessionId=new StringPdu();
	private StringPdu passPort=new StringPdu();
	private ShortPdu connectType=new ShortPdu();
	private IntPdu heartbeatInterval=new IntPdu();
	private IntPdu adUpdateInterval=new IntPdu();

	public String getSessionId() {
		return sessionId.getValue();
	}

	public void setSessionId(String sessionId) {
		this.sessionId = new StringPdu(LENGTH_SESSION_ID, sessionId);
	}

	public String getPassPort() {
		return passPort.getValue();
	}

	public void setPassPort(String passPort) {
		this.passPort = new StringPdu(LENGTH_PASS_PORT, passPort);
	}

	public Short getConnectType() {
		return connectType.getValue();
	}

	public void setConnectType(Short connectType) {
		this.connectType = new ShortPdu(connectType);
	}

	public int getHeartbeatInterval() {
		return heartbeatInterval.getValue();
	}

	public void setHeartbeatInterval(int heartbeatInterval) {
		this.heartbeatInterval = new IntPdu(heartbeatInterval);
	}

	public int getAdUpdateInterval() {
		return adUpdateInterval.getValue();
	}

	public void setAdUpdateInterval(int adUpdateInterval) {
		this.adUpdateInterval = new IntPdu(adUpdateInterval);
	}
	public LoginRsp(Header header){
		data = new byte[PACKAGE_LENGTH];
		this.header = header.clone(Commands.LOGIN_RSP, data);
	}
	
	public LoginRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.LOGIN_RSP);
	}
	
	public LoginRsp(byte[] data){
		this.data = data;
		header.setData(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, sessionId.getBytes(), INDEX_SESSION_ID);
		ByteOps.addByteArray(data, passPort.getBytes(), INDEX_PASS_PORT);
		ByteOps.addByteArray(data, connectType.getBytes(), INDEX_CONNECT_TYPE);
		ByteOps.addByteArray(data, heartbeatInterval.getBytes(), INDEX_HEARTBEAT_INTERVAL);
		ByteOps.addByteArray(data, adUpdateInterval.getBytes(), INDEX_AD_UPDATE_INTERVAL);
	}

	@Override
	public void parseBody() throws MessageParseException {
		sessionId = new StringPdu(LENGTH_SESSION_ID, this.INDEX_SESSION_ID, data);
		passPort = new StringPdu(LENGTH_PASS_PORT, this.INDEX_PASS_PORT, data);
		connectType = new ShortPdu(this.INDEX_CONNECT_TYPE, data);
		heartbeatInterval = new IntPdu(this.INDEX_HEARTBEAT_INTERVAL, data);
		adUpdateInterval = new IntPdu(this.INDEX_AD_UPDATE_INTERVAL, data);
	}
	
	@Override
	public String toString() {
		return "LoginRsp [sessionId=" + sessionId + ", passPort=" + passPort
				+ ", connectType=" + connectType + ", heartbeatInterval="
				+ heartbeatInterval + ", adUpdateInterval=" + adUpdateInterval
				+ "]";
	}

	public static void main(String[] args) throws MessageParseException{
		LoginRsp rsp = new LoginRsp();
		rsp.setErrorCode(1);
		rsp.getHeader().setEquipmentNo("1234566");
		rsp.getHeader().setSessionID("11111111111111");
		rsp.setSessionId("22222222");
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		LoginRsp rsp2 = new LoginRsp(rsp.getHeader());
		rsp2.setErrorCode(1);
		//rsp2.getHeader().setEquipmentNo("1234566");
		//rsp2.getHeader().setSessionID("11111111111111");
		//rsp2.setSessionId("22222222");
		rsp2.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp2.getData()));
	}
}
