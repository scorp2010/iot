package cn.inovance.iotgp.common.msg.server.bean;

@SuppressWarnings("serial")
public class TdCtrlCmdInfo implements java.io.Serializable{

	private String name;
	private String ctrlType;
	private String templet;
	private String param;
	private Short format;
	private Short checkType;
	private String cmd;
	private Short rspDataLength;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Short getFormat() {
		return format;
	}

	public void setFormat(Short format) {
		this.format = format;
	}

	public Short getCheckType() {
		return checkType;
	}

	public void setCheckType(Short checkType) {
		this.checkType = checkType;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Short getRspDataLength() {
		return rspDataLength;
	}

	public void setRspDataLength(Short rspDataLength) {
		this.rspDataLength = rspDataLength;
	}

}
