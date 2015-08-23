package com.hand.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PermissionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PermissionFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("permissionFilter的初始化方法");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("permissionFilter的do方法");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String servletPath = req.getServletPath();
		
		HttpSession session = req.getSession();
		String flag = (String) session.getAttribute("flag");
		

		if (servletPath != null
				&& (servletPath.equals("/login.jsp") 
						|| (servletPath.equals("/LoginServlet")))) {
			System.out.println("第一个if");
			chain.doFilter(request, response);
		}else {
			//如果用户处于登录状态则可以直接进行访问
			if (flag != null && flag.equals("login_success")) {
				System.out.println("第二个if" + flag);
				chain.doFilter(request, response);
			}
			else if (flag != null && flag.equals("login_error")) {
				System.out.println("第三个if");
				req.setAttribute("msg", "登录失败，请重新登录");
				req.setAttribute("return_uri", servletPath);
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			} else {
				System.out.println("第si个if");
				req.setAttribute("msg", "您尚未登录");
				req.setAttribute("return_uri", servletPath);
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			}
		}

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
