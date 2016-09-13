package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.DataProtocolVersionAndCommunicationStatusInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * @author w1898
 */
public class DataProtocolAndCommunicationStatusNotify extends
		AbstractReqPackage {

	private ShortPdu tdTypeCode = new ShortPdu();

	private List<DataProtocolVersionAndCommunicationStatusInfoPdu> dataProtocolVersionList = null;

	public DataProtocolAndCommunicationStatusNotify() {
		this.header
				.setMsgType(Commands.TD_DATA_PROTOCOL_AND_COMMUNICATION_STATUS_NOTIFY);
	}

	public DataProtocolAndCommunicationStatusNotify(byte[] data) {
		super(data);
	}

	public ShortPdu getTdTypeCode() {
		return tdTypeCode;
	}

	public void setTdTypeCode(ShortPdu tdTypeCode) {
		this.tdTypeCode = tdTypeCode;
	}

	public List<DataProtocolVersionAndCommunicationStatusInfoPdu> getDataProtocolVersionList() {
		return dataProtocolVersionList;
	}

	public void setDataProtocolVersionList(
			List<DataProtocolVersionAndCommunicationStatusInfoPdu> dataProtocolVersionList) {
		this.dataProtocolVersionList = dataProtocolVersionList;
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, tdTypeCode.getBytes(), index);
		index += tdTypeCode.getLength();
		for (DataProtocolVersionAndCommunicationStatusInfoPdu each : dataProtocolVersionList) {
			ByteOps.addByteArray(data, each.getBytes(), index);
			index += each.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		tdTypeCode = new ShortPdu(index, data);
		index += tdTypeCode.getLength();
		dataProtocolVersionList = new ArrayList<DataProtocolVersionAndCommunicationStatusInfoPdu>();
		while (index < data.length) {
			DataProtocolVersionAndCommunicationStatusInfoPdu each = new DataProtocolVersionAndCommunicationStatusInfoPdu(
					index, data);
			index += each.getLength();
			dataProtocolVersionList.add(each);
		}
	}

	@Override
	protected int getMsgLength() {
		int msgLength = Header.LENGTH_HEADER;
		if (dataProtocolVersionList == null) {
			return msgLength;
		}
		msgLength += tdTypeCode.getLength();
		for (DataProtocolVersionAndCommunicationStatusInfoPdu each : dataProtocolVersionList) {
			msgLength += each.getLength();
		}
		return msgLength;
	}

	@Override
	public String toString() {
		return "ReportDataProtocolNotify [tdTypeCode=" + tdTypeCode
				+ ", dataProtocolVersionList=" + dataProtocolVersionList + "]";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		DataProtocolAndCommunicationStatusNotify req = new DataProtocolAndCommunicationStatusNotify();
		req.setTdTypeCode(new ShortPdu((short) 1));
		List dataProtocolVersionInfoList = new ArrayList<DataProtocolVersionAndCommunicationStatusInfoPdu>();
		DataProtocolVersionAndCommunicationStatusInfoPdu pdu1 = new DataProtocolVersionAndCommunicationStatusInfoPdu(
				"AppManager", "V00B02D05", 1, 1);
		dataProtocolVersionInfoList.add(pdu1);
		DataProtocolVersionAndCommunicationStatusInfoPdu pdu2 = new DataProtocolVersionAndCommunicationStatusInfoPdu(
				"AppFtp", "V00B02D05", 2, 1);
		dataProtocolVersionInfoList.add(pdu2);
		DataProtocolVersionAndCommunicationStatusInfoPdu pdu3 = new DataProtocolVersionAndCommunicationStatusInfoPdu(
				"AppPlcdata", "V00B02D05", 3, 0);
		dataProtocolVersionInfoList.add(pdu3);
		req.setDataProtocolVersionList(dataProtocolVersionInfoList);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		DataProtocolAndCommunicationStatusNotify req1 = new DataProtocolAndCommunicationStatusNotify(
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
