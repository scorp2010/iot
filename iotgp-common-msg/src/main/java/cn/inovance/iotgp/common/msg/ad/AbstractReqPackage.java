package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;

public abstract class AbstractReqPackage extends AbstractPackage{
	
	public void construct() {
		this.header.construct(data);
		this.constructBody(this.data);
	}

	
	public void parse() throws MessageParseException {
		this.header.parse();
		this.parseBody();
	}

}
