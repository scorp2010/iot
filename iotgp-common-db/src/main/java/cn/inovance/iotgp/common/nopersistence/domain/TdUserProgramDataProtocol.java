/**
 * Project Name:iotgp-common-db
 * File Name:TdUserProgramDataProtocol.java
 * Package Name:cn.inovance.iotgp.common.nopersistence.domain
 * Date:2014-9-5上午10:38:56
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.nopersistence.domain;

/**
 * ClassName:TdUserProgramDataProtocol <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-5 上午10:38:56 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@SuppressWarnings("serial")
public class TdUserProgramDataProtocol implements java.io.Serializable {

	private String id;
	
	private Integer tdAddressCode;
	private Integer tdControllerType;
	private String typeName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTdAddressCode() {
		return tdAddressCode;
	}
	public void setTdAddressCode(Integer tdAddressCode) {
		this.tdAddressCode = tdAddressCode;
	}
	public Integer getTdControllerType() {
		return tdControllerType;
	}
	public void setTdControllerType(Integer tdControllerType) {
		this.tdControllerType = tdControllerType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((tdAddressCode == null) ? 0 : tdAddressCode.hashCode());
		result = prime
				* result
				+ ((tdControllerType == null) ? 0 : tdControllerType.hashCode());
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
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
		TdUserProgramDataProtocol other = (TdUserProgramDataProtocol) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tdAddressCode == null) {
			if (other.tdAddressCode != null)
				return false;
		} else if (!tdAddressCode.equals(other.tdAddressCode))
			return false;
		if (tdControllerType == null) {
			if (other.tdControllerType != null)
				return false;
		} else if (!tdControllerType.equals(other.tdControllerType))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TdUserProgramDataProtocol [id=" + id + ", tdAddressCode="
				+ tdAddressCode + ", tdControllerType=" + tdControllerType
				+ ", typeName=" + typeName + "]";
	}
	
}

