<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备模拟器管理</title>
<jsp:include page="include_base.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		getCdSimulatorList();
	});

	//查询模拟器连接信息
	function getCdSimulatorList() {

		$('#dg').datagrid({
			url : iotgp.contextPath + '/cdSimulator/list', //请求数据的URL
			queryParams : { //查询参数
				Code : "#Code"
			},
			columns : [ [ {
				field : 'clientCode',
				title : '编号',
				width : 250,
				align : 'center'
			}, {
				field : 'remoteAddress',
				title : '远端地址',
				width : 150,
				align : 'center'
			}, {
				field : 'loginedStr',
				title : '登录状态',
				width : 100,
				align : 'center'
			}, {
				field : 'connectTimeStr',
				title : '连接时间',
				width : 150,
				align : 'center'
			}, {
				field : 'lastInteractiveTimeStr',
				title : '最近交互时间',
				width : 150,
				align : 'center'
			}, {
				field : 'connectCount',
				title : '连接设备数',
				width : 100,
				align : 'center'
			}, {
				field : 'onlineCount',
				title : '在线设备数',
				width : 100,
				align : 'center'
			}, {
				field : 'distributeCount',
				title : '<font color="red">【设置分配数量】</font>',
				width : 100,
				align : 'center',
				styler : function() {
					return 'color:red';
				},
				editor : {
					type : 'numberbox',
					options : {
						required : true
					}
				}
			}, {
				field : 'rowmow',
				title : '<font color="red">操作</font>',
				width : 100,
				align : 'center',
				formatter: function(value,row,index){
					return '<button  onclick = "clickfun();">更新</button>';
				}
			} ] ],
			toolbar : [ {
				text : '更新【设置分配数量】',
				iconCls : 'icon-edit',
				handler : updateCdSimulatorList
			} ],
			onClickCell : onClickCell,
			loadMsg : "数据加载中...",
			width : 1150,
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

	//更新模拟器配置信息
	function updateCdSimulatorList() {
		var configListStr = "";
		var Rows = $('#dg').datagrid("getRows");
		for ( var row in Rows) {
			if (Rows[row].clientCode.length <= 0
					|| Rows[row].clientCode == undefined) {
				continue;
			} else {
				configListStr += Rows[row].clientCode + ':'
						+ Rows[row].distributeCount + ';';
			}
		}

		$.post(iotgp.contextPath + '/cdSimulator/update', {
			'configListStr' : configListStr,
		}, function(result) {
			if (!result.success) {
				alert("更新配置失败，请重试! 错误信息：" + result.msg);
			}
		}, 'json');
	};
	function clickfun()
 	{
		var configListStr="";
		var row = $('#dg').datagrid('getSelected');
		if(row !=null){
			configListStr +=row.clientCode +":"+row.distributeCount
		//alert(configListStr);
		}
		$.post(iotgp.contextPath + '/cdSimulator/update', {
			'configListStr' : configListStr + ';',
		}, function(result) {
			if (!result.success) {
				alert("更新配置失败，请重试! 错误信息：" + result.msg);
			}
		}, 'json');
		
	};
	//**************** easy ui cell edit fun start **************************/
	$.extend($.fn.datagrid.methods, {
		editCell : function(jq, param) {
			return jq.each(function() {
				var fields = $(this).datagrid('getColumnFields', true).concat(
						$(this).datagrid('getColumnFields'));
				for ( var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field) {
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for ( var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});

	var editIndex = undefined;
	var editField = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true;
		}
		if ($('#dg').datagrid('validateRow', editIndex)) {
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			editField = undefined;
			return true;
		} else {
			return false;
		}
	}

	function onClickCell(index, field) {
		if (editIndex != index && editField != field) {
			if (endEditing()) {
				$('#dg').datagrid('selectRow', index).datagrid('editCell', {
					index : index,
					field : field
				});
				editIndex = index;
				editField = field;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		} else {
			endEditing();
		}
	}
	function getDeatil(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			//console.log(row);
			//alert(row.clientCode)
			//window.location.href="/cdSimulator/deatil";
			window.open(iotgp.contextPath +"/cdSimulator/deatil?clientCode="+row.clientCode);
		}else{
			alert("请选择一行数据");
		}
		
	}

	//**************** easy ui cell edit fun end **************************/
</script>
</head>
<body>
	<div align="center">
		<br />
		<a href="#" class="easyui-linkbutton" onclick="getDeatil()">查看详细数据</a>
		<table id="dg"></table>
	</div>

	<div align="center" style="margin-top: 200px;">
		<a href="/cdsm/loginTestResult/init" target="blank" >登录测试结果</a><br />
	</div>
	<div align="center" style="margin-top: 200px;">
		<a href="/cdsm/websocketTest/init" target="blank" >websocket测试</a><br />
	</div>
</body>
</html>
