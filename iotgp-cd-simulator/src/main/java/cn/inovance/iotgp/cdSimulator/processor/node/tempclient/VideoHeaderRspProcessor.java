package cn.inovance.iotgp.cdSimulator.processor.node.tempclient;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.thread.VideoDataHandler;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ITempClientMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.VideoHeaderRsp;

@Component
public class VideoHeaderRspProcessor extends AbstractIoProcessor implements ITempClientMessageProcessor {

	public void processMessage(IoSession session, byte[] data) {
		try {
			VideoHeaderRsp rsp = new VideoHeaderRsp(data);
			rsp.parse();
			logger.info("视频头响应消息：{}", rsp.toString());

			if (rsp.getErrorCode() == ErrorCode.OK.getCode()) {
				String regCode = (String) session
						.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
				String mapKey = (String) session
						.getAttribute(StaticValues.KEY_IOSESSION_TEMPGUID);

				VideoDataHandler handler = (VideoDataHandler) (CdSimulatorMgr.cdSimulatorClientList
						.get(regCode).getThreadHandler(mapKey));

				handler.switchSignal(VideoDataHandler.SIGNAL_TRANSFER_VIDEOSTREAM); // 发送视频流消息
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
