package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class Header {
	private final int INDEX_PACKAGE_LENGTH=0;
	private final int INDEX_CRC=2;
	private final int INDEX_MSG_TYPE=4;
	private final int INDEX_MSG_SEQ=6;
	private final int INDEX_PROTOCOL_VERSION=10;
	private final int INDEX_ENCODE_VERSION=11;
	public static final int INDEX_EQUIPMENT_NO=12;
	private final int INDEX_SESSION_ID=28;
	
	public static final int LENGTH_EQUIPMENT_NO = 16;
	
	private static final int LENGTH_SESSION_ID = 18;
	
	public static final int LENGTH_HEADER = 46;
	
	private IntPdu packetLength;
	
	private IntPdu crc16;
	
	private IntPdu msgType;
	
	private LongPdu msgSeq;
	
	private ShortPdu protocolVersion;
	
	private ShortPdu encodeVersion;
	
	private StringPdu equipmentNo;
	
	private StringPdu sessionID;
	
	private byte[] data;
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Header(){
		this.packetLength = new IntPdu( LENGTH_HEADER );
		this.msgType = new IntPdu( 0x0000 );
		this.msgSeq = new LongPdu( SeqGenerator.next() );
		this.protocolVersion = new ShortPdu( (short)0x01 );
		this.encodeVersion = new ShortPdu( (short)0x01 );
		this.crc16 = new IntPdu( 0x0000 );
		this.equipmentNo = new StringPdu(Header.LENGTH_EQUIPMENT_NO, "");
		this.sessionID = new StringPdu(Header.LENGTH_SESSION_ID, "");
	}
	
	public Header(byte[] data){
		this.data = data;
	}
	
	public Header(byte[] data, int msgType){
		this.data = data;
		this.packetLength = new IntPdu( data.length );
		this.msgType = new IntPdu( msgType );
		this.msgSeq = new LongPdu( SeqGenerator.next() );
		this.protocolVersion = new ShortPdu( (short)0x01 );
		this.encodeVersion = new ShortPdu( (short)0x01 );
		this.crc16 = new IntPdu( 0x0000 );
		this.equipmentNo = new StringPdu(Header.LENGTH_EQUIPMENT_NO, "");
		this.sessionID = new StringPdu(Header.LENGTH_SESSION_ID, "");
	}
	
	public void construct(byte[] data){
		ByteOps.addByteArray(data, packetLength.getBytes(), INDEX_PACKAGE_LENGTH);
		ByteOps.addByteArray(data, crc16.getBytes(), INDEX_CRC);
		ByteOps.addByteArray(data, msgType.getBytes(), INDEX_MSG_TYPE);
		ByteOps.addByteArray(data, msgSeq.getBytes(), INDEX_MSG_SEQ);
		ByteOps.addByteArray(data, protocolVersion.getBytes(), INDEX_PROTOCOL_VERSION);
		ByteOps.addByteArray(data, encodeVersion.getBytes(), INDEX_ENCODE_VERSION);
		ByteOps.addByteArray(data, equipmentNo.getBytes(), INDEX_EQUIPMENT_NO);
		ByteOps.addByteArray(data, sessionID.getBytes(), INDEX_SESSION_ID);
	}
	
	public void parse() throws MessageParseException{
		packetLength = new IntPdu(this.INDEX_PACKAGE_LENGTH, data);
		crc16 = new IntPdu(this.INDEX_CRC, data);
		msgType = new IntPdu(this.INDEX_MSG_TYPE, data);
		msgSeq = new LongPdu(this.INDEX_MSG_SEQ, data);
		protocolVersion = new ShortPdu(this.INDEX_PROTOCOL_VERSION, data);
		encodeVersion = new ShortPdu(this.INDEX_ENCODE_VERSION, data);
		equipmentNo = new StringPdu(Header.LENGTH_EQUIPMENT_NO, Header.INDEX_EQUIPMENT_NO, data);
		sessionID = new StringPdu(Header.LENGTH_SESSION_ID, this.INDEX_SESSION_ID, data);
	}

	public long getMsgSeq() {
		return msgSeq.getValue();
	}

	public void setMsgSeq(long msgSeq) {
		this.msgSeq = new LongPdu( msgSeq );
	}

	public int getPacketLength() {
		return packetLength.getValue();
	}

	public void setPacketLength(int packetLength) {
		this.packetLength = new IntPdu(packetLength);
	}

	public int getCrc16() {
		return crc16.getValue();
	}

	public void setCrc16(int crc16) {
		this.crc16 = new IntPdu( crc16 );
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
		this.protocolVersion = new ShortPdu( protocolVersion );
	}

	public short getEncodeVersion() {
		return encodeVersion.getValue();
	}

	public void setEncodeVersion(short encodeVersion) {
		this.encodeVersion = new ShortPdu( encodeVersion );
	}

	public String getEquipmentNo() {
		return equipmentNo.getValue();
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = new StringPdu(Header.LENGTH_EQUIPMENT_NO, equipmentNo);
	}

	public String getSessionID() {
		return sessionID.getValue();
	}

	public void setSessionID(String sessionID) {
		this.sessionID = new StringPdu(Header.LENGTH_SESSION_ID, sessionID);
	}
	
	public Header clone(int commandid, byte[] data){
		Header newHeader = new Header();
		newHeader.setData(data);
		newHeader.setCrc16(this.crc16.getValue());
		newHeader.setEncodeVersion(this.encodeVersion.getValue());
		newHeader.setEquipmentNo(this.equipmentNo.getValue());
		newHeader.setMsgSeq(this.msgSeq.getValue());
		newHeader.setMsgType(commandid);
		newHeader.setPacketLength(data.length);
		newHeader.setProtocolVersion(this.protocolVersion.getValue());
		newHeader.setSessionID(this.sessionID.getValue());
		return newHeader;
	}
	
	public static void main(String[] args){
		Header header = new Header();
		header.setMsgType(Commands.LOGIN_REQ);
		header.setEquipmentNo("1234567890abcedf");
		header.setPacketLength(46);
		byte[] data = new byte[46];
		header.setData(data);
		header.construct(data);
		System.out.println(ByteOps.bytes2HexStringWithBlank(data));
	}

	@Override
	public String toString() {
		return "Header [packetLength=" + packetLength + ", crc16=" + crc16
				+ ", msgType=" + msgType + ", msgSeq=" + msgSeq
				+ ", protocolVersion=" + protocolVersion + ", encodeVersion="
				+ encodeVersion + ", equipmentNo=" + equipmentNo
				+ ", sessionID=" + sessionID + "]";
	}
	
}
