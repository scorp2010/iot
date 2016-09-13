package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;

public abstract class AbstractPackage {

	protected Header header = new Header();

	protected int index = Header.LENGTH_HEADER;

	protected byte[] data;

	public AbstractPackage() {

	}

	public AbstractPackage(byte[] data) {
		this.data = data;
		this.header.setData(data);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getPackageLength() {
		return (data != null) ? data.length : 0;
	}

	abstract protected void constructBody(byte[] data);

	abstract protected void parseBody() throws MessageParseException;

	abstract protected int getMsgLength();
}
