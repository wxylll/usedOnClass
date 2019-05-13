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
		table {
			text-align:center;
			padding-bottom:20px;
		}
		#sub {
			background:#d3d7d4;
		}
		.t1 {
			background-color:rgba(0,0,0,0.1);
			border:5px solid white;
			border-radius:10px;
		}
		.t2 {
			background-color:rgba(0,0,0,0.02);
			border:5px solid white;
			border-radius:10px;
		}
	</style>

  </head>
  
  <body>
  	<div align="center" style="padding-top:20px;">
		<table cellspacing="0" style="width:80%;">
			<tr><td colspan="4" style="background-color:#0c212b;color:white;">全部分类</td></tr>
			<tr id="sub"><th style="background-color:rgba(0,0,0,0.2);">分类名称</th><th colspan="2" style="background-color:rgba(0,0,0,0.1);">操作</th></tr>
			<c:forEach items="${categoryList }" var="category">
				<tr>
					<td class="t1">${category.cname }</td>
					<td class="t2"><a href="<c:url value='/admin/AdminCategoryServlet?method=editPre&cid=${category.cid }'/>" style="color:green;">修改</a></td>
					<td class="t1"><a href="<c:url value='/admin/AdminCategoryServlet?method=delete&cid=${category.cid }'/>" style="color:red;" onclick="return confirm('您确定要删除此分类吗？')">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
