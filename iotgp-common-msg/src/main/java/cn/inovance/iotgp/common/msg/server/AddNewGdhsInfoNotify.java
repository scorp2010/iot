/**
 * Project Name:iotgp-common-msg
 * File Name:AddNewGdhsInfoNotify.java
 * Package Name:cn.inovance.iotgp.common.msg.server
 * Date:2014-5-4下午4:13:26
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;

import cn.inovance.iotgp.common.msg.server.bean.GdhsInfo;

/**
 * ClassName:AddNewGdhsInfoNotify <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-4 下午4:13:26 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class AddNewGdhsInfoNotify extends Request {

	public AddNewGdhsInfoNotify() {
		this.msgType = MsgType.ADD_NEW_GDHS_INFO_NOTIFY;
	}

	private ArrayList<GdhsInfo> gdhsInfos = new ArrayList<GdhsInfo>();

	public void addGdhsInfo(GdhsInfo gdhsInfo) {
		gdhsInfos.add(gdhsInfo);
	}

	public ArrayList<GdhsInfo> getGdhsInfos() {
		return gdhsInfos;
	}

	public void setGdhsInfos(ArrayList<GdhsInfo> gdhsInfos) {
		this.gdhsInfos = gdhsInfos;
	}

}
