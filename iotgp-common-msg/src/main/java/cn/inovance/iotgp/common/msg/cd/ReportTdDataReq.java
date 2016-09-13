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
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.cd.meta.TdTagValue;
import cn.inovance.iotgp.common.msg.cd.meta.TdTagValuePdu;
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
public class ReportTdDataReq extends AbstractReqPackage {
	
	private static final int SECURITY_LENGTH = 16;
	
	private StringPdu securityCode;

	private ShortPdu tdSerial;

	private List<TdTagValuePdu> tdDataTagValues = new ArrayList<TdTagValuePdu>();

	public ReportTdDataReq() {
		this.header.setMsgType(Commands.REPORT_TD_DATA_REQ);
	}

	public ReportTdDataReq(byte[] data) {
		super(data);
	}
	
	public StringPdu getSecurityCode() {
		return securityCode;
	}


	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public List<TdTagValue> getTdDataTagValues() {
		List<TdTagValue> tdTagValueList = new ArrayList<TdTagValue>();
		for (TdTagValuePdu pdu : tdDataTagValues) {
			tdTagValueList.add(new TdTagValue(pdu));
		}
		return tdTagValueList;
	}

	public void setTdDataTagValues(List<TdTagValue> tdDataTags) {
		for (TdTagValue tdTagValue : tdDataTags) {
			tdDataTagValues.add(new TdTagValuePdu(tdTagValue));
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
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, tdSerial.getBytes(), index);
		index += tdSerial.getLength();
		for (TdTagValuePdu each : tdDataTagValues) {
			ByteOps.addByteArray(data, each.getBytes(), index);
			index += each.getLength();
		}

	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		tdSerial = new ShortPdu(index, data);
		index += tdSerial.getLength();
		while (index < data.length) {
			TdTagValuePdu each = new TdTagValuePdu(index, data);
			tdDataTagValues.add(each);
			index += each.getLength();
		}

	}

	@Override
	protected int getMsgLength() {
		int tdDataTagValuesLength = 0;
		for (TdTagValuePdu each : tdDataTagValues) {
			tdDataTagValuesLength += each.getLength();
		}
		return Header.LENGTH_HEADER + SECURITY_LENGTH + tdSerial.getLength()
				+ tdDataTagValuesLength;
	}


	@Override
	public String toString() {
		return "ReportTdDataReq [securityCode=" + securityCode + ", tdSerial="
				+ tdSerial + ", tdDataTagValues=" + tdDataTagValues + "]";
	}

	public static void main(String[] args) {

		ReportTdDataReq req = new ReportTdDataReq();
		req.setTdSerial((short) 1);
		byte[] dataValue = { '1', '2', '3', '4' };
		TdTagValue pdu = new TdTagValue((short) 1, dataValue);
		byte[] dataValue1 = { '5', '6', '7', '8' };
		TdTagValue pdu1 = new TdTagValue((short) 2, dataValue1);
		List<TdTagValue> tdDataTags = new ArrayList<TdTagValue>();
		tdDataTags.add(pdu);
		tdDataTags.add(pdu1);
		req.setTdDataTagValues(tdDataTags);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		ReportTdDataReq req1 = new ReportTdDataReq(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}

}
