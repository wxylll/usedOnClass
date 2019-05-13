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
	<meta charset="UTF-8">
	<title>悦阅书店登陆界面</title>
</head>
<style>
body{
	font-family:Arial, Helvetica, sans-serif,"宋体"; 
	font-size:12px;
	background:#FDFCF8;
}
.out{
	border-top:15px solid #FFFFFF;
	border-bottom:15px solid #FFFFFF;
	border-right:10px solid #FFFFFF;
	border-left:10px solid #FFFFFF;	
	background-color:#F5F5F5;
	height: 600px;
	width: 1300px;
	padding:20px 20px 20px 20px;
	position:absolute;
	left:300px;
	top:160px;		
}
.top{
	margin-left: 20px;
	background-color: red;
	width:66px;
	height:68px;
	float: left;
	margin-bottom: 20px;
	background:url(<c:url value="jsps/user/01.png"/>);
}
.head{
	width: 600px;
	height: 60px;
	font-size:50px;
	margin-left: 20px;
	float: left;
	padding-bottom:5px;
}
.pic{
	margin-top: 80px;
	clear: both;
	margin-left: 20px;
	width: 520px;
	height:350px;
	background:url(<c:url value="jsps/user/02.png"/> );
	float: left;
	background-repeat:no-repeat;
}
#head1{
	font-size:18px;
	font-weight:bold;	
	}
#register{
	margin-top:5px;
	float: right;
}
.main{
	border: 1px solid darkgray;
	margin-top: 20px;
	padding: 20px 20px 15px 15px;
	width: 550px;
	height: 400px;
	margin-left:20px;
	background-color: #FFFFFF;
	float: left;	
}
.textbox{
	width:300px;
	height: 250px;
	background-color: #FFFFFF;
	margin-top: 50px;
	margin-left: 150px;
	padding: 20px 30px 30px 30px;
	font-size: 20px;
}
.textbox dl{
	width:100%;
	height:40px;	
	}
.textbox dt{
	float:left;
	text-align:right;
	}
.textbox dd{
	float:left;
	}

.input1{
	width:100px;
	height: 40px;
	background:orange;
}

</style>
<body>
<form id="target" action="<c:url value='/jsps/user/login.jsp'/>" target="_parent">
	<input type="hidden" name="msg" value="${msg }"/>
</form>
<c:choose>
	<c:when test="${!empty isForward }">
		<script type="text/javascript">
            alert('您还没有登录！');
            document.getElementById("target").submit();
		</script>
	</c:when>
	<c:otherwise>
		<div class="out">
			<div class="top"></div>
			<div class="head">悦阅书城</div>
			<div class="pic"></div>
			<div class="main">
				<div id = "head1">悦阅会员登陆</div>
				<div id="register"><a href="<c:url value="jsps/user/regist.jsp"/>"><button>立即注册</button></a></div>
				<div class=textbox>
					<p style="color: red;">${msg}</p>
					<form id="form1" name="form1" action="<c:url value='/UserServlet'/>" method="post">
						<input type="hidden" name="method" value="login"/>
						<dl>
							<dt>用户名:</dt>
							<dd><input type="text" name="username" >
								<br/>
								<span id="s1" style="color:red;">${errors.username }</span></dd>
						</dl>
						<dl>
							<dt>密&nbsp;&nbsp;&nbsp;&nbsp;码:</dt>
							<dd><input type="password" name="password" >
								<br/>
								<span id="s2" style="color:red;">${errors.password }</span></dd>
						</dl>
						<input type="submit" name="submit" value="立即登陆" class="input1">
					</form>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
</body>
</html>
