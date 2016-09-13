/**
 * Project Name:iotgp-common-msg
 * File Name:DataProtocolVersionInfoPdu.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.meta
 * Date:2014-9-4上午11:07:46
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:DataProtocolVersionInfoPdu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-4 上午11:07:46 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DataProtocolVersionAndCommunicationStatusInfoPdu  extends  PDU{

	protected static final int DATA_PROTOCOL_VERSION_LENGTH = 9;
	
	private ShortPdu tdAddressCode = new ShortPdu();
	
	private ShortPdu communicateStatus = new ShortPdu();
	
	/** 数据协议名字长度. */
	private ShortPdu dataProtocolNameLength = new ShortPdu();

	/** 数据协议名字. */
	private StringPdu dataProtocolName = new StringPdu();

	/** 数据协议版本. */
	private StringPdu dataProtocoVersion = new StringPdu();
	
	private DataProtocolVersionAndCommunicationStatusInfo dataProtocolVersionInfo;

	public DataProtocolVersionAndCommunicationStatusInfoPdu(DataProtocolVersionAndCommunicationStatusInfo dataProtocolVersionInfo){
		this.dataProtocolVersionInfo = dataProtocolVersionInfo;
		tdAddressCode = new ShortPdu(dataProtocolVersionInfo.getTdAddressCode().shortValue());
		communicateStatus = new ShortPdu(dataProtocolVersionInfo.getCommunicateStatus().shortValue());
		dataProtocolNameLength = new ShortPdu((short)dataProtocolVersionInfo.getDataProtocolName().length());
		dataProtocolName = new StringPdu(dataProtocolVersionInfo.getDataProtocolName());
		dataProtocoVersion = new StringPdu(DATA_PROTOCOL_VERSION_LENGTH,dataProtocolVersionInfo.getDataProtocolVersion());
		dataLenth = getLength();
	}
	public DataProtocolVersionAndCommunicationStatusInfoPdu(String dataProtocolName, String dataProtocolVersion,int tdAddressCode,int communicateStatus){
		
		this.tdAddressCode =  new ShortPdu((short)tdAddressCode);
		this.communicateStatus = new ShortPdu((short)communicateStatus);
		this.dataProtocolVersionInfo = new DataProtocolVersionAndCommunicationStatusInfo(dataProtocolName, dataProtocolVersion,tdAddressCode,communicateStatus);
		this.dataProtocolNameLength = new ShortPdu((short)dataProtocolVersionInfo.getDataProtocolName().length());
		this.dataProtocolName = new StringPdu(dataProtocolVersionInfo.getDataProtocolName());
		this.dataProtocoVersion = new StringPdu(DATA_PROTOCOL_VERSION_LENGTH,dataProtocolVersionInfo.getDataProtocolVersion());
		dataLenth = getLength();

	}
	public DataProtocolVersionAndCommunicationStatusInfoPdu(int startIndex, byte[] data) throws MessageParseException{
		dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			tdAddressCode = new ShortPdu(startIndex, data);
			startIndex += tdAddressCode.getLength();
			communicateStatus = new ShortPdu(startIndex, data);
			startIndex += communicateStatus.getLength();
			dataProtocolNameLength = new ShortPdu(startIndex, data);
			startIndex += dataProtocolNameLength.getLength();
			dataProtocolName = new StringPdu(dataProtocolNameLength.getValue(), startIndex, data);
			startIndex += dataProtocolName.getLength();
			dataProtocoVersion = new StringPdu(DATA_PROTOCOL_VERSION_LENGTH, startIndex, data);
			startIndex += dataProtocoVersion.getLength();
			this.dataProtocolVersionInfo = new DataProtocolVersionAndCommunicationStatusInfo(dataProtocolName.getValue(), dataProtocoVersion.getValue(),tdAddressCode.getValue(),communicateStatus.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	public DataProtocolVersionAndCommunicationStatusInfo getDataProtocolVersionInfo() {
		return dataProtocolVersionInfo;
	}

	public void setDataProtocolVersionInfo(
			DataProtocolVersionAndCommunicationStatusInfo dataProtocolVersionInfo) {
		this.dataProtocolVersionInfo = dataProtocolVersionInfo;
	}

	public ShortPdu getTdAddressCode() {
		return tdAddressCode;
	}
	public void setTdAddressCode(ShortPdu tdAddressCode) {
		this.tdAddressCode = tdAddressCode;
	}
	public ShortPdu getCommunicateStatus() {
		return communicateStatus;
	}
	public void setCommunicateStatus(ShortPdu communicateStatus) {
		this.communicateStatus = communicateStatus;
	}
	
	public ShortPdu getDataProtocolNameLength() {
		return dataProtocolNameLength;
	}

	public void setDataProtocolNameLength(ShortPdu dataProtocolNameLength) {
		this.dataProtocolNameLength = dataProtocolNameLength;
	}

	public StringPdu getDataProtocolName() {
		return dataProtocolName;
	}

	public void setDataProtocolName(StringPdu dataProtocolName) {
		this.dataProtocolName = dataProtocolName;
	}

	public StringPdu getDataProtocoVersion() {
		return dataProtocoVersion;
	}

	public void setDataProtocoVersion(StringPdu dataProtocoVersion) {
		this.dataProtocoVersion = dataProtocoVersion;
	}

	@Override
	public DataProtocolVersionAndCommunicationStatusInfo getValue() {
		
		return dataProtocolVersionInfo;
	}

	@Override
	public int getLength() {
		return tdAddressCode.getLength()+ communicateStatus.getLength()+dataProtocolNameLength.getLength() + dataProtocolNameLength.getValue() + DATA_PROTOCOL_VERSION_LENGTH;
	}

	@Override
	public byte[] getBytes() {
		
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		ByteOps.addByteArray(bytes, tdAddressCode.getBytes(), index);
		index += tdAddressCode.getLength();
		ByteOps.addByteArray(bytes, communicateStatus.getBytes(), index);
		index += communicateStatus.getLength();
		dataProtocolNameLength = new ShortPdu((short) dataProtocolName.getLength());
		ByteOps.addByteArray(bytes, dataProtocolNameLength.getBytes(), index);
		index += dataProtocolNameLength.getLength();
		ByteOps.addByteArray(bytes, dataProtocolName.getBytes(), index);
		index += dataProtocolName.getLength();
		ByteOps.addByteArray(bytes, dataProtocoVersion.getBytes(), index);
		index += dataProtocoVersion.getLength();
		return bytes;
	}
	@Override
	public String toString() {
		return "DataProtocolVersionInfoPdu [tdAddressCode=" + tdAddressCode
				+ ", communicateStatus=" + communicateStatus
				+ ", dataProtocolNameLength=" + dataProtocolNameLength
				+ ", dataProtocolName=" + dataProtocolName
				+ ", dataProtocoVersion=" + dataProtocoVersion
				+ ", dataProtocolVersionInfo=" + dataProtocolVersionInfo + "]";
	}
	public static void main(String[] args) {
		DataProtocolVersionAndCommunicationStatusInfo dataProtocolVersionInfo = new DataProtocolVersionAndCommunicationStatusInfo("AppManager","V00B02D04",1,1);
		System.out.println(dataProtocolVersionInfo.toString());
		DataProtocolVersionAndCommunicationStatusInfoPdu pdu = new DataProtocolVersionAndCommunicationStatusInfoPdu(dataProtocolVersionInfo);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			DataProtocolVersionAndCommunicationStatusInfoPdu pdu1 = new DataProtocolVersionAndCommunicationStatusInfoPdu(0,
					pdu.getBytes());
			System.out
					.println(ByteOps.bytes2HexStringWithBlank(pdu1.getBytes()));
		} catch (MessageParseException e) {
			e.printStackTrace();

		}
	}
	
}

