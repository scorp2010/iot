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
 * ClassName:ServerLoginRsp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:47:08 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class GetTdRealtimeDataRsp extends Response {

	public GetTdRealtimeDataRsp() {
		this.msgType = MsgType.GET_TD_REALTIME_DATA_RSP;
	}

	public GetTdRealtimeDataRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.GET_TD_REALTIME_DATA_RSP;
	}

	public GetTdRealtimeDataRsp(GetTdRealtimeDataReq req) {
		super(req);
		this.msgType = MsgType.GET_TD_REALTIME_DATA_RSP;
	}

	private String securityCode;

	private String deviceSerial;

	private String tdSerial;

	private String rdtsHost;

	private Integer rdtsPort;

	private Integer rdtsWsPort;

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getRdtsHost() {
		return rdtsHost;
	}

	public void setRdtsHost(String rdtsHost) {
		this.rdtsHost = rdtsHost;
	}

	public Integer getRdtsPort() {
		return rdtsPort;
	}

	public void setRdtsPort(Integer rdtsPort) {
		this.rdtsPort = rdtsPort;
	}

	public Integer getRdtsWsPort() {
		return rdtsWsPort;
	}

	public void setRdtsWsPort(Integer rdtsWsPort) {
		this.rdtsWsPort = rdtsWsPort;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getTdSerial() {
		return tdSerial;
	}

	public void setTdSerial(String tdSerial) {
		this.tdSerial = tdSerial;
	}

}
