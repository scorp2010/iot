package cn.inovance.iotgp.common.msg.cd;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * GFTP文件传输透传上行消息
 * 
 * @author c2100
 * 
 */
public class GftpTransparentUpMsg extends AbstractTransparentPackage {
	protected static final int INDEX_BODY_RESERVE = 7;

	public static int PACKAGE_LENGTH = 8; // 长度由具体透传的消息而定

	private byte[] gftpCommand; // 透传消息体

	public byte[] getGftpCommand() {
		return gftpCommand;
	}

	public void setGftpCommand(byte[] gftpCommand) {
		this.gftpCommand = gftpCommand;
	}

	public GftpTransparentUpMsg(Header header, int bodyLength) {
		PACKAGE_LENGTH = INDEX_BODY_RESERVE + bodyLength;
		data = new byte[PACKAGE_LENGTH];
		this.header = header.clone(Commands.TRNSPARENT_CD_TO_GFTP, data);
	}

	public GftpTransparentUpMsg(int bodyLength) {
		PACKAGE_LENGTH = INDEX_BODY_RESERVE + bodyLength;
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.TRNSPARENT_CD_TO_GFTP);
	}

	public GftpTransparentUpMsg(byte[] data) {
		PACKAGE_LENGTH = data.length;
		this.data = data;
		header.setData(data);
	}

	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, gftpCommand, INDEX_BODY_RESERVE);
	}

	@Override
	public void parseBody() throws MessageParseException {
		index = INDEX_BODY_RESERVE;
		gftpCommand = ByteOps.cpByteArray(data, index, data.length - index);
	}

	@Override
	public String toString() {
		return "GftpTransparentUpMsg [gftpCommand="
				+ Arrays.toString(gftpCommand) + "]";
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + gftpCommand.length;
	}
}
