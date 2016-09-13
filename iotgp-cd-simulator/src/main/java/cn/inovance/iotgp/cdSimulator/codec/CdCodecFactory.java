package cn.inovance.iotgp.cdSimulator.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * ClassName: CdCodecFactory <br/>
 * Function: 实现ProtocolCodecFactory类，用于返回当前通信协议的Decoder与Encoder. <br/>
 * date: 2014-4-4 下午5:11:35 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class CdCodecFactory implements ProtocolCodecFactory {
	/**
	 * Cd接收数据对应的解码器
	 */
	private CdDecoder decoder;
	/**
	 * Cd发送数据对应的编码器
	 */
	private CdEncoder encoder;

	public CdEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(CdEncoder encoder) {
		this.encoder = encoder;
	}

	public CdCodecFactory() {
		decoder = new CdDecoder();
		encoder = new CdEncoder();
	}

	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {

		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {

		return encoder;
	}

}
