package cn.inovance.iotgp.common.msg.ad;

import java.util.Arrays;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetPlaylistRsp extends AbstractRspPackage {
	protected final int INDEX_FTP_HOST=48;
	protected final int INDEX_PORT=54;
	protected final int INDEX_FTP_PASSWORD=56;
	protected final int INDEX_NEW_AD_PLAYLIST_ID=72;
	protected final int INDEX_AD_PLAYLIST_FILE_PATH=104;
	protected final int INDEX_AD_PLAYLIST_FILE_NAME=154;
	protected final int INDEX_AD_PLAYLIST_FILE_MD5=203;
	protected final int INDEX_NEW_MSG_PLAYLIST_ID=235;
	protected final int INDEX_MSG_PLAYLIST_FILE_PATH=267;
	protected final int INDEX_MSG_PLAYLIST_FILE_NAME=317;
	protected final int INDEX_MSG_PLAYLIST_FILE_MD5=366;
	protected final int INDEX_NEW_PICAD_PLAYLIST_ID=398;
	protected final int INDEX_PICAD_PLAYLIST_FILE_PATH=430;
	protected final int INDEX_PICAD_PLAYLIST_FILE_NAME=480;
	protected final int INDEX_PICAD_PLAYLIST_FILE_MD5=526;
	protected final int INDEX_NEW_CONFIG_PLAYLIST_ID=558;
	protected final int INDEX_CONFIG_PLAYLIST_FILE_PATH=590;
	protected final int INDEX_CONFIG_PLAYLIST_FILE_NAME=640;
	protected final int INDEX_CONFIG_PLAYLIST_FILE_MD5=686;
	protected final int INDEX_LOG_UPLOAD_PATH=718;
	
	public final int PACKAGE_LENGTH=766;
	
	public final int LENGTH_FTP_PASSWORD=16;
	public final int LENGTH_PLAYLIST_ID=32;
	public final int LENGTH_PLAYLIST_FILE_PATH=50;
	public final int LENGTH_PLAYLIST_FILE_NAME=49;
	public final int LENGTH_CONFIG_FILE_NAME=46;
	public final int LENGTH_PLAYLIST_FILE_MD5=32;
	public final int LENGTH_LOG_UPLOAD_PATH=48;
	
	private ShortPdu[] ftpHost = new ShortPdu[6];//默认处理成功
	
	private IntPdu ftpPort = new IntPdu(0);
	
	private StringPdu ftpPassword=new StringPdu();
	private StringPdu newAdPlaylistId=new StringPdu();
	private StringPdu adPlaylistFilePath=new StringPdu();
	private StringPdu adPlaylistFileName=new StringPdu();
	private StringPdu adPlaylistFileMd5=new StringPdu();
	private StringPdu newMsgPlaylistId=new StringPdu();
	private StringPdu msgPlaylistFilePath=new StringPdu();
	private StringPdu msgPlaylistFileName=new StringPdu();
	private StringPdu msgPlaylistFileMd5=new StringPdu();
	private StringPdu logUploadPath=new StringPdu();
	private StringPdu newPicPlaylistId=new StringPdu();
	private StringPdu picPlaylistFilePath=new StringPdu();
	private StringPdu picPlaylistFileName=new StringPdu();
	private StringPdu picPlaylistFileMd5=new StringPdu();
	private StringPdu newConfigId=new StringPdu();
	private StringPdu configFilePath=new StringPdu();
	private StringPdu configFileName=new StringPdu();
	private StringPdu configFileMd5=new StringPdu();
	
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

	public GetPlaylistRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.AD_UPDATE_QUERY_RSP);
	}
	
	public GetPlaylistRsp(Header header){
		data = new byte[PACKAGE_LENGTH];
		this.header = header.clone(Commands.AD_UPDATE_QUERY_RSP, data);
		
	}
	
	public GetPlaylistRsp(byte[] data){
		this.data = data;
		header.setData(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		for(int index = this.INDEX_FTP_HOST; index<ftpHost.length+INDEX_FTP_HOST; index++){
			ShortPdu tmp = ftpHost[index - INDEX_FTP_HOST];
			if(tmp == null){
				ByteOps.addByteArray(data, (byte)0x00, index);
			}else 
				ByteOps.addByteArray(data, tmp.getBytes(), index);
		}
		ByteOps.addByteArray(data, ftpPort.getBytes(), INDEX_PORT);
		ByteOps.addByteArray(data, this.ftpPassword.getBytes(), this.INDEX_FTP_PASSWORD);
		ByteOps.addByteArray(data, this.newAdPlaylistId.getBytes(), this.INDEX_NEW_AD_PLAYLIST_ID);
		ByteOps.addByteArray(data, this.adPlaylistFilePath.getBytes(), this.INDEX_AD_PLAYLIST_FILE_PATH);
		ByteOps.addByteArray(data, this.adPlaylistFileName.getBytes(), this.INDEX_AD_PLAYLIST_FILE_NAME);
		ByteOps.addByteArray(data, this.adPlaylistFileMd5.getBytes(), this.INDEX_AD_PLAYLIST_FILE_MD5);
		ByteOps.addByteArray(data, this.newMsgPlaylistId.getBytes(), this.INDEX_NEW_MSG_PLAYLIST_ID);
		ByteOps.addByteArray(data, this.msgPlaylistFilePath.getBytes(), this.INDEX_MSG_PLAYLIST_FILE_PATH);
		ByteOps.addByteArray(data, this.msgPlaylistFileName.getBytes(), this.INDEX_MSG_PLAYLIST_FILE_NAME);
		ByteOps.addByteArray(data, this.msgPlaylistFileMd5.getBytes(), this.INDEX_MSG_PLAYLIST_FILE_MD5);
		ByteOps.addByteArray(data, this.logUploadPath.getBytes(), this.INDEX_LOG_UPLOAD_PATH);
		ByteOps.addByteArray(data, this.newPicPlaylistId.getBytes(), this.INDEX_NEW_PICAD_PLAYLIST_ID);
		ByteOps.addByteArray(data, this.picPlaylistFilePath.getBytes(), this.INDEX_PICAD_PLAYLIST_FILE_PATH);
		ByteOps.addByteArray(data, this.picPlaylistFileName.getBytes(), this.INDEX_PICAD_PLAYLIST_FILE_NAME);
		ByteOps.addByteArray(data, this.picPlaylistFileMd5.getBytes(), this.INDEX_PICAD_PLAYLIST_FILE_MD5);
		ByteOps.addByteArray(data, this.newConfigId.getBytes(), this.INDEX_NEW_CONFIG_PLAYLIST_ID);
		ByteOps.addByteArray(data, this.configFilePath.getBytes(), this.INDEX_CONFIG_PLAYLIST_FILE_PATH);
		ByteOps.addByteArray(data, this.configFileName.getBytes(), this.INDEX_CONFIG_PLAYLIST_FILE_NAME);
		ByteOps.addByteArray(data, this.configFileMd5.getBytes(), this.INDEX_CONFIG_PLAYLIST_FILE_MD5);
	}

	@Override
	public void parseBody() throws MessageParseException {
		for(int index = 0; index<ftpHost.length; index++){
			ftpHost[index] = new ShortPdu(index+INDEX_FTP_HOST , data);
		}
		ftpPort = new IntPdu(this.INDEX_PORT, data);
		ftpPassword = new StringPdu(this.LENGTH_FTP_PASSWORD, this.INDEX_FTP_PASSWORD, data);
		newAdPlaylistId = new StringPdu(this.LENGTH_PLAYLIST_ID, this.INDEX_NEW_AD_PLAYLIST_ID, data);
		adPlaylistFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, this.INDEX_AD_PLAYLIST_FILE_PATH, data);
		adPlaylistFileName = new StringPdu(this.LENGTH_PLAYLIST_FILE_NAME, this.INDEX_AD_PLAYLIST_FILE_NAME, data);
		adPlaylistFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, this.INDEX_AD_PLAYLIST_FILE_MD5, data);
		newMsgPlaylistId = new StringPdu(this.LENGTH_PLAYLIST_ID, this.INDEX_NEW_MSG_PLAYLIST_ID, data);
		msgPlaylistFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, this.INDEX_MSG_PLAYLIST_FILE_PATH, data);
		msgPlaylistFileName = new StringPdu(this.LENGTH_PLAYLIST_FILE_NAME, this.INDEX_MSG_PLAYLIST_FILE_NAME, data);
		msgPlaylistFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, this.INDEX_MSG_PLAYLIST_FILE_MD5, data);
		logUploadPath = new StringPdu(this.LENGTH_LOG_UPLOAD_PATH, this.INDEX_LOG_UPLOAD_PATH, data);
		newPicPlaylistId = new StringPdu(this.LENGTH_PLAYLIST_ID, this.INDEX_NEW_PICAD_PLAYLIST_ID, data);
		picPlaylistFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, this.INDEX_PICAD_PLAYLIST_FILE_PATH, data);
		picPlaylistFileName = new StringPdu(this.LENGTH_CONFIG_FILE_NAME, this.INDEX_PICAD_PLAYLIST_FILE_NAME, data);
		picPlaylistFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, this.INDEX_PICAD_PLAYLIST_FILE_MD5, data);
		newConfigId = new StringPdu(this.LENGTH_PLAYLIST_ID, this.INDEX_NEW_CONFIG_PLAYLIST_ID, data);
		configFilePath = new StringPdu(this.LENGTH_PLAYLIST_FILE_PATH, this.INDEX_CONFIG_PLAYLIST_FILE_PATH, data);
		configFileName = new StringPdu(this.LENGTH_CONFIG_FILE_NAME, this.INDEX_CONFIG_PLAYLIST_FILE_NAME, data);
		configFileMd5 = new StringPdu(this.LENGTH_PLAYLIST_FILE_MD5, this.INDEX_CONFIG_PLAYLIST_FILE_MD5, data);
	}

	public static void main(String[] args) throws MessageParseException{
		GetPlaylistRsp rsp = new GetPlaylistRsp();
		rsp.setErrorCode(1);
		rsp.getHeader().setEquipmentNo("1234566");
		rsp.getHeader().setSessionID("11111111111111");
		rsp.setAdPlaylistFileMd5("234234234");
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		GetPlaylistRsp rsp2 = new GetPlaylistRsp(rsp.getData());
		rsp2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
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
