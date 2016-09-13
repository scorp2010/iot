package cn.inovance.iotgp.common.msg.cdSimulator;

import java.util.List;

import cn.inovance.iotgp.common.msg.cdSimulator.bean.LoginTestResult;
import cn.inovance.iotgp.common.msg.util.SeqGenerator;

public class LoginTestResultRsp extends Response {

	public LoginTestResultRsp() {
		this(SeqGenerator.next());
	}

	public LoginTestResultRsp(long msgSeq) {
		super(msgSeq);
		this.msgType = MsgType.LOGIN_TEST_RESULT_RSP;
	}

	public LoginTestResultRsp(LoginTestResultReq req) {
		super(req);
		this.msgType = MsgType.LOGIN_TEST_RESULT_RSP;
	}

	private List<LoginTestResult> loginTestResults;

	public List<LoginTestResult> getLoginTestResults() {
		return loginTestResults;
	}

	public void setLoginTestResults(List<LoginTestResult> loginTestResults) {
		this.loginTestResults = loginTestResults;
	}

}
