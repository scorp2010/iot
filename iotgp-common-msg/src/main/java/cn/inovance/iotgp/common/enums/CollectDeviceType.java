package cn.inovance.iotgp.common.enums;

public enum CollectDeviceType {

	/** 中继 */
	Collect(0x01),
	/** 主机 */
	Host(0x02);

	private short value = 0x01;

	// 调用构造函数来构造枚举项
	private CollectDeviceType(int value) {
		this.value = (short) value;
	}

	// 从short到enum的转换函数
	public static CollectDeviceType valueOf(short value) {
		switch (value) {
		case 1:
			return Collect;
		case 2:
			return Host;
		default:
			return null;
		}
	}

	public short value() {
		return this.value;
	}

}
