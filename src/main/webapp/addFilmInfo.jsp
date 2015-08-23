<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加用户信息</title>
<link href="bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div style="height: 50px; background-color: #ADD8E6;">
			<div style="padding-top: 10px;" class="col-md-3 col-md-push-9">
				用户名：<%=request.getSession().getAttribute("username")%>
				<%
					String flag = "";
					Object obj = session.getAttribute("flag");
					if (obj != null) {
						flag = obj.toString();
					}
					if (flag.equals("login_success")) {
				%>
				<button>
					<a href="<%=request.getContextPath()%>/LogoutServlet">退出登陆</a>
				</button>
				<%
					} else {
				%>
				<button>
					<a href="<%=request.getContextPath()%>/login.jsp">登录</a>
				</button>
				<%
					}
				%>
			</div>
		</div>

		<div style="width: 200px; float: left; font-size: 20px;">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation"><a href="#"
					aria-controls="profile" role="tab" data-toggle="tab">应用设置</a></li>
				<li role="presentation"><a href="#"
					aria-controls="messages" role="tab" data-toggle="tab">管理设置</a></li>
				<li role="presentation"><a href="#"
					aria-controls="messages" role="tab" data-toggle="tab">数据配置</a></li>
				<li role="presentation"><a href="#"
					aria-controls="messages" role="tab" data-toggle="tab">主数据管理</a></li>
				<li role="presentation"><a href="#"
					aria-controls="messages" role="tab" data-toggle="tab">客户订单管理</a></li>
				<li role="presentation"><a href="#"
					aria-controls="messages" role="tab" data-toggle="tab">发货单管理</a></li>
			</ul>
		</div>

		<div>
			<div style="font-size: 30px;">创建Customer</div>
			<div style="margin:10px auto; width:800px;">
				<form action="<%=request.getContextPath()%>/DoAddFilmServlet"
					method="post">
					<table>
						<tr>
							<td>first_name</td>
							<td><input type="text" name="first_name" /></td>
						</tr>
						<tr>
							<td>last_name</td>
							<td><input type="text" name="last_name" /></td>
						</tr>
						<tr>
							<td>email</td>
							<td><input type="text" name="email" /></td>
						</tr>
						<tr>
							<td>address</td>
							<td><input type="text" name="address" /></td>
						</tr>
						<tr>
							<td><input type="submit" name="submit" value="submit" /></td>
							<td><input type="reset" name="reset" value="reset" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<script src="jquery-2.1.4.min.js"></script>
		<script src="bootstrap.min.js"></script>
</body>
</html>