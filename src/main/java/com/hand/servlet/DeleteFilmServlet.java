package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.dao.impl.FilmDaoImpl;
import com.hand.entity.Film;
import com.hand.util.ConnectionFactory;

/**
 * Servlet implementation class DeleteFilmServlet
 */
public class DeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFilmServlet() {
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
		String film_id = request.getParameter("film_id");
		System.out.println(film_id);
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		Film film = new Film();
		film.setFilm_id(Long.parseLong(film_id));
		FilmDaoImpl fdi = new FilmDaoImpl();
		try {
			fdi.delete(conn, film);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() +"/getFilmServlet");
	}

}
