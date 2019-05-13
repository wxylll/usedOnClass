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
		.bank {
			vertical-align:-30px;
			margin-top:10px;
			width:250px;
			height:50px;
			border:1px solid black;
			background-position:10px 10px;
		}
	</style>
	<script>
		function pay() {
			var address = document.getElementById("address").value;
			if(address!=null&&address!=undefined&&address!="") {
				document.getElementById('form').submit();
			}else {
				alert("请先填写收货地址!");
			}
		}
	</script>

  </head>
  
  <body>
	<table cellspacing="0" style="width:100%;">
		<tr><td colspan="6"><h3>当前订单</h3></td></tr>
		<tr>
			<td colspan="6" style="background-color:#0c212b;color:white;text-align:left;">
				订单编号：${order.oid }&nbsp;&nbsp;
				成交时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime }"/>&nbsp;&nbsp;
				金额：<span style="color:red;">${order.total }元</span>
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
		<tr>
			<td colspan="6" style="background-color:#0c212b;"></td>
		</tr>
	</table>
	<form method="post" action="<c:url value='/OrderServlet'/>" id="form" target="_parent">
		<input type="hidden" name="method" value="pay"/>
		<input type="hidden" name="oid" value="${order.oid }"/>
		<table cellspacing="0" style="width:100%;">
			<tr style="height:20px;"></tr>
			<tr><td colspan="6">收货地址：<input id="address" style="width:600px;" type="text" name="address" value=""/></td></tr>
			<tr style="height:20px;"></tr>
			<tr>
				<td colspan="3" style="text-align:right;"><div><input type="radio" checked name="pd_FrpId" value="ICBC-NET-B2C"/>工商银行&nbsp;&nbsp;<img class="bank" src="<c:url value='/bank_image/zggsyh.png'/>"/>&nbsp;&nbsp;</div></td>
				<td colspan="3" style="text-align:left;"><div>&nbsp;&nbsp;<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行&nbsp;&nbsp;<img class="bank" src="<c:url value='/bank_image/zgyh.png'/>"/></div></td>
			</tr>
			<tr>
				<td colspan="3" style="text-align:right;"><div><input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行&nbsp;&nbsp;<img class="bank" src="<c:url value='/bank_image/zgnyyh.png'/>"/>&nbsp;&nbsp;</div></td>
				<td colspan="3" style="text-align:left;"><div>&nbsp;&nbsp;<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行&nbsp;&nbsp;<img class="bank" src="<c:url value='/bank_image/zgjsyh.png'/>"/></div></td>
			</tr>
			<tr>
				<td colspan="3" style="text-align:right;"><div><input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行&nbsp;&nbsp;<img class="bank" src="<c:url value='/bank_image/jtyh.png'/>"/>&nbsp;&nbsp;</div></td>
				<td colspan="3" style="text-align:left;"></td>
			</tr>
			<tr>
				<td colspan="6" style="padding-top:10px;">
					<a class="buy" onclick="pay()">直接结算</a>
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>