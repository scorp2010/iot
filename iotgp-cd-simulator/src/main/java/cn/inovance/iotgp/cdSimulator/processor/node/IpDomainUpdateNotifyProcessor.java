package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.DeviceIpDns;
import cn.inovance.iotgp.common.msg.cd.DeviceServerIpDomainUpdateNotify;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class IpDomainUpdateNotifyProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			DeviceServerIpDomainUpdateNotify notify = new DeviceServerIpDomainUpdateNotify(
					data);
			notify.parse();
			logger.info("[{}]IP/DNS更新消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), notify.toString());
			
			//切换连接
			//DeviceIpDns ipDns = notify.getIpDomains().get(0);
			//nodeClient.swichServer(ipDns.getDns().getValue(),ipDns.getPort().getValue());
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
