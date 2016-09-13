package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.TdCtrlCmdInfo;

public class TdCtrlCmdSyncReq extends Request {

	/** 同步类型（1：新增 2：删除 3：替换 ） */
	private short syncType;

	private List<TdCtrlCmdInfo> tdCmdInfos = new ArrayList<TdCtrlCmdInfo>();

	public TdCtrlCmdSyncReq() {
		this.msgType = MsgType.TD_CTRL_CMD_SYNC_REQ;
	}

	public void addTdCmdInfo(TdCtrlCmdInfo tdCmdInfo) {
		tdCmdInfos.add(tdCmdInfo);
	}

	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	public List<TdCtrlCmdInfo> getTdCmdInfos() {
		return tdCmdInfos;
	}

	public void setTdCmdInfos(List<TdCtrlCmdInfo> tdCmdInfos) {
		this.tdCmdInfos = tdCmdInfos;
	}

}
