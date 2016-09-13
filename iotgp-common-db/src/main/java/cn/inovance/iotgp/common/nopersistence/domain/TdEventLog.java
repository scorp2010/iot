package cn.inovance.iotgp.common.nopersistence.domain;

@SuppressWarnings("serial")
public class TdEventLog implements java.io.Serializable {

	/**
	 * 编号
	 */
	private Long id;
	/**
	 * 采集设备注册码
	 */
	private String cdRegCode;
	/**
	 * 目标设备地址码
	 */
	private Short tdAdress;

	/**
	 * 事件数据
	 */
	private String eventData;

	/**
	 * 接收时间
	 */
	private Long recvTime;

	private java.util.Date logShowTime;

	/** 查询字段 */
	private java.lang.String startLogTime;

	/** 查询字段 */
	private java.lang.String endLogTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCdRegCode() {
		return cdRegCode;
	}

	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}

	public Short getTdAdress() {
		return tdAdress;
	}

	public void setTdAdress(Short tdAdress) {
		this.tdAdress = tdAdress;
	}

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	public Long getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(Long recvTime) {
		this.recvTime = recvTime;
	}
	public java.lang.String getStartLogTime() {
		return startLogTime;
	}

	public void setStartLogTime(java.lang.String startLogTime) {
		this.startLogTime = startLogTime;
	}
	public java.lang.String getEndLogTime() {
		return endLogTime;
	}

	public void setEndLogTime(java.lang.String endLogTime) {
		this.endLogTime = endLogTime;
	}

	public java.util.Date getLogShowTime() {
		return logShowTime;
	}

	public void setLogShowTime(java.util.Date logShowTime) {
		this.logShowTime = logShowTime;
	}
}
