package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 采集设备软件更新通知
 * 
 * @author z1979
 */
public class CdSoftwareUpdateNotify extends AbstractReqPackage {

	protected static final int SOFTWARE_VERSION_LENGTH = 9;

	public static final int FILE_MD5_LENGTH = 16;

	public static final int DOWNLOAD_USER_ACCOUNT_LENGTH = 30;

	public static final int DOWNLOAD_USER_PASSWORD_LENGTH = 30;

	/** 软件名字长度. */
	private ShortPdu softwareNameLength = new ShortPdu();

	/** 软件名字. */
	private StringPdu softwareName = new StringPdu();

	/** 版本. */
	private StringPdu softwareVersion = new StringPdu();

	/** 文件MD5 16. */
	private StringPdu fileMd5 = new StringPdu();

	/** 传输方式 1. */
	private ShortPdu transferType = new ShortPdu((short) 0);

	/** 文件名字长度 1. */
	private ShortPdu fileNameLength = new ShortPdu();

	/** 文件名字 FileNameLength确定. */
	private StringPdu fileName = new StringPdu();

	/** 文件路径长度 1. */
	private ShortPdu filePathLength = new ShortPdu();

	/** 文件路径 FileNameLength确定. */
	private StringPdu filePath = new StringPdu();

	/** 文件长度（Byte） 4. */
	private LongPdu fileLength = new LongPdu();

	/**
	 * 下载服务器信息
	 */
	private ServerConnectionInfoPdu serverConnectionInfo;

	/** 下载用户名. */
	private StringPdu downloadUserAccount = new StringPdu();

	/** 下载密码. */
	private StringPdu downloadUserPassword = new StringPdu();
	/** 软件类型 长度 1 */
	private ShortPdu softType = new ShortPdu();

	public StringPdu getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(StringPdu softwareName) {
		this.softwareName = softwareName;
	}

	public StringPdu getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(StringPdu softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public ShortPdu getSoftwareNameLength() {
		return softwareNameLength;
	}

	public void setSoftwareNameLength(ShortPdu softwareNameLength) {
		this.softwareNameLength = softwareNameLength;
	}

	public StringPdu getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(StringPdu fileMd5) {
		this.fileMd5 = fileMd5;
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

	public LongPdu getFileLength() {
		return fileLength;
	}

	public void setFileLength(LongPdu fileLength) {
		this.fileLength = fileLength;
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

	public ShortPdu getSoftType() {
		return softType;
	}

	public void setSoftType(ShortPdu softType) {
		this.softType = softType;
	}

	public CdSoftwareUpdateNotify() {
		this.header.setMsgType(Commands.CD_UPDATE_SOFTWARE_NOTIFY);
	}

	public CdSoftwareUpdateNotify(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		softwareNameLength = new ShortPdu((short) softwareName.getLength());
		ByteOps.addByteArray(data, softwareNameLength.getBytes(), index);
		index += softwareNameLength.getLength();
		ByteOps.addByteArray(data, softwareName.getBytes(), index);
		index += softwareName.getLength();
		ByteOps.addByteArray(data, softwareVersion.getBytes(), index);
		index += softwareVersion.getLength();
		ByteOps.addByteArray(data, fileMd5.getBytes(), index);
		index += fileMd5.getLength();
		ByteOps.addByteArray(data, transferType.getBytes(), index);
		index += transferType.getLength();
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
		ByteOps.addByteArray(data, fileLength.getBytes(), index);
		index += fileLength.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
		ByteOps.addByteArray(data, downloadUserAccount.getBytes(), index);
		index += downloadUserAccount.getLength();
		ByteOps.addByteArray(data, downloadUserPassword.getBytes(), index);
		index += downloadUserPassword.getLength();
		ByteOps.addByteArray(data, softType.getBytes(), index);
		index += softType.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {

		softwareNameLength = new ShortPdu(index, data);
		index += softwareNameLength.getLength();
		softwareName = new StringPdu(softwareNameLength.getValue(), index, data);
		index += softwareName.getLength();
		softwareVersion = new StringPdu(SOFTWARE_VERSION_LENGTH, index, data);
		index += SOFTWARE_VERSION_LENGTH;
		fileMd5 = new StringPdu(FILE_MD5_LENGTH, index, data);
		index += FILE_MD5_LENGTH;
		transferType = new ShortPdu(index, data);
		index += transferType.getLength();
		fileNameLength = new ShortPdu(index, data);
		index += fileNameLength.getLength();
		fileName = new StringPdu(fileNameLength.getValue(), index, data);
		index += fileName.getLength();
		filePathLength = new ShortPdu(index, data);
		index += filePathLength.getLength();
		filePath = new StringPdu(filePathLength.getValue(), index, data);
		index += filePath.getLength();
		fileLength = new LongPdu(index, data);
		index += fileLength.getLength();
		serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		index += serverConnectionInfo.getLength();
		downloadUserAccount = new StringPdu(DOWNLOAD_USER_ACCOUNT_LENGTH,
				index, data);
		index += DOWNLOAD_USER_ACCOUNT_LENGTH;
		downloadUserPassword = new StringPdu(DOWNLOAD_USER_PASSWORD_LENGTH,
				index, data);
		index += DOWNLOAD_USER_PASSWORD_LENGTH;
		softType = new ShortPdu(index, data);
		index += softType.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + +this.softwareNameLength.getLength()
				+ this.softwareName.getLength() + SOFTWARE_VERSION_LENGTH
				+ FILE_MD5_LENGTH + this.transferType.getLength()
				+ this.fileNameLength.getLength() + this.fileName.getLength()
				+ this.filePathLength.getLength() + this.filePath.getLength()
				+ this.serverConnectionInfo.getLength()
				+ this.fileLength.getLength() + DOWNLOAD_USER_ACCOUNT_LENGTH
				+ DOWNLOAD_USER_PASSWORD_LENGTH + this.softType.getLength();
	}

	@Override
	public String toString() {
		return "CdSoftwareUpdateNotify [softwareNameLength="
				+ softwareNameLength + ", softwareName=" + softwareName
				+ ", softwareVersion=" + softwareVersion + ", fileMd5="
				+ fileMd5 + ", transferType=" + transferType
				+ ", fileNameLength=" + fileNameLength + ", fileName="
				+ fileName + ", filePathLength=" + filePathLength
				+ ", filePath=" + filePath + ", fileLength=" + fileLength
				+ ", serverConnectionInfo=" + serverConnectionInfo
				+ ", downloadUserAccount=" + downloadUserAccount
				+ ", downloadUserPassword=" + downloadUserPassword
				+ ", softType=" + softType + "]";
	}

	public static void main(String[] args) {
		CdSoftwareUpdateNotify req = new CdSoftwareUpdateNotify();
		StringPdu softwareName = new StringPdu(28, "010270184D800171");
		req.setSoftwareName(softwareName);
		StringPdu softwareVersion = new StringPdu(9, "V11B11D11");
		req.setSoftwareVersion(softwareVersion);
		StringPdu fileMd5 = new StringPdu(16, "1234567890123456");
		req.setFileMd5(fileMd5);
		StringPdu fileName = new StringPdu(7, "test.so");
		req.setFileName(fileName);
		StringPdu filePath = new StringPdu(9, "/test/11/");
		req.setFilePath(filePath);
		LongPdu fileLength = new LongPdu(100);
		req.setFileLength(fileLength);
		req.setTransferType(new ShortPdu((short) 1));
		int[] host = { 192, 168, 60, 47 };
		ServerConnectionInfoPdu pdu = new ServerConnectionInfoPdu(host, 6397);
		req.setServerConnectionInfo(pdu);
		StringPdu userAccount = new StringPdu(DOWNLOAD_USER_ACCOUNT_LENGTH,
				"test");
		req.setDownloadUserAccount(userAccount);
		StringPdu userPassword = new StringPdu(DOWNLOAD_USER_PASSWORD_LENGTH,
				"123456");
		req.setDownloadUserPassword(userPassword);
		req.setSoftType(new ShortPdu((short) 1));
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		CdSoftwareUpdateNotify req1 = new CdSoftwareUpdateNotify(req.getData());
		try {
			System.out.println(req.getData().length);
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
