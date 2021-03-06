package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 过程文件开始上传响应消息
 * 
 * @author t2129
 *
 */
public class ProcedureFileStartUploadRsp extends AbstractRspPackage {
	/** SessionID */
	private LongPdu sessionID = new LongPdu();

	public LongPdu getSessionID() {
		return sessionID;
	}

	public void setSessionID(LongPdu sessionID) {
		this.sessionID = sessionID;
	}

	public ProcedureFileStartUploadRsp() {
		this.header.setMsgType(Commands.PROCEDURE_FILE_START_UPLOAD_RSP);
	}

	public ProcedureFileStartUploadRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, sessionID.getBytes(), index);
		index += sessionID.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		sessionID = new LongPdu(index, data);
		index += sessionID.getLength();
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + + this.errorCode.getLength() + this.sessionID.getLength();
		return length;
	}

	@Override
	public String toString() {
		return "ProcedureFileStartUploadRsp [sessionID=" + sessionID + "]";
	}
}
