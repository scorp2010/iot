package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetSecurityCodeRsp extends AbstractRspPackage {
	protected final int INDEX_SECURITY_CODE=48;
	
	public final int PACKAGE_LENGTH=56;
	
	public final int LENGTH_SECURITY_CODE=8;
	
	private StringPdu securityCode;
	
	public GetSecurityCodeRsp(Header header){
		data = new byte[PACKAGE_LENGTH];
		this.header = new Header(data, Commands.GET_SECURITY_CODE_RSP);
		this.header.setEquipmentNo(header.getEquipmentNo());
		this.header.setMsgSeq(header.getMsgSeq());
	}

	public String getSecurityCode() {
		return securityCode.getValue();
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(LENGTH_SECURITY_CODE, securityCode);
	}

	public GetSecurityCodeRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.GET_SECURITY_CODE_RSP);
	}
	
	public GetSecurityCodeRsp(byte[] data){
		this.data = data;
		header.setData(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), INDEX_SECURITY_CODE);
	}

	@Override
	public void parseBody() throws MessageParseException {
		securityCode = new StringPdu(LENGTH_SECURITY_CODE, this.INDEX_SECURITY_CODE, data);
	}

	@Override
	public String toString() {
		return "GetSecurityCodeRsp [securityCode=" + securityCode + "]";
	}

	public static void main(String[] args) throws MessageParseException{
		GetSecurityCodeRsp rsp = new GetSecurityCodeRsp();
		rsp.setErrorCode(1);
		rsp.getHeader().setEquipmentNo("1234566");
		rsp.getHeader().setSessionID("11111111111111");
		rsp.setSecurityCode("22222222");
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		GetSecurityCodeRsp rsp2 = new GetSecurityCodeRsp(rsp.getData());
		rsp2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
	}
}
