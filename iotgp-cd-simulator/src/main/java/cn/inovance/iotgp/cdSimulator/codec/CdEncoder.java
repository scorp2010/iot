package cn.inovance.iotgp.cdSimulator.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 报文编码器
 */
public class CdEncoder extends ProtocolEncoderAdapter {

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {

		IoBuffer buf = null;

		buf = IoBuffer.wrap((byte[]) message);

		out.write(buf);
	}

}
