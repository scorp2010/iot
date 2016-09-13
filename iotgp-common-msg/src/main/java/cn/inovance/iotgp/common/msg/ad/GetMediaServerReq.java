package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.CRCUtil;

public class GetMediaServerReq extends AbstractReqPackage {
	public final int PACKAGE_LENGTH=46;
	
	public GetMediaServerReq(){
		data = new byte[PACKAGE_LENGTH];
		header.setData(data);
		header.setMsgType(Commands.GET_MEDIA_SERVER_INFO_REQ);
	}
	
	public GetMediaServerReq(byte[] data){
		this.data = data;
		header.setData(data);
	}

	@Override
	public void constructBody(byte[] data) {
		if(data != null){
			int crcbytes = CRCUtil.evalCRC16(data);
			System.out.println(crcbytes);
		}

	}

	@Override
	public void parseBody() throws MessageParseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws MessageParseException{
		GetMediaServerReq req = new GetMediaServerReq();
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
		GetMediaServerReq req2 = new GetMediaServerReq(req.getData());
		req2.parse();
		System.out.println(req2.getHeader().getMsgType());
	}
}
