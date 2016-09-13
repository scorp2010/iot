package cn.inovance.iotgp.common.msg.cd;

import java.util.Date;

import cn.inovance.iotgp.common.msg.cd.meta.DatatimePdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

/**
 * 过程文件开始上传请求消息
 * 
 * @author t2129
 *
 */
public class ProcedureFileStartUploadReq extends AbstractReqPackage {
	public static final int UPLOAD_USER_ACCOUNT_LENGTH = 100;

	public static final int UPLOAD_USER_PASSWORD_LENGTH = 32;

	/** SessionID */
	private LongPdu sessionID = new LongPdu();
	/** 开始时间 */
	private DatatimePdu startDate = new DatatimePdu(new Date());
	/** 文件类型 */
	private ShortPdu fileType = new ShortPdu();
	/** 传输方式 0：FTP 1：GFTP */
	private ShortPdu transferType = new ShortPdu();
	/** 文件服务器地址 */
	private ServerConnectionInfoPdu serverConnectionInfo;
	/** 上传用户名 */
	private StringPdu uploadUserAccount = new StringPdu();
	/** 上传密码 */
	private StringPdu uploadUserPassword = new StringPdu();
	/** 上传文件路径长度 */
	private ShortPdu uploadFilePathLength = new ShortPdu();
	/** 上传 文件路径 */
	private StringPdu uploadFilePath = new StringPdu();

	public LongPdu getSessionID() {
		return sessionID;
	}

	public void setSessionID(LongPdu sessionID) {
		this.sessionID = sessionID;
	}

	public DatatimePdu getStartDate() {
		return startDate;
	}

	public void setStartDate(DatatimePdu startDate) {
		this.startDate = startDate;
	}

	public ShortPdu getFileType() {
		return fileType;
	}

	public void setFileType(ShortPdu fileType) {
		this.fileType = fileType;
	}

	public ShortPdu getTransferType() {
		return transferType;
	}

	public void setTransferType(ShortPdu transferType) {
		this.transferType = transferType;
	}

	public ServerConnectionInfoPdu getServerConnectionInfo() {
		return serverConnectionInfo;
	}

	public void setServerConnectionInfo(
			ServerConnectionInfoPdu serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
	}

	public StringPdu getUploadUserAccount() {
		return uploadUserAccount;
	}

	public void setUploadUserAccount(StringPdu uploadUserAccount) {
		this.uploadUserAccount = uploadUserAccount;
	}

	public StringPdu getUploadUserPassword() {
		return uploadUserPassword;
	}

	public void setUploadUserPassword(StringPdu uploadUserPassword) {
		this.uploadUserPassword = uploadUserPassword;
	}

	public ShortPdu getUploadFilePathLength() {
		return uploadFilePathLength;
	}

	public void setUploadFilePathLength(ShortPdu uploadFilePathLength) {
		this.uploadFilePathLength = uploadFilePathLength;
	}

	public StringPdu getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(StringPdu uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public ProcedureFileStartUploadReq() {
		this.header.setMsgType(Commands.PROCEDURE_FILE_START_UPLOAD_REQ);
		this.setSessionID(new LongPdu(SeqGenerator.next()));
	}

	public ProcedureFileStartUploadReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, sessionID.getBytes(), index);
		index += sessionID.getLength();
		ByteOps.addByteArray(data, startDate.getBytes(), index);
		index += startDate.getLength();
		ByteOps.addByteArray(data, fileType.getBytes(), index);
		index += fileType.getLength();
		ByteOps.addByteArray(data, transferType.getBytes(), index);
		index += transferType.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
		ByteOps.addByteArray(data, uploadUserAccount.getBytes(), index);
		index += uploadUserAccount.getLength();
		ByteOps.addByteArray(data, uploadUserPassword.getBytes(), index);
		index += uploadUserPassword.getLength();
		uploadFilePathLength = new ShortPdu((short) uploadFilePath.getLength());
		ByteOps.addByteArray(data, uploadFilePathLength.getBytes(), index);
		index += uploadFilePathLength.getLength();
		ByteOps.addByteArray(data, uploadFilePath.getBytes(), index);
		index += uploadFilePath.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		sessionID = new LongPdu(index, data);
		index += sessionID.getLength();
		startDate = new DatatimePdu(index, data);
		index += startDate.getLength();
		fileType = new ShortPdu(index, data);
		index += fileType.getLength();
		transferType = new ShortPdu(index, data);
		index += transferType.getLength();
		serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		index += serverConnectionInfo.getLength();
		uploadUserAccount = new StringPdu(UPLOAD_USER_ACCOUNT_LENGTH, index,
				data);
		index += UPLOAD_USER_ACCOUNT_LENGTH;
		uploadUserPassword = new StringPdu(UPLOAD_USER_PASSWORD_LENGTH, index,
				data);
		index += UPLOAD_USER_PASSWORD_LENGTH;
		uploadFilePathLength = new ShortPdu(index, data);
		index += uploadFilePathLength.getLength();
		uploadFilePath = new StringPdu(uploadFilePathLength.getValue(), index,
				data);
		index += uploadFilePath.getLength();
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + this.sessionID.getLength()
				+ this.getStartDate().getLength()
				+ this.getFileType().getLength()
				+ this.getTransferType().getLength()
				+ this.getServerConnectionInfo().getLength()
				+ UPLOAD_USER_ACCOUNT_LENGTH + UPLOAD_USER_PASSWORD_LENGTH
				+ this.getUploadFilePathLength().getLength()
				+ this.getUploadFilePath().getLength();
		return length;
	}

	@Override
	public String toString() {
		return "ProcedureFileStartUploadReq [sessionID=" + sessionID
				+ ", startDate=" + startDate + ", fileType=" + fileType
				+ ", transferType=" + transferType + ", serverConnectionInfo="
				+ serverConnectionInfo + ", uploadUserAccount="
				+ uploadUserAccount + ", uploadUserPassword="
				+ uploadUserPassword + ", uploadFilePathLength="
				+ uploadFilePathLength + ", uploadFilePath=" + uploadFilePath
				+ "]";
	}
}
