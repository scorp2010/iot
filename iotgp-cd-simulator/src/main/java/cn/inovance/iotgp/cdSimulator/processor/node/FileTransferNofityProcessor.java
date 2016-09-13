package cn.inovance.iotgp.cdSimulator.processor.node;

import java.util.Date;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.cdSimulator.util.DatetimeUtil;
import cn.inovance.iotgp.cdSimulator.util.FtpUtil;
import cn.inovance.iotgp.common.enums.TransferType;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.FileTransferNotify;
import cn.inovance.iotgp.common.msg.cd.FileTransferProgressNotify;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;

@Component
public class FileTransferNofityProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			FileTransferNotify notify = new FileTransferNotify(data);
			notify.parse();
			logger.info("[{}]资源文件上/下载通知消息：{}", nodeClient.getCdRegisterCode()
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
			String lacalFileName = String.format("%s\\localfiles\\%s%s.%s.%s",
					System.getProperty("user.dir"), notify.getFilePath(),
					notify.getFileName(), DatetimeUtil
							.getDateByNotStyle(new Date()), nodeClient
							.getSession().getId());

			if (notify.getUpDownType().getValue() == FileTransferNotify.UpDownType_DownLoad) {
				if (notify.getTransferType().getValue() == TransferType.FTP
						.value()) {
					// FTP下载文件
					boolean updateSucc = new FtpUtil().downLoadFile(host, port,
							userAccount, userPassword, filePath, fileName,
							lacalFileName);

					if (!updateSucc) {
						logger.warn("[{}]FTP下载文件失败：[{}]", nodeClient
								.getCdRegisterCode().getRegCode(), notify
								.getFileName().getValue());
						return false;
					}
				} else if (notify.getTransferType().getValue() == TransferType.GFTP
						.value()) {
					// GFTP下载文件
					nodeClient.startGFTP(host, port, userAccount, userPassword,
							StartTransferReq.TRANSFER_TYPE_DOWNLOAD, filePath,
							fileName, lacalFileName);
				}
			} else {
				lacalFileName = String.format("%s\\localfiles\\%s",
						System.getProperty("user.dir"), "logfile.log");
				if (notify.getTransferType().getValue() == TransferType.FTP
						.value()) {
					// FTP上传文件
					boolean updateSucc = new FtpUtil().upLoadFile(host, port,
							userAccount, userPassword, filePath, fileName,
							lacalFileName);

					if (!updateSucc) {
						logger.warn("[{}]FTP上传文件失败：[{}]", nodeClient
								.getCdRegisterCode().getRegCode(), notify
								.getFileName().getValue());
						return false;
					}
				} else if (notify.getTransferType().getValue() == TransferType.GFTP
						.value()) {
					// GFTP上传文件
					nodeClient.startGFTP(host, port, userAccount, userPassword,
							StartTransferReq.TRANSFER_TYPE_UPLOAD, filePath,
							fileName, lacalFileName);
				}
			}

			Thread.sleep(1 * 2000);

			// 资源文件上/下载进度通知
			FileTransferProgressNotify rsp = new FileTransferProgressNotify();
			rsp.setErrorCode(ErrorCode.OK);
			rsp.setTransferProgress((short) 100);
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]资源文件上/下载进度通知信息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
