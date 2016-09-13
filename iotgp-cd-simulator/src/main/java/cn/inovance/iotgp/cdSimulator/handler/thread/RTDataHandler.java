package cn.inovance.iotgp.cdSimulator.handler.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdSimulator.client.TempClient;
import cn.inovance.iotgp.common.msg.cd.DataConnHeartBeatReq;
import cn.inovance.iotgp.common.msg.cd.ReportTdDataReq;
import cn.inovance.iotgp.common.msg.cd.meta.TdTagValue;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@SuppressWarnings("unused")
public class RTDataHandler extends Thread implements IThreadHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(RTDataHandler.class);

	private TempClient tempClient;

	private boolean shutdown = false;
	private int signal = -1; // 当前处理进度信号标识

	private int interval;
	private String registerCode;
	private String securityCode;
	private short tdSerial;

	public RTDataHandler(String host, int port, String registerCode,
			String tempSessionKey, int interval, String securityCode,
			short tdSerial) {
		tempClient = new TempClient(host, port, registerCode, tempSessionKey);
		this.registerCode = registerCode;
		this.securityCode = securityCode;
		this.tdSerial = tdSerial;
		this.interval = interval;
	}

	@Override
	public void run() {
		logger.info("[{}]发送运行数据线程启动", registerCode);

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
			logger.info("[{}]30秒内仍未连接上实时数据服务器[{}:{}]，将关闭连接", registerCode,
					tempClient.getServerHost(), tempClient.getServerPort());
		}

		int count = 1;
		while (!shutdown && tempClient.isConnected()
				&& tempClient.getSession().isConnected()) {
			try {

				byte[] first = ByteOps.int2ByteArray(2553 * count++, 2);
				byte[] second = ByteOps.int2ByteArray(6812 + count++, 2);
				byte[] third = ByteOps.int2ByteArray(10 + count++, 1);
				byte[] forth = ByteOps.int2ByteArray(10 + count++, 2);
				byte[] date = ByteOps.int2ByteArray(1406860274 + count++, 4);
				byte[] sendtag1 = new byte[11];
				int start = 0;
				for (int i = 0; i < 2; i++) {
					sendtag1[start++] = first[i];
				}
				for (int i = 0; i < 2; i++) {
					sendtag1[start++] = second[i];
				}
				for (int i = 0; i < 1; i++) {
					sendtag1[start++] = third[i];
				}
				for (int i = 0; i < 2; i++) {
					sendtag1[start++] = forth[i];
				}
				for (int i = 0; i < 4; i++) {
					sendtag1[start++] = date[i];
				}

				start = 0;
				byte[] first2 = ByteOps.int2ByteArray(2553 + count++, 2);
				byte[] second2 = ByteOps.int2ByteArray(6812 + count++, 2);
				byte[] sendtag2 = new byte[4];
				for (int i = 0; i < 2; i++) {
					sendtag2[start++] = first2[i];
				}
				for (int i = 0; i < 2; i++) {
					sendtag2[start++] = second2[i];
				}

				TdTagValue pdu = new TdTagValue((short) 1, sendtag1);
				TdTagValue pdu1 = new TdTagValue((short) 2, sendtag2);

				List<TdTagValue> tdDataTags = new ArrayList<TdTagValue>();
				tdDataTags.add(pdu);
				tdDataTags.add(pdu1);

				// 发送运行数据
				ReportTdDataReq reportData = new ReportTdDataReq();
				reportData.setSecurityCode(securityCode);
				reportData.setTdSerial(this.tdSerial);
				reportData.setTdDataTagValues(tdDataTags);
				reportData.construct();
				
				tempClient.send(reportData.getData());
				
				logger.debug("[{}]上报控制器运行数据：{}", registerCode,
						reportData.toString());

				// 随机上报目标设备心跳消息
				if (new Random().nextBoolean()) {
					DataConnHeartBeatReq req = new DataConnHeartBeatReq();
					req.setSecurityCode(securityCode);
					req.construct();
					tempClient.send(req.getData());
					logger.debug("[{}]上报心跳消息：{}", registerCode, req.toString());
				}

				Thread.sleep(interval * 1000);
			} catch (InterruptedException e) {
				logger.info("[{}]终止发送运行数据", registerCode);
			}
		}

		tempClient.close();
		logger.info("[{}]发送运行数据线程关闭", registerCode);
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
