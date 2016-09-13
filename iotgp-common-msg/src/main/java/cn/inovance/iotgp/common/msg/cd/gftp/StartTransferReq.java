package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 开始传输请求消息
 * 
 * @author z1979
 */
public class StartTransferReq extends GftpCommand {

	public static final short TRANSFER_TYPE_DOWNLOAD = 1;
	public static final short TRANSFER_TYPE_UPLOAD = 2;

	/** 文件传输类型：1-下载2-上传 */
	private ShortPdu transferType = new ShortPdu();

	/** 文件名字长度 1 */
	private ShortPdu fileNameLength = new ShortPdu();

	/** 文件名字 FileNameLength确定 */
	private StringPdu fileName = new StringPdu();

	/** 文件路径长度 1 */
	private ShortPdu filePathLength = new ShortPdu();

	/** 文件路径 FileNameLength确定 */
	private StringPdu filePath = new StringPdu();

	/** 已传输文件字节数（下载时有效） */
	private LongPdu fileOffset = new LongPdu();

	/** 文件总字节数（上传时有效） */
	private LongPdu fileLength = new LongPdu();

	public LongPdu getFileLength() {
		return fileLength;
	}

	public void setFileLength(LongPdu fileLength) {
		this.fileLength = fileLength;
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

	public LongPdu getFileOffset() {
		return fileOffset;
	}

	public void setFileOffset(LongPdu fileOffset) {
		this.fileOffset = fileOffset;
	}

	public StartTransferReq(short transferType, String fileName,
			String filePath, long fileOffset, long fileLength) {
		this.commandid = new ShortPdu(GFtpCommands.START_TRANSFER_REQ);
		this.transferType = new ShortPdu(transferType);
		this.fileNameLength = new ShortPdu((short) fileName.length());
		this.fileName = new StringPdu(fileName);
		this.filePathLength = new ShortPdu((short) filePath.length());
		this.filePath = new StringPdu(filePath);
		this.fileOffset = new LongPdu(fileOffset);
		this.fileLength = new LongPdu(fileLength);
		this.dataLenth = getLength();
	}

	public StartTransferReq(byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	public StartTransferReq(int startIndex, byte[] data)
			throws MessageParseException {
		parseData(startIndex, data);
	}

	private void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			commandid = new ShortPdu(startIndex, data);
			startIndex += commandid.getLength();
			transferType = new ShortPdu(startIndex, data);
			startIndex += commandid.getLength();
			fileNameLength = new ShortPdu(startIndex, data);
			startIndex += fileNameLength.getLength();
			fileName = new StringPdu(fileNameLength.getValue(), startIndex,
					data);
			startIndex += fileName.getLength();
			filePathLength = new ShortPdu(startIndex, data);
			startIndex += filePathLength.getLength();
			filePath = new StringPdu(filePathLength.getValue(), startIndex,
					data);
			startIndex += filePath.getLength();
			fileOffset = new LongPdu(startIndex, data);
			startIndex += fileOffset.getLength();
			fileLength = new LongPdu(startIndex, data);
			startIndex += fileLength.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public Object getValue() {
		return "StartTransferReq [transferType=" + transferType.getValue()
				+ ", fileNameLength=" + fileNameLength.getValue()
				+ ", fileName=" + fileName.getValue() + ", filePathLength="
				+ filePathLength.getValue() + ", filePath="
				+ filePath.getValue() + ", fileOffset=" + fileOffset.getValue()
				+ ", fileLength=" + fileLength.getValue() + "]";
	}

	@Override
	public int getLength() {
		return commandid.getLength() + transferType.getLength()
				+ fileNameLength.getLength() + fileName.getLength()
				+ filePathLength.getLength() + filePath.getLength()
				+ fileOffset.getLength() + fileLength.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, commandid.getBytes(), index);
		index += commandid.getLength();
		ByteOps.addByteArray(bytes, transferType.getBytes(), index);
		index += transferType.getLength();
		fileNameLength = new ShortPdu((short) fileName.getLength());
		ByteOps.addByteArray(bytes, fileNameLength.getBytes(), index);
		index += fileNameLength.getLength();
		ByteOps.addByteArray(bytes, fileName.getBytes(), index);
		index += fileName.getLength();
		filePathLength = new ShortPdu((short) filePath.getLength());
		ByteOps.addByteArray(bytes, filePathLength.getBytes(), index);
		index += filePathLength.getLength();
		ByteOps.addByteArray(bytes, filePath.getBytes(), index);
		index += filePath.getLength();
		ByteOps.addByteArray(bytes, fileOffset.getBytes(), index);
		index += fileOffset.getLength();
		ByteOps.addByteArray(bytes, fileLength.getBytes(), index);
		index += fileLength.getLength();
		return bytes;
	}

}
