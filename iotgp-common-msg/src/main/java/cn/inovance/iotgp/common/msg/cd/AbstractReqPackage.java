package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;

/**
 * 请求消息基类
 * 
 * @author c2100
 * 
 */
public abstract class AbstractReqPackage extends AbstractPackage {

	public AbstractReqPackage() {

	}

	public AbstractReqPackage(byte[] data) {
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
