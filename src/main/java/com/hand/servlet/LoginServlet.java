package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.dao.impl.CustomerDaoImpl;
import com.hand.util.ConnectionFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("customerName");
		String password = request.getParameter("password");
		
		String returnUri = request.getParameter("return_uri");
		
		System.out.println("客户名===》" + name);
		System.out.println("客户名===》" + password);
		
		RequestDispatcher rd = null;
		
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		ResultSet rs;
		if(name == null || password == null)
		{
			request.setAttribute("msg", "客户名或者密码为空！！！");
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		else{
			
			if(cdi.isExist(conn, name,password))
			{
				//表明当前用户处于登录状态
				request.getSession().setAttribute("flag", "login_success");
				request.getSession().setAttribute("username", name);
				rs = cdi.getCustomer(conn);
				StringBuilder sb = new StringBuilder();
				List<String> list = new ArrayList<String>();
				try {
					//往sb里添加json格式的数据
					sb.append("[");
					while(rs.next())
					{
						sb.append("{\"first_name\" : \" "+rs.getString("first_name") + "\",");
						sb.append("\"last_name\" : \"" +rs.getString("last_name") + "\",");
						sb.append("\"address_id\":\""+rs.getString("address_id")+"\",");
						sb.append("\"email\":\""+rs.getString("email")+"\",");
						sb.append("\"customer_id\":\""+rs.getString("customer_id")+"\",");
						sb.append("\"last_update\":\""+rs.getString("last_update")+"\"},");
						
						list.add(rs.getString("first_name"));
						list.add(rs.getString("last_name"));
						list.add(rs.getString("address_id"));
						list.add(rs.getString("email"));
						list.add(rs.getString("customer_id"));
						list.add(rs.getString("last_update"));
					}
//					sb.setLength(sb.length() - 1);
					//删除最后没用的逗号
					sb.deleteCharAt(sb.length()-1);
					sb.append("]");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//把stringbuilder转为String
				String str = sb.toString();
				//传到处理数据的页面
				request.setAttribute("customerData", str);
				request.setAttribute("list", list);
				
				if(returnUri != null)
				{
					rd = request.getRequestDispatcher(returnUri);
					rd.forward(request, response);
				}
				else{
					rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}
			else{
				request.getSession().setAttribute("flag", "login_error");
				request.setAttribute("msg", "用户名输入错误");
				rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				
			}
		}
	}

}
