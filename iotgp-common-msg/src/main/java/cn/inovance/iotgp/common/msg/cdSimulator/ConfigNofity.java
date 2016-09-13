package cn.inovance.iotgp.common.msg.cdSimulator;

import com.alibaba.fastjson.JSON;

public class ConfigNofity extends Request {

	public ConfigNofity() {
		this.msgType = MsgType.CONFIG_NOTIFY;
	}

	private String selectSql = ""; // 查询设备的Sql
	private int start = 0; // 记录起始索引
	private int count = 0; // 结束记录索引
	private boolean onlyTestLogin = false; // 只做登录测试
	private BatchHandlerFlag batchHandlerFlag;//批量处理标志类
	

	public String getSelectSql() {
		return selectSql;
	}

	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isOnlyTestLogin() {
		return onlyTestLogin;
	}

	public void setOnlyTestLogin(boolean onlyTestLogin) {
		this.onlyTestLogin = onlyTestLogin;
	}

	public BatchHandlerFlag getBatchHandlerFlag() {
		return batchHandlerFlag;
	}

	public void setBatchHandlerFlag(BatchHandlerFlag batchHandlerFlag) {
		this.batchHandlerFlag = batchHandlerFlag;
	}
	
	public String toJsonString(ConfigNofity notify ){
		return JSON.toJSONString(notify);
	}

}
