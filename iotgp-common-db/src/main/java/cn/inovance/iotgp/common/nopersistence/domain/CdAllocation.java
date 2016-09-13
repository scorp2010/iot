/**
 * Project Name:iotgp-common-db
 * File Name:CdAllocation.java
 * Package Name:cn.inovance.iotgp.common.nopersistence.domain
 * Date:2015-1-7上午9:33:13
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.nopersistence.domain;
/**
 * ClassName:CdAllocation <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-7 上午9:33:13 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CdAllocation implements  java.io.Serializable {

	private String from;
	private String to;
	private String allocationData;
	private String operator;
	private String allocationType;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAllocationData() {
		return allocationData;
	}
	public void setAllocationData(String allocationData) {
		this.allocationData = allocationData;
	}
	public String getAllocationType() {
		return allocationType;
	}
	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allocationData == null) ? 0 : allocationData.hashCode());
		result = prime * result
				+ ((allocationType == null) ? 0 : allocationType.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		CdAllocation other = (CdAllocation) obj;
		if (allocationData == null) {
			if (other.allocationData != null)
				return false;
		} else if (!allocationData.equals(other.allocationData))
			return false;
		if (allocationType == null) {
			if (other.allocationType != null)
				return false;
		} else if (!allocationType.equals(other.allocationType))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CdAllocation [from=" + from + ", to=" + to
				+ ", allocationData=" + allocationData + ", operator="
				+ operator + ", allocationType=" + allocationType + "]";
	}
	
}

