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
public class SyncFtpAccountRsp extends Response {

	public SyncFtpAccountRsp() {
		this.msgType = MsgType.SYNC_FTP_ACCOUNT_RSP;
	}

	public SyncFtpAccountRsp(SyncFtpAccountReq req) {
		super(req);
		this.msgType = MsgType.SYNC_FTP_ACCOUNT_RSP;
	}

	public SyncFtpAccountRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.SYNC_FTP_ACCOUNT_RSP;
	}
}
