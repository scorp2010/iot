package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.cd.meta.RealtimeData;
import cn.inovance.iotgp.common.msg.cd.meta.RealtimeDataPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class CtrlEventNotify extends AbstractReqPackage {

	/** 消息序号 */
	private LongPdu eventSeq = new LongPdu();
	/** 设备注册码. */
	private StringPdu deviceRegCode = new StringPdu();
	/** 目标设备编号 */
	private ShortPdu tdCode = new ShortPdu();

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

	/** 实时数据个数 */
	private ShortPdu realtimeDataCount = new ShortPdu();
	/** 实时数据列表 */
	private List<RealtimeDataPdu> realtimeDataList = null;

	public LongPdu getEventSeq() {
		return eventSeq;
	}

	public void setEventSeq(long eventSeq) {
		this.eventSeq = new LongPdu(eventSeq);
	}

	public void setEventSeq(LongPdu eventSeq) {
		this.eventSeq = eventSeq;
	}

	public short getTdCode() {
		return tdCode.getValue();
	}

	public void setTdCode(short tdCode) {
		this.tdCode = new ShortPdu(tdCode);
	}

	public void setTdCode(ShortPdu tdCode) {
		this.tdCode = tdCode;
	}

	public StringPdu getDeviceRegCode() {
		return deviceRegCode;
	}

	public void setDeviceRegCode(StringPdu deviceRegCode) {
		this.deviceRegCode = deviceRegCode;
	}

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

	public ShortPdu getRealtimeDataCount() {
		return realtimeDataCount;
	}

	public void setRealtimeDataCount(short realtimeDataCount) {
		this.realtimeDataCount = new ShortPdu(realtimeDataCount);
	}

	public void setRealtimeDataCount(ShortPdu realtimeDataCount) {
		this.realtimeDataCount = realtimeDataCount;
	}

	public List<RealtimeDataPdu> getRealtimeDataList() {
		return realtimeDataList;
	}

	public void setRealtimeDataList(List<RealtimeDataPdu> realtimeDataList) {
		this.realtimeDataList = realtimeDataList;
	}

	public CtrlEventNotify() {
		this.header.setMsgType(Commands.CTRL_EVENT_NOTIFY);
	}

	public CtrlEventNotify(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, eventSeq.getBytes(), index);
		index += eventSeq.getLength();
		ByteOps.addByteArray(data, deviceRegCode.getBytes(), index);
		index += deviceRegCode.getLength();
		ByteOps.addByteArray(data, tdCode.getBytes(), index);
		index += tdCode.getLength();

		ByteOps.addByteArray(data, eventCode.getBytes(), index);
		index += eventCode.getLength();
		ByteOps.addByteArray(data, eventStatus.getBytes(), index);
		index += eventStatus.getLength();
		ByteOps.addByteArray(data, eventDataLength.getBytes(), index);
		index += eventDataLength.getLength();
		ByteOps.addByteArray(data, eventData, index);
		index += eventData.length;
		ByteOps.addByteArray(data, timeStamp.getBytes(), index);
		index += timeStamp.getLength();

		ByteOps.addByteArray(data, realtimeDataCount.getBytes(), index);
		index += realtimeDataCount.getLength();
		for (RealtimeDataPdu each : realtimeDataList) {
			ByteOps.addByteArray(data, each.getBytes(), index);
			index += each.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		eventSeq = new LongPdu(index, data);
		index += eventSeq.getLength();
		deviceRegCode = new StringPdu(DeviceLoginReq.DEVICE_REG_CODE_LENGTH,
				index, data);
		index += deviceRegCode.getLength();
		tdCode = new ShortPdu(index, data);
		index += tdCode.getLength();

		eventCode = new ShortPdu(index, data);
		index += eventCode.getLength();
		eventStatus = new ShortPdu(index, data);
		index += eventStatus.getLength();
		eventDataLength = new IntPdu(index, data);
		index += eventDataLength.getLength();
		eventData = ByteOps
				.cpByteArray(data, index, eventDataLength.getValue());
		index += eventData.length;
		timeStamp = new LongPdu(index, data);
		index += timeStamp.getLength();

		realtimeDataCount = new ShortPdu(index, data);
		index += realtimeDataCount.getLength();
		realtimeDataList = new ArrayList<RealtimeDataPdu>();
		while (index < data.length) {
			RealtimeDataPdu each = new RealtimeDataPdu(index, data);
			index += each.getLength();
			realtimeDataList.add(each);
		}
	}

	@Override
	protected int getMsgLength() {
		int msgLength = Header.LENGTH_HEADER;
		msgLength += eventSeq.getLength();
		msgLength += deviceRegCode.getLength();
		msgLength += tdCode.getLength();

		msgLength += eventCode.getLength();
		msgLength += eventStatus.getLength();
		msgLength += eventDataLength.getLength();
		msgLength += eventData.length;
		msgLength += timeStamp.getLength();

		msgLength += realtimeDataCount.getLength();
		for (RealtimeDataPdu each : realtimeDataList) {
			msgLength += each.getLength();
		}
		return msgLength;
	}

	@Override
	public String toString() {
		return "CtrlEventNofity [msgSeq=" + eventSeq + ", deviceRegCode="
				+ deviceRegCode + ", tdCode=" + tdCode + ", eventCode="
				+ eventCode + ", eventStatus=" + eventStatus
				+ ", eventDataLength=" + eventDataLength + ", eventData="
				+ ByteOps.bytes2HexStringWithBlank(eventData) + ", timeStamp="
				+ timeStamp + ", realtimeDataList=" + realtimeDataList + "]";
	}

	public static void main(String[] args) {
		CtrlEventNotify req = new CtrlEventNotify();
		req.setEventSeq((short) 1);
		req.setDeviceRegCode(new StringPdu(28, "010270184D800171"));
		req.setTdCode((short) 1);

		req.setEventCode((short) 6);
		req.setEventStatus((short) 1);
		req.setEventDataLength(1);
		byte[] eventData = new byte[1];
		eventData[0] = 0x62;
		req.setEventData(eventData);
		req.setTimeStamp(new Date().getTime());

		List<RealtimeDataPdu> realtimeDataList = new ArrayList<RealtimeDataPdu>();

		RealtimeData realtimeData = new RealtimeData();
		realtimeData.setTag((short) 1);
		realtimeData.setRealtimeDataLength(1);
		byte[] rtData = new byte[1];
		rtData[0] = 0x62;
		realtimeData.setRealtimeData(rtData);
		realtimeDataList.add(new RealtimeDataPdu(realtimeData));

		realtimeData.setTag((short) 2);
		realtimeData.setRealtimeDataLength(0);
		rtData = new byte[0];
		realtimeData.setRealtimeData(rtData);
		realtimeDataList.add(new RealtimeDataPdu(realtimeData));

		req.setRealtimeDataCount((short) realtimeDataList.size());
		req.setRealtimeDataList(realtimeDataList);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		try {
			CtrlEventNotify req1 = new CtrlEventNotify(req.getData());
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}
}
