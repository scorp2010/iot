package cn.inovance.iotgp.common.msg.cd.meta;

public abstract class PDU {

	protected int dataLenth;

	protected int startIndex;

	public abstract Object getValue();

	public abstract int getLength();

	public abstract byte[] getBytes();

	@Override
	public String toString() {
		return this.getValue().toString();
	}
}
