package cn.inovance.iotgp.cdSimulator.processor.node;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.cdSimulator.util.FtpUtil;
import cn.inovance.iotgp.common.enums.TransferType;
import cn.inovance.iotgp.common.msg.cd.ErrorCode;
import cn.inovance.iotgp.common.msg.cd.ProcedureFileStartUploadReq;
import cn.inovance.iotgp.common.msg.cd.ProcedureFileStartUploadRsp;
import cn.inovance.iotgp.common.msg.cd.ProcedureFileUploadCompleteReq;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;

@Component
public class StartTransferProcedureFileProcessor extends AbstractIoProcessor
		implements ICdSimulatorMessageProcessor {

	private void writeByteArrayToFile(byte[] buffer, String filePath,
			boolean append) throws Exception {
		OutputStream out = null;
		BufferedOutputStream bufferOut = null;
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new FileOutputStream(file, append);
			bufferOut = new BufferedOutputStream(out);
			bufferOut.write(buffer);
			bufferOut.flush();
			bufferOut.close();
			out.close();
		} finally {
			if (bufferOut != null) {
				try {
					bufferOut.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean transferProcedureFile(ProcedureFileStartUploadReq req,
			NodeClient nodeClient) {
		try {
			ShortPdu[] hostPdus = req.getServerConnectionInfo().getHost();
			String host = String.format("%s.%s.%s.%s", hostPdus[0],
					hostPdus[1], hostPdus[2], hostPdus[3]);
			int port = req.getServerConnectionInfo().getPort().getValue();
			String userAccount = req.getUploadUserAccount().getValue();
			String userPassword = req.getUploadUserPassword().getValue();
			String filePath = req.getUploadFilePath().getValue();
			
			Calendar currentTime = Calendar.getInstance();
			currentTime.setTime(req.getStartDate().getValue());
			String sStartTime = String.format("%04d%02d%02d%02d%02d%02d",
					currentTime.get(Calendar.YEAR),
					currentTime.get(Calendar.MONTH) + 1,
					currentTime.get(Calendar.DAY_OF_MONTH),
					currentTime.get(Calendar.HOUR_OF_DAY),
					currentTime.get(Calendar.MINUTE),
					currentTime.get(Calendar.SECOND));

			Thread.sleep(5000);

			for (int i = 0; i < 10; i++) {
				Thread.sleep(5000);
				currentTime = Calendar.getInstance();
				String sStopTime = String.format("%04d%02d%02d%02d%02d%02d",
						currentTime.get(Calendar.YEAR),
						currentTime.get(Calendar.MONTH) + 1,
						currentTime.get(Calendar.DAY_OF_MONTH),
						currentTime.get(Calendar.HOUR_OF_DAY),
						currentTime.get(Calendar.MINUTE),
						currentTime.get(Calendar.SECOND));

				String fileName = sStartTime + "-" + sStopTime + ".log";
				String localfilename = "D:\\testdata.log";
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
				
				// 发送过程文件上传完成
				ProcedureFileUploadCompleteReq procedureFileUploadCompleteReq = new ProcedureFileUploadCompleteReq();
				procedureFileUploadCompleteReq.setSessionID(req.getSessionID());
				procedureFileUploadCompleteReq.setFileName(new StringPdu(fileName));
				procedureFileUploadCompleteReq.setFileNameLength(new ShortPdu((short) fileName.length()));
				procedureFileUploadCompleteReq.setFileType(req.getFileType());
				
				procedureFileUploadCompleteReq.construct();
				nodeClient.send(procedureFileUploadCompleteReq.getData());
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
			ProcedureFileStartUploadReq req = new ProcedureFileStartUploadReq(
					data);
			req.parse();
			logger.info("[{}]过程文件上传请求消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), req.toString());

			// 应答过程文件请求
			ProcedureFileStartUploadRsp rsp = new ProcedureFileStartUploadRsp();
			rsp.setErrorCode(ErrorCode.OK);
			rsp.setSessionID(req.getSessionID());
			rsp.construct();
			nodeClient.send(rsp.getData());
			logger.info("[{}]发送过程文件上传请求响应消息：{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());

			transferProcedureFile(req, nodeClient);
		} catch (MessageParseException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
