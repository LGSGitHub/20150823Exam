package com.hand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hand.dao.FilmDao;
import com.hand.entity.Film;

public class FilmDaoImpl implements FilmDao {

	public void add(Connection conn, Film film) throws SQLException {
		PreparedStatement ps = conn.prepareCall("insert into film(title,description,language_id) values(?,?,?)");
		ps.setString(1, film.getTitle());
		ps.setString(2, film.getDescription());
		ps.setLong(3, film.getLanguage_id());
		ps.execute();

	}

	public void update(Connection conn,Film film) throws SQLException {

		PreparedStatement ps = conn.prepareCall("update film set title=?,description=?,language_id=? where film_id=?");
		ps.setString(1, film.getTitle());
		ps.setString(2, film.getDescription());
		ps.setLong(3, film.getLanguage_id());
		ps.setLong(4, film.getFilm_id());
		ps.execute();
	}

	public void delete(Connection conn, Film film) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("delete from film where film_id=?");
		ps.setLong(1, film.getFilm_id());
		ps.execute();

	}

	public ResultSet get(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select film_id,title,description,name from film,language where film.language_id = language.language_id");
		return ps.executeQuery();
	}
	
	public ResultSet getFilmById(Connection conn,Film film) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement("select title,description,language_id from film where film_id=?");
		ps.setLong(1, film.getFilm_id());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			return rs;
		}
		return null;
	}

}
