/**
 * Project Name:iotgp-common-msg
 * File Name:GftpCommand.java
 * Package Name:cn.inovance.iotgp.common.msg.cd.gftp
 * Date:2014-6-9上午11:23:41
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd.gftp;

import cn.inovance.iotgp.common.msg.cd.meta.PDU;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;

/**
 * ClassName:GftpCommand <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-9 上午11:23:41 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public abstract class GftpCommand extends PDU {
	/**
	 * 命令类型
	 */
	protected ShortPdu commandid = new ShortPdu();

	public ShortPdu getCommandid() {
		return commandid;
	}

	public void setCommandid(ShortPdu commandid) {
		this.commandid = commandid;
	}

}
