package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.enums.ResourceFileType;
import cn.inovance.iotgp.common.enums.TransferType;
import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class FileTransferNotify extends AbstractReqPackage {

	public static final int DOWNLOAD_USER_ACCOUNT_LENGTH = 30;
	public static final int DOWNLOAD_USER_PASSWORD_LENGTH = 30;

	public static final int UpDownType_DownLoad = 0;
	public static final int UpDownType_UpLoad = 1;

	/** 消息序号 */
	private ShortPdu seq = new ShortPdu();
	/** 传输方式 */
	private ShortPdu transferType = new ShortPdu(TransferType.FTP.value());
	/** 上/下载类型 */
	private ShortPdu upDownType = new ShortPdu((short) 0);
	/** 文件名字长度 */
	private ShortPdu fileNameLength = new ShortPdu();
	/** 文件名字 */
	private StringPdu fileName = new StringPdu();
	/** 文件路径长度 */
	private ShortPdu filePathLength = new ShortPdu();
	/** 文件路径 */
	private StringPdu filePath = new StringPdu();
	/** 资源文件类型 */
	private ShortPdu resourceType = new ShortPdu(ResourceFileType.Log.value());
	/** 服务器信息 */
	private ServerConnectionInfoPdu serverConnectionInfo;
	/** 用户名 */
	private StringPdu downloadUserAccount = new StringPdu();
	/** 密码 */
	private StringPdu downloadUserPassword = new StringPdu();

	public ShortPdu getSeq() {
		return seq;
	}

	public void setSeq(ShortPdu seq) {
		this.seq = seq;
	}

	public ShortPdu getTransferType() {
		return transferType;
	}

	public void setTransferType(ShortPdu transferType) {
		this.transferType = transferType;
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

	public ServerConnectionInfoPdu getServerConnectionInfo() {
		return serverConnectionInfo;
	}

	public void setServerConnectionInfo(
			ServerConnectionInfoPdu serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
	}

	public StringPdu getDownloadUserAccount() {
		return downloadUserAccount;
	}

	public void setDownloadUserAccount(StringPdu downloadUserAccount) {
		this.downloadUserAccount = downloadUserAccount;
	}

	public StringPdu getDownloadUserPassword() {
		return downloadUserPassword;
	}

	public void setDownloadUserPassword(StringPdu downloadUserPassword) {
		this.downloadUserPassword = downloadUserPassword;
	}

	public ShortPdu getUpDownType() {
		return upDownType;
	}

	public void setUpDownType(ShortPdu updownType) {
		this.upDownType = updownType;
	}

	public ShortPdu getResourceType() {
		return resourceType;
	}

	public void setResourceType(ShortPdu resourceType) {
		this.resourceType = resourceType;
	}

	public FileTransferNotify() {
		this.header.setMsgType(Commands.FILE_TRANSFER_NOTIFY);
	}

	public FileTransferNotify(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, transferType.getBytes(), index);
		index += transferType.getLength();
		ByteOps.addByteArray(data, upDownType.getBytes(), index);
		index += upDownType.getLength();
		fileNameLength = new ShortPdu((short) fileName.getLength());
		ByteOps.addByteArray(data, fileNameLength.getBytes(), index);
		index += fileNameLength.getLength();
		ByteOps.addByteArray(data, fileName.getBytes(), index);
		index += fileName.getLength();
		filePathLength = new ShortPdu((short) filePath.getLength());
		ByteOps.addByteArray(data, filePathLength.getBytes(), index);
		index += filePathLength.getLength();
		ByteOps.addByteArray(data, filePath.getBytes(), index);
		index += filePath.getLength();
		ByteOps.addByteArray(data, resourceType.getBytes(), index);
		index += resourceType.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
		ByteOps.addByteArray(data, downloadUserAccount.getBytes(), index);
		index += downloadUserAccount.getLength();
		ByteOps.addByteArray(data, downloadUserPassword.getBytes(), index);
		index += downloadUserPassword.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		transferType = new ShortPdu(index, data);
		index += transferType.getLength();
		upDownType = new ShortPdu(index, data);
		index += upDownType.getLength();
		fileNameLength = new ShortPdu(index, data);
		index += fileNameLength.getLength();
		fileName = new StringPdu(fileNameLength.getValue(), index, data);
		index += fileName.getLength();
		filePathLength = new ShortPdu(index, data);
		index += filePathLength.getLength();
		filePath = new StringPdu(filePathLength.getValue(), index, data);
		index += filePath.getLength();
		resourceType = new ShortPdu(index, data);
		index += resourceType.getLength();
		serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		index += serverConnectionInfo.getLength();
		downloadUserAccount = new StringPdu(DOWNLOAD_USER_ACCOUNT_LENGTH,
				index, data);
		index += DOWNLOAD_USER_ACCOUNT_LENGTH;
		downloadUserPassword = new StringPdu(DOWNLOAD_USER_PASSWORD_LENGTH,
				index, data);
		index += DOWNLOAD_USER_PASSWORD_LENGTH;
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.transferType.getLength()
				+ this.upDownType.getLength() + this.fileNameLength.getLength()
				+ this.fileName.getLength() + this.filePathLength.getLength()
				+ this.filePath.getLength() + this.resourceType.getLength()
				+ this.serverConnectionInfo.getLength()
				+ DOWNLOAD_USER_ACCOUNT_LENGTH + DOWNLOAD_USER_PASSWORD_LENGTH;
	}

	@Override
	public String toString() {
		return "FileTransferNofity [transferType=" + transferType
				+ ", upDownType=" + upDownType + ", fileNameLength="
				+ fileNameLength + ", fileName=" + fileName
				+ ", filePathLength=" + filePathLength + ", filePath="
				+ filePath + ", resourceType=" + resourceType
				+ ", serverConnectionInfo=" + serverConnectionInfo
				+ ", downloadUserAccount=" + downloadUserAccount
				+ ", downloadUserPassword=" + downloadUserPassword + "]";
	}

	public static void main(String[] args) {
		FileTransferNotify req = new FileTransferNotify();
		StringPdu fileName = new StringPdu("test.so");
		req.setFileName(fileName);
		StringPdu filePath = new StringPdu("/test/11/");
		req.setFilePath(filePath);
		int[] host = { 10, 44, 218, 35 };
		ServerConnectionInfoPdu pdu = new ServerConnectionInfoPdu(host, 6397);
		req.setServerConnectionInfo(pdu);
		StringPdu userAccount = new StringPdu(DOWNLOAD_USER_ACCOUNT_LENGTH,
				"test");
		req.setDownloadUserAccount(userAccount);
		StringPdu userPassword = new StringPdu(DOWNLOAD_USER_PASSWORD_LENGTH,
				"123456");
		req.setDownloadUserPassword(userPassword);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		FileTransferNotify req1 = new FileTransferNotify(req.getData());
		try {
			System.out.println(req.getData().length);
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
