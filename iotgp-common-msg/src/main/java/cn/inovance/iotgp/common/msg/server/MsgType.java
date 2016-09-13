/**
 * Project Name:cdag
 * File Name:MsgType.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-4-9下午2:04:10
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

/**
 * ClassName:MsgType <br/>
 * Function: 服务器内部通信消息类型. <br/>
 * Date: 2014-4-9 下午2:04:10 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class MsgType {
	/** 子系统登陆请求消息. */
	public static final String SERVER_LOGIN_REQ = "SERVER_LOGIN_REQ";
	/** 子系统登陆响应消息. */
	public static final String SERVER_LOGIN_RSP = "SERVER_LOGIN_RSP";
	/** 子系统心跳消息. */
	public static final String SERVER_HEARTBEAT_REQ = "SERVER_HEARTBEAT_REQ";
	/** 子系统心跳响应消息. */
	public static final String SERVER_HEARTBEAT_RSP = "SERVER_HEARTBEAT_RSP";
	/** 子系统上报参数请求消息. */
	public static final String REPORT_SERVER_PARAS_REQ = "REPORT_SERVER_PARAS_REQ";
	/** 子系统上报参数请求消息. */
	public static final String REPORT_SERVER_PARAS_RSP = "REPORT_SERVER_PARAS_RSP";
	/** 采集设备透传消息. */
	public static final String DEVICE_DATA_TRANSFER = "DEVICE_DATA_TRANSFER";
	/** 手机App透传请求消息. */
	public static final String APP_DATA_TRANSFER_REQ = "APP_DATA_TRANSFER_REQ";
	/** 手机App透传响应消息. */
	public static final String APP_DATA_TRANSFER_RSP = "APP_DATA_TRANSFER_RSP";
	/** 采集设备状态变更请求消息. */
	public static final String DEVICE_STATUS_CHANGE_REQ = "DEVICE_STATUS_CHANGE_REQ";
	/** 采集设备状态变更响应消息. */
	public static final String DEVICE_STATUS_CHANGE_RSP = "DEVICE_STATUS_CHANGE_RSP";
	/** 目标设备状态变更请求消息. */
	public static final String TARGET_DEVICE_STATUS_CHANGE_REQ = "TARGET_DEVICE_STATUS_CHANGE_REQ";
	/** 目标设备状态变更响应消息. */
	public static final String TARGET_DEVICE_STATUS_CHANGE_RSP = "TARGET_DEVICE_STATUS_CHANGE_RSP";
	/** 采集设备信息同步请求消息. */
	public static final String DEVICE_INFO_SYNC_REQ = "DEVICE_INFO_SYNC_REQ";
	/** 采集设备信息同步响应消息. */
	public static final String DEVICE_INFO_SYNC_RSP = "DEVICE_INFO_SYNC_RSP";
	/** 目标设备信息同步请求消息. */
	public static final String TARGET_DEVICE_SYNC_REQ = "TARGET_DEVICE_SYNC_REQ";
	/** 目标设备信息同步响应消息. */
	public static final String TARGET_DEVICE_SYNC_RSP = "TARGET_DEVICE_SYNC_RSP";
	/** 控制器协议同步请求消息. */
	public static final String CTRL_PROTOCOL_SYNC_REQ = "CTRL_PROTOCOL_SYNC_REQ";
	/** 控制器协议同步响应消息. */
	public static final String CTRL_PROTOCOL_SYNC_RSP = "CTRL_PROTOCOL_SYNC_RSP";
	/** 故障信息同步请求消息. */
	public static final String FAULT_CODE_SYNC_REQ = "FAULT_CODE_SYNC_REQ";
	/** 故障信息同步响应消息. */
	public static final String FAULT_CODE_SYNC_RSP = "FAULT_CODE_SYNC_RSP";
	/** 指令库信息同步请求消息. */
	public static final String TD_CTRL_CMD_SYNC_REQ = "TD_CTRL_CMD_SYNC_REQ";
	/** 指令库信息同步响应消息. */
	public static final String TD_CTRL_CMD_SYNC_RSP = "TD_CTRL_CMD_SYNC_RSP";
	/** 控制器协议请求消息. */
	public static final String CTRL_PROTOCOL_REQ = "CTRL_PROTOCOL_REQ";
	/** 控制器协议响应消息. */
	public static final String CTRL_PROTOCOL_RSP = "CTRL_PROTOCOL_RSP";
	/** 客户信息同步请求消息. */
	public static final String CUSTOMER_INFO_SYNC_REQ = "CUSTOMER_INFO_SYNC_REQ";
	/** 客户信息同步响应消息. */
	public static final String CUSTOMER_INFO_SYNC_RSP = "CUSTOMER_INFO_SYNC_RSP";
	/** 设备参数信息同步请求消息. */
	public static final String DEVICE_PARAS_SYNC_REQ = "DEVICE_PARAS_SYNC_REQ";
	/** 设备参数信息同步响应消息. */
	public static final String DEVICE_PARAS_SYNC_RSP = "DEVICE_PARAS_SYNC_RSP";
	/** 新增动态gdhs通知. */
	public static final String ADD_NEW_GDHS_INFO_NOTIFY = "ADD_NEW_GDHS_INFO_NOTIFY";
	/** 上报服务器负载信息. */
	public static final String REPORT_SERVER_BALANCE_INFO = "REPORT_SERVER_BALANCE_INFO";
	/** 同步ftp账号信息请求. */
	public static final String SYNC_FTP_ACCOUNT_REQ = "SYNC_FTP_ACCOUNT_REQ";
	/** 同步ftp账号信息响应. */
	public static final String SYNC_FTP_ACCOUNT_RSP = "SYNC_FTP_ACCOUNT_RSP";
	/** 同步文件信息请求. */
	public static final String SYNC_FILE_INFO_REQ = "SYNC_FILE_INFO_REQ";
	/** 同步文件信息响应. */
	public static final String SYNC_FILE_INFO_RSP = "SYNC_FILE_INFO_RSP";
	/** 获取目标设备实时数据请求. */
	public static final String GET_TD_REALTIME_DATA_REQ = "GET_TD_REALTIME_DATA_REQ";
	/** 获取目标设备实时数据响应. */
	public static final String GET_TD_REALTIME_DATA_RSP = "GET_TD_REALTIME_DATA_RSP";
	/** 目标设备实时数据连接建立请求. */
	public static final String TD_REALTIME_DATA_ESTABLISH_REQ = "TD_REALTIME_DATA_ESTABLISH_REQ";
	/** 目标设备实时数据连接建立响应. */
	public static final String TD_REALTIME_DATA_ESTABLISH_RSP = "TD_REALTIME_DATA_ESTABLISH_RSP";
	/** 停止目标设备实时数据请求. */
	public static final String STOP_TD_REALTIME_DATA_REQ = "STOP_TD_REALTIME_DATA_REQ";
	/** 停止目标设备实时数据请求. */
	public static final String STOP_TD_REALTIME_DATA_RSP = "STOP_TD_REALTIME_DATA_RSP";
	/** 视频浏览请求. */
	public static final String VIDEO_VIEW_REQ = "VIDEO_VIEW_REQ";
	/** 视频浏览响应. */
	public static final String VIDEO_VIEW_RSP = "VIDEO_VIEW_RSP";
	/** 音频浏览请求. */
	public static final String AUDIO_VIEW_REQ = "AUDIO_VIEW_REQ";
	/** 音频浏览响应. */
	public static final String AUDIO_VIEW_RSP = "AUDIO_VIEW_RSP";
	/** 摄像头抓拍请求 */
	public static final String PHOTO_VIEW_REQ = "PHOTO_VIEW_REQ";
	/** 摄像头抓拍响应 */
	public static final String PHOTO_VIEW_RSP = "PHOTO_VIEW_RSP";
	/** 新广告更新通知. */
	public static final String NEW_ADS_UPDATE_NOTIFY = "NEW_ADS_UPDATE_NOTIFY";
	/** 新广告屏登陆通知. */
	public static final String NEW_ADSCREEN_LOGIN_NOTIFY = "NEW_ADSCREEN_LOGIN_NOTIFY";

	/** APP登陆请求 */
	public static final String APP_LOGIN_REQ = "APP_LOGIN_REQ";
	/** APP登陆响应 */
	public static final String APP_LOGIN_RSP = "APP_LOGIN_RSP";
	/** APP心跳请求 */
	public static final String APP_HEARTBEAT_REQ = "APP_HEARTBEAT_REQ";
	/** APP心跳响应 */
	public static final String APP_HEARTBEAT_RSP = "APP_HEARTBEAT_RSP";
	/** APP状态变更请求 */
	public static final String APP_STATUS_CHANGE_REQ = "APP_STATUS_CHANGE_REQ";
	/** APP状态变更响应 */
	public static final String APP_STATUS_CHANGE_RSP = "APP_STATUS_CHANGE_RSP";
}
