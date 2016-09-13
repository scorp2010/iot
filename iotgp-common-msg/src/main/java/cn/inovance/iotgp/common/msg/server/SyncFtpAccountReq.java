/**
 * Project Name:iotgp-common-msg
 * File Name:SyncFtpAccountReq.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-5-27上午10:50:25
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.FtpAccountInfo;

/**
 * ClassName:SyncFtpAccountReq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-27 上午10:50:25 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class SyncFtpAccountReq extends Request {

	private List<FtpAccountInfo> ftpAccounts = new ArrayList<FtpAccountInfo>();

	public List<FtpAccountInfo> getFtpAccounts() {
		return ftpAccounts;
	}

	public void setFtpAccounts(List<FtpAccountInfo> ftpAccounts) {
		this.ftpAccounts = ftpAccounts;
	}

	public SyncFtpAccountReq() {
		this.msgType = MsgType.SYNC_FTP_ACCOUNT_REQ;
	}

}
