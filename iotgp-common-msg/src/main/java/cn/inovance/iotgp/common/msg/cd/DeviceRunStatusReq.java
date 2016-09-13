/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceRunStatusReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-5-29下午7:27:14
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:DeviceRunStatusReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-29 下午7:27:14 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceRunStatusReq extends AbstractReqPackage {

	private List<ShortPdu> devicemodules = new ArrayList<ShortPdu>(0);

	public DeviceRunStatusReq() {
		this.header.setMsgType(Commands.CD_RUN_STATUS_REQ);
	}

	public DeviceRunStatusReq(byte[] data) {
		super(data);
	}

	public List<ShortPdu> getDevicemodules() {
		return devicemodules;
	}

	public List<Short> getShortDevicemodules() {
		List<Short> modules = new ArrayList<Short>(0);
		for (ShortPdu module : devicemodules) {
			modules.add(module.getValue());
		}
		return modules;
	}

	public void setDevicemodules(List<ShortPdu> devicemodules) {
		this.devicemodules = devicemodules;
	}

	public void setShortToDevicemodules(List<Short> Shortdevicemodules) {
		this.devicemodules.clear();
		for (Short each : Shortdevicemodules) {
			this.devicemodules.add(new ShortPdu(each));
		}
	}

	@Override
	protected void constructBody(byte[] data) {
		for (ShortPdu each : devicemodules) {
			ByteOps.addByteArray(data, each.getBytes(), index);
			index += each.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {

		while (index < data.length) {
			ShortPdu module = new ShortPdu(index, data);
			devicemodules.add(module);
			index += module.getLength();
		}

	}

	@Override
	protected int getMsgLength() {
		if (devicemodules.size() > 0) {
			return Header.LENGTH_HEADER + devicemodules.size()
					* devicemodules.get(0).getLength();
		} else {
			return Header.LENGTH_HEADER;
		}
	}

	@Override
	public String toString() {
		return "DeviceRunStatusReq [devicemodules=" + devicemodules + "]";
	}

	public static void main(String[] args) {

		DeviceRunStatusReq req = new DeviceRunStatusReq();
		List<Short> list = new ArrayList<Short>(Arrays.asList((short) 0,
				(short) 1, (short) 2, (short) 3, (short) 4));
		req.setShortToDevicemodules(list);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		DeviceRunStatusReq req1 = new DeviceRunStatusReq(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
