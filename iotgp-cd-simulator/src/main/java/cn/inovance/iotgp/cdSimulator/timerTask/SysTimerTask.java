package cn.inovance.iotgp.cdSimulator.timerTask;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.inovance.iotgp.cdSimulator.client.CdSimulatorMgr;
import cn.inovance.iotgp.cdSimulator.client.CdsmClient;
import cn.inovance.iotgp.cdSimulator.contants.StaticValues;

/**
 * 系统定时器(每秒执行一次，其它的定时任务通过计数器进行调试)
 * 
 * @author c2100
 * 
 */
@Component
public class SysTimerTask extends TimerTask {

	@Autowired
	private CdSimulatorMgr cdSimulatorMgr; // 设备模拟器管理服务
	@Autowired
	private CdsmClient cdsmClient; // 设备模拟器管理服务通讯客户端

	private int cdsmHeartBeatIntervalCount = 0; // cdsm心跳计时器
	private int cdsmRunStatusNotifyCount = 0;// cdsm运行状态通知
	private int cdagHeartBeatIntervalCount = 0; // cdag心跳计时器
	private int ctrlEventIntervalCount = 0; // 控制器事件计时器

	public SysTimerTask() {
		// 压力模式时延时启动
		if (StaticValues.SYS_RUN_MODE == 2) {
			cdagHeartBeatIntervalCount = -90;
			ctrlEventIntervalCount = -10;
		}
	}

	@Override
	public void run() {
		try {
			cdsmHeartBeatIntervalCount++;
			if (cdsmHeartBeatIntervalCount >= StaticValues.CDSM_HeartBeatInterval) {
				cdsmClient.sendHeartBeat();
				cdsmHeartBeatIntervalCount = 0;
			}

			cdsmRunStatusNotifyCount++;
			if (cdsmRunStatusNotifyCount >= StaticValues.CDSM_RunStatusNotifyInterval) {
				cdsmClient.sendRunStatusNotify();
				cdsmRunStatusNotifyCount = 0;
			}

			cdagHeartBeatIntervalCount++;
			if (cdagHeartBeatIntervalCount >= StaticValues.CDAG_HeartBeatInterval) {
				cdSimulatorMgr.sendHeartBeat();
				cdagHeartBeatIntervalCount = 0;
			}

//			ctrlEventIntervalCount++;
//			if (ctrlEventIntervalCount >= StaticValues.CTRL_EVENT_INTERVAL) {
//				cdSimulatorMgr.sendCtrlEvent();
//				ctrlEventIntervalCount = 0;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
