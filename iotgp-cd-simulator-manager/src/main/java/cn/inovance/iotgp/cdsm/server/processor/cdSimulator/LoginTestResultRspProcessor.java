package cn.inovance.iotgp.cdsm.server.processor.cdSimulator;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.server.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdsm.server.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.cdsm.session.TestResultSession;
import cn.inovance.iotgp.cdsm.websocket.CdsmWebSocket;
import cn.inovance.iotgp.cdsm.websocket.msg.GetLoginTestResultRsp;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginTestResultRsp;
import cn.inovance.iotgp.common.msg.cdSimulator.bean.LoginTestResult;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component
public class LoginTestResultRspProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	@Override
	public boolean processMessage(IoSession session, String message) {
		LoginTestResultRsp rsp = JsonBinder.normalBinder.fromJson(message,
				LoginTestResultRsp.class);
		logger.info("模拟器登录测试响应消息:{}", rsp.toString());
		
		try {
			// 更新登录测试结果缓存
			for (LoginTestResult result : rsp.getLoginTestResults()) {
				TestResultSession.loginTestResultList.put(result.getRegCode(),
						result);
			}
			
			//通知界面websocket
			GetLoginTestResultRsp getLoginTestResultRsp = new GetLoginTestResultRsp();
			CdsmWebSocket.broadcastMsg(getLoginTestResultRsp.toString());
		} catch (Exception e) {
			logger.error("模拟器登录测试响应消息处理失败:{}", e.getMessage());
			return false;
		}

		return true;
	}

}
