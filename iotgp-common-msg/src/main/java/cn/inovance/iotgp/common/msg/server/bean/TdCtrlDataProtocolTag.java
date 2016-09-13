package cn.inovance.iotgp.common.msg.server.bean;

import java.util.ArrayList;
import java.util.List;

public class TdCtrlDataProtocolTag {
	/** tag名称 */
	private String name;
	/** 标签值，即seq */
	private Integer value;
	/** 采样周期 */
	private Integer samplingPeriod;
	/** 标签描述 */
	private String describe;
	/** 协议标签值列表 */
	private List<TdCtrlDataProtocolValue> ctrlDataProtocolValues = new ArrayList<TdCtrlDataProtocolValue>();

	public TdCtrlDataProtocolTag() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getSamplingPeriod() {
		return samplingPeriod;
	}

	public void setSamplingPeriod(Integer samplingPeriod) {
		this.samplingPeriod = samplingPeriod;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<TdCtrlDataProtocolValue> getCtrlDataProtocolValues() {
		return ctrlDataProtocolValues;
	}

	public void setCtrlDataProtocolValues(
			List<TdCtrlDataProtocolValue> ctrlDataProtocolValues) {
		this.ctrlDataProtocolValues = ctrlDataProtocolValues;
	}
}
