package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class DefaultRsp extends AbstractRspPackage{
	protected final int PACKAGE_LENGTH=48;
	
	public DefaultRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, 0);
	}
	
	public DefaultRsp(int commandid){
		header.setMsgType(commandid);
	}
	
	public DefaultRsp(int commandid, Header reqHeader){
		data = new byte[PACKAGE_LENGTH];
		this.header = reqHeader.clone(commandid, data);
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
		DefaultRsp rsp = new DefaultRsp();
		rsp.setErrorCode(1);
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
	}
}
