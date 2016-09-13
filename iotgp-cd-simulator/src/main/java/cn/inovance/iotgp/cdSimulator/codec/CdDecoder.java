package cn.inovance.iotgp.cdSimulator.codec;

import org.apache.mina.core.buffer.BufferDataException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName: CdDecoder <br/>
 * Function: 继承CumulativeProtocolDecoder类，用于读取字节流中关于包长度的信息，待完整接收到数据包之后再交下一层处理. <br/>
 * date: 2014-4-4 下午5:14:03 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class CdDecoder extends CumulativeProtocolDecoder {

	/**
	 * 重载父类的doDecode方法 判断buffer的长度是否与数据包头所指示的一致，然后读取相应长度的字节组成完整的数据包送下一步处理.
	 * 
	 * @see org.apache.mina.filter.codec.CumulativeProtocolDecoder#doDecode<br/>
	 *      (org.apache.mina.core.session.IoSession,<br/>
	 *      org.apache.mina.core.buffer.IoBuffer,
	 *      org.apache.mina.filter.codec.ProtocolDecoderOutput)
	 * @param session
	 *            当前与采集设备连接对应的session
	 * @param in
	 *            接收消息的缓存
	 * @param out
	 *            送下一步处理的完整数据包
	 * @throws Exception
	 *             当处理过程中出现错误则抛出这个异常
	 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {

		if (prefixedDataAvailable(in, 2)) {
			byte[] la = new byte[2];
			in.get(la, 0, 2);
			int length = Header.getPacketLength(la);  //使用prefixedDataAvailable返回的datalength替换
			byte[] bytes = new byte[length];
			bytes[0] = la[0];
			bytes[1] = la[1];
			in.get(bytes, 2, length - 2);
			out.write(bytes);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断IoBuffer内未读取的内容与包头中指示的长度是否相符.
	 * 
	 * @param buffer
	 *            存放接收到数据包的缓存
	 * @param prefixLength
	 *            存放长度消息的字节数
	 * @return 如相符返回true，否则返回false
	 */
	public boolean prefixedDataAvailable(IoBuffer buffer, int prefixLength) {
		if (buffer.remaining() < prefixLength) {
			return false;
		}

		int dataLength;
		switch (prefixLength) {
		case 1: //单字节直接读取
			dataLength = buffer.getUnsigned(buffer.position());
			break;
		case 2: //多字节进行大小端转换
			int a1 = buffer.getUnsigned(buffer.position());
			int a2 = buffer.getUnsigned(buffer.position() + 1);
			dataLength = ByteOps.makeInt(ByteOps.int2ByteArray(a1, 1)[0],
					ByteOps.int2ByteArray(a2, 1)[0]);
			break;
		default:
			throw new IllegalArgumentException("prefixLength: " + prefixLength);
		}

		if (dataLength < 0 || dataLength > Integer.MAX_VALUE) {
			throw new BufferDataException("dataLength: " + dataLength);
		}

		return buffer.remaining() >= dataLength;
	}
}
