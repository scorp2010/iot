package cn.inovance.iotgp.cdSimulator.processor;

import org.apache.mina.core.session.IoSession;

import cn.inovance.iotgp.common.msg.cd.GftpTransparentDownMsg;

public interface IGftpeMessageProcessor {

	void processMessage(IoSession session, GftpTransparentDownMsg msg);
	
}
