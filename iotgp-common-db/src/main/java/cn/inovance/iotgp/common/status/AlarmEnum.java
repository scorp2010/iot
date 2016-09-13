/**
 * Project Name:iotgp-cds
 * File Name:ConstantUtil.java
 * Package Name:cn.inovance.iotgp.cds.util
 * Date:2014-5-9上午9:18:45
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.status;

/**
 * ClassName:ConstantUtil 常量.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public enum AlarmEnum {


	/**
	 * 故障类型:1.故障困人、2.停电困人、9.手动呼救
	 */
	FAULT_TYPE("FAULT_TYPE"), 
		FAULT_TYPE_FAILURE("1"), FAULT_TYPE_POWER_CUT("2"), FAULT_TYPE_ACTIVE("9"),
	
	/**
	 * 故障状态 1.故障中 2.故障已解除
	 */
	FAULT_STATUS("FAULT_STATUS"), 
		FAULT_STATUS_FAULTING("1"), FAULT_STATUS_FAULT_REMOVE("2"),FAULT_STATUS_FAULT_UNTREATED("3"),
	
	/**
	 * 报警信息通知状态（1.未发送报警通知信息、2.已发送一级报警通知信息、3.已发送二级报警通知信息、4.已发送三级报警通知信息）
	 */
	ALARM_MESSAGE_STATUS("ALARM_MESSAGE_STATUS"), 
		ALARM_MESSAGE_STATUS_NONE("1"), ALARM_MESSAGE_STATUS_LEVEL_1("2"), 
		ALARM_MESSAGE_STATUS_LEVEL_2("3"),ALARM_MESSAGE_STATUS_LEVEL_3("4"),

		
	/**
	 * 当前报警状态（1.一级报警中、2.一级告警已确认、3.二级报警中、4.二级报警已确认、5.三级报警中、6.三级报警已确认）
	 */
	ALARM_LEVEL_STATUS("ALARM_LEVEL_STATUS"), 
		ALARM_LEVEL_STATUS_LEVEL_1("1"), ALARM_LEVEL_STATUS_LEVEL_1_CONFIRM("2"), 
		ALARM_LEVEL_STATUS_LEVEL_2("3"), ALARM_LEVEL_STATUS_LEVEL_2_CONFIRM("4"), 
		ALARM_LEVEL_STATUS_LEVEL_3("5"), ALARM_LEVEL_STATUS_LEVEL_3_CONFIRM("6"), 
		ALARM_LEVEL_STATUS_TIME_OUT("9"),
	
		
		
	Constant_util_version("1");
	public String value;

	AlarmEnum(String value) {
		this.value = value;
	}

}
