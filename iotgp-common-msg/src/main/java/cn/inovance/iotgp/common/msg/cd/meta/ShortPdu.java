package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class ShortPdu extends PDU {

	private short value = 0;

	public ShortPdu() {
		this.dataLenth = 1;
	}

	public ShortPdu(short value) {
		this.dataLenth = 1;
		this.value = value;
	}

	public ShortPdu(int startIndex, byte[] data) throws MessageParseException {
		this.dataLenth = 1;
		this.startIndex = startIndex;
		try {
			value = ByteOps.makeShort(data[startIndex]);
		} catch (Exception e) {
			throw new MessageParseException();
		}
	}

	@Override
	public Short getValue() {
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
