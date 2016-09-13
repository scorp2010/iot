package cn.inovance.iotgp.common.enums;

public enum DataSyncType {

	/** 新增 */
	New(0x01),
	/** 删除 */
	Delete(0x02),
	/** 修改 */
	Update(0x03),
	/** 替换 */
	Replace(0x04);

	private short value = 0;

	// 调用构造函数来构造枚举项
	private DataSyncType(int value) {
		this.value = (short) value;
	}

	// 从short到enum的转换函数
	public static DataSyncType valueOf(short value) {
		switch (value) {
		case 1:
			return New;
		case 2:
			return Delete;
		case 3:
			return Update;
		case 4:
			return Replace;
		default:
			return null;
		}
	}

	public short value() {
		return this.value;
	}
}
