package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class GetPlaylistReq extends AbstractReqPackage {
	public final int PACKAGE_LENGTH=174;
	
	protected final int INDEX_AD_PLAYLIST_ID=46;
	protected final int INDEX_MSG_PLAYLIST_ID=78;
	protected final int INDEX_PICAD_PLAYLIST_ID=110;
	protected final int INDEX_CONFIG_ID=142;
	
	protected final int LENGTH_PLAYLIST_ID=32;
	
	private StringPdu currentAdPlaylistId=new StringPdu();
	private StringPdu currentMsgPlaylistId=new StringPdu();
	private StringPdu currentPicAdPlaylistId=new StringPdu();
	private StringPdu currentConfigId=new StringPdu();
	
	public String getCurrentAdPlaylistId() {
		return currentAdPlaylistId.getValue();
	}

	public void setCurrentAdPlaylistId(String currentAdPlaylistId) {
		this.currentAdPlaylistId = new StringPdu(INDEX_AD_PLAYLIST_ID, currentAdPlaylistId);
	}
	
	public String getCurrentMsgPlaylistId() {
		return currentMsgPlaylistId.getValue();
	}

	public void setCurrentMsgPlaylistId(String currentMsgPlaylistId) {
		this.currentMsgPlaylistId = new StringPdu(INDEX_MSG_PLAYLIST_ID, currentMsgPlaylistId);
	}
	
	public String getCurrentPicAdPlaylistId() {
		return currentPicAdPlaylistId.getValue();
	}

	public void setCurrentPicAdPlaylistId(StringPdu currentPicAdPlaylistId) {
		this.currentPicAdPlaylistId = currentPicAdPlaylistId;
	}

	public String getCurrentConfigId() {
		return currentConfigId.getValue();
	}

	public void setCurrentConfigId(StringPdu currentConfigId) {
		this.currentConfigId = currentConfigId;
	}

	public GetPlaylistReq(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.AD_UPDATE_QUERY_REQ);
	}
	
	public GetPlaylistReq(byte[] data){
		this.data = data;
		header.setData(data);
	}
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, currentAdPlaylistId.getBytes(), INDEX_AD_PLAYLIST_ID);
		ByteOps.addByteArray(data, currentMsgPlaylistId.getBytes(), INDEX_MSG_PLAYLIST_ID);
		ByteOps.addByteArray(data, currentPicAdPlaylistId.getBytes(), INDEX_PICAD_PLAYLIST_ID);
		ByteOps.addByteArray(data, currentConfigId.getBytes(), INDEX_CONFIG_ID);
	}

	@Override
	public void parseBody() throws MessageParseException {
		currentAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, this.INDEX_AD_PLAYLIST_ID, data);
		currentMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, this.INDEX_MSG_PLAYLIST_ID, data);
		if (data.length == PACKAGE_LENGTH){
			currentPicAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, this.INDEX_PICAD_PLAYLIST_ID, data);
			currentConfigId = new StringPdu(LENGTH_PLAYLIST_ID, this.INDEX_CONFIG_ID, data);
		}
	}

	public static void main(String[] args) throws MessageParseException{
		GetPlaylistReq req = new GetPlaylistReq();
		//req.setCurrentAdPlaylistId("");
		//req.setCurrentMsgPlaylistId("");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
		System.out.println(req.toString());
	}

	@Override
	public String toString() {
		return "GetPlaylistReq [currentAdPlaylistId=" + currentAdPlaylistId
				+ ", currentMsgPlaylistId=" + currentMsgPlaylistId
				+ ", currentPicAdPlaylistId=" + currentPicAdPlaylistId
				+ ", currentConfigId=" + currentConfigId + "]";
	}

}
