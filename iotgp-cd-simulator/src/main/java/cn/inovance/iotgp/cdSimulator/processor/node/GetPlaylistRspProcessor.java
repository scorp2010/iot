package cn.inovance.iotgp.cdSimulator.processor.node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.csvreader.CsvReader;

import cn.inovance.iotgp.cdSimulator.client.NodeClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.pkg.GetPlaylistRsp;
import cn.inovance.iotgp.cdSimulator.pkg.PlaylistUpdateCompleteReq;
import cn.inovance.iotgp.cdSimulator.processor.AbstractIoProcessor;
import cn.inovance.iotgp.cdSimulator.processor.ICdSimulatorMessageProcessor;
import cn.inovance.iotgp.cdSimulator.util.DatetimeUtil;
import cn.inovance.iotgp.cdSimulator.util.FtpUtil;
import cn.inovance.iotgp.common.enums.CommonStaticValue;
import cn.inovance.iotgp.common.enums.TransferType;
import cn.inovance.iotgp.common.msg.cd.CtrlEventRsp;
import cn.inovance.iotgp.common.msg.cd.gftp.StartTransferReq;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.util.ByteOps;

@Component
public class GetPlaylistRspProcessor extends AbstractIoProcessor implements
		ICdSimulatorMessageProcessor {

	public boolean processMessage(NodeClient nodeClient, byte[] data) {
		try {
			if (nodeClient.getMediaUpdateFlag().get()){
				logger.warn("采集设备正在更新媒体信息，不处理重复请求！");
				return true;
			}
			GetPlaylistRsp rsp = new GetPlaylistRsp(data);
			rsp.parse();
			logger.info("[{}]播放列表响应消息:{}", nodeClient.getCdRegisterCode()
					.getRegCode(), rsp.toString());
			Short[] hostPdus = rsp.getFtpHost();
			String host = String.format("%s.%s.%s.%s", hostPdus[0],
					hostPdus[1], hostPdus[2], hostPdus[3]);
			int port = rsp.getFtpPort();
			String userAccount = nodeClient.getCdRegisterCode().getRegCode();
			String userPassword = rsp.getFtpPassword();
			String filePath = rsp.getAdPlaylistFilePath();
			String fileName = rsp.getAdPlaylistFileName();

			// 本地文件名
			String localFileName = String.format("%s\\localfiles\\%s%s.%s.%s",
					System.getProperty("user.dir"), fileName,
					rsp.getAdPlaylistFileMd5(), DatetimeUtil
							.getDateByNotStyle(new Date()), nodeClient
							.getSession().getId());
			// FTP下载文件
			boolean updateSucc = new FtpUtil().downLoadFile(host, port,
					userAccount, userPassword, filePath, fileName,
					localFileName);
			boolean mediaUpdateSucc = true;
			if (!updateSucc) {
				logger.warn("[{}]FTP下载播放列表文件失败：[{}]", nodeClient
						.getCdRegisterCode().getRegCode(), fileName);
			} else {
				logger.info("[{}]FTP下载播放列表文件成功：[{}]", nodeClient
						.getCdRegisterCode().getRegCode(), fileName);
				// 更新本地版本号
				nodeClient.setAdplaylistid(rsp.getAdPlaylistFileMd5());
				
				// 下载素材文件
				File localFile = new File(localFileName);
				Map<String, MediaFile> mediaMap = getMediaMap(localFile);
				if (mediaMap != null){
					// 下载素材文件
					for (MediaFile mediaFile : mediaMap.values()){
						// 本地文件名
						String localMediaFileName = String.format("%s\\localfiles\\%s%s.%s.%s",
								System.getProperty("user.dir"), mediaFile.fileName,
								mediaFile.fileMd5, DatetimeUtil
										.getDateByNotStyle(new Date()), nodeClient
										.getSession().getId());
						// FTP下载文件
						mediaUpdateSucc = new FtpUtil().downLoadFile(host, port,
								userAccount, userPassword, mediaFile.filePath, mediaFile.fileName,
								localMediaFileName);
						if (!mediaUpdateSucc) {
							logger.warn("[{}]FTP下载素材文件失败：[{}]", nodeClient
									.getCdRegisterCode().getRegCode(), mediaFile.fileName);
							break;
						} else {
							logger.info("[{}]FTP下载素材文件成功：[{}]", nodeClient
									.getCdRegisterCode().getRegCode(), mediaFile.fileName);
						}	
					}
				}
			}

			
			// 上报播放列表更新完成通知
			PlaylistUpdateCompleteReq req = new PlaylistUpdateCompleteReq();
			req.setPlaylistId(nodeClient.getAdplaylistid());
			req.setPlaylistFileMd5(rsp.getAdPlaylistFileMd5());
			req.setPlaylistFilePath("/log");
			req.setPlaylistFileName("test");
			if (updateSucc && mediaUpdateSucc){
				req.setUpdateStatus((short)0);
			}
			else 
				req.setUpdateStatus((short)1);
			req.construct();
			nodeClient.send(req.getData());
			//nodeClient.sendPlaylistUpdateCompleteReq(req);
			nodeClient.getMediaUpdateFlag().set(false);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public Map getMediaMap(File plFile){
		InputStream in;
		try {
			in = new FileInputStream(plFile);
			CsvReader cvsReader  = new CsvReader(in, Charset.forName("utf8"));
		    cvsReader.setDelimiter('\t');
		    int i=1;
		    String playlistid="";
		    Map<String, MediaFile> mediaMap = new LinkedHashMap<String, MediaFile>(); 
		    while(cvsReader.readRecord()){
		    		String rawRecord = cvsReader.get(0);
		    		System.out.println(rawRecord);
		        	String[] values = rawRecord.split(",");
		        	if (i == 2){
		        		playlistid = values[1];
		        	}else if (i>5){
		        		int videoPosition = 0;
		        		if (rawRecord.indexOf("\"") > -1){
		        			videoPosition = 3;
		        		}
		        		String mediaFileName = values[videoPosition+3];
		        		if (StringUtils.isNotBlank(mediaFileName)){
		        			String fileMd5 = values[videoPosition+15];
		        			String filePath = values[videoPosition+14];
		        			if (mediaMap.get(fileMd5) == null){
		        				mediaMap.put(fileMd5, new MediaFile("video", fileMd5, mediaFileName, filePath));
		        			}
		        		}
		        		String audioFileName = values[videoPosition+4];
		        		if (StringUtils.isNotBlank(audioFileName)){
		        			String fileMd5 = values[videoPosition+17];
		        			String filePath = values[videoPosition+16];
		        			if (mediaMap.get(fileMd5) == null){
		        				mediaMap.put(fileMd5, new MediaFile("audio", fileMd5, audioFileName, filePath));
		        			}
		        		}
		        		String picFileName = values[videoPosition+7];
		        		if (StringUtils.isNotBlank(picFileName)){
		        			String fileMd5 = values[videoPosition+19];
		        			String filePath = values[videoPosition+18];
		        			if (mediaMap.get(fileMd5) == null){
		        				mediaMap.put(fileMd5, new MediaFile("pic", fileMd5, picFileName, filePath));
		        			}
		        		}
		        	}
		        	i++;
		    }
		    System.out.println(mediaMap.toString());
		    return mediaMap;
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	
	}
	
	
	public static void main(String[] args) {
		File plFile = new File("d://ed8c99e311434085956cb00933d59ded.csv");
		InputStream in;
		try {
			in = new FileInputStream(plFile);
			CsvReader cvsReader  = new CsvReader(in, Charset.forName("utf8"));
		    cvsReader.setDelimiter('\t');
		    int i=1;
		    String playlistid="";
		    Map<String, MediaFile> mediaMap = new LinkedHashMap<String, MediaFile>(); 
		    while(cvsReader.readRecord()){
		    		String rawRecord = cvsReader.get(0);
		    		System.out.println(rawRecord);
		        	String[] values = rawRecord.split(",");
		        	if (i == 2){
		        		playlistid = values[1];
		        	}else if (i>5){
		        		int videoPosition = 0;
		        		if (rawRecord.indexOf("\"") > -1){
		        			videoPosition = 3;
		        		}
		        		String mediaFileName = values[videoPosition+3];
		        		if (StringUtils.isNotBlank(mediaFileName)){
		        			String fileMd5 = values[videoPosition+15];
		        			String filePath = values[videoPosition+14];
		        			if (mediaMap.get(fileMd5) == null){
		        				mediaMap.put(fileMd5, new MediaFile("video", fileMd5, mediaFileName, filePath));
		        			}
		        		}
		        		String audioFileName = values[videoPosition+4];
		        		if (StringUtils.isNotBlank(audioFileName)){
		        			String fileMd5 = values[videoPosition+17];
		        			String filePath = values[videoPosition+16];
		        			if (mediaMap.get(fileMd5) == null){
		        				mediaMap.put(fileMd5, new MediaFile("audio", fileMd5, audioFileName, filePath));
		        			}
		        		}
		        		String picFileName = values[videoPosition+7];
		        		if (StringUtils.isNotBlank(picFileName)){
		        			String fileMd5 = values[videoPosition+19];
		        			String filePath = values[videoPosition+18];
		        			if (mediaMap.get(fileMd5) == null){
		        				mediaMap.put(fileMd5, new MediaFile("pic", fileMd5, picFileName, filePath));
		        			}
		        		}
		        	}
		        	i++;
		    }
		    System.out.println(mediaMap.toString());
		    
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
