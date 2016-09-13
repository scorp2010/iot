package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.DeviceCmdChanelReq;
import cn.inovance.iotgp.common.msg.cd.DeviceCmdChanelRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class CmdChanelReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			DeviceCmdChanelReq req = new DeviceCmdChanelReq(data);
			req.parse();
			logger.info("[{}]命令行请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 命令行响应消息
			DeviceCmdChanelRsp rsp = new DeviceCmdChanelRsp();
			rsp.setErrorCode(0);
			rsp.setSeq(req.getSeq());
			rsp.setValue("reback from cdSimulator for cmd:"
					+ req.getValue().getValue());
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]发送命令行响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
