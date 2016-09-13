package cn.inovance.iotgp.common.enums;

public enum ServerType {

	/**
	 * 未定义
	 */
	UNDEFINED("-"),

	/**
	 * 采集设备接入服务器
	 */
	GDHS("gdhs"),

	/**
	 * 采集设备接入服务器
	 */
	CDAG("cdag"),

	/**
	 * 应用客户端接入服务器
	 */
	ACAG("acag"),
	
	/**
	 * 实时数据传输服务器
	 */
	RDTS("rdts"),

	/**
	 * 流媒体分发服务器
	 */
	MDDS("mdds"),

	/**
	 * 文件服务器
	 */
	FTP("ftp"),

	/**
	 * 文件服务器
	 */
	GFTP("gftp"),

	/**
	 * 业务管理系统
	 */
	BMS("bms"),

	/**
	 * 业务管理系统mms
	 */
	MMS("mms"),

	/**
	 * 业务管理系统oms
	 */
	OMS("oms"),

	/**
	 * 业务管理系统cds
	 */
	CDS("cds"),

	/**
	 * 业务管理系统sys
	 */
	SYS("sys"),

	/**
	 * 数据路由服务器
	 */
	DRS("drs"),

	/**
	 * 电梯行业通用处理服务器
	 */
	EVDHS("evdhs"),

	/**
	 * 空压机行业应用数据处理系统
	 */
	AIRCDHS("airc"),
	
	/**
	 * 远程局域网引用数据处理系统
	 */
	P2PMSDHS("p2pmsdhs"),
	/**
	 * 放热站应用数据处理系统
	 */
	HSDHS("hsdhs"),
	
	/**
	 * 通用应用数据处理系统
	 */
	PDHS("pdhs"),
	/**
	 * 通用应用数据处理系统
	 */
	GADHS("gashs"),

	/**
	 * 广告业务应用系统
	 */
	ABMS("abms");
	
	

	private String code;

	private ServerType(String code) {
		this.code = code;
	}

	// 从String到enum的转换函数
	public static ServerType valueOfString(String code) {
		return ServerType.valueOf(code.toUpperCase());
	}

	public String getCode() {
		return code;
	}

}
