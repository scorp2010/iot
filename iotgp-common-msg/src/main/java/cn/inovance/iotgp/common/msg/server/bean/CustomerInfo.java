package cn.inovance.iotgp.common.msg.server.bean;

public class CustomerInfo {

	/** 用户名(登录账号)*/
	private String loginAccount;
	/** 用户密码 */
	private String loginPassword;
	/** 用户(登录)名称*/
	private String loginName;
	/** usbKey */
	private String usbKey;
	/** 状态（启用、停用） */
	private String loginStatus;
	/** 单位名称 */
	private String customerName;
	/** 单位代码 */
	private String customerCode;
	/** 单位地址 */
	private String customerAddress;
	/** 单位电话 */
	private String customerPhone;
	private String customerFax;
	/** 单位域名 */
	private String customerDomain;
	/** 单位网站站点名称  */
	private String customerSiteName;
	/** 单位logo */ 
	private  String  customerLogo;
	/** 单位行业 */
	private String customerIndustry;
	/** 联系人姓名*/
	private String linkMan;
	/**联系人邮箱*/
	private String linkManEmail;
	/**联系人电话*/
	private String linkManPhone;
	/** 客户定制的界面信息（预留） */
	private CustomizedInfo customizedInfo;
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsbKey() {
		return usbKey;
	}
	public void setUsbKey(String usbKey) {
		this.usbKey = usbKey;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerFax() {
		return customerFax;
	}
	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}
	public String getCustomerDomain() {
		return customerDomain;
	}
	public void setCustomerDomain(String customerDomain) {
		this.customerDomain = customerDomain;
	}
	public String getCustomerSiteName() {
		return customerSiteName;
	}
	public void setCustomerSiteName(String customerSiteName) {
		this.customerSiteName = customerSiteName;
	}
	public String getCustomerLogo() {
		return customerLogo;
	}
	public void setCustomerLogo(String customerLogo) {
		this.customerLogo = customerLogo;
	}
	public String getCustomerIndustry() {
		return customerIndustry;
	}
	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkManEmail() {
		return linkManEmail;
	}
	public void setLinkManEmail(String linkManEmail) {
		this.linkManEmail = linkManEmail;
	}
	public String getLinkManPhone() {
		return linkManPhone;
	}
	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	public CustomizedInfo getCustomizedInfo() {
		return customizedInfo;
	}
	public void setCustomizedInfo(CustomizedInfo customizedInfo) {
		this.customizedInfo = customizedInfo;
	}
	@Override
	public String toString() {
		return "CustomerInfo [loginAccount=" + loginAccount
				+ ", loginPassword=" + loginPassword + ", loginName="
				+ loginName + ", usbKey=" + usbKey + ", loginStatus="
				+ loginStatus + ", customerName=" + customerName
				+ ", customerCode=" + customerCode + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone
				+ ", customerFax=" + customerFax + ", customerDomain="
				+ customerDomain + ", customerSiteName=" + customerSiteName
				+ ", customerLogo=" + customerLogo + ", customerIndustry="
				+ customerIndustry + ", linkMan=" + linkMan + ", linkManEmail="
				+ linkManEmail + ", linkManPhone=" + linkManPhone
				+ ", customizedInfo=" + customizedInfo + "]";
	}
}
