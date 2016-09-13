package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetSecurityCodeReq extends AbstractReqPackage {
	public final int PACKAGE_LENGTH=46;
	
	public GetSecurityCodeReq(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.GET_SECURITY_CODE_REQ);
	}
	
	public GetSecurityCodeReq(byte[] data){
		this.data = data;
		header.setData(data);
	}
	@Override
	public void constructBody(byte[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void parseBody() throws MessageParseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws MessageParseException{
		GetSecurityCodeReq req = new GetSecurityCodeReq();
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}
}
