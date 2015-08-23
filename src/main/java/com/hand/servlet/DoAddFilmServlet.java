package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.dao.impl.FilmDaoImpl;
import com.hand.dao.impl.LanguageDaoImpl;
import com.hand.entity.Film;
import com.hand.util.ConnectionFactory;

/**
 * Servlet implementation class DoAddFilmServlet
 */
public class DoAddFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAddFilmServlet() {
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
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String language = request.getParameter("language");
		String language_id = null;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		LanguageDaoImpl ldi = new LanguageDaoImpl();
		ResultSet rs = ldi.getLanguageId(conn, language);
		
		try {
			language_id = rs.getString("language_id");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Film film = new Film();
		film.setTitle(title);
		film.setDescription(description);
		film.setLanguage_id(Long.parseLong(language_id));
		
		FilmDaoImpl fd = new FilmDaoImpl();
		try {
			fd.add(conn, film);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
		
	}

}
