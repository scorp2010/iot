package cn.inovance.iotgp.cdsm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import cn.inovance.iotgp.cdsm.bean.JsonResponse;
import cn.inovance.iotgp.cdsm.bean.easyui.Grid;
import cn.inovance.iotgp.cdsm.constants.AppConstants;
import cn.inovance.iotgp.cdsm.exception.AppException;
import cn.inovance.iotgp.cdsm.exception.BusinessException;
import cn.inovance.iotgp.cdsm.service.ServiceCenter;
import cn.inovance.iotgp.cdsm.util.MessageUtils;

@Controller
@SuppressWarnings("rawtypes")
public class BaseController {
	protected final String ERROR_PAGE_500 = "error/error_500";
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected ServiceCenter sc;
	@Autowired
	protected HttpServletRequest request;

	protected String redirect(String url) {
		return "redirect:" + url;
	}

	protected String forward(String url) {
		return "forward:" + url;
	}

	protected Grid ajaxGrid(List rows) {
		Grid grid = new Grid();
		grid.setRows(rows);
		if (rows != null)
			grid.setTotal(rows.size());
		return grid;
	}

	protected Grid ajaxGrid(List rows, Integer totalCount) {
		Grid grid = new Grid();
		grid.setRows(rows);
		grid.setTotal(totalCount);
		return grid;
	}

	protected JsonResponse ajaxSuccess() {
		return ajaxSuccess(null);
	}

	protected JsonResponse ajaxSuccess(Object data) {
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);
		response.setData(data);

		return response;
	}

	protected JsonResponse ajaxSuccess(Object data, String msg) {
		JsonResponse response = new JsonResponse();
		response.setSuccess(true);
		response.setData(data);
		response.setMsg(msg);
		return response;
	}

	protected JsonResponse ajaxFailure() {
		return ajaxFailure(null);
	}

	protected JsonResponse ajaxFailure(String msg) {
		JsonResponse response = new JsonResponse();
		response.setSuccess(false);
		response.setMsg(msg);

		return response;
	}

	@ExceptionHandler({ Exception.class })
	protected ModelAndView handleException(HttpServletRequest request,
			Exception ex) {
		JsonResponse response = new JsonResponse();
		response.setSuccess(false);

		if (ex instanceof BusinessException) {
			response.setMsg(ex.getMessage());
		} else if (ex instanceof AppException) {
			response.setMsg(ex.getMessage());
			logger.error(ex.getMessage(), ex);
		} else {
			response.setMsg(MessageUtils
					.getMessage(AppConstants.ERROR_CODE_E1SP0001));
			logger.error(ex.getMessage(), ex);
		}

		return new ModelAndView(ERROR_PAGE_500).addObject(
				AppConstants.EXCEPTION_KEY, response).addObject(
				"exceptionDebug", ex);
	}
}
