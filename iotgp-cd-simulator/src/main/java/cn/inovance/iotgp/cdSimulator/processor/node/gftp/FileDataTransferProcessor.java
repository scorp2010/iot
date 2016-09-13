package cn.inovance.iotgp.cdSimulator.processor.node.gftp;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.thread.GftpHandler;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.IGftpeMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.GftpTransparentDownMsg;
import cn.inovance.iotgp.common.msg.cd.gftp.FileDataTransfer;

@Component
public class FileDataTransferProcessor extends AbstractIoProcessor implements
		IGftpeMessageProcessor {

	public void processMessage(IoSession session, GftpTransparentDownMsg msg) {
		try {
			FileDataTransfer transfer = new FileDataTransfer(
					msg.getGftpCommand());

			logger.info("FileDataTransfer:[{}]", transfer.getValue());

			String regCode = (String) session
					.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
			String mapKey = (String) session
					.getAttribute(StaticValues.KEY_IOSESSION_TEMPGUID);

			GftpHandler handler = (GftpHandler) (CdSimulatorMgr.cdSimulatorClientList
					.get(regCode).getThreadHandler(mapKey));

			handler.mergeFileData(
					Integer.parseInt(transfer.getFileOffset().toString()),
					transfer.getFileData());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
