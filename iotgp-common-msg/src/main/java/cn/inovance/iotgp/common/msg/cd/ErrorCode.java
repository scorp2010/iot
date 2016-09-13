package cn.inovance.iotgp.common.msg.cd;

/**
 * ClassName: ErrorCode Function: 消息错误码定义
 * 
 * @author zhangqiang
 * @version 2013-08-29
 */
public enum ErrorCode {

	OK(0x00, "OK"), // 成功
	INTERNAL_SERVER_ERROR(0x01, "Internal Server Error"), // 服务器内部错误
	INVAILD_EQUIPMENT_NO(0x02, "Invaild EQUIPMET NO"), // 终端设备编码无效
	INVAILD_SESSION_ID(0x03, "Invaild Session ID"), // SESSION id 无效
	INVALID_LOGIN_PSW(0x04, "Invalid login password"), // 设备登陆密码错误
	INVALID_SECURITY_CODE(0x05, "Invalid security code"), // 无效的安全码
	MSG_PARSE_ERROR(0x06, "Message parse error")// 消息解析出错
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
