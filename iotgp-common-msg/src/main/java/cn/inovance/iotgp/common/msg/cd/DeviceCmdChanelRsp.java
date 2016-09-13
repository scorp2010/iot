/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceCmdChanelRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-3下午1:53:46
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:DeviceCmdChanelRsp. 【注意：设备端如果发现命令行结果过长,则截取两个字节长度的数据，舍掉后面过长字符】
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */

public class DeviceCmdChanelRsp extends AbstractRspPackage {

	/** seq 序列号. */
	private ShortPdu seq;

	private IntPdu vLength;

	private StringPdu value;

	public DeviceCmdChanelRsp() {
		this.header.setMsgType(Commands.CD_CMD_CHANEL_RSP);
	}

	public DeviceCmdChanelRsp(byte[] data) {
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
		this.vLength = new IntPdu(this.value.getLength());
	}

	public void setValue(String value) {
		this.value = new StringPdu(value);
		this.vLength = new IntPdu(this.value.getLength());
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
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + this.errorCode.getLength()
				+ seq.getLength() + vLength.getLength() + value.getLength();
	}

	@Override
	public String toString() {
		return "DeviceCmdChanelRsp [seq=" + seq + ", vLength=" + vLength
				+ ", value=" + value + ", errorCode=" + errorCode + "]";
	}

	public static void main(String[] args) {

		DeviceCmdChanelRsp rsp = new DeviceCmdChanelRsp();
		rsp.setSeq((short) 255);
		rsp.setErrorCode((short) 1);
		rsp.setValue("ibjb8964");
		rsp.construct();
		System.out.println(ByteOps.bytesToHexString(rsp.getData()));
		DeviceCmdChanelRsp rsp1 = new DeviceCmdChanelRsp(rsp.getData());
		System.out.println(rsp.getData().length);
		try {
			rsp1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(rsp1.toString());
	}

}
