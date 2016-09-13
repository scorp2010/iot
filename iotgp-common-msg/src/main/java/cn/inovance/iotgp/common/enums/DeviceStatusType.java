package cn.inovance.iotgp.common.enums;

public enum DeviceStatusType {

	/** 在线 */
	OnLine(0x00), //1
	/** 离线 */
	OffLine(0x01), //2
	
	Passwd(0x02);
	private short value = 0x02;

	//调用构造函数来构造枚举项
	private DeviceStatusType(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static DeviceStatusType valueOf(short value) { 
		switch (value) {
		case 0x00:
			return OnLine;
		case 0x01:
			return OffLine;
		case 0x02:
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
