package cn.inovance.iotgp.common.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_message_tb", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Message extends BaseEntity implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公司id
	 */
	private String companyId;
	/**
	 * 发送短信的时间
	 */
	private Date createTime;
	/**
	 * 发送短信的号码
	 */
	private String phone;
	/**
	 * 调用发送短信的系统
	 */
	private String appSystem;
	
	@Column(name = "company_id", length = 50)
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "phone", length = 20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "app_system")
	public String getAppSystem() {
		if(appSystem == null){
			return "0";
		}
		return appSystem;
	}
	public void setAppSystem(String appSystem) {
		this.appSystem = appSystem;
	}	
}
