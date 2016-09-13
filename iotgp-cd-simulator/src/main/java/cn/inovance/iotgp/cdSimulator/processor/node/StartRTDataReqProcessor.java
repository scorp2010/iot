package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.StartTdDataReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class StartRTDataReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			StartTdDataReq req = new StartTdDataReq(data);
			req.parse();
			logger.info("[{}]上报运行数据请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			ShortPdu[] hostShorts = req.getServerConnectionInfo().getHost();
			String host = String.format("%s.%s.%s.%s",
					hostShorts[0].getValue(), hostShorts[1].getValue(),
					hostShorts[2].getValue(), hostShorts[3].getValue());
			int port = req.getServerConnectionInfo().getPort().getValue();

			logger.info("rundata:"+host+"--"+port+"--"+req.getSecurityCode().getValue()+"--"+req.getTdSerial().getValue());
			// 启动运行数据上报线程
			nodeClient.startRTData(host, port,
					req.getSecurityCode().getValue(), req.getTdSerial()
							.getValue(), 3);
			
			/*nodeClient.requestRTData(host, port,
					req.getSecurityCode().getValue(), req.getTdSerial().getValue(),3);*/
			
			return true;
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
