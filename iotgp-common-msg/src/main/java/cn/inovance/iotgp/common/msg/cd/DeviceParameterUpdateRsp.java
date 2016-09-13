/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceParameterUpdateRep.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-28下午5:22:01
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.HashMap;
import java.util.Map;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.Coder;

/**
 * ClassName:DeviceParameterUpdateRep <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-28 下午5:22:01 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceParameterUpdateRsp extends AbstractRspPackage {

	/** SEQ. */
	protected static final short DEVICE_PARAMETER_SEQ = 1;
	/** 序号 */
	private ShortPdu seq;
	
	private Map<ShortPdu, StringPdu> resultMap = new HashMap<ShortPdu, StringPdu>(
			0);

	public DeviceParameterUpdateRsp() {
		this.header.setMsgType(Commands.CD_PARAMETER_UPDATE_BACK);
	}

	public DeviceParameterUpdateRsp(byte[] data) {
		super(data);
	}
	public Map<ShortPdu, StringPdu> getResultMap() {
		return resultMap;
	}

	public Map<Short, String> getResultMapByStr() {
		Map<Short, String> result = new HashMap<Short, String>(0);
		for(ShortPdu key:resultMap.keySet()){
			result.put(key.getValue(), resultMap.get(key).getValue());
		}
		return result;
	}
	public void setResultMap(Map<ShortPdu, StringPdu> resultMap) {
		this.resultMap = resultMap;
	}

	public ShortPdu getSeq() {
		return seq;
	}

	public void setSeq(ShortPdu seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "DeviceParameterUpdateRsp [seq=" + seq + ", resultMap="
				+ resultMap + ", errorCode=" + errorCode + "]";
	}

	

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, seq.getBytes(), index);
		index += seq.getLength();
		for(ShortPdu key:resultMap.keySet()){
			ByteOps.addByteArray(data, key.getBytes(), index);
			index += key.getLength();
			ShortPdu length = new ShortPdu((short)resultMap.get(key).getLength());
			ByteOps.addByteArray(data, length.getBytes(), index);
			index += length.getLength();
			ByteOps.addByteArray(data, resultMap.get(key).getBytes(), index);
			index += resultMap.get(key).getLength();
		}
		
	}

	@Override
	protected void parseBody() throws MessageParseException {
		seq = new ShortPdu(index, data);
		index += seq.getLength();
		while(index < data.length){
			ShortPdu key = new ShortPdu(index, data);
			index += key.getLength();
			ShortPdu valueLength = new ShortPdu(index, data);
			index += valueLength.getLength();
			StringPdu value = new StringPdu(valueLength.getValue(), index, data);
			resultMap.put(key, value);
			index += value.getLength();
		}
	}

	@Override
	protected int getMsgLength() {

		int length =  Header.LENGTH_HEADER + this.errorCode.getLength()
				+ this.seq.getLength();
		for (ShortPdu key : resultMap.keySet()) {
			length += key.getLength() + DEVICE_PARAMETER_SEQ + resultMap.get(key).getLength();
		}
		return length;
	}

	public static void main(String[] args) {

		DeviceParameterUpdateRsp rsp = new DeviceParameterUpdateRsp();
		rsp.setErrorCode(0);
		rsp.setSeq(new ShortPdu((short) 1));
		Map<ShortPdu, StringPdu> resultMap = new HashMap<ShortPdu, StringPdu>(
				0);
		resultMap.put(new ShortPdu((short)1), new StringPdu("2"));
		resultMap.put(new ShortPdu((short)2), new StringPdu("dd"));
		resultMap.put(new ShortPdu((short)3), new StringPdu("ff"));
		resultMap.put(new ShortPdu((short)4), new StringPdu("hello world"));
		rsp.setResultMap(resultMap);
		rsp.construct();
		try {
			System.out.println(rsp.toString());
		} catch (Exception e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		DeviceParameterUpdateRsp rsp1;
		try {
			rsp1 = new DeviceParameterUpdateRsp(
					Coder.decryptBASE64("ABQAMQEAAAEEATQDATMCATIBATE=\r\n"));
			System.out.println(rsp.getData().length);
			try {
				rsp1.parse();
			} catch (MessageParseException e) {
				e.printStackTrace();
			}
			System.out.println(rsp1.toString());
		} catch (Exception e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}
}
