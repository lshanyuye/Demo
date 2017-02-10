<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title></title>
	
	<link id="bs-css" href="<%=basePath %>/master/css/bootstrap-cerulean.css" rel="stylesheet">
	<link href="<%=basePath %>/master/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="<%=basePath %>/master/css/charisma-app.css" rel="stylesheet">
	<link href="<%=basePath %>/master/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='<%=basePath %>/master/css/fullcalendar.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='<%=basePath %>/master/css/chosen.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/uniform.default.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/colorbox.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/jquery.noty.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/noty_theme_default.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/elfinder.min.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/elfinder.theme.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/opa-icons.css' rel='stylesheet'>
	<link href='<%=basePath %>/master/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="<%=basePath %>/master/img/favicon.ico">
	
	<!-- jQuery -->
	<script src="<%=basePath %>/master/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="<%=basePath %>/master/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="<%=basePath %>/master/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="<%=basePath %>/master/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="<%=basePath %>/master/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="<%=basePath %>/master/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="<%=basePath %>/master/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="<%=basePath %>/master/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="<%=basePath %>/master/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="<%=basePath %>/master/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="<%=basePath %>/master/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="<%=basePath %>/master/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="<%=basePath %>/master/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="<%=basePath %>/master/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="<%=basePath %>/master/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="<%=basePath %>/master/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='<%=basePath %>/master/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=basePath %>/master/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="<%=basePath %>/master/js/excanvas.js"></script>
	<script src="<%=basePath %>/master/js/jquery.flot.min.js"></script>
	<script src="<%=basePath %>/master/js/jquery.flot.pie.min.js"></script>
	<script src="<%=basePath %>/master/js/jquery.flot.stack.js"></script>
	<script src="<%=basePath %>/master/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="<%=basePath %>/master/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="<%=basePath %>/master/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="<%=basePath %>/master/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="<%=basePath %>/master/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="<%=basePath %>/master/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="<%=basePath %>/master/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=basePath %>/master/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=basePath %>/master/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=basePath %>/master/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=basePath %>/master/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=basePath %>/master/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=basePath %>/master/js/charisma.js"></script>
	
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