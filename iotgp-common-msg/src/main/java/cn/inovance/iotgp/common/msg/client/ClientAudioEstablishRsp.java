package cn.inovance.iotgp.common.msg.client;

import cn.inovance.iotgp.common.msg.cd.AbstractRspPackage;
import cn.inovance.iotgp.common.msg.cd.CdHeartbeatRsp;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 音频客户端连接响应消息
 * 
 * @author c2100
 */
public class ClientAudioEstablishRsp extends AbstractRspPackage {

	public ClientAudioEstablishRsp() {
		this.header.setMsgType(Commands.CLIENT_AUDIO_ESTABLISH_RSP);
	}

	public ClientAudioEstablishRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
	}

	@Override
	protected void parseBody() throws MessageParseException {
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + errorCode.getLength();
	}

	@Override
	public String toString() {
		return "VideoConnEstablishRsp [errorCode=" + errorCode.getValue() + "]";
	}
	
	public static void main(String[] args) {
		ClientAudioEstablishRsp rsp = new ClientAudioEstablishRsp();
		rsp.setErrorCode((short) 1);
		rsp.construct();
		System.out.println(ByteOps.bytesToHexString(rsp.getData()));
		CdHeartbeatRsp rsp1 = new CdHeartbeatRsp(rsp.getData());
		System.out.println(rsp.getData().length);
		try {
			rsp1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(rsp1.toString());
	}

}
