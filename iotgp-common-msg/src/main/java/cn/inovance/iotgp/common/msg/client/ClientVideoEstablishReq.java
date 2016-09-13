package cn.inovance.iotgp.common.msg.client;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * 视频客户端连接请求消息0x1070
 * 
 * @author c2100
 */
public class ClientVideoEstablishReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	public ClientVideoEstablishReq() {
		this.header.setMsgType(Commands.CLIENT_VIDEO_ESTABLISH_REQ);
	}

	public String getSecurityCode() {
		return securityCode.getValue();
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public ClientVideoEstablishReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + SECURITY_LENGTH;
	}

	@Override
	public String toString() {
		return "VideoConnEstablishReq [securityCode=" + securityCode + "]";
	}

	public static void main(String[] args) {

		ClientVideoEstablishReq req = new ClientVideoEstablishReq();
		req.setSecurityCode(new String(RandomCode.generateCode16()));
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		String test1 = "002000700100003743394C4E37434432434932484E5944010004C0A83C2F18FD";
		ClientVideoEstablishReq req1 = new ClientVideoEstablishReq(
				ByteOps.hexStringToBytes(test1));
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
