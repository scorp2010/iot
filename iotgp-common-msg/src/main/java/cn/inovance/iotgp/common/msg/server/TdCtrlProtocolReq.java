package cn.inovance.iotgp.common.msg.server;

public class TdCtrlProtocolReq extends Request {

	public TdCtrlProtocolReq() {
		this.msgType = MsgType.CTRL_PROTOCOL_REQ;
	}

	/** 控制器协议类型名称 */
	private String ctrlType;

	/** 控制器协议最后更新时间秒时间戳 */
	private Long updateTime;

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
