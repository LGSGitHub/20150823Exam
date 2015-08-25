<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>首页</title>
<link href="bootstrap.min.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.costomerList div
	{
		border:2px solid red;
	}
</style>
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
		<div id="menu"
			style="font-size: 20px; width: 200px; height: 800px; float: left;">
			<ul class="nav" role="tablist">
				<li role="presentation"><a href="#profile" id="customerManage"
					aria-controls="profile" role="tab" data-toggle="tab">
						Customer管理</a></li>
				<li role="presentation"><a href="#messages"
					aria-controls="messages" role="tab" data-toggle="tab">Film设置</a></li>
			</ul>
		</div>
		<div class="tab-content"">
			<div role="tabpanel" class="tab-pane" id="profile"
				style="height: 50px; background-color: #cccccc; font-size: 20px;">
				<div class="col-md-4">客户列表</div>
				<div class="col-md-6">
					<button>
						<a href="<%=request.getContextPath()%>/AddFilmServlet"">新建</a>
					</button>
				</div>

				<div>
					<div style="padding-top: 30px;" id="costomerList">
						<p>
							first_name:<span id="fname"></span>
						</p>
						<br />
						<p>
							last_name:<span id="lname"></span>
						</p>
						<div class="col-sm-1"><strong>操作</strong></div>
						<div class="col-sm-1">First_Name</div>
						<div class="col-sm-1">Last_Name</div>
						<div class="col-sm-2">Address</div>
						<div class="col-sm-2">Email</div>
						<div class="col-sm-1">Customer_id</div>
						<div class="col-sm-2">LastUpdate</div>
						<%-- <table>
							<tr>
								<td>first_name</td>
								<td>last_name</td>
								<td>address</td>
								<td>email</td>
								<td>customer_id</td>
								<td>last_update</td>
							</tr>
							<%
								String str = request.getAttribute("customerData").toString();
								List<String> list = new ArrayList<String>();
								list = (ArrayList) request.getAttribute("list");
								for (int i = 0; i < list.size(); i += 6) {
							%>
							<tr>
								<td><%=list.get(i)%></td>
								<td><%=list.get(i + 1)%></td>
								<td><%=list.get(i + 2)%></td>
								<td><%=list.get(i + 3)%></td>
								<td><%=list.get(i + 4)%></td>
								<td><%=list.get(i + 5)%></td>
								<td><a
									href="<%=request.getContextPath()%>/UpdateFilmServlet?film_id=<%=list.get(i)%>">编辑</a></td>
								<td><a
									href="<%=request.getContextPath()%>/DeleteFilmServlet?film_id=<%=list.get(i)%>">删除</a></td>
							<tr />
							<%
								}
							%>
						</table> --%>
					</div>


					<!--默认分页-->
					<nav>
					<ul class="pagination pagination-lg">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
					</nav>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="messages">这是第三个tab</div>
		</div>


	</div>
	<script src="jquery-2.1.4.min.js"></script>
	<script src="bootstrap.min.js"></script>
	<script>
		
		$(customerManage).click(function(){
			getList('<%=request.getAttribute("customerData").toString()%>');
		})
		function getList(str) {
			var obj = eval("(" + str + ")");
			//document.getElementById("fname").innerHTML = obj.employees[1].firstName;
			var json = JSON.parse(str);
			for (i = 0; i < json.length; i++) {
				document.getElementById("fname").innerHTML = obj[i].first_name;
				document.getElementById("lname").innerHTML = obj[i].last_name;
				var fname = "<div>"+ obj[i].first_name + "</div>";
				var lname = "<div"+ obj[i].last_name + "</div>";
				var address_id = "<div>"+ obj[i].address_id + "</div>";
				var email = "<div>"+ obj[i].email + "</div>";
				var customer_id = "<div>"+ obj[i].customer_id + "</div>";
				var lastUpdate = "<div>"+ obj[i].last_update + "</div>";
				$("costomerList").append(fname,lname,address_id,email,customer_id,lastUpdate);
			}
		}
	</script>
</body>
</html>