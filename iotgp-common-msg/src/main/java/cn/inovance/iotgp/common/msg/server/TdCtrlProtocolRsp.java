package cn.inovance.iotgp.common.msg.server;

import java.util.ArrayList;
import java.util.List;

import cn.inovance.iotgp.common.msg.server.bean.TdCtrlDataProtocolTag;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class TdCtrlProtocolRsp extends Response {

	public TdCtrlProtocolRsp() {
		this(SeqGenerator.next());
	}

	public TdCtrlProtocolRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.CTRL_PROTOCOL_RSP;
	}

	public TdCtrlProtocolRsp(TdCtrlProtocolReq req) {
		super(req);
		this.msgType = MsgType.CTRL_PROTOCOL_RSP;
	}

	/** 同步类型 */
	private short syncType;
	/** 控制器类型名称 */
	private String ctrlType;
	/** 控制器类型版本 */
	private String ctrlTypeVersion;
	/** 描述 */
	private String describe;
	/** 生产厂商   */
	private String productFactory;
	/** 协议标签列表 */
	private List<TdCtrlDataProtocolTag> ctrlDataProtocolTags = new ArrayList<TdCtrlDataProtocolTag>();

	public short getSyncType() {
		return syncType;
	}

	public void setSyncType(short syncType) {
		this.syncType = syncType;
	}

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	public String getCtrlTypeVersion() {
		return ctrlTypeVersion;
	}

	public void setCtrlTypeVersion(String ctrlTypeVersion) {
		this.ctrlTypeVersion = ctrlTypeVersion;
	}

	public String getProductFactory() {
		return productFactory;
	}

	public void setProductFactory(String productFactory) {
		this.productFactory = productFactory;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<TdCtrlDataProtocolTag> getCtrlDataProtocolTags() {
		return ctrlDataProtocolTags;
	}

	public void setCtrlDataProtocolTags(
			List<TdCtrlDataProtocolTag> ctrlDataProtocolTags) {
		this.ctrlDataProtocolTags = ctrlDataProtocolTags;
	}

	

}
