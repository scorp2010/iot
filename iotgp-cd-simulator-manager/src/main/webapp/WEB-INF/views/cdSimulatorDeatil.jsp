<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>模拟器详情</title>
<jsp:include page="include_base.jsp"></jsp:include>
<script type="text/javascript">
	var requestParams = new Object();
	$(document).ready(function() {
		requestParams = GetRequest();
		//console.log(requestParams);
		getCdSimulatorList();
	});


	function GetRequest() {

		var url = location.search; //获取url中"?"符后的字串
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for ( var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
	
	//查询模拟器连接信息
	function getCdSimulatorList() {

		$('#dg').datagrid({
			url : iotgp.contextPath + '/cdSimulator/deviceInfo', //请求数据的URL
			queryParams : { //查询参数
				clientCode :requestParams.clientCode
			},
			columns : [ [ {
				field : 'regCode',
				title : '设备注册码',
				width : 250,
				align : 'center'
			}, {
				field : 'reconnectCount',
				title : '重连次数',
				width : 150,
				align : 'center'
			}
			 ] ],
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

	//**************** easy ui cell edit fun end **************************/
</script>
</head>
<body>
	<div align="center">
		<br />
		<table id="dg"></table>
	</div>
</body>
</html>
