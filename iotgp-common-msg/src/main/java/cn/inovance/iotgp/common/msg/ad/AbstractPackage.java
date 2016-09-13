package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;

public abstract class AbstractPackage {
	
	protected Header header=new Header();
	
	protected int commandid; 
	
	protected byte[] data;
	
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

	abstract protected void constructBody(byte[] data);
	
	abstract protected void parseBody() throws MessageParseException;
}
