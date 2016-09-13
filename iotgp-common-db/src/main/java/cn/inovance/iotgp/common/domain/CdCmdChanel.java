/**
 * Project Name:iotgp-common-db
 * File Name:CdCmdChanel.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-6-14下午5:01:52
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.xml.crypto.Data;

/**
 * ClassName:CdCmdChanel <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-14 下午5:01:52 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CdCmdChanel implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = -6380589916998187275L;
	/** 消息命令.*/
	private String cdRegCode;
	


	/** 消息命令.*/
	private String cmdMsg;
	/** 消息命令结果.*/
	private String cmdMsgResult;
	/** 发送时间. */
	private Date sendTime;
	/** 接收时间. */
	private Date receiveTime;
	
	private Integer errorCode;
	
	public Integer getErrorCode() {
		if(errorCode == null )
			errorCode = 0;
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getCmdMsg() {
		return cmdMsg;
	}

	public String getCdRegCode() {
		return cdRegCode;
	}


	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}
	public void setCmdMsg(String cmdMsg) {
		this.cmdMsg = cmdMsg;
	}

	public String getCmdMsgResult() {
		return cmdMsgResult;
	}

	public void setCmdMsgResult(String cmdMsgValue) {
		this.cmdMsgResult = cmdMsgValue;
	}

	public Date getSendTime() {
		if(sendTime == null )
			return new Date();
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;

	}

	public Date getReceiveTime() {
		if(receiveTime == null )
			return new Date();
		return receiveTime;
	}

	public void setReceiveTime(java.util.Date date) {
		this.receiveTime = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime * result + ((cmdMsg == null) ? 0 : cmdMsg.hashCode());
		result = prime * result
				+ ((cmdMsgResult == null) ? 0 : cmdMsgResult.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result
				+ ((receiveTime == null) ? 0 : receiveTime.hashCode());
		result = prime * result
				+ ((sendTime == null) ? 0 : sendTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdCmdChanel other = (CdCmdChanel) obj;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (cmdMsg == null) {
			if (other.cmdMsg != null)
				return false;
		} else if (!cmdMsg.equals(other.cmdMsg))
			return false;
		if (cmdMsgResult == null) {
			if (other.cmdMsgResult != null)
				return false;
		} else if (!cmdMsgResult.equals(other.cmdMsgResult))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (receiveTime == null) {
			if (other.receiveTime != null)
				return false;
		} else if (!receiveTime.equals(other.receiveTime))
			return false;
		if (sendTime == null) {
			if (other.sendTime != null)
				return false;
		} else if (!sendTime.equals(other.sendTime))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CdCmdChanel [cdRegCode=" + cdRegCode + ", cmdMsg=" + cmdMsg
				+ ", cmdMsgResult=" + cmdMsgResult + ", sendTime=" + sendTime
				+ ", receiveTime=" + receiveTime + ", errorCode=" + errorCode
				+ "]";
	}

}


