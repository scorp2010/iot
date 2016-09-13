package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.VideoEstablishReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class VideoEstablishReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {
	
	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			VideoEstablishReq req = new VideoEstablishReq(data);
			req.parse();
			logger.info("[{}]视频连接请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 启动视频数据上报线程
			ShortPdu[] hostShorts = req.getServerConnectionInfo().getHost();
			String host = String.format("%s.%s.%s.%s",
					hostShorts[0].getValue(), hostShorts[1].getValue(),
					hostShorts[2].getValue(), hostShorts[3].getValue());
			logger.info("启动视频数据上报:host={},port={},securityCode={},cameraSerial={}",host,req.getServerConnectionInfo().getPort().getValue(), req.getSecurityCode(),req.getCameraSerial());
			nodeClient.startVideo(host, req.getServerConnectionInfo()
					.getPort().getValue(), req.getSecurityCode(),req.getCameraSerial(), 1);

			return true;
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
