package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;

/**
 * 数据流消息基类
 * 
 * @author c2100
 * 
 */
public abstract class AbstractStreamDataPackage extends AbstractPackage {

	public AbstractStreamDataPackage() {

	}

	public AbstractStreamDataPackage(byte[] data) {
		super(data);
	}

	public void construct() {
		data = new byte[this.getMsgLength()];
		this.header.construct(data);
		this.constructBody(this.data);
	}

	public void parse() throws MessageParseException {
		this.header.parse();
		this.parseBody();
	}

}
