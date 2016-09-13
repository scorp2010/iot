package cn.inovance.iotgp.common.msg.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName:SeqGenerator Function: 消息序号生成器
 * 
 * @author zhangqiang
 * @version 2013-08-29
 */
public class SeqGenerator {

	private static AtomicLong sequenceNumber = new AtomicLong(1);

	private static AtomicInteger seqNumber = new AtomicInteger(1);

	private static AtomicInteger seqMsgNumber = new AtomicInteger(1);

	public static long next() {
		if (sequenceNumber.get() > 999999999999999L) {
			sequenceNumber.set(1L);
		}
		return sequenceNumber.getAndIncrement();
	}

	public static int nextInt() {
		if (seqNumber.get() > 65535) {
			seqNumber.set(1);
		}
		return seqNumber.getAndIncrement();
	}

	public static short nextShort() {
		if (seqMsgNumber.get() > 255) {
			seqMsgNumber.set(1);
		}
		return (short) seqMsgNumber.getAndIncrement();
	}
}
