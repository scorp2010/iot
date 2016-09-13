
package cn.inovance.iotgp.common.msg.ad;

/**   
 * ClassName: ErrorCode   
 * Function:  消息错误码定义
 *   
 * @author   zhangqiang   
 * @version  2013-08-29
 */
public enum ErrorCode {

	OK(0, "OK"), //成功
	INTERNAL_SERVER_ERROR(-1, "Internal Server Error"), //服务器内部错误
	INVAILD_EQUIPMENT_NO(0x0001, "Invaild EQUIPMET NO"), // 终端设备编码无效
	INVAILD_SESSION_ID(0x0002, "Invaild Session ID"), // SESSION id 无效
	INVALID_LOGIN_PSW(0x0003, "Invalid login password"), // 设备登陆密码错误
	INVALID_SECURITY_CODE(0x0004, "Invalid security code"), // 无效的安全码
	MSG_PARSE_ERROR(0x0004, "Message parse error")//消息解析出错
	;

	private int code;
	private String value;

	ErrorCode(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
