<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备模拟器登录测试</title>
<jsp:include page="../include_base.jsp"></jsp:include>
<jsp:include page="../include_ws.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		Chat.initialize();
		getLoginTestResultList();
	});

	//查询模拟器登录测试结果
	function getLoginTestResultList() {

		$('#dg').datagrid({
			url : iotgp.contextPath + '/loginTestResult/list', //请求数据的URL
			queryParams : { //查询参数
				Code : "#Code",
				pageNumber : 1,
				pageSize : 100
			},
			columns : [ [ {
				field : 'regCode',
				title : '注册码',
				width : 250,
				align : 'center'
			}, {
				field : 'logined',
				title : '登录标识',
				width : 100,
				align : 'center',
				formatter : function(val, rec) {
					if (val == true) {
						return '<span style="color:green;">在线</span>';
					} else {
						return '<span style="color:red;">离线</span>';
					}
				}
			}, {
				field : 'startLonginTime',
				title : '登录开始时间',
				width : 200,
				align : 'center',
				formatter : function(val, rec) {
					return formatDate(val);
				}
			}, {
				field : 'endLoginTime',
				title : '登录结束时间',
				width : 200,
				align : 'center',
				formatter : function(val, rec) {
					return formatDate(val);
				}
			}, {
				field : 'loginTimeSpan',
				title : '登录时长（毫秒）',
				width : 200,
				align : 'center',
				formatter : function(val, rec) {
					return rec.endLoginTime - rec.startLonginTime;
				}
			} ] ],
			toolbar : [ {
				text : '更新测试结果',
				iconCls : 'icon-search',
				handler : updateLoginTestResultList
			} ],
			loadMsg : "数据加载中...",
			width : 1050,
			striped : true,
			pagination : true,
			rownumbers : true,
			singleSelect : true,
			pageNumber : 1,
			pageSize : 100,
			pageList : [ 100, 200, 500 ],
			showFooter : true
		});
	}

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

	var message = function() {
		this.msgType = "GET_LOGIN_TEST_RESULT_REQ";
	};

	//发送更新登录测试结果请求
	var sendLoginTestReq = function() {
		var req = new message();
		req.msgType = "GET_LOGIN_TEST_RESULT_REQ";
		Chat.sendMessage(O2String(req));
	};

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
		};

		Chat.socket.onclose = function() {
			Chat.hasconnect = false;
			console.log('Info: WebSocket closed.');
		};

		Chat.socket.onmessage = function(message) {
			console.log(message.data);
			getLoginTestResultList(); //更新列表
		};
	});

	Chat.initialize = function() {
		if (window.location.protocol == 'http:') {
			Chat.connect('ws://' + window.location.host + '/cdsm/websocket');
		} else {
			Chat.connect('wss://' + window.location.host + '/cdsm/websocket');
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

	//更新登录测试结果信息
	function updateLoginTestResultList() {
		if (!Chat.hasconnect) {
			alert("websocket不在连接状态，请刷新页面重试");
			return;
		}
		sendLoginTestReq();
	};

	function formatDate(now) {
		var date = new Date(now), year = date.getFullYear(), month = date
				.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date
				.getMonth() + 1, day = date.getDate() < 10 ? "0"
				+ date.getDate() : date.getDate(), hour = date.getHours() < 10 ? "0"
				+ date.getHours()
				: date.getHours(), minutes = date.getMinutes() < 10 ? "0"
				+ date.getMinutes() : date.getMinutes(), seconds = date
				.getSeconds() < 10 ? "0" + date.getSeconds() : date
				.getSeconds();
		millSeconds = date.getMilliseconds() < 10 ? "0"
				+ date.getMilliseconds() : date.getMilliseconds();

		return year + "-" + month + "-" + day + " " + hour + ":" + minutes
				+ ":" + seconds + "." + millSeconds;
	};
</script>
</head>
<body>
	<div align="center">
		<br />
		<table id="dg"></table>
	</div>
</body>
</html>
