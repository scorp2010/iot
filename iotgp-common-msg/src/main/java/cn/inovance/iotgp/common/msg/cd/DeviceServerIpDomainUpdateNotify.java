/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceServerIpDomainUpdateNotify.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-4上午9:29:35
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import java.util.ArrayList;
import java.util.List;
import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:DeviceServerIpDomainUpdateNotify <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-4 上午9:29:35 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class DeviceServerIpDomainUpdateNotify extends AbstractReqPackage {

	private static final int IP_DOMIAN_LENGTH = new ShortPdu().getLength();
	private ShortPdu ipDomainNums;
	/** key为ip/domain/, value端口号. */
	private List<DeviceIpDns> ipDomains  = new ArrayList<DeviceIpDns>(0);

	public DeviceServerIpDomainUpdateNotify() {
		this.header.setMsgType(Commands.SERVER_IP_DOMIAN_UPDATE_NOTIFY);
	}

	public DeviceServerIpDomainUpdateNotify(byte[] data) {
		super(data);
	}

	public ShortPdu getIpDomainNums() {
		return ipDomainNums;
	}

	public void setIpDomainNums(ShortPdu ipDomainNums) {

		this.ipDomainNums = ipDomainNums;
	}

	public List<DeviceIpDns> getIpDomains() {
		return ipDomains;
	}

	public void setIpDomains(List<DeviceIpDns> ipDomains) {
		this.ipDomains = ipDomains;
		ipDomainNums = new ShortPdu((short) this.ipDomains.size());
	}

	

	@Override
	protected void constructBody(byte[] data) {

		ByteOps.addByteArray(data, ipDomainNums.getBytes(), index);
		index += ipDomainNums.getLength();
		for (DeviceIpDns each : ipDomains) {
			ShortPdu length = new ShortPdu((short) each.getDns().getLength());
			ByteOps.addByteArray(data, length.getBytes(), index);
			index += length.getLength();

			ByteOps.addByteArray(data, each.getDns().getBytes(), index);
			index += each.getDns().getLength();

			ByteOps.addByteArray(data, each.getPort().getBytes(), index);
			index += each.getPort().getLength();
		}

	}

	@Override
	protected void parseBody() throws MessageParseException {

		ipDomainNums = new ShortPdu(index, data);
		index += ipDomainNums.getLength();
		int numbers = ipDomainNums.getValue();
		while (numbers-- > 0) {
			ShortPdu length = new ShortPdu(index, data);
			index += length.getLength();
			StringPdu ipDomain = new StringPdu(length.getValue(), index, data);
			index += ipDomain.getLength();
			IntPdu port = new IntPdu(index, data);
			index += port.getLength();
			ipDomains.add(new DeviceIpDns(ipDomain,port));
		}
	}

	@Override
	public String toString() {
		return "DeviceServerIpDomainUpdateNotify [ipDomainNums=" + ipDomainNums
				+ ", ipDomains=" + ipDomains + "]";
	}

	@Override
	protected int getMsgLength() {

		int msgLength = Header.LENGTH_HEADER + ipDomainNums.getLength();

		for (DeviceIpDns each : ipDomains) {

			msgLength += IP_DOMIAN_LENGTH + each.getDns().getLength()
					+  each.getPort().getLength();
		}

		return msgLength;
	}

	public static void main(String[] args) {
		DeviceServerIpDomainUpdateNotify notify = new DeviceServerIpDomainUpdateNotify();
		List<DeviceIpDns> paras = new  ArrayList<DeviceIpDns>(0);
		for (int i = 0; i < 4; i++) {
			paras.add(new DeviceIpDns(Integer.toString(i),i));
		}
		notify.setIpDomains(paras);
		notify.construct();
		System.out.println(ByteOps.bytesToHexString(notify.getData()));
		DeviceServerIpDomainUpdateNotify notify1 = new DeviceServerIpDomainUpdateNotify(
				notify.getData());
		System.out.println(notify.getData().length);
		try {
			notify1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(notify1.toString());
	}
}
