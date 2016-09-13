package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.StopTdDataReq;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class StopRTDataReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			StopTdDataReq req = new StopTdDataReq(data);
			req.parse();
			logger.info("[{}]停止上报控制器运行数据消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			nodeClient.shutRTData(req.getTdSerial().toString()); //停止控制器运行数据上报线程

			return true;

		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
