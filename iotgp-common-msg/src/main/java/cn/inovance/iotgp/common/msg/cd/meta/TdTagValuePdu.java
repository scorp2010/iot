/**
 * Project Name:iotgp-common-msg
 * File Name:TdTagValue.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-7-15上午11:56:58
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd.meta;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:TdTagValue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-15 上午11:56:58 <br/>
 * @author   z1979
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TdTagValuePdu extends PDU{

	protected static final int SOFTWARE_VERSION_LENGTH = 9;

	/** 实时数据tag编号. */
	private ShortPdu dataTag = new ShortPdu();
	
	/** 实时数据value编号. */
	private IntPdu dataValueLength = new IntPdu();

	/** 实时数据value值. */
	private byte[] dataValue;

	public ShortPdu getDataTag() {
		return dataTag;
	}

	public void setDataTag(ShortPdu dataTag) {
		this.dataTag = dataTag;
	}

	public IntPdu getDataValueLength() {
		return dataValueLength;
	}

	public void setDataValueLength(IntPdu dataValueLength) {
		this.dataValueLength = dataValueLength;
	}

	public byte[] getDataValue() {
		return dataValue;
	}

	public void setDataValue(byte[] dataValue) {
		this.dataValue = dataValue;
	}

	private TdTagValue tdTagValue;

	@Override
	public TdTagValue getValue() {

		return tdTagValue;
	}

	public TdTagValuePdu(TdTagValue tdTagValue) {
		this.tdTagValue = tdTagValue;
		dataTag = new ShortPdu(tdTagValue.getDataTag());
		dataValueLength = new IntPdu(tdTagValue.getDataValueLength());
		dataValue = tdTagValue.getDataValue();
		dataLenth = getLength();
	}
	
	public TdTagValuePdu(short dataTag, byte[] dataValue) {
		this.tdTagValue = new TdTagValue(dataTag, dataValue);
		this.dataTag = new ShortPdu(tdTagValue.getDataTag());
		this.dataValueLength = new IntPdu(tdTagValue.getDataValueLength());
		this.dataValue = tdTagValue.getDataValue();
		this.dataLenth = getLength();
	}

	public TdTagValuePdu(int startIndex, byte[] data)
			throws MessageParseException {
		dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			dataTag = new ShortPdu(startIndex, data);
			startIndex += dataTag.getLength();
			dataValueLength = new IntPdu(startIndex, data);
			startIndex += dataValueLength.getLength();
			dataValue = ByteOps.cpByteArray(data, startIndex, dataValueLength.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public int getLength() {

		return dataTag.getLength() + dataValueLength.getLength() + dataValueLength.getValue();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, dataTag.getBytes(), index);
		index += dataTag.getLength();
		ByteOps.addByteArray(bytes, dataValueLength.getBytes(), index);
		index += dataValueLength.getLength();
		ByteOps.addByteArray(bytes, dataValue, index);
		index += dataValue.length;
		return bytes;
	}
	
	

	@Override
	public String toString() {
		return "TdTagValuePdu [dataTag=" + dataTag + ", dataValueLength="
				+ dataValueLength + ", dataValue=" + Arrays.toString(dataValue)
				+ "]";
	}

	public static void main(String[] args) {
		byte[] dataValue = {'1', '2', '3', '4'};
		TdTagValuePdu pdu = new TdTagValuePdu((short)1, dataValue);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
	}
}

