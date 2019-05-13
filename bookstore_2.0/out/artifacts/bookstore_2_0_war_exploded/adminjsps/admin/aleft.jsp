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
    
    <title>My JSP 'aleft.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function show(id1,id2) {
			var option = document.getElementById(id1);
			var span = document.getElementById(id2);
			if(option.className == "option") {
				option.className = "option1";
			}else {
				option.className = "option";
			}
			if(span.innerHTML=='+') {
				span.innerHTML = '-';
				span.style.color = "rgba(0,0,0,0.5)";
			}else {
				span.innerHTML = '+';
				span.style.color = "rgba(255,255,255,0.5)";
			}
		}
	</script>
	<style>
		body {
			font-famliy:"微软雅黑";
		}
		th {
			text-align:left;
			color:white;
			background-color:rgba(0,0,0,0.4);
			border-radius:5px;
			width:130px;
		}
		th:active {
			background-color:rgba(0,0,0,0.5);
		}
		th:hover {
			cursor: pointer;
		}
		a {
			display:block;
			width:120px;
			height:20px;
			background-color:rgba(0,0,0,0.2);
			border-radius:10px;
			color:white;
			margin-top:5px;
			margin-bottom:5px;
			text-decoration:none;
		}
		a:active {
			background-color:rgba(0,0,0,0.3);
		}
		span {
			display:inline-block;
			margin-left:5px;
			width:20px;
			height:20px;
			text-align:center;
			line-height:20px;
			background-color:rgba(255,255,255,0.4);
			color:rgba(255,255,255,0.5);
			font-size:20px;
			border-radius:50%;
			margin-right:5px;
		}
		.top {
			text-align:center;
			color:white;
			margin-left:-8px;
			background-color:rgba(0,0,0,0.6);
			border-radius:3px;
			width:150px;
		}
		.option {
			display:none;
		}
		.option1 {
			display:'';
		}
	</style>

  </head>
  
  <body>
  	<div class="top"><h3>墨玉书城</h3></div>
    <table width="100%" cellspacing = "4" style="margin-top:-15px;">
    	<tr><th id="th1" onclick="show('tr1','s1')"><span id="s1">+</span>分类管理</th></tr>
    	<tr id="tr1" class="option">
	    	<td>
	    		<div align="center" style="margin-top:-5px;margin-bottom:-5px;">
	    			<a href="<c:url value='/admin/AdminCategoryServlet?method=findAll'/>" target="abody">查看分类</a>
	    			<a href="<c:url value='/adminjsps/admin/category/add.jsp'/>" target="abody">添加分类</a>
	    		</div>
	    	</td>
    	</tr>
    	<tr><th id="th2" onclick="show('tr2','s2')"><span id="s2">+</span>图书管理</th></tr>
    	<tr id="tr2" class="option">
	    	<td>
	    		<div align="center" style="margin-top:-5px;margin-bottom:-5px;">
	    			<a href="<c:url value='/admin/AdminBookServlet?method=findAll'/>" target="abody">查看图书</a>
	    			<a href="<c:url value='/admin/AdminBookServlet?method=addPre'/>" target="abody">添加图书</a>
	    		</div>
	    	</td>
    	</tr>
    	<tr><th id="th3" onclick="show('tr3','s3')"><span id="s3">+</span>订单管理</th></tr>
    	<tr id="tr3" class="option">
	    	<td>
	    		<div align="center" style="margin-top:-5px;margin-bottom:-5px;">
	    			<a href="<c:url value='/admin/AdminOrderServlet?method=findAll'/>" target="abody">所有订单</a>
	    			<a href="<c:url value='/admin/AdminOrderServlet?method=findByState&state=1'/>" target="abody">未付款订单</a>
	    			<a href="<c:url value='/admin/AdminOrderServlet?method=findByState&state=2'/>" target="abody">已付款订单</a>
	    			<a href="<c:url value='/admin/AdminOrderServlet?method=findByState&state=3'/>" target="abody">未收货订单</a>
	    			<a href="<c:url value='/admin/AdminOrderServlet?method=findByState&state=4'/>" target="abody">已完成订单</a>
	    		</div>
	    	</td>
    	</tr>
    </table>
    <div class="top" style="height:10px;"></div>
  </body>
</html>
