/**
 * Project Name:cdag
 * File Name:CdMsgUtil.java
 * Package Name:cn.inovance.iotgp.common.util
 * Date:2014-4-8下午4:12:08
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.util;

import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:CdMsgUtil <br/>
 * Function: 采集设备消息处理工具类. <br/>
 * Date: 2014-4-8 下午4:12:08 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class CdMsgUtil {

	public static int getCommandId(byte[] msg) {
		int commandId = 0;
		if (msg != null && msg.length < 5) {
			commandId = 0;
		}
		if (msg != null && msg.length >= 5) {
			commandId = ByteOps.makeInt(msg[2], msg[3]);
		}
		return commandId;
	}
}
