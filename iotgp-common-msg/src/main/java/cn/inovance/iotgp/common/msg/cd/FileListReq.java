package cn.inovance.iotgp.common.msg.cd;

import java.util.Date;

import cn.inovance.iotgp.common.msg.cd.meta.DatePdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class FileListReq extends AbstractReqPackage {

	/** 消息序号 */
	private ShortPdu seq = new ShortPdu();
	/** 开始日期 */
	private DatePdu startDate = new DatePdu(new Date());
	/** 结束日期 */
	private DatePdu endDate = new DatePdu(new Date());
	/** 资源类型 */
	private ShortPdu resourceType = new ShortPdu();

	public ShortPdu getSeq() {
		return seq;
	}

	public void setSeq(ShortPdu seq) {
		this.seq = seq;
	}

	public DatePdu getStartDate() {
		return startDate;
	}

	public void setStartDate(DatePdu startDate) {
		this.startDate = startDate;
	}

	public DatePdu getEndDate() {
		return endDate;
	}

	public void setEndDate(DatePdu endDate) {
		this.endDate = endDate;
	}

	public ShortPdu getResourceType() {
		return resourceType;
	}

	public void setResourceType(ShortPdu resourceType) {
		this.resourceType = resourceType;
	}

	public FileListReq() {
		this.header.setMsgType(Commands.FILE_LIST_REQ);
	}

	public FileListReq(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, seq.getBytes(), index);
		index += seq.getLength();
		ByteOps.addByteArray(data, startDate.getBytes(), index);
		index += startDate.getLength();
		ByteOps.addByteArray(data, endDate.getBytes(), index);
		index += endDate.getLength();
		ByteOps.addByteArray(data, resourceType.getBytes(), index);
		index += resourceType.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		seq = new ShortPdu(index, data);
		index += seq.getLength();
		startDate = new DatePdu(index, data);
		index += seq.getLength();
		endDate = new DatePdu(index, data);
		index += seq.getLength();
		resourceType = new ShortPdu(index, data);
		index += seq.getLength();
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + seq.getLength()
				+ this.startDate.getLength() + endDate.getLength()
				+ resourceType.getLength();
	}

	@Override
	public String toString() {
		return "FileListReq [seq=" + seq + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", resourceType=" + resourceType
				+ "]";
	}

}
