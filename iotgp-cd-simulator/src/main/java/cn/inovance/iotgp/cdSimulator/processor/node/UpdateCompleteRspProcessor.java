package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.pkg.PlaylistUpdateCompleteRsp;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.CtrlEventRsp;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@Component
public class UpdateCompleteRspProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			PlaylistUpdateCompleteRsp rsp = new PlaylistUpdateCompleteRsp(data);
			rsp.parse();
			logger.info("[{}]更新完成响应消息:{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
