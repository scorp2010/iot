package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class FileTransferProgressNotify extends AbstractRspPackage {

	/** 消息序号 */
	private ShortPdu seq = new ShortPdu();
	/** 上/下载进度信息. */
	private ShortPdu transferProgress = new ShortPdu((short) 0);

	public short getSeq() {
		return seq.getValue();
	}

	public void setSeq(short seq) {
		this.seq = new ShortPdu(seq);
	}

	public short getTransferProgress() {
		return transferProgress.getValue();
	}

	public void setTransferProgress(short transferProgress) {
		this.transferProgress = new ShortPdu(transferProgress);
	}

	public FileTransferProgressNotify() {
		this.header.setMsgType(Commands.FILE_TRANSFER_PROGRESS_NOTIFY);
	}

	public FileTransferProgressNotify(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, transferProgress.getBytes(), index);
		index += transferProgress.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		transferProgress = new ShortPdu(index, data);
		index += transferProgress.getLength();
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.errorCode.getLength()
				+ this.transferProgress.getLength();
	}

	@Override
	public String toString() {
		return "FileTransferProgressNotify [errorCode=" + errorCode
				+ ", transferProgress=" + transferProgress + "]";
	}

	public static void main(String[] args) {
		FileTransferProgressNotify rsp = new FileTransferProgressNotify();
		rsp.setErrorCode(ErrorCode.OK);
		rsp.setSeq((short) 1);
		rsp.setTransferProgress((short) 100);
		rsp.construct();
		System.out.println(ByteOps.bytesToHexString(rsp.getData()));
		FileTransferProgressNotify rsp1 = new FileTransferProgressNotify(
				rsp.getData());
		try {
			System.out.println(rsp1.getData().length);
			rsp1.parse();
			System.out.println(rsp1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
