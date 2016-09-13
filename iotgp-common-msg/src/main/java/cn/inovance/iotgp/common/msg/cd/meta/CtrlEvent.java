package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.util.ByteOps;

public class CtrlEvent {

	/** 事件编码 */
	private ShortPdu eventCode = new ShortPdu();
	/** 事件状态 */
	private ShortPdu eventStatus = new ShortPdu();
	/** 事件数据长度 */
	private IntPdu eventDataLength = new IntPdu();
	/** 事件数据 */
	private byte[] eventData = new byte[0];
	/** 时间戳 */
	private LongPdu timeStamp = new LongPdu();

	public ShortPdu getEventCode() {
		return eventCode;
	}

	public void setEventCode(short eventCode) {
		this.eventCode = new ShortPdu(eventCode);
	}

	public void setEventCode(ShortPdu eventCode) {
		this.eventCode = eventCode;
	}

	public ShortPdu getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(short eventStatus) {
		this.eventStatus = new ShortPdu(eventStatus);
	}

	public void setEventStatus(ShortPdu eventStatus) {
		this.eventStatus = eventStatus;
	}

	public IntPdu getEventDataLength() {
		return eventDataLength;
	}

	public void setEventDataLength(int eventDataLength) {
		this.eventDataLength = new IntPdu(eventDataLength);
	}

	public void setEventDataLength(IntPdu eventDataLength) {
		this.eventDataLength = eventDataLength;
	}

	public byte[] getEventData() {
		return eventData;
	}

	public void setEventData(byte[] eventData) {
		this.eventData = eventData;
	}

	public LongPdu getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = new LongPdu(timeStamp);
	}
	
	public void setTimeStamp(LongPdu timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "CtrlEvent [eventCode=" + eventCode + ", eventStatus="
				+ eventStatus + ", eventDataLength=" + eventDataLength
				+ ", eventData=" + ByteOps.bytes2HexStringWithBlank(eventData)
				+ ", timeStamp=" + timeStamp + "]";
	}

}
