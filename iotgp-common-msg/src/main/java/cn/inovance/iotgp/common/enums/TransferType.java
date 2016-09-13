package cn.inovance.iotgp.common.enums;

public enum TransferType {
	FTP(0x00),
	GFTP(0x01);

	private short value = 0;

	//调用构造函数来构造枚举项
	private TransferType(int value) {
		this.value = (short)value;
	}

	// 从short到enum的转换函数
	public static TransferType valueOf(short value) { 
		switch (value) {
		case 0:
			return FTP;
		case 1:
			return GFTP;
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
