package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 摄像头抓拍响应
 * 
 * @author t2129
 *
 */
public class PhotoCaptureRsp extends AbstractReqPackage {
	/** 消息序号 */
	private ShortPdu msgSeq = new ShortPdu();
	/** 文件名长度 */
	private ShortPdu fileNameLength = new ShortPdu();
	/** 文件名 */
	private StringPdu fileName = new StringPdu();

	public ShortPdu getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(ShortPdu msgSeq) {
		this.msgSeq = msgSeq;
	}

	public ShortPdu getFileNameLength() {
		return fileNameLength;
	}

	public void setFileNameLength(ShortPdu fileNameLength) {
		this.fileNameLength = fileNameLength;
	}

	public StringPdu getFileName() {
		return fileName;
	}

	public void setFileName(StringPdu fileName) {
		this.fileName = fileName;
	}

	public PhotoCaptureRsp() {
		this.header.setMsgType(Commands.PHOTO_CAPTURE_RSP);
	}

	public PhotoCaptureRsp(byte[] data) {
		super(data);
	}
	
	public PhotoCaptureRsp(PhotoCaptureReq req) {
		this.header.setMsgType(Commands.PHOTO_CAPTURE_RSP);
		this.setMsgSeq(req.getMsgSeq());
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, msgSeq.getBytes(), index);
		index += msgSeq.getLength();
		fileNameLength = new ShortPdu((short) fileName.getLength());
		ByteOps.addByteArray(data, fileNameLength.getBytes(), index);
		index += fileNameLength.getLength();
		ByteOps.addByteArray(data, fileName.getBytes(), index);
		index += fileName.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		msgSeq = new ShortPdu(index, data);
		index += msgSeq.getLength();
		fileNameLength = new ShortPdu(index, data);
		index += fileNameLength.getLength();
		fileName = new StringPdu(fileNameLength.getValue(), index, data);
		index += fileName.getLength();
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + this.getMsgSeq().getLength()
				+ this.getFileNameLength().getLength()
				+ this.getFileName().getLength();
		return length;
	}

	@Override
	public String toString() {
		return "PhotoCaptureRsp [msgSeq=" + msgSeq + ", fileNameLength="
				+ fileNameLength + ", fileName=" + fileName + "]";
	}
}
