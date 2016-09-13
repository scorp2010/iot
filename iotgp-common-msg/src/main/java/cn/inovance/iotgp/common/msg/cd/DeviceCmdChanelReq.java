/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceCmdChanelReq.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-3下午1:53:24
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

/**
 * ClassName:DeviceCmdChanelReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-3 下午1:53:24 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceCmdChanelReq extends AbstractReqPackage {

	/** seq 序列号. */
	private ShortPdu seq = new ShortPdu(SeqGenerator.nextShort());

	private IntPdu vLength;

	private StringPdu value;

	public DeviceCmdChanelReq() {
		this.header.setMsgType(Commands.CD_CMD_CHANEL_REQ);
	}

	public DeviceCmdChanelReq(byte[] data) {
		super(data);
	}

	public ShortPdu getSeq() {
		return seq;
	}

	public void setSeq(ShortPdu seq) {
		this.seq = seq;
	}

	public void setSeq(Short seq) {
		this.seq = new ShortPdu(seq);
	}

	public IntPdu getvLength() {
		return vLength;
	}

	public void setvLength(IntPdu vLength) {
		this.vLength = vLength;
	}

	public StringPdu getValue() {
		return value;
	}

	public void setValue(StringPdu value) {
		this.value = value;
		this.vLength = new IntPdu((short) this.value.getLength());
	}

	public void setValue(String value) {
		this.value = new StringPdu(value);
		this.vLength = new IntPdu((short) this.value.getLength());
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, seq.getBytes(), index);
		index += seq.getLength();
		ByteOps.addByteArray(data, vLength.getBytes(), index);
		index += vLength.getLength();
		ByteOps.addByteArray(data, value.getBytes(), index);
		index += value.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		seq = new ShortPdu(index, data);
		index += seq.getLength();
		vLength = new IntPdu(index, data);
		index += vLength.getLength();
		value = new StringPdu(vLength.getValue(), index, data);
		index += value.getLength();
	}

	@Override
	public String toString() {
		return "DeviceCmdChanelReq [seq=" + seq + ", vLength=" + vLength
				+ ", value=" + value + "]";
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + seq.getLength() + vLength.getLength()
				+ value.getLength();
	}

	public static void main(String[] args) {
		String getRecordNO = "4F0108776C616E636F6E6669673A69643D302C67617465776179315F657468305F69703D3139322E3136382E322E3130302C67617465776179315F657468305F6D61736B3D3235352E3235352E3235352E302C67617465776179315F657468305F6E65743D3139322E3136382E322E302C67617465776179315F7070705F69703D31302E3234372E3134362E3131342C67617465776179325F657468305F69703D3139322E3136382E312E32302C67617465776179325F657468305F6D61736B3D3235352E3235352E3235352E302C67617465776179325F657468305F6E65743D3139322E3136382E312E302C67617465776179325F7070705F69703D31302E3234372E3135322E313231";
		byte[] bytes = ByteOps.hexStringToBytes(getRecordNO);
		
		System.out.println(new String(bytes));
		DeviceCmdChanelReq req1 = new DeviceCmdChanelReq(bytes);
		
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}

}
