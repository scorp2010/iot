package cn.inovance.iotgp.common.msg.cd.processor;

import org.apache.mina.core.session.IoSession;

import cn.inovance.iotgp.common.msg.server.DeviceDataTransferReq;

/**
 * 透传消息体处理器.
 * 
 * @author zhangqiang
 * 
 */
public interface CdMsgProcessor {

	public boolean process(IoSession session, DeviceDataTransferReq transferMsg);

}
