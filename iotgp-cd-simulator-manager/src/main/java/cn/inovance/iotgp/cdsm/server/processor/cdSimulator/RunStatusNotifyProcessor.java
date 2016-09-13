package cn.inovance.iotgp.cdsm.server.processor.cdSimulator;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.bean.CdSimulatorInfo;
import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.server.CdsmServer;
import cn.inovance.iotgp.cdsm.server.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdsm.server.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.RunStatusNotify;
import cn.inovance.iotgp.common.msg.cdSimulator.RunStatusRsp;
import cn.inovance.iotgp.common.msg.server.ErrorCode;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component
public class RunStatusNotifyProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	@Override
	public boolean processMessage(IoSession session, String message) {
		boolean ret = true;
		RunStatusNotify notify = JsonBinder.normalBinder.fromJson(message,
				RunStatusNotify.class);
		logger.debug("模拟器运行状态通知消息:{}", notify.toString());
		RunStatusRsp rsp = new RunStatusRsp(notify);
		rsp.setFrom(StaticValues.sysAccount);
		
		try {
			// 更新在线数量
			CdSimulatorInfo cdSimulatorInfo = CdsmServer.clientLinkList
					.get(session.getId());
			cdSimulatorInfo.setDistributeCount(notify.getDistributeCount());
			cdSimulatorInfo.setConnectCount(notify.getConnectCount());
			cdSimulatorInfo.setOnlineCount(notify.getOnlineCount());
			cdSimulatorInfo.setSimulateDeviceInfoMap(notify.getSimulateDeviceInfoMap());
		} catch (Exception e) {
			rsp.setErrorCode(ErrorCode.SERVER_INTERAL_ERROR.getCode());
			rsp.setValue(e.getMessage());
			logger.error("模拟器运行状态通知消息处理失败:{}", e.getMessage());
			ret = false;
		}

		logger.debug("模拟器运行状态响应消息:{}", rsp.toString());
		session.write(rsp.toString());
		return ret;
	}
}
