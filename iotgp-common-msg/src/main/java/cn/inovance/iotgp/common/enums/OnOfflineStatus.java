package cn.inovance.iotgp.common.enums;

public enum OnOfflineStatus {

	/** 在线 */
	OnLine(0x01),
	/** 离线 */
	OffLine(0x02);

	private short value = 0x02;

	//调用构造函数来构造枚举项
	private OnOfflineStatus(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static OnOfflineStatus valueOf(short value) { 
		switch (value) {
		case 0x01:
			return OnLine;
		case 0x02:
			return OffLine;
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
