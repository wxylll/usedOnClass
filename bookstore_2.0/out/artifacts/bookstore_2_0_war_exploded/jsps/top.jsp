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
			background-color: rgb(186,1,1);
		}
	</style>

  </head> 
  
  <body>
  	<table align="center" width="100%">
  		<tr align="center">
  			<td style="color:white;font-size: 20px"><h1 style="font-size: 80px">悦阅网上书城</h1></td>
  		</tr>
  		<tr>
  			<c:choose>
  				<c:when test="${empty sessionScope.session_user}">
  					<td><a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a></td>
  				</c:when>
  				<c:otherwise>
  					<td>
  						<span>您好:&nbsp;&nbsp;${sessionScope.session_user.username}</span>&nbsp;&nbsp;|&nbsp;&nbsp;
  						<a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">我的购物车</a>&nbsp;&nbsp;
  						<a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;
  						<a href="<c:url value='/UserServlet?method=quit'/>" onclick="javascript:return confirm('您确定要退出吗？')" target="_parent">退出</a>
  					</td>
  				</c:otherwise>
  			</c:choose>
  			
  		</tr>
  	</table>
  
  </body>
</html>
