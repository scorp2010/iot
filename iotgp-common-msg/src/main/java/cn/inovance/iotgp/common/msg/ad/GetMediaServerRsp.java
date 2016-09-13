package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetMediaServerRsp extends AbstractRspPackage {
	protected final int INDEX_MEDIA_SERVER_HOST=48;
	protected final int INDEX_MEDIA_SERVER_PORT=54;
	
	public final int PACKAGE_LENGTH=56;
	
	private ShortPdu[] mediaServerHost = new ShortPdu[6];//默认处理成功
	
	private IntPdu mediaServerPort = new IntPdu(0);
	
	public Short[] getMediaServerHost() {
		Short[] values = new Short[6];
		for(int i = 0;i<mediaServerHost.length; i++){
			if(mediaServerHost[i] != null)
			values[i] = mediaServerHost[i].getValue();
		}
		return values;
	}

	public void setMediaServerHost(Short[] mediaServerHost) throws MessageParseException {
		for(int i = 0;i<mediaServerHost.length; i++){
			this.mediaServerHost[i] = new ShortPdu(mediaServerHost[i]);
		}
	}
	
	public void setMediaServerHost(String mediaServerHost) throws MessageParseException {
		String[] array = mediaServerHost.split("\\.");
		for(int i = 0;i<array.length; i++){
			this.mediaServerHost[i] = new ShortPdu(Short.parseShort(array[i]));
		}
	}

	public int getMediaServerPort() {
		return mediaServerPort.getValue();
	}

	public void setMediaServerPort(int mediaServerPort) {
		this.mediaServerPort = new IntPdu(mediaServerPort);
	}

	public GetMediaServerRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.GET_MEDIA_SERVER_INFO_RSP);
	}
	
	public GetMediaServerRsp(byte[] data){
		this.data = data;
		header.setData(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		for(int index = INDEX_MEDIA_SERVER_HOST; index<mediaServerHost.length+INDEX_MEDIA_SERVER_HOST; index++){
			ShortPdu tmp = mediaServerHost[index - INDEX_MEDIA_SERVER_HOST];
			if(tmp == null){
				ByteOps.addByteArray(data, (byte)0x00, index);
			}else 
				ByteOps.addByteArray(data, tmp.getBytes(), index);
		}
		ByteOps.addByteArray(data, mediaServerPort.getBytes(), INDEX_MEDIA_SERVER_PORT);
	}

	@Override
	public void parseBody() throws MessageParseException {
		for(int index = 0; index<mediaServerHost.length; index++){
			mediaServerHost[index] = new ShortPdu(index+INDEX_MEDIA_SERVER_HOST , data);
		}
		mediaServerPort = new IntPdu(this.INDEX_MEDIA_SERVER_PORT, data);
	}

	public static void main(String[] args) throws MessageParseException{
		GetMediaServerRsp rsp = new GetMediaServerRsp();
		Short[] hosts = {192, 168, 58, 38};
		int port = 8080;
		rsp.setErrorCode(1);
		rsp.getHeader().setEquipmentNo("1234566");
		rsp.getHeader().setSessionID("11111111111111");
		rsp.setMediaServerHost("192.168.58.38");
		rsp.setMediaServerPort(port);
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		GetMediaServerRsp rsp2 = new GetMediaServerRsp(rsp.getData());
		rsp2.parse();
		System.out.println(rsp2.getMediaServerHost()[0]);
	}
}
