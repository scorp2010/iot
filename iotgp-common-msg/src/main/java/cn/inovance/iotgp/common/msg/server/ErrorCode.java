package cn.inovance.iotgp.common.msg.server;

public enum ErrorCode {

	OK(0), SERVER_INTERAL_ERROR(-1), PARAM_INVALID(-2), SYS_ACCOUNT_OR_PSW_ERROR(
			0x1001), SYS_ACCOUNT_ALREADY_LOGIN(0x1002), SYS_ACCOUNT_NOT_LOGIN(
			0x1003), SYS_ACCOUNT_NOT_AVALIABLE(0x1004), SECURITY_CODE_ERROR(
			0x1005), LOGIN_RELOCATE(0x1006), DEVICE_OFFLINE(0x2001), DATA_NOT_EXIST(0x2002), NO_LASTER_VERSIOIN(
			0x2003);

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	// 构造函数，枚举类型只能为私有
	private ErrorCode(int errorCode) {
		this.code = errorCode;
	}

	@Override
	public String toString() {
		return String.valueOf(this.code);
	}

}
