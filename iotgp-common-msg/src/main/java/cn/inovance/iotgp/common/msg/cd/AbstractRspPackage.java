package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * 响应消息基类
 * 
 * @author c2100
 * 
 */
public abstract class AbstractRspPackage extends AbstractPackage {

	public final static short ERROR_OK = 0;
	public final static short ERROR_INTERNAL = 1;

	public AbstractRspPackage() {
		index = Header.LENGTH_HEADER + 1;
	}

	public AbstractRspPackage(byte[] data) {
		super(data);
		index = Header.LENGTH_HEADER + 1;
	}

	protected final int INDEX_ERROR_CODE = 7;

	protected ShortPdu errorCode = new ShortPdu(ERROR_OK);// 默认处理成功

	public int getErrorCode() {
		return errorCode.getValue();
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = new ShortPdu((short) errorCode);
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = new ShortPdu(errorCode.getCode());
	}

	public void construct() {
		data = new byte[this.getMsgLength()];
		this.header.construct(data);
		ByteOps.addByteArray(data, errorCode.getBytes(), INDEX_ERROR_CODE);
		this.constructBody(data);
	}

	public void parse() throws MessageParseException {
		this.header.parse();
		errorCode = new ShortPdu(this.INDEX_ERROR_CODE, data);
		this.parseBody();
	}

}
