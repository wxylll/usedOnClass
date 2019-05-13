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
		img {
			width:90;
			height:125;
			margin-top:10px;
			margin-bottom:10px;
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
			background-color:rgba(0,0,0,0.01);
			border:5px solid white;
			border-radius:10px;
		}
		.buy {
			width:100px;
			height:30px;
			color:white;
			display:inline-block;
			text-decoration:none;
			border-radius:10px;
			background-color:rgba(0,0,0,0.5);
		}
		.buy:hover {
			background-color:rgba(0,0,0,0.7);
		}
		.cart {
			width:300px;
			height:300px;
			border:10px solid rgba(0,0,0,0.2);
			border-radius:50%;
		}
		#cart {
			width:200px;
			height:200px;
			margin-top:50px;
			margin-left:-10px;
			opacity:0.5;
		}
		.under {
			padding-top:20px;
			font-family: "微软雅黑";
			color:rgba(0,0,0,0.3);
		}
	</style>

  </head>
  
  <body>
  	<c:choose>
  		<c:when test="${empty sessionScope.cart.cartItems }">
  			<div align="center" style="padding-top:50px;">
  				<div class="cart" ><img id="cart" src="<c:url value='/cart_img/cart.jpg'/>"></div>
  			</div>
  			<div class="under" align="center"><h1>&nbsp;&nbsp;&nbsp;空空如也...</h1></div>
  		</c:when>
  		<c:otherwise>
			<table cellspacing="0" style="width:100%;">
				<tr><td colspan="7" style="background-color:#0c212b;color:white;">我的购物车<a style="color:red;float:right;" href="<c:url value='/CartServlet?method=clear'/>">清空购物车</a></td></tr>
				<tr id="sub"><th>图片</th><th>书名</th><th>作者</th><th>单价</th><th>数量</th><th>小计</th><th>操作</th></tr>
				<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
					<tr>
						<td class="t1"><img src="<c:url value='/${cartItem.book.image }'/>"/></td>
						<td class="t2">${cartItem.book.bname }</td>
						<td class="t1">${cartItem.book.author }</td>
						<td class="t2">${cartItem.book.price }</td>
						<td class="t1">${cartItem.count }</td>
						<td class="t2">${cartItem.subtotal }</td>
						<td class="t1"><a style="color:red;" href="<c:url value='/CartServlet?method=delete&bid=${cartItem.book.bid }'/>">删除</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td  id="sub" colspan="7">
						合计：${sessionScope.cart.total }元
					</td>
				</tr>
				<tr>
					<td colspan="7" style="padding-top:10px;">
						<a class="buy" href="<c:url value='/OrderServlet?method=add'/>">一键购买</a>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
  </body>
</html>
