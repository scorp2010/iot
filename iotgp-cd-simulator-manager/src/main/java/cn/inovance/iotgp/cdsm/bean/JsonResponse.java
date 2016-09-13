package cn.inovance.iotgp.cdsm.bean;

public class JsonResponse {

	/** 成功标识位 */
	private boolean success;
	/** 消息 */
	private String msg;
	/** 返回的数据结果 */
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
