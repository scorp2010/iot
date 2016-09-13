package cn.inovance.iotgp.common.enums;

public enum ResourceFileType {

	Log(0x00),
	HisVedio(0x01),
	TroubleVedio(0x02),
	EventLog(0x03),
	TroubleData(0x04);

	private short value = 0;

	//调用构造函数来构造枚举项
	private ResourceFileType(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static ResourceFileType valueOf(short value) { 
		switch (value) {
		case 0:
			return Log;
		case 1:
			return HisVedio;
		case 2:
			return TroubleVedio;
		case 3:
			return EventLog;
		case 4:
			return TroubleData;
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
