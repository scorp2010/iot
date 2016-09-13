package cn.inovance.iotgp.cdSimulator.processor;

import cn.inovance.iotgp.cdSimulator.client.CdsmClient;

public interface ICdsmMessageProcessor {

	public boolean processMessage(CdsmClient cdsmClient, String message);
	
}
