<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<title>页面不存在</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<!-- CSS -->
<link rel="stylesheet" href="${ctx}/resources/css/error.css" />
</head>
<body class="errorpage">
	<div id="wrap">
		<div class="error_bg">
			<a href="#" onclick="window.location.href = '${ctx}/index.jsp';">返回首页</a>
		</div>
	</div>
</body>
</html>