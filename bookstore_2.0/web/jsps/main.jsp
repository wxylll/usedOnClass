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
		body,table,tr {
			margin: 0px;
			padding: 0px;
		}
		#i1 {
			background-color:rgba(255,255,255,1);
			border-radius:0 0 0 10px;
            border: rgb(186,1,1) 5px solid;
            border-top: 0px;
		}
		#i2 {
			background-color:#ffff;
			border-radius:0 0 10px 0;
		}
	</style>

  </head>
  
  <body style="background-color:rgba(0,0,0,0.1);">
	<table align="center" cellspacing = "0" width="100%" height="10000px">
		<tr style="background:rgba(0,0,0,0.8); height:120px;">
			<td colspan="2" align="center">
				<iframe width="100%" height="250px" frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
			</td>
		</tr>
		<tr>
			<td id="i1" width="250" style="padding:0px;" align="center" valign="top">
				<iframe frameborder="0" width="250"  height="100%" src="<c:url value='/CategoryServlet?method=findAll'/>" name="left"></iframe>
			</td>
			<td id="i2">
				<iframe frameborder="0" width="100%" height="100%" src="<c:url value='/jsps/hello.jsp'/>" name="body"></iframe>
			</td>
		</tr>
	</table>	
  </body>
  
</html>
