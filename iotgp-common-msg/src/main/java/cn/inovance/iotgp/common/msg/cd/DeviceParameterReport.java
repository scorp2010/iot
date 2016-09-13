/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceParameterReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-28上午11:39:00
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

/**
 * ClassName:DeviceParameterReq.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceParameterReport extends AbstractReqPackage {

	/** 采集设备参数编号长度. */
	protected static final int DEVICE_PARAMETER_CODE = 1;
	/** 采集设备参数值长度. */
	protected static final int DEVICE_PARAMETER_VALUE_LENGTH = 1;

	private Map<ShortPdu, StringPdu> parameters = new HashMap<ShortPdu, StringPdu>(
			0);

	public DeviceParameterReport() {
		this.header.setMsgType(Commands.CD_PARAMETER_NOTIFY);
	}

	public DeviceParameterReport(byte[] data) {
		super(data);
	}

	public Map<ShortPdu, StringPdu> getParameters() {
		return parameters;
	}

	public Map<Short, String> getParametersToShort() {
		Map<Short, String> strMap = new HashMap<Short, String>(0);
		for (ShortPdu key : parameters.keySet()) {
			strMap.put(key.getValue(), parameters.get(key).getValue());
		}
		return strMap;
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

	public void setParameters(Map<ShortPdu, StringPdu> parameters) {
		if (null != parameters && !parameters.isEmpty())
			this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "DeviceParameterReq [parameters=" + parameters + "]";
	}

	@Override
	protected void constructBody(byte[] data) {
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
		int length = Header.LENGTH_HEADER;
		for (ShortPdu key : parameters.keySet()) {
			length += key.getLength() * 2 + parameters.get(key).getLength();
		}
		return length;
	}

	public static void main(String[] args) {

		DeviceParameterReport report = new DeviceParameterReport();
		Map<String, String> paras = new HashMap<String, String>(0);
		for (int i = 0; i < 4; i++) {
			paras.put(Integer.toString(i), Integer.toString(i));
		}
		report.setStrToParameters(paras);
		report.construct();
		System.out.println(ByteOps.bytesToHexString(report.getData()));

		DeviceParameterReport report1 = new DeviceParameterReport(
				report.getData());
		System.out.println(report.getData().length);
		try {
			report1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(report1.toString());
	}
}
