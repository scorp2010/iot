/**
 * Project Name:iotgp-common-msg
 * File Name:DeviceCmdChanelRsp.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-6-3下午1:53:46
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:DeviceCmdChanelRsp <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-3 下午1:53:46 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
public class CdHeartbeatRsp extends AbstractRspPackage {

	public CdHeartbeatRsp() {
		this.header.setMsgType(Commands.CD_HEARTBEAT_RSP);
	}

	public CdHeartbeatRsp(byte[] data) {
		super(data);
	}

	@Override
	protected void constructBody(byte[] data) {

	}

	@Override
	protected void parseBody() throws MessageParseException {

	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + this.errorCode.getLength();
	}

	@Override
	public String toString() {
		return "CdHeartbeatRsp [errorCode=" + errorCode + "]";
	}

	public static void main(String[] args) {

		CdHeartbeatRsp rsp = new CdHeartbeatRsp();
		rsp.setErrorCode((short) 1);
		rsp.construct();
		System.out.println(ByteOps.bytesToHexString(rsp.getData()));
		CdHeartbeatRsp rsp1 = new CdHeartbeatRsp(rsp.getData());
		System.out.println(rsp.getData().length);
		try {
			rsp1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(rsp1.toString());
	}

}
