package cn.inovance.iotgp.common.msg.cd.meta;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:SoftwareVersionInfoPdu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-15 上午9:22:54 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class SoftwareVersionInfoPdu extends PDU {

	protected static final int SOFTWARE_VERSION_LENGTH = 9;

	/** 软件名字长度. */
	private ShortPdu softwareNameLength = new ShortPdu();

	/** 软件名字. */
	private StringPdu softwareName = new StringPdu();

	/** 版本. */
	private StringPdu softwareVersion = new StringPdu();

	/** 文件传输方式 */
	private ShortPdu transferType = new ShortPdu((short) 0);

	public ShortPdu getSoftwareNameLength() {
		return softwareNameLength;
	}

	public void setSoftwareNameLength(ShortPdu softwareNameLength) {
		this.softwareNameLength = softwareNameLength;
	}

	public StringPdu getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(StringPdu softwareName) {
		this.softwareName = softwareName;
	}

	public StringPdu getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(StringPdu softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public ShortPdu getTransferType() {
		return transferType;
	}

	public void setTransferType(ShortPdu transferType) {
		this.transferType = transferType;
	}

	public SoftwareVersionInfo getSoftwareVersionInfo() {
		return softwareVersionInfo;
	}

	public void setSoftwareVersionInfo(SoftwareVersionInfo softwareVersionInfo) {
		this.softwareVersionInfo = softwareVersionInfo;
	}

	private SoftwareVersionInfo softwareVersionInfo = new SoftwareVersionInfo();

	@Override
	public SoftwareVersionInfo getValue() {

		return softwareVersionInfo;
	}

	public SoftwareVersionInfoPdu(SoftwareVersionInfo softwareVersionInfo) {
		this.softwareVersionInfo = softwareVersionInfo;
		softwareNameLength = new ShortPdu((short) softwareVersionInfo
				.getSoftwareName().length());
		softwareName = new StringPdu(softwareVersionInfo.getSoftwareName());
		softwareVersion = new StringPdu(SOFTWARE_VERSION_LENGTH,
				softwareVersionInfo.getSoftwareVersion());
		transferType = new ShortPdu(softwareVersionInfo.getTransferType());
		dataLenth = getLength();
	}

	public SoftwareVersionInfoPdu(String softwareName, String softwareVersion,
			short transferType) {
		this.softwareVersionInfo = new SoftwareVersionInfo(softwareName,
				softwareVersion, transferType);
		this.softwareNameLength = new ShortPdu((short) softwareVersionInfo
				.getSoftwareName().length());
		this.softwareName = new StringPdu(softwareVersionInfo.getSoftwareName());
		this.softwareVersion = new StringPdu(SOFTWARE_VERSION_LENGTH,
				softwareVersionInfo.getSoftwareVersion());
		this.transferType = new ShortPdu(softwareVersionInfo.getTransferType());
		dataLenth = getLength();
	}

	public SoftwareVersionInfoPdu(int startIndex, byte[] data)
			throws MessageParseException {
		dataLenth = data.length;
		this.startIndex = startIndex;
		try {
			softwareNameLength = new ShortPdu(startIndex, data);
			startIndex += softwareNameLength.getLength();
			softwareName = new StringPdu(softwareNameLength.getValue(),
					startIndex, data);
			startIndex += softwareName.getLength();
			softwareVersion = new StringPdu(SOFTWARE_VERSION_LENGTH,
					startIndex, data);
			startIndex += softwareVersion.getLength();
			transferType = new ShortPdu(startIndex, data);
			startIndex += transferType.getLength();
			this.softwareVersionInfo = new SoftwareVersionInfo(
					softwareName.getValue(), softwareVersion.getValue(),
					transferType.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageParseException(e.getMessage());
		}
	}

	@Override
	public int getLength() {
		return softwareNameLength.getLength() + softwareNameLength.getValue()
				+ SOFTWARE_VERSION_LENGTH + transferType.getLength();
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[dataLenth];
		int index = 0;
		softwareNameLength = new ShortPdu((short) softwareName.getLength());
		ByteOps.addByteArray(bytes, softwareNameLength.getBytes(), index);
		index += softwareNameLength.getLength();
		ByteOps.addByteArray(bytes, softwareName.getBytes(), index);
		index += softwareName.getLength();
		ByteOps.addByteArray(bytes, softwareVersion.getBytes(), index);
		index += softwareVersion.getLength();
		ByteOps.addByteArray(bytes, transferType.getBytes(), index);
		index += transferType.getLength();
		return bytes;
	}

	public static void main(String[] args) {
		SoftwareVersionInfo softwareVersionInfo = new SoftwareVersionInfo(
				"AppManager", "V00B02D04", (short) 0);
		SoftwareVersionInfoPdu pdu = new SoftwareVersionInfoPdu(
				softwareVersionInfo);
		System.out.println(ByteOps.bytes2HexStringWithBlank(pdu.getBytes()));
		try {
			SoftwareVersionInfoPdu pdu1 = new SoftwareVersionInfoPdu(0,
					pdu.getBytes());
			System.out
					.println(ByteOps.bytes2HexStringWithBlank(pdu1.getBytes()));
		} catch (MessageParseException e) {
			e.printStackTrace();

		}
	}
}
