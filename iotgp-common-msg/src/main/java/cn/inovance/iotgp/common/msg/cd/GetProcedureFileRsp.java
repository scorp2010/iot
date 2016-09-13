package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetProcedureFileRsp extends AbstractRspPackage {
	/** SessionID */
	private LongPdu sessionID = new LongPdu();
	/** 文件查询结果 0：未上传 1：已上传 */
	private ShortPdu result = new ShortPdu();

	public LongPdu getSessionID() {
		return sessionID;
	}

	public void setSessionID(LongPdu sessionID) {
		this.sessionID = sessionID;
	}

	public ShortPdu getResult() {
		return result;
	}

	public void setResult(ShortPdu result) {
		this.result = result;
	}

	public GetProcedureFileRsp() {
		this.header.setMsgType(Commands.QUERY_PROCEDURE_FILE_RSP);
	}

	public GetProcedureFileRsp(byte[] data) {
		super(data);
	}
	
	public GetProcedureFileRsp(GetProcedureFileReq req ) {
		this.header.setMsgType(Commands.QUERY_PROCEDURE_FILE_RSP);
		this.setSessionID(req.getSessionID());
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, sessionID.getBytes(), index);
		index += sessionID.getLength();
		ByteOps.addByteArray(data, result.getBytes(), index);
		index += result.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		sessionID = new LongPdu(index, data);
		index += sessionID.getLength();
		result = new ShortPdu(index, data);
		index += result.getLength();
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + + this.errorCode.getLength() + this.sessionID.getLength()
				+ this.getResult().getLength();
		return length;
	}

	@Override
	public String toString() {
		return "QueryProcedureFileRsp [sessionID=" + sessionID + ", result="
				+ result + "]";
	}
}
