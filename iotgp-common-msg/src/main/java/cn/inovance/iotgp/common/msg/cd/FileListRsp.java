package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.cd.meta.FileNamePdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class FileListRsp extends AbstractRspPackage {

	/** 消息序号 */
	private ShortPdu seq = new ShortPdu();
	/** 文件列表 */
	private List<FileNamePdu> fileList = new ArrayList<FileNamePdu>();

	public short getSeq() {
		return seq.getValue();
	}

	public void setSeq(short seq) {
		this.seq = new ShortPdu(seq);
	}

	public List<FileNamePdu> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileNamePdu> fileList) {
		this.fileList = fileList;
	}

	public void addFile(String fileName) {
		this.fileList.add(new FileNamePdu(fileName));
	}

	public FileListRsp() {
		this.header.setMsgType(Commands.FILE_LIST_RSP);
	}

	public FileListRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, seq.getBytes(), index);
		index += seq.getLength();
		for (FileNamePdu file : fileList) {
			ByteOps.addByteArray(data, file.getBytes(), index);
			index += file.getLength();
		}
	}

	@Override
	protected void parseBody() throws MessageParseException {
		seq = new ShortPdu(index, data);
		index += seq.getLength();
		while (index < data.length) {
			ShortPdu fileNameLength = new ShortPdu(index, data);
			index += fileNameLength.getLength();
			StringPdu fileName = new StringPdu(fileNameLength.getValue(),
					index, data);
			index += fileName.getLength();
			this.addFile(fileName.getValue());
		}
	}

	@Override
	protected int getMsgLength() {
		int protocolLength = Header.LENGTH_HEADER + this.seq.getLength() + this.errorCode.getLength();
		for (FileNamePdu file : fileList) {
			protocolLength += file.getLength();
		}
		return protocolLength;
	}

	@Override
	public String toString() {
		return "FileListRsp [errorCode=" + errorCode + ",seq=" + seq
				+ ", fileList=" + fileList + "]";
	}

	public static void main(String[] args) {
		FileListRsp rsp = new FileListRsp();
		rsp.setErrorCode(ErrorCode.OK);
		rsp.setSeq((short) 1);
		rsp.addFile("fileXXX.txt");
		rsp.construct();
		System.out.println(ByteOps.bytesToHexString(rsp.getData()));
		try {
			FileListRsp rsp1 = new FileListRsp(rsp.getData());
			rsp1.parse();
			System.out.println(rsp1.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
