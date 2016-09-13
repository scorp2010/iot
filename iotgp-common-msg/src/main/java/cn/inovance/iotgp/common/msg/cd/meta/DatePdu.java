package cn.inovance.iotgp.common.msg.cd.meta;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.DateUtil;

public class DatePdu extends PDU {

	private final int dataLenth = 4;

	private Date value = new Date();

	private byte[] bytes = new byte[dataLenth];

	private IntPdu year = new IntPdu();

	private ShortPdu month = new ShortPdu();

	private ShortPdu day = new ShortPdu();

	public DatePdu(Date value) {
		this.value = value;
		Calendar cal = Calendar.getInstance();
		cal.setTime(value);
		year = new IntPdu(cal.get(Calendar.YEAR));
		month = new ShortPdu((short) (cal.get(Calendar.MONTH) + 1));
		day = new ShortPdu((short) cal.get(Calendar.DAY_OF_MONTH));
	}

	public DatePdu(byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}
	
	public DatePdu(int startIndex, byte[] data) throws MessageParseException {
		parseData(startIndex, data);
	}

	public void parseData(int startIndex, byte[] data)
			throws MessageParseException {
		this.startIndex = startIndex;
		try {
			year = new IntPdu(startIndex, data);
			startIndex += year.getLength();
			month = new ShortPdu(startIndex, data);
			startIndex += month.getLength();
			day = new ShortPdu(startIndex, data);
			startIndex += day.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public Date getValue() {
		String strDate = year.toString() + "-" + month.toString() + "-"
				+ day.toString(); // 格式yyyy-MM-dd
		try {
			value = DateUtil.convertStringToDate("yyyy-MM-dd", strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public int getLength() {
		return dataLenth;
	}

	@Override
	public byte[] getBytes() {
		int index = 0;
		ByteOps.addByteArray(bytes, year.getBytes(), index);
		index += year.getLength();
		ByteOps.addByteArray(bytes, month.getBytes(), index);
		index += month.getLength();
		ByteOps.addByteArray(bytes, day.getBytes(), index);
		index += day.getLength();
		return bytes;
	}

	@Override
	public String toString() {
		return "DataPdu [value=" + value + "]";
	}

	public static void main(String[] args) {
		DatePdu pdu = new DatePdu(new Date());
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			DatePdu pdu1 = new DatePdu(0, pdu.getBytes());
			System.out.println(pdu1.getValue());
		} catch (MessageParseException e) {
			e.printStackTrace();

		}
	}

}
