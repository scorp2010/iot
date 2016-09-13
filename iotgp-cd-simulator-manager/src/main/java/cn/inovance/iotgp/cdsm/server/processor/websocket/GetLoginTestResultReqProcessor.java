package cn.inovance.iotgp.cdsm.server.processor.websocket;

import javax.websocket.Session;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.server.CdsmServer;
import cn.inovance.iotgp.cdsm.server.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdsm.server.processor.IWebSocketMessageProcessor;
import cn.inovance.iotgp.cdsm.session.TestResultSession;
import cn.inovance.iotgp.cdsm.websocket.msg.GetLoginTestResultReq;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginTestResultReq;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component
public class GetLoginTestResultReqProcessor extends AbstractIoProcessor implements IWebSocketMessageProcessor {

	@Override
	public boolean processMessage(Session session, String message) {
		GetLoginTestResultReq req = JsonBinder.normalBinder.fromJson(message,
				GetLoginTestResultReq.class);
		logger.info("获取登录测试结果请求消息:{}", req.toString());
		
		try{
			//清空现有测试结果
			TestResultSession.loginTestResultList.clear();
			
			//广播登录测试结果请求消息
			LoginTestResultReq loginTestResultReq = new LoginTestResultReq();
			loginTestResultReq.setFrom(StaticValues.sysAccount);
			CdsmServer.brodcastPacket(loginTestResultReq.toString());
			logger.info("广播登录测试结果请求消息:{}", loginTestResultReq.toString());
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
