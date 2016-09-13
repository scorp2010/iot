package cn.inovance.iotgp.cdSimulator.processor.node.cdsm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginTestResultReq;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginTestResultRsp;
import cn.inovance.iotgp.common.msg.cdSimulator.bean.LoginTestResult;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component("cdsmLoginTestResultReqProcessor")
public class LoginTestResultReqProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	public boolean processMessage(CdsmClient cdsmClient, String message) {
		boolean ret = true;
		LoginTestResultReq req = JsonBinder.normalBinder.fromJson(message,
				LoginTestResultReq.class);
		logger.info("收到CDSM的获取登录测试结果请求消息:{}", req.toString());

		try {
			List<LoginTestResult> loginTestResults = CdSimulatorMgr
					.getLoginTestResults();
			int totalCount = loginTestResults.size();
			int i = 0;
			while (i < totalCount) {
				List<LoginTestResult> loginTestResultsNow = new ArrayList<LoginTestResult>();
				int j = (totalCount - i) > 100 ? 100 : totalCount - i;
				for (int k = 0; k < j; k++) {
					loginTestResultsNow.add(loginTestResults.get(i + k));
				}
				i += j;
				LoginTestResultRsp rsp = new LoginTestResultRsp(req);
//				rsp.setLoginTestResults(loginTestResultsNow);
				logger.info("获取登录测试结果响应消息:{}", rsp.toString());
				cdsmClient.send(rsp.toString());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			logger.error("获取登录测试结果请求消息处理失败:{}", e.getMessage());
			ret = false;
		}
		return ret;
	}

}
