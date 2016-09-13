package cn.inovance.iotgp.common.msg.cd.gftp;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 文件数据传输
 * 
 * @author c2100
 * 
 */
public class FileDataTransfer extends GftpCommand {

	/** 当前包传输文件字节起始位置. */
	private LongPdu fileOffset = new LongPdu();

	/** 当前包传输文件数据字节数. */
	private LongPdu fileDataLenth = new LongPdu();

	/** 文件数据 */
	private byte[] fileData;

	public LongPdu getFileOffset() {
		return fileOffset;
	}

	public void setFileOffset(LongPdu fileOffset) {
		this.fileOffset = fileOffset;
	}

	public LongPdu getFileDataLenth() {
		return fileDataLenth;
	}

	public void setFileDataLenth(LongPdu fileDataLenth) {
		this.fileDataLenth = fileDataLenth;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public FileDataTransfer(long fileOffset, byte[] fileData) {
		this.commandid = new ShortPdu(GFtpCommands.FILE_DATA_TRANSFER);
		this.fileOffset = new LongPdu(fileOffset);
		this.fileDataLenth = new LongPdu(fileData.length);
		this.fileData = fileData;
		this.dataLenth = getLength();
	}

	public FileDataTransfer(byte[] data) throws MessageParseException {
		parseData(startIndex,data);
	}

	public FileDataTransfer(int startIndex, byte[] data)
			throws MessageParseException {
		parseData(startIndex,data);
	}

	private void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			commandid = new ShortPdu(startIndex, data);
			startIndex += commandid.getLength();
			fileOffset = new LongPdu(startIndex, data);
			startIndex += fileOffset.getLength();
			fileDataLenth = new LongPdu(startIndex, data);
			startIndex += fileDataLenth.getLength();
			fileData = ByteOps.cpByteArray(data, startIndex,
					Integer.parseInt(fileDataLenth.toString()));
			startIndex += fileData.length;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}
	
	@Override
	public Object getValue() {
		return "FileDataTransfer [fileOffset=" + fileOffset.getValue()
				+ ", fileDataLenth=" + fileDataLenth.getValue() + ", fileData="
				+ ByteOps.bytes2HexStringWithBlank(fileData) + "]";
	}

	@Override
	public int getLength() {
		return commandid.getLength() + fileOffset.getLength()
				+ fileDataLenth.getLength() + fileData.length;
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, commandid.getBytes(), index);
		index += commandid.getLength();
		ByteOps.addByteArray(bytes, fileOffset.getBytes(), index);
		index += fileOffset.getLength();
		ByteOps.addByteArray(bytes, fileDataLenth.getBytes(), index);
		index += fileDataLenth.getLength();
		ByteOps.addByteArray(bytes, fileData, index);
		index += fileData.length;
		return bytes;
	}

	@Override
	public String toString() {
		return "FileDataTransfer [fileOffset=" + fileOffset.getValue()
				+ ", fileDataLenth=" + fileDataLenth.getValue() + ", fileData="
				+ Arrays.toString(fileData) + "]";
	}

}
