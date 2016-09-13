package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.meta.TdTag;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class TdTagPdu extends PDU {

	/** Tag编号 */
	private ShortPdu tagId = new ShortPdu();
	/** 采样周期 */
	private IntPdu samplingPeriod = new IntPdu();

	public ShortPdu getTagId() {
		return tagId;
	}

	public void setTagId(ShortPdu tagId) {
		this.tagId = tagId;
	}

	public IntPdu getSamplingPeriod() {
		return samplingPeriod;
	}

	public void setSamplingPeriod(IntPdu samplingPeriod) {
		this.samplingPeriod = samplingPeriod;
	}

	public TdTagPdu(TdTag tdTag) {
		this.tagId = new ShortPdu(tdTag.getValue());
		this.samplingPeriod = new IntPdu(tdTag.getSamplingPeriod());
		this.dataLenth = getLength();
	}

	public TdTagPdu(ShortPdu tagId, IntPdu samplingPeriod) {
		this.tagId = tagId;
		this.samplingPeriod =samplingPeriod;
		this.dataLenth = getLength();
	}
	
	public TdTagPdu(short tagId, int samplingPeriod) {
		this.tagId = new ShortPdu(tagId);
		this.samplingPeriod = new IntPdu(samplingPeriod);
		this.dataLenth = getLength();
	}

	public TdTagPdu(byte[] data) throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public TdTagPdu(int startIndex, byte[] data) throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			tagId = new ShortPdu(startIndex, data);
			startIndex += tagId.getLength();
			samplingPeriod = new IntPdu(startIndex, data);
			startIndex += samplingPeriod.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public int getLength() {
		return tagId.getLength() + samplingPeriod.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, tagId.getBytes(), index);
		index += tagId.getLength();
		ByteOps.addByteArray(bytes, samplingPeriod.getBytes(), index);
		index += samplingPeriod.getLength();
		return bytes;
	}

	@Override
	public String toString() {
		return "TagPdu [tagId=" + tagId + ", samplingPeriod=" + samplingPeriod
				+ "]";
	}

	@Override
	public Object getValue() {
		return tagId;
	}

	public static void main(String[] args) {
		TdTagPdu pdu = new TdTagPdu((short) 1, 0);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));

		try {
			TdTagPdu pdu2 = new TdTagPdu(pdu.getBytes());
			System.out.println(pdu2.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
