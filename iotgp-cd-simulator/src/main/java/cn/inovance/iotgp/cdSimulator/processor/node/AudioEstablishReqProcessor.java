package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.AudioEstablishReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class AudioEstablishReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			AudioEstablishReq req = new AudioEstablishReq(data);
			req.parse();
			logger.info("[{}]音频连接请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 启动音频数据上报线程
			ShortPdu[] hostShorts = req.getServerConnectionInfo().getHost();
			String host = String.format("%s.%s.%s.%s",
					hostShorts[0].getValue(), hostShorts[1].getValue(),
					hostShorts[2].getValue(), hostShorts[3].getValue());
			
			nodeClient.startAudio(host, req.getServerConnectionInfo()
					.getPort().getValue(), req.getSecurityCode(),req.getChannel().getValue(), 1);

			return true;
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
