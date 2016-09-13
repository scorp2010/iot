package cn.inovance.iotgp.common.msg.exception;

public class MessageParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public MessageParseException() {
		super();
	}

	public MessageParseException(String errorMsg) {
		super(errorMsg);
	}

	public MessageParseException(Throwable cause) {
		super(cause);
	}

	public MessageParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
