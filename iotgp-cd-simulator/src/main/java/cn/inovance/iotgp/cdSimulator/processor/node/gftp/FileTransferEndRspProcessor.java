package cn.inovance.iotgp.cdSimulator.processor.node.gftp;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.IGftpeMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.GftpTransparentDownMsg;
import cn.inovance.iotgp.common.msg.cd.gftp.EndTransferRsp;

@Component
public class FileTransferEndRspProcessor extends AbstractIoProcessor implements IGftpeMessageProcessor {

	public void processMessage(IoSession session, GftpTransparentDownMsg msg) {
		try {
			EndTransferRsp rsp = new EndTransferRsp(msg.getGftpCommand());
			logger.info("EndTransferRsp:" + rsp.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
