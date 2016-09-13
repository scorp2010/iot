/**
 * Project Name:iotgp-common-db
 * File Name:CdIpDns.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-6-16下午2:43:06
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:CdIpDns <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-16 下午2:43:06 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CdIpDns implements java.io.Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = -1623210087809535778L;
	private String cdRegCode;
	
	private String dns;
	private Integer port;
	private String dns1;
	private Integer port1;
	private String dns2;
	private Integer port2;
	private String dns3;
	private Integer port3;
	public String getCdRegCode() {
		return cdRegCode;
	}
	public void setCdRegCode(String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}
	public String getDns() {
		return dns;
	}
	public void setDns(String dns) {
		this.dns = dns;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getDns1() {
		return dns1;
	}
	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}
	public Integer getPort1() {
		return port1;
	}
	public void setPort1(Integer port1) {
		this.port1 = port1;
	}
	public String getDns2() {
		return dns2;
	}
	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}
	public Integer getPort2() {
		return port2;
	}
	public void setPort2(Integer port2) {
		this.port2 = port2;
	}
	public String getDns3() {
		return dns3;
	}
	public void setDns3(String dns3) {
		this.dns3 = dns3;
	}
	public Integer getPort3() {
		return port3;
	}
	public void setPort3(Integer port3) {
		this.port3 = port3;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdRegCode == null) ? 0 : cdRegCode.hashCode());
		result = prime * result + ((dns == null) ? 0 : dns.hashCode());
		result = prime * result + ((dns1 == null) ? 0 : dns1.hashCode());
		result = prime * result + ((dns2 == null) ? 0 : dns2.hashCode());
		result = prime * result + ((dns3 == null) ? 0 : dns3.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + ((port1 == null) ? 0 : port1.hashCode());
		result = prime * result + ((port2 == null) ? 0 : port2.hashCode());
		result = prime * result + ((port3 == null) ? 0 : port3.hashCode());
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
		CdIpDns other = (CdIpDns) obj;
		if (cdRegCode == null) {
			if (other.cdRegCode != null)
				return false;
		} else if (!cdRegCode.equals(other.cdRegCode))
			return false;
		if (dns == null) {
			if (other.dns != null)
				return false;
		} else if (!dns.equals(other.dns))
			return false;
		if (dns1 == null) {
			if (other.dns1 != null)
				return false;
		} else if (!dns1.equals(other.dns1))
			return false;
		if (dns2 == null) {
			if (other.dns2 != null)
				return false;
		} else if (!dns2.equals(other.dns2))
			return false;
		if (dns3 == null) {
			if (other.dns3 != null)
				return false;
		} else if (!dns3.equals(other.dns3))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (port1 == null) {
			if (other.port1 != null)
				return false;
		} else if (!port1.equals(other.port1))
			return false;
		if (port2 == null) {
			if (other.port2 != null)
				return false;
		} else if (!port2.equals(other.port2))
			return false;
		if (port3 == null) {
			if (other.port3 != null)
				return false;
		} else if (!port3.equals(other.port3))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CdIpDns [cdRegCode=" + cdRegCode + ", dns=" + dns + ", port="
				+ port + ", dns1=" + dns1 + ", port1=" + port1 + ", dns2="
				+ dns2 + ", port2=" + port2 + ", dns3=" + dns3 + ", port3="
				+ port3 + "]";
	}
	
}

