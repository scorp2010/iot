package cn.inovance.iotgp.cdSimulator.processor.node;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.cdSimulator.util.FtpUtil;
import cn.inovance.iotgp.common.enums.TransferType;
import cn.inovance.iotgp.common.msg.cd.PhotoCaptureReq;
import cn.inovance.iotgp.common.msg.cd.PhotoCaptureRsp;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class PhotoCaptureReqProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	private boolean transferProcedureFile(PhotoCaptureReq req,
			NodeClient nodeClient) {
		try {
			ShortPdu[] hostPdus = req.getServerConnectionInfo().getHost();
			String host = String.format("%s.%s.%s.%s", hostPdus[0],
					hostPdus[1], hostPdus[2], hostPdus[3]);
			int port = req.getServerConnectionInfo().getPort().getValue();
			String userAccount = req.getUploadUserAccount().getValue();
			String userPassword = req.getUploadUserPassword().getValue();
			String filePath = req.getFilePath().getValue();

			short captureInterval = req.getCaptureInterval().getValue();
			short captureNumber = req.getCaptureNumber().getValue();

			Thread.sleep(5000);

			for (int i = 0; i < captureNumber; i++) {
				Thread.sleep(captureInterval);
				Calendar currentTime = Calendar.getInstance();
				String sStopTime = String.format("%04d%02d%02d%02d%02d%02d",
						currentTime.get(Calendar.YEAR),
						currentTime.get(Calendar.MONTH) + 1,
						currentTime.get(Calendar.DAY_OF_MONTH),
						currentTime.get(Calendar.HOUR_OF_DAY),
						currentTime.get(Calendar.MINUTE),
						currentTime.get(Calendar.SECOND));

				String fileName = sStopTime + ".jpg";
				String localfilename = "D:\\test.jpg";
				if (req.getTransferType().getValue() == TransferType.FTP
						.value()) {
					// FTP上传文件
					boolean updateSucc = new FtpUtil().upLoadFile(host, port,
							userAccount, userPassword, filePath, fileName,
							localfilename);

					if (!updateSucc) {
						logger.warn("[{}]FTP上传文件失败：[{}]", nodeClient
								.getCdRegisterCode().getRegCode(), fileName);
						continue;
					}
				} else if (req.getTransferType().getValue() == TransferType.GFTP
						.value()) {
					// GFTP上传文件
					nodeClient.startGFTP(host, port, userAccount, userPassword,
							StartTransferReq.TRANSFER_TYPE_UPLOAD, filePath,
							fileName, localfilename);
				}

				// 发送抓拍响应
				PhotoCaptureRsp rsp = new PhotoCaptureRsp(req);
				rsp.setFileName(new StringPdu(fileName));
				rsp.setFileNameLength(new ShortPdu((short) fileName.length()));
				rsp.construct();
				nodeClient.send(rsp.getData());
			}

			Thread.sleep(1 * 2000);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			PhotoCaptureReq req = new PhotoCaptureReq(data);
			req.parse();
			logger.info("[{}]摄像头抓拍请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			transferProcedureFile(req, nodeClient);
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
