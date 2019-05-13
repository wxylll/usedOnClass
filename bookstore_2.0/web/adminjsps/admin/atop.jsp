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
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		a {
			color:white;
		}
		body {
			color:white;
		}
	</style>

  </head> 
  
  <body>
  	<table align="center" width="100%">
  		<tr align="center">
  			<td style="color:white;"><h1>墨玉书城后台管理系统</h1></td>
  		</tr>
  		<tr>
  			<td><span style="font-family: 微软雅黑"><h3>您好:&nbsp;&nbsp;${sessionScope.session_admin}先生&nbsp;&nbsp;</h3></span></td>
  		</tr>
  	</table>
  
  </body>
</html>
