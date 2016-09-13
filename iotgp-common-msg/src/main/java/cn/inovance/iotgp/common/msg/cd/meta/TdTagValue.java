/**
 * Project Name:iotgp-common-msg
 * File Name:TdTagValue.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-7-15下午5:10:06
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd.meta;
/**
 * ClassName:TdTagValue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-15 下午5:10:06 <br/>
 * @author   z1979
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TdTagValue {
	/** 实时数据tag编号. */
	private short dataTag;
	
	/** 实时数据value编号. */
	private int dataValueLength;

	/** 实时数据value值. */
	private byte[] dataValue;
	
	public TdTagValue(){
		
	}
	
	public TdTagValue(short dataTag, byte[] dataValue){
		this.dataTag = dataTag;
		this.dataValueLength = dataValue.length;
		this.dataValue = dataValue;
	}

	public TdTagValue(TdTagValuePdu pdu) {
		
		this.dataTag = pdu.getDataTag().getValue();
		this.dataValueLength = pdu.getDataValueLength().getValue();
		this.dataValue = pdu.getDataValue();
	}

	public short getDataTag() {
		return dataTag;
	}

	public void setDataTag(short dataTag) {
		this.dataTag = dataTag;
	}

	public int getDataValueLength() {
		return dataValueLength;
	}

	public void setDataValueLength(int dataValueLength) {
		this.dataValueLength = dataValueLength;
	}

	public byte[] getDataValue() {
		return dataValue;
	}

	public void setDataValue(byte[] dataValue) {
		this.dataValue = dataValue;
	}
}

