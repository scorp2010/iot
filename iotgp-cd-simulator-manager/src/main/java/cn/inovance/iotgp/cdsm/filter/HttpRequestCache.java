package cn.inovance.iotgp.cdsm.filter;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestCache {
	private static ThreadLocal<HttpServletRequest> requestCache = new ThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) requestCache.get();
		return request;
	}

	public static void setRequest(HttpServletRequest request) {
		requestCache.set(request);
	}
}
