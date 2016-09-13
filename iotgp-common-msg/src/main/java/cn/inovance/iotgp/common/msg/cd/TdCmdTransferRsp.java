package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class TdCmdTransferRsp extends AbstractReqPackage {

	public TdCmdTransferRsp() {
		this.header.setMsgType(Commands.TD_CMD_TRANSFER_RSP);
	}

	public TdCmdTransferRsp(byte[] data) {
		super(data);
	}

	/** 消息序号 */
	private ShortPdu msgSeq = new ShortPdu();
	/** 目标设备类型 */
	private ShortPdu tdType = new ShortPdu();
	/** 目标设备编号 */
	private ShortPdu tdCode = new ShortPdu();
	/** 指令响应数据长度 */
	private IntPdu cmdRspDataLength = new IntPdu();
	/** 指令响应数据 */
	private byte[] cmdRspData = new byte[0];

	public ShortPdu getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(short msgSeq) {
		this.msgSeq = new ShortPdu(msgSeq);
	}

	public void setMsgSeq(ShortPdu msgSeq) {
		this.msgSeq = msgSeq;
	}

	public short getTdType() {
		return tdType.getValue();
	}

	public void setTdType(ShortPdu tdType) {
		this.tdType = tdType;
	}

	public void setTdType(short tdType) {
		this.tdType = new ShortPdu(tdType);
	}

	public void setTdCode(ShortPdu tdCode) {
		this.tdCode = tdCode;
	}

	public short getTdCode() {
		return tdCode.getValue();
	}

	public void setTdCode(short tdCode) {
		this.tdCode = new ShortPdu(tdCode);
	}

	public int getCmdRspDataLength() {
		return cmdRspDataLength.getValue();
	}

	public void setCmdRspDataLength(int cmdRspDataLength) {
		this.cmdRspDataLength = new IntPdu(cmdRspDataLength);
	}

	public void setCmdRspDataLength(IntPdu cmdRspDataLength) {
		this.cmdRspDataLength = cmdRspDataLength;
	}

	public byte[] getCmdRspData() {
		return cmdRspData;
	}

	public void setCmdRspData(byte[] cmdRspData) {
		this.cmdRspData = cmdRspData;
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, msgSeq.getBytes(), index);
		index += msgSeq.getLength();
		ByteOps.addByteArray(data, tdType.getBytes(), index);
		index += tdType.getLength();
		ByteOps.addByteArray(data, tdCode.getBytes(), index);
		index += tdCode.getLength();
		ByteOps.addByteArray(data, cmdRspDataLength.getBytes(), index);
		index += cmdRspDataLength.getLength();
		if (cmdRspDataLength.getValue() > 0) {
			ByteOps.addByteArray(data, cmdRspData, index);
			index += cmdRspData.length;
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		msgSeq = new ShortPdu(index, data);
		index += msgSeq.getLength();
		tdType = new ShortPdu(index, data);
		index += tdType.getLength();
		tdCode = new ShortPdu(index, data);
		index += tdCode.getLength();
		cmdRspDataLength = new IntPdu(index, data);
		index += cmdRspDataLength.getLength();
		if (cmdRspDataLength.getValue() > 0) {
			cmdRspData = ByteOps.cpByteArray(data, index,
					cmdRspDataLength.getValue());
			index += cmdRspDataLength.getValue();
		}
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.msgSeq.getLength()
				+ this.tdType.getLength() + this.tdCode.getLength()
				+ this.cmdRspDataLength.getLength()
				+ this.cmdRspDataLength.getValue();
	}

	@Override
	public String toString() {
		return "TdCmdTransferRsp [msgSeq=" + msgSeq + ", tdType=" + tdType
				+ ", tdCode=" + tdCode + ", cmdRspDataLength="
				+ cmdRspDataLength + ", cmdRspData="
				+ ByteOps.bytes2HexStringWithBlank(cmdRspData) + "]";
	}

	public static void main(String[] args) {
		TdCmdTransferRsp req = new TdCmdTransferRsp();
		byte[] cmdRspData = new byte[1];
		cmdRspData[0] = 0x01;
		req.setMsgSeq((short) 1);
		req.setTdType((short) 0);
		req.setTdCode((short) 1);
		req.setCmdRspDataLength(1);
		req.setCmdRspData(cmdRspData);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		try {
			TdCmdTransferRsp req1 = new TdCmdTransferRsp(req.getData());
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
