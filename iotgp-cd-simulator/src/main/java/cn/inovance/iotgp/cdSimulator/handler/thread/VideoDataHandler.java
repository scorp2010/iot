package cn.inovance.iotgp.cdSimulator.handler.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdSimulator.client.TempClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.common.msg.cd.VideoHeaderReq;
import cn.inovance.iotgp.common.msg.cd.VideoStreamData;
import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.LongPdu;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class VideoDataHandler extends Thread implements IThreadHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(VideoDataHandler.class);

	public static int SIGNAL_TRANSFER_VIDEOSTREAM = 2;
	
	private TempClient tempClient;
	private boolean shutdown = false;
	private int signal = -1; // 当前处理进度信号标识

	private int interval;
	private String registerCode;
	private String securityCode;
	private short cameraSerial;

	public VideoDataHandler(String host, int port, String registerCode,
			String tempSessionKey, int interval, String securityCode,short cameraSerial) {
		tempClient = new TempClient(host, port, registerCode, tempSessionKey);
		this.registerCode = registerCode;
		this.interval = interval;
		this.securityCode = securityCode;
		this.cameraSerial = cameraSerial;
	}

	@Override
	public void run() {
		logger.info("[{}]发送视频数据线程启动", registerCode);

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

		// 发送视频头消息
		if (!shutdown && tempClient.isConnected()) {
			VideoHeaderReq headReq = new VideoHeaderReq();
			headReq.setSecurityCode(securityCode);
			headReq.setCameraSerial(cameraSerial);
			byte[] videoDataHeader = ByteOps
					.hexStringToBytes(StaticValues.Video_Data_Header);
			headReq.setVideoDataHeader(videoDataHeader);
			headReq.construct();
			tempClient.send(headReq.getData());
			logger.info("[{}]发送视频头消息：{}", registerCode, headReq.toString());
		}

		// 等待平台的视频头响应消息
		t = 3;
		while (!shutdown && signal != SIGNAL_TRANSFER_VIDEOSTREAM && t > 0) {
			try {
				Thread.sleep(1000);
				t--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (signal != SIGNAL_TRANSFER_VIDEOSTREAM) {
			logger.info("[{}]3秒内未收到视频头响应，将关闭连接", registerCode);
			this.shutdown();
		}
		
		int pollCount=0;//轮询次数
		while (!shutdown && tempClient.isConnected()
				&& tempClient.getSession().isConnected()) {
			pollCount++;
			try {
				// 发送视频数据
				VideoStreamData videoSreamData = new VideoStreamData();
				videoSreamData.setSecurityCode(securityCode);
				
				videoSreamData.setPackNumPerFrame(new IntPdu(51201));
				videoSreamData.setPackNo(new IntPdu(1));
				byte[] streamData = null;
				if (pollCount%2==0) {
					videoSreamData.setSecondTimeStamp(new LongPdu(32));
					videoSreamData.setMilSecondTimeStamp(new LongPdu(66));
					streamData=ByteOps.hexStringToBytes(StaticValues.Video_Data_Stream2);
				}else {
					videoSreamData.setSecondTimeStamp(new LongPdu(35));
					videoSreamData.setMilSecondTimeStamp(new LongPdu(68));
					streamData=ByteOps.hexStringToBytes(StaticValues.Video_Data_Stream);
				}
				videoSreamData.setStreamData(streamData);
				videoSreamData.construct();
				tempClient.send(videoSreamData.getData());
//				logger.debug("[{}]发送实时视频头数据：{}...", registerCode, videoSreamData.getHeader().toString());
				logger.debug("[{}]发送实时视频数据：{}...", registerCode, videoSreamData.getData().length);
				//Thread.sleep(interval * 10);//程序传入
				Thread.sleep(10);//配置文件控制模拟发送视频频率
			} catch (InterruptedException e) {
				logger.info("[{}]终止发送实时视频数据", registerCode);
				break;
			}
		}

		tempClient.close();
		logger.info("[{}]发送视频数据线程关闭", registerCode);
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
