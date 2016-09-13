package cn.inovance.iotgp.cdSimulator.pkg;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class NewPlaylistUpdateNotify extends AbstractReqPackage {
	
	protected final int LENGTH_PLAYLIST_ID=32;
	
	private StringPdu newAdPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID, "");
	private StringPdu newMsgPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID, "");
	private StringPdu newPicMsgPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID, "");
	private StringPdu newConfPlaylistId=new StringPdu(this.LENGTH_PLAYLIST_ID, "");
	
	public String getNewAdPlaylistId() {
		return newAdPlaylistId.getValue();
	}

	public void setNewAdPlaylistId(String newAdPlaylistId) {
		this.newAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, newAdPlaylistId);
	}
	
	public String getNewMsgPlaylistId() {
		return newMsgPlaylistId.getValue();
	}

	public void setNewMsgPlaylistId(String newMsgPlaylistId) {
		this.newMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, newMsgPlaylistId);
	}
	
	/**
	 * @return the newPicMsgPlaylistId
	 */
	public StringPdu getNewPicMsgPlaylistId() {
		return newPicMsgPlaylistId;
	}

	/**
	 * @param newPicMsgPlaylistId the newPicMsgPlaylistId to set
	 */
	public void setNewPicMsgPlaylistId(String newPicMsgPlaylistId) {
		this.newPicMsgPlaylistId =  new StringPdu(LENGTH_PLAYLIST_ID, newPicMsgPlaylistId);
	}

	/**
	 * @return the newConfPlaylistId
	 */
	public StringPdu getNewConfPlaylistId() {
		return newConfPlaylistId;
	}

	/**
	 * @param newConfPlaylistId the newConfPlaylistId to set
	 */
	public void setNewConfPlaylistId(String newConfPlaylistId) {
		this.newConfPlaylistId =  new StringPdu(LENGTH_PLAYLIST_ID, newConfPlaylistId);
	}

	public NewPlaylistUpdateNotify() {
		this.header.setMsgType(Commands.TRNSPARENT_NEW_PLAYLIST_NOTIFY);
	}

	public NewPlaylistUpdateNotify(byte[] data) {
		super(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, newAdPlaylistId.getBytes(), index);
		index += newAdPlaylistId.getLength();
		ByteOps.addByteArray(data, newMsgPlaylistId.getBytes(), index);
		index += newMsgPlaylistId.getLength();
		ByteOps.addByteArray(data, newPicMsgPlaylistId.getBytes(), index);
		index += newPicMsgPlaylistId.getLength();
		ByteOps.addByteArray(data, newConfPlaylistId.getBytes(), index);
		index += newConfPlaylistId.getLength();
	}

	@Override
	public void parseBody() throws MessageParseException {
		newAdPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += newAdPlaylistId.getLength();
		newMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += newMsgPlaylistId.getLength();
		newPicMsgPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += newPicMsgPlaylistId.getLength();
		newConfPlaylistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
		index += newConfPlaylistId.getLength();
	}
	

	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER +  + newAdPlaylistId.getLength() +  this.newMsgPlaylistId.getLength()
				+ this.newPicMsgPlaylistId.getLength() + this.newConfPlaylistId.getLength();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewPlaylistUpdateNotify [newAdPlaylistId=" + newAdPlaylistId
				+ ", newMsgPlaylistId=" + newMsgPlaylistId
				+ ", newPicMsgPlaylistId=" + newPicMsgPlaylistId
				+ ", newConfPlaylistId=" + newConfPlaylistId + "]";
	}

	public static void main(String[] args) throws MessageParseException{
		NewPlaylistUpdateNotify req = new NewPlaylistUpdateNotify();
		req.setNewAdPlaylistId("123123123");
		req.setNewMsgPlaylistId("123123123");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}
}
