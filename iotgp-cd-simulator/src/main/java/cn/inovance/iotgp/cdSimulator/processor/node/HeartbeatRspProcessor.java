package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.CdHeartbeatRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class HeartbeatRspProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			CdHeartbeatRsp rsp = new CdHeartbeatRsp(data);
			rsp.parse();

			logger.debug("[{}]收到心跳响应消息:{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
