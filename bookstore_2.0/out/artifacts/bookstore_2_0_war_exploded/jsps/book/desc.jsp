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
	<style>
		.icon {
			display:block;
			border-radius:10px;
			background-color:rgba(0,0,0,0.1);
			width:250px;
			height:270px;
			padding-top:20px;
			margin:20px;
		}
		.buy {
			display:block;
			border-radius:5px;
			background-color:rgba(0,0,0,0.8);
			width:80px;
			color:white;
		}
		.line {
			height:80px;
			background-color:rgba(0,0,0,0.1);
			border-radius:5px;
		}
		.span {
			vertical-align:-1px;
			display:inline-block;
			width:20px;
			height:22px;
			color:white;
			text-decoration:none;
			background-color:rgba(0,0,0,0.3);
		}
		.span:active {
			background-color:rgba(0,0,0,0.5);
		}

		#ul1 li {
			float:left;
			margin-left: 10px;
			background-color:;
		}
		#comment {
			width: 100%;
			height:500px;
		}
	</style>
	
	<script type="text/javascript">
		function isLogined() {
			/*if(${empty sessionScope.session_user}) {
				alert("请先登录！");
				return false;
			}*/
			document.getElementById('form').submit();
		}
		function subtract() {
			var num = document.getElementById("num");
			if(num.value > 1) {
				num.value--;
			}
		}
		function plus() {
			var num = document.getElementById("num");
			num.value++;
		}
		function switchTo(id1,id2) {
		    document.getElementById(id1);
		    document.getElementById(id2).style.backgroundColor = ""
		}
	</script>
	
  </head>
  
  <body>
  	<div align="center" style="width:100%;padding-top:0px;">
  		<div class="line" style="margin-top:0px;"></div>
	  	<div align="center" style="width:350px;padding-bottom:20px;">
			<div align="center" class="icon">
				<img src="<c:url value='/${book.image }'/>" width="167" height="242" />
			</div>
			<ul  style="margin-left:100px;text-align:left;">
				<li>书名：${book.bname }</li>
				<li>作者：${book.author }</li>
				<li>单价：${book.price }元</li>
			</ul>
			<form id="form" action="<c:url value='/CartServlet'/>" method="post">
				<input type="hidden" name="method" value="add">
				<input type="hidden" name="bid" value="${book.bid }">
				数量：<a class="span" href="javascript:void(0);" onclick="subtract()">-</a><input id="num" type="text" size="3" name="count" value="1"/><a class="span" href="javascript:void(0);" onclick="plus()">+</a>
			</form>
			<a class="buy" href="javascript:void(0);" onclick="isLogined()">购买</a>
		</div>
		<div class="line"></div>
		<div>
			<ul id="ul1" style="list-style-type:none">
				<li>作品简介</li>
				<li>用户评论</li>
			</ul>
			<div id="comment">

			</div>
		</div>
	</div>
  </body>
</html>
