/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceParameterRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-28下午4:20:11
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
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

/**
 * ClassName:DeviceParameterRsp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-28 下午4:20:11 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceParameterUpdateReq extends AbstractReqPackage {

	private ShortPdu seq = new ShortPdu(SeqGenerator.nextShort());
	private Map<ShortPdu, StringPdu> parameters = new HashMap<ShortPdu, StringPdu>(
			0);

	public DeviceParameterUpdateReq() {
		this.header.setMsgType(Commands.CD_PARAMETER_UPDATE_NOTIFY);
	}

	public DeviceParameterUpdateReq(byte[] data) {
		super(data);
	}

	public ShortPdu getSeq() {
		return seq;
	}

	public void setSeq(ShortPdu seq) {
		this.seq = seq;
	}

	public void setSeq(Short seq) {
		this.seq = new ShortPdu(seq);
	}

	public Map<ShortPdu, StringPdu> getParameters() {
		return parameters;
	}

	public void setParameters(Map<ShortPdu, StringPdu> parameters) {
		if (null != parameters && !parameters.isEmpty())
			this.parameters = parameters;
	}

	public Map<String, String> getParametersToStr() {
		Map<String, String> strMap = new HashMap<String, String>(0);
		for (ShortPdu key : parameters.keySet()) {
			strMap.put(key.getValue().toString(), parameters.get(key)
					.getValue());
		}
		return strMap;
	}

	public void setStrToParameters(Map<String, String> strParametes) {
		parameters.clear();
		for (String key : strParametes.keySet()) {
			parameters.put(new ShortPdu(Short.parseShort(key)), new StringPdu(
					strParametes.get(key)));
		}
	}

	@Override
	public String toString() {
		return "DeviceParameterUpdateReq [seq=" + seq + ", parameters="
				+ parameters + "]";
	}

	@Override
	protected void constructBody(byte[] data) {

		ByteOps.addByteArray(data, seq.getBytes(), index);
		index += seq.getLength();
		for (ShortPdu key : parameters.keySet()) {
			ByteOps.addByteArray(data, key.getBytes(), index);
			index += key.getLength();
			ShortPdu valueLength = new ShortPdu((short) parameters.get(key)
					.getLength());
			ByteOps.addByteArray(data, valueLength.getBytes(), index);
			index += valueLength.getLength();
			ByteOps.addByteArray(data, parameters.get(key).getBytes(), index);
			index += parameters.get(key).getLength();
		}

	}

	@Override
	protected void parseBody() throws MessageParseException {

		seq = new ShortPdu(index, data);
		index += seq.getLength();
		while (index < data.length) {

			ShortPdu key = new ShortPdu(index, data);
			index += key.getLength();
			ShortPdu valueLength = new ShortPdu(index, data);
			index += valueLength.getLength();
			StringPdu value = new StringPdu(valueLength.getValue(), index, data);
			parameters.put(key, value);
			index += value.getLength();
		}

	}

	@Override
	protected int getMsgLength() {
		int length = Header.LENGTH_HEADER + seq.getLength();
		for (ShortPdu key : parameters.keySet()) {
			length += key.getLength() * 2 + parameters.get(key).getLength();
		}
		return length;
	}

	public static void main(String[] args) {

		DeviceParameterUpdateReq req = new DeviceParameterUpdateReq();
		Map<String, String> paras = new HashMap<String, String>(0);
		for (int i = 0; i < 4; i++) {
			paras.put(Integer.toString(i), Integer.toString(i));
		}
		req.setStrToParameters(paras);
		req.setSeq(new Short((short) 1));
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		DeviceParameterUpdateReq req1 = new DeviceParameterUpdateReq(
				req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
