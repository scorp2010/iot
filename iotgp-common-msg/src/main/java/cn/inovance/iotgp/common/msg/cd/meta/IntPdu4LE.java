package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class IntPdu4LE extends PDU {

	private int value = 0;

	public IntPdu4LE(int value) {
		this.dataLenth = 2;
		this.value = value;
	}

	public IntPdu4LE() {
		this.dataLenth = 2;
	}

	public IntPdu4LE(int startIndex, byte[] data) throws MessageParseException {
		this.dataLenth = 2;
		this.startIndex = startIndex;
		try {
			value = ByteOps.makeInt(data[startIndex + 1], data[startIndex]);
		} catch (Exception e) {
			throw new MessageParseException();
		}
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public int getLength() {

		return dataLenth;
	}

	@Override
	public byte[] getBytes() {

		return ByteOps.int2ByteArrayIntel(value, this.dataLenth);
	}

}
