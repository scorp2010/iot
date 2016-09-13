package cn.inovance.iotgp.cdSimulator.client;

import java.net.InetSocketAddress;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.codec.CdCodecFactory;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.NodeClientIoHandler;
import cn.inovance.iotgp.cdSimulator.handler.thread.AudioDataHandler;
import cn.inovance.iotgp.cdSimulator.handler.thread.GftpHandler;
import cn.inovance.iotgp.cdSimulator.handler.thread.IThreadHandler;
import cn.inovance.iotgp.cdSimulator.handler.thread.RTDataHandler;
import cn.inovance.iotgp.cdSimulator.handler.thread.VideoDataHandler;
import cn.inovance.iotgp.cdSimulator.pkg.GetPlaylistReq;
import cn.inovance.iotgp.cdSimulator.pkg.PlaylistUpdateCompleteReq;
import cn.inovance.iotgp.cdSimulator.main.CdSimulator;
import cn.inovance.iotgp.cdSimulator.util.CDSessionService;
import cn.inovance.iotgp.common.domain.CdRegisterCode;
import cn.inovance.iotgp.common.enums.TargetDeviceType;
import cn.inovance.iotgp.common.msg.cd.CdHeartBeatReq;
import cn.inovance.iotgp.common.msg.cd.CtrlEventNotify;
import cn.inovance.iotgp.common.msg.cd.DataProtocolAndCommunicationStatusNotify;
import cn.inovance.iotgp.common.msg.cd.DeviceLoginReq;
import cn.inovance.iotgp.common.msg.cd.DeviceParameterReport;
import cn.inovance.iotgp.common.msg.cd.ReportSoftwareUpdateProgressNotify;
import cn.inovance.iotgp.common.msg.cd.ReportSoftwareVersionReq;
import cn.inovance.iotgp.common.msg.cd.SimulateDeviceInfo;
import cn.inovance.iotgp.common.msg.cd.meta.DataProtocolVersionAndCommunicationStatusInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.RealtimeData;
import cn.inovance.iotgp.common.msg.cd.meta.RealtimeDataPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.SoftwareVersionInfo;
import cn.inovance.iotgp.common.msg.cd.meta.SoftwareVersionInfoPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.cdSimulator.BatchHandlerFlag;
import cn.inovance.iotgp.common.msg.util.ByteOps;
import cn.inovance.iotgp.common.msg.util.DateUtil;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;
import cn.inovance.iotgp.common.util.MD5Util;
import cn.inovance.iotgp.common.util.RandomCode;

@Component
public class NodeClient {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private SocketConnector connector;
	private IoSession session;
	private IoHandler ioHandler;

	private int id;
	private String host;
	private int port;
	private boolean useLog;
	private int idleTime;
	private int connectTimeout;

	private boolean connected = false;
	private boolean logined = false;
	private CdSimulatorMgr cdSimulatorMgr;
	
	private ExecutorService executorService;

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	private CdRegisterCode cdRegisterCode;//CdSimulatorMgr 类传递而来
	private String securityCode;

	private boolean onlyLoginTest = false; // 只做登录测试
	private long startLoginTime = Calendar.getInstance().getTimeInMillis(); // 登录开始时间
	private long endLoginTime = Calendar.getInstance().getTimeInMillis(); // 登录结束时间
	
	private AtomicBoolean mediaUpdateFlag = new AtomicBoolean(false);
	
	private int count=0;

	/** 临时处理器线程Map */
	private Map<String, IThreadHandler> threadHandlerMap = new HashMap<String, IThreadHandler>();
	
	/**批量处理标志类*/
	private BatchHandlerFlag batchHandlerFlag;
	/**当前广告播放列表ID*/
	private String adplaylistid="";
	/**当前公告播放列表ID*/
	private String msgplaylistid="";
	/**当前配置播放列表ID*/
	private String confplaylistid="";
	
	private SimulateDeviceInfo simulateDevice=new SimulateDeviceInfo();

	public NodeClient() {

	}
	
	public String getAdplaylistid() {
		return adplaylistid;
	}

	public void setAdplaylistid(String adplaylistid) {
		this.adplaylistid = adplaylistid;
	}

	public String getMsgplaylistid() {
		return msgplaylistid;
	}

	public void setMsgplaylistid(String msgplaylistid) {
		this.msgplaylistid = msgplaylistid;
	}

	public String getConfplaylistid() {
		return confplaylistid;
	}

	public void setConfplaylistid(String confplaylistid) {
		this.confplaylistid = confplaylistid;
	}

	public AtomicBoolean getMediaUpdateFlag() {
		return mediaUpdateFlag;
	}

	public void setMediaUpdateFlag(AtomicBoolean mediaUpdateFlag) {
		this.mediaUpdateFlag = mediaUpdateFlag;
	}

	// 启动客户端
	public void startClient() {
		init();
		connect();
	}

	// 停止客户端
	public void stopClient() {
		logger.info("stopClient() 断开设备连接:{}", cdRegisterCode);
		connected = false;
		logined = false;
		session.setAttribute("ACTIVE_CLOSE", "true");
		session.close(true);
		for (IThreadHandler handler : threadHandlerMap.values()) {
			handler.shutdown();
		}
		threadHandlerMap.clear();
	}

	// 连接关闭后操作
	public void closed() {
		logger.info("closed() 设备连接已关闭:{}", cdRegisterCode);
		connected = false;
		logined = false;
		for (IThreadHandler handler : threadHandlerMap.values()) {
			handler.shutdown();
		}
		threadHandlerMap.clear();
	}

	/**
	 * 组件初始化
	 */
	public void init() {
		System.out.println("cpu num =="+(Runtime.getRuntime().availableProcessors()+1));
		connector = new NioSocketConnector(1);
		
		connector.setConnectTimeoutMillis(connectTimeout * 1000);
		SocketSessionConfig sessionConfig = connector.getSessionConfig();
		sessionConfig.setIdleTime(IdleStatus.READER_IDLE, idleTime * 1000);
		//sessionConfig.setTcpNoDelay(true);
		//sessionConfig.setKeepAlive(true);
		sessionConfig.setSoLinger(-1); // close()时立即关闭不等待
		//sessionConfig.setReceiveBufferSize(10240);
		//sessionConfig.setReceiveBufferSize(10240);	// 设置接收缓冲区的大小
		//sessionConfig.setSendBufferSize(10240);          // 设置输出缓冲区的大小

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		// 绑定编解码器
		chain.addLast("cdCodec", new ProtocolCodecFilter(new CdCodecFactory()));
		//ExecutorService threadPool=Executors.newCachedThreadPool();
		if (executorService != null)
			chain.addLast("threadPool", new ExecutorFilter(executorService));
		// 绑定IO事件处理器
		if (ioHandler == null) {
			logger.error("ioHandler 未设置");
			return;
		}
		NodeClientIoHandler noNodeClientIoHandler=(NodeClientIoHandler)ioHandler;
		noNodeClientIoHandler.setConnector((NioSocketConnector)connector);
		connector.setHandler(noNodeClientIoHandler);
		
	}

	/**
	 * 连接服务器，直到连接成功
	 */
	public void connect() {
		logger.info("最大内存 maxMemory==="+Runtime.getRuntime().maxMemory() / (1024 *1024));
		logger.info("已用内存 totalMemory==="+Runtime.getRuntime().totalMemory() / (1024 *1024));
		logger.info("可用内存 freeMemory==="+Runtime.getRuntime().freeMemory() / (1024 *1024));
		connected = false;
		logined = false;
		//connector.setDefaultRemoteAddress(new InetSocketAddress(host, port));// 设置默认访问地址
		ConnectFuture future = connector.connect(new InetSocketAddress(host,port));
		// 利用监听器进行重连
		count++;
		logger.info("***regcode:"+cdRegisterCode.getRegCode()+"******connector*"+connector.getTransportMetadata()+"**重连次数："+count);
		if (count++ >=30) {
			connector.dispose();
		}else {
			future.addListener(new IoFutureListener<ConnectFuture>() {
				@Override
				public void operationComplete(ConnectFuture future) {
					try {
						session = future.getSession();// 获取连接的session
						session.setAttribute(StaticValues.KEY_IOSESSION_REGCODE,
								cdRegisterCode.getRegCode()); // 缓存RegCode至Session会话中
						connected = true;
						logger.info("--node--session-address---"+session.getRemoteAddress()+"--sessionid:"+session.getId()
								+"---securityCode="+securityCode+"--conf:"+session.getConfig()+"--handler"+session.getHandler());
						if (session.isConnected())
							sendLoginRequest(); // 发送登录请求报文
						
					} catch (RuntimeIoException e) {
						logger.error("ConnectorException", e);
						e.printStackTrace();
						try {
							logger.info("连接到cdag服务器[{}:{}]失败，{}秒后重连 ...次数{}..异常{}", host,
									port, StaticValues.CDAG_ReconnectInterval,count,e.getMessage());
							Thread.sleep(StaticValues.CDAG_ReconnectInterval * 1000);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						connect(); // 重新连接
					}
				}
			});
		}
		
	}

	/**
	 * 登录成功处理
	 */
	public void processLoginSucc() {
		this.logined = true;
		this.endLoginTime = Calendar.getInstance().getTimeInMillis();
		if (onlyLoginTest)
			return;

		try {
//			Thread.sleep(5000);
//			this.sendGetPlaylistReq();
//			Thread.sleep(5000);
//			this.sendPlaylistUpdateCompleteReq();
//			Thread.sleep(5000);
//			this.sendParametersNotify(); // 发送系统参数通知消息(0x0030)
//			Thread.sleep(30000);
//			this.sendSoftwareVersionNotify(); // 发送版本通知消息(0x0020)
//			Thread.sleep(30000);
//			this.sendDataProtocolAndCommunicationStatusNotify(); // 目标设备通信状态和协议通知消息(0x0050)
//			Thread.sleep(5000);
//			while (true){
//				this.sendCtrlEvent();// 发送事件消息
//				Thread.sleep(1000);
//			}
			simulateDevice.setRegCode(this.cdRegisterCode.getRegCode());
			simulateDevice.setClientCode(cdSimulatorMgr.getClientSecurityCode());
			simulateDevice.setEndLoginTime(this.endLoginTime);
			simulateDevice.setStartLoginTime(this.startLoginTime);
			logger.info("id["+this.id+"]:["+cdRegisterCode.getRegCode()+"]*******login in success onlyLoginTest:"+onlyLoginTest+simulateDevice.toString());
			CDSessionService.getInstance().saveDeviceLoginInfos(cdSimulatorMgr.getClientSecurityCode(), this.cdRegisterCode.getRegCode(), simulateDevice);
//			Iterator<Entry<String, NodeClient>> it=CdSimulatorMgr.cdSimulatorClientList.entrySet().iterator();
//			int i=0;
//			while (it.hasNext()) {
//				i++;
//				Entry<String, NodeClient> entry =it.next();
//				logger.debug(" online regcode:{},重连count={},循环次数:{}",entry.getKey(),entry.getValue().count,i);
//				if (count>=StaticValues.RECONNECT_COUNT
//						//||true 加入redis缓存
//						) {
//					SimulateDeviceInfo simulateDevice=new SimulateDeviceInfo();
//					simulateDevice.setRegCode(entry.getKey());
//					simulateDevice.setReconnectCount(count);
//					//CDSessionService.getInstance().saveTimeoutTime(entry.getKey(),simulateDevice, 30*60);//缓存regcode至redis
//					CdSimulatorMgr.simulateDeviceInfoMap.put(entry.getKey(), simulateDevice);
//				}
//			}
			
			//CDSessionService.getInstance().saveTimeoutTime(CDSessionService.getInstance().get(StaticValues.KEY_IOSESSION_SECURITYCODE).toString(),CdSimulatorMgr.simulateDeviceInfoMap,24*60*60);//缓存至redis 时间24小时
			
//			logger.debug("*--*--*"+CDSessionService.getInstance().get("szinovance100000000000000000")+"--session id="+session.getId()
//					+"--securityCode="+CDSessionService.getInstance().get(StaticValues.KEY_IOSESSION_SECURITYCODE));
//			logger.debug(" redis session SECURITYCODE="+CDSessionService.getInstance().get(StaticValues.KEY_IOSESSION_SECURITYCODE));					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendGetPlaylistReq(){
		GetPlaylistReq req = new GetPlaylistReq();
		req.setCurrentAdPlaylistId(this.adplaylistid);
		req.setCurrentMsgPlaylistId(this.msgplaylistid);
		req.setCurrentConfigId(this.confplaylistid);
		req.setCurrentPicAdPlaylistId("222222222222222222222222222");
		req.construct();
		this.send(req.getData());
		logger.info("[{}]发送获取播放列表请求消息(0x0010):{}", cdRegisterCode.getRegCode(),
				req.toString());
	}
	
	public void sendPlaylistUpdateCompleteReq(PlaylistUpdateCompleteReq req){
//		PlaylistUpdateCompleteReq req = new PlaylistUpdateCompleteReq();
//		req.setPlaylistId("11111111111111111111111111111");
//		req.setPlaylistFileMd5("123123123");
//		req.setPlaylistFileName("123123");
//		req.setPlaylistFilePath("123131");
//		req.setUpdateStatus((short)1);
//		req.construct();
		this.send(req.getData());
		logger.info("[{}]发送获取播放列表请求消息(0x0010):{}", cdRegisterCode.getRegCode(),
				req.toString());
	}


	/**
	 * 切换服务器连接
	 */
	public void swichServer(String host, int port) {
		this.host = host;
		this.port = port;
		session.close(true);
	}

	/**
	 * 发送报文
	 */
	public boolean send(byte[] message) {
		if (connected && session != null) {
			session.write(message);
			logger.debug("发送消息：" + ByteOps.bytes2HexStringWithBlank(message));
			return true;
		}
		return false;
	}

	/**
	 * 发送登录请求消息(0x0010)
	 */
	public void sendLoginRequest() {
		DeviceLoginReq req = new DeviceLoginReq();

		// 注册码
		StringPdu deviceRegCode = new StringPdu(28, cdRegisterCode.getRegCode());
		req.setDeviceRegCode(deviceRegCode);
		// 动态随机数
		String strSecurityCode = new String(RandomCode.generateCode8())
				.substring(0, 6);
		StringPdu securityCode = new StringPdu(strSecurityCode);
		req.setSecurityCode(securityCode);
		// 注册鉴权码
		String strEncryPassword = MD5Util.generate16Md5(cdRegisterCode
				.getEncryptSn() + strSecurityCode);
		StringPdu encryPassword = new StringPdu(16, strEncryPassword);
		req.setEncryPassword(encryPassword);

		req.construct();
		this.send(req.getData());
		logger.info("[{}]发送登录请求消息(0x0010):{}", cdRegisterCode.getRegCode(),
				req.toString());

		this.startLoginTime = Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * 发送心跳通知消息(0x0012)
	 */
	public void sendHeartBeat() {
		if (!logined)
			return;
		CdHeartBeatReq reqPacket = new CdHeartBeatReq();
		reqPacket.construct();
		this.send(reqPacket.getData());
		logger.debug("[{}]发送心跳通知消息(0x0012)", cdRegisterCode.getRegCode());
	}

	/**
	 * 发送版本通知消息(0x0020)
	 */
	public void sendSoftwareVersionNotify() {
		ReportSoftwareVersionReq report = new ReportSoftwareVersionReq();
		List<SoftwareVersionInfoPdu> softwareVersionInfoList = new ArrayList<SoftwareVersionInfoPdu>();
		SoftwareVersionInfoPdu softwareVersionInfoPdu = new SoftwareVersionInfoPdu(
				new SoftwareVersionInfo(StaticValues.CdSimulator_SoftFileName,
						StaticValues.CdSimulator_SoftVersion,
						StaticValues.CdSimulator_TransferType));
		softwareVersionInfoList.add(softwareVersionInfoPdu);
		report.setSoftwareVersionInfoList(softwareVersionInfoList);
		report.construct();
		this.send(report.getData());

		logger.info("[{}]发送版本通知消息(0x0020):{}", cdRegisterCode.getRegCode(),
				report.toString());
	}

	/**
	 * 发送系统参数通知消息(0x0030)
	 */
	public void sendParametersNotify() {
		DeviceParameterReport report = new DeviceParameterReport();
		Map<String, String> paras = new HashMap<String, String>(0);
		for (int i = 0; i < 4; i++) {
			paras.put(Integer.toString(i),
					Integer.toString(new Random().nextInt(10)));
		}
		if (this.cdRegisterCode.getRegCode().startsWith("szinovance")){
			paras.put("14", "1");
			String zhujiRegCode = this.cdRegisterCode.getRegCode().replaceAll("szinovance", "szinovancf");
			paras.put("15", zhujiRegCode);
		}else if (this.cdRegisterCode.getRegCode().startsWith("szinovancf")){
			paras.put("14", "2");
		}
		report.setStrToParameters(paras);
		report.construct();
		this.send(report.getData());
		logger.info("[{}]发送系统参数通知消息(0x0030):{}", cdRegisterCode.getRegCode(),
				report.toString());
	}

	/**
	 * 上报软件版本更新进度
	 */
	public void sendSoftWareUpdateProgressNotify(StringPdu softwareName,
			StringPdu softwareVersion, short updateProgress, short updateResult) {
		ReportSoftwareUpdateProgressNotify nofity = new ReportSoftwareUpdateProgressNotify();
		nofity.setSoftwareName(softwareName);
		nofity.setSoftwareVersion(softwareVersion);
		nofity.setUpdateProgress(new ShortPdu(updateProgress));
		nofity.setUpdateResult(new ShortPdu(updateResult));
		nofity.construct();
		this.send(nofity.getData());
		logger.info("[{}]上报软件版本更新进度消息：{}", cdRegisterCode.getRegCode(),
				nofity.toString());
	}

	/**
	 * 目标设备通信状态和协议通知消息(0x0050)
	 */
	public void sendDataProtocolAndCommunicationStatusNotify() {
		DataProtocolAndCommunicationStatusNotify nofity;

		// 1个控制器
		nofity = new DataProtocolAndCommunicationStatusNotify();
		nofity.setTdTypeCode(new ShortPdu(TargetDeviceType.Ctrl.value()));
		List<DataProtocolVersionAndCommunicationStatusInfoPdu> paras = new ArrayList<DataProtocolVersionAndCommunicationStatusInfoPdu>();
		for (int i = 0; i < 1; i++) {
			paras.add(new DataProtocolVersionAndCommunicationStatusInfoPdu(
					"2+WP_TEST", "0010", i + 1, 0));
		}
		nofity.setDataProtocolVersionList(paras);
		nofity.construct();
		this.send(nofity.getData());
		logger.info("[{}]发送目标设备通信状态和协议通知消息[1个控制器]:{}",
				cdRegisterCode.getRegCode(), nofity.toString());

		// 一个摄像头
		nofity = new DataProtocolAndCommunicationStatusNotify();
		nofity.setTdTypeCode(new ShortPdu(TargetDeviceType.Camera.value()));
		paras = new ArrayList<DataProtocolVersionAndCommunicationStatusInfoPdu>();
		paras.add(new DataProtocolVersionAndCommunicationStatusInfoPdu(
				"AppCamera0", "V00B02D03", 3, 0));
		nofity.setDataProtocolVersionList(paras);
		nofity.construct();
		this.send(nofity.getData());
		logger.info("[{}]发送目标设备通信状态和协议通知消息(0x0050)[1个摄像头]:{}",
				cdRegisterCode.getRegCode(), nofity.toString());
	}

	/**
	 * 发送控制器事件消息(0x0061)
	 */
	public void sendCtrlEvent() {
		if (!logined)
			return;
		CtrlEventNotify nofity = new CtrlEventNotify();
		nofity.setDeviceRegCode(new StringPdu(28, this.cdRegisterCode
				.getRegCode()));
		nofity.setTdCode((short) 1);
		long eventSeq = System.currentTimeMillis() / 1000;
		nofity.setEventSeq(SeqGenerator.next());
		nofity.setTimeStamp(eventSeq);
		
		nofity.setEventCode((short) 6);
		nofity.setEventStatus((short) 1);
		nofity.setEventDataLength(1);
		byte[] eventData = new byte[1];
		eventData[0] = 0x62;
		nofity.setEventData(eventData);
		

		List<RealtimeDataPdu> realtimeDataList = new ArrayList<RealtimeDataPdu>();

		RealtimeData realtimeData = new RealtimeData();
		realtimeData.setTag((short) 1);
		realtimeData.setRealtimeDataLength(1);
		byte[] rtData = new byte[1];
		rtData[0] = 0x62;
		realtimeData.setRealtimeData(rtData);
		realtimeDataList.add(new RealtimeDataPdu(realtimeData));

		realtimeData.setTag((short) 2);
		realtimeData.setRealtimeDataLength(0);
		rtData = new byte[0];
		realtimeData.setRealtimeData(rtData);
		realtimeDataList.add(new RealtimeDataPdu(realtimeData));

		nofity.setRealtimeDataCount((short) realtimeDataList.size());
		nofity.setRealtimeDataList(realtimeDataList);

		nofity.construct();
		this.send(nofity.getData());

		if (onlyLoginTest)
			return;
		logger.info("[{}]发送控制器事件消息(0x0061)：{}", cdRegisterCode.getRegCode(),
				nofity.toString());
	}
	
	
	
	/**
	 * 启动发送运行数据线程
	 * xuy-添加注释如下
	 * @param host 服务端地址
	 * @param port 服务端端口号
	 * @param tdSerial 设备序列号
	 * @param interval 间隔周期（秒）
	 */
	public void startRTData(String host, int port, String securityCode,
			short tdSerial, int interval) {
		logger.info("[{}]启动发送运行数据线程", cdRegisterCode.getRegCode());
		String guid = String.valueOf(tdSerial);
		RTDataHandler handler = new RTDataHandler(host, port,
				cdRegisterCode.getRegCode(), guid.toString(), interval,
				securityCode, tdSerial);
		//xuy - 利用线程池
		//handler.start();
		CdSimulator.taskExecutor.execute(handler);
		this.threadHandlerMap.put(guid, handler);
	}

	/**
	 * 关闭发送运行数据线程
	 */
	public void shutRTData(String key) {
		RTDataHandler handler = (RTDataHandler) this.threadHandlerMap.get(key);
		if (handler != null) {
			handler.shutdown();
			handler.interrupt();
			handler = null;
		}
		this.threadHandlerMap.remove(key);
	}

	/**
	 * 启动发送音频数据线程
	 */
	public void startAudio(String host, int port, String securityCode,
			short channel, int interval) {
		logger.debug("[{}]启动发送音频数据线程", cdRegisterCode.getRegCode());
		String guid = securityCode;
		AudioDataHandler handler = new AudioDataHandler(host, port,
				cdRegisterCode.getRegCode(), guid, interval, securityCode,
				channel);
		handler.start();
		this.threadHandlerMap.put(guid, handler);
	}

	/**
	 * 关闭发送音频数据线程
	 */
	public void shutAudio(String key) {
		AudioDataHandler handler = (AudioDataHandler) this.threadHandlerMap
				.get(key);
		if (handler != null) {
			handler.shutdown();
			handler.interrupt();
			handler = null;
		}
		this.threadHandlerMap.remove(key);
	}

	/**
	 * 启动发送视频数据线程
	 */
	public void startVideo(String host, int port, String securityCode,
			short cameraSerial, int interval) {
		logger.debug("[{}]启动发送视频数据线程", cdRegisterCode.getRegCode());
		String guid = securityCode;
		VideoDataHandler handler = new VideoDataHandler(host, port,
				cdRegisterCode.getRegCode(), guid, interval, securityCode,
				cameraSerial);
		handler.start();
		this.threadHandlerMap.put(guid, handler);
	}

	/**
	 * 关闭发送视频数据线程
	 */
	public void shutVideo(String key) {
		VideoDataHandler handler = (VideoDataHandler) this.threadHandlerMap
				.get(key);
		if (handler != null) {
			handler.shutdown();
			handler.interrupt();
			handler = null;
		}
		this.threadHandlerMap.remove(key);
	}

	/**
	 * 启动GFTP线程
	 */
	public void startGFTP(String host, int port, String userAccount,
			String userPassword, short transferType, String filePath,
			String fileName, String localFileName) {
		logger.debug("[{}]启动GFTP文件传输线程", cdRegisterCode.getRegCode());
		String guid = java.util.UUID.randomUUID().toString();
		GftpHandler handler = new GftpHandler(host, port,
				cdRegisterCode.getRegCode(), guid, userAccount, userPassword,
				transferType, filePath, fileName, localFileName);
		handler.start();
		this.threadHandlerMap.put(guid, handler);
	}

	/**
	 * 关闭GFTP线程
	 */
	public void shutGFTP(String key) {
		GftpHandler handler = (GftpHandler) this.threadHandlerMap.get(key);
		if (handler != null) {
			handler.shutdown();
			handler.interrupt();
			handler = null;
		}
		this.threadHandlerMap.remove(key);
	}
	


	@Override
	public NodeClient clone() {
		NodeClient nodeClient = new NodeClient();
		nodeClient.setCdRegisterCode(cdRegisterCode);
		nodeClient.setIoHandler(ioHandler);
		return nodeClient;
	}

	public IoHandler getIoHandler() {
		return ioHandler;
	}

	public void setIoHandler(IoHandler ioHandler) {
		this.ioHandler = ioHandler;
	}

	public SocketConnector getConnector() {
		return connector;
	}

	public void setConnector(SocketConnector connector) {
		this.connector = connector;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isUseLog() {
		return useLog;
	}

	public void setUseLog(boolean useLog) {
		this.useLog = useLog;
	}

	public int getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public Map<String, IThreadHandler> getThreadHandlerMap() {
		return threadHandlerMap;
	}

	public void setThreadHandlerMap(Map<String, IThreadHandler> threadHandlerMap) {
		this.threadHandlerMap = threadHandlerMap;
	}

	public CdRegisterCode getCdRegisterCode() {
		return cdRegisterCode;
	}

	public void setCdRegisterCode(CdRegisterCode cdRegisterCode) {
		this.cdRegisterCode = cdRegisterCode;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public IThreadHandler getThreadHandler(String key) {
		return threadHandlerMap.get(key);
	}

	public void removeThreadHandler(String key) {
		if (threadHandlerMap.containsKey(key))
			threadHandlerMap.remove(key);
	}

	public boolean isOnlyLoginTest() {
		return onlyLoginTest;
	}

	public void setOnlyLoginTest(boolean onlyLoginTest) {
		this.onlyLoginTest = onlyLoginTest;
	}

	public long getStartLoginTime() {
		return startLoginTime;
	}

	public void setStartLoginTime(long startLoginTime) {
		this.startLoginTime = startLoginTime;
	}

	public long getEndLoginTime() {
		return endLoginTime;
	}

	public void setEndLoginTime(long endLoginTime) {
		this.endLoginTime = endLoginTime;
	}


	public BatchHandlerFlag getBatchHandlerFlag() {
		return batchHandlerFlag;
	}

	public void setBatchHandlerFlag(BatchHandlerFlag batchHandlerFlag) {
		this.batchHandlerFlag = batchHandlerFlag;
	}

	public CdSimulatorMgr getCdSimulatorMgr() {
		return cdSimulatorMgr;
	}

	public void setCdSimulatorMgr(CdSimulatorMgr cdSimulatorMgr) {
		this.cdSimulatorMgr = cdSimulatorMgr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
