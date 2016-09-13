package cn.inovance.iotgp.cdsm.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.inovance.iotgp.cdsm.bean.easyui.Grid;
import cn.inovance.iotgp.cdsm.session.TestResultSession;
import cn.inovance.iotgp.common.msg.cdSimulator.bean.LoginTestResult;

@Controller
@RequestMapping("/loginTestResult")
public class LoginTestResultController extends BaseController {

	@RequestMapping("/init")
	public String init(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		logger.info("/loginTestResult/init");
		return "test/loginTestResult";
	}

	@ResponseBody
	@RequestMapping("/list")
	public Grid list(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, Integer pageNumber, Integer pageSize) {
		logger.info("/loginTestResult/list");
		pageNumber = pageNumber > 1 ? pageNumber : 1;
		ArrayList<LoginTestResult> loginTestResults = new ArrayList<LoginTestResult>(
				TestResultSession.loginTestResultList.values());

		ArrayList<LoginTestResult> loginTestResultsNow = new ArrayList<LoginTestResult>();
		int totalCount = loginTestResults.size();
		int start = (pageNumber - 1) * pageSize;
		int count = (totalCount - start) > pageSize ? pageSize : totalCount
				- start;
		for (int i = start; i < count; i++) {
			loginTestResultsNow.add(loginTestResults.get(i));
		}
		return ajaxGrid(loginTestResultsNow,totalCount);
	}
}
