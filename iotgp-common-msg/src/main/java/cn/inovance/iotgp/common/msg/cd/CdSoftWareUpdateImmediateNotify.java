/**
 * Project Name:iotgp-common-msg
 * File Name:CdSoftWareUpdateImmediateNotify.java
 * Package Name:cn.inovance.iotgp.common.msg.cd
 * Date:2014-7-23下午7:40:53
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.msg.cd;

import cn.inovance.iotgp.common.msg.cd.meta.ShortPdu;
import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

/**
 * ClassName:CdSoftWareUpdateImmediateNotify <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-23 下午7:40:53 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CdSoftWareUpdateImmediateNotify extends AbstractReqPackage {

	/** id 长度.*/
	private ShortPdu idLength;
	/** 更新job id. */
	private StringPdu jobId ;
	
	
	/** 更新job 类型 1 采集 2 用户程序. */
	private ShortPdu softType ;
	
	
	public CdSoftWareUpdateImmediateNotify(){
		this.header.setMsgType(Commands.CD_UPDATE_SOFTWARE_NOTIFY);
	}
	public CdSoftWareUpdateImmediateNotify(byte[] data){
		super(data);
	}
	
	public ShortPdu getIdLength() {
		return idLength;
	}
	public void setIdLength(ShortPdu idLength) {
		this.idLength = idLength;
	}

	public StringPdu getJobId() {
		return jobId;
	}

	public void setJobId(StringPdu jobId) {
		this.jobId = jobId;
		this.idLength = new ShortPdu((short)this.jobId.getLength());
	}

	public void setJobId(String jobId) {
		this.jobId = new StringPdu(jobId);
		this.idLength = new ShortPdu((short)this.jobId.getLength());
	}
	public ShortPdu getSoftType() {
		return softType;
	}
	
	public void setSoftType(ShortPdu softType) {
		this.softType = softType;
	}
	public void setSoftType(short softType) {
		this.softType = new ShortPdu(softType);
	}
	@Override
	protected void constructBody(byte[] data) {
		ByteOps.addByteArray(data, idLength.getBytes(), index);
		index += idLength.getLength();
		
		ByteOps.addByteArray(data, jobId.getBytes(), index);
		index += jobId.getLength();
		
		ByteOps.addByteArray(data, softType.getBytes(), index);
		index += softType.getLength();
	}

	@Override
	protected void parseBody() throws MessageParseException {
		
		idLength = new ShortPdu(index, data);
		index += idLength.getLength();
		jobId = new StringPdu(idLength.getValue(), index, data);
		index += jobId.getLength();
		softType = new ShortPdu(index, data);
		index += softType.getLength();
	}

	@Override
	public String toString() {
		return "CdSoftWareUpdateImmediateNotify [idLength=" + idLength
				+ ", jobId=" + jobId + ", softType=" + softType + "]";
	}
	@Override
	protected int getMsgLength() {
		
		return Header.LENGTH_HEADER + idLength.getLength() + jobId.getLength()
				+ softType.getLength();
	}
	
	public static void main(String[] args) {

		CdSoftWareUpdateImmediateNotify req = new CdSoftWareUpdateImmediateNotify();
		req.setJobId("1ersdsadsadsadas");
		req.setSoftType((short)1);
		req.construct();
		System.out.println(ByteOps.bytesToHexString(req.getData()));
		CdSoftWareUpdateImmediateNotify req1 = new CdSoftWareUpdateImmediateNotify(req.getData());
		System.out.println(req.getData().length);
		try {
			req1.parse();
		} catch (MessageParseException e) {
			e.printStackTrace();
		}
		System.out.println(req1.toString());
	}
}

