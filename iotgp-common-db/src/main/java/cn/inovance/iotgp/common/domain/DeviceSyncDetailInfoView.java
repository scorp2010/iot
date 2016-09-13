package cn.inovance.iotgp.common.domain;

import java.util.ArrayList;
import java.util.List;

public class DeviceSyncDetailInfoView {

	private String sysAccount;

	private DeviceSyncDetailInfo deviceSyncDetailInfo;

	private CdRegisterCode cdRegisterCode;

	private CollectDevice collectDevice;

	private List<TargetDevice> targetDevices = new ArrayList<TargetDevice>();

	public DeviceSyncDetailInfoView() {
	}

	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	public DeviceSyncDetailInfo getDeviceSyncDetailInfo() {
		return deviceSyncDetailInfo;
	}

	public void setDeviceSyncDetailInfo(
			DeviceSyncDetailInfo deviceSyncDetailInfo) {
		this.deviceSyncDetailInfo = deviceSyncDetailInfo;
	}

	public CollectDevice getCollectDevice() {
		return collectDevice;
	}

	public void setCollectDevice(CollectDevice collectDevice) {
		this.collectDevice = collectDevice;
	}

	public CdRegisterCode getCdRegisterCode() {
		return cdRegisterCode;
	}

	public void setCdRegisterCode(CdRegisterCode cdRegisterCode) {
		this.cdRegisterCode = cdRegisterCode;
	}

	public List<TargetDevice> getTargetDevices() {
		return targetDevices;
	}

	public void setTargetDevices(List<TargetDevice> targetDevices) {
		this.targetDevices = targetDevices;
	}

	public void addTargetDevice(TargetDevice targetDevice) {
		this.targetDevices.add(targetDevice);
	}

}
