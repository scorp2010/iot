package cn.inovance.iotgp.cdSimulator.contants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.inovance.iotgp.common.enums.TransferType;

public class StaticValues {

	public static Properties pt;

	/** Session Key值 --REGCODE */
	public static String KEY_IOSESSION_REGCODE = "REGCODE";
	/** TempClient Session Key值 --GUID */
	public static String KEY_IOSESSION_TEMPGUID = "TMEPGUID";
	/** Session Key值 --REGCODE */
	public static String KEY_IOSESSION_SECURITYCODE = "SECURITYCODE";

	public static int SYS_RUN_MODE = 1;

	/** CDAG重连时间间隔 */
	public static int CDAG_ReconnectInterval = 30;
	/** CDAG心跳时间间隔 */
	public static int CDAG_HeartBeatInterval = 30;
	/** CDAG心跳超时时长 */
	public static int CDAG_HeartBeatTimeout = 30;

	/** CDSM重连时间间隔 */
	public static int CDSM_ReconnectInterval = 300;
	/** CDSM心跳时间间隔 */
	public static int CDSM_HeartBeatInterval = 30;
	/** CDSM心跳超时时长 */
	public static int CDSM_HeartBeatTimeout = 30;

	/** CDSM运行状态通知时间间隔 */
	public static int CDSM_RunStatusNotifyInterval = 5;

	/** 控制器事件上报时间间隔 */
	public static int CTRL_EVENT_INTERVAL = 1;

	/** 采集设备模拟器注册码 */
	public static String CdSimulator_RegCode = "";

	/** 本地软件名 */
	public static String CdSimulator_SoftFileName = "";
	/** 本地软件版本 */
	public static String CdSimulator_SoftVersion = "";
	/** 传输模式(0为FTP，2为GFTP) */
	public static short CdSimulator_TransferType = TransferType.FTP.value();

	/** 视频头 */
	public static String Video_Data_Header = "";
	/** 视频数据流 */
	public static String Video_Data_Stream = "";
	/** 视频数据流1 */
	public static String Video_Data_Stream1 = "";
	/** 视频数据流2 */
	public static String Video_Data_Stream2 = "";
	/** 音频头 */
	public static String Audio_Data_Header = "";
	/** 音频数据流 */
	public static String Audio_Data_Stream = "";
	/**重连次数*/
	public static int RECONNECT_COUNT;
	
	/**视频传输频率（单位毫秒）*/
	public static int VIDEO_SEND_FREQUENCY;
	
	/** 本地软件版本 */
	public static String CdSimulator_Ad_Playlist_MD5 = "";
	

	static {
		try {
			// 读取配置文件
			InputStream is = cn.inovance.iotgp.cdSimulator.main.CdSimulator.class
					.getResourceAsStream("/global.properties");
			pt = new Properties();
			pt.load(is);

			SYS_RUN_MODE = getPropertyInt("sys.run.mode");

			CDAG_ReconnectInterval = getPropertyInt("cdag.reconnectInterval");
			CDAG_HeartBeatInterval = getPropertyInt("cdag.heartBeatInterval");
			CDAG_HeartBeatTimeout = getPropertyInt("cdag.heartBeatTimeout");

			CDSM_ReconnectInterval = getPropertyInt("cdsm.reconnectInterval");
			CDSM_HeartBeatInterval = getPropertyInt("cdsm.heartBeatInterval");
			CDSM_HeartBeatTimeout = getPropertyInt("cdsm.heartBeatTimeout");

			CdSimulator_RegCode = pt.getProperty("cdSimulator.regCode");

			CdSimulator_SoftFileName = pt
					.getProperty("cdSimulator.softFileName");
			CdSimulator_SoftVersion = pt.getProperty("cdSimulator.softVersion");
			CdSimulator_TransferType = (short) getPropertyShort("cdSimulator.TransferType");

			Video_Data_Header = pt.getProperty("video.data.header");
			Video_Data_Stream = pt.getProperty("video.data.stream");
			Video_Data_Stream1 = pt.getProperty("video.data.stream1");
			Video_Data_Stream2 = pt.getProperty("video.data.stream2");
			Audio_Data_Header = pt.getProperty("audio.data.header");
			Audio_Data_Stream = pt.getProperty("audio.data.stream");
			RECONNECT_COUNT=getPropertyInt("reconnect.count");
			VIDEO_SEND_FREQUENCY=getPropertyInt("video.send.frequency");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyStr(String key) {
		return pt.getProperty(key);
	}

	public static int getPropertyInt(String key) {
		return Integer.parseInt(pt.getProperty(key));
	}
	
	public static int getPropertyShort(String key) {
		return Short.parseShort(pt.getProperty(key));
	}

	@SuppressWarnings("unused")
	private static boolean setProperty(String key, String value) {
		try {
			pt.setProperty(key, value);
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir") + "\\global.properties");
			pt.store(fos, "cdSimulator"); // 将Properties集合保存到流中
			fos.close(); // 关闭流
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
