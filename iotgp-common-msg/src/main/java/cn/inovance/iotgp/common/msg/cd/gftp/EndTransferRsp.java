package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class EndTransferRsp extends GftpCommand {

	public final static short ERROR_INTERNAL = 1;
	public final static short ERROR_FILE_DATA_INVALID = 2;
	public final static short ERROR_FILE_SAVE_FAIL = 3;
	
	/** 错误码 */
	private ShortPdu errorCode = new ShortPdu((short) 0);

	public ShortPdu getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(short errorCode) {
		this.errorCode = new ShortPdu(errorCode);
	}

	public EndTransferRsp() {
		this.commandid = new ShortPdu(GFtpCommands.END_TRANSFER_RSP);
		this.dataLenth = getLength();
	}

	public EndTransferRsp(byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	public EndTransferRsp(int startIndex, byte[] data)
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public Object getValue() {
		return "EndTransferRsp [errorCode=" + errorCode.getValue() + "]";
	}

	@Override
	public int getLength() {
		return commandid.getLength() + errorCode.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, commandid.getBytes(), index);
		index += commandid.getLength();
		ByteOps.addByteArray(bytes, errorCode.getBytes(), index);
		index += errorCode.getLength();
		return bytes;
	}

}
