package cn.inovance.iotgp.cdSimulator.handler.thread;

public interface IThreadHandler {
	/** 终止处理线程 */
	void shutdown(); 
	
	/** 切换处理信号 */
	void switchSignal(int signal);
}
