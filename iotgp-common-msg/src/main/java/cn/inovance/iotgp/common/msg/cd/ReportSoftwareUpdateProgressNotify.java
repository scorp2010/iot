/**
 * Project Name:cdag
 * File Name:DeviceLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-4-10下午5:16:44
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName: ReportSoftwareUpdateProgressNotify <br/>
 * Function: 采集设备上报软件更新进度通知. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014-5-16 下午2:57:38 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 */
public class ReportSoftwareUpdateProgressNotify extends AbstractReqPackage {

	protected static final int SOFTWARE_VERSION_LENGTH = 9;

	/** 设备软件更新结果. */
	private ShortPdu updateResult = new ShortPdu((short) 0);

	/** 软件名字长度. */
	private ShortPdu softwareNameLength = new ShortPdu();

	/** 软件名字. */
	private StringPdu softwareName = new StringPdu();

	/** 版本. */
	private StringPdu softwareVersion = new StringPdu();

	/** 更新进度信息. */
	private ShortPdu updateProgress = new ShortPdu((short) 0);

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

	public ReportSoftwareUpdateProgressNotify() {
		this.header.setMsgType(Commands.CD_SOFTWARE_UPDATE_PROGRESS_NOTIFY);
	}

	public ReportSoftwareUpdateProgressNotify(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, updateResult.getBytes(), index);
		index += updateResult.getLength();
		softwareNameLength = new ShortPdu((short) softwareName.getLength());
		ByteOps.addByteArray(data, softwareNameLength.getBytes(), index);
		index += softwareNameLength.getLength();
		ByteOps.addByteArray(data, softwareName.getBytes(), index);
		index += softwareName.getLength();
		ByteOps.addByteArray(data, softwareVersion.getBytes(), index);
		index += softwareVersion.getLength();
		ByteOps.addByteArray(data, updateProgress.getBytes(), index);
		index += updateProgress.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		updateResult = new ShortPdu(index, data);
		index += updateResult.getLength();
		softwareNameLength = new ShortPdu(index, data);
		index += softwareNameLength.getLength();
		softwareName = new StringPdu(softwareNameLength.getValue(), index, data);
		index += softwareName.getLength();
		softwareVersion = new StringPdu(SOFTWARE_VERSION_LENGTH, index, data);
		index += softwareVersion.getLength();
		updateProgress = new ShortPdu(index, data);
		index += updateProgress.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + this.updateResult.getLength()
				+ this.softwareNameLength.getLength()
				+ this.softwareName.getLength()
				+ this.softwareVersion.getLength()
				+ this.updateProgress.getLength();
	}

	@Override
	public String toString() {
		return "ReportSoftwareUpdateProgressNotify [updateResult="
				+ updateResult + ", softwareNameLength=" + softwareNameLength
				+ ", softwareName=" + softwareName + ", softwareVersion="
				+ softwareVersion + ", updateProgress=" + updateProgress + "]";
	}

	public static void main(String[] args) {
		ReportSoftwareUpdateProgressNotify req = new ReportSoftwareUpdateProgressNotify();
		StringPdu softwareName = new StringPdu(28, "010270184D800171");
		req.setSoftwareName(softwareName);
		StringPdu softwareVersion = new StringPdu(9, "V11B11D11");
		req.setSoftwareVersion(softwareVersion);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		ReportSoftwareUpdateProgressNotify req1 = new ReportSoftwareUpdateProgressNotify(
				req.getData());
		try {
			System.out.println(req.getData().length);
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

	public ShortPdu getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(ShortPdu updateResult) {
		this.updateResult = updateResult;
	}

	public ShortPdu getUpdateProgress() {
		return updateProgress;
	}

	public void setUpdateProgress(ShortPdu updateProgress) {
		this.updateProgress = updateProgress;
	}

}
