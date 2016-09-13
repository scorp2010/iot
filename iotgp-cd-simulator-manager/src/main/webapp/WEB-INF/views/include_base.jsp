<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
String contextPath = request.getContextPath();
String jsPath =contextPath+"/resources/js";
String version = "20141118";
%>

<%
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "metro-blue";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>

<script type="text/javascript">
var iotgp = iotgp || {};
iotgp.contextPath = '<%=contextPath%>';
iotgp.basePath = '<%=basePath%>';
iotgp.jsPath = '<%=jsPath%>';
iotgp.version = '<%=version%>';
iotgp.pixel_0 = '<%=contextPath%>/images/pixel_0.gif';//0像素的背景，一般用于占位
</script>

<%-- jquery --%>
<%
String User_Agent = request.getHeader("User-Agent");
if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE") > -1 && (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1 || StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 7") > -1 || StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 8") > -1)) {
	out.println("<script src='" + jsPath + "/jquery-1.9.1.js' type='text/javascript' charset='utf-8'></script>");
} else {
	out.println("<script src='" + jsPath + "/jquery-2.1.0.js' type='text/javascript' charset='utf-8'></script>");
}
%>

<%-- easyui --%>
<link id="easyuiTheme" rel="stylesheet" href="<%=jsPath%>/jquery-easyui-1.3.4/themes/<%=easyuiTheme%>/easyui.css" type="text/css">
<link rel="stylesheet" href="<%=jsPath%>/jquery-easyui-1.3.4/themes/icon.css" type="text/css">
<script type="text/javascript" src="<%=jsPath%>/jquery-easyui-1.3.4/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery-easyui-1.3.4/datagrid-bufferview.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery-easyui-1.3.4/datagrid-detailview.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
