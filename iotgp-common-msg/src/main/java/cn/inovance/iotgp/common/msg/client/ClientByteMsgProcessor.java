package cn.inovance.iotgp.common.msg.client;

import org.apache.mina.core.session.IoSession;

/**
 * 音/视频客户端消息处理器.
 * 
 * @author c2100
 * 
 */
public interface ClientByteMsgProcessor {

	public boolean process(IoSession session, byte[] byteMsg);
	
}
