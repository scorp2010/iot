/**
 * Project Name:cdag
 * File Name:DeviceLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-4-10下午5:16:44
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.SoftwareVersionInfoPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName: ReportSoftwareVersionReq <br/>
 * Function: 采集设备上报软件版本消息. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014-5-16 下午2:56:05 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 */
public class ReportSoftwareVersionReq extends AbstractReqPackage {

	protected static final int SOFTWARE_VERSION_LENGTH = 9;
	
	private List<SoftwareVersionInfoPdu> softwareVersionInfoList;


	public List<SoftwareVersionInfoPdu> getSoftwareVersionInfoList() {
		return softwareVersionInfoList;
	}

	public void setSoftwareVersionInfoList(
			List<SoftwareVersionInfoPdu> softwareVersionInfoList) {
		this.softwareVersionInfoList = softwareVersionInfoList;
	}

	public ReportSoftwareVersionReq() {
		this.header.setMsgType(Commands.CD_REPORT_SOFTWARE_VERSION);
	}

	public ReportSoftwareVersionReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		for(SoftwareVersionInfoPdu newSoftwareVersionInfoPdu : softwareVersionInfoList){
			ByteOps.addByteArray(data, newSoftwareVersionInfoPdu.getBytes(), index);
			index += newSoftwareVersionInfoPdu.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		softwareVersionInfoList = new ArrayList<SoftwareVersionInfoPdu>();
		while(index < data.length-1){
			SoftwareVersionInfoPdu newSoftwareVersionInfoPdu = new SoftwareVersionInfoPdu(index, data);
			index += newSoftwareVersionInfoPdu.getLength();
			softwareVersionInfoList.add(newSoftwareVersionInfoPdu);
		}
	}

	@Override
	protected int getMsgLength() {

		int msgLength = Header.LENGTH_HEADER;
		for(SoftwareVersionInfoPdu newSoftwareVersionInfoPdu : softwareVersionInfoList){
			msgLength += newSoftwareVersionInfoPdu.getLength();
		}
		return msgLength;
	}


	@Override
	public String toString() {
		return "ReportSoftwareVersionReq [softwareVersionInfoList="
				+ softwareVersionInfoList + "]";
	}

	public static void main(String[] args) {
		ReportSoftwareVersionReq req = new ReportSoftwareVersionReq();
		List<SoftwareVersionInfoPdu> softwareVersionInfoList = new ArrayList<SoftwareVersionInfoPdu>();
		SoftwareVersionInfoPdu pdu1 = new SoftwareVersionInfoPdu("AppManager", "V00B02D05",(short)0);
		softwareVersionInfoList.add(pdu1);
		SoftwareVersionInfoPdu pdu2 = new SoftwareVersionInfoPdu("AppFtp", "V00B02D05",(short)0);
		softwareVersionInfoList.add(pdu2);
		SoftwareVersionInfoPdu pdu3 = new SoftwareVersionInfoPdu("AppPlcdata", "V00B02D05",(short)1);
		softwareVersionInfoList.add(pdu3);
		req.setSoftwareVersionInfoList(softwareVersionInfoList);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		ReportSoftwareVersionReq req1 = new ReportSoftwareVersionReq(
				req.getData());
		try {
			System.out.println(req.getData().length);
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
