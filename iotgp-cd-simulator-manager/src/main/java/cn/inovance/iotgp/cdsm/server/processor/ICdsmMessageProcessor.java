package cn.inovance.iotgp.cdsm.server.processor;

import org.apache.mina.core.session.IoSession;

public interface ICdsmMessageProcessor {

	public boolean processMessage(IoSession session, String message);
	
}
