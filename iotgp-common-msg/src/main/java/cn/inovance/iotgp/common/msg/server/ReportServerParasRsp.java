/**
 * Project Name:cdag
 * File Name:ServerLoginRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:47:08
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.ServerParameter;

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
public class ReportServerParasRsp extends Response {

	private List<ServerParameter> parameters = new ArrayList<ServerParameter>();

	public List<ServerParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ServerParameter> parameters) {
		this.parameters = parameters;
	}

	public ReportServerParasRsp() {
		this.msgType = MsgType.REPORT_SERVER_PARAS_RSP;
	}

	public ReportServerParasRsp(ReportServerParasReq req) {
		super(req);
		this.msgType = MsgType.REPORT_SERVER_PARAS_RSP;
	}

	public ReportServerParasRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.REPORT_SERVER_PARAS_RSP;
	}
}
