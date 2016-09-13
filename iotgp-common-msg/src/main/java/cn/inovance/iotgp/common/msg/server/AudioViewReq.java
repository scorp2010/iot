package cn.inovance.iotgp.common.msg.server;

public class AudioViewReq extends Request {

	/** 安全码 */
	private String securityCode;
	/** 采集设备注册码 */
	private String deviceSerial;
	/** 麦克风编号 */
	private String microphoneSerial;
	/** 通信方式:0 TCP，1 UDP */
	private short connType = 0;
	/** 是否广播 : 0 不广播，1 广播 */
	private short broadcast = 0;
	/** 通道 : 1：单声道 2：立体声 */
	private short channel = 1;
	/** 采样配置信息.
	 * 默认0x01
	 *  */
	private short sampling = 0x01;
	
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getMicrophoneSerial() {
		return microphoneSerial;
	}

	public void setMicrophoneSerial(String microphoneSerial) {
		this.microphoneSerial = microphoneSerial;
	}

	public short getConnType() {
		return connType;
	}

	public void setConnType(short connType) {
		this.connType = connType;
	}

	public short getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(short broadcast) {
		this.broadcast = broadcast;
	}

	public short getChannel() {
		return channel;
	}

	public void setChannel(short channel) {
		this.channel = channel;
	}

	public short getSampling() {
		return sampling;
	}

	public void setSampling(short sampling) {
		this.sampling = sampling;
	}

	public AudioViewReq() {
		this.msgType = MsgType.AUDIO_VIEW_REQ;
	}
	
}
