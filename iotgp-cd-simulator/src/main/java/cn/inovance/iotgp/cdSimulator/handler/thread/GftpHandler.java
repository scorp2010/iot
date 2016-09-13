package cn.inovance.iotgp.cdSimulator.handler.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.inovance.iotgp.cdSimulator.client.TempClient;
import cn.inovance.iotgp.common.msg.cd.GftpTransparentUpMsg;
import cn.inovance.iotgp.common.msg.cd.gftp.EndTransferNofity;
import cn.inovance.iotgp.common.msg.cd.gftp.FileDataTransfer;
import cn.inovance.iotgp.common.msg.cd.gftp.LoginReq;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferReq;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GftpHandler extends Thread implements IThreadHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GftpHandler.class);

	public static int SIGNAL_START_TRANSFER = 1;
	public static int SIGNAL_TRANSFER = 2;
	public static int SIGNAL_END_TRANSFER = 3;

	private TempClient tempClient;
	private String registerCode;

	private boolean shutdown = false;
	private int signal = -1; // 当前处理进度信号标识

	private String userAccount; // 下载用户名
	private String userPassword; // 下载用户密码

	private short transferType; // 传输方式（FTP/GFTP)
	private String filePath; // 文件路径
	private String fileName; // 文件名
	private byte[] fileData; // 文件数据

	private String localFileName; // 本地保存的文件全名

	public GftpHandler(String host, int port, String registerCode,
			String tempSessionKey, String userAccount, String userPassword,
			short transferType, String filePath, String fileName,
			String localFileName) {
		tempClient = new TempClient(host, port, registerCode, tempSessionKey);

		this.registerCode = registerCode;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.transferType = transferType;
		this.filePath = filePath;
		this.fileName = fileName;
		this.localFileName = localFileName;
	}

	@Override
	public void run() {
		logger.info("[{}]GFTP文件传输线程启动", registerCode);

		// 连接服务器
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

		// 发送鉴权请求
		if (!shutdown && tempClient.isConnected()) {
			LoginReq req = new LoginReq(userAccount, userPassword);
			this.sendGftpTransparentUpMsg(req.getBytes());
		}

		t = 3;
		while (!shutdown && signal != SIGNAL_START_TRANSFER && t > 0) {
			try {
				Thread.sleep(1000);
				t--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!shutdown && signal != SIGNAL_START_TRANSFER) {
			logger.info("[{}]3秒内未能鉴权成功，将关闭连接", registerCode);
			this.shutdown();
		}

		// 发送文件传输请求
		if (!shutdown && tempClient.isConnected()) {
			StartTransferReq req = new StartTransferReq(
					transferType,
					fileName,
					filePath,
					0,
					transferType == StartTransferReq.TRANSFER_TYPE_UPLOAD ? 3 * 1024
							: 0);
			this.sendGftpTransparentUpMsg(req.getBytes());
		}

		t = 3;
		while (!shutdown && signal != SIGNAL_TRANSFER && t > 0) {
			try {
				Thread.sleep(1000);
				t--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!shutdown && signal != SIGNAL_TRANSFER) {
			logger.info("[{}]3秒内未收到文件传输响应，将关闭连接", registerCode);
			this.shutdown();
		}

		// 发送文件数据&&传输结束通知
		if (!shutdown && transferType == StartTransferReq.TRANSFER_TYPE_UPLOAD
				&& tempClient.isConnected()) {
			try {
				// 发送文件数据
				byte[] fileData = new byte[1024];
				for (int i = 0; i < fileData.length; i++) {
					fileData[i] = 'a';
				}

				for (int i = 0; i < 3; i++) {
					FileDataTransfer transfer = new FileDataTransfer(i * 1024l,
							fileData);
					this.sendGftpTransparentUpMsg(transfer.getBytes());
				}

				// 发送传输结束通知
				EndTransferNofity nofity = new EndTransferNofity(3 * 1024, 0l);
				this.sendGftpTransparentUpMsg(nofity.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		t = 300;
		while (!shutdown && signal != SIGNAL_END_TRANSFER && t > 0) {
			try {
				Thread.sleep(1000);
				t--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!shutdown && signal != SIGNAL_END_TRANSFER) {
			logger.info("[{}]300秒内文件传输未完成，将关闭连接", registerCode);
			this.shutdown();
		}

		tempClient.close();
		logger.info("[{}]GFTP文件传输线程关闭", registerCode);
	}

	// 发送GFTP透传上行消息
	private void sendGftpTransparentUpMsg(byte[] gftpCommand) {
		GftpTransparentUpMsg transparentMsg = new GftpTransparentUpMsg(
				gftpCommand.length);
		transparentMsg.setGftpCommand(gftpCommand);
		transparentMsg.construct();
		tempClient.send(transparentMsg.getData());
	}

	// 设置文件总字节数
	public void setFileLength(int fileLength) {
		fileData = new byte[fileLength];
	}

	// 合并文件数据
	public void mergeFileData(int offset, byte[] data) {
		ByteOps.addByteArray(fileData, data, offset);
	}

	// 保存文件（可以考虑优化成边接收边保存文件）
	public boolean saveFile() {
		try {
			File localFile = new File(localFileName);
			// 目录不存在则创建
			File parent = localFile.getParentFile();
			if (parent != null && !parent.exists())
				parent.mkdirs();

			OutputStream fileOut = new FileOutputStream(localFile);
			fileOut.write(fileData);
			fileOut.flush();
			fileOut.close();
			logger.info("{}文件下载完成", localFileName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void shutdown() {
		this.shutdown = true;
	}

	public void switchSignal(int signal) {
		this.signal = signal;
	}

}
