<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			width:50;
			height:80;
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
  	<div align="center"><h3>订单管理</h3></div>
	  	<c:if test="${empty orderList }">
	  		<div align="center">
	  			<hr>
	  			<h4 style="color:rgba(0,0,0,0.3);">无数据</h4>
	  		</div>
	  	</c:if>
	  <c:forEach items="${orderList }" var="order">
			<table cellspacing="0" style="width:100%;">
				<tr>
					<td colspan="6" style="background-color:#0c212b;color:white;text-align:left;">
						订单编号：${order.oid }&nbsp;&nbsp;
						成交时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime }"/>&nbsp;&nbsp;
						金额：<span style="color:red;">${order.total }元</span>
						<div style="float:right;margin-right:10px;">
							<c:choose>
								<c:when test="${order.state eq 1 }">
									<span style="color:yellow;">待支付</span>
								</c:when>
								<c:when test="${order.state eq 2 }">
									<a href="<c:url value='/admin/AdminOrderServlet?method=send&oid=${order.oid }'/>"  onclick="return confirm('确定发货吗？')" style="color:red;">发货</a>
								</c:when>
								<c:when test="${order.state eq 3 }">
									<span style="color:green;">待收货</span>
								</c:when>
								<c:when test="${order.state eq 4 }">
									<span>交易成功</span>
								</c:when>
							</c:choose>
						</div>
					</td>
				</tr>
				<c:forEach items="${order.orderItemList }" var="orderItem">
					<tr>
						<td class="t1"><img src="<c:url value='/${orderItem.book.image }'/>"/></td>
						<td class="t2">书名：${orderItem.book.bname }</td>
						<td class="t1">作者：${orderItem.book.author }</td>
						<td class="t2">单价：${orderItem.book.price }</td>
						<td class="t1">数量：${orderItem.count }</td>
						<td class="t2">小计：${orderItem.subtotal }</td>
					</tr>
				</c:forEach>
				<tr><td colspan="6" style="background-color:#0c212b;height:5px;"></td></tr>
			</table>
		</c:forEach>
  </body>
</html>
