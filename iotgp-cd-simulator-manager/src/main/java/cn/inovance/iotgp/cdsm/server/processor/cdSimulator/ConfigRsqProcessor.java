package cn.inovance.iotgp.cdsm.server.processor.cdSimulator;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.server.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdsm.server.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.ConfigRsp;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component
public class ConfigRsqProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	@Override
	public boolean processMessage(IoSession session, String message) {
		ConfigRsp rsq = JsonBinder.normalBinder.fromJson(message,
				ConfigRsp.class);
		logger.info("模拟器配置响应消息:{}", rsq.toString());
		return true;
	}

}
