package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 过程文件查询请求消息
 * 
 * @author t2129
 *
 */
public class GetProcedureFileReq extends AbstractReqPackage {
	/** SessionID */
	private LongPdu sessionID = new LongPdu();
	/** 文件名长度 */
	private ShortPdu fileNameLength = new ShortPdu();
	/** 文件名 */
	private StringPdu fileName = new StringPdu();
	/** 文件类型 1:实时数据日志文件 2：音视频文件 3：设备运行日志 */
	private ShortPdu fileType = new ShortPdu();

	public LongPdu getSessionID() {
		return sessionID;
	}

	public void setSessionID(LongPdu sessionID) {
		this.sessionID = sessionID;
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

	public ShortPdu getFileType() {
		return fileType;
	}

	public void setFileType(ShortPdu fileType) {
		this.fileType = fileType;
	}

	public GetProcedureFileReq() {
		this.header.setMsgType(Commands.QUERY_PROCEDURE_FILE_REQ);
	}

	public GetProcedureFileReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, sessionID.getBytes(), index);
		index += sessionID.getLength();
		fileNameLength = new ShortPdu((short) fileName.getLength());
		ByteOps.addByteArray(data, fileNameLength.getBytes(), index);
		index += fileNameLength.getLength();
		ByteOps.addByteArray(data, fileName.getBytes(), index);
		index += fileName.getLength();
		ByteOps.addByteArray(data, fileType.getBytes(), index);
		index += fileType.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		sessionID = new LongPdu(index, data);
		index += sessionID.getLength();
		fileNameLength = new ShortPdu(index, data);
		index += fileNameLength.getLength();
		fileName = new StringPdu(fileNameLength.getValue(), index, data);
		index += fileName.getLength();
		fileType = new ShortPdu(index, data);
		index += fileType.getLength();
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + this.sessionID.getLength()
				+ this.getFileNameLength().getLength()
				+ this.getFileName().getLength()
				+ this.getFileType().getLength();
		return length;
	}

	@Override
	public String toString() {
		return "QueryProcedureFileReq [sessionID=" + sessionID
				+ ", fileNameLength=" + fileNameLength + ", fileName="
				+ fileName + ", fileType=" + fileType + "]";
	}
}
