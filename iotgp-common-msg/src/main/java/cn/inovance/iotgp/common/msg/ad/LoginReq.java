package cn.inovance.iotgp.common.msg.ad;

import cn.inovance.iotgp.common.msg.cd.meta.StringPdu;
import cn.inovance.iotgp.common.msg.exception.MessageParseException;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class LoginReq extends AbstractReqPackage {
	public final int PACKAGE_LENGTH=94;
	
	protected final int INDEX_ENCRYPT_CODE=46;
	protected final int INDEX_ELEV_EQUIP_NO=78;
	
	protected final int LENGTH_ENCRYPT_CODE=32;
	
	protected final int LENGTH_ELEV_EQUIP_NO=16;
	
	private StringPdu encryptCode=new StringPdu();
	
	private StringPdu elevEquipNo = new StringPdu();
	
	public String getElevEquipNo() {
		return elevEquipNo.getValue();
	}

	public void setElevEquipNo(String elevEquipNo) {
		this.elevEquipNo = new StringPdu(LENGTH_ELEV_EQUIP_NO, elevEquipNo);
	}

	public String getEncryptCode() {
		return encryptCode.getValue();
	}

	public void setEncryptCode(String encryptCode) {
		this.encryptCode = new StringPdu(LENGTH_ENCRYPT_CODE, encryptCode);
	}

	public LoginReq(){
		data = new byte[PACKAGE_LENGTH];
		header = new Header(data, Commands.LOGIN_REQ);
	}
	
	public LoginReq(byte[] data){
		this.data = data;
		header.setData(data);
	}
	@Override
	public void constructBody(byte[] data) {
		ByteOps.addByteArray(data, encryptCode.getBytes(), INDEX_ENCRYPT_CODE);
		ByteOps.addByteArray(data, elevEquipNo.getBytes(), INDEX_ELEV_EQUIP_NO);
	}

	@Override
	public void parseBody() throws MessageParseException {
		encryptCode = new StringPdu(LENGTH_ENCRYPT_CODE, this.INDEX_ENCRYPT_CODE, data);
		if (data.length == PACKAGE_LENGTH)
			elevEquipNo = new StringPdu(LENGTH_ELEV_EQUIP_NO, this.INDEX_ELEV_EQUIP_NO, data);
	}

	

	@Override
	public String toString() {
		return "LoginReq [encryptCode=" + encryptCode + ", elevEquipNo="
				+ elevEquipNo + ", header=" + header + "]";
	}

	public static void main(String[] args) throws MessageParseException{
		LoginReq req = new LoginReq();
		req.setEncryptCode("123123123");
		req.setElevEquipNo("1111111111111111");
		req.construct();
		System.out.println(ByteOps.bytes2HexStringWithBlank(req.getData()));
	}
}
