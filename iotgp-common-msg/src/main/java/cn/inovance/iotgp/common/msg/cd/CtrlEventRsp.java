package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;


public class CtrlEventRsp extends AbstractReqPackage {
	/** 事件序号 */
	private LongPdu eventSeq = new LongPdu();

	public LongPdu getEventSeq() {
		return eventSeq;
	}
	
	public void setEventSeq(Long evnetSeq) {
		this.eventSeq = new LongPdu(evnetSeq);
	}

	public void setEventSeq(LongPdu evnetSeq) {
		this.eventSeq = evnetSeq;
	}

	public CtrlEventRsp() {
		this.header.setMsgType(Commands.CTRL_EVENT_RSP);
	}

	public CtrlEventRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, eventSeq.getBytes(), index);
		index += eventSeq.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		eventSeq = new LongPdu(index, data);
		index += eventSeq.getLength();
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.eventSeq.getLength();
	}

	@Override
	public String toString() {
		return "CtrlEventRsp [eventSeq=" + eventSeq.getValue() + "]";
	}

	public static void main(String[] args) {
		CtrlEventRsp req = new CtrlEventRsp();
		req.setEventSeq((long) 1);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		try {
			CtrlEventRsp req1 = new CtrlEventRsp(req.getData());
			req1.parse();
			System.out.println(req1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
