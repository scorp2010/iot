/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceRunStatusRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-29下午7:27:52
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.HashMap;
import java.util.Map;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.Coder;

/**
 * ClassName:DeviceRunStatusRsp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-29 下午7:27:52 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceRunStatusRsp extends AbstractRspPackage {

	/** 模块值长度. */
	private static int MODULE_VALUE_LENGHT = new IntPdu().getLength();

	private Map<ShortPdu, StringPdu> devicemodules = new HashMap<ShortPdu, StringPdu>(
			0);

	private Map<Short, String> shortDevicemodules = new HashMap<Short, String>(
			0);

	public DeviceRunStatusRsp() {
		this.header.setMsgType(Commands.CD_RUN_STATUS_RSP);
	}

	public DeviceRunStatusRsp(byte[] data) {
		super(data);
	}

	public Map<ShortPdu, StringPdu> getDevicemodules() {
		return devicemodules;
	}

	public void setDevicemodules(Map<ShortPdu, StringPdu> devicemodules) {
		this.devicemodules = devicemodules;
	}

	public void setShortToDevicemodules(Map<Short, String> strDevicemodules) {
		this.shortDevicemodules.clear();
		this.shortDevicemodules = strDevicemodules;
		/*for (Short key : strDevicemodules.keySet()) {
			
			this.devicemodules.put(new ShortPdu(key), new StringPdu(
					strDevicemodules.get(key)));
		}*/
	}

	public Map<Short, String> getShortDevicemodules() {
		Map<Short, String> modules = new HashMap<Short, String>(0);
		for (ShortPdu key : devicemodules.keySet()) {
			modules.put(key.getValue(), devicemodules.get(key).getValue());
		}
		return modules;
	}

	public void pareseShortMapToShortPduMap() {
		this.devicemodules.clear();
		for (Short key : shortDevicemodules.keySet()) {
			this.devicemodules.put(new ShortPdu(key), new StringPdu(
					shortDevicemodules.get(key)));
		}
	}

	public void addDeviceModules(Short module, String value) {
		if (!shortDevicemodules.containsKey(module)) {
			shortDevicemodules.put(module, value);
		}
	}

	@Override
	protected void constructBody(byte[] data) {
		for (ShortPdu key : devicemodules.keySet()) {
			ByteOps.addByteArray(data, key.getBytes(), index);
			index += key.getLength();
			IntPdu valueLength = new IntPdu(devicemodules.get(key).getLength());
			ByteOps.addByteArray(data, valueLength.getBytes(), index);
			index += valueLength.getLength();
			ByteOps.addByteArray(data, devicemodules.get(key).getBytes(), index);
			index += devicemodules.get(key).getLength();
		}

	}

	@Override
	protected void parseBody() throws MessageParseException {

		while (index < data.length) {
			ShortPdu key = new ShortPdu(index, data);
			index += key.getLength();
			IntPdu valueLength = new IntPdu(index, data);
			index += valueLength.getLength();
			StringPdu value = new StringPdu(valueLength.getValue(), index, data);
			devicemodules.put(key, value);
			index += value.getLength();
		}

	}

	@Override
	public String toString() {
		return "DeviceRunStatusRsp [devicemodules=" + devicemodules
				+ ", errorCode=" + errorCode + "]";
	}

	@Override
	protected int getMsgLength() {

		int length = Header.LENGTH_HEADER + this.errorCode.getLength();
		for (ShortPdu key : devicemodules.keySet()) {
			length += key.getLength() + devicemodules.get(key).getLength()
					+ MODULE_VALUE_LENGHT;
		}
		return length;
	}

	public static void main(String[] args) {

		/*
		 * DeviceRunStatusRsp rsq = new DeviceRunStatusRsp(); Map<Short, String>
		 * paras = new HashMap<Short, String>(0); for (int i = 0; i < 4; i++) {
		 * paras.put((short) i, Integer.toString(i)+" "+"Acccess domain"); }
		 * rsq.setErrorCode(1); rsq.setShortToDevicemodules(paras);
		 * rsq.construct();
		 * System.out.println(ByteOps.bytesToHexString(rsq.getData()));
		 * DeviceRunStatusRsp rsq1 = new DeviceRunStatusRsp(rsq.getData());
		 * System.out.println(rsq.getData().length); try { rsq1.parse(); } catch
		 * (MessageParseException e) { e.printStackTrace(); }
		 * System.out.println(rsq1.toString());
		 */
		byte[] bytes;
		try {
			bytes = Coder
					.decryptBASE64("AB4ANAAAAAABAA5BY2Nlc3Mgc3VjY2VzcwIAATAD\r\n");
			DeviceRunStatusRsp testrsp = new DeviceRunStatusRsp(bytes);
			testrsp.parse();
			System.out.println(testrsp.toString());

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
