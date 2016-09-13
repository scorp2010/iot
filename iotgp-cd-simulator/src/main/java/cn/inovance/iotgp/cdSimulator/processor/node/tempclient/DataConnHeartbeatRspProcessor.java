package cn.inovance.iotgp.cdSimulator.processor.node.tempclient;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ITempClientMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.CdHeartbeatRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class DataConnHeartbeatRspProcessor extends AbstractIoProcessor
		implements ITempClientMessageProcessor {

	public void processMessage(IoSession session, byte[] data) {
		try {
			CdHeartbeatRsp rsp = new CdHeartbeatRsp(data);
			rsp.parse();

			logger.debug("数据心跳响应消息:{}", rsp.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
	}

}
