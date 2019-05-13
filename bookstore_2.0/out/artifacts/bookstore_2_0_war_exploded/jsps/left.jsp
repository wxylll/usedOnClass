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

    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link href="jsps/templatemo_style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="background-color: white">
	  <div>
		  <div><h2>悦阅网上书城</h2></div>
		  <div><a target="body" href="<c:url value='/BookServlet?method=findAll'/>">全部分类</a></div>
		  <c:forEach items="${categoryList }" var="category">
			  <div><a target="body" href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid }'/>">${category.cname }</a></div>
		  </c:forEach>
	  </div>
	  <div class="templatemo_sidebar_wrapper float_l">
		  <div class="templatemo_sidebar_top"></div>
		  <div class="templatemo_sidebar">

			  <div class="sidebar_box">

				  <h2>悦阅网上书城</h2>
				  <div class="sidebar_box_content">

					  <ul class="categories_list">
						  <li><a target="body" href="<c:url value='/BookServlet?method=findAll'/>">全部分类</a></li>
							<c:forEach items="${categoryList }" var="category">
							  <li><a target="body" href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid }'/>">${category.cname }</a></li>
							</c:forEach>
					  </ul>

				  </div>
			  </div>
		  </div> <div class="templatemo_sidebar_bottom"></div> <!-- end of sidebar -->
	  </div>
  </body>
</html>
