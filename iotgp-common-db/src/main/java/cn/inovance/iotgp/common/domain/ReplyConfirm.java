package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Entity
public class ReplyConfirm {
   private String id;
   
   /**短信内容**/
   private String content;
   
   /**短信回复的时间**/
   private long time;
   
   /**短信回复的号码**/
   private  String telephone;
   
   /**
	 * 状态.
	*/
	private Integer status;
	
	/**短信的id**/
	private String messageId;
	
	private Date createTime;
    @Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	
	public void setId(String id) {
		this.id = id;
	}

    @Column(name = "content",nullable = false, length = 50)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "time",nullable = false)
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	@Column(name = "telephone",nullable = false,length = 36)
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	

	@Column(name = "messageId",nullable = false,length = 36)
	public String getMessageId() {
		return messageId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	   
   
}
