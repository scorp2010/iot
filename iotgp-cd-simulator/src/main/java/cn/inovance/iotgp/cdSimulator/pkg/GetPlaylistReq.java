package cn.inovance.iotgp.cdSimulator.pkg;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;


public class GetPlaylistReq extends AbstractReqPackage {

	protected final int LENGTH_PLAYLIST_ID=32;
	
	private StringPdu currentAdPlaylistId=new StringPdu();
	private StringPdu currentMsgPlaylistId=new StringPdu();
	private StringPdu currentPicAdPlaylistId=new StringPdu();
	private StringPdu currentConfigId=new StringPdu();
	
	public String getCurrentAdPlaylistId() {
		return currentAdPlaylistId.getValue();
	}

	public void setCurrentAdPlaylistId(String currentAdPlaylistId) {
		this.currentAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, currentAdPlaylistId);
	}
	
	public String getCurrentMsgPlaylistId() {
		return currentMsgPlaylistId.getValue();
	}

	public void setCurrentMsgPlaylistId(String currentMsgPlaylistId) {
		this.currentMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, currentMsgPlaylistId);
	}
	
	public String getCurrentPicAdPlaylistId() {
		return currentPicAdPlaylistId.getValue();
	}

	public void setCurrentPicAdPlaylistId(String currentPicAdPlaylistId) {
		this.currentPicAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, currentPicAdPlaylistId);
	}

	public String getCurrentConfigId() {
		return currentConfigId.getValue();
	}

	public void setCurrentConfigId(String currentConfigId) {
		this.currentConfigId = new StringPdu(LENGTH_PLAYLIST_ID, currentConfigId);
	}
	
	public GetPlaylistReq() {
		this.header.setMsgType(Commands.TRNSPARENT_GET_PLAYLIST_REQ);
	}

	public GetPlaylistReq(byte[] data) {
		super(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, currentAdPlaylistId.getBytes(), index);
		index += currentAdPlaylistId.getLength();
		ByteOps.addByteArray(data, currentMsgPlaylistId.getBytes(), index);
		index += currentMsgPlaylistId.getLength();
		ByteOps.addByteArray(data, currentPicAdPlaylistId.getBytes(), index);
		index += currentPicAdPlaylistId.getLength();
		ByteOps.addByteArray(data, currentConfigId.getBytes(), index);
		index += currentConfigId.getLength();
	}

	@Override
	public void parseBody() throws MessageParseException {
		currentAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += currentAdPlaylistId.getLength();
		currentMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += currentMsgPlaylistId.getLength();
		currentPicAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += currentPicAdPlaylistId.getLength();
		currentConfigId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += currentConfigId.getLength();
	}

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + this.currentAdPlaylistId.getLength()
				+ this.currentMsgPlaylistId.getLength() + this.currentPicAdPlaylistId.getLength()
				+ this.currentConfigId.getLength();
	}
	
	public static void main(String[] args) throws MessageParseException{

		GetPlaylistReq req = new GetPlaylistReq();
		req.setCurrentAdPlaylistId("11111111111111111111111111111");
		req.setCurrentMsgPlaylistId("222222222222222222222222222");
		req.setCurrentConfigId("222222222222222222222222222");
		req.setCurrentPicAdPlaylistId("222222222222222222222222222");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
		GetPlaylistReq getPlaylistReq1 = new GetPlaylistReq(req.getData());
		getPlaylistReq1.parse();
		System.out.println(getPlaylistReq1.toString());
	
	}

	@Override
	public String toString() {
		return "GetPlaylistReq [currentAdPlaylistId=" + currentAdPlaylistId
				+ ", currentMsgPlaylistId=" + currentMsgPlaylistId
				+ ", currentPicAdPlaylistId=" + currentPicAdPlaylistId
				+ ", currentConfigId=" + currentConfigId + "]";
	}

}
