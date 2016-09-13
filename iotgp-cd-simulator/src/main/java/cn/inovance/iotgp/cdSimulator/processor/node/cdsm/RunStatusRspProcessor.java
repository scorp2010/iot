package cn.inovance.iotgp.cdSimulator.processor.node.cdsm;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.RunStatusRsp;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component("cdsmRunStatusRspProcessor")
public class RunStatusRspProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	public boolean processMessage(CdsmClient cdsmClient, String message) {
		try {
			RunStatusRsp rsp = JsonBinder.normalBinder.fromJson(message,
					RunStatusRsp.class);
			logger.debug("收到CDSM的运行状态响应消息:{}", rsp.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
