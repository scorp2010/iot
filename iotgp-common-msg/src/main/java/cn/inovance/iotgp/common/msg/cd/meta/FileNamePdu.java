package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class FileNamePdu extends PDU {

	/** 文件名字长度 */
	private ShortPdu fileNameLength = new ShortPdu();
	/** 文件名字 */
	private StringPdu fileName = new StringPdu();

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

	public FileNamePdu(String fileName) {
		this.fileNameLength = new ShortPdu((short) fileName.getBytes().length);
		this.fileName = new StringPdu(fileName);
		this.dataLenth = getLength();
	}

	public FileNamePdu(byte[] data) throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public FileNamePdu(int startIndex, byte[] data)
			throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			fileNameLength = new ShortPdu(startIndex, data);
			startIndex += fileNameLength.getLength();
			fileName = new StringPdu(fileNameLength.getValue(), startIndex,
					data);
			startIndex += fileName.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public int getLength() {
		return fileNameLength.getLength() + fileName.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, fileNameLength.getBytes(), index);
		index += fileNameLength.getLength();
		ByteOps.addByteArray(bytes, fileName.getBytes(), index);
		index += fileName.getLength();
		return bytes;
	}

	@Override
	public String toString() {
		return "FileNamePdu [fileNameLength=" + fileNameLength + ", fileName="
				+ fileName + "]";
	}

	public static void main(String[] args) {
		String fileName = "fileXXX.txt";
		FileNamePdu pdu = new FileNamePdu(fileName);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));

		try {
			FileNamePdu pdu2 = new FileNamePdu(pdu.getBytes());
			System.out.println(pdu2.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getValue() {
		return fileName;
	}

}
