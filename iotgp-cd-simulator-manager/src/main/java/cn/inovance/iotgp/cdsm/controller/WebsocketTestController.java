package cn.inovance.iotgp.cdsm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocketTest")
public class WebsocketTestController extends BaseController {
	
	@RequestMapping("/init")
	public String init(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		logger.info("/websocketTest/init");
		return "test/websocketTest";
	}
}
