package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class CtrlEventNewRsp extends AbstractReqPackage {

	/** 消息序号 */
	private LongPdu msgSeq = new LongPdu();

	public LongPdu getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(LongPdu msgSeq) {
		this.msgSeq = msgSeq;
	}

	public void setMsgSeq(long msgSeq) {
		this.msgSeq = new LongPdu(msgSeq);
	}

	public CtrlEventNewRsp() {
		this.header.setMsgType(Commands.CTRL_EVENT_RSP);
	}

	public CtrlEventNewRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, msgSeq.getBytes(), index);
		index += msgSeq.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		msgSeq = new LongPdu(index, data);
		index += msgSeq.getLength();
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.msgSeq.getLength();
	}

	@Override
	public String toString() {
		return "CtrlEventRsp [msgSeq=" + msgSeq + "]";
	}

	public static void main(String[] args) {
		CtrlEventNewRsp req = new CtrlEventNewRsp();
		req.setMsgSeq(1);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		try {
			CtrlEventNewRsp req1 = new CtrlEventNewRsp(req.getData());
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
