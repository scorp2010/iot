package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.GftpTransparentUpMsg;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class LoginReq extends GftpCommand {

	public final static int GFTP_USERNAME_LENGTH = 30;
	public final static int GFTP_PASSWORD_LENGTH = 30;

	/** 下载用户名. */
	private StringPdu userAccount = new StringPdu();

	/** 下载密码. */
	private StringPdu userPassword = new StringPdu();

	public StringPdu getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(StringPdu userAccount) {
		this.userAccount = userAccount;
	}

	public StringPdu getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(StringPdu userPassword) {
		this.userPassword = userPassword;
	}

	public LoginReq(String userAccount, String userPassord) {
		this.commandid = new ShortPdu(GFtpCommands.LOGIN_REQ);
		this.userAccount = new StringPdu(GFTP_USERNAME_LENGTH, userAccount);
		this.userPassword = new StringPdu(GFTP_PASSWORD_LENGTH, userPassord);
		this.dataLenth = getLength();
	}

	public LoginReq(byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	public LoginReq(int startIndex, byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	private void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			commandid = new ShortPdu(startIndex, data);
			startIndex += commandid.getLength();
			userAccount = new StringPdu(GFTP_USERNAME_LENGTH, startIndex, data);
			startIndex += userAccount.getLength();
			userPassword = new StringPdu(GFTP_PASSWORD_LENGTH, startIndex, data);
			startIndex += userPassword.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public Object getValue() {

		return "LoginReq [userAccount=" + userAccount.getValue()
				+ ", userPassword=" + userPassword.getValue() + "]";
	}

	@Override
	public int getLength() {
		return commandid.getLength() + GFTP_USERNAME_LENGTH
				+ GFTP_PASSWORD_LENGTH;
	}

	@Override
	public byte[] getBytes() {

		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, commandid.getBytes(), index);
		index += commandid.getLength();
		ByteOps.addByteArray(bytes, userAccount.getBytes(), index);
		index += userAccount.getLength();
		ByteOps.addByteArray(bytes, userPassword.getBytes(), index);
		index += userPassword.getLength();
		return bytes;
	}

	public static void main(String[] args) {
		LoginReq req = new LoginReq("test", "123456");
		GftpTransparentUpMsg publishNotify = new GftpTransparentUpMsg(
				req.getBytes().length);
		publishNotify.setGftpCommand(req.getBytes());
		publishNotify.construct();

		System.out.println(publishNotify.toString());
		System.out.println(req.toString());

		GftpTransparentUpMsg publishNotify1 = new GftpTransparentUpMsg(
				publishNotify.getData());
		try {
			publishNotify1.parse();
			LoginReq req1 = new LoginReq(publishNotify1.getGftpCommand());
			System.out.println(publishNotify1.toString());
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();

		}
	}

}
