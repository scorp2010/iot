package cn.inovance.iotgp.cdSimulator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.TempClientIoHandler;
import cn.inovance.iotgp.cdSimulator.timerTask.SysTimerTask;
import cn.inovance.iotgp.cdSimulator.util.CDSessionService;
import cn.inovance.iotgp.common.redis.SessionService;

public class CdSimulator {

	private static Timer timer;
	public static CdSimulatorMgr cdSimulatorMgr;
	public static TempClientIoHandler tempClientIoHandler;
	
	public static ThreadPoolTaskExecutor taskExecutor;
	/*** 创建全局的唯一redisTemplate实例. */
	public static RedisTemplate redisTemplate;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		System.out.println("\t\t======================================");
		System.out.println("\t\t============== IOTGP ===============");
		System.out.println("\t\t======== 采集设备模拟器 v1.0 ========");
		System.out.println("\t\t======================================");

		Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
					Runtime.getRuntime().exec("cmd");
				} catch (IOException e) {
					e.printStackTrace();
				}
            }    
        });
		
		// 加载配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-context.xml");

		cdSimulatorMgr = (CdSimulatorMgr) ctx.getBean("cdSimulatorMgr");
		tempClientIoHandler = (TempClientIoHandler) ctx.getBean("tempClientIoHandler");
		SysTimerTask sysTimerTask = (SysTimerTask) ctx.getBean("sysTimerTask");
		taskExecutor=(ThreadPoolTaskExecutor) ctx.getBean("taskExecutor");
		redisTemplate=(RedisTemplate) ctx.getBean("redisTemplate");
		
//		boolean dds=CDSessionService.getInstance().sessionExists("ssd");
//		System.out.println(dds);
		

		/*
		 * int mode = Integer
		 * .parseInt(readDataFromConsole("请选择运行模式 (1-单台   2-多台)："));
		 */
		cdSimulatorMgr.init();
		if (StaticValues.SYS_RUN_MODE == 1) {
			System.out.println("当前运行模式为【单台模式】");
			// 启动模拟客户端管理器
			cdSimulatorMgr.start(StaticValues.CdSimulator_RegCode);
		} else {
			System.out.println("当前运行模式为【多台模式】");

			CdsmClient cdsmClient = (CdsmClient) ctx.getBean("cdsmClient");
			cdsmClient.startClient();
			cdSimulatorMgr.setClientSecurityCode(cdsmClient.getSecurityCode());
		}

		timer = new Timer();
		timer.schedule(sysTimerTask, 3 * 1000, 1000);
	}

	// 从控制台获取输入
	@SuppressWarnings("unused")
	private static String readDataFromConsole(String prompt) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			System.out.print(prompt);
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;

		/*
		 * Console console = System.console(); if (console == null) { throw new
		 * IllegalStateException("Console is not available!"); } return
		 * console.readLine(prompt);
		 */
	}

}
