package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.DeviceParameterUpdateReq;
import cn.inovance.iotgp.common.msg.cd.DeviceParameterUpdateRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class ParamUpdateReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			DeviceParameterUpdateReq req = new DeviceParameterUpdateReq(data);
			req.parse();
			logger.info("[{}]参数更新请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 系统参数更新响应
			DeviceParameterUpdateRsp rsp = new DeviceParameterUpdateRsp();
			rsp.setSeq(req.getSeq());
			rsp.setErrorCode(0);
			rsp.setResultMap(req.getParameters());
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]发送参数更新响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());

		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
