package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.FormatConvertor;

public class VarStringPdu extends PDU {

	private String value = "";

	public void setValue(String value) {
		this.value = value;
	}

	public VarStringPdu() {

	}

	public VarStringPdu(String value) {
		this.dataLenth = (value != null) ? value.length() : 0;
		this.value = value;
	}

	public VarStringPdu(int length, String value) {
		this.dataLenth = length;
		this.value = value;
	}

	public VarStringPdu(int length, int startIndex, byte[] data)
			throws MessageParseException {
		this.dataLenth = length;
		this.startIndex = startIndex;
		try {
			value = ByteOps.makeNormalStringFromBytes(data, startIndex, length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getLength() {

		return dataLenth;
	}

	@Override
	public byte[] getBytes() {
		String hexValue = FormatConvertor.encode(this.value);
		int tobeAddedEmptyBytesLenth = this.dataLenth * 2 - hexValue.length();
		for (int i = 0; i < tobeAddedEmptyBytesLenth; i++) {
			hexValue += "0";
		}
		return ByteOps.hexStringToBytes(hexValue);
	}
}
