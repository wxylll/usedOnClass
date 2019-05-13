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
	<script type="text/javascript">
		function setMethod(method,message) {
			var _form = document.getElementById("form");
			var _input = document.getElementById("met");
			if(confirm(message)) {
				_input.value = method;
				_form.submit();
			}
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
		.icon {
			display:block;
			border-radius:10px;
			background-color:rgba(0,0,0,0.1);
			width:250px;
			height:270px;
			padding-top:20px;
			margin:20px;
		}
		.line {
			height:80px;
			background-color:rgba(0,0,0,0.1);
			border-radius:5px;
		}
		.item {
			margin-bottom:10px;
		}
	</style>

  </head>
  
  <body>
  	<div align="center" style="width:100%;padding-top:0px;">
  		<div class="line" style="margin-top:0px;"></div>
	  	<div align="center" style="width:350px;padding-bottom:20px;">
	  		<form id="form" action="">
	  			<input id="met" type="hidden" name="method" value=""/>
				<div align="center" class="icon">
					<img src="<c:url value='/${book.image }'/>" width="167" height="242" />
					<input type="hidden" name="image" value="${book.image }"/>
					<input type="hidden" name="bid" value="${book.bid }"/>
				</div>
				<div>
					<div class="item">图书名称：<input type="text" name="bname" value="${book.bname }"/></div>
					<div class="item">图书单价：<input type="text" name="price" value="${book.price }"/></div>
					<div class="item">图书作者：<input type="text" name="author" value="${book.author }"/></div>
					<div class="item">图书分类：<select name="cid" style="width:153px;">
													<c:forEach items="${categoryList }" var="category">
														<option value="${category.cid }" <c:if test="${category.cid eq book.category.cid }">selected="selected"</c:if>>${category.cname }</option>
													</c:forEach>
												</select>
					</div>
				</div>
			</form>
			<div><a style="color:red;" onclick="setMethod('delete','您确定要删除该图书吗？')" >删除</a><a style="color:green;" onclick="setMethod('edit','您确定要修改该图书吗？')">修改</a></div>
		</div>
		<div class="line"></div>
	</div>
  </body>
</html>
