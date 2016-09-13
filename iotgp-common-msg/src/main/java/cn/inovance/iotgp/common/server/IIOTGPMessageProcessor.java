package cn.inovance.iotgp.common.server;

import org.apache.mina.core.session.IoSession;

/**
 * 支援平台消息处理器接口
 * 
 * @author c2100
 */
public interface IIOTGPMessageProcessor {

	public boolean processMessage(IoSession session, String message);

}
