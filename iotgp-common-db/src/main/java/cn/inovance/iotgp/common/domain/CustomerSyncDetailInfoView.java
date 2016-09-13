package cn.inovance.iotgp.common.domain;

public class CustomerSyncDetailInfoView {

	private String sysAccount;
	
	private CustomerSyncDetailInfo customerSyncDetailInfo;
	
	private CustomerInfo customerInfo;
	
	public CustomerSyncDetailInfoView(){
	}

	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}
	
	public CustomerSyncDetailInfo getCustomerSyncDetailInfo() {
		return customerSyncDetailInfo;
	}

	public void setCustomerSyncDetailInfo(
			CustomerSyncDetailInfo customerSyncDetailInfo) {
		this.customerSyncDetailInfo = customerSyncDetailInfo;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
}
