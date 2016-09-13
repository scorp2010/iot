package cn.inovance.iotgp.cdsm.server.processor.cdSimulator;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.bean.CdSimulatorInfo;
import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.server.CdsmServer;
import cn.inovance.iotgp.cdsm.server.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdsm.server.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginReq;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginRsp;
import cn.inovance.iotgp.common.msg.server.ErrorCode;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component
public class LoginReqProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	@Override
	public boolean processMessage(IoSession session, String message) {
		boolean ret = true;
		LoginReq req = JsonBinder.normalBinder
				.fromJson(message, LoginReq.class);
		logger.info("模拟器登录请求消息:{}", req.toString());
		LoginRsp rsp = new LoginRsp(req);
		rsp.setFrom(StaticValues.sysAccount);
		
		try {
			// 增加到在线列表
			CdSimulatorInfo cdSimulatorInfo = CdsmServer.clientLinkList
					.get(session.getId());
			cdSimulatorInfo.setClientCode(req.getSecurityCode());
			cdSimulatorInfo.setLogined(true);
			CdsmServer.clientOnlineList.put(cdSimulatorInfo.getClientCode(),
					cdSimulatorInfo);
		} catch (Exception e) {
			rsp.setErrorCode(ErrorCode.SERVER_INTERAL_ERROR.getCode());
			rsp.setValue(e.getMessage());
			logger.error("登录请求消息处理失败:{}", e.getMessage());
			ret = false;
		}
		
		logger.info("模拟器登录响应消息:{}", rsp.toString());
		session.write(rsp.toString());
		return ret;
	}

}
