package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class TdCmdTransferReq extends AbstractReqPackage {

	public TdCmdTransferReq() {
		this.header.setMsgType(Commands.TD_CMD_TRANSFER_REQ);
	}

	public TdCmdTransferReq(byte[] data) {
		super(data);
	}

	/** 消息序号 */
	private ShortPdu msgSeq = new ShortPdu();
	/** 目标设备类型 */
	private ShortPdu tdType = new ShortPdu();
	/** 目标设备编号 */
	private ShortPdu tdCode = new ShortPdu();
	/** 指令数据长度 */
	private IntPdu cmdDataLength = new IntPdu();
	/** 指令数据 */
	private byte[] cmdData = new byte[0];
	/** 指令响应数据长度 */
	private IntPdu cmdRspDataLength = new IntPdu();
	/** 指令响应数据校验方式 */
	private ShortPdu cmdRspDataCheckType = new ShortPdu();

	public short getMsgSeq() {
		return msgSeq.getValue();
	}

	public void setMsgSeq(short msgSeq) {
		this.msgSeq = new ShortPdu(msgSeq);
	}

	public short getTdType() {
		return tdType.getValue();
	}

	public void setTdType(short tdType) {
		this.tdType = new ShortPdu(tdType);
	}

	public short getTdCode() {
		return tdCode.getValue();
	}

	public void setTdCode(short tdCode) {
		this.tdCode = new ShortPdu(tdCode);
	}

	public int getCmdDataLength() {
		return cmdDataLength.getValue();
	}

	public void setCmdDataLength(int cmdDataLength) {
		this.cmdDataLength = new IntPdu(cmdDataLength);
	}

	public byte[] getCmdData() {
		return cmdData;
	}

	public void setCmdData(byte[] cmdData) {
		this.cmdData = cmdData;
	}

	public int getCmdRspDataLength() {
		return cmdRspDataLength.getValue();
	}

	public void setCmdRspDataLength(int cmdRspDataLength) {
		this.cmdRspDataLength = new IntPdu(cmdRspDataLength);
	}

	public short getCmdRspDataCheckType() {
		return cmdRspDataCheckType.getValue();
	}

	public void setCmdRspDataCheckType(short cmdRspDataCheckType) {
		this.cmdRspDataCheckType = new ShortPdu(cmdRspDataCheckType);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, msgSeq.getBytes(), index);
		index += msgSeq.getLength();
		ByteOps.addByteArray(data, tdType.getBytes(), index);
		index += tdType.getLength();
		ByteOps.addByteArray(data, tdCode.getBytes(), index);
		index += tdCode.getLength();
		ByteOps.addByteArray(data, cmdDataLength.getBytes(), index);
		index += cmdDataLength.getLength();
		if (cmdDataLength.getValue() > 0) {
			ByteOps.addByteArray(data, cmdData, index);
			index += cmdData.length;
		}
		ByteOps.addByteArray(data, cmdRspDataLength.getBytes(), index);
		index += cmdRspDataLength.getLength();
		ByteOps.addByteArray(data, cmdRspDataCheckType.getBytes(), index);
		index += cmdRspDataCheckType.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		msgSeq = new ShortPdu(index, data);
		index += msgSeq.getLength();
		tdType = new ShortPdu(index, data);
		index += tdType.getLength();
		tdCode = new ShortPdu(index, data);
		index += tdCode.getLength();
		cmdDataLength = new IntPdu(index, data);
		index += cmdDataLength.getLength();
		if (cmdDataLength.getValue() > 0) {
			cmdData = ByteOps
					.cpByteArray(data, index, cmdDataLength.getValue());
			index += cmdDataLength.getValue();
		}
		cmdRspDataLength = new IntPdu(index, data);
		index += cmdRspDataLength.getLength();
		cmdRspDataCheckType = new ShortPdu(index, data);
		index += cmdRspDataCheckType.getLength();
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.msgSeq.getLength()
				+ this.tdType.getLength() + this.tdCode.getLength()
				+ this.cmdDataLength.getLength()
				+ this.cmdDataLength.getValue()
				+ this.cmdRspDataLength.getLength()
				+ this.cmdRspDataCheckType.getLength();
	}

	@Override
	public String toString() {
		return "TdCmdTransferReq [msgSeq=" + msgSeq + ", tdType=" + tdType
				+ ", tdCode=" + tdCode + ", cmdDataLength=" + cmdDataLength
				+ ", cmdData=" + ByteOps.bytes2HexStringWithBlank(cmdData)
				+ ", cmdRspDataLength=" + cmdRspDataLength
				+ ", cmdRspDataCheckType=" + cmdRspDataCheckType + "]";
	}

	public static void main(String[] args) {
		TdCmdTransferReq req = new TdCmdTransferReq();
		byte[] cmdData = new byte[1];
		cmdData[0] = 0x01;
		req.setCmdDataLength(1);
		req.setCmdData(cmdData);
		req.setCmdRspDataCheckType((short) 1);
		req.setCmdRspDataLength(2);
		req.setMsgSeq((short) 1);
		req.setTdCode((short) 1);
		req.setTdType((short) 0);

		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		TdCmdTransferReq req1 = new TdCmdTransferReq(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}
