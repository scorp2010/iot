package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 开始传输响应消息
 * 
 * @author z1979
 */
public class StartTransferRsp extends GftpCommand {

	public final static short ERROR_INTERNAL = 1;
	public final static short ERROR_NOT_LOGIN = 2;
	public final static short ERROR_FILE_NOT_EXIST = 3;

	/** 错误码. */
	private ShortPdu errorCode = new ShortPdu((short) 0);
	/** 已传输文件字节数（上传时有效） */
	private LongPdu fileOffset = new LongPdu(0l);
	/** 文件总字节数 . */
	private LongPdu fileLength = new LongPdu(0l);

	public ShortPdu getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(short errorCode) {
		this.errorCode = new ShortPdu(errorCode);
	}

	public LongPdu getFileOffset() {
		return fileOffset;
	}

	public void setFileOffset(long fileOffset) {
		this.fileOffset = new LongPdu(fileOffset);
	}

	public LongPdu getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = new LongPdu(fileLength);
	}

	public StartTransferRsp() {
		this.commandid = new ShortPdu(GFtpCommands.START_TRANSFER_RSP);
		this.dataLenth = getLength();
	}

	public StartTransferRsp(byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	public StartTransferRsp(int startIndex, byte[] data)
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
			errorCode = new ShortPdu(startIndex, data);
			startIndex += errorCode.getLength();
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
		return "StartTransferRsp [errorCode=" + errorCode.getValue()
				+ ", fileOffset=" + fileOffset.getValue() + ", fileLength="
				+ fileLength.getValue() + "]";
	}

	@Override
	public int getLength() {
		return commandid.getLength() + errorCode.getLength()
				+ fileOffset.getLength() + fileLength.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, commandid.getBytes(), index);
		index += commandid.getLength();
		ByteOps.addByteArray(bytes, errorCode.getBytes(), index);
		index += errorCode.getLength();
		ByteOps.addByteArray(bytes, fileOffset.getBytes(), index);
		index += fileOffset.getLength();
		ByteOps.addByteArray(bytes, fileLength.getBytes(), index);
		index += fileLength.getLength();
		return bytes;
	}

}
