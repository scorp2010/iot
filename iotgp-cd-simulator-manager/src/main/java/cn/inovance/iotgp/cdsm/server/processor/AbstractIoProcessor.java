package cn.inovance.iotgp.cdsm.server.processor;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mina消息处理器基类
 */
public abstract class AbstractIoProcessor {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public void sendMessage(IoSession session, String message) {
		if (session != null) {
			session.write(message);
			logger.debug("发送消息:" + message);
		}
	}

}
