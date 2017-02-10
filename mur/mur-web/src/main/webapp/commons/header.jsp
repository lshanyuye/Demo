<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/easyui/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/easyui/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/easyui/color.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/easyui/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/index.css">
	<style type="text/css">
		.datagrid-btn-separator {
			vertical-align: middle;
			height: 15px;
			display: inline-block;
			float: none;
		}
	</style>
	<script type="text/javascript" src="<%=basePath %>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/dialog.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/platform/biz.js"></script>
	<script type="text/javascript">
	//格式化枚举显示值
	function formatEnum(enumType, enumVal){
		var displayName = "-";
		$.ajax({
			url:"<%=basePath%>/enum/findEnumrate/"+enumType+"/"+enumVal + ".do",
			async: false,
			success:function(data){
				if(data != null){
					displayName = data.displayName;
				}
			}
		});
		return displayName;
	}
	</script>
</html>