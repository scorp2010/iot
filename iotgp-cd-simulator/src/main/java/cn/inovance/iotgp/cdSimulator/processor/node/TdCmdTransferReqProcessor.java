/**
 * Project Name:iotgp-cd-simulator
 * File Name:TdCmdTransferReqProcessor.java
 * Package Name:cn.inovance.iotgp.cdSimulator.processor.node
 * Date:2015-8-5下午5:56:44
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.cdSimulator.processor.node;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.common.msg.cd.TdCmdTransferReq;
import cn.inovance.iotgp.common.msg.cd.TdCmdTransferRsp;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

/**
 * ClassName:TdCmdTransferReqProcessor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-8-5 下午5:56:44 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
public class TdCmdTransferReqProcessor extends AbstractIoProcessor implements
ICdSimulatorMessageProcessor{

	@Override
	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			TdCmdTransferReq req = new TdCmdTransferReq(data);
			req.parse();
			TdCmdTransferRsp rsp = new TdCmdTransferRsp();
			logger.info("[{}]设备指令请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());
			byte [] bb = new String("ttet").getBytes();
			rsp.setCmdRspData(bb);
			rsp.setCmdRspDataLength(bb.length);
			rsp.setMsgSeq(req.getMsgSeq());
			rsp.setTdCode(req.getTdCode());
			rsp.setTdType(req.getTdType());
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]上报设备指令响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
		}catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

