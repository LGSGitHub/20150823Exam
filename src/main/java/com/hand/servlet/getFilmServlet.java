package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.dao.impl.FilmDaoImpl;
import com.hand.util.ConnectionFactory;

/**
 * Servlet implementation class getFilmServlet
 */
public class getFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDaoImpl fdi = new FilmDaoImpl();
		RequestDispatcher rd = null;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		try {

			ResultSet rs = fdi.get(conn);
			List<String> list = new ArrayList<String>();
			while(rs.next())
			{
				list.add(rs.getString("film_id"));
				list.add(rs.getString("title"));
				list.add(rs.getString("description"));
				list.add(rs.getString("name"));
			}
			request.setAttribute("list", list);
			rd = request.getRequestDispatcher("filmsInfo.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
