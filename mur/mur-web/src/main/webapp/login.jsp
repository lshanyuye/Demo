<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<body>
	<form action="<%=basePath %>/login.do" method="post">
		用户名:<input type="text" name="code"/></br>
		密码:<input type="password" name="password">
		<input type="submit" value="登录">
	</form>
	<P><c:out value="${msg}" /></P>  
</body>
</html>
