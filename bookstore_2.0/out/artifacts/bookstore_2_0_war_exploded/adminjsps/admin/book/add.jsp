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
    
    <title>My JSP 'desc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function sure() {
			if(confirm('您确定要添加此图书吗？'))
				document.getElementById('form').submit();
		}
	</script>
	<style>
		a {
			display:inline-block;
			width:100px;
			background-color:rgba(0,0,0,0.3);
			border-radius:5px;
			color:white;
			text-decoration:none;
			margin:5px 20px 0 20px;
		}
		a:hover {
			background-color:rgba(0,0,0,0.5);
			cursor: pointer;
		}
		.line {
			height:80px;
			background-color:rgba(0,0,0,0.1);
			border-radius:5px;
		}
		.item {
			margin-bottom:10px;
		}
		.file {
			width:152px;
		}
	</style>

  </head>
  
  <body>
  	<div align="center" style="width:100%;padding-top:0px;">
  		<div class="line" style="margin-top:0px;"></div>
  		<div><h3>添加图书</h3></div>
  		<div><h5 style="color:red;">${msg }</h5></div>
	  	<div align="center" style="width:370px;padding-bottom:20px;">
	  		<form id="form" action="<c:url value='/admin/AdminAddBookServlet'/>" method="post" enctype="multipart/form-data">
				<div>
					<div class="item">图书名称：<input type="text" name="bname" value=""/></div>
					<div class="item">图书图片：<input class="file" type="file" name="image" value=""/></div>
					<div class="item">图书单价：<input type="text" name="price" value=""/></div>
					<div class="item">图书作者：<input type="text" name="author" value=""/></div>
					<div class="item">图书分类：<select name="cid" style="width:153px;">
													<c:forEach items="${categoryList }" var="category">
														<option value="${category.cid }">${category.cname }</option>
													</c:forEach>
												</select>
					</div>
				</div>
			</form>
			<div><a onclick="sure()">添加图书</a></div>
		</div>
		<div class="line"></div>
	</div>
  </body>
</html>

