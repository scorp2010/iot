package cn.inovance.iotgp.cdSimulator.processor;

import org.apache.mina.core.session.IoSession;

public interface ITempClientMessageProcessor {

	void processMessage(IoSession session, byte[] data);
	
}
