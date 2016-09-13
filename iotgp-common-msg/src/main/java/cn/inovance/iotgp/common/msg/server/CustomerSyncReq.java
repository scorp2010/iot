package cn.inovance.iotgp.common.msg.server;

import cn.inovance.iotgp.common.msg.server.bean.CustomerInfo;

public class CustomerSyncReq extends Request {

	/** 同步类型 */
	private short syncType;

	/** 客户信息 */
	private CustomerInfo customerInfo;

	public CustomerSyncReq() {
		this.msgType = MsgType.CUSTOMER_INFO_SYNC_REQ;
	}

	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

}
