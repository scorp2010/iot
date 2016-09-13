package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * 视频播放停止通知消息
 * 
 * @author z1979
 */
public class VideoStopReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;

	private StringPdu securityCode;

	private ShortPdu cameraSerial;

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public Short getCameraSerial() {
		return cameraSerial.getValue();
	}

	public void setCameraSerial(Short cameraSerial) {
		this.cameraSerial = new ShortPdu(cameraSerial);
	}

	public VideoStopReq() {
		this.header.setMsgType(Commands.VIDEO_STOP_REQ);
	}

	public VideoStopReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {

		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, cameraSerial.getBytes(), index);
		index += cameraSerial.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {

		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		cameraSerial = new ShortPdu(index, data);
		index += cameraSerial.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + SECURITY_LENGTH
				+ cameraSerial.getLength();
	}

	@Override
	public String toString() {
		return "VideoConnDestroyReq [securityCode=" + securityCode
				+ ", cameraSerial=" + cameraSerial + "]";
	}

	public static void main(String[] args) throws Exception {

		VideoStopReq req = new VideoStopReq();
		req.setSecurityCode(new String(RandomCode.generateCode16()));
		req.setCameraSerial((short) 1);
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
		System.out.println(req.toString());
	}

}
