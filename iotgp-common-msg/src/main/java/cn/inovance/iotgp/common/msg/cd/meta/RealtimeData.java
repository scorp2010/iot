package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.util.ByteOps;

public class RealtimeData {

	/** 实时数据编号 */
	private ShortPdu tag = new ShortPdu();
	/** 实时数据长度 */
	private IntPdu realtimeDataLength = new IntPdu();
	/** 实时数据 */
	private byte[] realtimeData = new byte[0];

	public ShortPdu getTag() {
		return tag;
	}

	public void setTag(short tag) {
		this.tag = new ShortPdu(tag);
	}
	
	public void setTag(ShortPdu tag) {
		this.tag = tag;
	}

	public IntPdu getRealtimeDataLength() {
		return realtimeDataLength;
	}

	public void setRealtimeDataLength(int realtimeDataLength) {
		this.realtimeDataLength = new IntPdu(realtimeDataLength);
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

	@Override
	public String toString() {
		return "RealtimeData [tag=" + tag + ", realtimeDataLength="
				+ realtimeDataLength + ", realtimeData="
				+ ByteOps.bytes2HexStringWithBlank(realtimeData) + "]";
	}
}
