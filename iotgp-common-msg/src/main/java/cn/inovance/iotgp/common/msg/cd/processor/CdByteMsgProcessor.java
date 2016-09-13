package cn.inovance.iotgp.common.msg.cd.processor;

import org.apache.mina.core.session.IoSession;

/**
 * 采集设备原始消息体处理器.
 * 
 * @author z1979
 * 
 */
public interface CdByteMsgProcessor {

	public boolean process(IoSession session, byte[] byteMsg);

}
