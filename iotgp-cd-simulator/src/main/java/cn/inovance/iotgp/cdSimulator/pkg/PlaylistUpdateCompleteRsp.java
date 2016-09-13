package cn.inovance.iotgp.cdSimulator.pkg;

import cn.inovance.iotgp.common.msg.cd.AbstractReqPackage;
import cn.inovance.iotgp.common.msg.cd.Commands;
import cn.inovance.iotgp.common.msg.cd.Header;
import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class PlaylistUpdateCompleteRsp extends AbstractReqPackage {
	
	public final int LENGTH_PLAYLIST_ID=32;
	
	private StringPdu playlistId=new StringPdu(this.LENGTH_PLAYLIST_ID, "");
	private IntPdu errorCode = new IntPdu(0);
	
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
	public String getPlaylistId() {
		return playlistId.getValue();
	}

	public void setPlaylistId(String playlistId) {
		this.playlistId = new StringPdu(LENGTH_PLAYLIST_ID, playlistId);
	}


	public PlaylistUpdateCompleteRsp() {
		this.header.setMsgType(Commands.TRNSPARENT_PLAYLIST_UPDATED_RSP);
	}

	public PlaylistUpdateCompleteRsp(byte[] data) {
		super(data);
	}
	
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, playlistId.getBytes(), index);
		index+= playlistId.getLength();
	}

	@Override
	public void parseBody() throws MessageParseException {
		playlistId = new StringPdu(LENGTH_PLAYLIST_ID, index, data);
	}

	public static void main(String[] args) throws MessageParseException{
		PlaylistUpdateCompleteRsp req = new PlaylistUpdateCompleteRsp();
		req.setErrorCode(1);
		req.setPlaylistId("123123123");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}
	
	@Override
	protected int getMsgLength() {
		return Header.LENGTH_HEADER + errorCode.getLength() + this.playlistId.getLength();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlaylistUpdateCompleteRsp [playlistId=" + playlistId
				+ ", errorCode=" + errorCode + ", header=" + header + "]";
	}


}
