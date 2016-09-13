package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.VideoStopReq;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class VideoStopReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {
	
	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			VideoStopReq req = new VideoStopReq(data);
			req.parse();
			logger.info("[{}]停止视频连接消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			nodeClient.shutVideo(req.getSecurityCode().getValue()); //停止视频上报线程

			return true;

		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
