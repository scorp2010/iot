package cn.inovance.iotgp.cdsm.exception.handler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import cn.inovance.iotgp.cdsm.constants.AppConstants;

public class AnnotationExceptionHandlerExceptionResolver extends
		ExceptionHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveHandlerMethodException(
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception) {
		// 调用父类处理
		ModelAndView returnValue = super.doResolveHandlerMethodException(
				request, response, handlerMethod, exception);
		if (returnValue != null) {
			ResponseBody responseBodyAnn = handlerMethod
					.getMethodAnnotation(ResponseBody.class);
			if (responseBodyAnn != null) {
				// 如果加有ResponseBody注解，输出JSON
				try {
					return handleResponseBody(returnValue, request, response);
				} catch (Exception ex) {
					if (logger.isWarnEnabled()) {
						logger.warn("Could not converte json:", ex);
					}
					return null;
				}
			}
		}
		return returnValue;
	}

	/**
	 * Copy from AnnotationMethodHandlerExceptionResolver and modify parameters
	 * 
	 * @param returnValue
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @see org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	private ModelAndView handleResponseBody(ModelAndView returnValue,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object value = returnValue.getModel().get(AppConstants.EXCEPTION_KEY);
		HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders()
				.getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}
		MediaType.sortByQualityValue(acceptedMediaTypes);
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(
				response);
		Class<?> returnValueType = value.getClass();
		if (this.getMessageConverters() != null) {
			for (MediaType acceptedMediaType : acceptedMediaTypes) {
				for (HttpMessageConverter messageConverter : this
						.getMessageConverters()) {
					if (messageConverter.canWrite(returnValueType,
							acceptedMediaType)) {
						messageConverter.write(value, acceptedMediaType,
								outputMessage);
						return new ModelAndView();
					}
				}
			}
		}
		if (logger.isWarnEnabled()) {
			logger.warn("Could not find HttpMessageConverter that supports return type ["
					+ returnValueType + "] and " + acceptedMediaTypes);
		}
		return null;
	}
}
