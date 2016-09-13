<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
String contextPath = request.getContextPath();
String jsPath =contextPath+"/resources/js";
String version = "20141118";
%>

<%-- websocket --%>
<script type="text/javascript" src="<%=jsPath%>/websocket/swfobject.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=jsPath%>/websocket/web_socket.js" charset="utf-8"></script>
