package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@Component
public class CtrlProcedureRecordNotifyProcessor extends AbstractIoProcessor
		implements ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {

			logger.debug("[{}]过程记录通知消息:{}", nodeClient.getCdRegisterCode()
					.getRegCode(), ByteOps.bytes2HexStringWithBlank(data));

			nodeClient.sendCtrlEvent(); // 发送1条事件消息

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
