<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.block {
			border-radius:10px;
			background-color:rgba(0,0,0,0.1);
			width:500px;
			height:300px;
		}
	</style>

  </head>
  
  <body>
  	<div align="center">
	  	<div class="block" align="center">
		  	<form action="<c:url value='/UserServlet'/>" method="post">
		  		<input type="hidden" name="method" value="regist"/>
			  	<table align="center">
			  		<tr><th align="center" colspan="2"><h1>用户注册</h1></th></tr>
			  		<tr><td colspan="2" align="center" style="padding-bottom:10px;color:red;">${msg }<p id="p1"></p></td></tr>
			  		<tr>
			  			<td>用户名:</td><td><input type="text" value="${form.username}" name="username"/></td><td><span id="s1" style="color:red;">${errors.username }</span></td>
			  		</tr>
			   		<tr>
			  			<td>密码:</td><td><input type="password" value="${form.password}" name="password"/></td><td><span id="s2" style="color:red;">${errors.password }</span></td>
			  		</tr>
			   		<tr>
			  			<td>邮箱:</td><td><input type="text" value="${form.email}" name="email"/></td><td><span id="s3" style="color:red;">${errors.email }</span></td>
			  		</tr>
			  		<tr><td></td><td><input type="submit" value="注册"/></td></tr>
			  	</table>
		  	</form>
	  	</div>
  	</div>
  </body>
</html>
