package cn.inovance.iotgp.common.msg.server.bean;

import java.util.ArrayList;
import java.util.List;

public class TdCtrlDataProtocol {
	/** 协议名称 */
	private String name;
	/** 协议版本 */
	private String version;
	/** 协议描述 */
	private String describe;
	/** 协议标签列表 */
	private List<TdCtrlDataProtocolTag> ctrlDataProtocolTags = new ArrayList<TdCtrlDataProtocolTag>();
	
	public TdCtrlDataProtocol() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<TdCtrlDataProtocolTag> getCtrlDataProtocolTags() {
		return ctrlDataProtocolTags;
	}

	public void setCtrlDataProtocolTags(
			List<TdCtrlDataProtocolTag> ctrlDataProtocolTags) {
		this.ctrlDataProtocolTags = ctrlDataProtocolTags;
	}

}
