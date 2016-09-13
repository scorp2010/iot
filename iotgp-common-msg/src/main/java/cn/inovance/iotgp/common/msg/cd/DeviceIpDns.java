/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceIpDns.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-16下午3:33:32
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;

/**
 * ClassName:DeviceIpDns <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-16 下午3:33:32 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DeviceIpDns {

		private  StringPdu dns;
		
		private  IntPdu port;
		
		public DeviceIpDns(String dns,Integer port){
			this.dns = new StringPdu(dns);
			this.port = new IntPdu(port);
		}
		public DeviceIpDns(StringPdu dns,IntPdu port){
			this.dns= dns;
			this.port = port;
		}
		public StringPdu getDns() {
			return dns;
		}
		public void setDns(StringPdu dns) {
			this.dns = dns;
		}
		public void setDns(String dns) {
			this.dns = new StringPdu(dns);
		}
		public IntPdu getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = new IntPdu(port);
		}
		public void setPort(IntPdu port) {
			this.port = port;
		}
		@Override
		public String toString() {
			return "DeviceIpDns [dns=" + dns + ", port=" + port + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dns == null) ? 0 : dns.hashCode());
			result = prime * result + ((port == null) ? 0 : port.hashCode());
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
			DeviceIpDns other = (DeviceIpDns) obj;
			if (dns == null) {
				if (other.dns != null)
					return false;
			} else if (!dns.equals(other.dns))
				return false;
			if (port == null) {
				if (other.port != null)
					return false;
			} else if (!port.equals(other.port))
				return false;
			return true;
		}
}

