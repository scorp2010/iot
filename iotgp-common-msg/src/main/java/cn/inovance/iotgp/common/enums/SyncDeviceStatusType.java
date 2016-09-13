/**
 * Project Name:iotgp-common-msg
 * File Name:SyncDeviceStatusType.java
 * Package Name:cn.inovance.iotgp.common.enums
 * Date:2015-6-9下午2:33:12
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.enums;
/**
 * ClassName:SyncDeviceStatusType <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-6-9 下午2:33:12 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public enum  SyncDeviceStatusType {

	/** 在线 */
	OnLine(0x01), //1
	/** 离线 */
	OffLine(0x02), //2
	/** 有密码  */
	Passwd(0x03); 
	private short value = 0x02;
	
	//调用构造函数来构造枚举项
	private SyncDeviceStatusType(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static SyncDeviceStatusType valueOf(short value) { 
		switch (value) {
		case 0x01:
			return OnLine;
		case 0x02:
			return OffLine;
		case 0x03:
			return Passwd;
		default:
			return null;
		}
	}

	public short value() {
		return this.value;
	}
	
	public String valueString() {
		return ((Short)this.value).toString();
	}
}

