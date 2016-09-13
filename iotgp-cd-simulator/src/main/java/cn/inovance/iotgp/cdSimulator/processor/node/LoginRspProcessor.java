package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.DeviceLoginRsp;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;

@Component
public class LoginRspProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			DeviceLoginRsp rsp = new DeviceLoginRsp(data);
			rsp.parse();
			logger.info("[{}]登录响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
			if (rsp.getErrorCode() == ErrorCode.OK.getCode()) {
				logger.info("[{}]登录成功,code:{}", nodeClient.getCdRegisterCode()
						.getRegCode(),rsp.getErrorCode());

				nodeClient.processLoginSucc(); // 调用登陆成功后处理方法
			} else if (rsp.getErrorCode() == DeviceLoginRsp.ERROR_SERVER_RELOCATE) {
				logger.info("[{}]服务器重定向响应：{}", nodeClient.getCdRegisterCode()
						.getRegCode(), rsp.toString());
				String host = String.format("%s.%s.%s.%s", rsp
						.getServerConnectionInfo().getHost()[0].toString(), rsp
						.getServerConnectionInfo().getHost()[1].toString(), rsp
						.getServerConnectionInfo().getHost()[2].toString(), rsp
						.getServerConnectionInfo().getHost()[3].toString());
				nodeClient.swichServer(host, rsp.getServerConnectionInfo()
						.getPort().getValue());
			} else {
				logger.warn("[{}]采集设备鉴权失败：{}", nodeClient.getCdRegisterCode()
						.getRegCode(), rsp.getErrorCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
