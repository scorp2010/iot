package cn.inovance.iotgp.cdSimulator.pkg;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class PlaylistUpdateCompleteReq extends AbstractReqPackage {
	
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
	private StringPdu remark=new StringPdu(12,"");
	
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

	public PlaylistUpdateCompleteReq() {
		this.header.setMsgType(Commands.TRNSPARENT_PLAYLIST_UPDATED_REQ);
	}

	public PlaylistUpdateCompleteReq(byte[] data) {
		super(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, playlistId.getBytes(), index);
		index += playlistId.getLength();
		ByteOps.addByteArray(data, playlistFilePath.getBytes(), index);
		index += playlistFilePath.getLength();
		ByteOps.addByteArray(data, playlistFileName.getBytes(), index);
		index += playlistFileName.getLength();
		ByteOps.addByteArray(data, playlistFileMd5.getBytes(), index);
		index += playlistFileMd5.getLength();
		ByteOps.addByteArray(data, updateStatus.getBytes(), index);
		index += updateStatus.getLength();
		ByteOps.addByteArray(data, remark.getBytes(), index);
		index += remark.getLength();
	}

	@Override
	public void parseBody() throws MessageParseException {
		playlistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += playlistId.getLength();
		playlistFilePath = new StringPdu(LENGTH_PLAYLIST_FILE_PATH, index, data);
		index += playlistFilePath.getLength();
		playlistFileName = new StringPdu(LENGTH_PLAYLIST_FILE_NAME, index, data);
		index += playlistFileName.getLength();
		playlistFileMd5 = new StringPdu(LENGTH_PLAYLIST_FILE_MD5, index, data);
		index += playlistFileMd5.getLength();
		updateStatus = new ShortPdu(index, data);
		index += updateStatus.getLength();
		remark = new StringPdu(LENGTH_REMARK, index, data);
		index += remark.getLength();
	}
	
	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.playlistId.getLength()
				+ this.playlistFilePath.getLength() + this.playlistFileName.getLength()
				+ this.playlistFileMd5.getLength() + updateStatus.getLength()
				+ remark.getLength();
	}

	public static void main(String[] args) throws MessageParseException{
		PlaylistUpdateCompleteReq req = new PlaylistUpdateCompleteReq();
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
