/**
 * Project Name:cdag
 * File Name:ServerLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:31:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.ServerMsgAddress;
import cn.inovance.iotgp.common.msg.server.bean.ServerParameter;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

/**
 * ClassName:ServerLoginReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:31:45 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ReportServerParasReq extends Request {

	private List<ServerParameter> parameters = new ArrayList<ServerParameter>();

	public List<ServerParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ServerParameter> parameters) {
		this.parameters = parameters;
	}

	public ReportServerParasReq() {
		this.msgType = MsgType.REPORT_SERVER_PARAS_REQ;
	}

	public static void main(String[] args) {
		ReportServerParasReq req = new ReportServerParasReq();
		req.setTo((new ServerMsgAddress("gdhs")).toString());
		System.out.println(req.toString());
		List<ServerParameter> parameters = new ArrayList<ServerParameter>();
		req.setParameters(parameters);
		parameters.add(new ServerParameter("external_host", "192.168.60.47"));
		ReportServerParasReq req1 = JsonBinder.normalBinder.fromJson(
				req.toString(), ReportServerParasReq.class);
		System.out.println(req1.toString());
	}
}
