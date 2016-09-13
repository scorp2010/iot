package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class RealtimeDataPdu extends PDU {

	/** 实时数据编号 */
	private ShortPdu tag = new ShortPdu();
	/** 实时数据长度 */
	private IntPdu realtimeDataLength = new IntPdu();
	/** 实时数据 */
	private byte[] realtimeData = new byte[0];

	private RealtimeData value;

	public ShortPdu getTag() {
		return tag;
	}

	public void setTag(ShortPdu tag) {
		this.tag = tag;
	}

	public IntPdu getRealtimeDataLength() {
		return realtimeDataLength;
	}

	public void setRealtimeDataLength(IntPdu realtimeDataLength) {
		this.realtimeDataLength = realtimeDataLength;
	}

	public byte[] getRealtimeData() {
		return realtimeData;
	}

	public void setRealtimeData(byte[] realtimeData) {
		this.realtimeData = realtimeData;
	}

	public RealtimeDataPdu(RealtimeData realtimeData) {
		this.value = realtimeData;
		this.tag = realtimeData.getTag();
		this.realtimeDataLength = realtimeData.getRealtimeDataLength();
		this.realtimeData = realtimeData.getRealtimeData();
		dataLenth = getLength();
	}

	public RealtimeDataPdu(byte[] data) throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public RealtimeDataPdu(int startIndex, byte[] data)
			throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.startIndex = startIndex;
		try {
			tag = new ShortPdu(startIndex, data);
			startIndex += tag.getLength();
			realtimeDataLength = new IntPdu(startIndex, data);
			startIndex += realtimeDataLength.getLength();
			if (realtimeDataLength.getValue() > 0) {
				realtimeData = ByteOps.cpByteArray(data, startIndex,
						realtimeDataLength.getValue());
			}
			startIndex += realtimeData.length;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}

		dataLenth = getLength();
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public int getLength() {
		return this.tag.getLength() + this.realtimeDataLength.getLength()
				+ this.realtimeData.length;
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, tag.getBytes(), index);
		index += tag.getLength();
		ByteOps.addByteArray(bytes, realtimeDataLength.getBytes(), index);
		index += realtimeDataLength.getLength();
		if (realtimeData.length > 0) {
			ByteOps.addByteArray(bytes, realtimeData, index);
			index += realtimeData.length;
		}
		return bytes;
	}

	@Override
	public String toString() {
		return "RealtimeDataPdu [tag=" + tag + ", realtimeDataLength="
				+ realtimeDataLength + ", realtimeData="
				+ ByteOps.bytes2HexStringWithBlank(realtimeData) + "]";
	}

	public static void main(String[] args) {
		RealtimeData realtimeData = new RealtimeData();
		realtimeData.setTag((short) 6);
		realtimeData.setRealtimeDataLength(1);
		byte[] rtData = new byte[1];
		rtData[0] = 0x62;
		realtimeData.setRealtimeData(rtData);
		System.out.println(realtimeData.toString());
		RealtimeDataPdu pdu = new RealtimeDataPdu(realtimeData);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			RealtimeDataPdu pdu1 = new RealtimeDataPdu(0, pdu.getBytes());
			System.out
					.println(ByteOps.bytes2HexStringWithBlank(pdu1.getBytes()));
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}
}
