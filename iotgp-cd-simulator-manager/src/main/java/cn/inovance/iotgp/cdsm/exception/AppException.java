package cn.inovance.iotgp.cdsm.exception;

import org.apache.commons.lang3.StringUtils;

import cn.inovance.iotgp.cdsm.util.MessageUtils;

/**
 * 应用异常基类
 * 
 * @author c2100
 * 
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = -6983227709594164508L;
	private String errorCode;
	private Object[] params;

	public AppException() {
		super();
	}

	public AppException(String msgKey) {
		super(msgKey);
		this.errorCode = msgKey;
	}

	public AppException(String msgKey, Object[] params) {
		super(msgKey);
		this.errorCode = msgKey;
		this.params = params;
	}

	public AppException(String msgKey, Throwable cause) {
		super(msgKey, cause);
		this.errorCode = msgKey;
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		try {
			if (StringUtils.isNotBlank(this.errorCode)) {
				if (params != null && params.length > 0) {
					return MessageUtils.getMessage(this.errorCode, params);
				} else {
					return MessageUtils.getMessage(this.errorCode);
				}
			}
		} catch (Exception ex) {
		}

		return super.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
