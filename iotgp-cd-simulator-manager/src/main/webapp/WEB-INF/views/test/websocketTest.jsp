<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>websocket测试</title>
<jsp:include page="../include_base.jsp"></jsp:include>
<jsp:include page="../include_ws.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		Chat.initialize();
	});

	/** obj2json*/
	var O2String = function(O) {
		var S = [];
		var J = "";
		if (Object.prototype.toString.apply(O) === '[object Array]') {
			for ( var i = 0; i < O.length; i++)
				S.push(O2String(O[i]));
			J = '[' + S.join(',') + ']';
		} else if (Object.prototype.toString.apply(O) === '[object Date]') {
			J = "new Date(" + O.getTime() + ")";
		} else if (Object.prototype.toString.apply(O) === '[object RegExp]'
				|| Object.prototype.toString.apply(O) === '[object Function]') {
			J = O.toString();
		} else if (Object.prototype.toString.apply(O) === '[object Object]') {
			for ( var i in O) {
				if (O[i] == null)
					continue;
				O[i] = typeof (O[i]) == 'string' ? '"' + O[i] + '"'
						: (typeof (O[i]) === 'object' ? O2String(O[i]) : O[i]);
				S.push('"' + i + '"' + ':' + O[i]);
			}
			J = '{' + S.join(',') + '}';
		} else {
			return O;
		}

		return J;
	};

	function isEmpty(s) {
		return ((s == undefined || s == null || s == "") ? true : false);
	}

	/** web socket*/
	WEB_SOCKET_SWF_LOCATION = iotgp.jsPath + '/websocket/WebSocketMain.swf';
	WEB_SOCKET_DEBUG = true;
	var Chat = {};
	Chat.socket = null;
	Chat.hasconnect = false;
	Chat.connect = (function(host) {
		Chat.socket = new WebSocket(host);

		Chat.socket.onopen = function() {
			Chat.hasconnect = true;
			console.log('Info: WebSocket opened.');
			//sendLoginTestReq();
		};

		Chat.socket.onclose = function() {
			Chat.hasconnect = false;
			console.log('Info: WebSocket closed.');
		};

		Chat.socket.onmessage = function(message) {
			console.log("receive:" + message.data);
		};
	});

	Chat.initialize = function() {
		if (window.location.protocol == 'http:') {
			Chat.connect('ws://127.0.0.1:7616');
		} else {
			Chat.connect('wss://127.0.0.1:7616');
		}
	};

	Chat.sendMessage = function(msg) {
		if (msg != '') {
			Chat.socket.send(msg);
		}
	};

	Chat.close = function() {
		if (Chat.hasconnect) {
			Chat.socket.close();
		}
	};

	var message = function() {
		this.msgType = "GET_LOGIN_TEST_RESULT_REQ";
	};

	//发送更新登录测试结果请求
	var sendLoginTestReq = function() {
		if (!Chat.hasconnect) {
			alert("websocket不在连接状态，请刷新页面重试");
			return;
		}

		var req = new message();
		req.msgType = "GET_LOGIN_TEST_RESULT_REQ";
		Chat.sendMessage(O2String(req));
	};
</script>
</head>
<body>
	<div align="center">
		<br /> <br /> <a href="#" onclick="sendLoginTestReq()">sendLoginTestReq</a>
	</div>
</body>
</html>
