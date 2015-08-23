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

import com.hand.dao.impl.FilmDaoImpl;
import com.hand.dao.impl.LanguageDaoImpl;
import com.hand.entity.Film;
import com.hand.util.ConnectionFactory;

/**
 * Servlet implementation class UpdateFilmServlet
 */
public class UpdateFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFilmServlet() {
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
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		Film film = new Film();
		film.setFilm_id(Long.parseLong(film_id));
		ResultSet rsF;
		FilmDaoImpl fdi = new FilmDaoImpl();
		List<String> filmList = new ArrayList<String>();
		
		try {
			rsF = fdi.getFilmById(conn, film);
//			System.out.println("test+++++++++++++");
//			System.out.println(rsF.getString("title"));
//			System.out.println(rsF.getString("description"));
			String title = rsF.getString("title");
			String description = rsF.getString("description");
			filmList.add(title);
			filmList.add(description);
//			while(rsF.next())
//			{
//				filmList.add(rsF.getString("title"));
//				filmList.add(rsF.getString("description"));
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LanguageDaoImpl ldi = new LanguageDaoImpl();
		RequestDispatcher rd = null;
		ResultSet rsL;
		List<String> languageList = new ArrayList<String>();
		try {
			rsL = ldi.getLanguage(conn);
			while(rsL.next())
			{
				languageList.add(rsL.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("film_id", film_id);
		request.setAttribute("filmList", filmList);
		request.setAttribute("languageList", languageList);
		rd = request.getRequestDispatcher("updateFilm.jsp");
		rd.forward(request, response);
		
	}

}
