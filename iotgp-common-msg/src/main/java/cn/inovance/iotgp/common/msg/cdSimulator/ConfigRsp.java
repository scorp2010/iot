package cn.inovance.iotgp.common.msg.cdSimulator;

import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class ConfigRsp extends Response {

	public ConfigRsp() {
		this(SeqGenerator.next());
	}

	public ConfigRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.CONFIG_RSP;
	}
	
	public ConfigRsp(ConfigNofity req) {
		super(req);
		this.msgType = MsgType.CONFIG_RSP;
	}

}
