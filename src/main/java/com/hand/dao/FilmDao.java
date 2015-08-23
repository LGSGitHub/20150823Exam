package com.hand.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hand.entity.Film;

public interface FilmDao {

	public void add(Connection conn, Film film) throws SQLException;

	public void update(Connection conn,Film film) throws SQLException;

	public void delete(Connection conn, Film film) throws SQLException;

	public ResultSet get(Connection conn) throws SQLException;

}
