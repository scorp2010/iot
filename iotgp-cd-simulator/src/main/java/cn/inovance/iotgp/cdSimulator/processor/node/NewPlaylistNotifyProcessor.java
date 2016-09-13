package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.pkg.NewPlaylistUpdateNotify;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.CtrlEventRsp;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@Component
public class NewPlaylistNotifyProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			NewPlaylistUpdateNotify notify = new NewPlaylistUpdateNotify(data);
			notify.parse();
			logger.info("[{}]新播放列表更新消息:{}", nodeClient.getCdRegisterCode()
					.getRegCode(), notify.toString());
			nodeClient.sendGetPlaylistReq();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
