package cn.inovance.iotgp.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 采集设备事件日志表模型
 * @date Thu Jun 25 17:05:33 CST 2015
 *
 */
@Entity
@Table(name = "t_mms_cd_event_log")
public class CdEventLog implements Serializable{


	/**序列化id*/
	private static final Long serialVersionUID = 1L;
	/***/
	private String id;
	/**采集设备注册码*/
	private String cdRegCode;
	/**目标设备地址*/
	private Integer tdAddress;
	/**事件序号*/
	private Long eventSeq;
	/**事件编码*/
	private Integer eventCode;
	/**事件内容*/
	private String eventData;
	/**事件状态*/
	private Integer eventStatus;
	/**事件触发时间*/
	private Long eventTime;
	/**事件发生时刻对应实时数据*/
	private String eventRealTimeDatas;
	/**客户编码*/
	private String customerCode;
	/***/
	private String dhsCode;
	/**最后更新时间*/
	private Long lastUpdateTime;
	/**同步标志*/
	private Short isSync = 0;
	
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Basic(optional = true)
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 28)
	public String getCdRegCode() {
		return cdRegCode;
	}
	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}
	@Basic(optional = true)
	@Column(name = "td_address", insertable = true, updatable = true, length = 1)
	public Integer getTdAddress() {
		return tdAddress;
	}
	public void setTdAddress(Integer tdAddress) {
		this.tdAddress = tdAddress;
	}
	@Basic(optional = true)
	@Column(name = "event_seq", insertable = true, updatable = true)
	public Long getEventSeq() {
		return eventSeq;
	}
	public void setEventSeq(Long eventSeq) {
		this.eventSeq = eventSeq;
	}
	@Basic(optional = true)
	@Column(name = "event_code", insertable = true, updatable = true)
	public Integer getEventCode() {
		return eventCode;
	}
	public void setEventCode(Integer eventCode) {
		this.eventCode = eventCode;
	}
	@Basic(optional = true)
	@Column(name = "event_data", insertable = true, updatable = true)
	public String getEventData() {
		return eventData;
	}
	public void setEventData(String eventData) {
		this.eventData = eventData;
	}
	@Basic(optional = true)
	@Column(name = "event_status", insertable = true, updatable = true)
	public Integer getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(Integer eventStatus) {
		this.eventStatus = eventStatus;
	}
	@Basic(optional = true)
	@Column(name = "event_time", insertable = true, updatable = true)
	public Long getEventTime() {
		return eventTime;
	}
	public void setEventTime(Long eventTime) {
		this.eventTime = eventTime;
	}
	@Basic(optional = true)
	@Column(name = "event_real_time_datas", insertable = true, updatable = true)
	public String getEventRealTimeDatas() {
		return eventRealTimeDatas;
	}
	public void setEventRealTimeDatas(String eventRealTimeDatas) {
		this.eventRealTimeDatas = eventRealTimeDatas;
	}
	@Basic(optional = true)
	@Column(name = "customer_code", insertable = true, updatable = true)
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	@Basic(optional = true)
	@Column(name = "dhs_code", insertable = true, updatable = true)
	public String getDhsCode() {
		return dhsCode;
	}
	public void setDhsCode(String dhsCode) {
		this.dhsCode = dhsCode;
	}
	@Basic(optional = true)
	@Column(name = "last_update_time", insertable = true, updatable = true)
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Basic(optional = true)
	@Column(name = "is_sync", insertable = true, updatable = true)
	public Short getIsSync() {
		return isSync;
	}
	public void setIsSync(Short isSync) {
		this.isSync = isSync;
	}
	
}