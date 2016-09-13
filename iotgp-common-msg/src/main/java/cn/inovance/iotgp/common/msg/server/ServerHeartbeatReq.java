/**
 * Project Name:cdag
 * File Name:ServerLoginReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午5:31:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.server.bean.ServerMsgAddress;
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
public class ServerHeartbeatReq extends Request {

	public ServerHeartbeatReq() {
		this.msgType = MsgType.SERVER_HEARTBEAT_REQ;
	}

	public static void main(String[] args) {
		ServerHeartbeatReq req = new ServerHeartbeatReq();
		req.setTo((new ServerMsgAddress("gdhs")).toString());
		System.out.println(req.toString());
		ServerHeartbeatReq req1 = JsonBinder.normalBinder.fromJson(
				req.toString(), ServerHeartbeatReq.class);
		System.out.println(req1.toString());
	}
}
