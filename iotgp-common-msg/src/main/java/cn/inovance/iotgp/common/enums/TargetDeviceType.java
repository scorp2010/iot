package cn.inovance.iotgp.common.enums;

public enum TargetDeviceType {

	/** 控制器 */
	Ctrl(0x00),
	/** 摄像头 */
	Camera(0x01),
	/** 对讲设备 */
	Intercom(0x02),
	/** 电梯控制器 */
	EVCtrl(0x03),
	/** 空压机控制器 */
	AirPumpCtrl(0x04),
	/** 光伏控制器 */
	PVCtrl(0x05),
	/** 起重控制器 */
	CraneCtrl(0x06);

	private short value = 0;

	//调用构造函数来构造枚举项
	private TargetDeviceType(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static TargetDeviceType valueOf(short value) { 
		switch (value) {
		case 0:
			return Ctrl;
		case 1:
			return Camera;
		case 2:
			return Intercom;
		case 3:
			return EVCtrl;
		case 4:
			return AirPumpCtrl;
		case 5:
			return PVCtrl;
		case 6:
			return CraneCtrl;
		default:
			return null;
		}
	}

	public short value() {
		return this.value;
	}

}
