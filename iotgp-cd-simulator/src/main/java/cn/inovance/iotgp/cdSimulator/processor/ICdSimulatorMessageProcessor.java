package cn.inovance.iotgp.cdSimulator.processor;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;

public interface ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data);

}
