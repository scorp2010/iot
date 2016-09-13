package cn.inovance.iotgp.common.msg.cd;

import java.io.Serializable;

/**   
 * @Title: SimulateDeviceInfo.java 
 * @Package cn.inovance.iotgp.common.msg.cd 
 * @Description: 
 * @author xy2126 
 * @date 2015-12-9 上午10:29:48 
 * @version V1.0   
 */
public class SimulateDeviceInfo implements Serializable {
	

	private static final long serialVersionUID = 2954696536273624583L;

	private String regCode;//设备注册码
	
	private int reconnectCount;//重连次数

	private String clientCode;
	
	private long startLoginTime;
	
	private long endLoginTime;

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	public int getReconnectCount() {
		return reconnectCount;
	}

	public void setReconnectCount(int reconnectCount) {
		this.reconnectCount = reconnectCount;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
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

	
}
