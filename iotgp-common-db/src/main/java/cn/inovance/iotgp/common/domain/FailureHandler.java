/***********************************************************************
 * Module:  FailureHandler.java
 * Author:  w1898
 * Purpose: Defines the Class FailureHandler
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 采集设备故障处理方式
 * 
 * @pdOid 50a64d0a-59c3-4799-a662-abd741d7f4ef
 */
@Entity(name = "FailureHandler")
@Table(name = "t_mms_failure_handler")
public class FailureHandler implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid da4003d3-8c6d-45f0-be6c-a0b0dca40076
	 */
	private java.lang.String id;
	/**
	 * 处理人编号
	 * 
	 * @pdOid 826cbaf1-eb3a-4cb2-b589-39e4650f4a2e
	 */
	private java.lang.String handlerAccount;

	/**
	 * 处理人电话
	 * 
	 * @pdOid c35ee49d-3c30-4f8b-b8dc-802ac57edeea
	 */
	private java.lang.String handlerPhone;
	/**
	 * 处理人邮箱
	 * 
	 * @pdOid 97cb50c6-ec61-4c90-8b90-205891b59303
	 */
	private java.lang.String handlerEmail;
	/**
	 * 处理人微信
	 * 
	 * @pdOid 6ba37afd-abaf-4aef-8ea8-cc9b8f9771e7
	 */
	private java.lang.String handlerWechat;
	/**
	 * 短消息发送状态 =
	 * 
	 * @pdOid 649b0f60-7a45-4510-9937-2b8cfc9aef40
	 */
	private java.lang.String msgSendFlag;
	/**
	 * 短消息发送状态名称
	 * 
	 * @pdOid 649b0f60-7a45-4510-9937-2b8cfc9aef40
	 */
	private java.lang.String msgSendFlagName;

	/**
	 * 邮件发送状态
	 * 
	 * @pdOid 23f78426-c8a1-4de7-ad67-c7ce2ef6a274
	 */
	private java.lang.String emailSendFlag;
	/**
	 * 邮件发送状态名称
	 * 
	 * @pdOid 23f78426-c8a1-4de7-ad67-c7ce2ef6a274
	 */
	private java.lang.String emailSendFlagName;

	/**
	 * 微信发送状态
	 * 
	 * @pdOid d73defee-3951-4912-beb2-45a20d6a89fa
	 */
	private java.lang.String wechatSendFlag;

	/**
	 * 微信发送状态名称
	 * 
	 * @pdOid 23f78426-c8a1-4de7-ad67-c7ce2ef6a274
	 * 
	 */
	private java.lang.String wechatSendFlagName;
	/**
	 * 短信发送时间间隔
	 * 
	 * @pdOid 77962ef5-2575-4f24-a092-ac0b3019d262
	 */
	private java.lang.Integer msgSendInterval;
	/**
	 * 邮件发送时间间隔
	 * 
	 * @pdOid fcd66e20-231f-414e-8bcd-9cacb51eb6da
	 */
	private java.lang.Integer emailSendInterval;
	/**
	 * 微信发送时间间隔
	 * 
	 * @pdOid fa2d12e3-fbe4-4e79-803a-d84731e3c052
	 */
	private java.lang.Integer wechatSendInterval;
	/**
	 * 创建时间
	 * 
	 * @pdOid 46b0527a-963e-4464-b0d3-04b78089ab97
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 * 
	 * @pdOid 903c9ce9-53c5-4e32-91d1-16e8c4bec0db
	 */
	private java.util.Date updateTime;

	/**
	 * @pdRoleInfo migr=no name=CdFailureInfo assc=cdFailureInfoFailureHandler
	 *             mult=0..1 side=A
	 */
	public CdFailureInfo cdFailureInfo;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	@Basic(optional = true)
	@Column(name = "handler_account", nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getHandlerAccount() {
		return handlerAccount;
	}

	public void setHandlerAccount(java.lang.String handlerAccount) {
		this.handlerAccount = handlerAccount;
	}

	@Basic(optional = true)
	@Column(name = "hander_phone", length = 30)
	public java.lang.String getHandlerPhone() {
		return handlerPhone;
	}

	public void setHandlerPhone(java.lang.String handlerPhone) {
		this.handlerPhone = handlerPhone;
	}

	@Basic(optional = true)
	@Column(name = "hander_email", length = 50)
	public java.lang.String getHandlerEmail() {
		return handlerEmail;
	}

	public void setHandlerEmail(java.lang.String handlerEmail) {
		this.handlerEmail = handlerEmail;
	}

	@Basic(optional = true)
	@Column(name = "hander_wechat", length = 50)
	public java.lang.String getHandlerWechat() {
		return handlerWechat;
	}

	public void setHandlerWechat(java.lang.String handlerWechat) {
		this.handlerWechat = handlerWechat;
	}

	@Basic(optional = true)
	@Column(name = "msg_send_falg", length = 10)
	public java.lang.String getMsgSendFlag() {
		return msgSendFlag;
	}

	public void setMsgSendFlag(java.lang.String msgSendFlag) {
		this.msgSendFlag = msgSendFlag;
	}

	@Transient
	public java.lang.String getMsgSendFlagName() {
		return msgSendFlagName;
	}

	public void setMsgSendFlagName(java.lang.String msgSendFlagName) {
		this.msgSendFlagName = msgSendFlagName;
	}

	@Basic(optional = true)
	@Column(name = "email_send_flag", length = 10)
	public java.lang.String getEmailSendFlag() {
		return emailSendFlag;
	}

	public void setEmailSendFlag(java.lang.String emailSendFlag) {
		this.emailSendFlag = emailSendFlag;
	}

	@Basic(optional = true)
	@Column(name = "wechat_send_flag", length = 10)
	public java.lang.String getWechatSendFlag() {
		return wechatSendFlag;
	}

	public void setWechatSendFlag(java.lang.String wechatSendFlag) {
		this.wechatSendFlag = wechatSendFlag;
	}

	@Transient
	public java.lang.String getEmailSendFlagName() {
		return emailSendFlagName;
	}

	public void setEmailSendFlagName(java.lang.String emailSendFlagName) {
		this.emailSendFlagName = emailSendFlagName;
	}

	@Transient
	public java.lang.String getWechatSendFlagName() {
		return wechatSendFlagName;
	}

	public void setWechatSendFlagName(java.lang.String wechatSendFlagName) {
		this.wechatSendFlagName = wechatSendFlagName;
	}

	@Basic(optional = true)
	@Column(name = "msg_send_interval")
	public java.lang.Integer getMsgSendInterval() {
		return msgSendInterval;
	}

	public void setMsgSendInterval(java.lang.Integer msgSendInterval) {
		this.msgSendInterval = msgSendInterval;
	}

	@Basic(optional = true)
	@Column(name = "email_send_interval")
	public java.lang.Integer getEmailSendInterval() {
		return emailSendInterval;
	}

	public void setEmailSendInterval(java.lang.Integer emailSendInterval) {
		this.emailSendInterval = emailSendInterval;
	}

	@Basic(optional = true)
	@Column(name = "wechat_send_interval")
	public java.lang.Integer getWechatSendInterval() {
		return wechatSendInterval;
	}

	public void setWechatSendInterval(java.lang.Integer wechatSendInterval) {
		this.wechatSendInterval = wechatSendInterval;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @pdGenerated default parent getter
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_failure_info_id")
	public CdFailureInfo getCdFailureInfo() {
		return cdFailureInfo;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newCdFailureInfo
	 */
	public void setCdFailureInfo(CdFailureInfo newCdFailureInfo) {
		if (this.cdFailureInfo == null
				|| !this.cdFailureInfo.equals(newCdFailureInfo)) {
			if (this.cdFailureInfo != null) {
				CdFailureInfo oldCdFailureInfo = this.cdFailureInfo;
				this.cdFailureInfo = null;
				// oldCdFailureInfo.removeFailureHandlerList(this);
			}
			if (newCdFailureInfo != null) {
				this.cdFailureInfo = newCdFailureInfo;
				// this.cdFailureInfo.addFailureHandlerList(this);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FailureHandler other = (FailureHandler) obj;
		if (cdFailureInfo == null) {
			if (other.cdFailureInfo != null)
				return false;
		} else if (!cdFailureInfo.equals(other.cdFailureInfo))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (emailSendFlag == null) {
			if (other.emailSendFlag != null)
				return false;
		} else if (!emailSendFlag.equals(other.emailSendFlag))
			return false;
		if (emailSendFlagName == null) {
			if (other.emailSendFlagName != null)
				return false;
		} else if (!emailSendFlagName.equals(other.emailSendFlagName))
			return false;
		if (emailSendInterval == null) {
			if (other.emailSendInterval != null)
				return false;
		} else if (!emailSendInterval.equals(other.emailSendInterval))
			return false;
		if (handlerAccount == null) {
			if (other.handlerAccount != null)
				return false;
		} else if (!handlerAccount.equals(other.handlerAccount))
			return false;
		if (handlerEmail == null) {
			if (other.handlerEmail != null)
				return false;
		} else if (!handlerEmail.equals(other.handlerEmail))
			return false;
		if (handlerPhone == null) {
			if (other.handlerPhone != null)
				return false;
		} else if (!handlerPhone.equals(other.handlerPhone))
			return false;
		if (handlerWechat == null) {
			if (other.handlerWechat != null)
				return false;
		} else if (!handlerWechat.equals(other.handlerWechat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (msgSendFlag == null) {
			if (other.msgSendFlag != null)
				return false;
		} else if (!msgSendFlag.equals(other.msgSendFlag))
			return false;
		if (msgSendFlagName == null) {
			if (other.msgSendFlagName != null)
				return false;
		} else if (!msgSendFlagName.equals(other.msgSendFlagName))
			return false;
		if (msgSendInterval == null) {
			if (other.msgSendInterval != null)
				return false;
		} else if (!msgSendInterval.equals(other.msgSendInterval))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (wechatSendFlag == null) {
			if (other.wechatSendFlag != null)
				return false;
		} else if (!wechatSendFlag.equals(other.wechatSendFlag))
			return false;
		if (wechatSendFlagName == null) {
			if (other.wechatSendFlagName != null)
				return false;
		} else if (!wechatSendFlagName.equals(other.wechatSendFlagName))
			return false;
		if (wechatSendInterval == null) {
			if (other.wechatSendInterval != null)
				return false;
		} else if (!wechatSendInterval.equals(other.wechatSendInterval))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdFailureInfo == null) ? 0 : cdFailureInfo.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((emailSendFlag == null) ? 0 : emailSendFlag.hashCode());
		result = prime
				* result
				+ ((emailSendFlagName == null) ? 0 : emailSendFlagName
						.hashCode());
		result = prime
				* result
				+ ((emailSendInterval == null) ? 0 : emailSendInterval
						.hashCode());
		result = prime * result
				+ ((handlerAccount == null) ? 0 : handlerAccount.hashCode());
		result = prime * result
				+ ((handlerEmail == null) ? 0 : handlerEmail.hashCode());
		result = prime * result
				+ ((handlerPhone == null) ? 0 : handlerPhone.hashCode());
		result = prime * result
				+ ((handlerWechat == null) ? 0 : handlerWechat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((msgSendFlag == null) ? 0 : msgSendFlag.hashCode());
		result = prime * result
				+ ((msgSendFlagName == null) ? 0 : msgSendFlagName.hashCode());
		result = prime * result
				+ ((msgSendInterval == null) ? 0 : msgSendInterval.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((wechatSendFlag == null) ? 0 : wechatSendFlag.hashCode());
		result = prime
				* result
				+ ((wechatSendFlagName == null) ? 0 : wechatSendFlagName
						.hashCode());
		result = prime
				* result
				+ ((wechatSendInterval == null) ? 0 : wechatSendInterval
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "FailureHandler [id=" + id + ", handlerAccount="
				+ handlerAccount + ", handlerPhone=" + handlerPhone
				+ ", handlerEmail=" + handlerEmail + ", handlerWechat="
				+ handlerWechat + ", msgSendFlag=" + msgSendFlag
				+ ", msgSendFlagName=" + msgSendFlagName + ", emailSendFlag="
				+ emailSendFlag + ", emailSendFlagName=" + emailSendFlagName
				+ ", wechatSendFlag=" + wechatSendFlag
				+ ", wechatSendFlagName=" + wechatSendFlagName
				+ ", msgSendInterval=" + msgSendInterval
				+ ", emailSendInterval=" + emailSendInterval
				+ ", wechatSendInterval=" + wechatSendInterval
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", cdFailureInfo=" + cdFailureInfo + "]";
	}

}