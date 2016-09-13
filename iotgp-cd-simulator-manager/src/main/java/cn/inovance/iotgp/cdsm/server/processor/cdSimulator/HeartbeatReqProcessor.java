package cn.inovance.iotgp.cdsm.server.processor.cdSimulator;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.server.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdsm.server.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.HeartbeatReq;
import cn.inovance.iotgp.common.msg.cdSimulator.HeartbeatRsp;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component
public class HeartbeatReqProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	@Override
	public boolean processMessage(IoSession session, String message) {
		boolean ret = true;
		HeartbeatReq req = JsonBinder.normalBinder.fromJson(message,
				HeartbeatReq.class);
		logger.debug("模拟器心跳通知消息:{}", req.toString());
		HeartbeatRsp rsp = new HeartbeatRsp(req);
		rsp.setFrom(StaticValues.sysAccount);
		rsp.setSecurityCode(req.getSecurityCode());

		logger.debug("模拟器心跳响应消息:{}", rsp.toString());
		session.write(rsp.toString());
		return ret;
	}

}
