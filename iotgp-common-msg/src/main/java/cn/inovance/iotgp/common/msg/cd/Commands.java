package cn.inovance.iotgp.common.msg.cd;

/**
 * ClassName: Commands <br/>
 * Function: 采集设备通信消息ID定义. <br/>
 * date: 2014-4-8 下午4:52:51 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class Commands {
	/** 采集设备登陆请求消息 */
	public static final int LOGIN_REQ = 0X0010;
	/** 采集设备登陆响应消息 */
	public static final int LOGIN_RSP = 0X0011;
	/** 采集设备心跳请求消息 */
	public static final int CD_HEARTBEAT_REQ = 0X0012;
	/** 采集设备心跳响应消息 */
	public static final int CD_HEARTBEAT_RSP = 0X0013;
	/** 服务器IP/Domain更新通知消息  */
	public static final int SERVER_IP_DOMIAN_UPDATE_NOTIFY = 0X0014;
	
	/** 版本通知消息 */
	public static final int CD_REPORT_SOFTWARE_VERSION = 0x0020;
	/** 软件更新通知消息 */
	public static final int CD_UPDATE_SOFTWARE_NOTIFY = 0x0021;
	/** 软件更新进度通知消息 */
	public static final int CD_SOFTWARE_UPDATE_PROGRESS_NOTIFY = 0x0022;
	
	/** 文件传输透传上行消息 */
	public static final int TRNSPARENT_CD_TO_GFTP = 0x0023;
	/** 文件传输透传下行消息 */
	public static final int TRNSPARENT_GFTP_TO_CD = 0x0024;
	
	/** 播放内容更新查询请求消息 */
	public static final int TRNSPARENT_GET_PLAYLIST_REQ = 0x0025;
	/** 播放内容更新查询响应消息 */
	public static final int TRNSPARENT_GET_PLAYLIST_RSP = 0x0026;
	/** 播放内容更新完成请求消息 */
	public static final int TRNSPARENT_PLAYLIST_UPDATED_REQ = 0x0027;
	/** 播放内容更新完成响应消息 */
	public static final int TRNSPARENT_PLAYLIST_UPDATED_RSP = 0x0028;
	/** 新播放内容更新通知消息 */
	public static final int TRNSPARENT_NEW_PLAYLIST_NOTIFY = 0x0029;
	
	/** 采集设备参数通知消息 */
	public static final int CD_PARAMETER_NOTIFY = 0x0030;
	/** 采集设备参数更新通知消息 */
	public static final int CD_PARAMETER_UPDATE_NOTIFY = 0x0031;
	/** 采集设备参数更新响应消息 */
	public static final int CD_PARAMETER_UPDATE_BACK = 0x0032;

	/** 采集设备运行状态请求消息 */
	public static final int CD_RUN_STATUS_REQ = 0x0033;
	/** 采集设备运行状态响应消息 */
	public static final int CD_RUN_STATUS_RSP = 0x0034;
	/** 采集设备命令行通道请求消息 */
	public static final int CD_CMD_CHANEL_REQ = 0x0040;
	/** 采集设备命令行通道响应消息 */
	public static final int CD_CMD_CHANEL_RSP = 0x0041;
	
	/** 目标设备通信状态及数据协议通知消息 */
	public static final int TD_DATA_PROTOCOL_AND_COMMUNICATION_STATUS_NOTIFY = 0x0050;
	/** 目标设备指令透传请求消息 */
	public static final int TD_CMD_TRANSFER_REQ = 0x0051;
	/** 目标设备指令透传响应消息 */
	public static final int TD_CMD_TRANSFER_RSP = 0x0052;
	
	/** 告警消息 */
	public static final int CTRL_RUN_STATUS_NOTIFY = 0x0060;
	/** 事件消息 */
	public static final int CTRL_EVENT_NOTIFY = 0x0061;
	/** 事件确认消息 */
	public static final int CTRL_EVENT_RSP = 0x0062;
	/** 运行数据连接请求. */
	public static final int START_TD_DATA_REQ = 0x0064;
	/** 运行数据消息. */
	public static final int REPORT_TD_DATA_REQ = 0x0065;
	/** 运行数据停止上报请求. */
	public static final int STOP_TD_DATA_REQ = 0x0066;
	
	/** 视频连接请求消息 */
	public static final int VIDEO_ESTABLISH_REQ = 0x0070;
	/** 视频头请求消息 */
	public static final int VIDEO_HEADER_REQ = 0x0071;
	/** 视频流消息 */
	public static final int VIDEO_STREAM_DATA = 0X0072;
	/** 视频播放停止通知消息 */
	public static final int VIDEO_STOP_REQ = 0X0073;
	/** 视频播放进度定位通知消息 */
	public static final int SET_VIDEO_PLAY_PROGRESS_REQ = 0X0074;
	/** 视频头响应消息 */
	public static final int VIDEO_HEADER_RSP = 0x0075;
	
	/** 数据心跳请求消息 */
	public static final int DATA_CONN_HEARTBEAT_REQ = 0x0076;
	/** 数据心跳响应消息 */
	public static final int DATA_CONN_HEARTBEAT_RSP = 0x0077;
	
	/**摄像头抓拍请求消息*/
	public static final int PHOTO_CAPTURE_REQ = 0x0078;
	/**摄像头抓拍响应*/
	public static final int PHOTO_CAPTURE_RSP = 0x0079;
	
	/** 音频连接请求消息 */
	public static final int AUDIO_ESTABLISH_REQ = 0x0080;
	/** 音频头请求消息 */
	public static final int AUDIO_HEADER_REQ = 0x0081;
	/** 音频头响应消息 */
	public static final int AUDIO_HEADER_RSP = 0x0082;
	/** 音频流消息 */
	public static final int AUDIO_STREAM_DATA = 0X0083;
	/** 语音对讲停止通知消息 */
	public static final int AUDIO_STOP_REQ = 0X0084;
	
	/** 资源文件列表请求消息 */
	public static final int FILE_LIST_REQ = 0x0090;
	/** 资源文件列表响应消息 */
	public static final int FILE_LIST_RSP = 0x0091;
	/** 资源文件上/下载通知消息 */
	public static final int FILE_TRANSFER_NOTIFY = 0x0092;
	/** 资源文件上/下载进度通知消息 */
	public static final int FILE_TRANSFER_PROGRESS_NOTIFY = 0x0093;
	
	/**过程文件开始上传请求消息*/
	public static final int PROCEDURE_FILE_START_UPLOAD_REQ = 0x0094;
	/**过程文件开始上传响应消息*/
	public static final int PROCEDURE_FILE_START_UPLOAD_RSP = 0x0095;
	/**过程文件停止上传请求消息*/
	public static final int PROCEDURE_FILE_STOP_UPLOAD_REQ = 0x0096;
	/**过程文件停止上传请求响应消息*/
	public static final int PROCEDURE_FILE_STOP_UPLOAD_RSP = 0x0097;
	/**过程文件查询请求消息*/
	public static final int QUERY_PROCEDURE_FILE_REQ = 0x0098;
	/**过程文件查询响应消息*/
	public static final int QUERY_PROCEDURE_FILE_RSP = 0x0099;
	/**过程文件上传完成请求消息*/
	public static final int PROCEDURE_FILE_UPLOAD_OK_REQ = 0x009A;
	/**过程文件上传完成请求响应消息*/
	public static final int PROCEDURE_FILE_UPLOAD_OK_RSP = 0x009B;
	
	/** 客户端视频连接请求消息 */
	public static final int CLIENT_VIDEO_ESTABLISH_REQ = 0x1070;
	/** 客户端音频连接请求消息 */
	public static final int CLIENT_AUDIO_ESTABLISH_REQ = 0x1080;
	/** 客户端音频连接响应消息 */
	public static final int CLIENT_AUDIO_ESTABLISH_RSP = 0x1081;
	/** 客户端心跳请求消息 */
	public static final int CLIENT_HEARTBEAT_REQ = 0x1090;
}
