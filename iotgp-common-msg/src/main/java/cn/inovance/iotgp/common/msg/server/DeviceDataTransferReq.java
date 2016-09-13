/**
 * Project Name:cdag
 * File Name:ServerLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:31:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

/**
 * ClassName:DeviceDataTransferReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:31:45 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceDataTransferReq extends Request {

	private int transferMsgType;

	private String deviceSerial;

	private String tdSerial;

	private String transferData = ""; // 字节数组的Hex表示法

	private String tdDataTags = ""; // 目标设备标签编号列表，多个标签时用“,”分隔。（transferMsgType为实时运行数据透传指令时有效)

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getTdSerial() {
		return tdSerial;
	}

	public void setTdSerial(String tdSerial) {
		this.tdSerial = tdSerial;
	}

	public DeviceDataTransferReq() {
		this.msgType = MsgType.DEVICE_DATA_TRANSFER;
	}

	public int getTransferMsgType() {
		return transferMsgType;
	}

	public void setTransferMsgType(int transferMsgType) {
		this.transferMsgType = transferMsgType;
	}

	public String getTransferData() {
		return transferData;
	}

	public void setTransferData(String transferData) {
		this.transferData = transferData;
	}

	public byte[] transferData2Bytes() {
		return ByteOps.hexStringToBytes(transferData);
	}

	public void bytes2TransferData(byte[] transferData) {
		this.transferData = ByteOps.bytesToHexString(transferData);
	}

	public String getTdDataTags() {
		return tdDataTags;
	}

	public void setTdDataTags(String tdDataTags) {
		this.tdDataTags = tdDataTags;
	}

	public static void main(String[] args) {
		String getRecordNO = "F90108776C616E636F6E6669673" +
				"A69643D312C67617465776179315F657468305F69703" +
				"D3139322E3136382E322E3130302C676174657761793" +
				"15F657468305F6D61736B3D3235352E3235352E323535" +
				"2E302C67617465776179315F657468305F6E65743D313" +
				"9322E3136382E322E302C676174657761" +
				"79315F7070705F69703D31302E3234372E3134362E3" +
				"131342C67617465776179325F657468305F69703D31393" +
				"22E3136382E312E32302C67617465776179325F657468305" +
				"F6D61736B3D3235352E3235352E3235352E302C6761746577" +
				"6179325F657468305F6E65743D3139322E3136382E312E302" +
				"C67617465776179325F7070705F69703D31302E3234372E3135322E313231";
		byte[] bytes = ByteOps.hexStringToBytes(getRecordNO);
		DeviceDataTransferReq req = new DeviceDataTransferReq();
		req.bytes2TransferData(bytes);
		System.out.println(req.toString());
		DeviceDataTransferReq req1 = JsonBinder.normalBinder.fromJson(
				req.toString(), DeviceDataTransferReq.class);
		System.out.println(req1.toString());
	}
}
