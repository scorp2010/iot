package cn.inovance.iotgp.cdsm.session;

import java.util.concurrent.ConcurrentHashMap;

import cn.inovance.iotgp.common.msg.cdSimulator.bean.LoginTestResult;

public class TestResultSession {

	public static ConcurrentHashMap<String, LoginTestResult> loginTestResultList = new ConcurrentHashMap<String, LoginTestResult>(); // 客户端在线列表

}
