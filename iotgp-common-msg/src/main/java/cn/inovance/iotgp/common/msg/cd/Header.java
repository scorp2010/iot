package cn.inovance.iotgp.common.msg.cd;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName: Header <br/>
 * Function: 采集设备通信消息头部. <br/>
 * date: 2014-4-8 下午4:21:00 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class Header {
	private static final int INDEX_PACKAGE_LENGTH = 0;
	private static final int INDEX_MSG_TYPE = 2;
	private static final int INDEX_PROTOCOL_VERSION = 4;
	private static final int INDEX_ENCODE_VERSION = 5;
	private static final int INDEX_RESERVE = 6;

	public static final int LENGTH_HEADER = 7;

	private IntPdu packetLength;

	private IntPdu msgType;

	private ShortPdu protocolVersion;

	private ShortPdu encodeVersion;

	private ShortPdu reserve;

	private byte[] data;

	public static int getPacketLength(byte[] data) throws MessageParseException {
		if (data != null && data.length < 2) {
			return 0;
		}
		IntPdu packetLength = new IntPdu(INDEX_PACKAGE_LENGTH, data);
		return packetLength.getValue();
	}

	public static int getCommandId(byte[] data) throws MessageParseException {
		if (data != null && data.length < LENGTH_HEADER) {
			return 0;
		}
		IntPdu msgType = new IntPdu(INDEX_MSG_TYPE, data);
		return msgType.getValue();
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Header() {
		this.packetLength = new IntPdu(LENGTH_HEADER);
		this.msgType = new IntPdu(0x0000);
		this.protocolVersion = new ShortPdu((short) 0x01);
		this.encodeVersion = new ShortPdu((short) 0x00);
		this.reserve = new ShortPdu((short) 0x00);
	}

	public Header(byte[] data) {
		this.data = data;
	}

	public Header(byte[] data, int msgType) {
		this.data = data;
		this.packetLength = new IntPdu(data.length);
		this.msgType = new IntPdu(msgType);
		this.protocolVersion = new ShortPdu((short) 0x01);
		this.encodeVersion = new ShortPdu((short) 0x00);
		this.reserve = new ShortPdu((short) 0x00);
	}

	public void construct(byte[] data) {
		packetLength = new IntPdu(data.length);
		ByteOps.addByteArray(data, packetLength.getBytes(),
				INDEX_PACKAGE_LENGTH);
		ByteOps.addByteArray(data, msgType.getBytes(), INDEX_MSG_TYPE);
		ByteOps.addByteArray(data, protocolVersion.getBytes(),
				INDEX_PROTOCOL_VERSION);
		ByteOps.addByteArray(data, encodeVersion.getBytes(),
				INDEX_ENCODE_VERSION);
		ByteOps.addByteArray(data, reserve.getBytes(), INDEX_RESERVE);
	}

	public void parse() throws MessageParseException {
		packetLength = new IntPdu(Header.INDEX_PACKAGE_LENGTH, data);
		msgType = new IntPdu(Header.INDEX_MSG_TYPE, data);
		protocolVersion = new ShortPdu(Header.INDEX_PROTOCOL_VERSION, data);
		encodeVersion = new ShortPdu(Header.INDEX_ENCODE_VERSION, data);
		reserve = new ShortPdu(Header.INDEX_RESERVE, data);
	}

	public int getPacketLength() {
		return packetLength.getValue();
	}

	public void setPacketLength(int packetLength) {
		this.packetLength = new IntPdu(packetLength);
	}

	public int getMsgType() {
		return msgType.getValue();
	}

	public void setMsgType(int msgType) {
		this.msgType = new IntPdu(msgType);
	}

	public short getProtocolVersion() {
		return protocolVersion.getValue();
	}

	public void setProtocolVersion(short protocolVersion) {
		this.protocolVersion = new ShortPdu(protocolVersion);
	}

	public short getEncodeVersion() {
		return encodeVersion.getValue();
	}

	public void setEncodeVersion(short encodeVersion) {
		this.encodeVersion = new ShortPdu(encodeVersion);
	}

	public short getReserve() {
		return reserve.getValue();
	}

	public void setReserve(short reserve) {
		this.reserve = new ShortPdu(reserve);
	}

	public Header clone(int commandid, byte[] data) {
		Header newHeader = new Header();
		newHeader.setData(data);
		newHeader.setEncodeVersion(this.encodeVersion.getValue());
		newHeader.setMsgType(commandid);
		newHeader.setPacketLength(data.length);
		newHeader.setProtocolVersion(this.protocolVersion.getValue());
		return newHeader;
	}

	public static void main(String[] args) {
		Header header = new Header();
		header.setMsgType(Commands.CD_HEARTBEAT_REQ);
		byte[] data = new byte[Header.LENGTH_HEADER];
		header.setData(data);
		header.construct(data);
		System.out.println(ByteOps.bytes2HexStringWithBlank(data));
	}

	@Override
	public String toString() {
		return "Header [msgType=" + msgType + ", protocolVersion="
				+ protocolVersion + ", encodeVersion=" + encodeVersion
				+ ", reserve=" + reserve + ", data=" + Arrays.toString(data)
				+ "]";
	}

}
