/***********************************************************************
 * Module:  ImLog.java
 * Author:  w1898
 * Purpose: Defines the Class ImLog
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 即时通信记录
 * 
 * @pdOid 80a07478-94f2-47ab-9483-2df6f741c1ba
 */
@Entity(name = "ImLog")
@Table(name = "t_mms_im_log")
public class ImLog implements java.io.Serializable {
	/**
	 * 编号
	 * 
	 * @pdOid 7f6a8beb-6aa3-411d-bc9e-a96adbb4d7f2
	 */
	private java.lang.String id;
	/**
	 * 发送人
	 * 
	 * @pdOid a27061ff-d534-455a-819e-d0a40f185c85
	 */
	private java.lang.String sender;
	/**
	 * 客户
	 * 
	 * @pdOid e4fe71f8-5eb2-43a7-907e-47f2d828ca77
	 */
	private java.lang.String customerAccount;
	/**
	 * 通信方式 0：message 1：weixin 2：email
	 * 
	 * @pdOid d8f714f2-cd7a-4278-b71d-a1d8ac66b86b
	 */
	private java.lang.String type;
	/**
	 * 通信方式名称 0：message 1：weixin 2：email
	 * 
	 **/
	private java.lang.String typeName;

	/**
	 * 通信主题
	 * 
	 * @pdOid e1bdcba6-b3d3-478e-bcba-921b04e2fc00
	 */
	private java.lang.String title;
	/**
	 * 通信内容
	 * 
	 * 
	 * @pdOid 01a3aa85-771e-4d6d-9096-a3c01d184a17
	 */
	private java.lang.String content;
	/**
	 * 接收者
	 * 
	 * @pdOid f15af47c-b48f-4561-a754-dad70ac187eb
	 */
	private java.lang.String receiverIds;
	/**
	 * 发送时间
	 * 
	 * @pdOid 794b0e6b-90f1-4ab3-a109-74a1c9c78a7c
	 */
	private java.util.Date sendTime;
	/**
	 * 附件，可以是文件、图片、地址连接等
	 * 
	 * @pdOid 57563edc-0fba-4209-8b53-8ff00afad5f4
	 */
	private java.lang.String attachment;
	/**
	 * 附件类型
	 * 
	 * @pdOid 5fc8fc74-c236-464c-99c1-685ecd5d8a84
	 */
	private java.lang.String attachmentType;
	/**
	 * 附件类型名称
	 * 
	 **/
	private java.lang.String attachmentTypeName;
	/**
	 * 通信原因 0：设备故障
	 * 
	 * @pdOid 7926abaf-76b3-4cd8-b83a-d765beeda6a2
	 */
	private java.lang.String cause;
	/**
	 * 通信原因 0：设备故障
	 * 
	 * @pdOid 7926abaf-76b3-4cd8-b83a-d765beeda6a2
	 */
	private java.lang.String causeName;

	/**
	 * 发送状态
	 * 
	 * @pdOid bf0444a3-d38b-4243-9d18-e63e3a7f6ab8
	 */
	private java.lang.String sendStatus;

	/**
	 * 发送状态名称
	 * 
	 * */
	private java.lang.String sendStatusName;
	/**
	 * 创建时间
	 * 
	 * @pdOid ebc7d460-6123-48f2-90a3-d6c85b1bbe33
	 */
	private java.util.Date createTime;

	/**
	 * Empty constructor which is required by EJB 3.0 spec.
	 * 
	 */
	public ImLog() {
		// TODO Add your own initialization code here.
	}

	/**
	 * Get value of id
	 * 
	 * @return id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set value of id
	 * 
	 * @param newId
	 */
	public void setId(java.lang.String newId) {
		this.id = newId;
	}

	/**
	 * Get value of sender
	 * 
	 * @return sender
	 */
	@Basic(optional = true)
	@Column(name = "sender", insertable = true, updatable = true, length = 50)
	public java.lang.String getSender() {
		return sender;
	}

	/**
	 * Set value of sender
	 * 
	 * @param newSender
	 */
	public void setSender(java.lang.String newSender) {
		this.sender = newSender;
	}

	/**
	 * Get value of customerAccount
	 * 
	 * @return customerAccount
	 */
	@Basic(optional = true)
	@Column(name = "customer_account", insertable = true, updatable = true, length = 100)
	public java.lang.String getCustomerAccount() {
		return customerAccount;
	}

	/**
	 * Set value of customerAccount
	 * 
	 * @param newCustomerAccount
	 */
	public void setCustomerAccount(java.lang.String newCustomerAccount) {
		this.customerAccount = newCustomerAccount;
	}

	/**
	 * Get value of type
	 * 
	 * @return type
	 */
	@Basic(optional = true)
	@Column(name = "type", insertable = true, updatable = true)
	public java.lang.String getType() {
		return type;
	}

	/**
	 * Set value of type
	 * 
	 * @param newType
	 */
	public void setType(java.lang.String newType) {
		this.type = newType;
	}

	@Transient
	public java.lang.String getTypeName() {
		return typeName;
	}

	/**
	 * Set value of type
	 * 
	 * @param newType
	 */
	public void setTypeName(java.lang.String newTypeName) {
		this.typeName = newTypeName;
	}

	/**
	 * Get value of title
	 * 
	 * @return title
	 */
	@Basic(optional = true)
	@Column(name = "title", insertable = true, updatable = true, length = 50)
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * Set value of title
	 * 
	 * @param newTitle
	 */
	public void setTitle(java.lang.String newTitle) {
		this.title = newTitle;
	}

	/**
	 * Get value of content
	 * 
	 * @return content
	 */
	@Basic(optional = true)
	@Column(name = "content", insertable = true, updatable = true, length = 180)
	public java.lang.String getContent() {
		return content;
	}

	/**
	 * Set value of content
	 * 
	 * @param newContent
	 */
	public void setContent(java.lang.String newContent) {
		this.content = newContent;
	}

	/**
	 * Get value of receiverIds
	 * 
	 * @return receiverIds
	 */
	@Basic(optional = true)
	@Column(name = "receiver_ids", insertable = true, updatable = true, length = 1024)
	public java.lang.String getReceiverIds() {
		return receiverIds;
	}

	/**
	 * Set value of receiverIds
	 * 
	 * @param newReceiverIds
	 */
	public void setReceiverIds(java.lang.String newReceiverIds) {
		this.receiverIds = newReceiverIds;
	}

	/**
	 * Get value of sendTime
	 * 
	 * @return sendTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "send_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getSendTime() {
		if (this.sendTime != null)
			return this.sendTime;
		return new Date();
	}

	/**
	 * Set value of sendTime
	 * 
	 * @param newSendTime
	 */
	public void setSendTime(java.util.Date newSendTime) {
		this.sendTime = newSendTime;
	}

	/**
	 * Get value of attachment
	 * 
	 * @return attachment
	 */
	@Basic(optional = true)
	@Column(name = "attachment", insertable = true, updatable = true, length = 400)
	public java.lang.String getAttachment() {
		return attachment;
	}

	/**
	 * Set value of attachment
	 * 
	 * @param newAttachment
	 */
	public void setAttachment(java.lang.String newAttachment) {
		this.attachment = newAttachment;
	}

	/**
	 * Get value of attachmentType
	 * 
	 * @return attachmentType
	 */
	@Transient
	public java.lang.String getAttachmentTypeName() {
		return attachmentType;
	}

	/**
	 * Set value of attachmentType
	 * 
	 * @param newAttachmentType
	 */
	public void setAttachmentTypeName(java.lang.String newAttachmentTypeName) {
		this.attachmentTypeName = newAttachmentTypeName;
	}

	/**
	 * Get value of attachmentType
	 * 
	 * @return attachmentType
	 */
	@Basic(optional = true)
	@Column(name = "attachment_type", insertable = true, updatable = true, length = 10)
	public java.lang.String getAttachmentType() {
		return attachmentType;
	}

	/**
	 * Set value of attachmentType
	 * 
	 * @param newAttachmentType
	 */
	public void setAttachmentType(java.lang.String newAttachmentType) {
		this.attachmentType = newAttachmentType;
	}

	/**
	 * Get value of cause
	 * 
	 * @return cause
	 */
	@Basic(optional = true)
	@Column(name = "cause", insertable = true, updatable = true, length = 10)
	public java.lang.String getCause() {
		return cause;
	}

	/**
	 * Set value of cause
	 * 
	 * @param newCause
	 */
	public void setCause(java.lang.String newCause) {
		this.cause = newCause;
	}

	@Transient
	public java.lang.String getCauseName() {
		return causeName;
	}

	public void setCauseName(java.lang.String causeName) {
		this.causeName = causeName;
	}

	/**
	 * Get value of sendStatus
	 * 
	 * @return sendStatus
	 */
	@Basic(optional = true)
	@Column(name = "send_status", insertable = true, updatable = true, length = 10)
	public java.lang.String getSendStatus() {
		return sendStatus;
	}

	/**
	 * Set value of sendStatus
	 * 
	 * @param newSendStatus
	 */
	public void setSendStatus(java.lang.String newSendStatus) {
		this.sendStatus = newSendStatus;
	}

	@Transient
	public java.lang.String getSendStatusName() {
		return sendStatusName;
	}

	public void setSendStatusName(java.lang.String sendStatusName) {
		this.sendStatusName = sendStatusName;
	}

	/**
	 * Get value of createTime
	 * 
	 * @return createTime
	 */
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public java.util.Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	/**
	 * Set value of createTime
	 * 
	 * @param newCreateTime
	 */
	public void setCreateTime(java.util.Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (!(other instanceof ImLog))
			return false;

		ImLog cast = (ImLog) other;

		if (!this.getId().equals(cast.getId()))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		if (this.sender != null)
			hashCode = 29 * hashCode + sender.hashCode();
		if (this.customerAccount != null)
			hashCode = 29 * hashCode + customerAccount.hashCode();
		if (this.type != null)
			hashCode = 29 * hashCode + type.hashCode();
		if (this.title != null)
			hashCode = 29 * hashCode + title.hashCode();
		if (this.content != null)
			hashCode = 29 * hashCode + content.hashCode();
		if (this.receiverIds != null)
			hashCode = 29 * hashCode + receiverIds.hashCode();
		if (this.sendTime != null)
			hashCode = 29 * hashCode + sendTime.hashCode();
		if (this.attachment != null)
			hashCode = 29 * hashCode + attachment.hashCode();
		if (this.attachmentType != null)
			hashCode = 29 * hashCode + attachmentType.hashCode();
		if (this.cause != null)
			hashCode = 29 * hashCode + cause.hashCode();
		if (this.sendStatus != null)
			hashCode = 29 * hashCode + sendStatus.hashCode();
		if (this.createTime != null)
			hashCode = 29 * hashCode + createTime.hashCode();
		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("cn.inovance.iotgp.bms.mms.ImLog: ");
		ret.append("id='" + id + "'");
		ret.append("sender='" + sender + "'");
		ret.append("customerAccount='" + customerAccount + "'");
		ret.append("type='" + type + "'");
		ret.append("title='" + title + "'");
		ret.append("content='" + content + "'");
		ret.append("receiverIds='" + receiverIds + "'");
		ret.append("sendTime='" + sendTime + "'");
		ret.append("attachment='" + attachment + "'");
		ret.append("attachmentType='" + attachmentType + "'");
		ret.append("cause='" + cause + "'");
		ret.append("sendStatus='" + sendStatus + "'");
		ret.append("createTime='" + createTime + "'");
		return ret.toString();
	}

}