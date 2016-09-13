package cn.inovance.iotgp.common.msg.server;

public class AudioViewRsp extends Response {

	private String securityCode;

	private String mddsHost;

	private Integer mddsPort;
	private String deviceSerial;
	/** 麦克风编号 */
	private String microphoneSerial;
	
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getMddsHost() {
		return mddsHost;
	}

	public void setMddsHost(String mddsHost) {
		this.mddsHost = mddsHost;
	}

	public Integer getMddsPort() {
		return mddsPort;
	}

	public void setMddsPort(Integer mddsPort) {
		this.mddsPort = mddsPort;
	}
	
	public AudioViewRsp() {
		this.msgType = MsgType.AUDIO_VIEW_RSP;
	}

	public AudioViewRsp(AudioViewReq req) {
		super(req);
		this.msgType = MsgType.AUDIO_VIEW_RSP;
	}

	public AudioViewRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.AUDIO_VIEW_RSP;
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
	
}
