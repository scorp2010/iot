package cn.inovance.iotgp.common.server;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支援平台消息处理器基类
 * 
 * @author c2100
 * 
 */
public class AbstractIoProcessor {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public void sendMessage(IoSession session, String message) {
		if (session != null) {
			session.write(message);
			logger.debug("发送消息:" + message);
		}
	}
}
