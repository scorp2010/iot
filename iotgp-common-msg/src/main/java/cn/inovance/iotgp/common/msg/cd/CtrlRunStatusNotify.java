package cn.inovance.iotgp.common.msg.cd;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class CtrlRunStatusNotify extends AbstractReqPackage {

	/** 设备编号 */
	private ShortPdu tdCode = new ShortPdu();
	/** 运行状态 */
	private byte[] runStatus;

	public short getTdCode() {
		return tdCode.getValue();
	}

	public void setTdCode(short tdCode) {
		this.tdCode = new ShortPdu(tdCode);
	}

	public byte[] getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(byte[] runStatus) {
		this.runStatus = runStatus;
	}

	public CtrlRunStatusNotify() {
		this.header.setMsgType(Commands.CTRL_RUN_STATUS_NOTIFY);
	}

	public CtrlRunStatusNotify(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, tdCode.getBytes(), index);
		index += tdCode.getLength();
		ByteOps.addByteArray(data, runStatus, index);
		index += runStatus.length;
	}

	@Override
	protected void parseBody() throws MessageParseException {
		tdCode = new ShortPdu(index, data);
		index += tdCode.getLength();
		runStatus = ByteOps.cpByteArray(data, index, data.length - index);
		index += runStatus.length;
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.tdCode.getLength()
				+ (runStatus != null ? runStatus.length : 0);
	}

	@Override
	public String toString() {
		return "CtrlRunStatusNotify [tdCode=" + tdCode + ", runStatus="
				+ Arrays.toString(runStatus) + "]";
	}

	public static void main(String[] args) {
		CtrlRunStatusNotify req = new CtrlRunStatusNotify();
		req.setTdCode((short) 1);
		req.setRunStatus(new byte[0]);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		try {
			CtrlRunStatusNotify req1 = new CtrlRunStatusNotify(req.getData());
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}
}
