package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ServerConnectionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.cd.meta.TdTagPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.meta.TdTag;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.util.RandomCode;

/**
 * @author c2100
 */
public class StartTdDataReq extends AbstractReqPackage {

	private static final int SECURITY_LENGTH = 16;//

	private StringPdu securityCode;//安全码
	private ShortPdu tdSerial;//目标设备编号
	private ServerConnectionInfoPdu serverConnectionInfo;//服务端信息
	private List<TdTagPdu> tdDataTags = new ArrayList<TdTagPdu>();

	public StringPdu getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = new StringPdu(SECURITY_LENGTH, securityCode);
	}

	public ShortPdu getTdSerial() {
		return tdSerial;
	}

	public void setTdSerial(Short tdSerial) {
		this.tdSerial = new ShortPdu(tdSerial);
	}

	public ServerConnectionInfoPdu getServerConnectionInfo() {
		return serverConnectionInfo;
	}

	public void setServerConnectionInfo(
			ServerConnectionInfoPdu serverConnectionInfo) {
		this.serverConnectionInfo = serverConnectionInfo;
	}

	public List<TdTagPdu> getTdDataTags() {
		return tdDataTags;
	}

	public void setTdDataTags(List<TdTagPdu> tdDataTags) {
		this.tdDataTags = tdDataTags;
	}

	public void setTdDataTagsByTdTag(List<TdTag> tdDataTags) {
		for (TdTag tdTag : tdDataTags) {
			this.tdDataTags.add(new TdTagPdu(tdTag));
		}
	}
	public void addTdDataTag(TdTagPdu tdTagPdu) {
		this.tdDataTags.add(tdTagPdu);
	}

	public void addTdDataTag(short tagId, Integer samplePeriod) {
		this.tdDataTags.add(new TdTagPdu(tagId, samplePeriod));
	}

	public void addTdDataTag(ShortPdu tagId, IntPdu samplePeriod) {
		this.tdDataTags.add(new TdTagPdu(tagId, samplePeriod));
	}

	public StartTdDataReq() {
		this.header.setMsgType(Commands.START_TD_DATA_REQ);
	}

	public StartTdDataReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, securityCode.getBytes(), index);
		index += securityCode.getLength();
		ByteOps.addByteArray(data, tdSerial.getBytes(), index);
		index += tdSerial.getLength();
		ByteOps.addByteArray(data, serverConnectionInfo.getBytes(), index);
		index += serverConnectionInfo.getLength();
		for (TdTagPdu tdTag : tdDataTags) {
			ByteOps.addByteArray(data, tdTag.getBytes(), index);
			index += tdTag.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		securityCode = new StringPdu(SECURITY_LENGTH, index, data);
		index += securityCode.getLength();
		tdSerial = new ShortPdu(index, data);
		index += tdSerial.getLength();
		serverConnectionInfo = new ServerConnectionInfoPdu(index, data);
		index += serverConnectionInfo.getLength();
		while (index < data.length) {
			ShortPdu tagId = new ShortPdu(index, data);
			index += tagId.getLength();
			IntPdu samplePeriod = new IntPdu(index, data);
			index += samplePeriod.getLength();
			this.addTdDataTag(tagId, samplePeriod);
		}
	}

	@Override
	protected int getMsgLength() {
		int protocolLength = Header.LENGTH_HEADER + SECURITY_LENGTH + tdSerial.getLength()
				+ this.serverConnectionInfo.getLength();
		for (TdTagPdu tdTag : tdDataTags) {
			protocolLength += tdTag.getLength();
		}
		return protocolLength;
	}

	@Override
	public String toString() {
		return "StartTdDataReq [securityCode=" + securityCode + ", tdSerial="
				+ tdSerial + ", serverConnectionInfo=" + serverConnectionInfo
				+ ", tdDataTags=" + tdDataTags + "]";
	}

	public static void main(String[] args) {

		StartTdDataReq req = new StartTdDataReq();
		req.setSecurityCode(new String(RandomCode.generateCode16()));
		req.setTdSerial((short) 1);
		for (int i = 0; i < 4; i++)
			req.addTdDataTag((short) i, i);
		int[] host = { 192, 168, 60, 47 };
		ServerConnectionInfoPdu pdu = new ServerConnectionInfoPdu(host, 6397);
		req.setServerConnectionInfo(pdu);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		StartTdDataReq req1 = new StartTdDataReq(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
