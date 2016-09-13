package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName: CdHeartBeat <br/>
 * Function: 采集设备心跳消息. <br/>
 * date: 2014-4-8 下午4:38:18 <br/>
 * 
 * @author z1979
 * @version 1.0
 * @since JDK 1.7
 */
public class CdHeartBeatReq extends AbstractReqPackage {
	protected static final int INDEX_BODY_RESERVE = 7;

	public final int PACKAGE_LENGTH = 8;

	private ShortPdu bodyReserve = new ShortPdu((short) 0);

	public short getBodyReserve() {
		return bodyReserve.getValue();
	}

	public void setBodyReserve(short bodyReserve) {
		this.bodyReserve = new ShortPdu(bodyReserve);
	}

	public CdHeartBeatReq(Header header) {
		data = new byte[PACKAGE_LENGTH];
		this.header = header.clone(Commands.CD_HEARTBEAT_REQ, data);
	}

	public CdHeartBeatReq() {
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.CD_HEARTBEAT_REQ);
	}

	public CdHeartBeatReq(byte[] data) {
		this.data = data;
		header.setData(data);
	}

	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, bodyReserve.getBytes(), INDEX_BODY_RESERVE);
	}

	@Override
	public void parseBody() throws MessageParseException {
		bodyReserve = new ShortPdu(INDEX_BODY_RESERVE, data);
	}

	public static void main(String[] args) throws MessageParseException {
		CdHeartBeatReq rsp = new CdHeartBeatReq();
		rsp.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
		CdHeartBeatReq rsp2 = new CdHeartBeatReq(rsp.getData());
		rsp2.parse();
		System.out.println(ByteOps.bytes2HexStringWithBlank(rsp.getData()));
	}

	@Override
	protected int getMsgLength() {

		return Header.LENGTH_HEADER + bodyReserve.getLength();
	}
}
