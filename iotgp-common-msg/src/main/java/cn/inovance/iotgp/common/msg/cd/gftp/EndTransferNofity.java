package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 文件传输结束通知
 * 
 * @author c2100
 * 
 */
public class EndTransferNofity extends GftpCommand {

	/** 文件总字节数 */
	private LongPdu fileLength = new LongPdu();

	/** 文件累加校验和 */
	private LongPdu checksum = new LongPdu(0l);

	public LongPdu getFileLength() {
		return fileLength;
	}

	public void setFileLength(LongPdu fileLength) {
		this.fileLength = fileLength;
	}

	public LongPdu getChecksum() {
		return checksum;
	}

	public void setChecksum(LongPdu checksum) {
		this.checksum = checksum;
	}

	public EndTransferNofity(long fileLength, long checksum) {
		this.commandid = new ShortPdu(GFtpCommands.END_TRANSFER_NOTIFY);
		this.fileLength = new LongPdu(fileLength);
		this.checksum = new LongPdu(checksum);
		this.dataLenth = getLength();
	}

	public EndTransferNofity(byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	public EndTransferNofity(int startIndex, byte[] data)
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
			fileLength = new LongPdu(startIndex, data);
			startIndex += fileLength.getLength();
			checksum = new LongPdu(startIndex, data);
			startIndex += checksum.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public Object getValue() {
		return "EndTransferNofity [fileLength=" + fileLength.getValue()
				+ ", checksum=" + checksum.getValue() + "]";
	}

	@Override
	public int getLength() {
		return commandid.getLength() + fileLength.getLength()
				+ checksum.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, commandid.getBytes(), index);
		index += commandid.getLength();
		ByteOps.addByteArray(bytes, fileLength.getBytes(), index);
		index += fileLength.getLength();
		ByteOps.addByteArray(bytes, checksum.getBytes(), index);
		index += checksum.getLength();
		return bytes;
	}

}
