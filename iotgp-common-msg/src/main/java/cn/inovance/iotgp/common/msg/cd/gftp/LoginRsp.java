package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class LoginRsp extends GftpCommand {

	public final static short ERROR_INTERNAL = 1;
	public final static short ERROR_USERNAME_PASSWORD_INVALID = 2;
	
	/** 错误码 */
	private ShortPdu errorCode = new ShortPdu((short) 0);

	public ShortPdu getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(short errorCode) {
		this.errorCode = new ShortPdu(errorCode);
	}

	public LoginRsp() {
		this.commandid = new ShortPdu(GFtpCommands.LOGIN_RSP);
		this.dataLenth = getLength();
	}
	
	public LoginRsp(byte[] data) throws MessageParseException {
		parseData(startIndex,data);
	}

	public LoginRsp(int startIndex, byte[] data) throws MessageParseException {
		parseData(startIndex,data);
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
		return "LoginRsp [errorCode=" + errorCode.getValue() + "]";
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
