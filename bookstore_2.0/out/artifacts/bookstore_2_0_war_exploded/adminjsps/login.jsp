<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>login</title>
 	<style>
		.block {
			border-radius:10px;
			background-color:rgba(0,0,0,0.1);
			width:500px;
			height:300px;
		}
	</style>
</head>
<body>
	<div align="center">
		<div align="center" class="block">
		    <form action="<c:url value='/AdminServlet'/>" method="post">
		    	<input type="hidden" name="method" value="login"/>
		        <table align="center">
		            <tr><th colspan="2" align="center"><h1>登录</h1></th></tr>
		            <tr><td colspan="2" align="center"><p style="color:red;">${msg }</p></td></tr>
		            <tr></tr>
		            <tr>
		                <td>管理员账户</td>
		                <td><input name="admin" type="text" value=""></td>
		                <td><span id="s1"></span></td>
		            </tr>
		            <tr>
		                <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
		                <td><input name="password" type="password" value=""></td>
		                <td><span id="s2"></span></td>
		            </tr>
		            <tr>
		                <td></td>
		                <td><input type="submit" value="进入后台"></td>
		            </tr>
		        </table>
		    </form>
	    </div>
	</div>
</body>
</html>
