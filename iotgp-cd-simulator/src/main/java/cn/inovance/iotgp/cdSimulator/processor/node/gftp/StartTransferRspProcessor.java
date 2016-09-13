package cn.inovance.iotgp.cdSimulator.processor.node.gftp;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.thread.GftpHandler;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.IGftpeMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.GftpTransparentDownMsg;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferRsp;

@Component
public class StartTransferRspProcessor extends AbstractIoProcessor implements IGftpeMessageProcessor {

	public void processMessage(IoSession session, GftpTransparentDownMsg msg) {
		try {
			StartTransferRsp rsp = new StartTransferRsp(msg.getGftpCommand());

			logger.info("StartTransferRsp:[{}:{}]", rsp.getErrorCode().getValue(),
					rsp.getValue());

			if (rsp.getErrorCode().getValue() == ErrorCode.OK.getCode()) {
				String regCode = (String) session
						.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
				String mapKey = (String) session
						.getAttribute(StaticValues.KEY_IOSESSION_TEMPGUID);

				GftpHandler handler = (GftpHandler) (CdSimulatorMgr.cdSimulatorClientList
						.get(regCode).getThreadHandler(mapKey));
				
				handler.setFileLength(Integer.parseInt(rsp.getFileLength().toString()));
				handler.switchSignal(GftpHandler.SIGNAL_TRANSFER); // 发送文件数据
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
