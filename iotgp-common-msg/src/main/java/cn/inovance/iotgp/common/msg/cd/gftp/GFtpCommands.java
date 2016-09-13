/**
 * Project Name:iotgp-common-msg
 * File Name:GFtpCommands.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-13下午1:50:53
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd.gftp;
/**
 * ClassName:GFtpCommands <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-13 下午1:50:53 <br/>
 * @author   z1979
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class GFtpCommands {

	/** 采集设备鉴权请求 */
	public static final short LOGIN_REQ = 0X01;
	
	/** 采集设备鉴权响应 */
	public static final short LOGIN_RSP = 0X02;
	
	/** 传输文件请求 */
	public static final short START_TRANSFER_REQ = 0X03;
	
	/** 传输文件响应 */
	public static final short START_TRANSFER_RSP = 0X04;
	
	/** 文件数据传输 */
	public static final short FILE_DATA_TRANSFER = 0X05;
	
	/** 传输结束通知 */
	public static final short END_TRANSFER_NOTIFY = 0X06;	
	
	/** 传输结束响应 */
	public static final short END_TRANSFER_RSP = 0X07;	
}

