package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.ProcedureFileStopUploadReq;
import cn.inovance.iotgp.common.msg.cd.ProcedureFileStopUploadRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class StoptTransferProcedureFileProcessor extends AbstractIoProcessor
implements ICdSimulatorMessageProcessor  {

	@Override
	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			ProcedureFileStopUploadReq req = new ProcedureFileStopUploadReq(data);
			req.parse();
			logger.info("[{}]过程文件停止上传请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 应答过程文件请求
			ProcedureFileStopUploadRsp rsp = new ProcedureFileStopUploadRsp();
			rsp.setErrorCode(ErrorCode.OK);
			rsp.setSessionID(req.getSessionID());
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]发送停止过程文件上传请求响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
			
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
