package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName: CdHeartBeat <br/>
 * Function: 采集设备数据心跳消息. <br/>
 * date: 2014-4-8 下午4:38:18 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class DataConnHeartBeatReq extends AbstractReqPackage {

	protected static final int INDEX_BODY_RESERVE = 7;
	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public String getSecurityCode() {
		return securityCode.getValue();
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public DataConnHeartBeatReq(Header header) {
		data = new byte[INDEX_BODY_RESERVE];
		this.header = header.clone(Commands.DATA_CONN_HEARTBEAT_REQ, data);
	}

	public DataConnHeartBeatReq() {
		data = new byte[INDEX_BODY_RESERVE];
		header = new Header(data, Commands.DATA_CONN_HEARTBEAT_REQ);
	}

	public DataConnHeartBeatReq(byte[] data) {
		this.data = data;
		header.setData(data);
	}

	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
	}

	@Override
	public void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
	}

	@Override
	public String toString() {
		return "DataConnHeartBeatReq [securityCode=" + securityCode
				+ ", header=" + header + "]";
	}
	
	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + SECURITY_LENGTH;
	}
	
	public static void main(String[] args) throws MessageParseException {
		DataConnHeartBeatReq req = new DataConnHeartBeatReq();
		req.setSecurityCode("123123");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
		DataConnHeartBeatReq req2 = new DataConnHeartBeatReq(req.getData());
		req2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req2.getData()));
	}
	
}
