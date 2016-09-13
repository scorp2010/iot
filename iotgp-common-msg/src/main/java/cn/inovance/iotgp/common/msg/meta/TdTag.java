package cn.inovance.iotgp.common.msg.meta;

public class TdTag {
	/** 标签值 */
	private Short value;
	/** 采样周期 */
	private Integer samplingPeriod;

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public Integer getSamplingPeriod() {
		return samplingPeriod;
	}

	public void setSamplingPeriod(Integer samplingPeriod) {
		this.samplingPeriod = samplingPeriod;
	}

	public TdTag() {
	}

	public TdTag(Short value, Integer samplingPeriod) {
		this.value = value;
		this.samplingPeriod = samplingPeriod;
	}

}
