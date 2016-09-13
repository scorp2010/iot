package cn.inovance.iotgp.common.msg.server;

public class PhotoViewRsp extends Response {

	private String fileUrl;

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public PhotoViewRsp() {
		this.msgType = MsgType.PHOTO_VIEW_RSP;
	}

	public PhotoViewRsp(VideoViewReq req) {
		super(req);
		this.msgType = MsgType.PHOTO_VIEW_RSP;
	}

	public PhotoViewRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.PHOTO_VIEW_RSP;
	}

}
