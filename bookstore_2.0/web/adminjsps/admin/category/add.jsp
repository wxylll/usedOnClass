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
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.box {
			width:400px;
			height:50px;
			padding-top:10px;
			padding-bottom:20px;
			background-color:rgba(0,0,0,0.1);
			border-radius:10px;
		}
		span {
			width:50px;
			background-color:rgba(0,0,0,0.4);
			border-radius:5px 0 0 5px;
			vertical-align:-1.1px;
			color:white;
		}
		h4 {
			display:inline-block;
		}
		a {
			width:100px;
			height:24px;
			display:inline-block;
			background-color:rgba(0,0,0,0.1);
			border-radius:0 5px 5px 0;
			color:green;
		}
	</style>

  </head>
  
  <body>
  	<div align="center" style="padding-top:30px;">
  		<h3>添加分类</h3>
  		<form id="form" action="<c:url value='/admin/AdminCategoryServlet'/>" method="post">
  			<input type="hidden" name="method" value="add"/>
  			<div class="box"><h4><span>&nbsp;分类名称：</span></h4><input name="cname"/><a href="javascript:document.getElementById('form').submit()">添加分类</a></div>
  		</form>
  	</div>
  </body>
</html>
