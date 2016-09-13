package cn.inovance.iotgp.cdSimulator.handler.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdSimulator.client.TempClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.common.msg.cd.AudioHeaderReq;
import cn.inovance.iotgp.common.msg.cd.AudioStreamData;
import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class AudioDataHandler extends Thread implements IThreadHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(AudioDataHandler.class);

	public static int SIGNAL_TRANSFER_AUDIOSTREAM = 2;
	
	private TempClient tempClient;
	private boolean shutdown = false;
	private int signal = -1; // 当前处理进度信号标识

	private int interval;
	private String registerCode;
	private String securityCode;
	private short channel;

	public AudioDataHandler(String host, int port, String registerCode,
			String tempSessionKey, int interval, String securityCode,short channel) {
		tempClient = new TempClient(host, port, registerCode, tempSessionKey);
		this.registerCode = registerCode;
		this.interval = interval;
		this.securityCode = securityCode;
		this.channel = channel;
	}

	@Override
	public void run() {
		logger.info("[{}]发送音频数据线程启动", registerCode);

		tempClient.connect();
		int t = 30;
		while (!tempClient.isConnected() && t > 0) {
			try {
				Thread.sleep(1000);
				t--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!tempClient.isConnected()) {
			logger.info("[{}]30秒内仍未连接上服务器[{}:{}]，将关闭连接", registerCode,
					tempClient.getServerHost(), tempClient.getServerPort());
			this.shutdown();
		}

		// 发送音频头消息
		if (!shutdown && tempClient.isConnected()) {
			AudioHeaderReq headReq = new AudioHeaderReq();
			headReq.setSecurityCode(securityCode);
			headReq.setChannel(channel);
			headReq.setSampling((short)0x01);
			headReq.construct();
			tempClient.send(headReq.getData());
			logger.info("[{}]发送音频头消息：{}", registerCode, headReq.toString());
		}

		// 等待平台的音频头响应消息
		t = 3;
		while (!shutdown && signal != SIGNAL_TRANSFER_AUDIOSTREAM && t > 0) {
			try {
				Thread.sleep(1000);
				t--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (signal != SIGNAL_TRANSFER_AUDIOSTREAM) {
			logger.info("[{}]3秒内未收到音频头响应，将关闭连接", registerCode);
			this.shutdown();
		}

		while (!shutdown && tempClient.isConnected()
				&& tempClient.getSession().isConnected()) {
			try {
				// 发送视频数据
				AudioStreamData audioSreamData = new AudioStreamData();
				audioSreamData.setSecurityCode(securityCode);
				audioSreamData.setPackNo(new IntPdu(1));
				audioSreamData.setSecondTimeStamp(new LongPdu(428571));
				audioSreamData.setMilSecondTimeStamp(new LongPdu(561851));
				byte[] streamData = ByteOps
						.hexStringToBytes(StaticValues.Audio_Data_Stream);
				audioSreamData.setStreamData(streamData);
				audioSreamData.construct();
				tempClient.send(audioSreamData.getData());
				logger.info("[{}]发送音频数据：{}...", registerCode, audioSreamData
						.getHeader().toString());

				Thread.sleep(interval);
			} catch (InterruptedException e) {
				logger.info("[{}]终止发送音频数据", registerCode);
				break;
			}
		}

		tempClient.close();
		logger.info("[{}]发送音频数据线程关闭", registerCode);
	}

	/**
	 * 关闭发送数据线程
	 */
	public void shutdown() {
		this.shutdown = true;
	}

	public void switchSignal(int signal) {
		this.signal = signal;
	}
}
