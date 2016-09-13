package cn.inovance.iotgp.common.msg.cdSimulator;

public class Response extends JsonObject {

	private Long msgSeq;

	public Long getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(Long msgSeq) {
		this.msgSeq = msgSeq;
	}

	private int errorCode = 0;

	private String value = "OK";

	public Response() {

	}

	public Response(Request req) {
		this.setFrom(req.getTo());
		this.setTo(req.getFrom());
		this.setMsgSeq(req.getMsgSeq());
		this.setReserve(req.getReserve());
	}

	public Response(long msgSeq) {
		this.msgSeq = msgSeq;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
