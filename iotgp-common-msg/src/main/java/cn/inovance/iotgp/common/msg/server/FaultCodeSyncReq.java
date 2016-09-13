package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.FaultCodeInfo;

public class FaultCodeSyncReq extends Request {

	/** 同步类型（1：新增 2：删除 3：替换 ） */
	private short syncType;

	private List<FaultCodeInfo> faultCodeInfos = new ArrayList<FaultCodeInfo>();

	public FaultCodeSyncReq() {
		this.msgType = MsgType.FAULT_CODE_SYNC_REQ;
	}

	public void addFaultCode(FaultCodeInfo faultCodeInfo) {
		faultCodeInfos.add(faultCodeInfo);
	}

	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	public List<FaultCodeInfo> getFaultCodeInfos() {
		return faultCodeInfos;
	}

	public void setFaultCodeInfos(List<FaultCodeInfo> faultCodeInfos) {
		this.faultCodeInfos = faultCodeInfos;
	}

}
