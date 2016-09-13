package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.CtrlEventRsp;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@Component
public class CtrlEventRspProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			CtrlEventRsp rsp = new CtrlEventRsp(data);
			rsp.parse();
			logger.info("[{}]事件确认消息:{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
