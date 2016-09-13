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
public class DataConnEstablishRsp extends Response {
	
	private String deviceSerial;
	
	private String tdSerial;

	public DataConnEstablishRsp() {
		this.msgType = MsgType.TD_REALTIME_DATA_ESTABLISH_RSP;
	}

	public DataConnEstablishRsp(DataConnEstablishReq req) {
		super(req);
		this.msgType = MsgType.TD_REALTIME_DATA_ESTABLISH_RSP;
	}

	public DataConnEstablishRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.TD_REALTIME_DATA_ESTABLISH_RSP;
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
