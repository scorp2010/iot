package cn.inovance.iotgp.common.msg.server.bean;

public class TargetDeviceInfo {

	private String id;

	private String name;

	private short type;

	private String ctrlType;

	private short status;

	private String address;

	public TargetDeviceInfo() {
	}

	public TargetDeviceInfo(String id, String name, short type,
			String ctrlType, short status, String address) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.ctrlType = ctrlType;
		this.status = status;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
