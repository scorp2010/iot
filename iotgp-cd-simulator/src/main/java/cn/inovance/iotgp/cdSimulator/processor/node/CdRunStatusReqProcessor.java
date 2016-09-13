package cn.inovance.iotgp.cdSimulator.processor.node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.DeviceRunStatusReq;
import cn.inovance.iotgp.common.msg.cd.DeviceRunStatusRsp;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class CdRunStatusReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			DeviceRunStatusReq req = new DeviceRunStatusReq(data);
			req.parse();
			logger.info("[{}]设备运行状态请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 上报采集设备运行状态响应
			DeviceRunStatusRsp rsp = new DeviceRunStatusRsp();
			List<Short> reqlist = req.getShortDevicemodules();
			Map<ShortPdu, StringPdu> rspMap = new HashMap<ShortPdu, StringPdu>(
					0);
			for (Short module : reqlist) {
				rspMap.put(new ShortPdu((short)1),
						new StringPdu(module.toString()
								+ "inovance:inovave;baidu:baidu;tencent:qq;"));
			}
			rsp.setErrorCode(0);
			rsp.setDevicemodules(rspMap);
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]上报设备运行状态响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
