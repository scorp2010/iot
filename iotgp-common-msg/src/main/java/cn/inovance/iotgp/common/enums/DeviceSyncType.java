package cn.inovance.iotgp.common.enums;

public enum DeviceSyncType {

	/** 新增绑定 */
	New(0x01),
	/** 解除绑定 */
	Unbound(0x02),
	/** 替换绑定 */
	Replace(0x03);

	private short value = 0;

	//调用构造函数来构造枚举项
	private DeviceSyncType(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static DeviceSyncType valueOf(short value) { 
		switch (value) {
		case 1:
			return New;
		case 2:
			return Unbound;
		case 3:
			return Replace;
		default:
			return null;
		}
	}

	public short value() {
		return this.value;
	}
}
