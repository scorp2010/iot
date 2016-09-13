package cn.inovance.iotgp.cdSimulator.processor.node.tempclient;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.thread.AudioDataHandler;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ITempClientMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.AudioHeaderRsp;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;

@Component
public class AudioHeaderRspProcessor extends AbstractIoProcessor implements ITempClientMessageProcessor {

	public void processMessage(IoSession session, byte[] data) {
		try {
			AudioHeaderRsp rsp = new AudioHeaderRsp(data);
			logger.info("音频头响应消息：{}", rsp.toString());

			if (rsp.getErrorCode() == ErrorCode.OK.getCode()) {
				String regCode = (String) session
						.getAttribute(StaticValues.KEY_IOSESSION_REGCODE);
				String mapKey = (String) session
						.getAttribute(StaticValues.KEY_IOSESSION_TEMPGUID);

				AudioDataHandler handler = (AudioDataHandler) (CdSimulatorMgr.cdSimulatorClientList
						.get(regCode).getThreadHandler(mapKey));

				handler.switchSignal(AudioDataHandler.SIGNAL_TRANSFER_AUDIOSTREAM); // 发送音频流消息
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
