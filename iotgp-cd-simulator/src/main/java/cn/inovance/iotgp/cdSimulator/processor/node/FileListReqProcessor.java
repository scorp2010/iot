package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.FileListReq;
import cn.inovance.iotgp.common.msg.cd.FileListRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class FileListReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			FileListReq req = new FileListReq(data);
			req.parse();
			logger.info("[{}]资源文件列表请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 应答资源文件列表
			FileListRsp rsp = new FileListRsp();
			rsp.setErrorCode(ErrorCode.OK);
			rsp.setSeq((short) 1);
			rsp.addFile("fileXXX.txt");
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
