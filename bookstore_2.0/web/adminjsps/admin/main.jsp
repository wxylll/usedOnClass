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
    
    <title>main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		body{
		}
		#i1 {
			background-color:rgba(0,0,0,0.2);
			border-radius:0 0 0 10px;
		}
		#i2 {
			background-color:rgba(255,255,255,1);
			border-radius:0 0 10px 0;
		}
	</style>

  </head>
  
  <body style="background-color:rgba(0,0,0,0.1);">
	<table align="center" style="margin-top:20px;border-radius:10px;" cellspacing = "0" width="1500px" height="840px" >
		<tr style="background:rgba(0,0,0,0.8); height:120px;">
			<td colspan="2" align="center" style="border-radius:10px 10px 0 0;">
				<iframe width="100%" height="100%" scrolling="no" frameborder="0" src="<c:url value='/adminjsps/admin/atop.jsp'/>" name="atop"></iframe>
			</td>
		</tr>
		<tr>
			<td id="i1" width="120" style="padding:5px;" align="center" valign="top">
				<iframe frameborder="0" width="150" height="100%" src="<c:url value='/adminjsps/admin/aleft.jsp'/>" name="aleft"></iframe>
			</td>
			<td id="i2">
				<iframe frameborder="0" width="100%" height="100%" src="<c:url value='/adminjsps/admin/ahello.jsp'/>" name="abody"></iframe>
			</td>
		</tr>
	</table>	
  </body>
  
</html>
