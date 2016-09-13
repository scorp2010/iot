package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class PlaylistUpdateCompleteNotify extends AbstractReqPackage {
	public final int PACKAGE_LENGTH=222;
	
	protected final int INDEX_PLAYLIST_ID=46;
	protected final int INDEX_PLAYLIST_FILE_PATH=78;
	protected final int INDEX_PLAYLIST_FILE_NAME=128;
	protected final int INDEX_PLAYLIST_FILE_MD5=177;
	protected final int INDEX_UPDATE_STATUS=209;
	protected final int INDEX_REMARK=210;
	
	public final int LENGTH_PLAYLIST_ID=32;
	public final int LENGTH_PLAYLIST_FILE_PATH=50;
	public final int LENGTH_PLAYLIST_FILE_NAME=49;
	public final int LENGTH_PLAYLIST_FILE_MD5=32;
	public final int LENGTH_REMARK=12;
	
	private StringPdu playlistId=new StringPdu();
	private StringPdu playlistFilePath=new StringPdu();
	private StringPdu playlistFileName=new StringPdu();
	private StringPdu playlistFileMd5=new StringPdu();
	private ShortPdu updateStatus = new ShortPdu();
	private StringPdu remark=new StringPdu();
	
	public ShortPdu getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(short updateStatus) {
		this.updateStatus = new ShortPdu(updateStatus);
	}

	public String getRemark() {
		return remark.getValue();
	}

	public void setRemark(String remark) {
		this.remark = new StringPdu(LENGTH_REMARK, remark);
	}
	
	public String getPlaylistId() {
		return playlistId.getValue();
	}

	public void setPlaylistId(String playlistId) {
		this.playlistId = new StringPdu(LENGTH_PLAYLIST_ID, playlistId);
	}

	public String getPlaylistFilePath() {
		return playlistFilePath.getValue();
	}

	public void setPlaylistFilePath(String playlistFilePath) {
		this.playlistFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, playlistFilePath);
	}

	public String getPlaylistFileName() {
		return playlistFileName.getValue();
	}

	public void setPlaylistFileName(String playlistFileName) {
		this.playlistFileName = new StringPdu(LENGTH_PLAYLIST_FILE_NAME, playlistFileName);
	}

	public String getPlaylistFileMd5() {
		return playlistFileMd5.getValue();
	}

	public void setPlaylistFileMd5(String playlistFileMd5) {
		this.playlistFileMd5 = new StringPdu(LENGTH_PLAYLIST_FILE_MD5, playlistFileMd5);
	}

	public PlaylistUpdateCompleteNotify(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.AD_UPDATE_COMPLETE_NOTIFY);
	}
	
	public PlaylistUpdateCompleteNotify(byte[] data){
		this.data = data;
		header.setData(data);
	}
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, playlistId.getBytes(), INDEX_PLAYLIST_ID);
		ByteOps.addByteArray(data, playlistFilePath.getBytes(), INDEX_PLAYLIST_FILE_PATH);
		ByteOps.addByteArray(data, playlistFileName.getBytes(), INDEX_PLAYLIST_FILE_NAME);
		ByteOps.addByteArray(data, playlistFileMd5.getBytes(), INDEX_PLAYLIST_FILE_MD5);
		ByteOps.addByteArray(data, updateStatus.getBytes(), INDEX_UPDATE_STATUS);
		ByteOps.addByteArray(data, remark.getBytes(), INDEX_REMARK);
	}

	@Override
	public void parseBody() throws MessageParseException {
		playlistId = new StringPdu(LENGTH_PLAYLIST_ID, this.INDEX_PLAYLIST_ID, data);
		playlistFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, this.INDEX_PLAYLIST_FILE_PATH, data);
		playlistFileName = new StringPdu(LENGTH_PLAYLIST_FILE_NAME, this.INDEX_PLAYLIST_FILE_NAME, data);
		playlistFileMd5 = new StringPdu(LENGTH_PLAYLIST_FILE_MD5, this.INDEX_PLAYLIST_FILE_MD5, data);
		updateStatus = new ShortPdu(INDEX_UPDATE_STATUS, data);
		remark = new StringPdu(LENGTH_REMARK, this.INDEX_REMARK, data);
	}

	public static void main(String[] args) throws MessageParseException{
		PlaylistUpdateCompleteNotify req = new PlaylistUpdateCompleteNotify();
		req.setPlaylistId("123123123");
		req.setUpdateStatus((short)1);
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}

	@Override
	public String toString() {
		return "PlaylistUpdateCompleteNotify [playlistId=" + playlistId
				+ ", playlistFilePath=" + playlistFilePath
				+ ", playlistFileName=" + playlistFileName
				+ ", playlistFileMd5=" + playlistFileMd5 + ", updateStatus="
				+ updateStatus + ", remark=" + remark + "]";
	}

}
