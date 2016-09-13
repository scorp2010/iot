package cn.inovance.iotgp.common.msg.server;

public class PhotoViewReq extends Request {
	/**
	 * 采集设备注册码.
	 */
	private String deviceSerial;
	/**
	 * 摄像头编号.
	 */
	private String cameraSerial;

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getCameraSerial() {
		return cameraSerial;
	}

	public void setCameraSerial(String cameraSerial) {
		this.cameraSerial = cameraSerial;
	}

	public PhotoViewReq() {
		this.msgType = MsgType.PHOTO_VIEW_REQ;
	}

	public static void main(String[] args) {
		PhotoViewReq req = new PhotoViewReq();
		req.setFrom("1111");
		req.setDeviceSerial("12312");
		req.setCameraSerial("1");
		System.out.println(req.getMsgType());
	}

}
