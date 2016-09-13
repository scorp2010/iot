package cn.inovance.iotgp.cdsm.server.processor;

import javax.websocket.Session;

public interface IWebSocketMessageProcessor {

	public boolean processMessage(Session session, String message);

}
