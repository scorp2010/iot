package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class PlaylistUpdateCompleteNotifyRsp extends AbstractRspPackage {
	public final int PACKAGE_LENGTH=80;
	
	protected final int INDEX_PLAYLIST_ID=48;
	
	public final int LENGTH_PLAYLIST_ID=32;
	
	private StringPdu playlistId=new StringPdu();
	
	
	public String getPlaylistId() {
		return playlistId.getValue();
	}

	public void setPlaylistId(String playlistId) {
		this.playlistId = new StringPdu(LENGTH_PLAYLIST_ID, playlistId);
	}


	public PlaylistUpdateCompleteNotifyRsp(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.AD_UPDATE_COMPLETE_NOTIFY_RSP);
	}
	
	public PlaylistUpdateCompleteNotifyRsp(byte[] data){
		this.data = data;
		header.setData(data);
	}
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, playlistId.getBytes(), INDEX_PLAYLIST_ID);
	}

	@Override
	public void parseBody() throws MessageParseException {
		playlistId = new StringPdu(LENGTH_PLAYLIST_ID, this.INDEX_PLAYLIST_ID, data);
	}

	public static void main(String[] args) throws MessageParseException{
		PlaylistUpdateCompleteNotifyRsp req = new PlaylistUpdateCompleteNotifyRsp();
		req.setPlaylistId("123123123");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}

	@Override
	public String toString() {
		return "PlaylistUpdateCompleteNotifyRsp [PACKAGE_LENGTH="
				+ PACKAGE_LENGTH + ", INDEX_PLAYLIST_ID=" + INDEX_PLAYLIST_ID
				+ ", LENGTH_PLAYLIST_ID=" + LENGTH_PLAYLIST_ID
				+ ", playlistId=" + playlistId + ", INDEX_ERROR_CODE="
				+ INDEX_ERROR_CODE + ", header=" + header + ", commandid="
				+ commandid + "]";
	}

}
