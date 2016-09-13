/**
 * Project Name:iotgp-common-db
 * File Name:CdAllocationHistory.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2014-9-25下午5:51:03
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CdAllocationHistory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-25 下午5:51:03 <br/>
 * @author   w1898
 * @version 
 * @since    JDK 1.7
 * @see 	
 */
@Entity(name = "CdAllocationLog")
@Table(name = "t_oms_cd_allocation_log",indexes ={ @Index(name="cd_reg_code_index",columnList= "cd_reg_code")  })
public class CdAllocationLog implements java.io.Serializable{

	private java.lang.String id;
	private java.lang.String  cdRegCode;

	private String allocationLog;

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
	@Column(name = "cd_reg_code", insertable = true, updatable = true, length = 50)
	public java.lang.String getCdRegCode() {
		return cdRegCode;
	}


	public void setCdRegCode(java.lang.String cdRegCode) {
		this.cdRegCode = cdRegCode;
	}

	@Basic(optional = true)
	@Column(name = "allocation_log", insertable = true, updatable = true, columnDefinition = "longtext")
	public String getAllocationLog() {
		return allocationLog;
	}
	

	public void setAllocationLog(String allocationLog) {
		this.allocationLog = allocationLog;
	}
	
//	@Transient
//	public String getAllocationLogByStr() {
//		try {
//			InputStream stream = this.allocationLog.getBinaryStream();
//			StringBuffer sb = new StringBuffer();
//			byte[] b = new byte[60000];// 每次获取60K
//			int i = 0;
//			while ((i = stream.read(b)) != -1) {
//					sb.append(new String(b));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return allocationLogByStr;
//	}
//
//
//	public void setAllocationLogByStr(String allocationLogByStr) {
//		this.allocationLogByStr = allocationLogByStr;
//	    this.allocationLog = new MyBlobImpl(allocationLogByStr.getBytes());
//	}
}

