package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class NewPlaylistUpdateNotify extends AbstractReqPackage {
	public final int PACKAGE_LENGTH=110;
	
	protected final int INDEX_AD_PLAYLIST_ID=46;
	protected final int INDEX_MSG_PLAYLIST_ID=78;
	
	protected final int LENGTH_AD_PLAYLIST_ID=32;
	protected final int LENGTH_MSG_PLAYLIST_ID=32;
	
	private StringPdu newAdPlaylistId=new StringPdu();
	private StringPdu newMsgPlaylistId=new StringPdu();
	
	public String getNewAdPlaylistId() {
		return newAdPlaylistId.getValue();
	}

	public void setNewAdPlaylistId(String newAdPlaylistId) {
		this.newAdPlaylistId = new StringPdu(LENGTH_AD_PLAYLIST_ID, newAdPlaylistId);
	}
	
	public String getNewMsgPlaylistId() {
		return newMsgPlaylistId.getValue();
	}

	public void setNewMsgPlaylistId(String newMsgPlaylistId) {
		this.newMsgPlaylistId = new StringPdu(LENGTH_MSG_PLAYLIST_ID, newMsgPlaylistId);
	}

	public NewPlaylistUpdateNotify(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.NEW_AD_UPDATE_NOTIFY);
	}
	
	public NewPlaylistUpdateNotify(byte[] data){
		this.data = data;
		header.setData(data);
	}
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, newAdPlaylistId.getBytes(), INDEX_AD_PLAYLIST_ID);
		ByteOps.addByteArray(data, newMsgPlaylistId.getBytes(), INDEX_MSG_PLAYLIST_ID);
	}

	@Override
	public void parseBody() throws MessageParseException {
		newAdPlaylistId = new StringPdu(LENGTH_AD_PLAYLIST_ID, this.INDEX_AD_PLAYLIST_ID, data);
		newMsgPlaylistId = new StringPdu(LENGTH_MSG_PLAYLIST_ID, this.INDEX_MSG_PLAYLIST_ID, data);
	}

	@Override
	public String toString() {
		return "NewPlaylistUpdateNotify [newAdPlaylistId=" + newAdPlaylistId
				+ ", newMsgPlaylistId=" + newMsgPlaylistId + "]";
	}

	public static void main(String[] args) throws MessageParseException{
		NewPlaylistUpdateNotify req = new NewPlaylistUpdateNotify();
		req.setNewAdPlaylistId("123123123");
		req.setNewMsgPlaylistId("123123123");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}
}
