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
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:GetTdDataReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-29 下午7:27:14 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class StopTdDataReq extends AbstractReqPackage {

	private ShortPdu tdSerial;

	private List<ShortPdu> tdDataTags = new ArrayList<ShortPdu>();

	public StopTdDataReq() {
		this.header.setMsgType(Commands.STOP_TD_DATA_REQ);
	}

	public StopTdDataReq(byte[] data) {
		super(data);
	}

	public List<Short> getTdDataTags() {
		List<Short> dataTags = new ArrayList<Short>(0);
		for (ShortPdu tag : tdDataTags) {
			dataTags.add(tag.getValue());
		}
		return dataTags;
	}

	public void setTdDataTags(List<ShortPdu> tdDataTags) {
		this.tdDataTags = tdDataTags;
	}

	public void setShortToTdDataTags(List<Short> tdDataTags) {
		this.tdDataTags.clear();
		for (Short each : tdDataTags) {
			this.tdDataTags.add(new ShortPdu(each));
		}
	}

	public ShortPdu getTdSerial() {
		return tdSerial;
	}

	public void setTdSerial(Short tdSerial) {
		this.tdSerial = new ShortPdu(tdSerial);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, tdSerial.getBytes(), index);
		index += tdSerial.getLength();
		for (ShortPdu each : tdDataTags) {
			ByteOps.addByteArray(data, each.getBytes(), index);
			index += each.getLength();
		}

	}

	@Override
	protected void parseBody() throws MessageParseException {
		tdSerial = new ShortPdu(index, data);
		index += tdSerial.getLength();
		while (index < data.length) {
			ShortPdu each = new ShortPdu(index, data);
			tdDataTags.add(each);
			index += each.getLength();
		}

	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + tdSerial.getLength() + tdDataTags.size();
	}

	@Override
	public String toString() {
		return "StartTdDataReq [tdSerial=" + tdSerial
				+ ", tdDataTags=" + tdDataTags
				+ "]";
	}

	public static void main(String[] args) {

		StopTdDataReq req = new StopTdDataReq();
		req.setTdSerial((short) 1);
//		List<Short> list = new ArrayList<Short>(Arrays.asList((short) 0,
//				(short) 1, (short) 2, (short) 3, (short) 4));
//		req.setShortToTdDataTags(list);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		StopTdDataReq req1 = new StopTdDataReq(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
