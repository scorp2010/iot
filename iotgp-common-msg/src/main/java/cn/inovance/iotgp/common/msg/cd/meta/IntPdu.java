package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class IntPdu extends PDU {

	private int value = 0;

	public IntPdu(int value) {
		this.dataLenth = 2;
		this.value = value;
	}

	public IntPdu() {
		this.dataLenth = 2;
	}

	public IntPdu(int startIndex, byte[] data) throws MessageParseException {
		this.dataLenth = 2;
		this.startIndex = startIndex;
		try {
			value = ByteOps.makeInt(data[startIndex], data[startIndex + 1]);
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

		return ByteOps.int2ByteArray(value, this.dataLenth);
	}

}
