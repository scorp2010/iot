package cn.inovance.iotgp.cdSimulator.pkg;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetPlaylistRsp extends AbstractReqPackage {
	
	public final int LENGTH_FTP_PASSWORD=16;
	public final int LENGTH_PLAYLIST_ID=32;
	public final int LENGTH_PLAYLIST_FILE_PATH=50;
	public final int LENGTH_PLAYLIST_FILE_NAME=49;
	public final int LENGTH_CONFIG_FILE_NAME=46;
	public final int LENGTH_PLAYLIST_FILE_MD5=32;
	public final int LENGTH_LOG_UPLOAD_PATH=48;
	
	private IntPdu errorCode = new IntPdu(0);
	private ShortPdu[] ftpHost = new ShortPdu[6];//默认处理成功
	
	private IntPdu ftpPort = new IntPdu(0);
	
	private StringPdu ftpPassword=new StringPdu(this.LENGTH_FTP_PASSWORD,"");
	private StringPdu newAdPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID,"");
	private StringPdu adPlaylistFilePath=new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH,"");
	private StringPdu adPlaylistFileName=new StringPdu(this.LENGTH_PLAYLIST_FILE_NAME,"");
	private StringPdu adPlaylistFileMd5=new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5,"");
	private StringPdu newMsgPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID,"");
	private StringPdu msgPlaylistFilePath=new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH,"");
	private StringPdu msgPlaylistFileName=new StringPdu(this.LENGTH_PLAYLIST_FILE_NAME,"");
	private StringPdu msgPlaylistFileMd5=new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5,"");
	private StringPdu logUploadPath=new StringPdu(this.LENGTH_LOG_UPLOAD_PATH,"");
	private StringPdu newPicPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID,"");
	private StringPdu picPlaylistFilePath=new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH,"");
	private StringPdu picPlaylistFileName=new StringPdu(this.LENGTH_CONFIG_FILE_NAME,"");
	private StringPdu picPlaylistFileMd5=new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5,"");
	private StringPdu newConfigId=new StringPdu(this.LENGTH_PLAYLIST_ID,"");
	private StringPdu configFilePath=new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH,"");
	private StringPdu configFileName=new StringPdu(this.LENGTH_CONFIG_FILE_NAME,"");
	private StringPdu configFileMd5=new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5,"");
	
	/**
	 * @return the errorCode
	 */
	public IntPdu getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = new IntPdu(errorCode);
	}

	public String getNewPicPlaylistId() {
		return newPicPlaylistId.getValue();
	}

	public void setNewPicPlaylistId(String newPicPlaylistId) {
		this.newPicPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, newPicPlaylistId);
	}

	public String getPicPlaylistFilePath() {
		return picPlaylistFilePath.getValue();
	}

	public void setPicPlaylistFilePath(String picPlaylistFilePath) {
		this.picPlaylistFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, picPlaylistFilePath); 
	}

	public String getPicPlaylistFileName() {
		return picPlaylistFileName.getValue();
	}

	public void setPicPlaylistFileName(String picPlaylistFileName) {
		this.picPlaylistFileName =new StringPdu(LENGTH_CONFIG_FILE_NAME, picPlaylistFileName);
	}

	public String getPicPlaylistFileMd5() {
		return picPlaylistFileMd5.getValue();
	}

	public void setPicPlaylistFileMd5(String picPlaylistFileMd5) {
		this.picPlaylistFileMd5 =new StringPdu(LENGTH_PLAYLIST_FILE_MD5, picPlaylistFileMd5);
	}

	public String getNewConfigId() {
		return newConfigId.getValue();
	}

	public void setNewConfigId(String newConfigId) {
		this.newConfigId = new StringPdu(LENGTH_PLAYLIST_ID, newConfigId);
	}

	public String getConfigFilePath() {
		return configFilePath.getValue();
	}

	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, configFilePath); 
	}

	public String getConfigFileName() {
		return configFileName.getValue();
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = new StringPdu(LENGTH_CONFIG_FILE_NAME, configFileName);
	}

	public String getConfigFileMd5() {
		return configFileMd5.getValue();
	}

	public void setConfigFileMd5(String configFileMd5) {
		this.configFileMd5 = new StringPdu(LENGTH_PLAYLIST_FILE_MD5, configFileMd5);
	}

	public void setLogUploadPath(String newLogUploadPath) {
		this.logUploadPath = new StringPdu(LENGTH_LOG_UPLOAD_PATH, newLogUploadPath);
	}

	public String getLogUploadPath() {
		return logUploadPath.getValue();
	}
	
	public Short[] getFtpHost() {
		Short[] values = new Short[6];
		for(int i = 0;i<ftpHost.length; i++){
			if(ftpHost[i] != null)
			values[i] = ftpHost[i].getValue();
		}
		return values;
	}
	
	public void setFtpHost(String ftpHost) throws MessageParseException {
		String[] ftpArray = ftpHost.split("\\.");
		for(int i = 0;i<ftpArray.length; i++){
			this.ftpHost[i] = new ShortPdu(Short.parseShort(ftpArray[i]));
		}
	}

	public void setFtpHost(Short[] ftpHost) throws MessageParseException {
		for(int i = 0;i<ftpHost.length; i++){
			this.ftpHost[i] = new ShortPdu(ftpHost[i]);
		}
	}

	public int getFtpPort() {
		return ftpPort.getValue();
	}

	public void setFtpPort(int ftpPort) {
		this.ftpPort = new IntPdu(ftpPort);
	}

	public String getFtpPassword() {
		return ftpPassword.getValue();
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = new StringPdu(LENGTH_FTP_PASSWORD, ftpPassword);
	}

	public String getNewAdPlaylistId() {
		return newAdPlaylistId.getValue();
	}

	public void setNewAdPlaylistId(String newAdPlaylistId) {
		this.newAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, newAdPlaylistId);
	}

	public String getAdPlaylistFilePath() {
		return adPlaylistFilePath.getValue();
	}

	public void setAdPlaylistFilePath(String adPlaylistFilePath) {
		this.adPlaylistFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, adPlaylistFilePath);
	}

	public String getAdPlaylistFileName() {
		return adPlaylistFileName.getValue();
	}

	public void setAdPlaylistFileName(String adPlaylistFileName) {
		this.adPlaylistFileName = new StringPdu(LENGTH_PLAYLIST_FILE_NAME, adPlaylistFileName);
	}

	public String getAdPlaylistFileMd5() {
		return adPlaylistFileMd5.getValue();
	}

	public void setAdPlaylistFileMd5(String adPlaylistFileMd5) {
		this.adPlaylistFileMd5 = new StringPdu(LENGTH_PLAYLIST_FILE_MD5, adPlaylistFileMd5);
	}

	public String getNewMsgPlaylistId() {
		return newMsgPlaylistId.getValue();
	}

	public void setNewMsgPlaylistId(String newMsgPlaylistId) {
		this.newMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, newMsgPlaylistId);
	}

	public String getMsgPlaylistFilePath() {
		return msgPlaylistFilePath.getValue();
	}

	public void setMsgPlaylistFilePath(String msgPlaylistFilePath) {
		this.msgPlaylistFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, msgPlaylistFilePath);
	}

	public String getMsgPlaylistFileName() {
		return msgPlaylistFileName.getValue();
	}

	public void setMsgPlaylistFileName(String msgPlaylistFileName) {
		this.msgPlaylistFileName = new StringPdu(LENGTH_PLAYLIST_FILE_NAME, msgPlaylistFileName);
	}

	public String getMsgPlaylistFileMd5() {
		return msgPlaylistFileMd5.getValue();
	}

	public void setMsgPlaylistFileMd5(String msgPlaylistFileMd5) {
		this.msgPlaylistFileMd5 = new StringPdu(LENGTH_PLAYLIST_FILE_MD5, msgPlaylistFileMd5);
	}

	public GetPlaylistRsp() {
		this.header.setMsgType(Commands.TRNSPARENT_GET_PLAYLIST_RSP);
	}

	public GetPlaylistRsp(byte[] data) {
		super(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, errorCode.getBytes(), index);
		index += errorCode.getLength();
		for(int i = 0; i<ftpHost.length; i++){
			ShortPdu tmp = ftpHost[i];
			if(tmp == null){
				ByteOps.addByteArray(data, (byte)0x00, i+index);
			}else 
				ByteOps.addByteArray(data, tmp.getBytes(), i+index);
		}
		index += ftpHost.length;
		ByteOps.addByteArray(data, ftpPort.getBytes(), index);
		index += ftpPort.getLength();
		ByteOps.addByteArray(data, this.ftpPassword.getBytes(), index);
		index += ftpPassword.getLength();
		ByteOps.addByteArray(data, this.newAdPlaylistId.getBytes(), index);
		index += newAdPlaylistId.getLength();
		ByteOps.addByteArray(data, this.adPlaylistFilePath.getBytes(), index);
		index += adPlaylistFilePath.getLength();
		ByteOps.addByteArray(data, this.adPlaylistFileName.getBytes(), index);
		index += adPlaylistFileName.getLength();
		ByteOps.addByteArray(data, this.adPlaylistFileMd5.getBytes(), index);
		index += adPlaylistFileMd5.getLength();
		ByteOps.addByteArray(data, this.newMsgPlaylistId.getBytes(), index);
		index += newMsgPlaylistId.getLength();
		ByteOps.addByteArray(data, this.msgPlaylistFilePath.getBytes(), index);
		index += msgPlaylistFilePath.getLength();
		ByteOps.addByteArray(data, this.msgPlaylistFileName.getBytes(), index);
		index += msgPlaylistFileName.getLength();
		ByteOps.addByteArray(data, this.msgPlaylistFileMd5.getBytes(), index);
		index += msgPlaylistFileMd5.getLength();
		ByteOps.addByteArray(data, this.newPicPlaylistId.getBytes(), index);
		index += newPicPlaylistId.getLength();
		ByteOps.addByteArray(data, this.picPlaylistFilePath.getBytes(), index);
		index += picPlaylistFilePath.getLength();
		ByteOps.addByteArray(data, this.picPlaylistFileName.getBytes(), index);
		index += picPlaylistFileName.getLength();
		ByteOps.addByteArray(data, this.picPlaylistFileMd5.getBytes(), index);
		index += picPlaylistFileMd5.getLength();
		ByteOps.addByteArray(data, this.newConfigId.getBytes(), index);
		index += newConfigId.getLength();
		ByteOps.addByteArray(data, this.configFilePath.getBytes(), index);
		index += configFilePath.getLength();
		ByteOps.addByteArray(data, this.configFileName.getBytes(), index);
		index += configFileName.getLength();
		ByteOps.addByteArray(data, this.configFileMd5.getBytes(), index);
		index += configFileMd5.getLength();
		ByteOps.addByteArray(data, this.logUploadPath.getBytes(), index);
		index += logUploadPath.getLength();
	}

	@Override
	public void parseBody() throws MessageParseException {
		errorCode = new IntPdu(index, data);
		index += errorCode.getLength();
		for(int i = 0; i<ftpHost.length; i++){
			ftpHost[i] = new ShortPdu(i+index , data);
		}
		index += ftpHost.length;
		ftpPort = new IntPdu(index, data);
		index += ftpPort.getLength();
		ftpPassword = new StringPdu(this.LENGTH_FTP_PASSWORD, index, data);
		index += ftpPassword.getLength();
		newAdPlaylistId = new StringPdu(this.LENGTH_PLAYLIST_ID, index, data);
		index += newAdPlaylistId.getLength();
		adPlaylistFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, index, data);
		index += adPlaylistFilePath.getLength();
		adPlaylistFileName = new StringPdu(this.LENGTH_PLAYLIST_FILE_NAME, index, data);
		index += adPlaylistFileName.getLength();
		adPlaylistFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, index, data);
		index += adPlaylistFileMd5.getLength();
		newMsgPlaylistId = new StringPdu(this.LENGTH_PLAYLIST_ID, index, data);
		index += newMsgPlaylistId.getLength();
		msgPlaylistFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, index, data);
		index += msgPlaylistFilePath.getLength();
		msgPlaylistFileName = new StringPdu(this.LENGTH_PLAYLIST_FILE_NAME, index, data);
		index += msgPlaylistFileName.getLength();
		msgPlaylistFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, index, data);
		index += msgPlaylistFileMd5.getLength();
		newPicPlaylistId = new StringPdu(this.LENGTH_PLAYLIST_ID, index, data);
		index += newPicPlaylistId.getLength();
		picPlaylistFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, index, data);
		index += picPlaylistFilePath.getLength();
		picPlaylistFileName = new StringPdu(this.LENGTH_CONFIG_FILE_NAME, index, data);
		index += picPlaylistFileName.getLength();
		picPlaylistFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, index, data);
		index += picPlaylistFileMd5.getLength();
		newConfigId = new StringPdu(this.LENGTH_PLAYLIST_ID, index, data);
		index += newConfigId.getLength();
		configFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, index, data);
		index += configFilePath.getLength();
		configFileName = new StringPdu(this.LENGTH_CONFIG_FILE_NAME, index, data);
		index += configFileName.getLength();
		configFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, index, data);
		index += configFileMd5.getLength();
		logUploadPath = new StringPdu(this.LENGTH_LOG_UPLOAD_PATH, index, data);
		index += logUploadPath.getLength();
	}
	
	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER +  + errorCode.getLength() +  this.ftpHost.length
				+ this.ftpPort.getLength() + this.ftpPassword.getLength()
				+ (this.newAdPlaylistId.getLength() + adPlaylistFilePath.getLength()
				+ adPlaylistFileMd5.getLength())*4 +adPlaylistFileName.getLength()*2 + configFileName.getLength()*2
				+ logUploadPath.getLength();
	}

	public static void main(String[] args) throws MessageParseException{
		GetPlaylistRsp rsp = new GetPlaylistRsp();
		rsp.setErrorCode(1);
		rsp.setAdPlaylistFileMd5("234234234");
		rsp.setFtpHost("192.168.1.10");
		rsp.setFtpPort(1000);
		rsp.setLogUploadPath("113456789");
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		GetPlaylistRsp rsp2 = new GetPlaylistRsp(rsp.getData());
		rsp2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		System.out.println(rsp2.getLogUploadPath());
	}

	public void setFtpHost(ShortPdu[] ftpHost) {
		this.ftpHost = ftpHost;
	}

	public void setFtpPort(IntPdu ftpPort) {
		this.ftpPort = ftpPort;
	}

	public void setFtpPassword(StringPdu ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public void setNewAdPlaylistId(StringPdu newAdPlaylistId) {
		this.newAdPlaylistId = newAdPlaylistId;
	}

	public void setAdPlaylistFilePath(StringPdu adPlaylistFilePath) {
		this.adPlaylistFilePath = adPlaylistFilePath;
	}

	public void setAdPlaylistFileName(StringPdu adPlaylistFileName) {
		this.adPlaylistFileName = adPlaylistFileName;
	}

	public void setAdPlaylistFileMd5(StringPdu adPlaylistFileMd5) {
		this.adPlaylistFileMd5 = adPlaylistFileMd5;
	}

	public void setNewMsgPlaylistId(StringPdu newMsgPlaylistId) {
		this.newMsgPlaylistId = newMsgPlaylistId;
	}

	public void setMsgPlaylistFilePath(StringPdu msgPlaylistFilePath) {
		this.msgPlaylistFilePath = msgPlaylistFilePath;
	}

	public void setMsgPlaylistFileName(StringPdu msgPlaylistFileName) {
		this.msgPlaylistFileName = msgPlaylistFileName;
	}

	public void setMsgPlaylistFileMd5(StringPdu msgPlaylistFileMd5) {
		this.msgPlaylistFileMd5 = msgPlaylistFileMd5;
	}

	public void setLogUploadPath(StringPdu logUploadPath) {
		this.logUploadPath = logUploadPath;
	}

	@Override
	public String toString() {
		return "GetPlaylistRsp [ftpHost=" + Arrays.toString(ftpHost)
				+ ", ftpPort=" + ftpPort + ", ftpPassword=" + ftpPassword
				+ ", newAdPlaylistId=" + newAdPlaylistId
				+ ", adPlaylistFilePath=" + adPlaylistFilePath
				+ ", adPlaylistFileName=" + adPlaylistFileName
				+ ", adPlaylistFileMd5=" + adPlaylistFileMd5
				+ ", newMsgPlaylistId=" + newMsgPlaylistId
				+ ", msgPlaylistFilePath=" + msgPlaylistFilePath
				+ ", msgPlaylistFileName=" + msgPlaylistFileName
				+ ", msgPlaylistFileMd5=" + msgPlaylistFileMd5
				+ ", logUploadPath=" + logUploadPath + ", newPicPlaylistId="
				+ newPicPlaylistId + ", picPlaylistFilePath="
				+ picPlaylistFilePath + ", picPlaylistFileName="
				+ picPlaylistFileName + ", picPlaylistFileMd5="
				+ picPlaylistFileMd5 + ", newConfigId=" + newConfigId
				+ ", configFilePath=" + configFilePath + ", configFileName="
				+ configFileName + ", configFileMd5=" + configFileMd5 + "]";
	}

}
