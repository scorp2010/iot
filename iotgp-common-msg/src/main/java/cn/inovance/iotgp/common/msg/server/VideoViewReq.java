/**
 * Project Name:cdag
 * File Name:ServerLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:31:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

/**
 * ClassName:VideoViewReq <br/>
 * Function: 客户端实时视频浏览请求. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:31:45 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class VideoViewReq extends Request {
	private String securityCode;
	/**
	 * 采集设备注册码. 
	 */
	private String deviceSerial;
	/**
	 * 摄像头编号.
	 */
	private String cameraSerial;
	/**
	 * 通信方式:0  TCP，1 UDP.
	 */
	private Integer connType = 0;
	/**
	 * 视频类型 : 0 直播，1 点播 .
	 */
	private Integer viewType = 0;
	/**
	 * 视频分辨率 1 - QCIF\ 2 - CIF \3 - DCIF\ 4 - D1.
	 */
	private Integer resolution = 1;

	private String fileName;

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

	public String getCameraSerial() {
		return cameraSerial;
	}

	public void setCameraSerial(String cameraSerial) {
		this.cameraSerial = cameraSerial;
	}

	public Integer getConnType() {
		return connType;
	}

	public void setConnType(Integer connType) {
		this.connType = connType;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public Integer getResolution() {
		return resolution;
	}

	public void setResolution(Integer resolution) {
		this.resolution = resolution;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public VideoViewReq() {
		this.msgType = MsgType.VIDEO_VIEW_REQ;
	}

	public static void main(String[] args){
		VideoViewReq req = new VideoViewReq();
		req.setFrom("1111");
		req.setDeviceSerial("12312");
		req.setCameraSerial("1");
		System.out.println(req.getMsgType());
	}

}
