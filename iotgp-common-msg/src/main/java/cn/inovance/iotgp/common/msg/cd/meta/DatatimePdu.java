/**
 * Project Name:cdag
 * File Name:DatatimePdu.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-4-14下午4:34:46
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd.meta;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.DateUtil;

/**
 * ClassName:DatatimePdu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-14 下午4:34:46 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class DatatimePdu extends PDU {

	@Override
	public String toString() {
		return "DatatimePdu [value=" + value + "]";
	}

	private final int dataLenth = 7;

	private Date value = new Date();

	private byte[] bytes = new byte[dataLenth];

	private IntPdu year = new IntPdu();

	private ShortPdu month = new ShortPdu();

	private ShortPdu day = new ShortPdu();

	private ShortPdu hour = new ShortPdu();

	private ShortPdu minite = new ShortPdu();

	private ShortPdu second = new ShortPdu();

	@Override
	public Date getValue() {
		// 格式yyyy-MM-dd HH:mm:ss
		String strDateTime = year.toString() + "-" + month.toString() + "-"
				+ day.toString() + " " + hour.toString() + ":"
				+ minite.toString() + ":" + second.toString();
		try {
			value = DateUtil.convertStringToDate(DateUtil.getDatePattern(),
					strDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public int getLength() {

		return dataLenth;
	}

	public DatatimePdu(Date value) {
		this.value = value;
		Calendar cal = Calendar.getInstance();
		cal.setTime(value);
		year = new IntPdu(cal.get(Calendar.YEAR));
		month = new ShortPdu((short) (cal.get(Calendar.MONTH) + 1));
		day = new ShortPdu((short) cal.get(Calendar.DAY_OF_MONTH));
		hour = new ShortPdu((short) cal.get(Calendar.HOUR_OF_DAY));
		minite = new ShortPdu((short) cal.get(Calendar.MINUTE));
		second = new ShortPdu((short) cal.get(Calendar.SECOND));
	}

	public DatatimePdu(int startIndex, byte[] data)
			throws MessageParseException {
		this.startIndex = startIndex;
		try {
			year = new IntPdu(startIndex, data);
			startIndex += year.getLength();
			month = new ShortPdu(startIndex, data);
			startIndex += month.getLength();
			day = new ShortPdu(startIndex, data);
			startIndex += day.getLength();
			hour = new ShortPdu(startIndex, data);
			startIndex += hour.getLength();
			minite = new ShortPdu(startIndex, data);
			startIndex += minite.getLength();
			second = new ShortPdu(startIndex, data);
			startIndex += second.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
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
		ByteOps.addByteArray(bytes, hour.getBytes(), index);
		index += hour.getLength();
		ByteOps.addByteArray(bytes, minite.getBytes(), index);
		index += minite.getLength();
		ByteOps.addByteArray(bytes, second.getBytes(), index);
		index += second.getLength();
		return bytes;
	}

	public static void main(String[] args) {
		DatatimePdu pdu = new DatatimePdu(new Date());
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			DatatimePdu pdu1 = new DatatimePdu(0, pdu.getBytes());
			System.out.println(pdu1.getValue());
		} catch (MessageParseException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
