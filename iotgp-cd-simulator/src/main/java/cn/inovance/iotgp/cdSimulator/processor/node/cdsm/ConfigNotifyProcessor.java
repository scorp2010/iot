package cn.inovance.iotgp.cdSimulator.processor.node.cdsm;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.main.CdSimulator;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdsmMessageProcessor;
import cn.inovance.iotgp.common.msg.cdSimulator.ConfigNofity;
import cn.inovance.iotgp.common.msg.cdSimulator.ConfigRsp;
import cn.inovance.iotgp.common.msg.server.ErrorCode;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

@Component("cdsmConfigNotifyProcessor")
public class ConfigNotifyProcessor extends AbstractIoProcessor implements
		ICdsmMessageProcessor {

	public boolean processMessage(CdsmClient cdsmClient, String message) {
		boolean ret = true;
		
		ConfigNofity notify = JSON.parseObject(message,ConfigNofity.class);
		
		logger.info("收到CDSM的模拟器参数配置通知消息:{}", notify.toJsonString(notify));
		ConfigRsp rsp = new ConfigRsp(notify);

		try {
			logger.info("开始关闭原模拟设备连接...");
			CdSimulator.cdSimulatorMgr.stop();
			while (CdSimulatorMgr.cdSimulatorClientList.size() > 0) {
				Thread.sleep(2000);
				logger.warn("等待原设备连接断开,有待关闭连接数：{}",
						CdSimulatorMgr.cdSimulatorClientList.size());
			}
			logger.info("开始启动新模拟设备连接...");
			CdSimulator.cdSimulatorMgr.start(notify.getSelectSql(),
					notify.getStart(), notify.getCount(),
					notify.isOnlyTestLogin(),notify.getBatchHandlerFlag());
		} catch (Exception e) {
			rsp.setErrorCode(ErrorCode.SERVER_INTERAL_ERROR.getCode());
			rsp.setValue(e.getMessage());
			logger.error("模拟器参数配置通知消息处理失败:{}", e.getMessage());
			ret = false;
		}

		logger.info("模拟器参数配置响应消息:{}", rsp.toString());
		cdsmClient.send(rsp.toString());
		return ret;
	}

}
