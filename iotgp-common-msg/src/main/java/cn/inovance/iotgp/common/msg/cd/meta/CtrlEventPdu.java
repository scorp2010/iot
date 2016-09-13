package cn.inovance.iotgp.common.msg.cd.meta;

import java.util.Date;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class CtrlEventPdu extends PDU {

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

	private CtrlEvent ctrlEvent;

	public ShortPdu getEventCode() {
		return eventCode;
	}

	public void setEventCode(ShortPdu eventCode) {
		this.eventCode = eventCode;
	}

	public ShortPdu getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(ShortPdu eventStatus) {
		this.eventStatus = eventStatus;
	}

	public IntPdu getEventDataLength() {
		return eventDataLength;
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

	public void setTimeStamp(LongPdu timeStamp) {
		this.timeStamp = timeStamp;
	}

	public CtrlEventPdu(CtrlEvent ctrlEvent) {
		this.ctrlEvent = ctrlEvent;
		this.eventCode = ctrlEvent.getEventCode();
		this.eventData = ctrlEvent.getEventData();
		this.eventDataLength = ctrlEvent.getEventDataLength();
		this.eventStatus = ctrlEvent.getEventStatus();
		this.timeStamp = ctrlEvent.getTimeStamp();
		dataLenth = getLength();
	}

	public CtrlEventPdu(byte[] data) throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public CtrlEventPdu(int startIndex, byte[] data)
			throws MessageParseException {
		this.parseData(startIndex, data);
	}

	public void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.startIndex = startIndex;
		try {
			eventCode = new ShortPdu(startIndex, data);
			startIndex += eventCode.getLength();
			eventStatus = new ShortPdu(startIndex, data);
			startIndex += eventStatus.getLength();
			eventDataLength = new IntPdu(startIndex, data);
			startIndex += eventDataLength.getLength();
			if (eventDataLength.getValue() > 0) {
				eventData = ByteOps.cpByteArray(data, startIndex,
						eventDataLength.getValue());
			}
			startIndex += eventData.length;
			timeStamp = new LongPdu(startIndex, data);
			startIndex += timeStamp.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
		
		dataLenth = getLength();
	}

	@Override
	public Object getValue() {
		return ctrlEvent;
	}

	@Override
	public int getLength() {
		return this.eventCode.getLength() + this.eventStatus.getLength()
				+ this.eventDataLength.getLength() + this.eventData.length
				+ this.timeStamp.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, eventCode.getBytes(), index);
		index += eventCode.getLength();
		ByteOps.addByteArray(bytes, eventStatus.getBytes(), index);
		index += eventStatus.getLength();
		ByteOps.addByteArray(bytes, eventDataLength.getBytes(), index);
		index += eventDataLength.getLength();
		if (eventData.length > 0) {
			ByteOps.addByteArray(bytes, eventData, index);
			index += eventData.length;
		}
		ByteOps.addByteArray(bytes, timeStamp.getBytes(), index);
		index += timeStamp.getLength();
		return bytes;
	}

	@Override
	public String toString() {
		return "CtrlEventPdu [eventCode=" + eventCode + ", eventStatus="
				+ eventStatus + ", eventDataLength=" + eventDataLength
				+ ", eventData=" + ByteOps.bytes2HexStringWithBlank(eventData)
				+ ", timeStamp=" + timeStamp + "]";
	}

	public static void main(String[] args) {
		CtrlEvent ctrlEvent = new CtrlEvent();
		ctrlEvent.setEventCode((short) 6);
		ctrlEvent.setEventStatus((short) 1);
		ctrlEvent.setEventDataLength(1);
		byte[] eventData = new byte[1];
		eventData[0] = 0x62;
		ctrlEvent.setEventData(eventData);
		ctrlEvent.setTimeStamp(new Date().getTime());
		System.out.println(ctrlEvent.toString());
		CtrlEventPdu pdu = new CtrlEventPdu(ctrlEvent);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			CtrlEventPdu pdu1 = new CtrlEventPdu(0, pdu.getBytes());
			System.out
					.println(ByteOps.bytes2HexStringWithBlank(pdu1.getBytes()));
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}
}
