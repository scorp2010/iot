/**
 * Project Name:cdag
 * File Name:ServerLoginRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:47:08
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

/**
 * ClassName:VideoViewRsp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:47:08 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class VideoViewRsp extends Response {

	private String securityCode;

	private String mddsHost;

	private Integer mddsPort;
	private String deviceSerial;
	
	private String cameraSerial;
	
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

	public VideoViewRsp() {
		this.msgType = MsgType.VIDEO_VIEW_RSP;
	}

	public VideoViewRsp(VideoViewReq req) {
		super(req);
		this.msgType = MsgType.VIDEO_VIEW_RSP;
	}

	public VideoViewRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.VIDEO_VIEW_RSP;
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
}
