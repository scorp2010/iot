package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 数据心跳响应消息
 * 
 * @author c2100
 * 
 */
public class DataConnHeartBeatRsp extends AbstractReqPackage {

	protected static final int INDEX_BODY_RESERVE = 7;
	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public String getSecurityCode() {
		return securityCode.getValue();
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public DataConnHeartBeatRsp(Header header) {
		data = new byte[INDEX_BODY_RESERVE];
		this.header = header.clone(Commands.DATA_CONN_HEARTBEAT_RSP, data);
	}

	public DataConnHeartBeatRsp() {
		data = new byte[INDEX_BODY_RESERVE];
		header = new Header(data, Commands.DATA_CONN_HEARTBEAT_RSP);
	}

	public DataConnHeartBeatRsp(byte[] data) {
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
		return "DataConnHeartBeatRsp [securityCode=" + securityCode
				+ ", header=" + header + "]";
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + SECURITY_LENGTH;
	}

	public static void main(String[] args) throws MessageParseException {
		DataConnHeartBeatRsp rsp = new DataConnHeartBeatRsp();
		rsp.setSecurityCode("123123");
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		DataConnHeartBeatRsp rsp2 = new DataConnHeartBeatRsp(rsp.getData());
		rsp2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
	}

}
