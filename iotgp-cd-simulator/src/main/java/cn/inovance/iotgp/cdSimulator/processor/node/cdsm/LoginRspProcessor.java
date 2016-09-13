package cn.inovance.iotgp.cdSimulator.processor.node.cdsm;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cdSimulator.LoginRsp;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component("cdsmLoginRspProcessor")
public class LoginRspProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	public boolean processMessage(CdsmClient cdsmClient, String message) {
		try {
			LoginRsp rsp = JsonBinder.normalBinder.fromJson(message,
					LoginRsp.class);
			if (rsp.getErrorCode() == ErrorCode.OK.getCode()) {
				cdsmClient.setLogined(true);
				logger.info("登录CDSM[{}:{}]成功", cdsmClient.getHost(),
						cdsmClient.getPort());
			} else {
				logger.warn("登录CDSM[{}:{}]失败:[{},{}],程序将退出", cdsmClient.getHost(),
						cdsmClient.getPort(), rsp.getErrorCode(),
						rsp.getValue());
				
				System.exit(0);  //退出程序
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
