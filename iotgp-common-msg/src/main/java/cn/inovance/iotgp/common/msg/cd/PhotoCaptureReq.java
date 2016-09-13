package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

/**
 * 摄像头抓拍请求
 * 
 * @author t2129
 *
 */
public class PhotoCaptureReq extends AbstractReqPackage {
	public static final int UPLOAD_USER_ACCOUNT_LENGTH = 100;

	public static final int UPLOAD_USER_PASSWORD_LENGTH = 32;

	/** 消息序号 */
	private ShortPdu msgSeq = new ShortPdu();
	/** 摄像头编号 */
	private ShortPdu cameraSerial = new ShortPdu();
	/** 抓拍间隔 */
	private ShortPdu captureInterval = new ShortPdu();
	/** 抓拍数量 */
	private ShortPdu captureNumber = new ShortPdu();
	/** 文件路径长度 */
	private ShortPdu filePathLength = new ShortPdu();
	/** 文件路径 */
	private StringPdu filePath = new StringPdu();
	/** 传输方式 0：FTP 1：GFTP */
	private ShortPdu transferType = new ShortPdu();
	/** 文件服务器地址 */
	private ServerConnectionInfoPdu serverConnectionInfo;
	/** 上传用户名 */
	private StringPdu uploadUserAccount = new StringPdu();
	/** 上传密码 */
	private StringPdu uploadUserPassword = new StringPdu();

	public ShortPdu getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(ShortPdu msgSeq) {
		this.msgSeq = msgSeq;
	}

	public ShortPdu getCameraSerial() {
		return cameraSerial;
	}

	public void setCameraSerial(ShortPdu cameraSerial) {
		this.cameraSerial = cameraSerial;
	}

	public ShortPdu getCaptureInterval() {
		return captureInterval;
	}

	public void setCaptureInterval(ShortPdu captureInterval) {
		this.captureInterval = captureInterval;
	}

	public ShortPdu getCaptureNumber() {
		return captureNumber;
	}

	public void setCaptureNumber(ShortPdu captureNumber) {
		this.captureNumber = captureNumber;
	}

	public ShortPdu getFilePathLength() {
		return filePathLength;
	}

	public void setFilePathLength(ShortPdu filePathLength) {
		this.filePathLength = filePathLength;
	}

	public StringPdu getFilePath() {
		return filePath;
	}

	public void setFilePath(StringPdu filePath) {
		this.filePath = filePath;
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

	public PhotoCaptureReq() {
		this.header.setMsgType(Commands.PHOTO_CAPTURE_REQ);
		this.setMsgSeq(new ShortPdu(SeqGenerator.nextShort()));
	}

	public PhotoCaptureReq(byte[] data) {
		super(data);
	}
	
	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, msgSeq.getBytes(), index);
		index += msgSeq.getLength();
		ByteOps.addByteArray(data, cameraSerial.getBytes(), index);
		index += cameraSerial.getLength();
		ByteOps.addByteArray(data, captureInterval.getBytes(), index);
		index += captureInterval.getLength();
		ByteOps.addByteArray(data, captureNumber.getBytes(), index);
		index += captureNumber.getLength();
		filePathLength = new ShortPdu((short) filePath.getLength());
		ByteOps.addByteArray(data, filePathLength.getBytes(), index);
		index += filePathLength.getLength();
		ByteOps.addByteArray(data, filePath.getBytes(), index);
		index += filePath.getLength();
		ByteOps.addByteArray(data, transferType.getBytes(), index);
		index += transferType.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
		ByteOps.addByteArray(data, uploadUserAccount.getBytes(), index);
		index += uploadUserAccount.getLength();
		ByteOps.addByteArray(data, uploadUserPassword.getBytes(), index);
		index += uploadUserPassword.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		msgSeq = new ShortPdu(index, data);
		index += msgSeq.getLength();
		cameraSerial = new ShortPdu(index, data);
		index += cameraSerial.getLength();
		captureInterval = new ShortPdu(index, data);
		index += captureInterval.getLength();
		captureNumber = new ShortPdu(index, data);
		index += captureNumber.getLength();
		filePathLength = new ShortPdu(index, data);
		index += filePathLength.getLength();
		filePath = new StringPdu(filePathLength.getValue(), index, data);
		index += filePath.getLength();
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
	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + this.getMsgSeq().getLength()
				+ this.getCameraSerial().getLength()
				+ this.getCaptureInterval().getLength()
				+ this.getCaptureNumber().getLength()
				+ this.getFilePathLength().getLength()
				+ this.getFilePath().getLength()
				+ this.getTransferType().getLength()
				+ this.getServerConnectionInfo().getLength()
				+ UPLOAD_USER_ACCOUNT_LENGTH + UPLOAD_USER_PASSWORD_LENGTH;
		return length;
	}

	@Override
	public String toString() {
		return "PhotoCaptureReq [msgSeq=" + msgSeq + ", cameraSerial="
				+ cameraSerial + ", captureInterval=" + captureInterval
				+ ", captureNumber=" + captureNumber + ", filePathLength="
				+ filePathLength + ", filePath=" + filePath + ",transferType="
				+ transferType + ", serverConnectionInfo="
				+ serverConnectionInfo + ", uploadUserAccount="
				+ uploadUserAccount + ", uploadUserPassword="
				+ uploadUserPassword + "]";
	}

}
