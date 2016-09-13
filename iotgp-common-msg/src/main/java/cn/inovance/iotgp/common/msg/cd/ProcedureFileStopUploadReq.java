package cn.inovance.iotgp.common.msg.cd;

import java.util.Date;

import cn.inovance.iotgp.common.msg.cd.meta.DatatimePdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

/**
 * 过程文件停止上传请求消息
 * @author t2129
 *
 */
public class ProcedureFileStopUploadReq extends AbstractReqPackage {
	/** SessionID */
	private LongPdu sessionID = new LongPdu();
	/** 停止时间 */
	private DatatimePdu stopDate = new DatatimePdu(new Date());

	public LongPdu getSessionID() {
		return sessionID;
	}

	public void setSessionID(LongPdu sessionID) {
		this.sessionID = sessionID;
	}

	public DatatimePdu getStopDate() {
		return stopDate;
	}

	public void setStopDate(DatatimePdu stopDate) {
		this.stopDate = stopDate;
	}

	public ProcedureFileStopUploadReq() {
		this.header.setMsgType(Commands.PROCEDURE_FILE_STOP_UPLOAD_REQ);
		this.setSessionID(new LongPdu(SeqGenerator.next()));
	}

	public ProcedureFileStopUploadReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, sessionID.getBytes(), index);
		index += sessionID.getLength();
		ByteOps.addByteArray(data, stopDate.getBytes(), index);
		index += stopDate.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		sessionID = new LongPdu(index, data);
		index += sessionID.getLength();
		stopDate = new DatatimePdu(index, data);
		index += stopDate.getLength();
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + this.sessionID.getLength()
				+ this.getStopDate().getLength();
		return length;
	}

	@Override
	public String toString() {
		return "ProcedureFileStoptUploadReq [sessionID=" + sessionID
				+ ", stopDate=" + stopDate + "]";
	}
}
