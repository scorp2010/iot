package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.IntPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public abstract class AbstractRspPackage extends AbstractPackage{
	protected final int INDEX_ERROR_CODE=46;
	
	private IntPdu errorCode = new IntPdu(0);//默认处理成功
	
	public int getErrorCode() {
		return errorCode.getValue();
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = new IntPdu(errorCode);
	}

	public void construct() {
		this.header.construct(data);
		ByteOps.addByteArray(data, errorCode.getBytes(), INDEX_ERROR_CODE);
		this.constructBody(data);
	}
	
	public void parse() throws MessageParseException {
		this.header.parse();
		errorCode = new IntPdu(this.INDEX_ERROR_CODE, data);
		this.parseBody();
	}

}
