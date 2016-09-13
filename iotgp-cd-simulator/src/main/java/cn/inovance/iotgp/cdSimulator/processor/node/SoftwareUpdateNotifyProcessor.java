package cn.inovance.iotgp.cdSimulator.processor.node;

import java.util.Date;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.cdSimulator.util.DatetimeUtil;
import cn.inovance.iotgp.cdSimulator.util.FtpUtil;
import cn.inovance.iotgp.common.enums.TransferType;
import cn.inovance.iotgp.common.msg.cd.CdSoftwareUpdateNotify;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class SoftwareUpdateNotifyProcessor extends AbstractIoProcessor
		implements ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			CdSoftwareUpdateNotify notify = new CdSoftwareUpdateNotify(data);
			notify.parse();
			logger.info("[{}]软件更新通知消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), notify.toString());

			ShortPdu[] hostPdus = notify.getServerConnectionInfo().getHost();
			String host = String.format("%s.%s.%s.%s", hostPdus[0],
					hostPdus[1], hostPdus[2], hostPdus[3]);
			int port = notify.getServerConnectionInfo().getPort().getValue();
			String userAccount = notify.getDownloadUserAccount().getValue();
			String userPassword = notify.getDownloadUserPassword().getValue();
			String filePath = notify.getFilePath().getValue();
			String fileName = notify.getFileName().getValue();

			// 本地文件名
			String localFileName = String.format("%s\\localfiles\\%s%s.%s.%s",
					System.getProperty("user.dir"), notify.getFileName(),
					notify.getSoftwareVersion(), DatetimeUtil
							.getDateByNotStyle(new Date()), nodeClient
							.getSession().getId());

			if (notify.getTransferType().getValue() == TransferType.FTP.value()) {
				// FTP下载文件
				boolean updateSucc = new FtpUtil().downLoadFile(host, port,
						userAccount, userPassword, filePath, fileName,
						localFileName);
				if (!updateSucc) {
					logger.warn("[{}]FTP下载软件更新文件失败：[{}]", nodeClient
							.getCdRegisterCode().getRegCode(), notify
							.getFileName().getValue());
					return false;
				} else {
					logger.info("[{}]FTP下载软件更新文件成功：[{}]", nodeClient
							.getCdRegisterCode().getRegCode(), notify
							.getFileName().getValue());
				}
			} else if (notify.getTransferType().getValue() == TransferType.GFTP
					.value()) {
				// GFTP下载文件
				nodeClient.startGFTP(host, port, userAccount, userPassword,
						StartTransferReq.TRANSFER_TYPE_DOWNLOAD, filePath,
						fileName, localFileName);
			}

			// 更新本地版本号
			StaticValues.CdSimulator_SoftVersion = notify.getSoftwareVersion()
					.getValue();

			// 上报软件版本更新进度（下载100%）
			Thread.sleep(1 * 5000);
			/*
			 * nodeClient.sendSoftWareUpdateProgressNotify(
			 * notify.getSoftwareName(), notify.getSoftwareVersion(), (short)
			 * 100, ErrorCode.OK.getCode());
			 * 
			 * // 上报软件版本更新进度（安装100%） Thread.sleep(1 * 2000);
			 * nodeClient.sendSoftWareUpdateProgressNotify(
			 * notify.getSoftwareName(), notify.getSoftwareVersion(), (short)
			 * 228, ErrorCode.OK.getCode());
			 * 
			 * // 上报当前版本号 nodeClient.sendSoftwareVersionNotify();
			 */
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
