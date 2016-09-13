package cn.inovance.iotgp.common.msg.server.bean;

@SuppressWarnings("serial")
public class CdParameter implements java.io.Serializable {

	private String tag;
	private String value;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
