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
public class StopTdRealtimeDataRsp extends Response {

	private String securityCode;

	private String rdtsHost;

	private Integer rdtsPort;

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

	public StopTdRealtimeDataRsp() {
		this.msgType = MsgType.STOP_TD_REALTIME_DATA_RSP;
	}

	public StopTdRealtimeDataRsp(StopTdRealtimeDataReq req) {
		super(req);
		this.msgType = MsgType.STOP_TD_REALTIME_DATA_RSP;
	}

	public StopTdRealtimeDataRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.STOP_TD_REALTIME_DATA_RSP;
	}
}
