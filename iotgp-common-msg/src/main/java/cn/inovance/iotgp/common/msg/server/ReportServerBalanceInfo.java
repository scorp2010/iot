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

import cn.inovance.iotgp.common.msg.server.bean.ServerBalance;
import cn.inovance.iotgp.common.msg.server.bean.ServerMsgAddress;
import cn.inovance.iotgp.common.msg.util.JsonBinder;

/**
 * ClassName:ReportServerBalanceInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-9 下午5:31:45 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ReportServerBalanceInfo extends Request {

	private List<ServerBalance> balanceInfos = new ArrayList<ServerBalance>();

	public List<ServerBalance> getBalanceInfos() {
		return balanceInfos;
	}

	public void setBalanceInfos(List<ServerBalance> balanceInfos) {
		this.balanceInfos = balanceInfos;
	}

	public ReportServerBalanceInfo() {
		this.msgType = MsgType.REPORT_SERVER_BALANCE_INFO;
	}

	public static void main(String[] args) {
		ReportServerBalanceInfo req = new ReportServerBalanceInfo();
		req.setTo((new ServerMsgAddress("gdhs")).toString());
		System.out.println(req.toString());
		List<ServerBalance> parameters = new ArrayList<ServerBalance>();
		req.setBalanceInfos(parameters);
		parameters.add(new ServerBalance("external_host", "192.168.60.47"));
		ReportServerBalanceInfo req1 = JsonBinder.normalBinder.fromJson(
				req.toString(), ReportServerBalanceInfo.class);
		System.out.println(req1.toString());
	}
}
