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
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.icon {
			display:block;
			border-radius:10px;
			background-color:rgba(0,0,0,0.1);
			width:250px;
			height:290px;
			padding-top:20px;
			margin:20px;
			float:left;
		}
		a {
			color:black;
			text-decoration:none;
			font-family: "微软雅黑"
		}
	</style>

  </head>
  
  <body style="padding-left:50px;">
	<c:forEach items="${bookList }" var="book">
		<div align="center" class="icon">
			
			<a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid }'/>"><img width="167" height="242" src="<c:url value='/${book.image }'/>"/></a>
			<br/>
			<a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid }'/>">${book.bname }</a>
		</div>
	</c:forEach>
  </body>
</html>
