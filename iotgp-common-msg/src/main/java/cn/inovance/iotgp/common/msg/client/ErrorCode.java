package cn.inovance.iotgp.common.msg.client;

public enum ErrorCode {

	OK(0x00, "OK"), // 成功
	INTERNAL_SERVER_ERROR(0x01, "Internal Server Error"), // 服务器内部错误
	SECURITY_CODE_INVALID(0x02, "SECURITY_CODE_INVALID"), // 安全码无效
	DEVICE_NOT_CONNECT(0x03, "DEVICE_NOT_CONNECT"), // 设备未连接
	DEVICE_AUDIO_LINE_BUSY(0x04, "DEVICE_AUDIO_LINE_BUSY") // 设备语音占线
	;

	private short code;
	private String value;

	ErrorCode(Integer code, String value) {
		this.code = Short.parseShort(code.toString());
		this.value = value;
	}

	public short getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
