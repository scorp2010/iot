package cn.inovance.iotgp.common.enums;

public enum PlatformType {
	platform("platform");

	private String code;

	private PlatformType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
