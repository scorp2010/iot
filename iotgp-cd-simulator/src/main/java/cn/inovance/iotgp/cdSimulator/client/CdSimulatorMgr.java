package cn.inovance.iotgp.cdSimulator.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.service.IoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.dao.CdSimulatorDao;
import cn.inovance.iotgp.cdSimulator.util.CDSessionService;
import cn.inovance.iotgp.common.domain.CdRegisterCode;
import cn.inovance.iotgp.common.msg.cd.SimulateDeviceInfo;
import cn.inovance.iotgp.common.msg.cdSimulator.BatchHandlerFlag;
import cn.inovance.iotgp.common.msg.cdSimulator.bean.LoginTestResult;

@Component
public class CdSimulatorMgr {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static NodeClient globalNodeClient;
	private IoHandler ioHandler;
	private String host;
	private int port;
	private boolean useLog;
	private int idleTime;
	/** 默认工作线程数目,如果共享的话共同使用该线程池 **/
	private int executorNum;
	private int connectTimeout;
	@Autowired
	private CdSimulatorDao cdSimulatorDao;
	
	private String clientSecurityCode;
	
	private ExecutorService executorService;

	/** 连接列表 */
	public static ConcurrentHashMap<String, NodeClient> cdSimulatorClientList = new ConcurrentHashMap<String, NodeClient>();
	/** 设备分配数量 */
	public static int distribuCount = 0;
	/**模拟设备统计信息Map*/
	public static ConcurrentHashMap<String, SimulateDeviceInfo> simulateDeviceInfoMap=new ConcurrentHashMap<String, SimulateDeviceInfo>();

	public CdSimulatorMgr() {
		
	}

	public void init(){
		executorService = Executors.newFixedThreadPool(executorNum);
	}
	// 启动(单台)
	public void start(String regCode) {
		if (StringUtils.isNotBlank(regCode)) {
			String selectCdSql = String
					.format("select distinct t from CdRegisterCode t where t.regCode = '%s'",
							regCode);
			List<CdRegisterCode> cdRegisterCodeList = cdSimulatorDao
					.getSuperBaseDao().find(selectCdSql);
			this.start(cdRegisterCodeList, false,null);
		} else {
			logger.warn("采集设备注册码不能为空");
		}
	}

	// 启动(多台)
	/**
	 * @param batchHandlerFlag 批量处理标志类 xuy
	 * */
	public void start(String selectSql, int start, int count,
			boolean onlyTestLogin,BatchHandlerFlag batchHandlerFlag) {
		distribuCount = count;
		List<CdRegisterCode> cdRegisterCodeList = cdSimulatorDao.find(
				selectSql, start, count);
		this.start(cdRegisterCodeList, onlyTestLogin,batchHandlerFlag);
	}

	// 启动
	public void start(List<CdRegisterCode> cdRegisterCodeList,
			boolean onlyTestLogin,BatchHandlerFlag batchHandlerFlag) {
		try {
			cdSimulatorClientList.clear(); // 启动前先清空
			CDSessionService.getInstance().updateExpiryDate(clientSecurityCode, 24*60*60);
			for (int i = 0; i < cdRegisterCodeList.size(); i++) {
				CdRegisterCode cdRegisterCode = cdRegisterCodeList.get(i);
				if (StringUtils.isNoneBlank(cdRegisterCode.getRegCode())
						&& StringUtils.isNoneBlank(cdRegisterCode
								.getEncryptSn())) {
					if (cdSimulatorClientList.get(cdRegisterCode.getRegCode()) != null) {
						logger.warn("重复的连接{}创建请求，不做处理",
								cdRegisterCode.getRegCode());
						return;
					}

					logger.info("载入第【{}】个CdSimulator:{}...模拟总数:{}", i + 1,
							cdRegisterCode.getRegCode(),cdRegisterCodeList.size());

					NodeClient nodeClient = new NodeClient();
					nodeClient.setId(i+1);
					nodeClient.setCdRegisterCode(cdRegisterCode);
					nodeClient.setIoHandler(ioHandler);
					nodeClient.setHost(host);
					nodeClient.setPort(port);
					nodeClient.setIdleTime(idleTime);
					nodeClient.setUseLog(useLog);
					nodeClient.setConnectTimeout(connectTimeout);
					nodeClient.setOnlyLoginTest(onlyTestLogin);
					nodeClient.setExecutorService(executorService);
					nodeClient.setCdSimulatorMgr(this);
					if (batchHandlerFlag!=null) {
						nodeClient.setBatchHandlerFlag(batchHandlerFlag);
					}
					cdSimulatorClientList.put(cdRegisterCode.getRegCode(),
							nodeClient); // 增加到连接列表
					nodeClient.startClient();
					globalNodeClient=nodeClient;
					if (i % 50 == 0)
						Thread.sleep(100); // 每登录一定设备sleep一下
				} else {
					logger.warn("设备regCode或encryptSn为空");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("111启动CdSimulator客户端发生错误");
		}
	}

	// 停止
	public void stop() {
		for (NodeClient nodeClient : cdSimulatorClientList.values()) {
			if (nodeClient != null) {
				nodeClient.stopClient();
			}
		}
		cdSimulatorClientList.clear();
	}

	// 获取模拟设备登录测试结果
	public static List<LoginTestResult> getLoginTestResults() {
		List<LoginTestResult> loginTestResults = new ArrayList<LoginTestResult>();
		for (NodeClient nc : cdSimulatorClientList.values()) {
			if (nc != null) {
				LoginTestResult loginTestResult = new LoginTestResult();
				loginTestResult.setRegCode(nc.getCdRegisterCode().getRegCode());
				//loginTestResult.setLogined(nc.isLogined());
				//loginTestResult.setStartLonginTime(nc.getStartLoginTime());
				//loginTestResult.setEndLoginTime(nc.getEndLoginTime());
				loginTestResults.add(loginTestResult);
			}
		}
		return loginTestResults;
	}

	// 发送消息
	public void send(String regCode, byte[] message) {
		NodeClient nodeClient = cdSimulatorClientList.get(regCode);
		if (nodeClient != null && nodeClient.isConnected()) {
			nodeClient.send(message);
		} else {
			logger.info("未找到NodeClient{}连接会话,发送消息失败:", regCode);
		}
	}

	// 发送心跳消息
	public void sendHeartBeat() {
		int i = 0;
		for (NodeClient nodeClient : cdSimulatorClientList.values()) {
			try {
				if (nodeClient != null && nodeClient.isLogined()) {
					nodeClient.sendHeartBeat();
					i++;
					if (i % 100 == 0)
						Thread.sleep(100); // 每发送100条心跳休眠一下
				} else {
					nodeClient.sendLoginRequest(); // 发送登录请求
					logger.debug("不在线设备{}不发送心跳消息，发送登录请求", nodeClient
							.getCdRegisterCode().getRegCode());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
			}
		}
		logger.debug("连接设备数：{},发送心跳数:{}", cdSimulatorClientList.size(), i);
	}

	// 发送事件消息
	public void sendCtrlEvent() {
		int i = 0;
		for (NodeClient nodeClient : cdSimulatorClientList.values()) {
			try {
				if (nodeClient != null && nodeClient.isLogined()) {
					nodeClient.sendCtrlEvent();
					i++;
					Thread.sleep(20);
				} else {
					logger.debug("不在线设备{}不发送事件消息:", nodeClient
							.getCdRegisterCode().getRegCode());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
			}
		}
		logger.info("连接设备数：{},发送事件数:{}", cdSimulatorClientList.size(), i);
	}

	public IoHandler getIoHandler() {
		return ioHandler;
	}

	public void setIoHandler(IoHandler ioHandler) {
		this.ioHandler = ioHandler;
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

	public CdSimulatorDao getCdSimulatorDao() {
		return cdSimulatorDao;
	}

	public void setCdSimulatorDao(CdSimulatorDao cdSimulatorDao) {
		this.cdSimulatorDao = cdSimulatorDao;
	}

	public int getOnlineCount() {
		int onlineCount = 0;
		for (NodeClient nodeClient : cdSimulatorClientList.values()) {
			if (nodeClient != null) {
				if (nodeClient.isLogined())
					onlineCount++;
			}
		}
		return onlineCount;
	}

	public int getExecutorNum() {
		return executorNum;
	}

	public void setExecutorNum(int executorNum) {
		this.executorNum = executorNum;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getClientSecurityCode() {
		return clientSecurityCode;
	}

	public void setClientSecurityCode(String clientSecurityCode) {
		this.clientSecurityCode = clientSecurityCode;
	}
	
}
